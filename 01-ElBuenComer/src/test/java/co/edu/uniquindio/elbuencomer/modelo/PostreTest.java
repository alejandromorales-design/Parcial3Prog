package co.edu.uniquindio.elbuencomer.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 5 pruebas unitarias sobre Postre: incremento del 15%, incremento adicional
 * del 5% por refrigeracion, y condiciones de calidad segun temperatura.
 */
public class PostreTest {

    @Test
    public void calcularPrecioVentaSinRefrigeracionAplicaSoloElIncrementoDel15Porciento() {
        Postre galleta = new Postre("P2", "Galleta", "desc", 4000.0, 10, 100, 20.0, false);
        assertEquals(4600.0, galleta.calcularPrecioVenta(), 0.001);
    }

    @Test
    public void calcularPrecioVentaConRefrigeracionSumaElIncrementoAdicionalDel5Porciento() {
        Postre flan = new Postre("P1", "Flan", "desc", 7000.0, 15, 150, 4.0, true);
        assertEquals(8400.0, flan.calcularPrecioVenta(), 0.001);
    }

    @Test
    public void cumpleCondicionesCalidadSiRequiereRefrigeracionYLaTemperaturaEstaEntre0y6() {
        Postre flan = new Postre("P1", "Flan", "desc", 7000.0, 15, 150, 4.0, true);
        assertTrue(flan.cumpleCondicionesCalidad());
    }

    @Test
    public void cumpleCondicionesCalidadSiNoRequiereRefrigeracionYLaTemperaturaEstaEntre15y30() {
        Postre galleta = new Postre("P2", "Galleta", "desc", 4000.0, 10, 100, 20.0, false);
        assertTrue(galleta.cumpleCondicionesCalidad());
    }

    @Test
    public void noCumpleCondicionesCalidadSiElPesoEstaFueraDeRango() {
        Postre pesoInvalido = new Postre("P3", "Postre gigante", "desc", 9000.0, 20, 700, 4.0, true);
        assertFalse(pesoInvalido.cumpleCondicionesCalidad());
    }
}
