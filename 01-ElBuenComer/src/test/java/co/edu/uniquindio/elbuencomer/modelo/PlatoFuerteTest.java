package co.edu.uniquindio.elbuencomer.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 5 pruebas unitarias sobre PlatoFuerte: calculo de precio y verificacion
 * de condiciones de calidad (peso, tiempo de preparacion, control de coccion).
 */
public class PlatoFuerteTest {

    @Test
    public void calcularPrecioVentaAplicaIncrementoDel20Porciento() {
        PlatoFuerte bandeja = new PlatoFuerte("PF1", "Bandeja Paisa", "desc", 25000.0, 35,
                700, TipoPreparacion.FRITO, true);
        assertEquals(30000.0, bandeja.calcularPrecioVenta(), 0.001);
    }

    @Test
    public void cumpleCondicionesCalidadConDatosValidos() {
        PlatoFuerte bandeja = new PlatoFuerte("PF1", "Bandeja Paisa", "desc", 25000.0, 35,
                700, TipoPreparacion.FRITO, true);
        assertTrue(bandeja.cumpleCondicionesCalidad());
    }

    @Test
    public void noCumpleCondicionesCalidadSiElPesoEstaFueraDeRango() {
        PlatoFuerte muyLiviano = new PlatoFuerte("PF2", "Plato chico", "desc", 15000.0, 20,
                100, TipoPreparacion.ASADO, true);
        assertFalse(muyLiviano.cumpleCondicionesCalidad());
    }

    @Test
    public void noCumpleCondicionesCalidadSiElTiempoDePreparacionEstaFueraDeRango() {
        PlatoFuerte muyLento = new PlatoFuerte("PF3", "Plato lento", "desc", 20000.0, 90,
                500, TipoPreparacion.HORNEADO, true);
        assertFalse(muyLento.cumpleCondicionesCalidad());
    }

    @Test
    public void noCumpleCondicionesCalidadSiNoSuperoElControlDeCoccion() {
        PlatoFuerte sinControl = new PlatoFuerte("PF4", "Plato sin control", "desc", 20000.0, 30,
                500, TipoPreparacion.A_LA_PLANCHA, false);
        assertFalse(sinControl.cumpleCondicionesCalidad());
    }
}
