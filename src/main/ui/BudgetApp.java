package ui;

import model.Budget;
import model.Category;
import model.Expenses;
import model.exception.LargeNumberException;
import model.exception.NegativeInputException;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class BudgetApp implements Serializable {
    private static Scanner reader;
    public Budget month;
    private int essential;
    private String expenseType;
    private int exp;
    private Category category;
    private Expenses expense;
    private ArrayList<Category> categoriesList;
    private int totalExpense;

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
        }
        if (!checkCategory(type)) {
            createCategory(type);
        }
    }


    public void report() {
        for (Category c : categoriesList) {
            System.out.println(c.getCategory() + ":");
            for (Map.Entry<String, Integer> entry : c.getExpenses().entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
                totalExpense += entry.getValue();
            }
        }
        System.out.println("Your expenses are: " + totalExpense);
        System.out.println(month.budgetStatus(totalExpense));

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


//
//    public void addExpenseList(String s, Integer i) {
//        expenses.put(s, i);
//    }


    public void enterExpense() {
        System.out.println("Name of expense: ");
        this.expenseType = reader.nextLine();
        System.out.println("Enter your expense amount: ");
        this.exp = reader.nextInt();
        try {
            expense = new Expenses(expenseType, exp);
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

