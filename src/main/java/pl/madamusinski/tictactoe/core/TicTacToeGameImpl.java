package pl.madamusinski.tictactoe.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import pl.madamusinski.tictactoe.domain.TicTacToeGameControl;
import pl.madamusinski.tictactoe.domain.board.Board;
import pl.madamusinski.tictactoe.domain.player.HumanPlayer;
import pl.madamusinski.tictactoe.domain.player.Player;
import pl.madamusinski.tictactoe.service.BoardPrinterImpl;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author Mateusz Adamusiński
 * Implementation of TicTacToe Interface
 */
@Component
public class TicTacToeGameImpl implements TicTacToeGame {

    private final Logger logger = LoggerFactory.getLogger(TicTacToeGameImpl.class);
    private final Environment env;
    private final Scanner scanner;
    private final BoardPrinterImpl boardPrinter;
    private final TicTacToeGameControl<Player> ticTacToeGameControl;
    private List<Player> players;
    private Board board;

    public TicTacToeGameImpl(Environment env,
                             @Qualifier(value = "playerEntryInput") Scanner scanner,
                             BoardPrinterImpl boardPrinter,
                             TicTacToeGameControl<Player> ticTacToeGameControl){
        this.env = env;
        this.scanner = scanner;
        this.boardPrinter = boardPrinter;
        this.ticTacToeGameControl = ticTacToeGameControl;
    }

    @Override
    public void play() throws Exception {
        logger.info("Starting {} game", env.getProperty("spring.application.name"));
        System.out.println("rozmiar planszy to AxB: "
                + env.getProperty("spring.application.board.height") + " x "
                + env.getProperty("spring.application.board.width"));
        initGame(); // Initialize players, board
        boardPrinter.printBoard(board);
        Player currentPlayer = ticTacToeGameControl.currentPlayerTurn();
        System.out.println("Current player: " + ((HumanPlayer) currentPlayer).getName());

    }

    private void initGame(){
        players = new ArrayList<>();
        players.add(new HumanPlayer("Player1", 'X'));
        players.add(new HumanPlayer("Player2", 'O'));
        Map<String, Map<String, Character>> presetBoard = new HashMap<String, Map<String, Character>>(){{
           put("A", new HashMap<String, Character>(){{
               put("1", '.');
               put("2", '.');
               put("3", '.');
           }});
            put("B", new HashMap<String, Character>(){{
                put("1", '.');
                put("2", '.');
                put("3", '.');
            }});
            put("C", new HashMap<String, Character>(){{
                put("1", '.');
                put("2", '.');
                put("3", '.');
            }});
        }};
        board = new Board(presetBoard);
        setStartingPlayer(players.get(0));
        ticTacToeGameControl.setPlayerWithTurn(players.get(0));
    }

    private void setStartingPlayer(Player player){
        player.setMyTurn(true);
    }

}
