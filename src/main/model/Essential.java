package model;

import java.io.Serializable;

public abstract class Essential extends Expenses implements Serializable {
    public int essential;

    public Essential(String expenseType, int expenses) {
        super(expenseType, expenses);
        essential += expenses;
    }

    abstract String reminder();
}

