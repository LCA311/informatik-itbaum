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

    String getTitle() {
        return current.getContent().title;
    }

    String getDescription() {
        return current.getContent().description;
    }

    String getPath() {
        return current.getContent().pathToImage;
    }

    boolean isAnswer() {
        return current.getLeftTree().getContent() == null && current.getRightTree().getContent() == null;
    }

    void answerYes() {
        current = current.getRightTree();
    }

    void answerNo() {
        current = current.getLeftTree();
    }

}
