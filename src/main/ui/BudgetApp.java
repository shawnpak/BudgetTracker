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
    public String expenseType;
    private int essential;
    private int nonEssential;

    // EFFECTS: Constructs a budget app which initializes a new Budget with 0 set as its budget
    public BudgetApp() {
        this.reader = new Scanner(System.in);
        this.month = new Budget(0);

    }

    public void initialize() throws IOException {
        System.out.println("Load/New?");
        String initial = reader.nextLine();
        if (initial.equals("load")) {
            try {
                load();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        start();
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
        month.budget += budget;
    }

    // MODIFIES: this
    // EFFECTS: asks user for an Expense and adds it to Budget
    public void expense() {
        System.out.println("e - Essential");
        System.out.println("ne - Non-Essential");
        String ess = reader.nextLine();
        if (ess.equals("e")) {
            essential();
        }
    }

    public void essential() {
        System.out.println("What's your expense type?");
        System.out.println("1 - Housing");
        System.out.println("2 - Food");
        System.out.println("3 - Utilities");
        String type = reader.nextLine();
        if (type.equals("1")) {
            housing();
        }
    }

    public void housing() {
        System.out.println("Name of expense: ");
        this.expenseType = reader.nextLine();
        System.out.println("Enter your expense amount: ");
        int exp = reader.nextInt();
        addEssential(exp);
        boolean paidYet;

        reader = new Scanner(System.in);
        System.out.println("Paid yet? Y/N");
        String paid = reader.nextLine();
        paidYet = paid.equals("Y");
        Housing housing = new Housing(expenseType, exp, paidYet);
        housing.reminder();
        month.addExpense(housing);
    }

    public void report() {
        System.out.println(month.budgetStatus());
        System.out.println("Your expenses are: " + month.checkExpenses());
        month.expenseList();
        System.out.println("Essentials: " + essential);

    }

    public void save() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./data/budget.bin"));
        oos.writeObject(this);
    }

    public void load() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./data/budget.bin"));
        BudgetApp month = (BudgetApp) ois.readObject();
        month.start();
        save();

    }


    public void addEssential(int e) {
        essential += e;
    }


}

