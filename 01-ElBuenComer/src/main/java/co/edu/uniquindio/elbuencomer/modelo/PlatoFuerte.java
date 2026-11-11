package co.edu.uniquindio.elbuencomer.modelo;

/**
 * Plato fuerte del menu. Su precio de venta tiene un incremento del 20 %
 * sobre el precio base.
 * Condiciones de calidad: peso entre 200 y 900 gramos, tiempo de preparacion
 * entre 10 y 60 minutos y control de coccion aprobado.
 */
public class PlatoFuerte extends Producto {

    private int pesoGramos;
    private TipoPreparacion tipoPreparacion;
    private boolean controlCoccionSuperado;

    /**
     * Crea un plato fuerte.
     *
     * @param codigo                    codigo unico del producto
     * @param nombre                    nombre del plato
     * @param descripcion               descripcion del plato
     * @param precioBase                precio base en pesos
     * @param tiempoEstimadoPreparacion tiempo de preparacion en minutos
     * @param pesoGramos                peso en gramos
     * @param tipoPreparacion           asado, frito, horneado o a la plancha
     * @param controlCoccionSuperado    true si supero el control de coccion
     */
    public PlatoFuerte(String codigo, String nombre, String descripcion, double precioBase,
                        int tiempoEstimadoPreparacion, int pesoGramos, TipoPreparacion tipoPreparacion,
                        boolean controlCoccionSuperado) {
        super(codigo, nombre, descripcion, precioBase, tiempoEstimadoPreparacion);
        this.pesoGramos = pesoGramos;
        this.tipoPreparacion = tipoPreparacion;
        this.controlCoccionSuperado = controlCoccionSuperado;
    }

    /**
     * @return precio base mas el 20 %
     */
    @Override
    public double calcularPrecioVenta() {
        return precioBase * 1.20;
    }

    @Override
    public CategoriaProducto getCategoria() {
        return CategoriaProducto.PLATO_FUERTE;
    }

    /**
     * @return true si el peso, el tiempo de preparacion y el control de coccion son validos
     */
    @Override
    public boolean cumpleCondicionesCalidad() {
        boolean pesoValido = pesoGramos >= 200 && pesoGramos <= 900;
        boolean tiempoValido = tiempoEstimadoPreparacion >= 10 && tiempoEstimadoPreparacion <= 60;
        return pesoValido && tiempoValido && controlCoccionSuperado;
    }

    public int getPesoGramos() { return pesoGramos; }
    public TipoPreparacion getTipoPreparacion() { return tipoPreparacion; }
    public boolean isControlCoccionSuperado() { return controlCoccionSuperado; }
}
