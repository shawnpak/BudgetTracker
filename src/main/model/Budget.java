package model;

import model.exception.NegativeInputException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Budget implements Serializable {
    public double budget;

    // REQUIRES: budget >= 0
    public Budget(double budget) throws NegativeInputException {
        if (budget < 0) {
            throw new NegativeInputException();
        }
        this.budget = budget;
    }


    // EFFECTS: returns true if expenses is lower than or equal to the budget,
    //          false otherwise
    public boolean checkBudget(double expense) {
        return expense <= budget;
    }


    // EFFECTS: returns a statement on whether or not budget has been exceeded
    public String budgetStatus(double expense) {
        if (checkBudget(expense)) {
            return "You are within budget!";
        } else {
            return "You have exceeded your budget!";
        }

    }

    // MODIFIES: this
    // EFFECTS: prints out all the expenses in expenseList
    public void setBudget(double budget) throws NegativeInputException {
        if (budget < 0) {
            throw new NegativeInputException();
        }
        this.budget = budget;
    }
}
