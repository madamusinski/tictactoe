package pl.madamusinski.tictactoe.core;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.madamusinski.tictactoe.core.board.factory.TicTacToeStandardBoardFactory;
import pl.madamusinski.tictactoe.core.config.GameConfig;
import pl.madamusinski.tictactoe.domain.board.Board;
import pl.madamusinski.tictactoe.domain.board.TicTacToeStandardBoard;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestTicTacToeGameRunner {

    @Autowired
    TicTacToeGameRunnerImpl gameRunner;
    @Autowired
    TicTacToeStandardBoardFactory boardFactory;

    @Test
    public void testSetGameConfig(){
        GameConfig testAgainst = new GameConfig();
        testAgainst.setNumberOfPlayers(2);
        testAgainst.setBoardWidth(3);
        testAgainst.setBoardHeight(3);
        gameRunner.setGameConfig(2, 3, 3);
        assertThat(gameRunner.getGameConfig())
                .isEqualToComparingFieldByField(testAgainst);
    }

    @Test
    public void testSetMarker(){
        Board testAgainstThisBoard = boardFactory.createBoard(3, 3, '.');
    }
}
