package ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static ui.Main.load;
import static ui.Main.save;


class MainTest {
    private BudgetApp b;

    @BeforeEach
    void runBefore() {
        b = new BudgetApp();
    }

    @Test
    void testSave() throws IOException, ClassNotFoundException {
    }
}
