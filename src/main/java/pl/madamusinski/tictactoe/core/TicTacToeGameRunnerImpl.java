package pl.madamusinski.tictactoe.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import pl.madamusinski.tictactoe.core.board.factory.BoardFactory;
import pl.madamusinski.tictactoe.core.board.factory.TicTacToeStandardBoardFactory;
import pl.madamusinski.tictactoe.core.config.GameConfig;
import pl.madamusinski.tictactoe.domain.Field;
import pl.madamusinski.tictactoe.domain.board.Board;
import pl.madamusinski.tictactoe.domain.board.TicTacToeStandardBoard;
import pl.madamusinski.tictactoe.domain.player.HumanPlayer;
import pl.madamusinski.tictactoe.domain.player.Player;
import pl.madamusinski.tictactoe.service.BoardPrinterImpl;

import java.util.*;

/**
 * @author Mateusz Adamusiński
 * Implementation of TicTacToe Interface
 */
@Component
public class TicTacToeGameRunnerImpl implements TicTacToeGameRunner {

    private final Logger logger = LoggerFactory.getLogger(TicTacToeGameRunnerImpl.class);
    private final Environment env;
    private final Scanner scanner;
    private final BoardPrinterImpl boardPrinter;
    private final BoardFactory boardFactory;
    private GameConfig gameConfig;
    private Board board;
    private Queue<Player> playerTurns;
    private HumanPlayer currentPlayer;
    private int movesLeft;



    public TicTacToeGameRunnerImpl(Environment env,
                                   @Qualifier(value = "playerEntryInput") Scanner scanner,
                                   BoardPrinterImpl boardPrinter,
                                   TicTacToeStandardBoardFactory boardFactory
                                   ){
        this.env = env;
        this.scanner = scanner;
        this.boardPrinter = boardPrinter;
        this.boardFactory = boardFactory;
        playerTurns = new LinkedList();
    }

    @Override
    public void play() throws Exception {
        logger.info("Starting {} game", env.getProperty("spring.application.name"));
        initGame(); // Initialize players, board
        boardPrinter.printBoard(board);
        currentPlayer = (HumanPlayer)playerTurns.poll();

        while(checkWinCondition()){
            System.out.println(currentPlayer.getName() + "' turn, type in field to mark: ");
            boolean turnFinished = false;
            makeTurn(turnFinished);
            boardPrinter.printBoard(board);
        }

    }

    private boolean checkWinCondition(){
        if(possibleMovesLeft() || filledSignsInaRow())
            return false;
        else
            return true;
    }

    private boolean filledSignsInaRow() {
        Field[][] boardCopy = ((TicTacToeStandardBoard) board).getBoardField();
        char playerSign;
        int row = 0;
        int column = 0;
        int winCount = 0;

        //check rows

        for (row = 0; row < boardCopy.length; row++) {
            playerSign = boardCopy[row][0].getSignDisplay();
            if (playerSign != gameConfig.getEmptyFieldSign()) {
                for (column = 0; column < boardCopy[row].length; column++) {
                    if (boardCopy[row][column].getSignDisplay() == playerSign)
                        winCount++;
                }
                if (winCount >= gameConfig.getBoardWidth()) {
                    System.out.println("Player with sign " + playerSign + " wins!");
                    return true;
                } else {
                    winCount = 0;
                }
            }
        }

        //check columns
        row = 0;
        column = 0;
        for (column = 0; column < boardCopy.length; column++) {
            playerSign = boardCopy[0][column].getSignDisplay();
            if (playerSign != gameConfig.getEmptyFieldSign()) {
                for (row = 0; row < boardCopy[column].length; row++) {
                    if (boardCopy[row][column].getSignDisplay() == playerSign)
                        winCount++;
                    }
                    if (winCount >= gameConfig.getBoardWidth()) {
                        System.out.println("Player with sign " + playerSign + " wins!");
                        return true;
                    } else {
                        winCount = 0;
                    }
                }
            }

            //check diagonal left to right
            playerSign = boardCopy[0][0].getSignDisplay();
            winCount = 0;
            if(playerSign!=gameConfig.getEmptyFieldSign()){
                for(int i = 0; i<gameConfig.getBoardWidth(); i++){
                    if(boardCopy[i][i].getSignDisplay()==playerSign){
                        winCount++;
                    }
                    if(winCount>=gameConfig.getBoardWidth()){
                        System.out.println("Player with sign " + playerSign + " wins!");
                        return true;
                    }
                }
            }

        //check diagonal right to left
        playerSign = boardCopy[0][2].getSignDisplay();
        winCount = 0;
        if(playerSign!=gameConfig.getEmptyFieldSign()){
            for(int i = 0; i<gameConfig.getBoardWidth(); i++){
                if(boardCopy[i][2-i].getSignDisplay()==playerSign){
                    winCount++;
                }
                if(winCount>=gameConfig.getBoardWidth()){
                    System.out.println("Player with sign " + playerSign + " wins!");
                    return true;
                }
            }
        }

            return false;
    }

