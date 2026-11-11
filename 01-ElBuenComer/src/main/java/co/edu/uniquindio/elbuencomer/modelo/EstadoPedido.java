package co.edu.uniquindio.elbuencomer.modelo;

/**
 * Estados posibles de un pedido a lo largo de su ciclo de vida:
 * PENDIENTE pasa a CONFIRMADO y luego a ENTREGADO; un pedido PENDIENTE o
 * CONFIRMADO tambien puede pasar a CANCELADO.
 */
public enum EstadoPedido {
    PENDIENTE,
    CONFIRMADO,
    ENTREGADO,
    CANCELADO
}
