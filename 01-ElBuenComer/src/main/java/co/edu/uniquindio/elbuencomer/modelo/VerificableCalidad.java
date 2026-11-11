package co.edu.uniquindio.elbuencomer.modelo;

/**
 * Contrato comun que deben cumplir todos los productos del menu.
 * Cada producto verifica la calidad con sus propias reglas, y el restaurante
 * puede preguntarle a cualquiera sin saber de que categoria es (polimorfismo
 * mediante realizacion de interfaz).
 */
public interface VerificableCalidad {

    /**
     * Indica si el producto cumple las condiciones de calidad de su categoria.
     *
     * @return true si puede incluirse en un pedido confirmado
     */
    boolean cumpleCondicionesCalidad();
}
