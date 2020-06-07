package pl.madamusinski.tictactoe.service;

import org.springframework.stereotype.Component;
import pl.madamusinski.tictactoe.domain.Field;
import pl.madamusinski.tictactoe.domain.board.Board;
import pl.madamusinski.tictactoe.domain.board.TicTacToeStandardBoard;

import java.util.*;


@Component
public class BoardPrinterImpl implements BoardPrinter<Board> {

    @Override
    public void printBoard(Board board) {
        Field[][] fieldBoard = ((TicTacToeStandardBoard) board).getBoardField();
        for(int row = 0; row<fieldBoard.length; row++){
            for(int column = 0; column<fieldBoard[row].length; column++){
                System.out.print(fieldBoard[row][column].getSignDisplay() + " ");
            }
                System.out.println();
        }
    }
}
