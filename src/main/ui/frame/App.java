package ui.frame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame {
    private JPanel panel;
    private JButton newB;
    private JButton loadB;
    private JFrame frame;
    private JLabel label;

    public static void main(String[] args) {
        App s = new App();
    }

    public App() {
        frame = new JFrame();
        panel = new JPanel();
        newB = new JButton("New");
        loadB = new JButton("Load");
        label = new JLabel("BudgetApp");

        newB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new NewBudget();
                frame.dispose();
            }
        });
        loadB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NewBudget nb = new NewBudget();
                try {
                    nb.load();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                nb.loadTable();
                frame.dispose();
            }
        });
//

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(new Insets(50, 100, 50, 100)));
        panel.add(label);
        label.setForeground(Color.white);
        label.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(newB);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(loadB);
        panel.setBackground(Color.black);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}

