package pl.madamusinski.tictactoe.core.config;

import org.springframework.stereotype.Component;


public class GameConfig {
    private int numberOfPlayers;
    private int boardWidth;
    private int boardHeight;
    private final Character EMPTY_FIELD_SIGN = '.';

    public GameConfig(){

    }

    public GameConfig(int numberOfPlayers, int boardWidth, int boardHeight){
        this.numberOfPlayers = numberOfPlayers;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
    }



    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public void setBoardWidth(int boardWidth) {
        this.boardWidth = boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public void setBoardHeight(int boardHeight) {
        this.boardHeight = boardHeight;
    }

    public Character getEmptyFieldSign(){
        return EMPTY_FIELD_SIGN;
    }
}
