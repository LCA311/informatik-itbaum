package de.slg.it.task;


import de.slg.it.datastructure.DecisionTree;

import java.util.Hashtable;

public abstract class SynchronizerTask implements Runnable {

    private String subject;
    private Hashtable<String, DecisionTree> decisionTreeMap;

    public SynchronizerTask(String subject, Hashtable<String, DecisionTree> decisionTreeMap) {
        this.subject = subject;
        this.decisionTreeMap = decisionTreeMap;
    }

    protected String getSubject() {
        return subject;
    }

    protected Hashtable<String, DecisionTree> getDecisionTreeMap() {
        return decisionTreeMap;
    }

}
