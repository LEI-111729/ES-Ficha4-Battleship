package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para a classe Galleon")
class GalleonTest {

    // ---------- Criação e estado inicial ----------

    @Nested
    @DisplayName("Criação do Galeão")
    class CreationTests {

        // TMS-GAL-001
        @Test
        @DisplayName("Criar galeão com sucesso e tamanho correto")
        void testCreateGalleon() {
            Galleon g = new Galleon(Compass.NORTH, new Position(5, 5));

            assertNotNull(g);
            assertEquals(5, g.getSize());
            assertEquals("Galeao", g.getCategory());
        }

        // TMS-GAL-002
        @Test
        @DisplayName("Galeão guarda bearing e posição inicial")
        void testInitialState() {
            Position pos = new Position(4, 4);
            Galleon g = new Galleon(Compass.SOUTH, pos);

            assertEquals(pos, g.getPosition());
            assertEquals(Compass.SOUTH, g.getBearing());
        }

        // TMS-GAL-003
        @Test
        @DisplayName("Falha ao criar galeão com bearing null (AssertionError)")
        void testNullBearingThrows() {
            Position pos = new Position(0, 0);

            assertThrows(AssertionError.class,
                    () -> new Galleon(null, pos));
        }
    }

    // ---------- Orientações / posições ----------

    @Nested
    @DisplayName("Testes de orientação")
    class OrientationTests {

        // TMS-GAL-004
        @Test
        @DisplayName("NORTH — galeão ocupa 5 posições em T")
        void testNorthPositions() {
            Position pos = new Position(5, 5);
            Galleon g = new Galleon(Compass.NORTH, pos);

            // fillNorth:
            // (r,c) (r,c+1) (r,c+2) (r+1,c+1) (r+2,c+1)
            assertEquals(5, g.getPositions().size());

            assertEquals(new Position(5, 5), g.getPositions().get(0));
            assertEquals(new Position(5, 6), g.getPositions().get(1));
            assertEquals(new Position(5, 7), g.getPositions().get(2));
            assertEquals(new Position(6, 6), g.getPositions().get(3));
            assertEquals(new Position(7, 6), g.getPositions().get(4));
        }

        // TMS-GAL-005
        @Test
        @DisplayName("SOUTH — galeão ocupa 5 posições em T invertido")
        void testSouthPositions() {
            Position pos = new Position(5, 5);
            Galleon g = new Galleon(Compass.SOUTH, pos);

            // fillSouth:
            // (r,c) (r+1,c) (r+2,c-1) (r+2,c) (r+2,c+1)
            assertEquals(5, g.getPositions().size());

            assertEquals(new Position(5, 5), g.getPositions().get(0));
            assertEquals(new Position(6, 5), g.getPositions().get(1));
            assertEquals(new Position(7, 4), g.getPositions().get(2));
            assertEquals(new Position(7, 5), g.getPositions().get(3));
            assertEquals(new Position(7, 6), g.getPositions().get(4));
        }

        // TMS-GAL-006
        @Test
        @DisplayName("EAST — galeão ocupa 5 posições em forma de T virado")
        void testEastPositions() {
            Position pos = new Position(5, 5);
            Galleon g = new Galleon(Compass.EAST, pos);

            // fillEast:
            // (r,c), (r+1,c-2), (r+1,c-1), (r+1,c), (r+2,c)
            assertEquals(5, g.getPositions().size());

            assertEquals(new Position(5, 5), g.getPositions().get(0));
            assertEquals(new Position(6, 3), g.getPositions().get(1));
            assertEquals(new Position(6, 4), g.getPositions().get(2));
            assertEquals(new Position(6, 5), g.getPositions().get(3));
            assertEquals(new Position(7, 5), g.getPositions().get(4));
        }

        // TMS-GAL-007
        @Test
        @DisplayName("WEST — galeão ocupa 5 posições em forma de T virado")
        void testWestPositions() {
            Position pos = new Position(5, 5);
            Galleon g = new Galleon(Compass.WEST, pos);

            // fillWest:
            // (r,c), (r+1,c), (r+1,c+1), (r+1,c+2), (r+2,c)
            assertEquals(5, g.getPositions().size());

            assertEquals(new Position(5, 5), g.getPositions().get(0));
            assertEquals(new Position(6, 5), g.getPositions().get(1));
            assertEquals(new Position(6, 6), g.getPositions().get(2));
            assertEquals(new Position(6, 7), g.getPositions().get(3));
            assertEquals(new Position(7, 5), g.getPositions().get(4));
        }
    }

    // ---------- Ocupação e tiros ----------

    @Nested
    @DisplayName("Testes de ocupação e tiros")
    class ShootingTests {

        Galleon g;
        Position origin;

        @BeforeEach
        void setup() {
            origin = new Position(5, 5);
            g = new Galleon(Compass.NORTH, origin);
        }

        // TMS-GAL-008
        @Test
        @DisplayName("Galeão ocupa todas as suas posições")
        void testOccupiesAllPositions() {
            for (IPosition p : g.getPositions()) {
                assertTrue(g.occupies(p),
                        "Deve ocupar " + p);
            }
        }

        // TMS-GAL-009
        @Test
        @DisplayName("Galeão não ocupa posição fora do alcance")
        void testDoesNotOccupyUnrelatedPosition() {
            assertFalse(g.occupies(new Position(0, 0)));
        }

        // TMS-GAL-010
        @Test
        @DisplayName("Galeão só afunda após 5 tiros certos")
        void testSinking() {
            assertTrue(g.stillFloating(), "Deve começar a flutuar");

            // 4 hits → ainda flutua
            for (int i = 0; i < 4; i++) {
                g.shoot(g.getPositions().get(i));
            }
            assertTrue(g.stillFloating(), "Ainda deve flutuar após 4 hits");

            // 5º hit → afunda
            g.shoot(g.getPositions().get(4));
            assertFalse(g.stillFloating(), "Deve afundar após 5 hits");
        }

        // TMS-GAL-011
        @Test
        @DisplayName("Tiro falhado não afunda o galeão")
        void testMissedShot() {
            g.shoot(new Position(1, 1)); // posição fora do barco
            assertTrue(g.stillFloating(),
                    "Tiro falhado não deve afundar o galeão");
        }
    }
}