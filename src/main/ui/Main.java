package ui;


import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        BudgetApp ba = new BudgetApp();
        Printer p = new Printer();
        ba.addObserver(p);
        try {
            ba.initialize();
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

}
