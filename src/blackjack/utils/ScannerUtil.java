package blackjack.utils;

import java.util.Scanner;

public class ScannerUtil {

    private final Scanner sc;

    public ScannerUtil(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Return the next Integer entry in the Scanner
     * @return
     */
    public int nextInt(){
        while (!sc.hasNextInt()){
            System.out.print("Invalid data.. Please input an integer : ");
            sc.next();
        }
        return sc.nextInt();
    }

    /**
     * Return the next String entry in the Scanner
     * @return
     */
    public String nextString(){
        return sc.next();
    }
}
