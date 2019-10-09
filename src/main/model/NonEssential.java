package model;

import java.io.Serializable;

public abstract class NonEssential extends Expenses implements Serializable {
    public NonEssential(String expenseType, int expenses) {
        super(expenseType, expenses);
    }
}
