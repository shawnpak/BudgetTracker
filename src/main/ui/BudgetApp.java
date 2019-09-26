package ui;

import model.Expenses;
import model.Budget;

import java.util.Scanner;

public class BudgetApp {
    private Scanner reader;
    private Budget month;

    // EFFECTS: Constructs a budget app which initializes a new Budget with 0 set as its budget
    public BudgetApp() {
        this.reader = new Scanner(System.in);
        this.month = new Budget(0);

    }

    public void start() {
        while (true) {
            System.out.println("Enter a command:");
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
        month.expenseList();
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
        System.out.println("Enter your expense type:");
        String type = reader.nextLine();
        System.out.println("Enter your expense amount:");
        int exp = reader.nextInt();
        Expenses exp1 = new Expenses(type, exp);
        month.addExpense(exp1);
    }
}

