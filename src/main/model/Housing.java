package model;

import java.io.Serializable;

public class Housing extends Essential implements Serializable {
    public int housing;

    public Housing(String expenseType, int expenses) {
        super(expenseType, expenses);
        housing += expenses;

    }

}