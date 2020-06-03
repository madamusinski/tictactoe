package pl.madamusinski.tictactoe.domain;

/**
 * @author Mateusz Adamusiński
 *
 */
public interface TicTacToeGameControl<P> {
    void makeTurn();
    P currentPlayerTurn() throws Exception;
    void setPlayerWithTurn(P p);
}
