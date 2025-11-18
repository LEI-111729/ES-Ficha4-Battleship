package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaravelTest {

    @Test
    @DisplayName("Caravel deve ter tamanho 2")
    void testCaravelSize() {
        IPosition pos = new Position(3, 4);
        Caravel caravel = new Caravel(Compass.NORTH, pos);

        assertEquals(2, caravel.getSize());
    }

    @Test
    @DisplayName("Caravel em NORTH ocupa duas posições verticais")
    void testCaravelPositionsNorth() {
        IPosition pos = new Position(2, 5);
        Caravel caravel = new Caravel(Compass.NORTH, pos);

        assertEquals(2, caravel.getPositions().size());
        assertEquals(new Position(2, 5), caravel.getPositions().get(0));
        assertEquals(new Position(3, 5), caravel.getPositions().get(1));
    }

    @Test
    @DisplayName("Caravel em SOUTH ocupa duas posições verticais")
    void testCaravelPositionsSouth() {
        IPosition pos = new Position(4, 1);
        Caravel caravel = new Caravel(Compass.SOUTH, pos);

        assertEquals(2, caravel.getPositions().size());
        assertEquals(new Position(4, 1), caravel.getPositions().get(0));
        assertEquals(new Position(5, 1), caravel.getPositions().get(1));
    }

    @Test
    @DisplayName("Caravel em EAST ocupa duas posições horizontais")
    void testCaravelPositionsEast() {
        IPosition pos = new Position(6, 2);
        Caravel caravel = new Caravel(Compass.EAST, pos);

        assertEquals(2, caravel.getPositions().size());
        assertEquals(new Position(6, 2), caravel.getPositions().get(0));
        assertEquals(new Position(6, 3), caravel.getPositions().get(1));
    }

    @Test
    @DisplayName("Caravel em WEST ocupa duas posições horizontais")
    void testCaravelPositionsWest() {
        IPosition pos = new Position(1, 7);
        Caravel caravel = new Caravel(Compass.WEST, pos);

        assertEquals(2, caravel.getPositions().size());
        assertEquals(new Position(1, 7), caravel.getPositions().get(0));
        assertEquals(new Position(1, 8), caravel.getPositions().get(1));
    }

    @Test
    @DisplayName("Construtor da Caravel falha se bearing for null")
    void testCaravelNullBearingThrows() {
        IPosition pos = new Position(0, 0);

        assertThrows(AssertionError.class,
                () -> new Caravel(null, pos));
    }

    @Test
    @DisplayName("Caravel continua a flutuar depois de apenas um tiro certo")
    void testCaravelStillFloatingAfterOneCorrectShot() {
        IPosition pos = new Position(2, 3);
        Caravel caravel = new Caravel(Compass.NORTH, pos);

        assertTrue(caravel.stillFloating(), "Antes dos tiros deve estar a flutuar");

        // 1º tiro numa posição ocupada
        caravel.shoot(new Position(2, 3));

        assertTrue(caravel.stillFloating(),
                "Depois de apenas um acerto ainda deve estar a flutuar");
    }

    @Test
    @DisplayName("Caravel deixa de flutuar depois de dois tiros certos")
    void testCaravelStopsFloatingAfterTwoCorrectShots() {
        IPosition pos = new Position(2, 3);
        Caravel caravel = new Caravel(Compass.NORTH, pos);

        // dois tiros, um em cada posição
        caravel.shoot(new Position(2, 3));
        caravel.shoot(new Position(3, 3));

        assertFalse(caravel.stillFloating(),
                "Depois de dois acertos nas posições ocupadas já não deve flutuar");
    }

    @Test
    @DisplayName("Caravel continua a flutuar se o tiro falhar")
    void testCaravelStillFloatingIfShotMisses() {
        IPosition pos = new Position(5, 5);
        Caravel caravel = new Caravel(Compass.EAST, pos);

        caravel.shoot(new Position(9, 9)); // posição onde não há barco

        assertTrue(caravel.stillFloating(),
                "Tiro falhado não deve afundar a caravel");
    }

    @Test
    @DisplayName("Caravel guarda categoria, bearing e posição inicial corretamente")
    void testCaravelCategoryBearingAndInitialPosition() {
        IPosition pos = new Position(4, 4);
        Compass bearing = Compass.WEST;

        Caravel caravel = new Caravel(bearing, pos);

        assertEquals("Caravela", caravel.getCategory());
        assertEquals(bearing, caravel.getBearing());
        assertEquals(pos, caravel.getPosition());
    }
}