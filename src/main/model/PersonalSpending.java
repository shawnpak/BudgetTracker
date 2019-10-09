package model;

import java.io.Serializable;

public class PersonalSpending extends NonEssential implements Serializable {

    public PersonalSpending(String expenseType, int expenses) {
        super(expenseType, expenses);
    }

    @Override
    boolean paidYet() {
        return false;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public void increase(int e) {

    }

    @Override
    public void decrease(int e) {

    }
}
