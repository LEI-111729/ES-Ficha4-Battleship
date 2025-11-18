package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarrackTest {

    @Test
    @DisplayName("Carrack deve ter tamanho 3")
    void testCarrackSize() {
        Carrack carrack = new Carrack(Compass.NORTH, new Position(1, 1));
        assertEquals(3, carrack.getSize());
    }

    @Test
    @DisplayName("Carrack em NORTH ocupa três posições verticais")
    void testCarrackPositionsNorth() {
        IPosition pos = new Position(2, 2);
        Carrack carrack = new Carrack(Compass.NORTH, pos);

        assertEquals(3, carrack.getPositions().size());
        assertEquals(2, carrack.getPositions().get(0).getRow());
        assertEquals(2, carrack.getPositions().get(0).getColumn());
        assertEquals(3, carrack.getPositions().get(1).getRow());
        assertEquals(2, carrack.getPositions().get(1).getColumn());
        assertEquals(4, carrack.getPositions().get(2).getRow());
        assertEquals(2, carrack.getPositions().get(2).getColumn());
    }

    @Test
    @DisplayName("Carrack em SOUTH ocupa três posições verticais")
    void testCarrackPositionsSouth() {
        IPosition pos = new Position(5, 1);
        Carrack carrack = new Carrack(Compass.SOUTH, pos);

        assertEquals(3, carrack.getPositions().size());
        assertEquals(5, carrack.getPositions().get(0).getRow());
        assertEquals(1, carrack.getPositions().get(0).getColumn());
        assertEquals(6, carrack.getPositions().get(1).getRow());
        assertEquals(1, carrack.getPositions().get(1).getColumn());
        assertEquals(7, carrack.getPositions().get(2).getRow());
        assertEquals(1, carrack.getPositions().get(2).getColumn());
    }

    @Test
    @DisplayName("Carrack em EAST ocupa três posições horizontais")
    void testCarrackPositionsEast() {
        IPosition pos = new Position(4, 4);
        Carrack carrack = new Carrack(Compass.EAST, pos);

        assertEquals(3, carrack.getPositions().size());
        assertEquals(4, carrack.getPositions().get(0).getRow());
        assertEquals(4, carrack.getPositions().get(0).getColumn());
        assertEquals(4, carrack.getPositions().get(1).getRow());
        assertEquals(5, carrack.getPositions().get(1).getColumn());
        assertEquals(4, carrack.getPositions().get(2).getRow());
        assertEquals(6, carrack.getPositions().get(2).getColumn());
    }

    @Test
    @DisplayName("Carrack em WEST ocupa três posições horizontais")
    void testCarrackPositionsWest() {
        IPosition pos = new Position(7, 3);
        Carrack carrack = new Carrack(Compass.WEST, pos);

        assertEquals(3, carrack.getPositions().size());
        assertEquals(7, carrack.getPositions().get(0).getRow());
        assertEquals(3, carrack.getPositions().get(0).getColumn());
        assertEquals(7, carrack.getPositions().get(1).getRow());
        assertEquals(4, carrack.getPositions().get(1).getColumn());
        assertEquals(7, carrack.getPositions().get(2).getRow());
        assertEquals(5, carrack.getPositions().get(2).getColumn());
    }

    @Test
    @DisplayName("Carrack continua a flutuar após um tiro certo")
    void testCarrackStillFloatingAfterOneHit() {
        IPosition pos = new Position(1, 1);
        Carrack carrack = new Carrack(Compass.NORTH, pos);

        carrack.shoot(new Position(1, 1)); // primeiro hit
        assertTrue(carrack.stillFloating(),
                "Depois de um hit ainda deve flutuar");
    }

    @Test
    @DisplayName("Carrack continua a flutuar após dois tiros certos")
    void testCarrackStillFloatingAfterTwoHits() {
        IPosition pos = new Position(1, 1);
        Carrack carrack = new Carrack(Compass.NORTH, pos);

        carrack.shoot(new Position(1, 1));
        carrack.shoot(new Position(2, 1));

        assertTrue(carrack.stillFloating(),
                "Depois de dois hits ainda deve flutuar");
    }

    @Test
    @DisplayName("Carrack deixa de flutuar após três tiros certos")
    void testCarrackStopsFloatingAfterThreeHits() {
        IPosition pos = new Position(1, 1);
        Carrack carrack = new Carrack(Compass.NORTH, pos);

        carrack.shoot(new Position(1, 1));
        carrack.shoot(new Position(2, 1));
        carrack.shoot(new Position(3, 1));

        assertFalse(carrack.stillFloating(),
                "Depois de três hits deve afundar");
    }

    @Test
    @DisplayName("Carrack continua a flutuar se o tiro falhar")
    void testCarrackMissedShotStillFloating() {
        IPosition pos = new Position(3, 3);
        Carrack carrack = new Carrack(Compass.EAST, pos);

        carrack.shoot(new Position(9, 9)); // falha

        assertTrue(carrack.stillFloating(),
                "Tiro falhado não deve afundar o barco");
    }

    @Test
    @DisplayName("Carrack guarda categoria, bearing e posição inicial corretamente")
    void testCarrackCategoryBearingInitialPosition() {
        IPosition pos = new Position(4, 4);
        Compass bearing = Compass.SOUTH;

        Carrack carrack = new Carrack(bearing, pos);

        assertEquals("Nau", carrack.getCategory());
        assertEquals(bearing, carrack.getBearing());
        assertEquals(pos, carrack.getPosition());
    }
}