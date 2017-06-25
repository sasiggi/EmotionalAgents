package nl.uu.arnason.blockworld.model.agent;

import nl.uu.arnason.blockworld.U;
import nl.uu.arnason.blockworld.model.Block;
import nl.uu.arnason.blockworld.model.Grid;
import nl.uu.arnason.blockworld.model.Model;
import oo2apl.agent.Context;

/**
 * An agent has an actuator to act on the world.
 */
public class Actuator implements Context {

    private Model realWorld;

    public Actuator(Model realWorld) {
        this.realWorld = realWorld;
    }


    public boolean moveAgentBy(int x, int y) {
        System.out.println("Actuator: moveBy "+x+","+y);
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
                realWorld.getGrid().moveAgentBy(x, y);
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            U.p("Actuator: Illegal movement:"+x+","+y);
            return false;
        }
    }

}
