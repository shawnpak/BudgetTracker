package model;

import model.exception.LargeNumberException;
import model.exception.NegativeInputException;

import java.io.Serializable;

public abstract class Expenses implements Serializable, Money {

    public String expenseType;
    public int expense;
    private static final long serialVersionUID = 403206167512077727L;

    // REQUIRES: expenses >= 0
    // EFFECTS: Constructs an expense with type and amount spent
    public Expenses(String expenseType, int expenses) throws NegativeInputException {
        if (expenses < 0) {
            throw new NegativeInputException();
        }
        this.expenseType = expenseType;
        this.expense = expenses;
    }

    // EFFECTS: returns type of expense
    public String getType() {
        return expenseType;
    }

    abstract boolean paidYet();

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public void setExpense(int expense) throws NegativeInputException, LargeNumberException {
        if (expense < 0) {
            throw new NegativeInputException();
        } else if (expense > 100000) {
            throw new LargeNumberException();
        }
        this.expense = expense;
    }
}
