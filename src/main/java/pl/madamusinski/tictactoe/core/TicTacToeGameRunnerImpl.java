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
import pl.madamusinski.tictactoe.service.HumanPlayerSpawner;
import pl.madamusinski.tictactoe.service.PlayerSpawner;

import java.util.*;

/**
 * @author Mateusz Adamusiński
 * Implementation of TicTacToe Interface
 * Class is designed to aggregate all necessary components and run game.
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
    private Queue<Player> playerQueue;
    private HumanPlayer currentPlayer;
    private PlayerSpawner playerSpawner;
    private int movesLeft;


    public TicTacToeGameRunnerImpl(Environment env,
                                   @Qualifier(value = "playerEntryInput") Scanner scanner,
                                   BoardPrinterImpl boardPrinter,
                                   TicTacToeStandardBoardFactory boardFactory,
                                   HumanPlayerSpawner humanPlayerSpawner
                                   ){
        this.env = env;
        this.scanner = scanner;
        this.boardPrinter = boardPrinter;
        this.boardFactory = boardFactory;
        this.playerQueue = new LinkedList();
        this.playerSpawner = humanPlayerSpawner;
    }

    /**
     * inerited method used for running core methods needed to run game
     */
    @Override
    public void play(){
        logger.info("Starting {} game", env.getProperty("spring.application.name"));
        initGame(); // Initialize players, board
        boardPrinter.printBoard(board);
        do{
            System.out.println(currentPlayer.getName() +" turn, type in field to mark: ");
            makeTurn(false);
            boardPrinter.printBoard(board);
        }while(checkWinCondition());

    }

    /**
     * Method checks wether currentPlayer isn't same player as one in front of queue
     * then it adds currentplayer to tail of queue and polls first player as new current player
     */
    private void nextPlayerTurn(){
        if(currentPlayer!=playerQueue.peek()){
            playerQueue.add(currentPlayer);
            currentPlayer = (HumanPlayer)playerQueue.poll();
        }
    }

    /**
     * Method used for creation of neccessary instances like players
     */
    private void initGame(){
        playerSpawner.spawnPlayers(gameConfig.getNumberOfPlayers());
        ((HumanPlayerSpawner) playerSpawner).getPlayers().stream()
                .forEach(p->playerQueue.add(p));
        board = (TicTacToeStandardBoard)boardFactory.createBoard(gameConfig.getBoardWidth(),
                gameConfig.getBoardHeight(), gameConfig.getEmptyFieldSign());
        movesLeft = gameConfig.getBoardHeight() * gameConfig.getBoardWidth();
        currentPlayer = (HumanPlayer)playerQueue.poll();
    }

    /**
     * method that is responsible for checking game victory or end of game conditions
     * @return
     */

    private boolean checkWinCondition(){
        if(filledSignsInaRow())
            return false;
        else
            if(possibleMovesLeft()){
                System.out.println("This game has ended with a tie, nobody wins!");
                return false;
            }
            else{
                nextPlayerTurn();
                return true;
            }

    }

    /**
     * method that checks for victory of player scoring in a row series of his marked fields on board
     * @return true if condition is met otherwise returns false
     */
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
                    System.out.println(currentPlayer.getName() + " wins!");
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
                        System.out.println(currentPlayer.getName() + " wins!");
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
                        System.out.println(currentPlayer.getName() + " wins!");
                        return true;
                    }
                }
            }

        //check diagonal right to left
        playerSign = boardCopy[0][gameConfig.getBoardWidth()-1].getSignDisplay();
        winCount = 0;
        if(playerSign!=gameConfig.getEmptyFieldSign()){
            for(int i = 0; i<gameConfig.getBoardWidth(); i++){
                if(boardCopy[i][(gameConfig.getBoardWidth()-1)-i].getSignDisplay()==playerSign){
                    winCount++;
                }
                if(winCount>=gameConfig.getBoardWidth()){
                    System.out.println(currentPlayer.getName() + " wins!");
                    return true;
                }
            }
        }

            return false;
    }

    /**
     * this method checks if there are any moves left on board
     * @return true if no more moves are left otherwise returns false if game can continue
     */
    private boolean possibleMovesLeft(){
        if(movesLeft==0)
            return true;
        else
            return false;
    }

    /**
     * method responsble for making turn it takes in by default false to run method otherwise ends
     * @param isTurnFinished
     */
    private void makeTurn(boolean isTurnFinished){
        String input;
        while(!isTurnFinished){
            try{
                input = scanner.next("[A-Z]{1}[1-9]{1}");
                if(findField(input)){
                    setMarker(input);
                    isTurnFinished = true;
                }
            }catch(InputMismatchException e){
                System.out.println("Incorrect Field coordinate, coordinates must be uppercase letter followed by number ie: B2 or A1");
                scanner.next();
            }
        }
    }

    /**
     * method responsble for finding an empty field checking before player mark whether field has
     * empty field marker otherwise returns false
     * @param coordinate
     * @returntrue if field is free returns true otherwise returns false
     * if field is already marked by a player
     */
    private boolean findField(String coordinate){
        TicTacToeStandardBoard boardCopy = (TicTacToeStandardBoard)board.getBoard();
        for(int row = 0; row<boardCopy.getBoardField().length; row++){
            for(int column = 0; column<boardCopy.getBoardField()[row].length; column++){
                if(coordinate.equals(boardCopy.getBoardField()[row][column].getCoordinate())){
                    if(boardCopy.getBoardField()[row][column].getSignDisplay()=='.'){
                        return true;
                    }else{
                        System.out.println("This field with coordinates: "
                                + boardCopy.getBoardField()[row][column].getCoordinate()
                                +" is already taken, pick a field that isn't taken!");
                        return false;
                    }
                }
            }
        }
        System.out.println("Such field does not exist, try again!");
        return false;
    }

    /**
     * method responsible for marking cell by currentplayer's mark
     * @param coordinate coordinate of Field
     */
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

    /**
     * method passes args passed when run app into GameConfig object.
     * @param numberOfPlayers
     * @param boardWidth
     * @param boardHeight
     */
    public void setGameConfig(int numberOfPlayers, int boardWidth, int boardHeight) {
        this.gameConfig = new GameConfig(numberOfPlayers, boardWidth, boardHeight);
    }

    /**
     * method returns current game config
     * @return returns instance of GameCofnig instance with settings
     */
    public GameConfig getGameConfig(){
        return gameConfig;
    }
}
