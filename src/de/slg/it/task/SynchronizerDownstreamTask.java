package de.slg.it.task;

import de.slg.it.Start;
import de.slg.it.datastructure.DecisionTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Hashtable;

public class SynchronizerDownstreamTask extends SynchronizerTask {

    public SynchronizerDownstreamTask(String subject, Hashtable<String, DecisionTree> decisionTreeMap) {
        super(subject, decisionTreeMap);
    }

    @Override
    public void run() {
        try {
            URL updateURL = new URL(Start.DOMAIN_DEV + "get.php?subject=" + getSubject());
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

            getDecisionTreeMap().put(getSubject(), new DecisionTree(result));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}