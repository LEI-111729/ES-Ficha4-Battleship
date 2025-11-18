package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para a classe Galleon")
class GalleonTest {

    private Galleon galleon;
    private Position position;

    @BeforeEach
    void setUp() {
        position = new Position(5, 5);
        galleon = new Galleon(Compass.NORTH, position);
    }

    @Test
    @DisplayName("Criar galeão com sucesso")
    void testCreateGalleon() {
        assertNotNull(galleon);
        assertEquals(5, galleon.getSize());
    }

    @Test
    @DisplayName("Verificar categoria do galeão")
    void testGetCategory() {
        assertEquals("Galeao", galleon.getCategory()); // Ajuste conforme retorna
    }

    @Test
    @DisplayName("Verificar posição inicial")
    void testGetPosition() {
        assertEquals(position, galleon.getPosition());
    }

    @Test
    @DisplayName("Verificar orientação")
    void testGetBearing() {
        assertEquals(Compass.NORTH, galleon.getBearing());
    }

    @Nested
    @DisplayName("Testes de ocupação de posições")
    class OccupationTests {

        @Test
        @DisplayName("Galeão ocupa posição inicial")
        void testOccupiesInitialPosition() {
            assertTrue(galleon.occupies(position));
        }

        @Test
        @DisplayName("Galeão não ocupa posição fora do seu alcance")
        void testDoesNotOccupyDistantPosition() {
            Position distant = new Position(0, 0);
            assertFalse(galleon.occupies(distant));
        }
    }

    @AfterEach
    void tearDown() {
        galleon = null;
        position = null;
    }
}