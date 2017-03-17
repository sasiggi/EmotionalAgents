package nl.uu.arnason.blockworld.model.agent;

import nl.uu.arnason.blockworld.U;
import nl.uu.arnason.blockworld.model.GoalList;
import nl.uu.arnason.blockworld.model.Grid;
import nl.uu.arnason.blockworld.model.Model;
import oo2apl.agent.Context;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by siggi on 14-Mar-17.
 */
public class AgentModel implements Context{

    private Grid grid;  // internal grid belief
    private GoalList goalList;

    public AgentModel() {
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }



    public GoalList getGoalList() {
        return goalList;
    }

    public void setGoalList(GoalList goalList) {
        this.goalList = goalList;
    }
}
