package pl.madamusinski.tictactoe.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestHumanPlayerSpawner {

    @Autowired
    private HumanPlayerSpawner humanPlayerSpawner;

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
