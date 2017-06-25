package nl.uu.arnason.blockworld;

import nl.uu.arnason.blockworld.model.Model;

import javax.swing.*;

/**
 **** This package is written by Sigurdur Arnason, email sasiggi@gmail.com ****
 *
 * Starts the UI thread
 */
public class Main {

    public static void main(String[] args) {
        Runnable r = new Runnable() {

            @Override
            public void run() {
                Blockworld bw = new Blockworld();

                JFrame f = new JFrame("BlockWorld");
                f.add(bw.getGui());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);

                // ensures the frame is the minimum size it needs to be
                // in order to display the components within it
                f.pack();
                // ensures the minimum size is enforced.
                f.setMinimumSize(f.getSize());
                f.setVisible(true);

                f.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                        bw.shutDownAgents();
                    }
                });
            }

        };
        SwingUtilities.invokeLater(r);
    }


}
