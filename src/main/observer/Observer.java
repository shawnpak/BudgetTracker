package observer;

import model.Expenses;

public interface Observer {
    void update(Expenses e, double stats);
}
