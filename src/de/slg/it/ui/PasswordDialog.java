package de.slg.it.ui;

import javax.swing.*;
import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class PasswordDialog extends JDialog {

    private JPasswordField passwordField;
    private String pass;

    private GUI gui;
    private JOptionPane optionPane;

    PasswordDialog(Frame frame, GUI parent) {
        super(frame, true);

        setSize(400, 150);


        gui = parent;
        pass = "Bitte Passwort eingeben.";

        JButton btnOK = new JButton("OK");
        JButton btnC = new JButton("Cancel");

        passwordField = new JPasswordField();

        btnOK.addActionListener(e -> {

            try {
                MessageDigest digest = MessageDigest.getInstance("MD5");
                String hashed = bytesToHex(digest.digest(String.valueOf(passwordField.getPassword()).getBytes("UTF-8")));

                if (hashed.equals("ab4f63f9ac65152575886860dde480a1")) {
                    gui.newProblem();
                    setVisible(false);
                    frame.dispose();
                    PasswordDialog.this.dispose();
                } else {
                    pass = "Invalid username or password";
                    optionPane.repaint();
                }

            } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                ex.printStackTrace();
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

    private String bytesToHex(byte[] bytes) {

        char[] hexArray = "0123456789abcdef".toCharArray();
        char[] hexChars = new char[bytes.length * 2];

        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }

        return new String(hexChars);

    }

}
