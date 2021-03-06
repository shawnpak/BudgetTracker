package model;

import model.exception.LargeNumberException;
import model.exception.NegativeInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    private Category c;
    private Expenses e;
    @BeforeEach
    void runBefore() {
        c = new Category("Housing");
        try {
            e = new Expenses("rent", 1500);
        } catch (NegativeInputException ex) {
            ex.printStackTrace();
        } catch (LargeNumberException ex) {
            ex.printStackTrace();
        }
    }
    @Test
    void testAddExpense() {
        c.addExpense(e);
        assertTrue(c.getExpenses().containsKey(e.getType()));
        assertTrue(c.getExpenses().containsValue(e.getExpense()));
        c.addExpense(e);
        assertEquals(c.getExpenses().size(), 1);
    }
    @Test
    void testAddDuplicateExpense() {
        try {
            Expenses e1 = new Expenses("rent", 1501);
            c.addExpense(e1);
            assertEquals(c.getExpenses().get("rent"), 1501);
        } catch (NegativeInputException ex) {
            ex.printStackTrace();
        } catch (LargeNumberException ex) {
            ex.printStackTrace();
        }
    }
    @Test
    void testGettersSetters() {
        c.addExpense(e);
        assertEquals("Housing", c.getCategory());
        assertTrue(c.getExpenses().containsKey("rent"));
    }
    @Test
    void testToString() {
        assertEquals("Housing", c.toString());
    }

}
