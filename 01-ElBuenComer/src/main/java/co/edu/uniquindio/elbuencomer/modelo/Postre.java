package co.edu.uniquindio.elbuencomer.modelo;

/**
 * Postre del menu. Su precio de venta tiene un incremento del 15 % sobre el
 * precio base, mas un 5 % adicional sobre el precio base si requiere refrigeracion.
 * Condiciones de calidad: peso entre 80 y 500 gramos; temperatura entre
 * 0 y 6 grados si requiere refrigeracion, o entre 15 y 30 grados si no.
 */
public class Postre extends Producto {

    private int pesoGramos;
    private double temperaturaAlmacenamiento;
    private boolean requiereRefrigeracion;

    /**
     * Crea un postre.
     *
     * @param codigo                    codigo unico del producto
     * @param nombre                    nombre del postre
     * @param descripcion               descripcion del postre
     * @param precioBase                precio base en pesos
     * @param tiempoEstimadoPreparacion tiempo de preparacion en minutos
     * @param pesoGramos                peso en gramos
     * @param temperaturaAlmacenamiento temperatura de almacenamiento en grados
     * @param requiereRefrigeracion     true si debe mantenerse refrigerado
     */
    public Postre(String codigo, String nombre, String descripcion, double precioBase,
                  int tiempoEstimadoPreparacion, int pesoGramos, double temperaturaAlmacenamiento,
                  boolean requiereRefrigeracion) {
        super(codigo, nombre, descripcion, precioBase, tiempoEstimadoPreparacion);
        this.pesoGramos = pesoGramos;
        this.temperaturaAlmacenamiento = temperaturaAlmacenamiento;
        this.requiereRefrigeracion = requiereRefrigeracion;
    }

    /**
     * @return precio base mas el 15 %, y un 5 % adicional si requiere refrigeracion
     */
    @Override
    public double calcularPrecioVenta() {
        double precio = precioBase * 1.15;
        if (requiereRefrigeracion) {
            precio = precio + (precioBase * 0.05);
        }
        return precio;
    }

    @Override
    public CategoriaProducto getCategoria() {
        return CategoriaProducto.POSTRE;
    }

    /**
     * @return true si el peso y la temperatura de almacenamiento estan en el rango permitido
     */
    @Override
    public boolean cumpleCondicionesCalidad() {
        boolean pesoValido = pesoGramos >= 80 && pesoGramos <= 500;
        boolean temperaturaValida;
        if (requiereRefrigeracion) {
            temperaturaValida = temperaturaAlmacenamiento >= 0 && temperaturaAlmacenamiento <= 6;
        } else {
            temperaturaValida = temperaturaAlmacenamiento >= 15 && temperaturaAlmacenamiento <= 30;
        }
        return pesoValido && temperaturaValida;
    }

    public int getPesoGramos() { return pesoGramos; }
    public double getTemperaturaAlmacenamiento() { return temperaturaAlmacenamiento; }
    public boolean isRequiereRefrigeracion() { return requiereRefrigeracion; }
}
