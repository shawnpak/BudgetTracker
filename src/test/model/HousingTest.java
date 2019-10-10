package model;

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
        exp = new Housing(expType, expAmnt, paid);
        exp1 = new Housing(expType, expAmnt, false);
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
