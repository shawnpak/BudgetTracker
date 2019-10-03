package model;

import java.io.Serializable;

public class Expenses implements Serializable, Money {

    public String expenseType;
    public int expense;
    private static final long serialVersionUID = 403206167512077727L;

    // REQUIRES: expenses > 0
    // EFFECTS: Constructs an expense with type and amount spent
    public Expenses(String expenseType, int expenses) {
        this.expenseType = expenseType;
        this.expense = expenses;
    }

    // EFFECTS: returns type of expense
    public String getType() {
        return expenseType;
    }

    @Override
    public int count() {
        return expense;
    }

    @Override
    public void increase(int e) {
        expense += e;
    }

    @Override
    public void decrease(int e) {
        expense -= e;
    }
}
