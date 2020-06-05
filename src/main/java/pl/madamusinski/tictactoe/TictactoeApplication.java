package pl.madamusinski.tictactoe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import pl.madamusinski.tictactoe.core.TicTacToeGameRunnerImpl;

@SpringBootApplication
public class TictactoeApplication implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(TictactoeApplication.class);
    private static TicTacToeGameRunnerImpl ticTacToeGame;



    public TictactoeApplication(TicTacToeGameRunnerImpl ticTacToeGame){
        this.ticTacToeGame = ticTacToeGame;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(TictactoeApplication.class);
        Environment env = app.run(args).getEnvironment();
        logger.info("Initializing {}", env.getProperty("spring.application.name"));
        logger.info("Debuggin is {}", (env.getProperty("debug").equals("true")) ? "on" : "off");
        ticTacToeGame.play();
    }


    /**
     *
     * @param args, arg0 tells game how many players there should be
     *              arg1 tells game what width of board should be
     *              arg2 tells game what height of board should be
     * @throws Exception
     */
    @Override
    public void run(String... args){
        if(args.length!=0){
            ticTacToeGame.setGameConfig(
                    Integer.valueOf(args[0]),
                    Integer.valueOf(args[1]),
                    Integer.valueOf(args[2]));
        }
    }
}
