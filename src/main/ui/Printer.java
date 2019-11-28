package ui;

import model.Expenses;
import observer.Observer;

import java.text.DecimalFormat;


public class Printer implements Observer {
    DecimalFormat df = new DecimalFormat("#.00");

    public Printer() {
    }

    @Override
    public void update(Expenses e, double stats) {
        System.out.println("Adding expense: " + e.getType() + " - " + e.getExpense());
        System.out.println("You have used up " + df.format(stats) + "% of your budget this month!");
    }
}
