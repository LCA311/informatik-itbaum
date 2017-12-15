package de.slg.it.utility;

/**
 * Answer.
 *
 * Verwaltungsobjekt. POJO mit allen Eigenschaften einer DecisionTree Node zum temporären Speichern dieser.
 *
 * @author Gianni
 * @since 0.1
 * @version 2017.1512
 */
public final class Answer {
    public final String name;
    public final Topic  topic;
    public final int    posX;
    public final int    posY;

    public Answer(String name, Topic topic, int posX, int posY) {
        this.name  = name;
        this.topic = topic;
        this.posX  = posX;
        this.posY  = posY;
    }
}
