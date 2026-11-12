package co.edu.uniquindio.elbuencomer.app;

import co.edu.uniquindio.elbuencomer.modelo.*;

import java.util.ArrayList;
import java.util.Scanner;

// ============================================================================
// Main.java
// ----------------------------------------------------------------------------
// Menu de consola para probar el sistema de "El Buen Comer": registrar
// productos, clientes, pedidos, confirmar pedidos, calcular valores totales,
// consultar historiales, y las consultas adicionales de negocio.
// ============================================================================
public class Main {

    private static Restaurante restaurante;
    private static RapidoExpress rapidoExpress = new RapidoExpress();
    private static EntregaSegura entregaSegura = new EntregaSegura();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        restaurante = new Restaurante("El Buen Comer", "900123456-7", "Cra 19 # 8-45, Armenia");
        precargarDatosDeEjemplo();

        int opcion = -1;
        while (opcion != 0) {
            mostrarMenuPrincipal();
            opcion = leerEntero("Selecciona una opcion: ");

            if (opcion == 1) {
                crearProductoUI();
            } else if (opcion == 2) {
                crearClienteUI();
            } else if (opcion == 3) {
                menuPedidos();
            } else if (opcion == 4) {
                consultarValorTotalPedidoUI();
            } else if (opcion == 5) {
                consultarHistorialUI();
            } else if (opcion == 6) {
                menuConsultasAdicionales();
            } else if (opcion == 0) {
                System.out.println("Gracias por usar el sistema de El Buen Comer.");
            } else {
                System.out.println("Opcion invalida.");
            }
        }
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n===== EL BUEN COMER - MENU PRINCIPAL =====");
        System.out.println("1. Registrar producto");
        System.out.println("2. Registrar cliente");
        System.out.println("3. Pedidos (crear / agregar productos / confirmar / entregar / cancelar / listar)");
        System.out.println("4. Calcular valor total de un pedido");
        System.out.println("5. Consultar historial de pedidos de un cliente");
        System.out.println("6. Consultas adicionales (disponibilidad, ingresos, mas vendido)");
        System.out.println("0. Salir");
    }

    // ========================================================================
    // 1. Registrar producto
    // ========================================================================
    private static void crearProductoUI() {
        System.out.println("Categoria: 1.Plato fuerte  2.Bebida  3.Postre");
        int categoria = leerEntero("Opcion: ");

        String codigo = leerTexto("Codigo: ");
        String nombre = leerTexto("Nombre: ");
        String descripcion = leerTexto("Descripcion: ");
        double precioBase = leerDecimal("Precio base: ");
        int tiempoPreparacion = leerEntero("Tiempo estimado de preparacion (minutos): ");

        Producto producto = null;

        if (categoria == 1) {
            int peso = leerEntero("Peso (gramos): ");
            TipoPreparacion tipo = leerTipoPreparacion();
            boolean coccionAprobada = leerBooleano("Supero el control de coccion? (s/n): ");
            producto = new PlatoFuerte(codigo, nombre, descripcion, precioBase, tiempoPreparacion,
                    peso, tipo, coccionAprobada);
        } else if (categoria == 2) {
            int volumen = leerEntero("Volumen (ml): ");
            double temperatura = leerDecimal("Temperatura de servicio (C): ");
            boolean esFria = leerBooleano("Es fria? (s/n, 'n' = caliente): ");
            boolean preparacionEspecial = leerBooleano("Requiere preparacion especial? (s/n): ");
            producto = new Bebida(codigo, nombre, descripcion, precioBase, tiempoPreparacion,
                    volumen, temperatura, esFria, preparacionEspecial);
        } else if (categoria == 3) {
            int peso = leerEntero("Peso (gramos): ");
            double temperatura = leerDecimal("Temperatura de almacenamiento (C): ");
            boolean refrigeracion = leerBooleano("Requiere refrigeracion? (s/n): ");
            producto = new Postre(codigo, nombre, descripcion, precioBase, tiempoPreparacion,
                    peso, temperatura, refrigeracion);
        } else {
            System.out.println("Categoria invalida.");
            return;
        }

        boolean ok = restaurante.registrarProducto(producto);
        System.out.println(ok ? "Producto registrado." : "Ya existe un producto con ese codigo.");
    }

    private static TipoPreparacion leerTipoPreparacion() {
        System.out.println("Tipo de preparacion: 1.Asado 2.Frito 3.Horneado 4.A la plancha");
        int op = leerEntero("Opcion: ");
        if (op == 1) {
            return TipoPreparacion.ASADO;
        } else if (op == 2) {
            return TipoPreparacion.FRITO;
        } else if (op == 3) {
            return TipoPreparacion.HORNEADO;
        } else {
            return TipoPreparacion.A_LA_PLANCHA;
        }
    }

    // ========================================================================
    // 2. Registrar cliente
    // ========================================================================
    private static void crearClienteUI() {
        String documento = leerTexto("Numero de documento: ");
        String nombre = leerTexto("Nombre completo: ");
        String telefono = leerTexto("Telefono: ");
        String correo = leerTexto("Correo electronico: ");
        String direccion = leerTexto("Direccion de residencia: ");

        Cliente cliente = new Cliente(documento, nombre, telefono, correo, direccion);
        boolean ok = restaurante.registrarCliente(cliente);
        System.out.println(ok ? "Cliente registrado." : "Ya existe un cliente con ese documento.");
    }

    // ========================================================================
    // 3. Pedidos
    // ========================================================================
    private static void menuPedidos() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- Pedidos ---");
            System.out.println("1. Crear pedido  2. Agregar producto a un pedido  3. Asignar domicilio");
            System.out.println("4. Confirmar  5. Marcar entregado  6. Cancelar  7. Listar  0. Volver");
            opcion = leerEntero("Opcion: ");

            if (opcion == 1) {
                crearPedidoUI();
            } else if (opcion == 2) {
                agregarProductoAPedidoUI();
            } else if (opcion == 3) {
                asignarDomicilioUI();
            } else if (opcion == 4) {
                String codigo = leerTexto("Codigo del pedido a confirmar: ");
                boolean ok = restaurante.confirmarPedido(codigo);
                System.out.println(ok ? "Pedido confirmado." : "No se pudo confirmar (revisa productos, cantidades o domicilio).");
            } else if (opcion == 5) {
                String codigo = leerTexto("Codigo del pedido a marcar como entregado: ");
                boolean ok = restaurante.marcarPedidoEntregado(codigo);
                System.out.println(ok ? "Pedido entregado." : "No se pudo marcar como entregado (debe estar confirmado).");
            } else if (opcion == 6) {
                String codigo = leerTexto("Codigo del pedido a cancelar: ");
                boolean ok = restaurante.cancelarPedido(codigo);
                System.out.println(ok ? "Pedido cancelado." : "No se pudo cancelar (ya fue entregado).");
            } else if (opcion == 7) {
                ArrayList<Pedido> lista = restaurante.listarPedidos();
                for (int i = 0; i < lista.size(); i++) {
                    System.out.println(lista.get(i));
                }
            }
        }
    }

    private static void crearPedidoUI() {
        String codigo = leerTexto("Codigo del pedido: ");
        String documentoCliente = leerTexto("Documento del cliente: ");
        System.out.println("Modalidad: 1.Consumo en restaurante 2.Domicilio");
        int op = leerEntero("Opcion: ");
        ModalidadEntrega modalidad = (op == 2) ? ModalidadEntrega.DOMICILIO : ModalidadEntrega.CONSUMO_RESTAURANTE;

        Pedido pedido = restaurante.crearPedido(codigo, documentoCliente, modalidad);
        System.out.println(pedido != null ? "Pedido creado." : "No se pudo crear (codigo repetido o cliente inexistente).");
    }

    private static void agregarProductoAPedidoUI() {
        String codigoPedido = leerTexto("Codigo del pedido: ");
        Pedido pedido = restaurante.buscarPedidoPorCodigo(codigoPedido);
        if (pedido == null) {
            System.out.println("No existe ese pedido.");
            return;
        }
        String codigoProducto = leerTexto("Codigo del producto: ");
        Producto producto = restaurante.buscarProductoPorCodigo(codigoProducto);
        if (producto == null) {
            System.out.println("No existe ese producto.");
            return;
        }
        int cantidad = leerEntero("Cantidad: ");
        pedido.agregarDetalle(producto, cantidad);
        System.out.println("Producto agregado al pedido.");
    }

    private static void asignarDomicilioUI() {
        String codigoPedido = leerTexto("Codigo del pedido: ");
        Pedido pedido = restaurante.buscarPedidoPorCodigo(codigoPedido);
        if (pedido == null) {
            System.out.println("No existe ese pedido.");
            return;
        }
        System.out.println("Empresa: 1.RapidoExpress  2.EntregaSegura");
        int op = leerEntero("Opcion: ");
        double distancia = leerDecimal("Distancia del recorrido (km): ");

        // Se solicita el servicio a cualquiera de las dos empresas mediante
        // el mismo procedimiento comun, gracias a la interface ServicioDomicilio.
        ServicioDomicilio empresa = (op == 2) ? entregaSegura : rapidoExpress;
        pedido.asignarDomicilio(empresa, distancia);
        System.out.println(empresa.getNombreEmpresa() + " asignada. Costo de envio: $"
                + (long) empresa.calcularCostoEnvio(distancia)
                + " - Acepta el recorrido: " + empresa.aceptaRecorrido(distancia));
    }

    // ========================================================================
    // 4. Calcular valor total de un pedido
    // ========================================================================
    private static void consultarValorTotalPedidoUI() {
        String codigo = leerTexto("Codigo del pedido: ");
        double total = restaurante.calcularValorTotalPedido(codigo);
        if (total < 0) {
            System.out.println("No existe ese pedido.");
        } else {
            System.out.println("Valor total del pedido " + codigo + ": $" + (long) total);
        }
    }

    // ========================================================================
    // 5. Consultar historial de pedidos de un cliente
    // ========================================================================
    private static void consultarHistorialUI() {
        String documento = leerTexto("Documento del cliente: ");
        ArrayList<Pedido> historial = restaurante.consultarHistorialPedidos(documento);
        if (historial == null) {
            System.out.println("No existe ese cliente.");
            return;
        }
        if (historial.isEmpty()) {
            System.out.println("Ese cliente no tiene pedidos registrados.");
            return;
        }
        for (int i = 0; i < historial.size(); i++) {
            System.out.println(historial.get(i));
        }
    }

    // ========================================================================
    // 6. Consultas adicionales
    // ========================================================================
    private static void menuConsultasAdicionales() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- Consultas adicionales ---");
            System.out.println("1. Productos disponibles (cumplen condiciones de calidad)");
            System.out.println("2. Producto con mayor cantidad vendida");
            System.out.println("3. Categoria con mayores ingresos");
            System.out.println("4. Ingresos propios vs ingresos por domicilios");
            System.out.println("0. Volver");
            opcion = leerEntero("Opcion: ");

            if (opcion == 1) {
                ArrayList<Producto> disponibles = restaurante.consultarProductosDisponibles();
                for (int i = 0; i < disponibles.size(); i++) {
                    System.out.println(disponibles.get(i));
                }
            } else if (opcion == 2) {
                Producto mejor = restaurante.obtenerProductoConMayorCantidadVendida();
                System.out.println(mejor == null ? "No hay productos registrados." : mejor);
            } else if (opcion == 3) {
                System.out.println("Categoria con mayores ingresos: " + restaurante.obtenerCategoriaConMayoresIngresos());
            } else if (opcion == 4) {
                System.out.println("Ingresos propios (productos): $" + (long) restaurante.calcularIngresosPropios());
                System.out.println("Ingresos por domicilios: $" + (long) restaurante.calcularIngresosDomicilios());
            }
        }
    }

    // ========================================================================
    // Metodos de ayuda para leer datos por consola
    // ========================================================================

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!sc.hasNextInt()) {
            System.out.print("Escribe un numero valido: ");
            sc.next();
        }
        int valor = sc.nextInt();
        sc.nextLine();
        return valor;
    }

    private static double leerDecimal(String mensaje) {
        System.out.print(mensaje);
        while (!sc.hasNextDouble()) {
            System.out.print("Escribe un numero valido: ");
            sc.next();
        }
        double valor = sc.nextDouble();
        sc.nextLine();
        return valor;
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine();
    }

    private static boolean leerBooleano(String mensaje) {
        System.out.print(mensaje);
        String texto = sc.nextLine();
        return texto.equalsIgnoreCase("s");
    }

    // ========================================================================
    // Datos de ejemplo
    // ========================================================================
    private static void precargarDatosDeEjemplo() {
        Producto bandeja = new PlatoFuerte("PF1", "Bandeja Paisa", "Bandeja tipica", 25000.0, 35,
                700, TipoPreparacion.FRITO, true);
        Producto jugo = new Bebida("B1", "Jugo de mora", "Jugo natural en agua", 6000.0, 8,
                400, 5.0, true, false);
        Producto flan = new Postre("P1", "Flan de caramelo", "Postre casero", 7000.0, 15,
                150, 4.0, true);
        restaurante.registrarProducto(bandeja);
        restaurante.registrarProducto(jugo);
        restaurante.registrarProducto(flan);

        Cliente cliente = new Cliente("1094123456", "Laura Gomez", "3001112233",
                "laura@correo.com", "Calle 10 # 5-20, Armenia");
        restaurante.registrarCliente(cliente);
    }
}
