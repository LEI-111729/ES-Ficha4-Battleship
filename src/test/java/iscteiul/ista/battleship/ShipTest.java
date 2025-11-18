package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes ao barco genérico (Ship/IShip)")
class ShipTest {

    private IShip ship;
    private Position start;
    private Compass bearing;

    @BeforeEach
    void setUp() {
        // posição inicial arbitrária
        start = new Position(2, 3);

        // escolhe um bearing qualquer existente no enum, sem assumir o nome
        bearing = Compass.values()[0];

        // Ship.buildShip precisa de um Position (não IPosition)
        ship = Ship.buildShip("barca", bearing, start);

        assertNotNull(ship, "buildShip deve devolver um barco válido");
    }

    // ----------------------------------------------------------------------
    @Test
    @DisplayName("buildShip cria um barco com categoria, bearing e posição válidos")
    void buildShipCreatesValidShip() {
        assertNotNull(ship.getCategory());
        assertEquals(bearing, ship.getBearing());
        assertEquals(start, ship.getPosition());
    }

    // ----------------------------------------------------------------------
    @Test
    @DisplayName("getCategory devolve a categoria do barco")
    void getCategory() {
        String category = ship.getCategory();
        assertNotNull(category);
        assertFalse(category.isBlank());
    }

    // ----------------------------------------------------------------------
    @Test
    @DisplayName("getPositions devolve todas as posições ocupadas pelo barco")
    void getPositions() {
        List<IPosition> positions = ship.getPositions();

        assertNotNull(positions);
        assertFalse(positions.isEmpty());
        assertEquals(ship.getSize(), positions.size());

        // todas as posições não podem ser nulas
        assertTrue(positions.stream().allMatch(p -> p != null));
    }

    // ----------------------------------------------------------------------
    @Test
    @DisplayName("getPosition devolve a posição inicial do barco")
    void getPosition() {
        assertEquals(start, ship.getPosition());
    }

    // ----------------------------------------------------------------------
    @Test
    @DisplayName("getBearing devolve o bearing correto do barco")
    void getBearing() {
        assertEquals(bearing, ship.getBearing());
    }

    // ----------------------------------------------------------------------
    @Test
    @DisplayName("Um barco novo ainda está a flutuar")
    void stillFloatingWhenNew() {
        assertTrue(ship.stillFloating());
    }

    // ----------------------------------------------------------------------
    @Test
    @DisplayName("getTopMostPos é o menor row das posições do barco")
    void getTopMostPos() {
        int top = ship.getTopMostPos();
        for (IPosition p : ship.getPositions()) {
            assertTrue(p.getRow() >= top);
        }
    }

    @Test
    @DisplayName("getBottomMostPos é o maior row das posições do barco")
    void getBottomMostPos() {
        int bottom = ship.getBottomMostPos();
        for (IPosition p : ship.getPositions()) {
            assertTrue(p.getRow() <= bottom);
        }
    }

    @Test
    @DisplayName("getLeftMostPos é o menor column das posições do barco")
    void getLeftMostPos() {
        int left = ship.getLeftMostPos();
        for (IPosition p : ship.getPositions()) {
            assertTrue(p.getColumn() >= left);
        }
    }

    @Test
    @DisplayName("getRightMostPos é o maior column das posições do barco")
    void getRightMostPos() {
        int right = ship.getRightMostPos();
        for (IPosition p : ship.getPositions()) {
            assertTrue(p.getColumn() <= right);
        }
    }

    // ----------------------------------------------------------------------
    @Test
    @DisplayName("occupies devolve true para posições ocupadas e false para posições afastadas")
    void occupies() {
        IPosition occupied = ship.getPositions().get(0);
        assertTrue(ship.occupies(occupied));

        IPosition far = new Position(50, 50);
        assertFalse(ship.occupies(far));
    }

    // ----------------------------------------------------------------------
    @Test
    @DisplayName("tooCloseTo(IShip) devolve true para barcos próximos e false para barcos afastados")
    void tooCloseTo() {
        IShip near = Ship.buildShip("barca", bearing, new Position(start.getRow() + 1, start.getColumn()));
        IShip far = Ship.buildShip("barca", bearing, new Position(start.getRow() + 10, start.getColumn() + 10));

        assertTrue(ship.tooCloseTo(near));
        assertFalse(ship.tooCloseTo(far));
    }

    @Test
    @DisplayName("tooCloseTo(IPosition) devolve true para posições adjacentes")
    void testTooCloseTo() {
        IPosition adjacent = new Position(start.getRow() + 1, start.getColumn());
        assertTrue(ship.tooCloseTo(adjacent));

        IPosition far = new Position(start.getRow() + 10, start.getColumn() + 10);
        assertFalse(ship.tooCloseTo(far));
    }

    // ----------------------------------------------------------------------
    @Test
    @DisplayName("shoot marca como atingida a posição do barco alvo")
    void shoot() {
        IPosition target = ship.getPositions().get(0);
        assertFalse(target.isHit());

        ship.shoot(target);

        assertTrue(target.isHit());
    }

    // ----------------------------------------------------------------------
    @Test
    @DisplayName("toString devolve uma representação textual não vazia")
    void testToString() {
        String s = ship.toString();
        assertNotNull(s);
        assertFalse(s.isBlank());
    }

    // ----------------------------------------------------------------------
    @Test
    @DisplayName("buildShip cria corretamente cada tipo concreto de barco")
    void buildShipCreatesAllConcreteTypes() {
        // BARCA -> Barge
        Ship barca = Ship.buildShip("barca", bearing, start);
        assertNotNull(barca);
        assertTrue(barca instanceof Barge);

        // CARAVELA -> Caravel
        Ship caravela = Ship.buildShip("caravela", bearing, start);
        assertNotNull(caravela);
        assertTrue(caravela instanceof Caravel);

        // NAU -> Carrack
        Ship nau = Ship.buildShip("nau", bearing, start);
        assertNotNull(nau);
        assertTrue(nau instanceof Carrack);

        // FRAGATA -> Frigate
        Ship fragata = Ship.buildShip("fragata", bearing, start);
        assertNotNull(fragata);
        assertTrue(fragata instanceof Frigate);

        // GALEAO -> Galleon
        Ship galeao = Ship.buildShip("galeao", bearing, start);
        assertNotNull(galeao);
        assertTrue(galeao instanceof Galleon);
    }

    @Test
    @DisplayName("buildShip devolve null para tipo de barco desconhecido")
    void buildShipReturnsNullForUnknownKind() {
        Ship unknown = Ship.buildShip("navio-pirata", bearing, start);
        assertNull(unknown);
    }

}
