package nl.uu.arnason.blockworld.controller;

import nl.uu.arnason.blockworld.U;
import nl.uu.arnason.blockworld.model.agent.Triggers.DestinationGoal;
import nl.uu.arnason.blockworld.model.agent.GoalBase;
import nl.uu.arnason.blockworld.view.GoalView;
import nl.uu.arnason.blockworld.view.MainWindow;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by siggi on 20-Mar-17.
 */
public class GoalController  implements java.awt.event.MouseListener, Observer {

    private MainWindow view;
    private GoalBase goalBase;

    public void addGoalBase(GoalBase gb){
        this.goalBase = gb;
    }

    public void addView(MainWindow v){
        this.view = v;
    }

    /**
     Rebuild the list of goals when the goal base has been changed
     */
    @Override
    public void update(Observable o, Object arg) {
        U.p("GoalController update");
        if(arg != null) {
            if (arg instanceof GoalBase) {
                GoalBase goalBase = (GoalBase) arg;
                JButton[] goalBlocks = new JButton[goalBase.getNumberOfGoals()];
                GoalView goalView = view.getGoalView();
                int[][] targetList = goalBase.getTargetList();
                DestinationGoal.Emotion[] emotionList = goalBase.getEmotionList();
                goalView.updateGoals(targetList, emotionList);
            }
        }
    }

    /**
     * When a goal is clicked we remove it from the goal base
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        int x = ((GoalView.GoalBlock) e.getSource()).getPosX();
        int y = ((GoalView.GoalBlock) e.getSource()).getPosY();

        if(SwingUtilities.isLeftMouseButton(e) ) {
            U.p("Left mouse clicked");
            goalBase.removeGoal(x,y);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

