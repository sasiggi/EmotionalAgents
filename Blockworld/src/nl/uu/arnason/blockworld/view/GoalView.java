package nl.uu.arnason.blockworld.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by siggi on 16-Mar-17.
 */
public class GoalView extends JPanel {

    private JButton[] goalBlocks;
    private int nrOfGoals;

	public GoalView() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.nrOfGoals = 10;
        this.goalBlocks = new JButton[nrOfGoals];

//        setBorder(new LineBorder(Color.BLACK));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // create the chess board squares
        Insets buttonMargin = new Insets(10,10,10,10);
        Insets buttonPadding = new Insets(10,10,10,10);

        for (int ii = 0; ii < nrOfGoals; ii++) {
            GoalBlock b = new GoalBlock();
            b.setPosX(ThreadLocalRandom.current().nextInt(0, nrOfGoals));
            b.setPosY(ThreadLocalRandom.current().nextInt(0, nrOfGoals));
            b.setMargin(buttonMargin);
            b.setText("Goal: "+b.getX()+","+b.getY());
            // our chess pieces are 64x64 px in size, so we'll
            // 'fill this in' using a transparent icon..
//            ImageIcon icon = new ImageIcon(
//                    new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
//            b.setIcon(icon);
//            b.setBackground(Color.WHITE);
            goalBlocks[ii] = b;
        }

        for (int ii = 0; ii < nrOfGoals; ii++) {
            add(goalBlocks[ii], BorderLayout.AFTER_LAST_LINE);
        }
    }


//    public void setController(MouseListener controller) {
//        // set controller as actionListener for all buttons that need it
//        for (int ii = 0; ii < height; ii++) {
//            for (int jj = 0; jj < width; jj++) {
//                gridSquares[ii][jj].addMouseListener(controller);
//            }
//        }
//    }
//
//    public JButton getGridSquare(int x, int y) {
//        return gridSquares[y][x];
//    }
//
//
//    public int getGridHeight() {
//        return height;
//    }
//
//    public int getGridWidth() {
//        return width;
//    }

    private class GoalBlock extends JButton {
        int posX;
        int posY;

        public int getPosX() {
            return posX;
        }

        public void setPosX(int posX) {
            this.posX = posX;
        }

        public int getPosY() {
            return posY;
        }

        public void setPosY(int posY) {
            this.posY = posY;
        }
    }

}