package model;

import java.io.Serializable;

public class PersonalSpending extends NonEssential implements Serializable {

    public int ps;
    private boolean paid;

    public PersonalSpending(String expenseType, int expenses, boolean paid) {
        super(expenseType, expenses);
        ps += expenses;
        this.paid = paid;
    }

    @Override
    boolean paidYet() {
        return paid;
    }

    @Override
    public int count() {
        return ps;
    }

    @Override
    public void increase(int e) {
        ps += e;
    }

    @Override
    public void decrease(int e) {
        ps -= e;
    }
}
