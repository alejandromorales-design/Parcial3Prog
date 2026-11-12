package co.edu.uniquindio.elbuencomer.modelo;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Pedido de un cliente, con una o varias lineas de producto (detalles), una
 * modalidad de entrega y, si es a domicilio, la empresa de transporte y la
 * distancia del recorrido.
 */
public class Pedido {

    private String codigo;
    private LocalDate fecha;
    private Cliente cliente;
    private ModalidadEntrega modalidadEntrega;
    private EstadoPedido estado;
    private ArrayList<DetallePedido> detalles;
    /** Empresa de domicilio asignada; null si es consumo en restaurante. */
    private ServicioDomicilio empresaDomicilio;
    /** Distancia del recorrido en km; 0 si es consumo en restaurante. */
    private double distanciaKm;

    /**
     * Crea un pedido en estado PENDIENTE con la fecha de hoy.
     *
     * @param codigo           codigo unico del pedido
     * @param cliente          cliente que realiza el pedido
     * @param modalidadEntrega consumo en restaurante o domicilio
     */
    public Pedido(String codigo, Cliente cliente, ModalidadEntrega modalidadEntrega) {
        this.codigo = codigo;
        this.fecha = LocalDate.now();
        this.cliente = cliente;
        this.modalidadEntrega = modalidadEntrega;
        this.estado = EstadoPedido.PENDIENTE;
        this.detalles = new ArrayList<DetallePedido>();
        this.empresaDomicilio = null;
        this.distanciaKm = 0.0;
    }

    /**
     * Agrega un producto al pedido congelando su precio de venta actual.
     * Solo se pueden agregar productos mientras el pedido este PENDIENTE.
     *
     * @param producto producto del menu
     * @param cantidad unidades solicitadas
     * @return true si se agrego; false si el pedido no esta PENDIENTE o el producto es null
     */
    public boolean agregarDetalle(Producto producto, int cantidad) {
        if (estado != EstadoPedido.PENDIENTE || producto == null) {
            return false;
        }
        DetallePedido detalle = new DetallePedido(producto, cantidad, producto.calcularPrecioVenta());
        detalles.add(detalle);
        return true;
    }

    /**
     * Asigna la empresa de domicilio y la distancia del recorrido.
     * Solo aplica a pedidos a DOMICILIO que sigan PENDIENTES.
     *
     * @param empresa     empresa externa de domicilio
     * @param distanciaKm distancia del recorrido en kilometros
     * @return true si se asigno
     */
    public boolean asignarDomicilio(ServicioDomicilio empresa, double distanciaKm) {
        if (modalidadEntrega != ModalidadEntrega.DOMICILIO || estado != EstadoPedido.PENDIENTE) {
            return false;
        }
        this.empresaDomicilio = empresa;
        this.distanciaKm = distanciaKm;
        return true;
    }

    /**
     * Un pedido cuenta como venta solo si fue confirmado o entregado.
     * Los pendientes aun no son venta y los cancelados nunca lo seran.
     *
     * @return true si el pedido suma a los ingresos del restaurante
     */
    public boolean cuentaComoVenta() {
        return estado == EstadoPedido.CONFIRMADO || estado == EstadoPedido.ENTREGADO;
    }

    /**
     * @return suma de los subtotales de todos los productos
     */
    public double calcularValorProductos() {
        double total = 0.0;
        for (int i = 0; i < detalles.size(); i++) {
            total = total + detalles.get(i).subtotal();
        }
        return total;
    }

    /**
     * @return costo cobrado por la empresa de domicilio, o 0 si no aplica
     */
    public double calcularCostoDomicilio() {
        if (modalidadEntrega == ModalidadEntrega.DOMICILIO && empresaDomicilio != null) {
            return empresaDomicilio.calcularCostoEnvio(distanciaKm);
        }
        return 0.0;
    }

    /**
     * @return valor de los productos mas el costo del domicilio
     */
    public double calcularValorTotal() {
        return calcularValorProductos() + calcularCostoDomicilio();
    }

    /**
     * Verifica que el pedido pueda confirmarse: debe tener al menos un
     * producto, todas las cantidades deben ser mayores que cero, todos los
     * productos deben cumplir las condiciones de calidad y, si es a domicilio,
     * la empresa asignada debe aceptar el recorrido.
     *
     * @return true si se cumplen todas las condiciones
     */
    public boolean puedeConfirmarse() {
        if (detalles.isEmpty()) {
            return false;
        }
        for (int i = 0; i < detalles.size(); i++) {
            DetallePedido d = detalles.get(i);
            if (d.cantidad() <= 0) {
                return false;
            }
            if (!d.producto().cumpleCondicionesCalidad()) {
                return false;
            }
        }
        if (modalidadEntrega == ModalidadEntrega.DOMICILIO) {
            if (empresaDomicilio == null) {
                return false;
            }
            if (!empresaDomicilio.aceptaRecorrido(distanciaKm)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Confirma el pedido si esta PENDIENTE y es valido (se valida antes de mutar).
     *
     * @return true si quedo CONFIRMADO
     */
    public boolean confirmar() {
        if (estado != EstadoPedido.PENDIENTE) {
            return false;
        }
        if (!puedeConfirmarse()) {
            return false;
        }
        estado = EstadoPedido.CONFIRMADO;
        return true;
    }

    /**
     * Marca el pedido como entregado; solo es posible si esta CONFIRMADO.
     *
     * @return true si quedo ENTREGADO
     */
    public boolean marcarEntregado() {
        if (estado != EstadoPedido.CONFIRMADO) {
            return false;
        }
        estado = EstadoPedido.ENTREGADO;
        return true;
    }

    /**
     * Cancela el pedido; no es posible si ya fue entregado o ya estaba cancelado.
     *
     * @return true si quedo CANCELADO
     */
    public boolean cancelar() {
        if (estado == EstadoPedido.ENTREGADO || estado == EstadoPedido.CANCELADO) {
            return false;
        }
        estado = EstadoPedido.CANCELADO;
        return true;
    }

    public String getCodigo() { return codigo; }
    public LocalDate getFecha() { return fecha; }
    public Cliente getCliente() { return cliente; }
    public ModalidadEntrega getModalidadEntrega() { return modalidadEntrega; }
    public EstadoPedido getEstado() { return estado; }
    public ArrayList<DetallePedido> getDetalles() { return detalles; }
    public ServicioDomicilio getEmpresaDomicilio() { return empresaDomicilio; }
    public double getDistanciaKm() { return distanciaKm; }

    @Override
    public String toString() {
        return "Pedido " + codigo + " - " + fecha + " - Cliente: " + cliente.getNombreCompleto()
                + " - Estado: " + estado + " - " + detalles.size() + " producto(s) - Total: $"
                + (long) calcularValorTotal();
    }
}

