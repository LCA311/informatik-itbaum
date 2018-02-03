package de.slg.it;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordDialog extends JDialog {

    private JPasswordField passwordField;
    private JButton btnOK;
    private JButton btnC;
    private String pass;

    private GUI_project gui;
    private JOptionPane optionPane;

    public PasswordDialog(Frame frame, GUI_project parent) {
        super(frame, true);

        setSize(400, 150);


        gui = parent;
        pass = "Bitte Passwort eingeben.";
        btnOK = new JButton("OK");
        btnC = new JButton("Cancel");
        passwordField = new JPasswordField();

        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("azerty".equals(String.valueOf(passwordField.getPassword()))) {
                    gui.newProblem();
                    setVisible(false);
                    frame.dispose();
                } else {
                    pass = "Invalid username or password";
                    optionPane.repaint();
                }
            }
        });
        btnC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                frame.dispose();
            }
        });

        Object[] array = {pass, passwordField};
        Object[] options = {btnOK, btnC};
        optionPane = new JOptionPane(array, JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION, null, options, options[0]);
        setContentPane(optionPane);
        setVisible(true);
    }


}
