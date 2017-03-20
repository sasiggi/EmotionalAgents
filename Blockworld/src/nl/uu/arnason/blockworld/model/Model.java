package nl.uu.arnason.blockworld.model;

import nl.uu.arnason.blockworld.U;
import nl.uu.arnason.blockworld.model.agent.GoalBase;
import oo2apl.agent.Context;

/**
 * The real world
 */
public class Model  extends java.util.Observable implements Context{

    private Grid grid;
    private GoalBase goalBase;


    public Model(int height, int width) {
        this.grid = new Grid(height,width);
    }

    public Grid getGrid() {
        return grid;
    }

    public GoalBase getGoalBase() {
        return goalBase;
    }

    public void setGoalBase(GoalBase goalBase) {
        this.goalBase = goalBase;
    }
}
