package de.slg.it.task;

import de.slg.it.Start;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImageSynchronizerTask implements Runnable {

    private String pathToFile;
    private JLabel pic;

    public ImageSynchronizerTask(String pathToFile, JLabel pic) {
        this.pathToFile = pathToFile;
        this.pic = pic;
    }

    @Override
    public void run() {
        try {
            URL updateURL = new URL(Start.DOMAIN_DATA + pathToFile);
            BufferedImage image = ImageIO.read(updateURL);

            if (image == null)
                return;


            ImageIcon imageIcon;

            if (image.getWidth() > image.getHeight()) {
                imageIcon = new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(pic.getWidth(), -1, Image.SCALE_DEFAULT));
            } else {
                imageIcon = new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(-1, pic.getHeight(), Image.SCALE_DEFAULT));

            }

            pic.setIcon(imageIcon);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
