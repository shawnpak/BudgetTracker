//package model;
//
//import model.Expenses;
//import model.exception.LargeNumberException;
//import model.exception.NegativeInputException;
//
//import java.io.Serializable;
//import java.util.HashMap;
//
//public class Housing implements Serializable {
//    public int housing;
//    private HashMap<String, Integer> expenses;
//
//    public Housing() {
//        expenses = new HashMap<>();
//    }
//
//    public void addExpenses(Expenses e) {
//        if (!expenses.containsKey(e.getType())) {
//            expenses.put(e.getType(), e.getExpense());
//        }
//    }
//
//
//}