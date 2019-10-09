package model;

import java.io.Serializable;

public class Housing extends Essential implements Serializable {
    public int housing;
    protected boolean paidYet;

    public Housing(String expenseType, int expenses, boolean paidYet) {
        super(expenseType, expenses);
        housing += expenses;
        this.paidYet = paidYet;

    }

    @Override
    boolean paidYet() {
        return paidYet;
    }

    @Override
    public String reminder() {
        if (paidYet) {
            return "You've paid rent for this month already, good job!";
        } else {
            return "Don't forget to pay rent!!";
        }

    }

    @Override
    public int count() {
        return housing;

    }

    @Override
    public void increase(int e) {
        housing += e;
    }

    @Override
    public void decrease(int e) {
        housing -= e;
    }
}