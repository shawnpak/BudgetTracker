package model;

import model.exception.NegativeInputException;

import java.io.Serializable;

public abstract class NonEssential extends Expenses implements Serializable {
    public int nonEssential;

    public NonEssential(String expenseType, int expenses) throws NegativeInputException {
        super(expenseType, expenses);
        nonEssential += expenses;
    }
}
