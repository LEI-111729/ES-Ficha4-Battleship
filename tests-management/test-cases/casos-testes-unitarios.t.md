# Test Suite - Casos de Testes Unitários

## Testes sobre Tasks

* **TMS-TSK-001** - Testes de interação com utilizador via Terminal

_(Para efeitos de cobertura, não considerar testes sobre a classe Tasks)_

---

## BargeTest 

* **TMS-BAR-001** - testBargeSize
* **TMS-BAR-002** - testBargePosition
* **TMS-BAR-003** - testBargeStopsFloatingAfterShootOnPosition
* **TMS-BAR-004** - testBargeStillFloatingIfShotMisses

---

## CaravelTest

* **TMS-CAR-001** - testCaravelSize
* **TMS-CAR-002** - testCaravelPositionsNorth
* **TMS-CAR-003** - testCaravelPositionsSouth
* **TMS-CAR-004** - testCaravelPositionsEast
* **TMS-CAR-005** - testCaravelPositionsWest
* **TMS-CAR-006** - testCaravelNullBearingThrows
* **TMS-CAR-007** - testCaravelStillFloatingAfterOneCorrectShot
* **TMS-CAR-008** - testCaravelStopsFloatingAfterTwoCorrectShots
* **TMS-CAR-009** - testCaravelStillFloatingIfShotMisses
* **TMS-CAR-010** - testCaravelCategoryBearingAndInitialPosition

---

## CarrackTest

* **TMS-CRK-001** - testCarrackSize
* **TMS-CRK-002** - testCarrackPositionsNorth
* **TMS-CRK-003** - testCarrackPositionsSouth
* **TMS-CRK-004** - testCarrackPositionsEast
* **TMS-CRK-005** - testCarrackPositionsWest
* **TMS-CRK-006** - testCarrackStillFloatingAfterOneHit
* **TMS-CRK-007** - testCarrackStillFloatingAfterTwoHits
* **TMS-CRK-008** - testCarrackStopsFloatingAfterThreeHits
* **TMS-CRK-009** - testCarrackMissedShotStillFloating
* **TMS-CRK-010** - testCarrackCategoryBearingInitialPosition

---

## CompassTest

* **TMS-CMP-001** - testCharToCompassNorth
* **TMS-CMP-002** - testCharToCompassSouth
* **TMS-CMP-003** - testCharToCompassEast
* **TMS-CMP-004** - testCharToCompassWest
* **TMS-CMP-005** - testCharToCompassUnknown
* **TMS-CMP-006** - testCharToCompassParametrized
* **TMS-CMP-007** - testNorthGetDirection
* **TMS-CMP-008** - testSouthGetDirection
* **TMS-CMP-009** - testEastGetDirection
* **TMS-CMP-010** - testWestGetDirection
* **TMS-CMP-011** - testUnknownGetDirection
* **TMS-CMP-012** - testGetDirectionParametrized
* **TMS-CMP-013** - testNorthToString
* **TMS-CMP-014** - testSouthToString
* **TMS-CMP-015** - testEastToString
* **TMS-CMP-016** - testWestToString
* **TMS-CMP-017** - testUnknownToString
* **TMS-CMP-018** - testEnumValuesCount
* **TMS-CMP-019** - testValueOfNorth
* **TMS-CMP-020** - testValueOfInvalid

---

## FleetTest

* **TMS-FLT-001** - testCreateEmptyFleet
* **TMS-FLT-002** - testGetShipsNotNull
* **TMS-FLT-003** - testAddValidShipToEmptyFleet
* **TMS-FLT-004** - testAddMultipleValidShips
* **TMS-FLT-005** - testAddShipOutsideBoardNegative
* **TMS-FLT-006** - testAddShipOutsideBoardOverLimit
* **TMS-FLT-007** - testAddShipWithCollisionRisk
* **TMS-FLT-008** - testFleetSizeLimit
* **TMS-FLT-009** - testAddShipFailsDueToNegativeY
* **TMS-FLT-010** - testAddShipFailsDueToPositiveY
* **TMS-FLT-011** - testGetShipsLikeExistingCategory
* **TMS-FLT-012** - testGetShipsLikeNonExistingCategory
* **TMS-FLT-013** - testGetShipsLikeEmptyFleet
* **TMS-FLT-014** - testAllShipsFloatingInitially
* **TMS-FLT-015** - testGetFloatingShipsEmptyFleet
* **TMS-FLT-016** - testShipsNotFloatingAreFiltered
* **TMS-FLT-017** - testShipAtOccupiedPosition
* **TMS-FLT-018** - testShipAtEmptyPosition
* **TMS-FLT-019** - testShipAtEmptyFleet
* **TMS-FLT-020** - testShipAtWithMultipleShips
* **TMS-FLT-021** - testPrintStatusEmptyFleet
* **TMS-FLT-022** - testPrintStatusPopulatedFleet
* **TMS-FLT-023** - testPrintShipsByCategory
* **TMS-FLT-024** - testPrintFloatingShips
* **TMS-FLT-025** - testPrintShipsByCategoryThrowsExceptionForNull
* **TMS-FLT-026** - testPrintShipsEmptyList
* **TMS-FLT-027** - testPrintShipsPopulatedList

