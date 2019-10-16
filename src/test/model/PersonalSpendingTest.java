package model;
import model.exception.NegativeInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonalSpendingTest {
    PersonalSpending ps;
    PersonalSpending ps1;
    @BeforeEach
    void runBefore() {
        try {
            ps = new PersonalSpending("movie", 15, true);
            ps1 = new PersonalSpending("ice cream", 5, false);
        } catch (NegativeInputException e) {

        }
    }

    @Test
    void testPaid() {
        assertTrue(ps.paidYet());
    }
    @Test
    void testNotPaid() {
        assertFalse(ps1.paidYet());
    }
    @Test
    void testIncrease() {
        ps.increase(5);
        assertEquals(20, ps.count());
    }

    @Test
    void testDecrease() {
        ps1.decrease(1);
        assertEquals(4, ps1.count());
    }

}
