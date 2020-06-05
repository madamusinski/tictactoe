package pl.madamusinski.tictactoe.core.board.factory;

public interface BoardFactory<B> {
    B createBoard(int boardWidth, int boardHeight, char emptyFieldSign);
}
