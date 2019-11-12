package ui;

import model.Budget;
import model.Category;
import model.Expenses;
import model.exception.LargeNumberException;
import model.exception.NegativeInputException;
import network.ReadWebPageEX;
import observer.Subject;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class BudgetApp extends Subject implements Serializable {
    private static Scanner reader;
    public Budget month;
    private String expenseType;
    private int exp;
    private Category category;
    private Expenses expense;
    private ArrayList<Category> categoriesList;
    private int totalExpense;
    private double stats;
    private String[] args;
    private int categoryExpense;

    // EFFECTS: Constructs a budget app which initializes a new Budget with 0 set as its budget
    public BudgetApp() {
        this.reader = new Scanner(System.in);
        this.categoriesList = new ArrayList<>();
        try {
            this.month = new Budget(0);
        } catch (NegativeInputException e) {
            e.printStackTrace();
        }

    }

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
    // EFFECTS: asks user for budget and adds the number given to current budget
    public void budget() {
        System.out.println("What's your budget?");
        int budget = reader.nextInt();
        try {
            month.setBudget(budget);
        } catch (NegativeInputException e) {
            System.out.println("You can't have a negative budget!");
        }
    }

    // MODIFIES: this
    // EFFECTS: asks user for an Expense and adds it to Budget
    public void expense() {
        System.out.println("What's your expense type?");
        System.out.println("1 - Housing");
        System.out.println("2 - Food");
        System.out.println("3 - Utilities");
        String type = reader.nextLine();
        enterExpense();
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
        stats = 100 * ((double) totalExpense / (double) month.budget);
        notifyObservers(expense, stats);
    }


    public void report() {
        for (Category c : categoriesList) {
            System.out.println(c.getCategory() + ":");
            categoryExpense = 0;
            for (Map.Entry<String, Integer> entry : c.getExpenses().entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
                categoryExpense += entry.getValue();
            }
            stats = 100 * ((double)categoryExpense / (double) month.budget);
            System.out.println(stats + "% of budget");
        }
        System.out.println("Your expenses are: " + totalExpense);
        System.out.println(month.budgetStatus(totalExpense));
        try {
            ReadWebPageEX.main(args);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void save() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./data/budget.bin"));
        oos.writeObject(this);
    }

    public void load() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./data/budget.bin"));
        BudgetApp month = (BudgetApp) ois.readObject();
        month.start();
    }


    public void enterExpense() {
        System.out.println("Name of expense: ");
        this.expenseType = reader.nextLine();
        System.out.println("Enter your expense amount: ");
        this.exp = reader.nextInt();
        try {
            expense = new Expenses(expenseType, exp);
            totalExpense += expense.getExpense();
        } catch (NegativeInputException e) {
            System.out.println("Your expenses can't be negative!");
        } catch (LargeNumberException e) {
            System.out.println("There is no way you need to spend that much!");
        }
    }

    public Boolean checkCategory(String s) {
        for (Category c : categoriesList) {
            if (c.getCategory().equals(s)) {
                expense.setCategory(c);
                c.addExpense(expense);
                return true;
            }
        }
        return false;

    }

    public void createCategory(String c) {
        category = new Category(c);
        categoriesList.add(category);
        category.addExpense(expense);
    }
}

