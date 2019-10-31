package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Category category1 = (Category) o;
        return Objects.equals(category, category1.category) && Objects.equals(expenses, category1.expenses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, expenses);
    }

    @Override
    public String toString() {
        return category;
    }
}
