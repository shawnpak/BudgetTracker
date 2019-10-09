//package ui;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import java.io.IOException;
//
//import static ui.Main.load;
//import static ui.Main.save;
//
//
//class MainTest {
//    private BudgetApp b;
//    private String expenseType = "test";
//    @BeforeEach
//    void runBefore() {
//        b = new BudgetApp();
//        b.expenseType = this.expenseType;
//    }
//
//    @Test
//    void testSave() throws IOException, ClassNotFoundException {
//        save(b);
//        load();
//        assertTrue(b.expenseType == "expenseType");
//
//    }
//}
