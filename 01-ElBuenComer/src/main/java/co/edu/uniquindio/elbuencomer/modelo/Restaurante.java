package co.edu.uniquindio.elbuencomer.modelo;

import java.util.ArrayList;

/**
 * Clase raiz del sistema El Buen Comer. Contiene el menu de productos, los
 * clientes y los pedidos, y resuelve las funcionalidades requeridas y las
 * consultas de ventas descritas en el enunciado.
 */
public class Restaurante {

    private String nombreComercial;
    private String nit;
    private String direccion;
    private ArrayList<Producto> productos;
    private ArrayList<Cliente> clientes;
    private ArrayList<Pedido> pedidos;

    /**
     * Crea el restaurante con las listas de productos, clientes y pedidos vacias.
     *
     * @param nombreComercial nombre comercial
     * @param nit             NIT del restaurante
     * @param direccion       direccion del establecimiento
     */
    public Restaurante(String nombreComercial, String nit, String direccion) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;
        this.productos = new ArrayList<Producto>();
        this.clientes = new ArrayList<Cliente>();
        this.pedidos = new ArrayList<Pedido>();
    }

    /**
     * Funcionalidad 1: registra un producto en el menu sin duplicar codigos.
     *
     * @param producto producto a registrar
     * @return true si se registro; false si el codigo ya existe
     */
    public boolean registrarProducto(Producto producto) {
        if (buscarProductoPorCodigo(producto.getCodigo()) != null) {
            return false;
        }
        productos.add(producto);
        return true;
    }

    /**
     * @param codigo codigo del producto
     * @return el producto, o null si no existe
     */
    public Producto buscarProductoPorCodigo(String codigo) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo().equals(codigo)) {
                return productos.get(i);
            }
        }
        return null;
    }

    public ArrayList<Producto> listarProductos() {
        return productos;
    }

    /**
     * Funcionalidad 2: registra un cliente sin duplicar documentos.
     *
     * @param cliente cliente a registrar
     * @return true si se registro; false si el documento ya existe
     */
    public boolean registrarCliente(Cliente cliente) {
        if (buscarClientePorDocumento(cliente.getNumeroDocumento()) != null) {
            return false;
        }
        clientes.add(cliente);
        return true;
    }

    /**
     * @param documento documento del cliente
     * @return el cliente, o null si no existe
     */
    public Cliente buscarClientePorDocumento(String documento) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getNumeroDocumento().equals(documento)) {
                return clientes.get(i);
            }
        }
        return null;
    }

    public ArrayList<Cliente> listarClientes() {
        return clientes;
    }

    /**
     * Funcionalidad 3: crea un pedido PENDIENTE para un cliente registrado y
     * lo agrega al historial del restaurante y del cliente.
     *
     * @param codigo           codigo unico del pedido
     * @param documentoCliente documento del cliente
     * @param modalidad        consumo en restaurante o domicilio
     * @return el pedido creado, o null si el codigo ya existe o el cliente no esta registrado
     */
    public Pedido crearPedido(String codigo, String documentoCliente, ModalidadEntrega modalidad) {
        if (buscarPedidoPorCodigo(codigo) != null) {
            return null;
        }
        Cliente cliente = buscarClientePorDocumento(documentoCliente);
        if (cliente == null) {
            return null;
        }
        Pedido pedido = new Pedido(codigo, cliente, modalidad);
        pedidos.add(pedido);
        cliente.agregarPedidoAlHistorial(pedido);
        return pedido;
    }

    /**
     * @param codigo codigo del pedido
     * @return el pedido, o null si no existe
     */
    public Pedido buscarPedidoPorCodigo(String codigo) {
        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getCodigo().equals(codigo)) {
                return pedidos.get(i);
            }
        }
        return null;
    }

    /**
     * Confirma un pedido. Primero valida lo que solo el restaurante puede
     * saber (cliente registrado y productos del menu); el resto de reglas
     * (cantidades, calidad y domicilio) las valida el propio pedido.
     *
     * @param codigo codigo del pedido
     * @return true si quedo CONFIRMADO
     */
    public boolean confirmarPedido(String codigo) {
        Pedido pedido = buscarPedidoPorCodigo(codigo);
        if (pedido == null) {
            return false;
        }
        if (buscarClientePorDocumento(pedido.getCliente().getNumeroDocumento()) == null) {
            return false;
        }
        for (int i = 0; i < pedido.getDetalles().size(); i++) {
            Producto p = pedido.getDetalles().get(i).producto();
            if (buscarProductoPorCodigo(p.getCodigo()) != p) {
                return false;
            }
        }
        return pedido.confirmar();
    }

    /**
     * @param codigo codigo del pedido
     * @return true si quedo ENTREGADO
     */
    public boolean marcarPedidoEntregado(String codigo) {
        Pedido pedido = buscarPedidoPorCodigo(codigo);
        if (pedido == null) {
            return false;
        }
        return pedido.marcarEntregado();
    }

    /**
     * @param codigo codigo del pedido
     * @return true si quedo CANCELADO
     */
    public boolean cancelarPedido(String codigo) {
        Pedido pedido = buscarPedidoPorCodigo(codigo);
        if (pedido == null) {
            return false;
        }
        return pedido.cancelar();
    }

    public ArrayList<Pedido> listarPedidos() {
        return pedidos;
    }

    /**
     * Funcionalidad 4: calcula el valor total de un pedido.
     *
     * @param codigo codigo del pedido
     * @return valor total, o -1.0 si el pedido no existe
     */
    public double calcularValorTotalPedido(String codigo) {
        Pedido pedido = buscarPedidoPorCodigo(codigo);
        if (pedido == null) {
            return -1.0;
        }
        return pedido.calcularValorTotal();
    }

    /**
     * Funcionalidad 5: consulta el historial de pedidos de un cliente.
     *
     * @param documentoCliente documento del cliente
     * @return lista de pedidos del cliente, o null si el cliente no existe
     */
    public ArrayList<Pedido> consultarHistorialPedidos(String documentoCliente) {
        Cliente cliente = buscarClientePorDocumento(documentoCliente);
        if (cliente == null) {
            return null;
        }
        return cliente.getHistorialPedidos();
    }

    /**
     * @return productos que cumplen las condiciones de calidad
     */
    public ArrayList<Producto> consultarProductosDisponibles() {
        ArrayList<Producto> disponibles = new ArrayList<Producto>();
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).cumpleCondicionesCalidad()) {
                disponibles.add(productos.get(i));
            }
        }
        return disponibles;
    }

    /**
     * @return productos que no cumplen las condiciones de calidad
     */
    public ArrayList<Producto> consultarProductosNoDisponibles() {
        ArrayList<Producto> noDisponibles = new ArrayList<Producto>();
        for (int i = 0; i < productos.size(); i++) {
            if (!productos.get(i).cumpleCondicionesCalidad()) {
                noDisponibles.add(productos.get(i));
            }
        }
        return noDisponibles;
    }

    /**
     * Suma las unidades vendidas de un producto en los pedidos que cuentan como venta.
     */
    private int calcularCantidadVendida(Producto producto) {
        int total = 0;
        for (int i = 0; i < pedidos.size(); i++) {
            Pedido pedido = pedidos.get(i);
            if (!pedido.cuentaComoVenta()) {
                continue;
            }
            for (int j = 0; j < pedido.getDetalles().size(); j++) {
                DetallePedido d = pedido.getDetalles().get(j);
                if (d.producto().getCodigo().equals(producto.getCodigo())) {
                    total = total + d.cantidad();
                }
            }
        }
        return total;
    }

    /**
     * @return el producto con mas unidades vendidas, o null si no hay productos
     */
    public Producto obtenerProductoConMayorCantidadVendida() {
        Producto mejor = null;
        int mejorCantidad = -1;
        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            int cantidadVendida = calcularCantidadVendida(p);
            if (cantidadVendida > mejorCantidad) {
                mejorCantidad = cantidadVendida;
                mejor = p;
            }
        }
        return mejor;
    }

    /**
     * @param categoria categoria a consultar
     * @return ingresos de esa categoria en pedidos confirmados o entregados
     */
    public double calcularIngresosPorCategoria(CategoriaProducto categoria) {
        double total = 0.0;
        for (int i = 0; i < pedidos.size(); i++) {
            Pedido pedido = pedidos.get(i);
            if (!pedido.cuentaComoVenta()) {
                continue;
            }
            for (int j = 0; j < pedido.getDetalles().size(); j++) {
                DetallePedido d = pedido.getDetalles().get(j);
                if (d.producto().getCategoria() == categoria) {
                    total = total + d.subtotal();
                }
            }
        }
        return total;
    }

    /**
     * @return la categoria que genera mayores ingresos
     */
    public CategoriaProducto obtenerCategoriaConMayoresIngresos() {
        double ingresosPlatoFuerte = calcularIngresosPorCategoria(CategoriaProducto.PLATO_FUERTE);
        double ingresosBebida = calcularIngresosPorCategoria(CategoriaProducto.BEBIDA);
        double ingresosPostre = calcularIngresosPorCategoria(CategoriaProducto.POSTRE);

        if (ingresosPlatoFuerte >= ingresosBebida && ingresosPlatoFuerte >= ingresosPostre) {
            return CategoriaProducto.PLATO_FUERTE;
        } else if (ingresosBebida >= ingresosPostre) {
            return CategoriaProducto.BEBIDA;
        } else {
            return CategoriaProducto.POSTRE;
        }
    }

    /**
     * @return ingresos propios del restaurante (venta de productos)
     */
    public double calcularIngresosPropios() {
        double total = 0.0;
        for (int i = 0; i < pedidos.size(); i++) {
            Pedido pedido = pedidos.get(i);
            if (!pedido.cuentaComoVenta()) {
                continue;
            }
            total = total + pedido.calcularValorProductos();
        }
        return total;
    }

    /**
     * @return valores correspondientes a domicilios (lo que cobran las empresas externas)
     */
    public double calcularIngresosDomicilios() {
        double total = 0.0;
        for (int i = 0; i < pedidos.size(); i++) {
            Pedido pedido = pedidos.get(i);
            if (!pedido.cuentaComoVenta()) {
                continue;
            }
            total = total + pedido.calcularCostoDomicilio();
        }
        return total;
    }

    public String getNombreComercial() { return nombreComercial; }
    public String getNit() { return nit; }
    public String getDireccion() { return direccion; }
}

