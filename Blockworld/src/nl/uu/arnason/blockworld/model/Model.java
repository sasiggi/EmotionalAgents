package nl.uu.arnason.blockworld.model;

import nl.uu.arnason.blockworld.U;
import oo2apl.agent.Context;

/**
 * Created by siggi on 13-Mar-17.
 */
public class Model  extends java.util.Observable implements Context{

    private Grid grid;
    private GoalList goalList;
    private GoalList.GoalPoint newestGoal;

    public Model(int height, int width) {
        this.grid = new Grid(height,width);
        this.goalList = new GoalList(100);
    }

    public Grid getGrid() {
        return grid;
    }

    public GoalList.GoalPoint getNewestGoal() {
        return newestGoal;
    }

    public boolean addGoal(int x, int y) {
//        if(goalList.addGoal(x,y)) {
            newestGoal = new GoalList(10).new GoalPoint(x,y);
            U.p("Model.addGoal():GoalPoint: "+(newestGoal.getX()+","+newestGoal.getY()));
            setChanged();
            notifyObservers(newestGoal);
            return true;
//        } else
//            return false;
    }

    public boolean removeGoal(int x, int y) {
        if(goalList.removeGoal(x,y)) {
            setChanged();
            notifyObservers(goalList); //TODO: listen to changes in GoalController
            return true;
        } else
            return false;
    }


}
