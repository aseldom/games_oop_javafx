package ru.job4j.chess.firuges.black;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

class BishopBlackTest {

    @Test
    void positionWhenA1() {
        Cell cell = Cell.A1;
        BishopBlack bishopBlack = new BishopBlack(cell);
        assertThat(bishopBlack.position()).isEqualTo(cell);
    }

    @Test
    void copyWhenA1ThenA2() {
        Cell cell = Cell.A1;
        Cell cellDest = Cell.A2;
        BishopBlack bishopBlack = new BishopBlack(cell);
        assertThat(bishopBlack.copy(cellDest).position()).isEqualTo(cellDest);

    }

    @Test
    void wayWhenC1G5ThenD2E3F4G5() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        Cell[] stepsExpected = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        Cell[] steps = bishopBlack.way(Cell.G5);
        assertThat(steps).isEqualTo(stepsExpected);
    }

    @Test
    void wayWhenException() {
        Cell position = Cell.C1;
        Cell destination = Cell.F5;
        BishopBlack bishopBlack = new BishopBlack(position);
        ImpossibleMoveException exception = assertThrows(
                ImpossibleMoveException.class,
                () -> {
                    bishopBlack.way(destination);
                });
        assertThat(exception.getMessage()).isEqualTo(String.format(
                "Could not move by diagonal from %s to %s", position, destination));
    }
}