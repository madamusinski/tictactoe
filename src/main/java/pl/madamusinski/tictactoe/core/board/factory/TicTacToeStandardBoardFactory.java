package pl.madamusinski.tictactoe.core.board.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import pl.madamusinski.tictactoe.domain.Field;
import pl.madamusinski.tictactoe.domain.board.Board;
import pl.madamusinski.tictactoe.domain.board.TicTacToeStandardBoard;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class TicTacToeStandardBoardFactory implements BoardFactory<Board> {

    @Autowired
    Environment env;
    private final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Override
    public TicTacToeStandardBoard createBoard(int boardWidth, int boardHeight, char emptyFieldFiller){
        TicTacToeStandardBoard board = new TicTacToeStandardBoard();

        Field[][] fieldBoard = new Field[boardHeight][boardWidth];
        for(int row = 1; row<=boardHeight; row++){
            for(int column = 1; column<=boardWidth; column++){
                fieldBoard[row-1][column-1] = new Field(alphabet.charAt(row-1) + String.valueOf(column), emptyFieldFiller);
            }
        }

        board.setBoardField(fieldBoard);
        return board;
    }
}
