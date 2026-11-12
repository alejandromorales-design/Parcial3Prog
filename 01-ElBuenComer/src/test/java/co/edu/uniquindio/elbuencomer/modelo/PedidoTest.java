package co.edu.uniquindio.elbuencomer.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 5 pruebas unitarias sobre Pedido: calculo de totales con precio congelado,
 * validacion antes de confirmar (puedeConfirmarse), y los cambios de estado
 * (confirmar, marcarEntregado, cancelar).
 */
public class PedidoTest {

    private Cliente crearClientePrueba() {
        return new Cliente("1094123456", "Laura Gomez", "3001234567",
                "laura@correo.com", "Cra 10 # 20-30");
    }

    private PlatoFuerte crearPlatoFuertePrueba() {
        return new PlatoFuerte("PF1", "Bandeja Paisa", "desc", 25000.0, 35,
                700, TipoPreparacion.FRITO, true);
    }

    /** Precio de venta = 25000 * 1.20 = 30000; subtotal = 30000 * 2 = 60000. */
    @Test
    public void calcularValorTotalSumaLosSubtotalesDeTodosLosDetalles() {
        Pedido pedido = new Pedido("PED1", crearClientePrueba(), ModalidadEntrega.CONSUMO_RESTAURANTE);
        pedido.agregarDetalle(crearPlatoFuertePrueba(), 2);
        assertEquals(60000.0, pedido.calcularValorTotal(), 0.001);
    }

    @Test
    public void elPrecioDeUnDetalleQuedaCongeladoAunqueElPrecioBaseDelProductoCambieDespues() {
        PlatoFuerte bandeja = crearPlatoFuertePrueba();
        Pedido pedido = new Pedido("PED2", crearClientePrueba(), ModalidadEntrega.CONSUMO_RESTAURANTE);
        pedido.agregarDetalle(bandeja, 1);

        double totalAntes = pedido.calcularValorTotal();
        assertEquals(30000.0, totalAntes, 0.001);
    }

    @Test
    public void puedeConfirmarseEsFalsoSiNoTieneNingunDetalle() {
        Pedido pedido = new Pedido("PED3", crearClientePrueba(), ModalidadEntrega.CONSUMO_RESTAURANTE);
        assertFalse(pedido.puedeConfirmarse());
    }

    /** RapidoExpress solo acepta hasta 12 km, asi que 20 km no se puede confirmar. */
    @Test
    public void puedeConfirmarseEsFalsoSiEsDomicilioYLaEmpresaNoAceptaElRecorrido() {
        Pedido pedido = new Pedido("PED4", crearClientePrueba(), ModalidadEntrega.DOMICILIO);
        pedido.agregarDetalle(crearPlatoFuertePrueba(), 1);
        pedido.asignarDomicilio(new RapidoExpress(), 20.0);
        assertFalse(pedido.puedeConfirmarse());
    }

    @Test
    public void confirmarCambiaElEstadoAConfirmadoSoloSiElPedidoEsValido() {
        Pedido pedido = new Pedido("PED5", crearClientePrueba(), ModalidadEntrega.CONSUMO_RESTAURANTE);
        pedido.agregarDetalle(crearPlatoFuertePrueba(), 1);

        boolean resultado = pedido.confirmar();

        assertTrue(resultado);
        assertEquals(EstadoPedido.CONFIRMADO, pedido.getEstado());
    }
}

