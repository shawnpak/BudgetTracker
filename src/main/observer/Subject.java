package observer;


import model.Expenses;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    public List<Observer> observers;

    public Subject() {
        observers = new ArrayList<>();
    }

    public void addObserver(Observer o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }


    public void notifyObservers(Expenses e, double stats) {
        for (Observer o : observers) {
            o.update(e, stats);
        }
    }
}
