package model;

import model.exception.LargeNumberException;
import model.exception.NegativeInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class BudgetTest {
    private int expenses = 500;
    private String expenseType = "rent";
    private ArrayList<Expenses> expenseList;
    private Budget budget;
    private Expenses expense;

    @BeforeEach
    void runBefore() {
        try {
            budget = new Budget(1500);
            expense = new Housing("rent", 500, true);
        } catch (NegativeInputException|LargeNumberException e) {
        }
        budget.addExpense(expense);
    }
    @Test
    void noExceptionConstructor() {
        try {
            Budget noException = new Budget(1500);
            assertEquals(noException.budget, 1500);
        } catch (NegativeInputException e) {
            fail();
        }
    }
    @Test
    void negativeExceptionConstructor() {
        try {
            Budget negative = new Budget(-1500);
            fail();
        } catch (NegativeInputException e) {
        }
    }
    @Test
    void testAddExpense() {
        assertEquals(expenses, expense.count());
        assertEquals(expenseType, expense.getType());
    }

    @Test
    void testCheckBudgetWhenTrue() {
        budget.addExpense(expense);
        assertTrue(budget.checkBudget());
    }
    @Test
    void testCheckBudgetWhenFalse() {
        budget.addExpense(expense);
        budget.addExpense(expense);
        budget.addExpense(expense);
        assertFalse(budget.checkBudget());
    }
    @Test
    void testBudgetStatusWhenWithin() {
        assertEquals("You are within budget!", budget.budgetStatus());
    }
    @Test
    void testBudgetStatusWhenExceeded() {
        budget.addExpense(expense);
        budget.addExpense(expense);
        budget.addExpense(expense);
        assertEquals("You have exceeded your budget!", budget.budgetStatus());
    }
    @Test
    void testCheckExpenses() {
        assertEquals(500, budget.checkExpenses());
    }


}
