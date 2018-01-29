package de.slg.it;

import de.slg.it.utility.Subject;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.Buffer;

import javax.swing.*;


class GUI_project extends JFrame {

    private JButton button1; //JA NEIN
    private JButton button2;

    private JButton button3; //NETZWERK, COMPUTER, BEAMER
    private JButton button4;
    private JButton button5;

    private JButton buttonRefresh;
    private JButton buttonAdd;

    private JLabel label1;
    private JLabel label2;
    private JLabel label3;

    private JLabel picLabel;


    private Session session = null;

    private Main curReference;


    GUI_project(Main reference) {
        curReference = reference;

        newProblem();

        this.setTitle("IT-Problemlöser");
        this.setSize(300, 400);

        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(300, 400));
        contentPane.setBackground(new Color(66, 143, 202));

        //BufferedImage myPicture = ImageIO.read(new reference.sy);
        picLabel = new JLabel("..");
        picLabel.setBounds(0, 130, 300, 200);
        picLabel.setVisible(false);
        picLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        button1 = new JButton();
        button1.setBounds(190, 350, 90, 35);
        button1.setForeground(new Color(66, 143, 202));
        button1.setBackground(new Color(255, 255, 255));
        button1.setEnabled(true);
        button1.setFont(new Font("raleway", Font.BOLD, 14));
        button1.setText("Ja");
        button1.setVisible(false);
        button1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                yesClicked(evt);
            }
        });

        button2 = new JButton();
        button2.setBounds(20, 350, 90, 35);
        button2.setForeground(new Color(66, 143, 202));
        button2.setBackground(new Color(255, 255, 255));
        button2.setEnabled(true);
        button2.setFont(new Font("raleway", 1, 14));
        button2.setText("Nein");
        button2.setVisible(false);
        button2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                noClicked(evt);
            }
        });

        //ERSTE AUSWAHL, PROBLEM = NETZWERK BEAMER COMPUTER

        button3 = new JButton();
        button3.setBounds(90, 100, 120, 35);
        button3.setForeground(new Color(66, 143, 202));
        button3.setBackground(new Color(255, 255, 255));
        button3.setEnabled(true);
        button3.setFont(new Font("raleway", 1, 14));
        button3.setText("Netzwerk");
        button3.setVisible(true);

        button3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                System.out.println("Clicked: Netzwerk");
                session = curReference.startNewSession(Subject.NETWORK);
                subjectChosen();
            }
        });

        button4 = new JButton();
        button4.setBounds(90, 170, 120, 35);
        button4.setForeground(new Color(66, 143, 202));
        button4.setBackground(new Color(255, 255, 255));
        button4.setEnabled(true);
        button4.setFont(new Font("raleway", 1, 14));
        button4.setText("Computer");
        button4.setVisible(true);

        button4.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                System.out.println("Clicked: Computer");
                session = curReference.startNewSession(Subject.COMPUTER);
                subjectChosen();
            }
        });


        button5 = new JButton();
        button5.setBounds(90, 240, 120, 35);
        button5.setForeground(new Color(66, 143, 202));
        button5.setBackground(new Color(255, 255, 255));
        button5.setEnabled(true);
        button5.setFont(new Font("raleway", 1, 14));
        button5.setText("Beamer");
        button5.setVisible(true);

        button5.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                System.out.println("Clicked: Beamer");
                session = curReference.startNewSession(Subject.BEAMER);
                subjectChosen();
            }
        });

        buttonRefresh = new JButton();
        buttonRefresh.setBounds(90, 350, 120, 35);
        buttonRefresh.setForeground(new Color(66, 143, 202));
        buttonRefresh.setBackground(new Color(255, 255, 255));
        buttonRefresh.setEnabled(true);
        buttonRefresh.setFont(new Font("raleway", 1, 14));
        buttonRefresh.setText("Neustart");
        buttonRefresh.setVisible(false);

        buttonRefresh.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                startAgain();
            }
        });

        buttonAdd = new JButton();
        buttonAdd.setBounds(240, 350, 35, 35);
        buttonAdd.setForeground(new Color(66, 143, 202));
        buttonAdd.setBackground(new Color(255, 255, 255));
        buttonAdd.setEnabled(true);
        buttonAdd.setFont(new Font("raleway", 1, 14));
        buttonAdd.setText("+");
        buttonAdd.setVisible(false);

        buttonAdd.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                startAgain();
            }
        });

        label1 = new JLabel();
        label1.setBounds(0, 0, 300, 40);
        label1.setForeground(new Color(255, 255, 255));
        label1.setHorizontalAlignment(0);
        label1.setEnabled(true);
        label1.setFont(new Font("raleway", 1, 20));
        label1.setText("IT-Problemlöser");
        label1.setVisible(true);

        label2 = new JLabel();
        label2.setBounds(0, 40, 300, 60);
        label2.setForeground(new Color(255, 255, 255));
        label2.setHorizontalAlignment(0);
        label2.setEnabled(true);
        label2.setFont(new Font("raleway", 0, 16));
        label2.setText("label");
        label2.setVisible(false);

        label3 = new JLabel();
        label3.setBounds(0, 50, 300, 120);
        label3.setForeground(new Color(255, 255, 255));
        label3.setHorizontalAlignment(0);
        label3.setEnabled(true);
        label3.setFont(new Font("raleway", 0, 14));
        label3.setText("label");
        label3.setVisible(false);

        contentPane.add(button1);
        contentPane.add(button2);
        contentPane.add(button3);
        contentPane.add(button4);
        contentPane.add(button5);
        contentPane.add(buttonRefresh);
        contentPane.add(buttonAdd);
        contentPane.add(label1);
        contentPane.add(label2);
        contentPane.add(label3);
        contentPane.add(picLabel);

        this.add(contentPane);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    //Method mouseClicked for button1
    private void yesClicked(MouseEvent evt) {
        session.answerYes();
        if (session.isAnswer()) {
            reboot("Ergebniss.");
        }
        label2.setText(session.getTitle());
        label3.setText(session.getDescription());


    }

    //Method mouseClicked for button2
    private void noClicked(MouseEvent evt) {
        session.answerNo();
        if (session.isAnswer()) {
            reboot("Ergebniss.");
        }
        label2.setText(session.getTitle());
        label3.setText(session.getDescription());


    }

    private void subjectChosen() {

        if (session != null) {
            if (session.isAvailable()) {
                button3.setVisible(false);
                button4.setVisible(false);
                button5.setVisible(false);

                button1.setVisible(true);
                button2.setVisible(true);

                label2.setText(session.getTitle());
                label3.setText(session.getDescription());
                //if (session.getPath() != null) { //TODO
                curReference.syncCurrentImage("hacker-stock-photo-15.jpg", picLabel);
                picLabel.setVisible(true);
                // }


                // }
                // else{
                //     System.out.println("bufferedImage is NULL");
                // }


                label2.setVisible(true);
                label3.setVisible(true);
            }
        } else {

            button3.setVisible(false);
            button4.setVisible(false);
            button5.setVisible(false);

            reboot("Ein Fehler ist aufgetreten.");
        }


    }

    private void reboot(String message) {
        button1.setVisible(false);
        button2.setVisible(false);

        buttonRefresh.setVisible(true);
        buttonAdd.setVisible(true);
        label2.setText(message);
        label2.setVisible(true);
        label3.setVisible(false);
        picLabel.setVisible(false);
    }

    private void startAgain() {
        buttonRefresh.setVisible(false);
        buttonAdd.setVisible(false);

        button3.setVisible(true);
        button4.setVisible(true);
        button5.setVisible(true);

        label2.setVisible(false);
        label3.setVisible(false);

    }

    private void newProblem() {
        if (curReference.internetAvailable())
            new NewEntryDialog(new Frame(), this);
        else
            label2.setText("Dazu ist ein Internetzugang nötig.");
    }

    public void addTree(String q, String qDesc, String ansNo, String noDesc, String ansYe, String yesDesc, String localPath, String file) {
        if (localPath != null && file != null) {
            try {
                curReference.uploadImage(localPath, file);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
      //  String makeTree = q+"_;_"+qDesc+"_;_"+"null"+"_;;_;"+"DUNNO"+ ansNo+"_;_"+noDesc"_;_"+"null"+
    }
}
