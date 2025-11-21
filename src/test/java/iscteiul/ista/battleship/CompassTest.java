package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para o enum Compass")
class CompassTest {

    @Nested
    @DisplayName("Testes de conversão de caracteres para Compass")
    class CharToCompassTests {

        // TMS-CMP-001
        @Test
        @DisplayName("Converter 'n' para NORTH")
        void testCharToCompassNorth() {
            assertEquals(Compass.NORTH, Compass.charToCompass('n'));
        }

        // TMS-CMP-002
        @Test
        @DisplayName("Converter 's' para SOUTH")
        void testCharToCompassSouth() {
            assertEquals(Compass.SOUTH, Compass.charToCompass('s'));
        }

        // TMS-CMP-003
        @Test
        @DisplayName("Converter 'e' para EAST")
        void testCharToCompassEast() {
            assertEquals(Compass.EAST, Compass.charToCompass('e'));
        }

        // TMS-CMP-004
        @Test
        @DisplayName("Converter 'o' para WEST")
        void testCharToCompassWest() {
            assertEquals(Compass.WEST, Compass.charToCompass('o'));
        }

        // TMS-CMP-005
        @Test
        @DisplayName("Converter caracter inválido para UNKNOWN")
        void testCharToCompassUnknown() {
            assertEquals(Compass.UNKNOWN, Compass.charToCompass('x'));
        }

        // TMS-CMP-006
        @ParameterizedTest
        @CsvSource({
                "'n', NORTH",
                "'s', SOUTH",
                "'e', EAST",
                "'o', WEST",
                "'x', UNKNOWN",
                "'z', UNKNOWN"
        })
        @DisplayName("Teste parametrizado de conversão de caracteres")
        void testCharToCompassParametrized(char input, Compass expected) {
            assertEquals(expected, Compass.charToCompass(input));
        }
    }

    @Nested
    @DisplayName("Testes do método getDirection()")
    class GetDirectionTests {

        // TMS-CMP-007
        @Test
        @DisplayName("NORTH retorna 'n'")
        void testNorthGetDirection() {
            assertEquals('n', Compass.NORTH.getDirection());
        }

        // TMS-CMP-008
        @Test
        @DisplayName("SOUTH retorna 's'")
        void testSouthGetDirection() {
            assertEquals('s', Compass.SOUTH.getDirection());
        }

        // TMS-CMP-009
        @Test
        @DisplayName("EAST retorna 'e'")
        void testEastGetDirection() {
            assertEquals('e', Compass.EAST.getDirection());
        }

        // TMS-CMP-010
        @Test
        @DisplayName("WEST retorna 'o'")
        void testWestGetDirection() {
            assertEquals('o', Compass.WEST.getDirection());
        }

        // TMS-CMP-011
        @Test
        @DisplayName("UNKNOWN retorna 'u'")
        void testUnknownGetDirection() {
            assertEquals('u', Compass.UNKNOWN.getDirection());
        }

        // TMS-CMP-012
        @ParameterizedTest
        @CsvSource({
                "NORTH, 'n'",
                "SOUTH, 's'",
                "EAST, 'e'",
                "WEST, 'o'",
                "UNKNOWN, 'u'"
        })
        @DisplayName("Teste parametrizado de getDirection()")
        void testGetDirectionParametrized(Compass compass, char expected) {
            assertEquals(expected, compass.getDirection());
        }
    }

    @Nested
    @DisplayName("Testes do método toString()")
    class ToStringTests {

        // TMS-CMP-013
        @Test
        @DisplayName("NORTH.toString() retorna 'n'")
        void testNorthToString() {
            assertEquals("n", Compass.NORTH.toString());
        }

        // TMS-CMP-014
        @Test
        @DisplayName("SOUTH.toString() retorna 's'")
        void testSouthToString() {
            assertEquals("s", Compass.SOUTH.toString());
        }

        // TMS-CMP-015
        @Test
        @DisplayName("EAST.toString() retorna 'e'")
        void testEastToString() {
            assertEquals("e", Compass.EAST.toString());
        }

        // TMS-CMP-016
        @Test
        @DisplayName("WEST.toString() retorna 'o'")
        void testWestToString() {
            assertEquals("o", Compass.WEST.toString());
        }

        // TMS-CMP-017
        @Test
        @DisplayName("UNKNOWN.toString() retorna 'u'")
        void testUnknownToString() {
            assertEquals("u", Compass.UNKNOWN.toString());
        }
    }

    @Nested
    @DisplayName("Testes dos valores do enum")
    class EnumValuesTests {

        // TMS-CMP-018
        @Test
        @DisplayName("Verificar que existem exatamente 5 valores no enum")
        void testEnumValuesCount() {
            assertEquals(5, Compass.values().length);
        }

        // TMS-CMP-019
        @Test
        @DisplayName("Verificar valueOf() para NORTH")
        void testValueOfNorth() {
            assertEquals(Compass.NORTH, Compass.valueOf("NORTH"));
        }

        // TMS-CMP-020
        @Test
        @DisplayName("Verificar valueOf() para valor inválido lança exceção")
        void testValueOfInvalid() {
            assertThrows(IllegalArgumentException.class, () -> {
                Compass.valueOf("INVALID");
            });
        }
    }
}