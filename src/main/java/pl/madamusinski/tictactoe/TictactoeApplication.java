package pl.madamusinski.tictactoe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import pl.madamusinski.tictactoe.core.TicTacToeGameImpl;

import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
public class TictactoeApplication implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(TictactoeApplication.class);
    private static TicTacToeGameImpl ticTacToeGame;

    public TictactoeApplication(TicTacToeGameImpl ticTacToeGame){
        this.ticTacToeGame = ticTacToeGame;
    }

    public static void main(String[] args){
        SpringApplication app = new SpringApplication(TictactoeApplication.class);
        Environment env = app.run(args).getEnvironment();
        logger.info("Initializing {}", env.getProperty("spring.application.name"));
        logger.info("Debuggin is {}", (env.getProperty("debug").equals("true")) ? "on" : "off");
        ticTacToeGame.play();
    }


    @Override
    public void run(String... args) throws Exception {

    }
}
