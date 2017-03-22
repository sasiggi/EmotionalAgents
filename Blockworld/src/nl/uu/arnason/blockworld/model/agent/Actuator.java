package nl.uu.arnason.blockworld.model.agent;

import nl.uu.arnason.blockworld.U;
import nl.uu.arnason.blockworld.model.Block;
import nl.uu.arnason.blockworld.model.Grid;
import nl.uu.arnason.blockworld.model.Model;
import oo2apl.agent.Context;

/**
 * Created by siggi on 16-Mar-17.
 */
public class Actuator implements Context {

    private Model realWorld;

    public Actuator(Model realWorld) {
        this.realWorld = realWorld;
    }


    public boolean moveAgentBy(int x, int y) {
        try {
            // a movement takes time, even if it fails
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Grid grid = realWorld.getGrid();
            int agentX = grid.getAgentPosX();
            int agentY = grid.getAgentPosY();
            if(grid.getBlockStatus(agentX + x, agentY + y).equals(Block.Status.WALL))
                return false;
            else {
                //use semaphores?
                realWorld.getGrid().moveAgentBy(x, y);
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            U.p("Illegal movement:"+x+","+y);
            return false;
        }
    }

}
