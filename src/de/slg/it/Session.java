package de.slg.it;

import de.slg.it.datastructure.DecisionTree;

import java.util.Hashtable;

class Session {

    private DecisionTree current;
    private String subject;

    Session(String subject, Hashtable<String, DecisionTree> selection) {
        current = selection.get(subject);
        this.subject = subject;
    }

    String getSubject() {
        return subject;
    }

    String getQuestion() {
        return current.getContent();
    }

    boolean isAnswer() {
        return current.getLeftTree() == null && current.getRightTree() == null;
    }

    void answerYes() {
        current = current.getRightTree();
    }

    void answerNo() {
        current = current.getLeftTree();
    }

}
