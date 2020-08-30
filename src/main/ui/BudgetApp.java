package ui;

import model.Budget;
import model.Category;
import model.Expenses;
import model.exception.InvalidExpenseException;
import model.exception.LargeNumberException;
import model.exception.NegativeInputException;
import network.ReadWebPageEX;
import observer.Subject;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class BudgetApp extends Subject implements Serializable {
    private static Scanner reader;
    public Budget month;
    private String expenseType;
    private double exp;
    private Category category;
    private Expenses expense;
    private ArrayList<Category> categoriesList;
    private double totalExpense;
    private double stats;
    private String[] args;
    private double categoryExpense;
    private String statString;
    private DecimalFormat df;

    // EFFECTS: constructs a budget app which initializes a new Budget with 0 set as its budget
    public BudgetApp() {
        this.reader = new Scanner(System.in);
        this.categoriesList = new ArrayList<>();
        df = new DecimalFormat("#.00");
        try {
            this.month = new Budget(0);
        } catch (NegativeInputException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: initializes the budget app, asking user to load previously saved file or start a new budget
    public void initialize() throws IOException {
        System.out.println("Load/New?");
        String initial = reader.nextLine();
        if (initial.equals("load")) {
            try {
                load();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                System.out.println("All done!");
            }
        } else {
            start();
        }
        save();
    }

    // EFFECTS: starts the budget app, asking for user commands
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
        report();
    }

    // MODIFIES: this
    // EFFECTS: asks user for budget and sets the budget
    public void budget() {
        System.out.println("What's your budget?");
        double budget;
        if (reader.hasNextDouble()) {
            budget = reader.nextDouble();
        } else {
            System.out.println("Your budget must be a valid number!");
            return;
        }
        try {
            month.setBudget(budget);
        } catch (NegativeInputException e) {
            System.out.println("You can't have a negative budget!");
        }
    }

    // MODIFIES: this
    // EFFECTS: asks user for an Expense and Category, adds it to an existing Category or creates a new one
    public void expense() {
        System.out.println("What's your expense type?\n1 - Housing\n2 - Food \n3 - Utilities/Bills");
        String type = reader.nextLine();
        try {
            enterExpense();
        } catch (InvalidExpenseException e) {
            return;
        }
        if (type.equals("1")) {
            type = "Housing";
        } else if (type.equals("2")) {
            type = "Food";
        } else if (type.equals("3")) {
            type = "Utilities";
        }
        if (!checkCategory(type)) {
            createCategory(type);
        }
    }

    // MODIFIES: this
    // EFFECTS: prints a report of the budget, total expenses, percentage of budget spent, and within budget or not
    public void report() {
        for (Category c : categoriesList) {
            System.out.println(c.getCategory() + ":");
            categoryExpense = 0;
            for (Map.Entry<String, Double> entry : c.getExpenses().entrySet()) {
                System.out.println(entry.getKey() + " - $" + entry.getValue());
                categoryExpense += entry.getValue();
            }
            stats = 100 * (categoryExpense / month.budget);
            System.out.println(df.format(stats) + "% of budget");
        }
        System.out.println("Your expenses are: $" + totalExpense);
        stats();
        System.out.println(statString + "% of budget spent this month.");
        System.out.println(month.budgetStatus(totalExpense));
    }

    // EFFECTS: writes this object to a file
    public void save() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./data/budget.bin"));
        oos.writeObject(this);
    }

    // EFFECTS: loads object from a file and starts it
    public void load() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./data/budget.bin"));
        BudgetApp month = (BudgetApp) ois.readObject();
        month.start();
    }

    // REQUIRES: expenses must be a valid number, cannot be negative or too large
    // MODIFIES: this
    // EFFECTS: make a new Expenses with expense name and expense amount
    public void enterExpense() throws InvalidExpenseException {
        System.out.println("Name of expense: ");
        this.expenseType = reader.nextLine();
        System.out.println("Enter your expense amount: ");
        if (reader.hasNextDouble()) {
            exp = reader.nextDouble();
        } else {
            System.out.println("Your expense must be a valid number!");
            throw new InvalidExpenseException();
        }
        try {
            expense = new Expenses(expenseType, exp);
        } catch (NegativeInputException e) {
            System.out.println("Your expenses can't be negative!");
            throw new InvalidExpenseException();
        } catch (LargeNumberException e) {
            System.out.println("There is no way you need to spend that much!");
            throw new InvalidExpenseException();
        }
    }

    // MODIFIES: this
    // EFFECTS: checks if given String s is already in categoriesList, returns true if it is, false if it's not
    //          adds expense to category, if it's not already in the list
    public Boolean checkCategory(String s) {
        for (Category c : categoriesList) {
            if (c.getCategory().equals(s)) {
                if (c.getExpenses().containsKey(expense.getType())) {
                    System.out.println("You've already inputted this expense this month!");
                } else {
                    c.addExpense(expense);
                    totalExpense += expense.getExpense();
                    stats();
                    notifyObservers(expense, stats);
                }
                return true;
            }
        }
        return false;

    }


    public void createCategory(String c) {
        category = new Category(c);
        categoriesList.add(category);
        category.addExpense(expense);
        totalExpense += expense.getExpense();
        stats();
        notifyObservers(expense, stats);
    }

    public void stats() {
        stats = (100 * (totalExpense / month.budget));
        statString = df.format(stats);
    }
}

