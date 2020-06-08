package pl.madamusinski.tictactoe.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.madamusinski.tictactoe.core.board.factory.TicTacToeStandardBoardFactory;
import pl.madamusinski.tictactoe.domain.board.Board;
import pl.madamusinski.tictactoe.domain.board.TicTacToeStandardBoard;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestBoardPrinterImpl {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    Board testBoard;
    @Autowired
    TicTacToeStandardBoardFactory standardBoardFactory;
    @Autowired
    BoardPrinterImpl boardPrinter;


    @BeforeEach
    public void init(){
        System.setOut(new PrintStream(outContent));
        testBoard = new TicTacToeStandardBoard();
        testBoard = standardBoardFactory.createBoard(3, 3, '.');
    }

    @Test
    public void testBoardPrinting(){
        boardPrinter.printBoard(testBoard);
        assertEquals(". . .\r\n" +
                ". . .\r\n" +
                ". . .\r\n", outContent.toString());
    }

    @AfterEach
    public void breakDown(){
        System.setOut(System.out);
    }
}
