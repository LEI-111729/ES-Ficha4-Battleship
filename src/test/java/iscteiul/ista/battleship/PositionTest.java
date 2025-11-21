package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para a classe Position")
class PositionTest {

    private Position position;

    @BeforeEach
    void setUp() {
        position = new Position(5, 5);
    }

    // TMS-POS-001
    @Test
    @DisplayName("Construtor define linha e coluna corretamente")
    void testConstructorAndGetters() {
        assertEquals(5, position.getRow());
        assertEquals(5, position.getColumn());
        assertFalse(position.isOccupied());
        assertFalse(position.isHit());
    }

    // TMS-POS-002
    @Test
    @DisplayName("ocupy() muda o estado de ocupado")
    void testOccupy() {
        assertFalse(position.isOccupied());
        position.occupy();
        assertTrue(position.isOccupied());
    }

    // TMS-POS-003
    @Test
    @DisplayName("shoot() muda o estado de atingido (hit)")
    void testShoot() {
        assertFalse(position.isHit());
        position.shoot();
        assertTrue(position.isHit());
    }

    // TMS-POS-004
    @Test
    @DisplayName("toString() devolve string formatada corretamente")
    void testToString() {
        String expected = "Linha = 5 Coluna = 5";
        assertEquals(expected, position.toString());
    }

    // --- Testes Críticos para Cobertura de Ramos (Equals) ---

    // TMS-POS-005
    @Test
    @DisplayName("equals: O mesmo objeto (identidade)")
    void testEqualsSameObject() {
        // Cobre: if (this == otherPosition) return true;
        assertTrue(position.equals(position));
    }

    // TMS-POS-006
    @Test
    @DisplayName("equals: Objeto de outro tipo ou null")
    void testEqualsDifferentTypeOrNull() {
        // Cobre: if (otherPosition instanceof IPosition) ... else return false;
        assertFalse(position.equals("Não sou uma posição"));
        assertFalse(position.equals(null));
    }

    // TMS-POS-007
    @Test
    @DisplayName("equals: Posição com coordenadas diferentes")
    void testEqualsDifferentCoordinates() {
        // Cobre as combinações do && dentro do return
        Position diffRow = new Position(4, 5);
        Position diffCol = new Position(5, 4);

        assertFalse(position.equals(diffRow));
        assertFalse(position.equals(diffCol));
    }

    // TMS-POS-008
    @Test
    @DisplayName("equals: Posição igual (valores iguais)")
    void testEqualsSameCoordinates() {
        Position same = new Position(5, 5);
        assertTrue(position.equals(same));
    }

    // TMS-POS-009
    @Test
    @DisplayName("hashCode: Objetos iguais têm o mesmo hash")
    void testHashCode() {
        Position same = new Position(5, 5);
        assertEquals(position.hashCode(), same.hashCode());
    }

    // --- Testes Críticos para Cobertura de Ramos (isAdjacentTo) ---

    // TMS-POS-010
    @Test
    @DisplayName("isAdjacentTo: Verdadeiro para todas as direções (inclusive diagonais)")
    void testIsAdjacentToTrue() {
        // Cobre: Math.abs(...) <= 1 && Math.abs(...) <= 1
        assertTrue(position.isAdjacentTo(new Position(4, 5))); // Cima
        assertTrue(position.isAdjacentTo(new Position(6, 5))); // Baixo
        assertTrue(position.isAdjacentTo(new Position(5, 4))); // Esquerda
        assertTrue(position.isAdjacentTo(new Position(5, 6))); // Direita
        assertTrue(position.isAdjacentTo(new Position(4, 4))); // Diagonal
        assertTrue(position.isAdjacentTo(new Position(5, 5))); // A própria (distância 0)
    }

    // TMS-POS-011
    @Test
    @DisplayName("isAdjacentTo: Falso para distâncias maiores que 1")
    void testIsAdjacentToFalse() {
        // Falha na linha (Row diff > 1)
        assertFalse(position.isAdjacentTo(new Position(3, 5)));

        // Falha na coluna (Col diff > 1)
        assertFalse(position.isAdjacentTo(new Position(5, 7)));

        // Falha em ambos
        assertFalse(position.isAdjacentTo(new Position(0, 0)));
    }
}