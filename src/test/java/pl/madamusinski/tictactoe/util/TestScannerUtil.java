package pl.madamusinski.tictactoe.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Scanner;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestScannerUtil {

    @Autowired
    private ScannerUtil scannerUtil;

    @Test
    public void testScannerInstanceReturned(){
        assertEquals(scannerUtil.getScanner().getClass(), Scanner.class);
    }
}
