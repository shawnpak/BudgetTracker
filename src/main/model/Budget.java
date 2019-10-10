package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Budget implements Serializable {
    public int budget;
    public int expenses;
    public ArrayList<Expenses> expenseList;

    //
    // REQUIRES: budget >= 0
    // EFFECTS: creates a new Budget with budget amount
    public Budget(int budget) {
        this.budget = budget;
        this.expenses = 0;
        expenseList = new ArrayList<>();
    }


    // EFFECTS: returns true if expenses is lower than or equal to the budget,
    //          false otherwise

    public boolean checkBudget() {
        return expenses <= budget;
    }

    // MODIFIES: this
    // EFFECTS: adds expense to expenseList
    public void addExpense(Expenses expense) {
        this.expenseList.add(expense);
        this.expenses += expense.count();
    }

    // EFFECTS: returns a statement on whether or not budget has been exceeded
    public String budgetStatus() {
        if (checkBudget()) {
            return "You are within budget!";
        } else {
            return "You have exceeded your budget!";
        }

    }

    // EFFECTS: returns amount spent so far

    public int checkExpenses() {
        return expenses;
    }

    // EFFECTS: prints out all the expenses in expenseList
    public void expenseList() {
        for (Expenses e : expenseList) {
            System.out.println(e.getType() + ", " + e.count());
        }
    }


}
