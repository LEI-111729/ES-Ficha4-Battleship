package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

@DisplayName("Testes para a classe Fleet")
class FleetTest {

    private Fleet fleet;
    private Position validPosition;

    @BeforeEach
    void setUp() {
        fleet = new Fleet();
        validPosition = new Position(5, 5);
    }

    @Nested
    @DisplayName("Testes de criação e inicialização")
    class InitializationTests {

        @Test
        @DisplayName("Criar frota vazia")
        void testCreateEmptyFleet() {
            assertNotNull(fleet);
            assertEquals(0, fleet.getShips().size());
        }

        @Test
        @DisplayName("getShips() retorna lista não nula")
        void testGetShipsNotNull() {
            assertNotNull(fleet.getShips());
        }
    }

    @Nested
    @DisplayName("Testes de adição de navios")
    class AddShipTests {

        @Test
        @DisplayName("Adicionar um navio válido à frota vazia")
        void testAddValidShipToEmptyFleet() {
            IShip ship = new Barge(Compass.NORTH, validPosition);
            assertTrue(fleet.addShip(ship));
            assertEquals(1, fleet.getShips().size());
        }

        @Test
        @DisplayName("Adicionar múltiplos navios válidos")
        void testAddMultipleValidShips() {
            IShip ship1 = new Barge(Compass.NORTH, new Position(0, 0));
            IShip ship2 = new Barge(Compass.SOUTH, new Position(5, 5));

            assertTrue(fleet.addShip(ship1));
            assertTrue(fleet.addShip(ship2));
            assertEquals(2, fleet.getShips().size());
        }

        @Test
        @DisplayName("Não permitir adicionar navio fora do tabuleiro - posição negativa")
        void testAddShipOutsideBoardNegative() {
            IShip ship = new Barge(Compass.NORTH, new Position(-1, 0));
            assertFalse(fleet.addShip(ship));
            assertEquals(0, fleet.getShips().size());
        }

        @Test
        @DisplayName("Não permitir adicionar navio fora do tabuleiro - posição superior ao limite")
        void testAddShipOutsideBoardOverLimit() {
            IShip ship = new Galleon(Compass.SOUTH, new Position(8, 8)); // Galeão tem tamanho 5, pode sair do tabuleiro
            assertFalse(fleet.addShip(ship));
        }

        @Test
        @DisplayName("Não permitir adicionar navio com risco de colisão")
        void testAddShipWithCollisionRisk() {
            IShip ship1 = new Barge(Compass.NORTH, new Position(2, 2));
            IShip ship2 = new Barge(Compass.NORTH, new Position(2, 3)); // Muito próximo

            assertTrue(fleet.addShip(ship1));
            assertFalse(fleet.addShip(ship2)); // Deve falhar por estar muito próximo
        }

        @Test
        @DisplayName("Verificar limite máximo de navios na frota")
        void testFleetSizeLimit() {
            // Adicionar navios até atingir o limite FLEET_SIZE
            for (int i = 0; i <= IFleet.FLEET_SIZE; i++) {
                IShip ship = new Barge(Compass.NORTH, new Position(i, 0));
                fleet.addShip(ship);
            }

            // A frota não deve ter mais que FLEET_SIZE + 1 navios
            assertTrue(fleet.getShips().size() <= IFleet.FLEET_SIZE + 1);
        }
    }

    @Nested
    @DisplayName("Testes de busca de navios por categoria")
    class GetShipsLikeTests {

        @Test
        @DisplayName("Buscar navios de uma categoria existente")
        void testGetShipsLikeExistingCategory() {
            fleet.addShip(new Barge(Compass.NORTH, new Position(0, 0)));
            fleet.addShip(new Barge(Compass.SOUTH, new Position(5, 5)));
            fleet.addShip(new Caravel(Compass.EAST, new Position(3, 3)));

            List<IShip> barges = fleet.getShipsLike("Barca");
            assertEquals(2, barges.size());
        }

        @Test
        @DisplayName("Buscar navios de uma categoria inexistente")
        void testGetShipsLikeNonExistingCategory() {
            fleet.addShip(new Barge(Compass.NORTH, new Position(0, 0)));

            List<IShip> galleons = fleet.getShipsLike("Galeao");
            assertEquals(0, galleons.size());
        }

        @Test
        @DisplayName("Buscar navios em frota vazia")
        void testGetShipsLikeEmptyFleet() {
            List<IShip> ships = fleet.getShipsLike("Barca");
            assertNotNull(ships);
            assertEquals(0, ships.size());
        }
    }

