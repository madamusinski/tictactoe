package pl.madamusinski.tictactoe.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import pl.madamusinski.tictactoe.domain.player.HumanPlayer;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestHumanPlayerSpawner {

    @Autowired
    HumanPlayerSpawner humanPlayerSpawner;

    @Test
    public void testNumberOfSpawnedPlayers(){
        humanPlayerSpawner.spawnPlayers(3);
        assertEquals(3, humanPlayerSpawner.getPlayers().size());
    }

    @Test
    public void testPlayerSign(){
        humanPlayerSpawner.spawnPlayers(2);
        assertEquals('X', humanPlayerSpawner.getPlayers().get(0).getPlayerSymbol());
    }
}
