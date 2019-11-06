package model;

import java.io.Serializable;
import java.util.HashMap;

public class Category implements Serializable {
    private String category;
    private HashMap<String, Integer> expenses;

    public Category(String category) {
        this.category = category;
        this.expenses = new HashMap<>();
    }

    public void addExpense(Expenses e) {
        if (!expenses.containsKey(e.getType())) {
            expenses.put(e.getType(), e.getExpense());
            e.setCategory(this);
        }
    }

    public HashMap<String, Integer> getExpenses() {
        return expenses;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return category;
    }
}
