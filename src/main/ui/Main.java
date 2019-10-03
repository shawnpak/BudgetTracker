package ui;


import java.io.*;
import java.util.Scanner;

import model.Money;
import model.Expenses;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(System.in);
        System.out.println("Load/New?");
        String next = reader.nextLine();
        if (next.equals("load")) {
            try {
                load();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            BudgetApp budget = new BudgetApp();
            budget.start();
            save(budget);
        }
    }

    public static void save(BudgetApp m) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./data/budget.bin"));
        oos.writeObject(m);
    }

    public static void load() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./data/budget.bin"));
        BudgetApp month = (BudgetApp) ois.readObject();
        month.start();
        save(month);
    }
}
