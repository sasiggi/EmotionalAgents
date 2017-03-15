package nl.uu.arnason.blockworld.controller;

import nl.uu.arnason.blockworld.Blockworld;
import nl.uu.arnason.blockworld.model.Model;
import nl.uu.arnason.blockworld.view.GridBlock;
import nl.uu.arnason.blockworld.view.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by siggi on 13-Mar-17.
 */
public class GridController implements java.awt.event.ActionListener {

    private MainWindow view;
    private Model model;


    @Override
    public void actionPerformed(ActionEvent e) {
        int x = ((GridBlock) e.getSource()).getPosX();
        int y = ((GridBlock) e.getSource()).getPosY();
        if(!model.getGrid().hasWall(x,y))
            model.getGrid().addWall(x,y);
        else
            model.getGrid().removeWall(x,y);
    }

    public void addModel(Model m){
        System.out.println("GridController: adding model");
        this.model = m;
    }

    public void addView(MainWindow v){
        System.out.println("GridController: adding view");
        this.view = v;
    }
}
