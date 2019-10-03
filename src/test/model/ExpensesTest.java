package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ExpensesTest {

    private Expenses exp;
    private String expType = "Rent";
    private int expAmnt = 500;

    @BeforeEach
    void runBefore() {
        exp = new Expenses(expType, expAmnt);
    }

    @Test
    void getExpense() {
        assertEquals(expAmnt, exp.count());
    }

    @Test
    void getExpenseType() {
        assertEquals(expType, exp.getType());
    }

    @Test
    void getType() {
        assertEquals(exp.getType(), expType);

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
