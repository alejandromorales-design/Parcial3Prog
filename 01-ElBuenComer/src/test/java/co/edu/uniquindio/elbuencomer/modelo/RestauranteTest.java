package co.edu.uniquindio.elbuencomer.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 5 pruebas unitarias sobre Restaurante: las funcionalidades principales de
 * registro (sin duplicados) y la consulta del valor total de un pedido.
 */
public class RestauranteTest {

    private Restaurante crearRestaurantePrueba() {
        return new Restaurante("El Buen Comer", "900123456-1", "Calle 10 # 5-20");
    }

    private PlatoFuerte crearPlatoFuertePrueba() {
        return new PlatoFuerte("PF1", "Bandeja Paisa", "desc", 25000.0, 35,
                700, TipoPreparacion.FRITO, true);
    }

    private Cliente crearClientePrueba() {
        return new Cliente("1094123456", "Laura Gomez", "3001234567",
                "laura@correo.com", "Cra 10 # 20-30");
    }

    @Test
    public void registrarProductoRetornaFalsoSiElCodigoYaExiste() {
        Restaurante restaurante = crearRestaurantePrueba();
        restaurante.registrarProducto(crearPlatoFuertePrueba());

        boolean resultado = restaurante.registrarProducto(crearPlatoFuertePrueba());

        assertFalse(resultado);
    }

    @Test
    public void registrarClienteRetornaFalsoSiElDocumentoYaExiste() {
        Restaurante restaurante = crearRestaurantePrueba();
        restaurante.registrarCliente(crearClientePrueba());

        boolean resultado = restaurante.registrarCliente(crearClientePrueba());

        assertFalse(resultado);
    }

    @Test
    public void crearPedidoRetornaNullSiElClienteNoEstaRegistrado() {
        Restaurante restaurante = crearRestaurantePrueba();

        Pedido pedido = restaurante.crearPedido("PED1", "0000000000", ModalidadEntrega.CONSUMO_RESTAURANTE);

        assertNull(pedido);
    }

    @Test
    public void calcularValorTotalPedidoRetornaMenosUnoSiElPedidoNoExiste() {
        Restaurante restaurante = crearRestaurantePrueba();

        double total = restaurante.calcularValorTotalPedido("NO_EXISTE");

        assertEquals(-1.0, total, 0.001);
    }

    /** Precio de venta = 25000 * 1.20 = 30000; total = 30000 * 2 = 60000. */
    @Test
    public void calcularValorTotalPedidoRetornaElValorCorrectoDeUnPedidoExistente() {
        Restaurante restaurante = crearRestaurantePrueba();
        restaurante.registrarCliente(crearClientePrueba());
        restaurante.registrarProducto(crearPlatoFuertePrueba());

        Pedido pedido = restaurante.crearPedido("PED1", "1094123456", ModalidadEntrega.CONSUMO_RESTAURANTE);
        pedido.agregarDetalle(restaurante.buscarProductoPorCodigo("PF1"), 2);

        double total = restaurante.calcularValorTotalPedido("PED1");

        assertEquals(60000.0, total, 0.001);
    }
}

