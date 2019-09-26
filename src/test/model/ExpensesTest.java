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
        assertEquals(expAmnt, exp.getExpense());
    }

    @Test
    void getExpenseType() {
        assertEquals(expType, exp.getExpenseType());
    }


}