    private boolean possibleMovesLeft(){
        if(movesLeft==0)
            return true;
        else
            return false;
    }


    private void makeTurn(boolean isFinished){
        String input;
        while(!isFinished){
            try{
                input = scanner.next("[A-Z]{1}[1-9]{1}");
                System.out.println(input);
                if(findField(input)){
                    setMarker(input);
                    isFinished = true;
                    if(currentPlayer!=playerTurns.peek()){
                        playerTurns.add(currentPlayer);
                        currentPlayer = (HumanPlayer)playerTurns.poll();
                    }
                }
            }catch(InputMismatchException e){
                System.out.println("Incorrect Field coordinate, coordinates are made like this: A1");
                scanner.next();
            }
        }
    }

    private boolean findField(String coordinate){
        TicTacToeStandardBoard boardCopy = (TicTacToeStandardBoard)board.getBoard();
        for(int row = 0; row<boardCopy.getBoardField().length; row++){
            for(int column = 0; column<boardCopy.getBoardField()[row].length; column++){
                if(coordinate.equals(boardCopy.getBoardField()[row][column].getCoordinate())){
                    if(boardCopy.getBoardField()[row][column].getSignDisplay()=='.'){
                        return true;
                    }else{
                        System.out.println("To pole o koordynatach:"
                                + boardCopy.getBoardField()[row][column].getCoordinate()
                                +" jest już zajęte! Wybierz inne!");
                        return false;
                    }
                }
            }
        }
        System.out.println("Pole nie znalezione!");
        return false;
    }

    private void setMarker(String coordinate){
        TicTacToeStandardBoard boardCopy = (TicTacToeStandardBoard)board.getBoard();
        for(int row = 0; row<boardCopy.getBoardField().length; row++){
            for(int column = 0; column<boardCopy.getBoardField()[row].length; column++){
                if(coordinate.equals(boardCopy.getBoardField()[row][column].getCoordinate())){
                    boardCopy.getBoardField()[row][column].setSignDisplay(currentPlayer.getPlayerSymbol());
                    movesLeft--;
                }

            }

        }
        board = boardCopy;
    }

    private void initGame(){
        HumanPlayer player1 = new HumanPlayer("Player1", 'X');
        HumanPlayer player2 = new HumanPlayer("Player2", 'O');
        playerTurns.add(player1);
        playerTurns.add(player2);
        board = (TicTacToeStandardBoard)boardFactory.createBoard(gameConfig.getBoardWidth(),
                gameConfig.getBoardHeight(), gameConfig.getEmptyFieldSign());
        movesLeft = gameConfig.getBoardHeight() * gameConfig.getBoardWidth();
    }

    public void setGameConfig(int numberOfPlayers, int boardWidth, int boardHeight) {
        this.gameConfig = new GameConfig(numberOfPlayers, boardWidth, boardHeight);
    }

    public GameConfig getGameConfig(){
        return gameConfig;
    }
}
