package pl.madamusinski.tictactoe.core.board.factory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.madamusinski.tictactoe.domain.board.Board;
import pl.madamusinski.tictactoe.domain.board.TicTacToeStandardBoard;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestTicTacToeStandardBoardFactory {

    BoardFactory<Board> boardFactory;
    TicTacToeStandardBoard testBoard;

    @BeforeEach
    public void init(){
        boardFactory = new TicTacToeStandardBoardFactory();
        testBoard  = (TicTacToeStandardBoard)boardFactory.createBoard(4, 4, '.');
    }

    @Test
    public void testBoardCreationWidthCheck(){
        assertEquals(4, testBoard.getBoardField().length);
    }

    @Test
    public void testBoardCreationHeightCheck(){
        assertEquals(4, testBoard.getBoardField()[0].length);
    }

    @Test
    public void testBoardIsEmpty(){
        for(int width = 0; width<testBoard.getBoardField().length; width++){
            for(int height = 0; height<testBoard.getBoardField()[width].length; height++){
                assertEquals('.', testBoard.getBoardField()[width][height].getSignDisplay());
            }
        }
    }
}
