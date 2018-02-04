package de.slg.it.ui;

import javax.swing.*;
import java.awt.*;

class PasswordDialog extends JDialog {

    private JPasswordField passwordField;
    private JButton btnOK;
    private JButton btnC;
    private String pass;

    private GUI gui;
    private JOptionPane optionPane;

    PasswordDialog(Frame frame, GUI parent) {
        super(frame, true);

        setSize(400, 150);


        gui = parent;
        pass = "Bitte Passwort eingeben.";
        btnOK = new JButton("OK");
        btnC = new JButton("Cancel");
        passwordField = new JPasswordField();

        btnOK.addActionListener(e -> {
            if ("azerty".equals(String.valueOf(passwordField.getPassword()))) {
                gui.newProblem();
                setVisible(false);
                frame.dispose();
            } else {
                pass = "Invalid username or password";
                optionPane.repaint();
            }
        });
        btnC.addActionListener(e -> {
            setVisible(false);
            frame.dispose();
        });

        Object[] array = {pass, passwordField};
        Object[] options = {btnOK, btnC};
        optionPane = new JOptionPane(array, JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION, null, options, options[0]);
        setContentPane(optionPane);
        setVisible(true);
    }


}