    @Nested
    @DisplayName("Testes de navios flutuantes")
    class FloatingShipsTests {

        @Test
        @DisplayName("Todos os navios estão flutuando inicialmente")
        void testAllShipsFloatingInitially() {
            fleet.addShip(new Barge(Compass.NORTH, new Position(0, 0)));
            fleet.addShip(new Caravel(Compass.SOUTH, new Position(5, 5)));

            List<IShip> floating = fleet.getFloatingShips();
            assertEquals(2, floating.size());
        }


        @Test
        @DisplayName("getFloatingShips() em frota vazia retorna lista vazia")
        void testGetFloatingShipsEmptyFleet() {
            List<IShip> floating = fleet.getFloatingShips();
            assertNotNull(floating);
            assertEquals(0, floating.size());
        }
    }

    @Nested
    @DisplayName("Testes de localização de navios")
    class ShipAtTests {

        @Test
        @DisplayName("Encontrar navio em posição ocupada")
        void testShipAtOccupiedPosition() {
            Position pos = new Position(3, 3);
            IShip ship = new Barge(Compass.NORTH, pos);
            fleet.addShip(ship);

            IShip foundShip = fleet.shipAt(pos);
            assertNotNull(foundShip);
            assertEquals(ship, foundShip);
        }

        @Test
        @DisplayName("Não encontrar navio em posição vazia")
        void testShipAtEmptyPosition() {
            fleet.addShip(new Barge(Compass.NORTH, new Position(0, 0)));

            IShip foundShip = fleet.shipAt(new Position(5, 5));
            assertNull(foundShip);
        }

        @Test
        @DisplayName("shipAt() em frota vazia retorna null")
        void testShipAtEmptyFleet() {
            IShip foundShip = fleet.shipAt(new Position(5, 5));
            assertNull(foundShip);
        }

        @Test
        @DisplayName("Encontrar navio correto quando há múltiplos navios")
        void testShipAtWithMultipleShips() {
            IShip ship1 = new Barge(Compass.NORTH, new Position(0, 0));
            IShip ship2 = new Caravel(Compass.SOUTH, new Position(5, 5));
            fleet.addShip(ship1);
            fleet.addShip(ship2);

            IShip foundShip = fleet.shipAt(new Position(5, 5));
            assertEquals(ship2, foundShip);
        }
    }

    @Nested
    @DisplayName("Testes de métodos de impressão")
    class PrintMethodsTests {

        @Test
        @DisplayName("printStatus() não lança exceção com frota vazia")
        void testPrintStatusEmptyFleet() {
            assertDoesNotThrow(() -> fleet.printStatus());
        }

        @Test
        @DisplayName("printStatus() não lança exceção com frota populada")
        void testPrintStatusPopulatedFleet() {
            fleet.addShip(new Barge(Compass.NORTH, new Position(0, 0)));
            fleet.addShip(new Caravel(Compass.SOUTH, new Position(5, 5)));

            assertDoesNotThrow(() -> fleet.printStatus());
        }

        @Test
        @DisplayName("printShipsByCategory() não lança exceção")
        void testPrintShipsByCategory() {
            fleet.addShip(new Barge(Compass.NORTH, new Position(0, 0)));

            assertDoesNotThrow(() -> fleet.printShipsByCategory("Barca"));
        }

        @Test
        @DisplayName("printFloatingShips() não lança exceção")
        void testPrintFloatingShips() {
            fleet.addShip(new Barge(Compass.NORTH, new Position(0, 0)));

            assertDoesNotThrow(() -> fleet.printFloatingShips());
        }
    }

    @Nested
    @DisplayName("Testes de método estático printShips")
    class StaticPrintShipsTests {

        @Test
        @DisplayName("printShips() com lista vazia não lança exceção")
        void testPrintShipsEmptyList() {
            List<IShip> emptyList = List.of();
            assertDoesNotThrow(() -> Fleet.printShips(emptyList));
        }

        @Test
        @DisplayName("printShips() com lista populada não lança exceção")
        void testPrintShipsPopulatedList() {
            List<IShip> ships = List.of(
                    new Barge(Compass.NORTH, new Position(0, 0)),
                    new Caravel(Compass.SOUTH, new Position(5, 5))
            );
            assertDoesNotThrow(() -> Fleet.printShips(ships));
        }
    }

    @AfterEach
    void tearDown() {
        fleet = null;
        validPosition = null;
    }
}