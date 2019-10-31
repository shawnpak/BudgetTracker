package model;

import model.exception.NegativeInputException;

import java.io.Serializable;
import java.util.ArrayList;

public class Budget implements Serializable {
    public int budget;
    public int expenses;

    //
    // REQUIRES: budget >= 0
    // EFFECTS: creates a new Budget with budget amount
    public Budget(int budget) throws NegativeInputException {
        if (budget < 0) {
            throw new NegativeInputException();
        }
        this.budget = budget;
        this.expenses = 0;
    }


    // EFFECTS: returns true if expenses is lower than or equal to the budget,
    //          false otherwise

    public boolean checkBudget() {
        return expenses <= budget;
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


    public void setBudget(int budget) throws NegativeInputException {
        if (budget < 0) {
            throw new NegativeInputException();
        }
        this.budget = budget;
    }
}
