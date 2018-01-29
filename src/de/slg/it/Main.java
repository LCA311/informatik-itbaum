package de.slg.it;


import de.slg.it.datastructure.DecisionTree;
import de.slg.it.utility.Subject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
@SuppressWarnings("WeakerAcess")
class Main {
    //Hashtable ist Threadsafe
    private Hashtable<String, DecisionTree> decisionTreeMap;
    private BufferedImage bufImage;
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
            new GUI_project(reference);
        } else {
            //TODO Textdatei
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
            //System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Startet eine neue "Session" in der Fragen bzgl. des Problems beantwortet werden und gibt diese zurück.
     * Bei jedem Start wird die Referenz der vorherigen Session verworfen.
     *
     * @param subject {@link Subject Thema} des Problems
     */
    Session startNewSession(String subject) {
        return new Session(subject, decisionTreeMap);
    }

    /**
     * Aktualisiert alle lokalen Entscheidungsbäume mithilfe der Datenbank.
     *
     * @param trees Bäume die aktualisiert werden sollen.
     */
    void syncTree(String... trees) {
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
            if (decisionTreeMap.get(cur) == null) {
                decisionTreeMap.put(cur, new DecisionTree());
            }
        }
    }


    /**
     * //TODO Interface JLabel
     */
    public BufferedImage syncCurrentImage(String pathToFile, JLabel pic) {
        Runnable cur = new SynchronizerImage(pathToFile, pic);
        new Thread(cur).start();
        if (bufImage != null) {
            return bufImage;
        }
        return null;
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
                BufferedImage image = ImageIO.read(updateURL);


                if (image == null){
                    System.out.println("BufferedImage is null");
                    return;
                }


                bufImage = image;
                pic.setIcon(new ImageIcon(image));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
    public void uploadImage(String localPath, String fileName) throws IOException, InterruptedException {
        HttpURLConnection httpUrlConnection = (HttpURLConnection)new URL("http://moritz.liegmanns.de/leoapp_php/itbaum/uploadImage.php?"+fileName).openConnection();
        httpUrlConnection.setDoOutput(true);
        httpUrlConnection.setRequestMethod("POST");
        OutputStream os = httpUrlConnection.getOutputStream();
        Thread.sleep(1000);
        BufferedInputStream fis = new BufferedInputStream(new FileInputStream(localPath));

   //     for (int i = 0; i < totalByte; i++) {
      //      os.write(fis.read());
     //       byteTrasferred = i + 1;
     //   }

        os.close();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        httpUrlConnection.getInputStream()));

        String s = null;
        while ((s = in.readLine()) != null) {
            System.out.println(s);
        }
        in.close();
        fis.close();
    }

    public boolean internetAvailable(){
        return hasInternet;
    }
}
