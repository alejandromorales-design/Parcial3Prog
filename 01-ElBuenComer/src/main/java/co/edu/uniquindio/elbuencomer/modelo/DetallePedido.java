package co.edu.uniquindio.elbuencomer.modelo;

/**
 * Una linea de un pedido: el producto solicitado, la cantidad y el precio
 * unitario en el momento exacto de la compra.
 * Se modela como record (inmutable) para que, si despues cambia el precio
 * base de un producto, los pedidos ya realizados no se vean afectados.
 *
 * @param producto       producto solicitado
 * @param cantidad       unidades solicitadas
 * @param precioUnitario precio de venta congelado al agregar el producto
 */
public record DetallePedido(Producto producto, int cantidad, double precioUnitario) {

    /**
     * @return cantidad por precio unitario
     */
    public double subtotal() {
        return cantidad * precioUnitario;
    }

    @Override
    public String toString() {
        return producto.getNombre() + " x" + cantidad + " - $" + (long) precioUnitario
                + " c/u - Subtotal: $" + (long) subtotal();
    }
}
