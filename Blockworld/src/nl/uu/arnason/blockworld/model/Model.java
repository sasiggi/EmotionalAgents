package nl.uu.arnason.blockworld.model;

import nl.uu.arnason.blockworld.U;
import oo2apl.agent.Context;

/**
 * Created by siggi on 13-Mar-17.
 */
public class Model  extends java.util.Observable implements Context{

    private Grid grid;

    public Model(int height, int width) {
        this.grid = new Grid(this, height,width);

    }

    @Override
    public void notifyObservers(Object arg) {
        U.p("notifyObservers in Model: agentPos: "+((Grid) arg).getAgentPosX()+","+((Grid) arg).getAgentPosY());
        super.notifyObservers(arg);
    }

    public Grid getGrid() {
        return grid;
    }

}
