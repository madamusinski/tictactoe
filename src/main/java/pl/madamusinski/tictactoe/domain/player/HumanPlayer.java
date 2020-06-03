package pl.madamusinski.tictactoe.domain.player;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author Mateusz Adamusiński
 * Implementation of Player interface
 */

public class HumanPlayer implements Player {

    private String name;
    private Character playerSymbol;
    private boolean isMyTurn;

    public HumanPlayer(String name, char playerSymbol){
        this.name = name;
        this.playerSymbol = playerSymbol;
    }

    @Override
    public boolean isMyTurn() {
        return isMyTurn;
    }

    public void setMyTurn(boolean isMyTurn){
        this.isMyTurn = isMyTurn;
    }

    public String getName(){
        return name;
    }

    public Character getPlayerSymbol(){
        return playerSymbol;
    }
}
