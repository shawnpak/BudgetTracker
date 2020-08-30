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
            expense = new Expenses("rent", 500);
        } catch (NegativeInputException | LargeNumberException e) {
        }
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
        assertEquals(expenses, 500);
        assertEquals(expenseType, "rent");
    }

//    @Test
//    void testCheckBudgetWhenTrue() {
//        budget.expenses = 500;
//        assertTrue(budget.checkBudget());
//    }
//
//    @Test
//    void testCheckBudgetWhenFalse() {
//        budget.expenses = 1501;
//        assertFalse(budget.checkBudget());
//    }

    @Test
    void testBudgetStatusWhenWithin() {
        assertEquals("You are within budget!", budget.budgetStatus(1500));
    }

    @Test
    void testBudgetStatusWhenExceeded() {
        assertEquals("You have exceeded your budget!", budget.budgetStatus(1501));
    }


    @Test
    void testSetBudget() {
        try {
            budget.setBudget(50);
            assertEquals(budget.budget, 50);
        } catch (NegativeInputException e) {
            fail();
        }
    }

    @Test
    void testSetBudgetNegativeException() {
        try {
            budget.setBudget(-50);
            fail();
        } catch (NegativeInputException e) {
        }
    }


}
