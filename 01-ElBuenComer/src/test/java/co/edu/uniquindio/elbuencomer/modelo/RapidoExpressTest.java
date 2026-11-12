package co.edu.uniquindio.elbuencomer.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 5 pruebas unitarias sobre RapidoExpress: tarifa base + costo por km,
 * y el limite de recorrido aceptado (12 km).
 */
public class RapidoExpressTest {

    @Test
    public void calcularCostoEnvioSumaTarifaBaseMasCostoPorKilometro() {
        RapidoExpress empresa = new RapidoExpress();
        assertEquals(20000.0, empresa.calcularCostoEnvio(10.0), 0.001);
    }

    @Test
    public void calcularCostoEnvioConDistanciaCero() {
        RapidoExpress empresa = new RapidoExpress();
        assertEquals(5000.0, empresa.calcularCostoEnvio(0.0), 0.001);
    }

    @Test
    public void aceptaRecorridoDentroDelLimiteDe12Km() {
        RapidoExpress empresa = new RapidoExpress();
        assertTrue(empresa.aceptaRecorrido(12.0));
    }

    @Test
    public void noAceptaRecorridoQueSupereLos12Km() {
        RapidoExpress empresa = new RapidoExpress();
        assertFalse(empresa.aceptaRecorrido(15.0));
    }

    @Test
    public void getNombreEmpresaRetornaElNombreCorrecto() {
        RapidoExpress empresa = new RapidoExpress();
        assertEquals("RapidoExpress", empresa.getNombreEmpresa());
    }
}

