package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para a classe Frigate")
class FrigateTest {

    private Frigate frigate;
    private Position position;

    @BeforeEach
    void setUp() {
        // Inicialização antes de cada teste
        position = new Position(5, 5);
        frigate = new Frigate(Compass.NORTH, position);
    }

    @Test
    @DisplayName("Criar fragata com sucesso")
    void testCreateFrigate() {
        assertNotNull(frigate);
        assertEquals(4, frigate.getSize());
    }

    @Test
    @DisplayName("Verificar categoria da fragata")
    void testGetCategory() {
        assertEquals("Fragata", frigate.getCategory());  // ← F maiúsculo
    }

    @Test
    @DisplayName("Verificar posição inicial")
    void testGetPosition() {
        assertEquals(position, frigate.getPosition());
    }

    @Test
    @DisplayName("Verificar orientação")
    void testGetBearing() {
        assertEquals(Compass.NORTH, frigate.getBearing());
    }

    @Nested
    @DisplayName("Testes de ocupação de posições")
    class OccupationTests {

        @Test
        @DisplayName("Fragata ocupa posição inicial")
        void testOccupiesInitialPosition() {
            assertTrue(frigate.occupies(position));
        }

        @Test
        @DisplayName("Fragata não ocupa posição fora do seu alcance")
        void testDoesNotOccupyDistantPosition() {
            Position distant = new Position(0, 0);
            assertFalse(frigate.occupies(distant));
        }
    }

    @AfterEach
    void tearDown() {
        frigate = null;
        position = null;
    }
}