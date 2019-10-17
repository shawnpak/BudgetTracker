package model;

import model.exception.LargeNumberException;
import model.exception.NegativeInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class HousingTest {

    private Housing exp;
    private Housing exp1;
    private String expType = "Rent";
    private int expAmnt = 500;
    private boolean paid = true;

    @BeforeEach
    void runBefore() {
        try {
            exp = new Housing(expType, expAmnt, paid);
            exp1 = new Housing(expType, expAmnt, false);
        } catch (NegativeInputException| LargeNumberException e) {

        }
    }

    @Test
    void setNegativeExpense() {
        try {
            exp.setExpense(-5);
            fail();
        } catch (NegativeInputException e) {
        } catch (LargeNumberException e) {
            fail();
        }

    }
    @Test
    void setTooLargeExpense() {
        try {
            exp.setExpense(100001);
            fail();
        } catch (NegativeInputException e) {
            fail();
        } catch (LargeNumberException e) {
        }
    }
    @Test
    void setNoException() {
        try {
            exp.setExpense(1);
        } catch (NegativeInputException|LargeNumberException e) {
            fail();
        }
    }
    @Test
    void paid() {
        assertTrue(exp.paidYet());
    }

    @Test
    void notPaid() {
        assertFalse(exp1.paidYet);
    }
    @Test
    void reminderWhenPaid() {
        assertEquals("You've paid rent for this month already, good job!", exp.reminder());
    }

    @Test
    void reminderWhenNotPaid() {
        assertEquals("Don't forget to pay rent!!", exp1.reminder());

    }

    @Test
    void increase() {
        exp.increase(5);
        assertEquals(exp.count(), expAmnt + 5);
    }

    @Test
    void decrease() {
        exp.decrease(5);
        assertEquals(exp.count(), expAmnt - 5);
    }

}
