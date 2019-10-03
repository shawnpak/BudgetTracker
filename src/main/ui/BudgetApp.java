package ui;

import model.Expenses;
import model.Budget;
import model.Housing;
import model.Essential;

import java.io.*;
import java.util.Scanner;

public class BudgetApp implements Serializable {
    private static Scanner reader;
    public Budget month;
    private String expenseType;
    private int essential;
    private int nonEssential;

    // EFFECTS: Constructs a budget app which initializes a new Budget with 0 set as its budget
    public BudgetApp() {
        this.reader = new Scanner(System.in);
        this.month = new Budget(0);

    }

    public void start() {
        while (true) {
            System.out.println("Enter a command:");
            reader = new Scanner(System.in);
            String command = reader.nextLine();
            if (command.equals("budget")) {
                budget();
            } else if (command.equals("expense")) {
                if (month.budget == 0) {
                    System.out.println("You have not entered a budget for this month yet!");
                } else {
                    expense();
                }
            } else if (command.equals("quit")) {
                break;
            }
        }
        System.out.println(month.budgetStatus());
        System.out.println("Your expenses are: " + month.checkExpenses());
    }

    // MODIFIES: this
    // EFFECTS: asks user for budget and adds the number given to current budget
    public void budget() {
        System.out.println("What's your budget?");
        int budget = reader.nextInt();
        month.budget += budget;
    }

    // MODIFIES: this
    // EFFECTS: asks user for an Expense and adds it to Budget
    public void expense() {
        System.out.println("Essential or non-essential?");
        String ess = reader.nextLine();
        if (ess.equals("essential")) {
            essential();
        }
    }

    public void essential() {
        System.out.println("What's your expense type?");
        String type = reader.nextLine();
        this.expenseType = type;
        if (type.equals("rent")) {
            housing();
        }
    }

    public void housing() {
        System.out.println("Enter your expense amount: ");
        int exp = reader.nextInt();
        Expenses housing = new Housing(expenseType, exp);
        month.addExpense(housing);
    }

}

