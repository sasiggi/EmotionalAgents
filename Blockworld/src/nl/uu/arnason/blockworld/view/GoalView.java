package nl.uu.arnason.blockworld.view;

import nl.uu.arnason.blockworld.U;
import nl.uu.arnason.blockworld.controller.GoalController;
import nl.uu.arnason.blockworld.model.DestinationGoal;

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
    private int nrOfGoals = 0;
    private GoalController controller;

	public GoalView() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


//        setBorder(new LineBorder(Color.BLACK));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        updateGoals(new int[][]{{0,0}}, new DestinationGoal.Emotion[]{DestinationGoal.Emotion.NEUTRAL});


    }

    public void updateGoals(int[][] targetList, DestinationGoal.Emotion[] emotionList) {
	    U.p("GoalView updateGoals: "+targetList.length);
	    clearGoals();
        this.nrOfGoals = targetList.length;
        this.goalBlocks = new JButton[nrOfGoals];

        // create the chess board squares
        Insets buttonMargin = new Insets(10,10,10,10);

        for (int ii = 0; ii < nrOfGoals; ii++) {
            GoalBlock b = new GoalBlock();
            b.setPosX(targetList[ii][0]);
            b.setPosY(targetList[ii][1]);
            b.setMargin(buttonMargin);
            b.setText("Goal: "+b.getPosX()+","+b.getPosY()+" E:"+emotionList[ii].toString());
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

        applyController();

        revalidate();
        repaint();
    }

    public void clearGoals() {
        this.removeAll();
    }


    public void setController(GoalController controller) {
	    this.controller = controller;
	    applyController();
    }

    public void applyController() {

        // set controller as actionListener for all buttons that need it
        for (int ii = 0; ii < nrOfGoals; ii++) {
            goalBlocks[ii].addMouseListener(controller);
        }
    }
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

    public class GoalBlock extends JButton {
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