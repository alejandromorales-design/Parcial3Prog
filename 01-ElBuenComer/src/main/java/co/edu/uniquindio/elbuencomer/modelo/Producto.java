package co.edu.uniquindio.elbuencomer.modelo;

/**
 * Clase base abstracta de PlatoFuerte, Bebida y Postre.
 * Contiene los datos que comparten los tres productos e implementa
 * VerificableCalidad sin dar cuerpo al metodo, obligando a cada subclase a
 * resolverlo con sus propias reglas.
 */
public abstract class Producto implements VerificableCalidad {

    protected String codigo;
    protected String nombre;
    protected String descripcion;
    protected double precioBase;
    /** Tiempo estimado de preparacion en minutos. */
    protected int tiempoEstimadoPreparacion;

    /**
     * Crea un producto con los datos comunes a todas las categorias.
     *
     * @param codigo                    codigo unico del producto
     * @param nombre                    nombre del producto
     * @param descripcion               descripcion del producto
     * @param precioBase                precio base en pesos
     * @param tiempoEstimadoPreparacion tiempo de preparacion en minutos
     */
    public Producto(String codigo, String nombre, String descripcion, double precioBase,
                     int tiempoEstimadoPreparacion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioBase = precioBase;
        this.tiempoEstimadoPreparacion = tiempoEstimadoPreparacion;
    }

    /**
     * Calcula el precio de venta aplicando el incremento de cada categoria.
     *
     * @return precio de venta en pesos
     */
    public abstract double calcularPrecioVenta();

    /**
     * Permite identificar la categoria sin usar instanceof en el resto del codigo.
     *
     * @return categoria del producto
     */
    public abstract CategoriaProducto getCategoria();

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public double getPrecioBase() { return precioBase; }
    public int getTiempoEstimadoPreparacion() { return tiempoEstimadoPreparacion; }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " (" + getCategoria() + ") - Precio venta: $"
                + (long) calcularPrecioVenta();
    }
}
