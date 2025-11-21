package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes ao comportamento global do Game")
class GameTest {

    // ------------------- MOCKS DE APOIO ------------------------
    static class MockFleet implements IFleet {

        IShip shipToReturn = null;
        boolean returnShip = false;
        int floatingShipsCount = 1;

        List<IShip> ships = new ArrayList<>();

        @Override
        public List<IShip> getShips() {
            return ships;
        }

        @Override
        public boolean addShip(IShip s) {
            ships.add(s);
            return true;
        }

        @Override
        public List<IShip> getShipsLike(String category) {
            List<IShip> list = new ArrayList<>();
            for (IShip ship : ships) {
                if (ship.getCategory().equals(category)) {
                    list.add(ship);
                }
            }
            return list;
        }

        @Override
        public List<IShip> getFloatingShips() {
            List<IShip> list = new ArrayList<>();
            for (int i = 0; i < floatingShipsCount; i++) {
                list.add(new MockShip(false)); // still floating
            }
            return list;
        }

        @Override
        public IShip shipAt(IPosition pos) {
            return returnShip ? shipToReturn : null;
        }

        @Override
        public void printStatus() {
            // Mock não imprime nada
        }
    }

    // ------------------- MOCK SHIP ------------------------
    static class MockShip implements IShip {
        boolean sink;

        MockShip(boolean sink) {
            this.sink = sink;
        }

        @Override
        public String getCategory() { return "mock"; }

        @Override
        public Integer getSize() { return 1; }

        @Override
        public List<IPosition> getPositions() {
            List<IPosition> p = new ArrayList<>();
            p.add(new Position(3,3));
            return p;
        }

        @Override
        public IPosition getPosition() { return new Position(3,3); }

        @Override
        public Compass getBearing() { return Compass.NORTH; }

        @Override
        public boolean stillFloating() { return !sink; }

        @Override
        public int getTopMostPos() { return 3; }

        @Override
        public int getBottomMostPos() { return 3; }

        @Override
        public int getLeftMostPos() { return 3; }

        @Override
        public int getRightMostPos() { return 3; }

        @Override
        public boolean occupies(IPosition pos) { return pos.equals(new Position(3,3)); }

        @Override
        public boolean tooCloseTo(IShip other) { return false; }

        @Override
        public boolean tooCloseTo(IPosition pos) { return false; }

        @Override
        public void shoot(IPosition pos) { /* ignore */ }
    }

    // ------------------------------------------------------------

    Game game;
    MockFleet fleet;

    @BeforeEach
    void setup() throws Exception {
        fleet = new MockFleet();
        game = new Game(fleet);

        setPrivateField(game, "countHits", 0);
        setPrivateField(game, "countSinks", 0);
    }

    private void setPrivateField(Object obj, String fieldName, Object value) throws Exception {
        var field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }

    static Position pos(int r, int c) {
        return new Position(r,c);
    }

    // ----------------------- TESTES AGRUPADOS --------------------------

    @Nested
    @DisplayName("Testes ao método fire()")
    class FireTests {

        // TMS-GAM-001
        @Test
        @DisplayName("1️⃣ Invalid shot incrementa countInvalidShots")
        void invalidShot() {
            Position p = pos(-1, -1);
            game.fire(p);
            assertEquals(1, game.getInvalidShots());
            assertEquals(0, game.getHits());
        }

        // TMS-GAM-002
        @Test
        @DisplayName("2️⃣ Repeated shot incrementa countRepeatedShots")
        void repeatedShot() {
            Position p = pos(5,5);
            game.fire(p);  // first time
            game.fire(p);  // repeated
            assertEquals(1, game.getRepeatedShots());
        }

        // TMS-GAM-003
        @Test
        @DisplayName("3️⃣ Valid shot sem barco não incrementa hits")
        void validShotNoShip() {
            Position p = pos(5,5);
            fleet.returnShip = false;

            game.fire(p);

            assertEquals(0, game.getHits());
            assertEquals(1, game.getShots().size());
        }

        // TMS-GAM-004
        @Test
        @DisplayName("4️⃣ Valid shot com barco mas sem afundar incrementa hits")
        void hitShipButNotSunk() {
            Position p = pos(3,3);
            fleet.returnShip = true;
            fleet.shipToReturn = new MockShip(false);

            game.fire(p);

            assertEquals(1, game.getHits());
            assertEquals(0, game.getSunkShips());
        }

        // TMS-GAM-005
        @Test
        @DisplayName("5️⃣ Valid shot com barco afundado incrementa sinks e retorna o ship")
        void hitShipAndSink() {
            Position p = pos(3,3);
            fleet.returnShip = true;
            fleet.shipToReturn = new MockShip(true);

            IShip result = game.fire(p);

            assertEquals(1, game.getHits());
            assertEquals(1, game.getSunkShips());
            assertNotNull(result);
        }
    }

    @Nested
    @DisplayName("Testes a métodos de contagem e estado")
    class CountTests {

        // TMS-GAM-006
        @Test
        @DisplayName("getRemainingShips devolve número correto")
        void remainingShips() {
            fleet.floatingShipsCount = 3;
            assertEquals(3, game.getRemainingShips());
        }

        // TMS-GAM-007
        @Test
        @DisplayName("printValidShots corre sem lançar exceções")
        void printValidShotsRuns() {
            // garantir que existem shots válidos para imprimir
            game.fire(pos(4,4));
            assertDoesNotThrow(() -> game.printValidShots());
        }

        // TMS-GAM-008
        @Test
        @DisplayName("printFleet corre sem lançar exceções")
        void printFleetRuns() {
            // garantir que existe pelo menos um ship na frota
            fleet.addShip(new MockShip(false));
            assertDoesNotThrow(() -> game.printFleet());
        }

        //NOVO TESTE

    }




    // NOVA CLASSE DE TESTES
    @Nested
    @DisplayName("Testes de Limites do Tabuleiro (validShot)")
    class BoundaryTests {
        // Limite superior do tabuleiro (assumindo 10)
        final int LIMIT = Fleet.BOARD_SIZE;

        // TMS-GAM-009
        @Test
        @DisplayName("Inválido: Linha negativa")
        void testInvalidRowNegative() {
            game.fire(new Position(-1, 5));
            assertEquals(1, game.getInvalidShots());
        }

        // TMS-GAM-010
        @Test
        @DisplayName("Inválido: Linha excessiva")
        void testInvalidRowTooHigh() {
            game.fire(new Position(LIMIT, 5)); // Agora com < LIMIT, isto falha corretamente
            assertEquals(1, game.getInvalidShots());
        }

        // TMS-GAM-011
        @Test
        @DisplayName("Inválido: Coluna negativa")
        void testInvalidColNegative() {
            game.fire(new Position(5, -1));
            assertEquals(1, game.getInvalidShots());
        }

        // TMS-GAM-012
        @Test
        @DisplayName("Inválido: Coluna excessiva")
        void testInvalidColTooHigh() {
            game.fire(new Position(5, LIMIT));
            assertEquals(1, game.getInvalidShots());
        }
    }

}