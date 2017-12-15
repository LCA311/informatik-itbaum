package de.slg.it;

/**
 * DecisionTree.
 *
 * Subklasse von BinaryTree, um einige Methoden für den Entscheidungsbaum erweitert.
 *
 * @author Gianni
 * @since 0.1
 * @version 2017.1512
 */
public class DecisionTree extends BinaryTree<String> {

    /**
     * Konstruktor.
     *
     * Erstellt den Baum anhand eines Strings. Format des Strings: Inhalt_;_X_;_Y_;;_
     *
     * @param tree Stringrepräsentation des Baums
     */
    public DecisionTree(String tree) {
        super();
        String[] nodes = tree.split("_;_");
    }

    public DecisionTree getLeftTree() {
        return (DecisionTree) super.getLeftTree();
    }

    public DecisionTree getRightTree() {
        return (DecisionTree) super.getRightTree();
    }

    public void setLeftTree(DecisionTree tree) {
        super.setLeftTree(tree);
    }

    public void setRightTree(DecisionTree tree) {
        super.setRightTree(tree);
    }

    @Override
    public String toString() {
        return createStringRepresentation(0, 0);
    }

    //preorder
    private String createStringRepresentation(int x, int y) {
        String current = getContent();

        return current+"_;_"+x+"_;_"+y+"_;;_"
                + getLeftTree().createStringRepresentation(2*x, ++y)
                + getRightTree().createStringRepresentation(2*x+1, ++y);
    }

}
