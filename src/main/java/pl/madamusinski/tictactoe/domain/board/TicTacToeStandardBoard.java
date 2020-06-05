package pl.madamusinski.tictactoe.domain.board;

import org.springframework.stereotype.Component;
import pl.madamusinski.tictactoe.domain.Field;

import java.util.HashMap;
import java.util.Map;

@Component
public class TicTacToeStandardBoard implements Board{

    private Map<String, Map<String, Character>> boardMap;
    private Field[][] boardField;

    public TicTacToeStandardBoard(){

    }


    public void setBoard(Map<String, Map<String, Character>> boardMap){
        this.boardMap = boardMap;
    }

    public Map getBoardMap(){
        return boardMap;
    }

    @Override
    public Board getBoard(){
        return this;
    }

    public void setBoardField(Field[][] boardField){
        this.boardField = boardField;
    }

    public Field[][] getBoardField(){
        return boardField;
    }


}
