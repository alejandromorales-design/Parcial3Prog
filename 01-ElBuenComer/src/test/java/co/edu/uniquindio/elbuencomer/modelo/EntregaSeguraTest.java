package co.edu.uniquindio.elbuencomer.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 5 pruebas unitarias sobre EntregaSegura: tarifa base + costo por km,
 * el recargo por distancia larga (> 5 km), y el limite de recorrido (20 km).
 */
public class EntregaSeguraTest {

    @Test
    public void calcularCostoEnvioSinRecargoCuandoLaDistanciaEsMenorOIgualA5Km() {
        EntregaSegura empresa = new EntregaSegura();
        assertEquals(10000.0, empresa.calcularCostoEnvio(3.0), 0.001);
    }

    @Test
    public void calcularCostoEnvioConRecargoCuandoLaDistanciaSuperaLos5Km() {
        EntregaSegura empresa = new EntregaSegura();
        assertEquals(20000.0, empresa.calcularCostoEnvio(10.0), 0.001);
    }

    @Test
    public void aceptaRecorridoDentroDelLimiteDe20Km() {
        EntregaSegura empresa = new EntregaSegura();
        assertTrue(empresa.aceptaRecorrido(20.0));
    }

    @Test
    public void noAceptaRecorridoQueSupereLos20Km() {
        EntregaSegura empresa = new EntregaSegura();
        assertFalse(empresa.aceptaRecorrido(21.0));
    }

    @Test
    public void getNombreEmpresaRetornaElNombreCorrecto() {
        EntregaSegura empresa = new EntregaSegura();
        assertEquals("EntregaSegura", empresa.getNombreEmpresa());
    }
}

