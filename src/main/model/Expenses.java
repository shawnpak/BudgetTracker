package model;

import model.exception.LargeNumberException;
import model.exception.NegativeInputException;

import java.io.Serializable;
import java.util.Objects;

public class Expenses implements Serializable {

    private String expenseType;
    private double expense;
    private Category category;
    private static final long serialVersionUID = 403206167512077727L;

    // REQUIRES: expenses >= 0
    // EFFECTS: Constructs an expense with type and amount spent
    public Expenses(String expenseType, double expenses) throws NegativeInputException, LargeNumberException {
        if (expenses < 0) {
            throw new NegativeInputException();
        } else if (expenses > 100000) {
            throw new LargeNumberException();
        }
        this.expenseType = expenseType;
        this.expense = expenses;
    }

    public double getExpense() {
        return expense;
    }

    public void setCategory(Category c) {
        this.category = c;
    }

    public Category getCategory() {
        return category;
    }

    public String getType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public void setExpense(double expense) throws NegativeInputException, LargeNumberException {
        if (expense < 0) {
            throw new NegativeInputException();
        } else if (expense > 100000) {
            throw new LargeNumberException();
        }
        this.expense = expense;
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
