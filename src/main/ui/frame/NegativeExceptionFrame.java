package ui.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NegativeExceptionFrame extends JFrame {
    private JLabel error;
    private JButton ok;

    public NegativeExceptionFrame() {
        error = new JLabel("You can't input a negative number!");
        ok = new JButton("Ok");
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(error);
        add(ok);
        setLayout(new FlowLayout());
        setVisible(true);
        setSize(200, 200);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
