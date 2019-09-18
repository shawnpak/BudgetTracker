package ui;

import model.Budget;
import model.Expenses;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Budget september = new Budget(1500);
        while (september.expenses <= september.budget) {
            Scanner reader = new Scanner(System.in);
            System.out.println("Enter your expense type:");
            String type = reader.nextLine();
            System.out.println("Enter your expense amount:");
            int exp = reader.nextInt();
            Expenses exp1 = new Expenses(type, exp);
            september.addExpense(exp1);
        }

        System.out.println(september.checkBudget());
        System.out.println(september.checkExpenses());

    }
}
