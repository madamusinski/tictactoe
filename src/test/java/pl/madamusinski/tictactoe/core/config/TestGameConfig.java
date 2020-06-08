package pl.madamusinski.tictactoe.core.config;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestGameConfig {

    private GameConfig gameConfig;

    @BeforeEach
    public void init(){
        gameConfig = new GameConfig();
    }

    @Test
    public void testGameConfigNumberOfPlayers(){
        gameConfig.setNumberOfPlayers(3);
        assertEquals(3, gameConfig.getNumberOfPlayers());
    }

    @Test
    public void testGameConfigBoardHeight(){
        gameConfig.setBoardHeight(4);
        assertEquals(4, gameConfig.getBoardHeight());
    }

    @Test
    public void testGameConfigBoardWidth(){
        gameConfig.setBoardWidth(5);
        assertEquals(5, gameConfig.getBoardWidth());
    }

    @Test
    public void testEmptyFieldMarker(){
        assertEquals('.', gameConfig.getEmptyFieldSign());
    }
}
