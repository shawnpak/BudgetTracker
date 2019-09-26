package model;

public class Expenses {
    public String expenseType;
    public int expense;

    // REQUIRES: expenses > 0
    // EFFECTS: Constructs an expense with type and amount spent
    public Expenses(String expenseType, int expenses) {
        this.expenseType = expenseType;
        this.expense = expenses;
    }

    // EFFECTS: returns expense amount
    public int getExpense() {
        return expense;
    }

    // EFFECTS: returns type of expense
    public String getExpenseType() {
        return expenseType;
    }
}
