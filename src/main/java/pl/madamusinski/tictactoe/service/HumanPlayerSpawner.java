package pl.madamusinski.tictactoe.service;


import org.springframework.stereotype.Component;
import pl.madamusinski.tictactoe.domain.player.HumanPlayer;
import pl.madamusinski.tictactoe.domain.player.Player;

import java.util.ArrayList;
import java.util.List;

@Component
public class HumanPlayerSpawner implements PlayerSpawner{

    List<HumanPlayer> players;
    private final String alphabet = "XOYZKGJ";
    public HumanPlayerSpawner(){
        players = new ArrayList<>();
    }

    @Override
    public void spawnPlayers(int numberOfPlayers){
        for(int i=0; i<numberOfPlayers; i++){
            players.add(new HumanPlayer("Player " + alphabet.charAt(i), alphabet.charAt(i)));
        }
    }

    public List<HumanPlayer> getPlayers(){
        return players;
    }


}
