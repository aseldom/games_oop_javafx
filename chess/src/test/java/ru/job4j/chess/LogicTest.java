package ru.job4j.chess;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;
import ru.job4j.chess.firuges.black.KingBlack;
import ru.job4j.chess.firuges.white.KingWhite;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
public class LogicTest {

    @Test
    public void whenMoveThenFigureNotFoundException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        FigureNotFoundException exception = assertThrows(FigureNotFoundException.class, () -> {
            logic.move(Cell.C1, Cell.H6);
        });
        assertThat(exception.getMessage()).isEqualTo("Figure not found on the board.");
    }

    @Test
    public void whenMoveThenImpossibleMoveException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        Logic logic = new Logic();
        logic.add(bishopBlack);
        ImpossibleMoveException exception = assertThrows(ImpossibleMoveException.class, () -> {
            logic.move(Cell.C1, Cell.C2);
        });
        assertThat(exception.getMessage()).isEqualTo(String.format
                ("Could not move by diagonal from %s to %s", Cell.C1, Cell.C2));
    }

    @Test
    public void whenMoveThenOccupiedCellException ()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        KingWhite kingWhite = new KingWhite(Cell.C1);
        KingBlack kingBlack = new KingBlack(Cell.H6);
        Logic logic = new Logic();
        logic.add(kingWhite);
        logic.add(kingBlack);
        OccupiedCellException exception = assertThrows(OccupiedCellException.class, () -> {
            logic.move(Cell.C1, Cell.H6);
        });
        assertThat(exception.getMessage()).isEqualTo(String.format
                ("Cell %s occupied", Cell.H6));
    }



//    @Test
//    public void moveWhenBishopBlackC1ThenH6()
//            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
//        Logic logic = new Logic();
//        Cell source = Cell.C1;
//        Cell dest = Cell.H6;
//        BishopBlack bishopBlack = new BishopBlack(source);
//        logic.add(bishopBlack);
//        logic.move(source, dest);
//        assertThat(logic.).isEqualTo(dest);
//    }
}





