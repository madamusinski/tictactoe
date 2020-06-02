package pl.madamusinski.tictactoe.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author Mateusz Adamusiński
 * Implementation of TicTacToe Interface
 */
@Component
public class TicTacToeGameImpl implements TicTacToeGame {

    private final Logger logger = LoggerFactory.getLogger(TicTacToeGameImpl.class);
    private final Environment env;
    private final Scanner scanner;


    public TicTacToeGameImpl(Environment env, @Qualifier(value = "playerEntryInput") Scanner scanner){
        this.env = env;
        this.scanner = scanner;
    }

    @Override
    public void startGame() {
        logger.info("Starting {} game", env.getProperty("spring.application.name"));
        while(true){
            try{
                System.out.print("Please enter input: ");
                String input = scanner.next();
                System.out.println(input);
            }catch(Exception e){
                logger.error("Exception: {}", e);
                e.printStackTrace();
            }

        }
    }
}
