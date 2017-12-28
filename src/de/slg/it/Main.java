package de.slg.it;

import de.slg.it.datastructure.DecisionTree;
import de.slg.it.utility.Subject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Hashtable;

/**
 * Main.
 *
 * Allgemeine Klasse der PC-Komponente. Verwaltet die Programmlogik.
 *
 * @author Gianni, Mirko
 * @since 0.1
 * @version 2017.1712
 */
@SuppressWarnings("WeakerAcess")
class Main {
    //Hashtable ist Threadsafe
    private Hashtable<String, DecisionTree> decisionTreeMap;

    /**
     * Konstruktor.
     *
     * Wird bei Programmstart aufgerufen, instanziiert und initialisiert alle nötigen Programmteile (inkl. GUI).
     */
    Main() {
        decisionTreeMap = new Hashtable<>();
        syncTree(Subject.BEAMER, Subject.COMPUTER, Subject.NETWORK);

        new GUI(this);
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
                URL updateURL = new URL(Start.DOMAIN_DEV + "get.php?subject="+subject);
                BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader(updateURL.openConnection().getInputStream(), "UTF-8"));

                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null)
                    builder.append(line);
                reader.close();

                String result = builder.toString();

                if(result.startsWith("-"))
                    return;

                decisionTreeMap.put(subject, new DecisionTree(result));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
