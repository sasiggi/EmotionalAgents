package nl.uu.arnason.blockworld.controller;

import nl.uu.arnason.blockworld.U;
import nl.uu.arnason.blockworld.model.Block;
import nl.uu.arnason.blockworld.model.Grid;
import nl.uu.arnason.blockworld.model.Model;
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
        if(arg != null) {
             if (arg instanceof Grid) {
                for (int ii = 0; ii < view.getGridView().getGridHeight(); ii++) {
                    for (int jj = 0; jj < view.getGridView().getGridWidth(); jj++) {
                        Block.Status status = ((Grid) arg).getBlockStatus(jj, ii);
                        if (status.equals(Block.Status.WALL))
                            view.getGridView().getGridSquare(jj, ii).setBackground(Color.black);
                        else if (status.equals(Block.Status.AGENT))
                            view.getGridView().getGridSquare(jj, ii).setBackground(Color.red);
                        else
                            view.getGridView().getGridSquare(jj, ii).setBackground(Color.white);
                    }
                }
            }
        }
    }

    /**
     * When a button is clicked in the grid we change the model
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        int x = ((GridView.GridBlock) e.getSource()).getPosX();
        int y = ((GridView.GridBlock) e.getSource()).getPosY();

        if(SwingUtilities.isLeftMouseButton(e) ) {
            U.p("Left mouse clicked");
            if (!model.getGrid().hasWall(x, y))
                model.getGrid().addWall(x, y);
            else
                model.getGrid().removeWall(x, y);
        }
        else if(SwingUtilities.isRightMouseButton(e)){
            U.p("Right mouse clicked");
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
