package nl.uu.arnason.blockworld.controller;

import nl.uu.arnason.blockworld.Blockworld;
import nl.uu.arnason.blockworld.U;
import nl.uu.arnason.blockworld.model.Block;
import nl.uu.arnason.blockworld.model.Model;
import nl.uu.arnason.blockworld.view.Grid;
import nl.uu.arnason.blockworld.view.GridBlock;
import nl.uu.arnason.blockworld.view.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by siggi on 13-Mar-17.
 */
public class GridController implements java.awt.event.MouseListener, Observer {

    private MainWindow view;
    private Model model;

    public void addModel(Model m){
        System.out.println("GridController: adding model");
        this.model = m;
    }

    public void addView(MainWindow v){
        System.out.println("GridController: adding view");
        this.view = v;
    }

    @Override
    public void update(Observable o, Object arg) {
        for (int ii = 0; ii < view.getGrid().getGridHeight(); ii++) {
            for (int jj = 0; jj < view.getGrid().getGridWidth(); jj++) {
                Block.Status status = ((nl.uu.arnason.blockworld.model.Grid) arg).getBlockStatus(jj,ii);
                if(status.equals(Block.Status.WALL))
                    view.getGrid().getGridSquare(jj,ii).setBackground(Color.black);
                else if(status.equals(Block.Status.AGENT))
                    view.getGrid().getGridSquare(jj,ii).setBackground(Color.red);
                else
                    view.getGrid().getGridSquare(jj,ii).setBackground(Color.white);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x = ((GridBlock) e.getSource()).getPosX();
        int y = ((GridBlock) e.getSource()).getPosY();

        if(SwingUtilities.isLeftMouseButton(e) ) {
            U.p("Left mouse clicked");
            if (!model.getGrid().hasWall(x, y))
                model.getGrid().addWall(x, y);
            else
                model.getGrid().removeWall(x, y);
        }
        else if(SwingUtilities.isRightMouseButton(e)){
            U.p("Right mouse clicked");
             model.addGoal(x,y);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
