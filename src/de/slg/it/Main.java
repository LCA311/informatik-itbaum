package de.slg.it;


import de.slg.it.datastructure.DecisionTree;
import de.slg.it.ui.GUI;
import de.slg.it.utility.Subject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.Hashtable;


/**
 * Main.
 * <p>
 * Allgemeine Klasse der PC-Komponente. Verwaltet die Programmlogik.
 *
 * @author Gianni, Mirko
 * @version 2017.1712
 * @since 0.1
 */
public class Main {
    //Hashtable ist Threadsafe
    private Hashtable<String, DecisionTree> decisionTreeMap;
    private boolean hasInternet;

    /**
     * Konstruktor.
     * <p>
     * Wird bei Programmstart aufgerufen, instanziiert und initialisiert alle nötigen Programmteile (inkl. GUI).
     */
    Main() {
        hasInternet = hasInternet();
        decisionTreeMap = new Hashtable<>();
        if (hasInternet) {
            syncTree(Subject.BEAMER, Subject.COMPUTER, Subject.NETWORK);
            Main reference = this;
            fillMissingTrees(Subject.BEAMER, Subject.COMPUTER, Subject.NETWORK);
            new GUI(reference);
        } else {
            Main reference = this;
            fillMissingTrees(Subject.BEAMER, Subject.COMPUTER, Subject.NETWORK);
            new GUI(reference);
        }
    }

    private boolean hasInternet() {
        try {
            URL url = new URL("http://google.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int code = connection.getResponseCode();
            System.out.println("Response code of the object is " + code);
            if (code == 200) {
                System.out.println("OK HTTP 200");
                return true;

            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Startet eine neue "Session" in der Fragen bzgl. des Problems beantwortet werden und gibt diese zurück.
     * Bei jedem Start wird die Referenz der vorherigen Session verworfen.
     *
     * @param subject {@link Subject Thema} des Problems
     */
    public Session startNewSession(String subject) {
        return new Session(subject, decisionTreeMap);
    }

    /**
     * Aktualisiert alle lokalen Entscheidungsbäume mithilfe der Datenbank.
     *
     * @param trees Bäume die aktualisiert werden sollen.
     */
    private void syncTree(String... trees) {
        for (String tree : trees) {
            Runnable cur = new SynchronizerDownstream(tree);
            new Thread(cur).start();
        }
    }

    private class SynchronizerDownstream implements Runnable {

        private String subject;

        private SynchronizerDownstream(String subject) {
            this.subject = subject;
        }

        @Override
        public void run() {
            try {
                URL updateURL = new URL(Start.DOMAIN_DEV + "get.php?subject=" + subject);
                BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader(updateURL.openConnection().getInputStream(), "UTF-8"));

                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null)
                    builder.append(line);
                reader.close();

                String result = builder.toString();

                if (result.startsWith("-"))
                    return;

                decisionTreeMap.put(subject, new DecisionTree(result));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    private void fillMissingTrees(String... subjects) {
        for (String cur : subjects) {
            decisionTreeMap.computeIfAbsent(cur, k -> new DecisionTree());
        }
    }

    public void syncCurrentImage(String pathToFile, JLabel pic) {
        Runnable cur = new SynchronizerImage(pathToFile, pic);
        new Thread(cur).start();
    }

    private class SynchronizerImage implements Runnable {

        private String pathToFile;
        private JLabel pic;

        private SynchronizerImage(String pathToFile, JLabel pic) {
            this.pathToFile = pathToFile;
            this.pic = pic;
        }

        @Override
        public void run() {
            try {
                URL updateURL = new URL(Start.DOMAIN_DATA + pathToFile);
                BufferedImage bufImage = ImageIO.read(updateURL);


                if (bufImage == null) {
                    System.out.println("BufferedImage is null");
                    return;
                }


               // BufferedImage newImage;
                ImageIcon imageIcon;

                if (bufImage.getWidth() > bufImage.getHeight()) {
                    imageIcon = new ImageIcon(new ImageIcon(bufImage).getImage().getScaledInstance(pic.getWidth(), -1, Image.SCALE_DEFAULT));
                } else {
                    imageIcon = new ImageIcon(new ImageIcon(bufImage).getImage().getScaledInstance(-1, pic.getHeight(), Image.SCALE_DEFAULT));

                }

                pic.setIcon(imageIcon);
               // pic.setIcon(new ImageIcon(newImage));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public void uploadImage(String localPath, String fileName) {
        Runnable r = new ImageUploader(localPath);
        new Thread(r).start();

    }

    private class ImageUploader implements Runnable {
        String localPath;
        ImageUploader(String localPath){
            this.localPath = localPath;
        }

        @Override
        public void run() {
            try {
                File fi = new File(localPath);
                byte[] fileContent = Files.readAllBytes(fi.toPath());

                System.out.println("Opening connection for image upload..."+localPath);
                URL uploadURL = new URL(Start.DOMAIN_DEV + "uploadImage.php");
                HttpURLConnection conn = (HttpURLConnection) uploadURL.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");

                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(fileContent);

                outputStream.close();
                System.out.println("Image uploaded.");

                BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader(
                                        conn.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null)
                    System.out.println("Image result: "+line);


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void uploadTree(String d) {
        new SynchronizerUpstream(d);
        Runnable cur = new SynchronizerUpstream(d);
        new Thread(cur).start();
    }

    private class SynchronizerUpstream implements Runnable {
        String subject;

        SynchronizerUpstream(String subject) {
            this.subject = subject;
        }

        @Override
        public void run() {
            try {
                System.out.println(decisionTreeMap.get(subject).toString());
                URL url = new URL(Start.DOMAIN_DEV + "update.php?text=" + decisionTreeMap.get(subject).toString().replace(" ", "%20") + "&thema=" + subject);
                BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader(
                                        url.openConnection().getInputStream()));
                String line;
                while ((line = reader.readLine()) != null)
                    System.out.println(line);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public boolean internetAvailable() {
        return hasInternet;
    }

}
