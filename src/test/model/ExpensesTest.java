package model;

import model.exception.LargeNumberException;
import model.exception.NegativeInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpensesTest {
    private Expenses expenses;
    private Category category;

    @BeforeEach
    void runBefore() {
        try {
            expenses = new Expenses("rent", 1500);
        } catch (NegativeInputException e) {
            e.printStackTrace();
        } catch (LargeNumberException e) {
            e.printStackTrace();
        }
        category = new Category("Housing");
        expenses.setCategory(category);
    }

    @Test
    void testSettersGetters() {
        assertEquals(1500, expenses.getExpense());
        assertEquals(category, expenses.getCategory());
        expenses.setExpenseType("food");
        assertEquals("food", expenses.getType());
    }

    @Test
    void testLargeNumberException() {
        try {
            expenses.setExpense(1000001);
            fail();
        } catch (NegativeInputException e) {
            fail();
        } catch (LargeNumberException e) {
        }
    }
    @Test
    void testNegativeNumberException() {
        try {
            expenses.setExpense(-11);
            fail();
        } catch (NegativeInputException e) {
        } catch (LargeNumberException e) {
            fail();
        }

    }
}




