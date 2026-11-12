package co.edu.uniquindio.elbuencomer.modelo;

/**
 * Empresa de domicilio EntregaSegura. Cobra una tarifa base de $7.000 mas
 * $1.000 por kilometro, adiciona $3.000 cuando la distancia supera 5 km y
 * acepta recorridos de hasta 20 km.
 */
public class EntregaSegura implements ServicioDomicilio {

    private static final double TARIFA_BASE = 7000.0;
    private static final double COSTO_POR_KM = 1000.0;
    private static final double RECARGO_DISTANCIA_LARGA = 3000.0;
    private static final double DISTANCIA_LIMITE_RECARGO = 5.0;
    private static final double RECORRIDO_MAXIMO_KM = 20.0;

    /**
     * @param distanciaKm distancia del recorrido en kilometros
     * @return tarifa base mas el costo por kilometro, y $3.000 si supera 5 km
     */
    @Override
    public double calcularCostoEnvio(double distanciaKm) {
        double costo = TARIFA_BASE + (COSTO_POR_KM * distanciaKm);
        if (distanciaKm > DISTANCIA_LIMITE_RECARGO) {
            costo = costo + RECARGO_DISTANCIA_LARGA;
        }
        return costo;
    }

    /**
     * @param distanciaKm distancia del recorrido en kilometros
     * @return true si el recorrido es de maximo 20 km
     */
    @Override
    public boolean aceptaRecorrido(double distanciaKm) {
        return distanciaKm <= RECORRIDO_MAXIMO_KM;
    }

    @Override
    public String getNombreEmpresa() {
        return "EntregaSegura";
    }
}

