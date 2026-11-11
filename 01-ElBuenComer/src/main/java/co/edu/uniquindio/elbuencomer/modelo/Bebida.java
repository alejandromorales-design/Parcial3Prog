package co.edu.uniquindio.elbuencomer.modelo;

/**
 * Bebida del menu. Su precio de venta tiene un incremento del 10 % sobre el
 * precio base, mas un recargo fijo de $3.000 si requiere preparacion especial.
 * Condiciones de calidad: volumen entre 150 y 1.500 ml; temperatura entre
 * 0 y 10 grados si es fria, o entre 55 y 85 grados si es caliente.
 */
public class Bebida extends Producto {

    private int volumenMililitros;
    private double temperaturaServicio;
    private boolean esFria;
    /** Se agrega porque el enunciado lo exige para calcular el recargo de $3.000. */
    private boolean requierePreparacionEspecial;

    /**
     * Crea una bebida.
     *
     * @param codigo                      codigo unico del producto
     * @param nombre                      nombre de la bebida
     * @param descripcion                 descripcion de la bebida
     * @param precioBase                  precio base en pesos
     * @param tiempoEstimadoPreparacion   tiempo de preparacion en minutos
     * @param volumenMililitros           volumen en mililitros
     * @param temperaturaServicio         temperatura de servicio en grados
     * @param esFria                      true si es fria, false si es caliente
     * @param requierePreparacionEspecial true si lleva el recargo de $3.000
     */
    public Bebida(String codigo, String nombre, String descripcion, double precioBase,
                  int tiempoEstimadoPreparacion, int volumenMililitros, double temperaturaServicio,
                  boolean esFria, boolean requierePreparacionEspecial) {
        super(codigo, nombre, descripcion, precioBase, tiempoEstimadoPreparacion);
        this.volumenMililitros = volumenMililitros;
        this.temperaturaServicio = temperaturaServicio;
        this.esFria = esFria;
        this.requierePreparacionEspecial = requierePreparacionEspecial;
    }

    /**
     * @return precio base mas el 10 %, y $3.000 adicionales si requiere preparacion especial
     */
    @Override
    public double calcularPrecioVenta() {
        double precio = precioBase * 1.10;
        if (requierePreparacionEspecial) {
            precio = precio + 3000.0;
        }
        return precio;
    }

    @Override
    public CategoriaProducto getCategoria() {
        return CategoriaProducto.BEBIDA;
    }

    /**
     * @return true si el volumen y la temperatura estan en el rango permitido
     */
    @Override
    public boolean cumpleCondicionesCalidad() {
        boolean volumenValido = volumenMililitros >= 150 && volumenMililitros <= 1500;
        boolean temperaturaValida;
        if (esFria) {
            temperaturaValida = temperaturaServicio >= 0 && temperaturaServicio <= 10;
        } else {
            temperaturaValida = temperaturaServicio >= 55 && temperaturaServicio <= 85;
        }
        return volumenValido && temperaturaValida;
    }

    public int getVolumenMililitros() { return volumenMililitros; }
    public double getTemperaturaServicio() { return temperaturaServicio; }
    public boolean isEsFria() { return esFria; }
    public boolean isRequierePreparacionEspecial() { return requierePreparacionEspecial; }
}
