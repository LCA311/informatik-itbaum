package de.slg.it.ui;

import de.slg.it.Main;
import de.slg.it.utility.Session;
import de.slg.it.datastructure.DecisionTree;
import de.slg.it.utility.ProblemContent;
import de.slg.it.utility.Subject;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;


public class GUI extends JFrame {

    private JButton button1; //JA NEIN
    private JButton button2;

    private JButton button3; //NETZWERK, COMPUTER, BEAMER
    private JButton button4;
    private JButton button5;

    private JButton buttonRefresh;
    private JButton buttonAdd;

    private JLabel label2;

    private JTextArea textArea;

    private JLabel picLabel;

    private JScrollPane scrollPane;

    private Session session = null;
    private Main curReference;


    public GUI(Main reference) {
        curReference = reference;


        this.setTitle("IT-Problemlöser");
        this.setSize(300, 400);

        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(300, 400));
        contentPane.setBackground(new Color(66, 143, 202));

        //BufferedImage myPicture = ImageIO.read(new reference.sy);
        picLabel = new JLabel("Kein Bild");
        picLabel.setBounds(0, 130, 300, 200);
        picLabel.setForeground(new Color(255, 255, 255));
        picLabel.setVisible(false);
        picLabel.setFont(new Font("raleway", Font.BOLD, 14));

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
                yesClicked();
            }
        });

        button2 = new JButton();
        button2.setBounds(20, 350, 90, 35);
        button2.setForeground(new Color(66, 143, 202));
        button2.setBackground(new Color(255, 255, 255));
        button2.setEnabled(true);
        button2.setFont(new Font("raleway", Font.BOLD, 14));
        button2.setText("Nein");
        button2.setVisible(false);
        button2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                noClicked();
            }
        });

        //ERSTE AUSWAHL, PROBLEM = NETZWERK BEAMER COMPUTER

        button3 = new JButton();
        button3.setBounds(90, 100, 120, 35);
        button3.setForeground(new Color(66, 143, 202));
        button3.setBackground(new Color(255, 255, 255));
        button3.setEnabled(true);
        button3.setFont(new Font("raleway", Font.BOLD, 14));
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
        button4.setFont(new Font("raleway", Font.BOLD, 14));
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
        button5.setFont(new Font("raleway", Font.BOLD, 14));
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
        buttonRefresh.setFont(new Font("raleway", Font.BOLD, 14));
        buttonRefresh.setText("Neustart");
        buttonRefresh.setVisible(false);
        buttonRefresh.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                startAgain();
            }
        });

        buttonAdd = new JButton();
        buttonAdd.setBounds(240, 350, 45, 35);
        buttonAdd.setForeground(new Color(66, 143, 202));
        buttonAdd.setBackground(new Color(255, 255, 255));
        buttonAdd.setEnabled(true);
        buttonAdd.setFont(new Font("raleway", Font.BOLD, 18));
        buttonAdd.setText("+");
        buttonAdd.setVisible(false);
        buttonAdd.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                openPassword();
            }
        });

        JLabel label1 = new JLabel();
        label1.setBounds(0, 0, 300, 40);
        label1.setForeground(new Color(255, 255, 255));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setEnabled(true);
        label1.setFont(new Font("raleway", Font.BOLD, 20));
        label1.setText("IT-Problemlöser");
        label1.setVisible(true);

        label2 = new JLabel();
        label2.setBounds(0, 20, 300, 60);
        label2.setForeground(new Color(255, 255, 255));
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setEnabled(true);
        label2.setFont(new Font("raleway", Font.PLAIN, 16));
        label2.setText("label");
        label2.setVisible(false);

        textArea = new JTextArea();
        //textArea.setBounds(0, 60, 300, 60);
        textArea.setForeground(new Color(255, 255, 255));
        textArea.setEnabled(true);
        textArea.setFont(new Font("raleway", Font.PLAIN, 14));
        textArea.setText("label");
        textArea.setEditable(false);

        textArea.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        textArea.setBackground(new Color(66, 143, 202));
        //textArea.setVisible(false);


        //scrollPane.add(textArea);
        scrollPane = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        //scrollPane.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        scrollPane.setVisible(false);
        // scrollPane.setSize(100,100);
        scrollPane.setBounds(0, 65, 300, 60);
        contentPane.add(scrollPane);

        contentPane.add(button1);
        contentPane.add(button2);
        contentPane.add(button3);
        contentPane.add(button4);
        contentPane.add(button5);
        contentPane.add(buttonRefresh);
        contentPane.add(buttonAdd);
        contentPane.add(label1);
        contentPane.add(label2);
        //   contentPane.add(textArea);
        contentPane.add(picLabel);

        this.add(contentPane);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    private void yesClicked() {
        session.answerYes();
        if (session.isAnswer()) {
            reboot("Ergebniss.");
        }
        label2.setText(session.getTitle());
        textArea.setText(session.getDescription());
        picLabel.setIcon(null);

    }

    //Method mouseClicked for button2
    private void noClicked() {
        session.answerNo();
        if (session.isAnswer()) {
            reboot("Ergebniss.");
        }
        label2.setText(session.getTitle());
        textArea.setText(session.getDescription());
        picLabel.setIcon(null);

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
                textArea.setText(session.getDescription());

                if (session.getPath() != null) {
                    curReference.getCurrentImageFromServer(session.getPath(), picLabel);
                    picLabel.setVisible(true);
                }


            } else {
                System.out.println("bufferedImage is NULL");
            }


            label2.setVisible(true);
            scrollPane.setVisible(true);
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
        scrollPane.setVisible(false);
        picLabel.setVisible(false);
    }

    private void startAgain() {
        buttonRefresh.setVisible(false);
        buttonAdd.setVisible(false);

        button3.setVisible(true);
        button4.setVisible(true);
        button5.setVisible(true);

        label2.setVisible(false);
        scrollPane.setVisible(false);

    }

    private void openPassword() {
        if (curReference.internetAvailable())
            new PasswordDialog(new Frame(), this);
        else
            label2.setText("Dazu ist ein Internetzugang nötig.");

    }

    void newProblem() {
        new NewEntryDialog(new Frame(), this);
    }

    void addTree(String q, String qDesc, String ansNo, String noDesc, String ansYe, String yesDesc, String localPath, String file) {
        System.out.println("Adding tree...");
        if (!ansNo.equals("") && !noDesc.equals("")) {
            String no = ansNo + "_;_" + noDesc + "_;_null_;;_";
            DecisionTree treeNo = new DecisionTree(no);
            session.getCurrent().setRightTree(treeNo);
        }

        if (!ansYe.equals("") && !yesDesc.equals("")) {
            String yes = ansYe + "_;_" + yesDesc + "_;_null_;;_";
            DecisionTree treeYes = new DecisionTree(yes);
            session.getCurrent().setLeftTree(treeYes);
        }

        if (!q.equals("") || !qDesc.equals("")) {

            if (localPath != null && file != null) {
                System.out.println("Uploading image...");
                curReference.uploadImage(localPath);
                session.setContent(new ProblemContent(q, qDesc, "data/"+file));
                curReference.uploadTree(session.getSubject());
            } else {
                System.out.println("No image attached...");
                session.setContent(new ProblemContent(q, qDesc, "null"));
                curReference.uploadTree(session.getSubject());
            }

        }
    }
}
