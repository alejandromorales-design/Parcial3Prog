# 01 - El Buen Comer (Maven + JavaFX)

Sistema de gestión del restaurante **El Buen Comer** (Armenia, Quindío): menú de productos,
clientes, pedidos con domicilio por empresas externas y reporte de ventas.

## Cómo abrirlo en IntelliJ

1. `File → Open…` y seleccionar **la carpeta `01-ElBuenComer`** (la que tiene el `pom.xml`).
2. Si pregunta, elegir *Open as Project* / *Trust project*. IntelliJ descarga solo JavaFX y JUnit.
3. Verificar en `File → Project Structure → Project` que el SDK sea **JDK 21 o superior**.
4. Ejecutar la app: abrir `src/main/java/co/edu/uniquindio/elbuencomer/app/Main.java` → botón ▶ Run.
   (Si IntelliJ se queja de JavaFX, usar la pestaña **Maven** → `Plugins → javafx → javafx:run`.)
5. Ejecutar las pruebas: clic derecho sobre `src/test/java` → *Run 'All Tests'* (40 pruebas, 5 por clase).

Desde la terminal (con Maven instalado): `mvn test` y `mvn javafx:run`.

## Estructura

```
01-ElBuenComer/
├── pom.xml                                  (Java 21, JavaFX 21, JUnit 5)
├── src/main/java/co/edu/uniquindio/elbuencomer/
│   ├── modelo/   enums, interfaces, Producto y sus hijas, DetallePedido (record),
│   │             Cliente, Pedido, Restaurante, RapidoExpress, EntregaSegura
│   └── app/      Main (lanza la ventana) y ElBuenComerApp (menú JavaFX)
└── src/test/java/co/edu/uniquindio/elbuencomer/modelo/   8 clases de prueba JUnit 5
```

## Menú interactivo (pestañas)

1. **Productos** – registrar platos fuertes, bebidas y postres (sin códigos duplicados).
2. **Clientes** – registrar clientes (sin documentos duplicados).
3. **Pedidos** – crear, agregar productos, asignar domicilio, confirmar, entregar, cancelar.
4. **Valor total** – consultar un pedido por código con su desglose y total.
5. **Historial cliente** – pedidos de un cliente por documento (código, fecha, estado, total).
6. **Ventas** – productos disponibles / que no cumplen calidad, más vendido,
   categoría con más ingresos, ingresos propios vs. domicilios.
