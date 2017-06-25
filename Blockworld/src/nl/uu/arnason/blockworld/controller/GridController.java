package nl.uu.arnason.blockworld.controller;

import nl.uu.arnason.blockworld.U;
import nl.uu.arnason.blockworld.model.Block;
import nl.uu.arnason.blockworld.model.Grid;
import nl.uu.arnason.blockworld.model.Model;
import nl.uu.arnason.blockworld.model.agent.EState;
import nl.uu.arnason.blockworld.model.agent.GoalBase;
import nl.uu.arnason.blockworld.view.GridView;
import nl.uu.arnason.blockworld.view.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Grid controller should observe the modelGrid and be a MouseListener for the GridButtons in the gridView.
 * The controller needs references to the model and the view given with addModel() and addView().
 */
public class GridController implements java.awt.event.MouseListener, Observer {

    private MainWindow view;
    private Model model;

    public void addModel(Model m){
        this.model = m;
    }

    public void addView(MainWindow v){
        this.view = v;
    }

    /**
    When the model notifies changes.
     If the change was the grid then we update the whole grid.
     */
    @Override
    public void update(Observable o, Object arg) {
        GridView gv = view.getGridView();
        if(arg != null) {
            if (arg instanceof Grid || arg instanceof GoalBase) {
                // show the agent and the walls
                for (int ii = 0; ii < gv.getGridHeight(); ii++) {
                    for (int jj = 0; jj < gv.getGridWidth(); jj++) {
                        Block.Status status = model.getGrid().getBlockStatus(jj, ii);
                        if (status.equals(Block.Status.WALL))
                            gv.getGridSquare(jj, ii).setBackground(Color.black);
                        else if (status.equals(Block.Status.AGENT))
                            gv.setBackgroundAgent(gv.getGridSquare(jj, ii));
                        else
                            gv.setBackgroundEmpty(gv.getGridSquare(jj, ii));
                    }
                }

                int[][] targetList = model.getGoalBase().getTargetList();
                EState[] emotionList = model.getGoalBase().getEStateList();


                // show the goals if the block is not a wall or the agent is positioned there
                for(int i = 0; i<targetList.length; i++) {
                    int[] target = targetList[i];
                    Block.Status status = model.getGrid().getBlockStatus(target[0], target[1]);
                    if(!status.equals(Block.Status.WALL) && !status.equals(Block.Status.AGENT)) {
                        if(emotionList[i].isHopeFearful())
                            gv.setBackgroundHopeFearfulGoal(gv.getGridSquare(target[0], target[1]));
                        if(emotionList[i].isHappy())
                            gv.setBackgroundHappyGoal(gv.getGridSquare(target[0], target[1]));
                        if(emotionList[i].isSad())
                            gv.setBackgroundSadGoal(gv.getGridSquare(target[0], target[1]));
                        if(emotionList[i].isNeutral())
                            gv.setBackgroundNeutralGoal(gv.getGridSquare(target[0], target[1]));

                    }
                }
            }
        }
    }

    /**
     * When a button is clicked in the grid we change the model
     * Left mouse button builds a wall.
     * Right mouse button adds a goal.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        int x = ((GridView.GridBlock) e.getSource()).getPosX();
        int y = ((GridView.GridBlock) e.getSource()).getPosY();

        if(SwingUtilities.isLeftMouseButton(e) ) {
            if (!model.getGrid().hasWall(x, y))
                model.getGrid().addWall(x, y);
            else
                model.getGrid().removeWall(x, y);
        }
        else if(SwingUtilities.isRightMouseButton(e)){
            model.getGoalBase().addGoal(x,y);
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
