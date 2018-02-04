package de.slg.it;

import de.slg.it.datastructure.DecisionTree;
import de.slg.it.task.ImageSynchronizerTask;
import de.slg.it.task.ImageUploadTask;
import de.slg.it.task.SynchronizerDownstreamTask;
import de.slg.it.task.SynchronizerUpstreamTask;
import de.slg.it.ui.GUI;
import de.slg.it.utility.Session;
import de.slg.it.utility.Subject;

import javax.swing.*;
import java.net.HttpURLConnection;
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
            Runnable cur = new SynchronizerDownstreamTask(tree, decisionTreeMap);
            new Thread(cur).start();
        }
    }

    private void fillMissingTrees(String... subjects) {
        for (String cur : subjects) {
            decisionTreeMap.computeIfAbsent(cur, k -> new DecisionTree());
        }
    }

    public void getCurrentImageFromServer(String pathToFile, JLabel pic) {
        Runnable cur = new ImageSynchronizerTask(pathToFile, pic);
        new Thread(cur).start();
    }


    public void uploadImage(String localPath) {
        Runnable r = new ImageUploadTask(localPath);
        new Thread(r).start();
    }

    public void uploadTree(String d) {
        Runnable cur = new SynchronizerUpstreamTask(d, decisionTreeMap);
        new Thread(cur).start();
    }

    public boolean internetAvailable() {
        return hasInternet;
    }

}
