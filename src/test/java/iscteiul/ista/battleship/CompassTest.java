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

        @Test
        @DisplayName("Converter 'n' para NORTH")
        void testCharToCompassNorth() {
            assertEquals(Compass.NORTH, Compass.charToCompass('n'));
        }

        @Test
        @DisplayName("Converter 's' para SOUTH")
        void testCharToCompassSouth() {
            assertEquals(Compass.SOUTH, Compass.charToCompass('s'));
        }

        @Test
        @DisplayName("Converter 'e' para EAST")
        void testCharToCompassEast() {
            assertEquals(Compass.EAST, Compass.charToCompass('e'));
        }

        @Test
        @DisplayName("Converter 'o' para WEST")
        void testCharToCompassWest() {
            assertEquals(Compass.WEST, Compass.charToCompass('o'));
        }

        @Test
        @DisplayName("Converter caracter inválido para UNKNOWN")
        void testCharToCompassUnknown() {
            assertEquals(Compass.UNKNOWN, Compass.charToCompass('x'));
        }

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

        @Test
        @DisplayName("NORTH retorna 'n'")
        void testNorthGetDirection() {
            assertEquals('n', Compass.NORTH.getDirection());
        }

        @Test
        @DisplayName("SOUTH retorna 's'")
        void testSouthGetDirection() {
            assertEquals('s', Compass.SOUTH.getDirection());
        }

        @Test
        @DisplayName("EAST retorna 'e'")
        void testEastGetDirection() {
            assertEquals('e', Compass.EAST.getDirection());
        }

        @Test
        @DisplayName("WEST retorna 'o'")
        void testWestGetDirection() {
            assertEquals('o', Compass.WEST.getDirection());
        }

        @Test
        @DisplayName("UNKNOWN retorna 'u'")
        void testUnknownGetDirection() {
            assertEquals('u', Compass.UNKNOWN.getDirection());
        }

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

        @Test
        @DisplayName("NORTH.toString() retorna 'n'")
        void testNorthToString() {
            assertEquals("n", Compass.NORTH.toString());
        }

        @Test
        @DisplayName("SOUTH.toString() retorna 's'")
        void testSouthToString() {
            assertEquals("s", Compass.SOUTH.toString());
        }

        @Test
        @DisplayName("EAST.toString() retorna 'e'")
        void testEastToString() {
            assertEquals("e", Compass.EAST.toString());
        }

        @Test
        @DisplayName("WEST.toString() retorna 'o'")
        void testWestToString() {
            assertEquals("o", Compass.WEST.toString());
        }

        @Test
        @DisplayName("UNKNOWN.toString() retorna 'u'")
        void testUnknownToString() {
            assertEquals("u", Compass.UNKNOWN.toString());
        }
    }

    @Nested
    @DisplayName("Testes dos valores do enum")
    class EnumValuesTests {

        @Test
        @DisplayName("Verificar que existem exatamente 5 valores no enum")
        void testEnumValuesCount() {
            assertEquals(5, Compass.values().length);
        }

        @Test
        @DisplayName("Verificar valueOf() para NORTH")
        void testValueOfNorth() {
            assertEquals(Compass.NORTH, Compass.valueOf("NORTH"));
        }

        @Test
        @DisplayName("Verificar valueOf() para valor inválido lança exceção")
        void testValueOfInvalid() {
            assertThrows(IllegalArgumentException.class, () -> {
                Compass.valueOf("INVALID");
            });
        }
    }
}