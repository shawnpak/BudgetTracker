package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Budget {
    public int budget;
    public int expenses;
    public ArrayList<Expenses> expenseList;

    // REQUIRES: budget >= 0
    // EFFECTS: creates a new Budget with budget amount
    public Budget(int budget) {
        this.budget = budget;
        this.expenses = 0;
        expenseList = new ArrayList<Expenses>();
    }


    // REQUIRES: expense.getExpense() > 0
    // MODIFIES: this
    // EFFECTS: adds expense to expenseList
    public void addExpense(Expenses expense) {
        expenseList.add(expense);
        this.expenses += expense.getExpense();
    }

    public String checkBudget() {
        if (expenses <= budget) {
            return "You are within budget!";
        } else {
            return "You have exceeded your budget!";
        }

    }

    public int checkExpenses() {
        return expenses;
    }

}
