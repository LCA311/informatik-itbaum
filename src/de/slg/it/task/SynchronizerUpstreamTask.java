package de.slg.it.task;

import de.slg.it.Start;
import de.slg.it.datastructure.DecisionTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Hashtable;

public class SynchronizerUpstreamTask extends SynchronizerTask {

    public SynchronizerUpstreamTask(String subject, Hashtable<String, DecisionTree> decisionTreeMap) {
        super(subject, decisionTreeMap);
    }

    @Override
    public void run() {
        try {
            System.out.println(getDecisionTreeMap().get(getSubject()).toString());
            URL url = new URL(Start.DOMAIN_DEV + "update.php?text=" + getDecisionTreeMap().get(getSubject()).toString().replace(" ", "%20") + "&thema=" + getSubject());
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
