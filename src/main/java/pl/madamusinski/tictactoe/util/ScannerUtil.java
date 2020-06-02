package pl.madamusinski.tictactoe.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ScannerUtil {

    @Bean("playerEntryInput")
    public Scanner getScanner(){
        return new Scanner(System.in);
    }
}
