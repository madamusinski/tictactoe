package pl.madamusinski.tictactoe.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
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
    private Set<Player> players;
    private Board board;

    public TicTacToeGameImpl(Environment env,
                             @Qualifier(value = "playerEntryInput") Scanner scanner,
                             BoardPrinterImpl boardPrinter){
        this.env = env;
        this.scanner = scanner;
        this.boardPrinter = boardPrinter;
    }

    @Override
    public void play() {
        logger.info("Starting {} game", env.getProperty("spring.application.name"));
        System.out.println("rozmiar planszy to AxB: "
                + env.getProperty("spring.application.board.height") + " x "
                + env.getProperty("spring.application.board.width"));
        initGame();
        boardPrinter.printBoard(board);
    }

    private void initGame(){
        players = new HashSet<>();
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
    }
}
