package model;

import model.exception.LargeNumberException;
import model.exception.NegativeInputException;

import java.io.Serializable;

public abstract class Essential extends Expenses implements Serializable {
    public int essential;

    public Essential(String expenseType, int expenses) throws NegativeInputException, LargeNumberException {
        super(expenseType, expenses);
        essential += expenses;
    }

    abstract String reminder();
}

