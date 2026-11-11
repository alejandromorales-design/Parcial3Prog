package co.edu.uniquindio.elbuencomer.modelo;

/**
 * Contrato comun para las empresas externas de domicilio.
 * RapidoExpress y EntregaSegura ofrecen el mismo servicio con tarifas y
 * condiciones distintas, pero el restaurante las solicita a las dos con
 * el mismo procedimiento (realizacion de interfaz).
 */
public interface ServicioDomicilio {

    /**
     * Calcula lo que cobra la empresa por un recorrido.
     *
     * @param distanciaKm distancia del recorrido en kilometros
     * @return costo del envio en pesos
     */
    double calcularCostoEnvio(double distanciaKm);

    /**
     * Indica si la empresa acepta hacer un recorrido de esa distancia.
     *
     * @param distanciaKm distancia del recorrido en kilometros
     * @return true si la empresa acepta el recorrido
     */
    boolean aceptaRecorrido(double distanciaKm);

    /**
     * @return nombre comercial de la empresa de domicilio
     */
    String getNombreEmpresa();
}
