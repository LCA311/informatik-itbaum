package de.slg.it;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class NewEntryDialog extends JDialog implements ActionListener {

    private JTextField question;
    private JTextField question_desc;
    private JTextField answerNo;
    private JTextField no_desc;
    private JTextField answerYes;
    private JTextField yes_desc;

    private GUI_project gui;
    private Frame fr;
    private JOptionPane optionPane;

    private JButton btnOK;
    private JButton btnCN;
    private JButton addImage;

    private String textQuest = "Neue Frage:";
    private String desc = "Beschreibung: ";
    private String textAnsNo = "Die Antwort für \"Nein\" : ";
    private String textAnsYes = "Die Antwort für \"Ja\" : ";
    private String localPath;
    private String fileName;

    private final JFileChooser fc;

    public NewEntryDialog(Frame aFrame, GUI_project parent) {
        super(aFrame, true);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        setSize(400, 600);

        gui = parent;
        fr = aFrame;

        fc = new JFileChooser();
        setTitle("Neue Frage erstellen");

        question = new JTextField();
        question_desc = new JTextField();
        answerNo = new JTextField();
        no_desc = new JTextField();
        answerYes = new JTextField();
        yes_desc = new JTextField();

        btnOK = new JButton("OK");
        btnOK.addActionListener(this);


        btnCN = new JButton("Cancel");
        btnCN.addActionListener(this);

        addImage = new JButton("Bild hinzufügen");
        addImage.addActionListener(this);

        Object[] array = {textQuest, question, desc, question_desc, textAnsNo, answerNo, desc, no_desc, textAnsYes, answerYes, desc, yes_desc, addImage};

        Object[] options = {btnOK, btnCN};

        optionPane = new JOptionPane(array, JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION, null, options, options[0]);

        setContentPane(optionPane);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(addImage)) {
            FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
            fc.setFileFilter(imageFilter);
            int val = fc.showOpenDialog(this);
            if (val == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                localPath = file.getAbsolutePath();
                fileName = file.getName();
                gui.addTree(question.getText(), question_desc.getText(), answerNo.getText(), no_desc.getText(), answerYes.getText(), yes_desc.getText(), localPath, fileName);
            }
        }
        if (e.getSource().equals(btnOK)) {
            System.out.println("Clicked OK.");
            if (localPath != null && fileName != null) {
            } else {
                gui.addTree(question.getText(), question_desc.getText(), answerNo.getText(), no_desc.getText(), answerYes.getText(), yes_desc.getText(), null, null);
            }
            clear();
        } else {
            clear();
        }

    }

    private void clear() {
        question.setText("");
        answerYes.setText("");
        answerNo.setText("");
        setVisible(false);
        fr.dispose();
    }
}
