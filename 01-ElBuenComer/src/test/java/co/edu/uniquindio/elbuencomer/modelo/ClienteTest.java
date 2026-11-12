package co.edu.uniquindio.elbuencomer.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 5 pruebas unitarias sobre Cliente: registro de datos basicos y manejo del
 * historial de pedidos.
 */
public class ClienteTest {

    @Test
    public void getNumeroDocumentoRetornaElDocumentoCorrecto() {
        Cliente cliente = new Cliente("1094123456", "Laura Gomez", "3001234567",
                "laura@correo.com", "Cra 10 # 20-30");
        assertEquals("1094123456", cliente.getNumeroDocumento());
    }

    @Test
    public void getNombreCompletoRetornaElNombreCorrecto() {
        Cliente cliente = new Cliente("1094123456", "Laura Gomez", "3001234567",
                "laura@correo.com", "Cra 10 # 20-30");
        assertEquals("Laura Gomez", cliente.getNombreCompleto());
    }

    @Test
    public void unClienteNuevoInicraConElHistorialDePedidosVacio() {
        Cliente cliente = new Cliente("1094123456", "Laura Gomez", "3001234567",
                "laura@correo.com", "Cra 10 # 20-30");
        assertTrue(cliente.getHistorialPedidos().isEmpty());
    }

    @Test
    public void agregarPedidoAlHistorialAumentaElTamanoDelHistorial() {
        Cliente cliente = new Cliente("1094123456", "Laura Gomez", "3001234567",
                "laura@correo.com", "Cra 10 # 20-30");
        Pedido pedido = new Pedido("PED1", cliente, ModalidadEntrega.CONSUMO_RESTAURANTE);

        cliente.agregarPedidoAlHistorial(pedido);

        assertEquals(1, cliente.getHistorialPedidos().size());
    }

    @Test
    public void agregarVariosPedidosAlHistorialConservaElOrdenDeRegistro() {
        Cliente cliente = new Cliente("1094123456", "Laura Gomez", "3001234567",
                "laura@correo.com", "Cra 10 # 20-30");
        Pedido pedido1 = new Pedido("PED1", cliente, ModalidadEntrega.CONSUMO_RESTAURANTE);
        Pedido pedido2 = new Pedido("PED2", cliente, ModalidadEntrega.DOMICILIO);

        cliente.agregarPedidoAlHistorial(pedido1);
        cliente.agregarPedidoAlHistorial(pedido2);

        assertEquals("PED1", cliente.getHistorialPedidos().get(0).getCodigo());
        assertEquals("PED2", cliente.getHistorialPedidos().get(1).getCodigo());
    }
}

