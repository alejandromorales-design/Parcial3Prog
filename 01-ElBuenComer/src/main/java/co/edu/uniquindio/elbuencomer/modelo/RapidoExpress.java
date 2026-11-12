package co.edu.uniquindio.elbuencomer.modelo;

/**
 * Empresa de domicilio RapidoExpress. Cobra una tarifa base de $5.000 mas
 * $1.500 por kilometro y acepta recorridos de hasta 12 km.
 */
public class RapidoExpress implements ServicioDomicilio {

    private static final double TARIFA_BASE = 5000.0;
    private static final double COSTO_POR_KM = 1500.0;
    private static final double RECORRIDO_MAXIMO_KM = 12.0;

    /**
     * @param distanciaKm distancia del recorrido en kilometros
     * @return tarifa base mas el costo por kilometro
     */
    @Override
    public double calcularCostoEnvio(double distanciaKm) {
        return TARIFA_BASE + (COSTO_POR_KM * distanciaKm);
    }

    /**
     * @param distanciaKm distancia del recorrido en kilometros
     * @return true si el recorrido es de maximo 12 km
     */
    @Override
    public boolean aceptaRecorrido(double distanciaKm) {
        return distanciaKm <= RECORRIDO_MAXIMO_KM;
    }

    @Override
    public String getNombreEmpresa() {
        return "RapidoExpress";
    }
}
