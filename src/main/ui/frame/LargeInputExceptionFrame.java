package ui.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LargeInputExceptionFrame extends JFrame  {
    private JLabel error;
    private JButton ok;

    public LargeInputExceptionFrame() {
        error = new JLabel("There is no way your expense is that high!");
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

