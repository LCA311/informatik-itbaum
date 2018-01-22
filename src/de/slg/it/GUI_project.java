package de.slg.it;

import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.border.Border;
import javax.swing.*;





    public class GUI_project extends JFrame {

        private JMenuBar menuBar;
        private JButton button1;
        private JButton button2;
        private JLabel label1;
        private JLabel label2;
        private JLabel label3;

        //Constructor
        public GUI_project(){

            this.setTitle("GUI_project");
            this.setSize(300,400);
            //menu generate method
            generateMenu();
            this.setJMenuBar(menuBar);

            //pane with null layout
            JPanel contentPane = new JPanel(null);
            contentPane.setPreferredSize(new Dimension(300,400));
            contentPane.setBackground(new Color(192,192,192));


            button1 = new JButton();
            button1.setBounds(176,349,90,35);
            button1.setBackground(new Color(214,217,223));
            button1.setForeground(new Color(0,0,0));
            button1.setEnabled(true);
            button1.setFont(new Font("sansserif",0,12));
            button1.setText("Button1");
            button1.setVisible(true);
            //Set methods for mouse events
            //Call defined methods
            button1.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    yesClicked(evt);
                }
            });


            button2 = new JButton();
            button2.setBounds(21,351,90,35);
            button2.setBackground(new Color(214,217,223));
            button2.setForeground(new Color(0,0,0));
            button2.setEnabled(true);
            button2.setFont(new Font("sansserif",0,12));
            button2.setText("Button2");
            button2.setVisible(true);
            //Set action for button click
            //Call defined method
            button2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    noPressed(evt);
                }
            });

            //Set methods for mouse events
            //Call defined methods
            button2.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    noClicked(evt);
                }
            });


            label1 = new JLabel();
            label1.setBounds(96,6,90,35);
            label1.setBackground(new Color(214,217,223));
            label1.setForeground(new Color(0,0,0));
            label1.setEnabled(true);
            label1.setFont(new Font("sansserif",0,12));
            label1.setText("label");
            label1.setVisible(true);

            label2 = new JLabel();
            label2.setBounds(80,60,90,35);
            label2.setBackground(new Color(214,217,223));
            label2.setForeground(new Color(0,0,0));
            label2.setEnabled(true);
            label2.setFont(new Font("sansserif",0,12));
            label2.setText("label");
            label2.setVisible(true);

            label3 = new JLabel();
            label3.setBounds(74,92,90,35);
            label3.setBackground(new Color(214,217,223));
            label3.setForeground(new Color(0,0,0));
            label3.setEnabled(true);
            label3.setFont(new Font("sansserif",0,12));
            label3.setText("label");
            label3.setVisible(true);

            //adding components to contentPane panel
            contentPane.add(button1);
            contentPane.add(button2);
            contentPane.add(label1);
            contentPane.add(label2);
            contentPane.add(label3);

            //adding panel to JFrame and seting of window position and close operation
            this.add(contentPane);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLocationRelativeTo(null);
            this.pack();
            this.setVisible(true);
        }

        //Method mouseClicked for button1
        private void yesClicked (MouseEvent evt) {
            //TODO
        }

        //Method actionPerformed for button2
        private void noPressed (ActionEvent evt) {
            //TODO
        }

        //Method mouseClicked for button2
        private void noClicked (MouseEvent evt) {
            //TODO
        }

        //method for generate menu
        public void generateMenu(){
            menuBar = new JMenuBar();

            JMenu file = new JMenu("File");
            JMenu tools = new JMenu("Tools");
            JMenu help = new JMenu("Help");

            JMenuItem open = new JMenuItem("Open   ");
            JMenuItem save = new JMenuItem("Save   ");
            JMenuItem exit = new JMenuItem("Exit   ");
            JMenuItem preferences = new JMenuItem("Preferences   ");
            JMenuItem about = new JMenuItem("About   ");


            file.add(open);
            file.add(save);
            file.addSeparator();
            file.add(exit);
            tools.add(preferences);
            help.add(about);

            menuBar.add(file);
            menuBar.add(tools);
            menuBar.add(help);
        }



        public static void main(String[] args){
            System.setProperty("swing.defaultlaf", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new GUI_project();
                }
            });
        }

    }
}
