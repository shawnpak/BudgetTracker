package model;

import model.exception.LargeNumberException;
import model.exception.NegativeInputException;

import java.io.Serializable;
import java.util.Objects;

public class Expenses implements Serializable {

    private String expenseType;
    private int expense;
    private Category category;
    private static final long serialVersionUID = 403206167512077727L;

    // REQUIRES: expenses >= 0
    // EFFECTS: Constructs an expense with type and amount spent
    public Expenses(String expenseType, int expenses) throws NegativeInputException, LargeNumberException {
        if (expenses < 0) {
            throw new NegativeInputException();
        } else if (expense > 100000) {
            throw new LargeNumberException();
        }
        this.expenseType = expenseType;
        this.expense = expenses;
    }

    public int getExpense() {
        return expense;
    }

    public void setCategory(Category c) {
        this.category = c;

    }

    public Category getCategory() {
        return category;
    }

    // EFFECTS: returns type of expense
    public String getType() {
        return expenseType;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Expenses expenses = (Expenses) o;
        return this.expense == expenses.expense && expenseType.equals(expenses.expenseType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expenseType, expense);
    }

    @Override
    public String toString() {
        return this.expenseType + ", " + this.expense;
    }
}
