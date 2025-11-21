package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para a classe Frigate")
class FrigateTest {

    // ---------- Criação e estado inicial ----------

    @Nested
    @DisplayName("Criação da Fragata")
    class CreationTests {

        // TMS-FRG-001
        @Test
        @DisplayName("Criar fragata com sucesso e tamanho correto")
        void testCreateFrigate() {
            Frigate f = new Frigate(Compass.NORTH, new Position(5, 5));

            assertNotNull(f);
            assertEquals(4, f.getSize());
            assertEquals("Fragata", f.getCategory());
        }

        // TMS-FRG-002
        @Test
        @DisplayName("Fragata guarda bearing e posição inicial")
        void testInitialState() {
            Position pos = new Position(3, 4);
            Frigate f = new Frigate(Compass.EAST, pos);

            assertEquals(pos, f.getPosition());
            assertEquals(Compass.EAST, f.getBearing());
        }

        // TMS-FRG-003
        @Test
        @DisplayName("Falha ao criar fragata com bearing null (AssertionError)")
        void testNullBearingThrows() {
            Position pos = new Position(0, 0);

            assertThrows(AssertionError.class,
                    () -> new Frigate(null, pos));
        }
    }

    // ---------- Orientações / posições ----------

    @Nested
    @DisplayName("Testes de orientação")
    class OrientationTests {

        // TMS-FRG-004
        @Test
        @DisplayName("NORTH — fragata ocupa 4 posições verticais")
        void testNorthPositions() {
            Position pos = new Position(5, 5);
            Frigate f = new Frigate(Compass.NORTH, pos);

            // (r,c), (r+1,c), (r+2,c), (r+3,c)
            assertEquals(4, f.getPositions().size());

            assertEquals(new Position(5, 5), f.getPositions().get(0));
            assertEquals(new Position(6, 5), f.getPositions().get(1));
            assertEquals(new Position(7, 5), f.getPositions().get(2));
            assertEquals(new Position(8, 5), f.getPositions().get(3));
        }

        // TMS-FRG-005
        @Test
        @DisplayName("SOUTH — fragata ocupa 4 posições verticais")
        void testSouthPositions() {
            Position pos = new Position(2, 3);
            Frigate f = new Frigate(Compass.SOUTH, pos);

            assertEquals(4, f.getPositions().size());

            assertEquals(new Position(2, 3), f.getPositions().get(0));
            assertEquals(new Position(3, 3), f.getPositions().get(1));
            assertEquals(new Position(4, 3), f.getPositions().get(2));
            assertEquals(new Position(5, 3), f.getPositions().get(3));
        }

        // TMS-FRG-006
        @Test
        @DisplayName("EAST — fragata ocupa 4 posições horizontais")
        void testEastPositions() {
            Position pos = new Position(4, 1);
            Frigate f = new Frigate(Compass.EAST, pos);

            // (r,c), (r,c+1), (r,c+2), (r,c+3)
            assertEquals(4, f.getPositions().size());

            assertEquals(new Position(4, 1), f.getPositions().get(0));
            assertEquals(new Position(4, 2), f.getPositions().get(1));
            assertEquals(new Position(4, 3), f.getPositions().get(2));
            assertEquals(new Position(4, 4), f.getPositions().get(3));
        }

        // TMS-FRG-007
        @Test
        @DisplayName("WEST — fragata ocupa 4 posições horizontais")
        void testWestPositions() {
            Position pos = new Position(7, 3);
            Frigate f = new Frigate(Compass.WEST, pos);

            assertEquals(4, f.getPositions().size());

            assertEquals(new Position(7, 3), f.getPositions().get(0));
            assertEquals(new Position(7, 4), f.getPositions().get(1));
            assertEquals(new Position(7, 5), f.getPositions().get(2));
            assertEquals(new Position(7, 6), f.getPositions().get(3));
        }
    }

    // ---------- Ocupação e tiros ----------

    @Nested
    @DisplayName("Testes de ocupação e tiros")
    class ShootingTests {

        Frigate f;
        Position origin;

        @BeforeEach
        void setup() {
            origin = new Position(5, 5);
            f = new Frigate(Compass.NORTH, origin);
        }

        // TMS-FRG-008
        @Test
        @DisplayName("Fragata ocupa todas as suas posições")
        void testOccupiesAllPositions() {
            for (IPosition p : f.getPositions()) {
                assertTrue(f.occupies(p),
                        "Deve ocupar " + p);
            }
        }

        // TMS-FRG-009
        @Test
        @DisplayName("Fragata não ocupa posição fora do alcance")
        void testDoesNotOccupyUnrelatedPosition() {
            assertFalse(f.occupies(new Position(0, 0)));
        }

        // TMS-FRG-010
        @Test
        @DisplayName("Fragata só afunda após 4 tiros certos")
        void testSinking() {
            assertTrue(f.stillFloating(), "Deve começar a flutuar");

            // 3 hits → ainda flutua
            for (int i = 0; i < 3; i++) {
                f.shoot(f.getPositions().get(i));
            }
            assertTrue(f.stillFloating(), "Ainda deve flutuar após 3 hits");

            // 4º hit → afunda
            f.shoot(f.getPositions().get(3));
            assertFalse(f.stillFloating(), "Deve afundar após 4 hits");
        }

        // TMS-FRG-011
        @Test
        @DisplayName("Tiro falhado não afunda a fragata")
        void testMissedShot() {
            f.shoot(new Position(1, 1)); // falha
            assertTrue(f.stillFloating(),
                    "Tiro falhado não deve afundar a fragata");
        }
    }
}