package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;


class BudgetTest {
    private int bud = 1500;
    private int expenses = 500;
    private String expenseType = "rent";
    private ArrayList<Expenses> expenseList;
    private Budget budget;
    private Expenses expense;

    @BeforeEach
    void runBefore() {
        budget = new Budget(bud);
        expense = new Housing("rent", 500, true);
        budget.addExpense(expense);
    }
    @Test
    void testAddExpense() {
        assertEquals(expenses, expense.count());
        assertEquals(expenseType, expense.getType());
    }

    @Test
    void testCheckBudgetWhenTrue() {
        expense = new Housing("test", 1000, true);
        budget.addExpense(expense);
        assertTrue(budget.checkBudget());
    }
    @Test
    void testCheckBudgetWhenFalse() {
        expense = new Housing("test", 1001, true);
        budget.addExpense(expense);
        assertFalse(budget.checkBudget());
    }
    @Test
    void testBudgetStatusWhenWithin() {
        assertEquals("You are within budget!", budget.budgetStatus());
    }
    @Test
    void testBudgetStatusWhenExceeded() {
        expense = new Housing("test", 1001, true);
        budget.addExpense(expense);
        assertEquals("You have exceeded your budget!", budget.budgetStatus());
    }
    @Test
    void testCheckExpenses() {
        assertEquals(500, budget.checkExpenses());
    }

}
