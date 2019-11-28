package ui.frame;

import model.exception.BudgetNotSetException;
import model.exception.InvalidExpenseException;
import model.exception.LargeNumberException;
import model.exception.NegativeInputException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;

public class NewBudget extends JFrame {
    private JFrame frame;
    private JPanel panelCont;
    private JPanel panel;
    private JPanel panelB;
    private JPanel panelE;
    private JButton budB;
    private JButton expB;

    private JLabel bud;
    private JButton setB;
    private JButton backB;
    private JTextField budText;
    private int budget;
    private JLabel finalBudget;

    private JLabel catE;
    private JRadioButton r1;
    private JRadioButton r2;
    private JRadioButton r3;
    private JRadioButton r4;
    private JLabel nameL;
    private JTextField nameF;
    private JLabel amountL;
    private JTextField amountF;
    private JButton enterE;
    private JTable expenses;
    private JButton backE;
    private DefaultTableModel model;
    private JLabel statusE;
    private String status;
    private int totalExpense;
    private String category;
    private JButton save;
    private JFileChooser myJFileChooser;
    private JButton quit;

    public NewBudget() {
        String[] columnNames = {"Category", "Name", "Amount"};
        Object[][] data = {};
        myJFileChooser = new JFileChooser(new File("./data/table.bin"));
        frame = new JFrame();
        panel = new JPanel();
        panelCont = new JPanel();
        panelB = new JPanel();
        panelE = new JPanel();
        budB = new JButton("Budget");
        expB = new JButton("Expense");
        save = new JButton("Save");
        quit = new JButton("Quit");
        CardLayout c1 = new CardLayout();

        bud = new JLabel("What is your budget?");
        setB = new JButton("Set");
        backB = new JButton("Back");
        budText = new JTextField(15);
        finalBudget = new JLabel("Your budget is: " + budget);

        catE = new JLabel("Choose a category: ");
        ButtonGroup bg = new ButtonGroup();
        r1 = new JRadioButton("Housing");
        r2 = new JRadioButton("Food");
        r3 = new JRadioButton("Utilities");
        r4 = new JRadioButton("Personal");
        bg.add(r1);
        bg.add(r2);
        bg.add(r3);
        bg.add(r4);
        nameL = new JLabel("Enter expense name: ");
        nameF = new JTextField(15);
        amountL = new JLabel("Enter expense amount: ");
        amountF = new JTextField(15);
        enterE = new JButton("Enter");
        expenses = new JTable();
        expenses.setPreferredScrollableViewportSize(new Dimension(500, 50));
        expenses.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(expenses);
        backE = new JButton("Back");
        model = new DefaultTableModel(data, columnNames);
        expenses.setModel(model);
        status = "Within budget!";
        statusE = new JLabel("Status: " + status + "\n Total: $" + totalExpense);

        panelCont.setLayout(c1);
        panel.add(budB);
        panel.add(expB);
        panel.add(save);
        panel.add(quit);
        panel.setBackground(Color.DARK_GRAY);

        panelB.add(bud);
        panelB.add(budText);
        panelB.add(setB);
        panelB.add(backB);
        panelB.add(finalBudget);

        panelE.add(catE);
        panelE.add(r1);
        panelE.add(r2);
        panelE.add(r3);
        panelE.add(r4);
        panelE.add(nameL);
        panelE.add(nameF);
        panelE.add(amountL);
        panelE.add(amountF);
        panelE.add(enterE);
        panelE.add(scrollPane);
        panelE.add(statusE);
        panelE.add(backE);

        panelCont.add(panel, "1");
        panelCont.add(panelB, "2");
        panelCont.add(panelE, "3");
        c1.show(panelCont, "1");


        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        budB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c1.show(panelCont, "2");
            }
        });

        setB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    setBudget();
                } catch (NegativeInputException ex) {
                    new NegativeExceptionFrame();
                } catch (NumberFormatException ex) {
                    new InvalidInputExceptionFrame();
                }
            }
        });
        backB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c1.show(panelCont, "1");
            }
        });

        expB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c1.show(panelCont, "3");
            }
        });

        enterE.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (Enumeration<AbstractButton> buttons = bg.getElements(); buttons.hasMoreElements(); ) {
                    AbstractButton button = buttons.nextElement();
                    if (button.isSelected()) {
                        category = button.getText();
                    }
                }
                try {
                    checkInput();
                } catch (BudgetNotSetException ex) {
                    new BudgetNotSetExceptionFrame();
                } catch (NegativeInputException ex) {
                    new NegativeExceptionFrame();
                } catch (LargeNumberException ex) {
                    new LargeInputExceptionFrame();
                } catch (InvalidExpenseException | NumberFormatException ex) {
                    new InvalidInputExceptionFrame();
                }
                checkStatus();
            }
        });
        backE.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c1.show(panelCont, "1");
            }
        });
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    save();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                saveTable();
            }
        });


        frame.add(panelCont);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }


    public void setBudget() throws NegativeInputException {
        budget = Integer.parseInt(budText.getText());
        if (budget < 0) {
            throw new NegativeInputException();
        } else {
            finalBudget.setText("Your budget is: " + budget);
        }
    }

    public void checkStatus() {
        if (totalExpense > budget) {
            statusE.setText("Status: Budget exceeded!\n Total: $" + totalExpense);
        } else {
            statusE.setText("Status: Within budget!\n Total: $" + totalExpense);
        }
    }

    public void checkInput() throws BudgetNotSetException, NegativeInputException,
            LargeNumberException, InvalidExpenseException, NumberFormatException {
        int amo = Integer.parseInt(amountF.getText());
        if (budget == 0) {
            throw new BudgetNotSetException();
        } else if (!nameF.getText().matches("[0-9]*[a-zA-Z]+[0-9]*")) {
            throw new InvalidExpenseException();
        } else if (amo < 0) {
            throw new NegativeInputException();
        } else if (amo > 100000) {
            throw new LargeNumberException();
        }
        //
        model.insertRow(model.getRowCount(), new Object[]{category, nameF.getText(), amountF.getText()});
        totalExpense += Integer.parseInt(amountF.getText());

    }

    public void loadTable() {
        if (myJFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            loadTable(myJFileChooser.getSelectedFile());
        }
    }

    public void loadTable(File file) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            Vector rowData = (Vector) ois.readObject();
            Iterator itr = rowData.iterator();
            while (itr.hasNext()) {
                model.addRow((Vector) itr.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveTable() {
        if (myJFileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            saveTable(myJFileChooser.getSelectedFile());
        }
    }

    public void saveTable(File file) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(model.getDataVector());
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() throws Exception {
        Properties p = new Properties();
        OutputStream os = new FileOutputStream("./data/budex.bin");
        p.setProperty("budget", Integer.toString(budget));
        p.setProperty("totalexpense", Integer.toString(totalExpense));
        p.store(os, null);
    }

    public void load() throws Exception {
        Properties p = new Properties();
        InputStream is = new FileInputStream("./data/budex.bin");
        p.load(is);
        budget = (Integer.parseInt(p.getProperty("budget")));
        totalExpense = (Integer.parseInt(p.getProperty("totalexpense")));
        checkStatus();
        finalBudget.setText("Your budget is: " + budget);
    }

}



