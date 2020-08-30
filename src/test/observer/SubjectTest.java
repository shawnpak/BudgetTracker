package observer;

import model.Expenses;
import model.exception.LargeNumberException;
import model.exception.NegativeInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.BudgetApp;
import ui.Printer;

import static org.junit.jupiter.api.Assertions.*;

class SubjectTest {
    private Subject s;
    private Printer p;
    private Expenses e;
    @BeforeEach
    void runBefore() {
        s = new BudgetApp();
        p = new Printer();
        s.addObserver(p);
        try {
            e = new Expenses("1", 1);
        } catch (NegativeInputException ex) {
            ex.printStackTrace();
        } catch (LargeNumberException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void testAddObserver() {
        assertTrue(s.observers.contains(p));
        s.addObserver(p);
        assertEquals(s.observers.size(), 1);
    }
//    @Test
//    void testNotifyObservers() {
//        assertFalse(p.getUpdated());
//        s.notifyObservers(e,1.0);
//        assertTrue(p.getUpdated());
//    }

}