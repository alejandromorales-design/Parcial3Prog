package co.edu.uniquindio.elbuencomer.modelo;

import java.util.ArrayList;

/**
 * Cliente del restaurante con sus datos de contacto y su historial de pedidos.
 */
public class Cliente {

    private String numeroDocumento;
    private String nombreCompleto;
    private String telefono;
    private String correoElectronico;
    private String direccionResidencia;
    private ArrayList<Pedido> historialPedidos;

    /**
     * Crea un cliente con el historial de pedidos vacio.
     *
     * @param numeroDocumento     documento de identidad (unico)
     * @param nombreCompleto      nombre completo
     * @param telefono            telefono de contacto
     * @param correoElectronico   correo electronico
     * @param direccionResidencia direccion de residencia
     */
    public Cliente(String numeroDocumento, String nombreCompleto, String telefono,
                   String correoElectronico, String direccionResidencia) {
        this.numeroDocumento = numeroDocumento;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.direccionResidencia = direccionResidencia;
        this.historialPedidos = new ArrayList<Pedido>();
    }

    /**
     * Agrega un pedido al historial del cliente.
     *
     * @param pedido pedido realizado por el cliente
     */
    public void agregarPedidoAlHistorial(Pedido pedido) {
        historialPedidos.add(pedido);
    }

    public String getNumeroDocumento() { return numeroDocumento; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getTelefono() { return telefono; }
    public String getCorreoElectronico() { return correoElectronico; }
    public String getDireccionResidencia() { return direccionResidencia; }
    public ArrayList<Pedido> getHistorialPedidos() { return historialPedidos; }

    @Override
    public String toString() {
        return numeroDocumento + " - " + nombreCompleto + " - " + telefono + " - " + correoElectronico;
    }
}

