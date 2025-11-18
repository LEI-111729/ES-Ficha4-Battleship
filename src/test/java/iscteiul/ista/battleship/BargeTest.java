package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BargeTest {

    @Test
    @DisplayName("Barge deve ter tamanho 1")
    void testBargeSize() {
        // Arrange
        IPosition pos = new Position(3, 4);

        // Act
        Barge barge = new Barge(Compass.NORTH, pos);

        // Assert
        assertEquals(1, barge.getSize());
    }

    @Test
    @DisplayName("Barge deve ocupar apenas a posição inicial")
    void testBargePosition() {
        // Arrange
        IPosition pos = new Position(5, 6);

        // Act
        Barge barge = new Barge(Compass.EAST, pos);

        // Assert
        assertEquals(1, barge.getPositions().size(), "Barge deve ter exatamente 1 posição");
        assertEquals(5, barge.getPositions().get(0).getRow());
        assertEquals(6, barge.getPositions().get(0).getColumn());
    }

    @Test
    @DisplayName("Barge deixa de flutuar depois de levar um tiro na posição certa")
    void testBargeStopsFloatingAfterShootOnPosition() {
        // Arrange
        IPosition pos = new Position(2, 3);
        Barge barge = new Barge(Compass.NORTH, pos);

        // sanity check: antes do tiro ainda está a flutuar
        assertTrue(barge.stillFloating(), "Antes do tiro a barge deve estar a flutuar");

        // Act – damos um tiro na posição onde o barco está
        barge.shoot(new Position(2, 3));

        // Assert – depois do tiro já não deve estar a flutuar
        assertFalse(barge.stillFloating(), "Depois do tiro certo a barge já não deve estar a flutuar");
    }

    @Test
    @DisplayName("Barge continua a flutuar se o tiro falhar")
    void testBargeStillFloatingIfShotMisses() {
        // Arrange
        IPosition pos = new Position(2, 3);
        Barge barge = new Barge(Compass.NORTH, pos);

        // Act – damos um tiro noutra posição qualquer
        barge.shoot(new Position(9, 9));

        // Assert
        assertTrue(barge.stillFloating(), "Tiro falhado não deve afundar a barge");
    }

}


