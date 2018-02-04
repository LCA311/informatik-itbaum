package de.slg.it.utility;

public class ProblemContent {

    public final String title;
    public final String description;
    public final String pathToImage;

    public ProblemContent(String title, String description, String pathToImage) {
        this.title = title;
        this.description = description;
        if (pathToImage != null)
            this.pathToImage = pathToImage.equals("null") ? null : pathToImage;
        else
            this.pathToImage = null;
    }

    @Override
    public String toString() {
        return title + "_;_" + description + "_;_" + pathToImage;
    }
}
