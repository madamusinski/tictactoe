package pl.madamusinski.tictactoe.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.madamusinski.tictactoe.domain.board.Board;


@Component
public class BoardPrinterImpl implements BoardPrinter<Board> {

    @Override
    public void printBoard(Board board) {
        board.getBoard().entrySet().stream().forEach(System.out::println);
    }
}
