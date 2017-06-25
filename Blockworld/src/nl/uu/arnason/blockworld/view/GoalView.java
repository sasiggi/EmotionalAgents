package nl.uu.arnason.blockworld.view;

import nl.uu.arnason.blockworld.U;
import nl.uu.arnason.blockworld.controller.GoalController;
import nl.uu.arnason.blockworld.model.agent.EState;
import nl.uu.arnason.blockworld.model.agent.Triggers.DestinationGoal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * The view listing the current goals that the agent has, and their moods.
 */
public class GoalView extends JPanel {

    private JButton[] goalBlocks;
    private int nrOfGoals = 0;
    private GoalController controller;


    public GoalView() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(150, 100));

        setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    public synchronized void updateGoals(int[][] targetList, EState[] eStates) {

        clearGoals();
        this.nrOfGoals = targetList.length;
        this.goalBlocks = new JButton[nrOfGoals];

        Insets buttonMargin = new Insets(10, 10, 10, 10);

        System.out.println("GoalView: numberOfGoals: " + nrOfGoals);

        for (int ii = 0; ii < nrOfGoals; ii++) {
            GoalBlock b = new GoalBlock();
            b.setPosX(targetList[ii][0]);
            b.setPosY(targetList[ii][1]);
            b.setMargin(buttonMargin);
            b.setText("Goal: " + b.getPosX() + "," + b.getPosY() + " E:" + eStates[ii].getDescription());
            if (eStates[ii].isHappy())
                setGoalBtnHappy(b);
            if (eStates[ii].isSad())
                setGoalBtnSad(b);
            if (eStates[ii].isNeutral())
                setGoalBtnNeutral(b);
            if (eStates[ii].isHopeFearful())
                setGoalBtnHopeFearful(b);

            goalBlocks[ii] = b;
        }

        for (int ii = 0; ii < nrOfGoals; ii++) {
            add(goalBlocks[ii], BorderLayout.AFTER_LAST_LINE);
        }

        applyController();

        revalidate();
        repaint();
//
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

    public void setGoalBtnHappy(JButton btn) {
	    btn.setBackground(Color.green);
    }
    public void setGoalBtnSad(JButton btn) {
        btn.setBackground(Color.red);
    }
    public void setGoalBtnNeutral(JButton btn) {
        btn.setBackground(Color.lightGray);
    }
    public void setGoalBtnHopeFearful(JButton btn) {
        btn.setBackground(Color.black);
        btn.setForeground(Color.white);
    }

    public class GoalBlock extends JButton {
        int posX;
        int posY;

        public GoalBlock() {

        }

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