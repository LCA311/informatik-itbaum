package de.slg.it;

import javax.swing.*;

/**
 * Start.
 * <p>
 * Verwaltet Programmstart und globale Konstanten.
 *
 * @author Gianni
 * @version 2017.1712
 * @since 0.1
 */
public class Start {
    public static final String DOMAIN_SYNC = "https://ucloud4schools.de/ext/slg/leoapp_php/itbaum/";
    public static final String DOMAIN_DEV = "http://moritz.liegmanns.de/leoapp_php/itbaum/";
    public static final String DOMAIN_DATA = "http://moritz.liegmanns.de/leoapp_php/data/";

    public static void main(String[] args) {
        new Main();


        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException ignored) {
        }

    }

}