---

## FrigateTest

* **TMS-FRG-001** - testCreateFrigate
* **TMS-FRG-002** - testInitialState
* **TMS-FRG-003** - testNullBearingThrows
* **TMS-FRG-004** - testNorthPositions
* **TMS-FRG-005** - testSouthPositions
* **TMS-FRG-006** - testEastPositions
* **TMS-FRG-007** - testWestPositions
* **TMS-FRG-008** - testOccupiesAllPositions
* **TMS-FRG-009** - testDoesNotOccupyUnrelatedPosition
* **TMS-FRG-010** - testSinking
* **TMS-FRG-011** - testMissedShot

---

## GalleonTest

* **TMS-GAL-001** - testCreateGalleon
* **TMS-GAL-002** - testInitialState
* **TMS-GAL-003** - testNullBearingThrows
* **TMS-GAL-004** - testNorthPositions
* **TMS-GAL-005** - testSouthPositions
* **TMS-GAL-006** - testEastPositions
* **TMS-GAL-007** - testWestPositions
* **TMS-GAL-008** - testOccupiesAllPositions
* **TMS-GAL-009** - testDoesNotOccupyUnrelatedPosition
* **TMS-GAL-010** - testSinking
* **TMS-GAL-011** - testMissedShot

---

## GameTest

* **TMS-GAM-001** - invalidShot
* **TMS-GAM-002** - repeatedShot
* **TMS-GAM-003** - validShotNoShip
* **TMS-GAM-004** - hitShipButNotSunk
* **TMS-GAM-005** - hitShipAndSink
* **TMS-GAM-006** - remainingShips
* **TMS-GAM-007** - printValidShotsRuns
* **TMS-GAM-008** - printFleetRuns
* **TMS-GAM-009** - testInvalidRowNegative
* **TMS-GAM-010** - testInvalidRowTooHigh
* **TMS-GAM-011** - testInvalidColNegative
* **TMS-GAM-012** - testInvalidColTooHigh

---

## PositionTest

* **TMS-POS-001** - testConstructorAndGetters
* **TMS-POS-002** - testOccupy
* **TMS-POS-003** - testShoot
* **TMS-POS-004** - testToString
* **TMS-POS-005** - testEqualsSameObject
* **TMS-POS-006** - testEqualsDifferentTypeOrNull
* **TMS-POS-007** - testEqualsDifferentCoordinates
* **TMS-POS-008** - testEqualsSameCoordinates
* **TMS-POS-009** - testHashCode
* **TMS-POS-010** - testIsAdjacentToTrue
* **TMS-POS-011** - testIsAdjacentToFalse

---

## ShipTest

* **TMS-SHP-001** - buildShipCreatesValidShip
* **TMS-SHP-002** - getCategory
* **TMS-SHP-003** - getPositions
* **TMS-SHP-004** - getPosition
* **TMS-SHP-005** - getBearing
* **TMS-SHP-006** - stillFloatingWhenNew
* **TMS-SHP-007** - getTopMostPos
* **TMS-SHP-008** - getBottomMostPos
* **TMS-SHP-009** - getLeftMostPos
* **TMS-SHP-010** - getRightMostPos
* **TMS-SHP-011** - occupies
* **TMS-SHP-012** - tooCloseTo
* **TMS-SHP-013** - testTooCloseTo
* **TMS-SHP-014** - shoot
* **TMS-SHP-015** - testToString
* **TMS-SHP-016** - buildShipCreatesAllConcreteTypes
* **TMS-SHP-017** - buildShipReturnsNullForUnknownKind
* **TMS-SHP-018** - testConstructorAsserts
* **TMS-SHP-019** - testMethodsAsserts
* **TMS-SHP-020** - testGetTopMostPosUpdates

---

## Tags
Tags: #unit-tests, #test-cases, #battleship, #automated-testing, #coverage, #tms-identifiers