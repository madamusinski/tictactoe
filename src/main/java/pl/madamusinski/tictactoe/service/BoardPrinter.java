package pl.madamusinski.tictactoe.service;

/**
 * @author Mateusz Adamusiński
 * Interface for handling board visualization
 */
public interface BoardPrinter<B> {
    void printBoard(B b);
}
