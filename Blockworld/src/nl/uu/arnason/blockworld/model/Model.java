package nl.uu.arnason.blockworld.model;

import nl.uu.arnason.blockworld.U;
import nl.uu.arnason.blockworld.model.agent.GoalBase;
import oo2apl.agent.Context;

/**
 * The real world Grid and the agent's GoalBase.
 * Normally a Model object would not have access to an agent's GoalBase, but since we want to show it
 * in the view we keep a reference to it here.
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
