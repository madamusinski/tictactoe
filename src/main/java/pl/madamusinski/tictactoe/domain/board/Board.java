package pl.madamusinski.tictactoe.domain.board;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


public class Board {

    private Map<String, Map<String, Character>> board;

    public Board(Map<String, Map<String, Character>> board){
        this.board = board;
    }

    public void setBoard(Map<String, Map<String, Character>> board){
        this.board = board;
    }

    public Map<String, Map<String, Character>> getBoard(){
        return board;
    }
}
