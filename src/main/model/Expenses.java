package model;

public class Expenses {
    public String expenseType;
    public int expense;

    // REQUIRES: expenses > 0
    public Expenses(String expenseType, int expenses) {
        this.expenseType = expenseType;
        this.expense = expenses;
    }

    public int getExpense() {
        return expense;
    }
}
