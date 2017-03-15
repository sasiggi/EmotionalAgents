package nl.uu.arnason.blockworld.model.agent;

import nl.uu.arnason.blockworld.U;
import nl.uu.arnason.blockworld.model.Grid;
import nl.uu.arnason.blockworld.model.Model;
import oo2apl.agent.Context;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by siggi on 14-Mar-17.
 */
public class AgentModel implements Context{


    private Grid realWorld; // reference to the real world to act on it
    private Grid grid;

    public AgentModel() {
    }


    public boolean moveRight() {
        if(grid.getAgentPosX() < grid.getWidth()) {
            //try to move in real world/model, model change updates the agent
            return realWorld.moveRight();
        }
        return false;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }


    public Grid getRealWorld() {
        return realWorld;
    }

    public void setRealWorld(Grid realWorld) {
        this.realWorld = realWorld;
    }

}
