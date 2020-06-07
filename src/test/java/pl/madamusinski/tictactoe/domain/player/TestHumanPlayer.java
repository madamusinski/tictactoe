package pl.madamusinski.tictactoe.domain.player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestHumanPlayer {


    HumanPlayer testPlayer;
    @BeforeEach
    public void init(){
        testPlayer = new HumanPlayer("Player Test", 'T');
    }

    @Test
    public void testHumanPlayerGetName(){
        assertEquals("Player Test", testPlayer.getName());
    }

    @Test
    public void testHumanPlayerSign(){
        assertEquals('T', testPlayer.getPlayerSymbol());
    }


}
