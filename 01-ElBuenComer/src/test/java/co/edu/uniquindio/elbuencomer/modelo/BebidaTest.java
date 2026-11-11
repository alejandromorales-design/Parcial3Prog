package co.edu.uniquindio.elbuencomer.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class BebidaTest {

    @Test
    public void calcularPrecioVentaSinPreparacionEspecialAplicaSoloElIncrementoDel10Porciento() {
        Bebida jugo = new Bebida("B1", "Jugo de mora", "desc", 6000.0, 8, 400, 5.0, true, false);
        assertEquals(6600.0, jugo.calcularPrecioVenta(), 0.001);
    }

    @Test

    public void calcularPrecioVentaConPreparacionEspecialSumaElRecargoFijoDe3000() {
        Bebida cafeEspecial = new Bebida("B2", "Cafe especial", "desc", 6000.0, 8, 400, 70.0, false, true);
        assertEquals(9600.0, cafeEspecial.calcularPrecioVenta(), 0.001);
    }

    @Test
    public void cumpleCondicionesCalidadSiEsFriaYLaTemperaturaEstaEntre0y10() {
        Bebida jugo = new Bebida("B1", "Jugo de mora", "desc", 6000.0, 8, 400, 5.0, true, false);
        assertTrue(jugo.cumpleCondicionesCalidad());
    }

    @Test
    public void cumpleCondicionesCalidadSiEsCalienteYLaTemperaturaEstaEntre55y85() {
        Bebida cafe = new Bebida("B3", "Cafe", "desc", 3000.0, 5, 200, 70.0, false, false);
        assertTrue(cafe.cumpleCondicionesCalidad());
    }

    @Test
    public void noCumpleCondicionesCalidadSiElVolumenEstaFueraDeRango() {
        Bebida volumenInvalido = new Bebida("B4", "Jarra gigante", "desc", 6000.0, 8, 2000, 5.0, true, true);
        assertFalse(volumenInvalido.cumpleCondicionesCalidad());
    }
}
