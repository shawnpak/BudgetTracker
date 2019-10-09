package ui;


import java.io.*;
import java.util.Scanner;

import model.Money;
import model.Expenses;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(System.in);
        BudgetApp ba = new BudgetApp();
        ba.initialize();
    }

}
