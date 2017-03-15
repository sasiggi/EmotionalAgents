package nl.uu.arnason.blockworld.model.agent;

import nl.uu.arnason.blockworld.model.Grid;
import oo2apl.agent.AgentContextInterface;
import oo2apl.agent.Goal;

/**
 * Created by siggi on 15-Mar-17.
 */
public class DestinationGoal extends Goal {

    private int x;
    private int y;

    public DestinationGoal(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean isAchieved(AgentContextInterface contextInterface) {
        AgentModel agentModel = contextInterface.getContext(AgentModel.class);
        Grid grid = agentModel.getGrid();
        if(grid.getAgentPosX() == this.x && grid.getAgentPosY() == this.y)
            return true;
        else
            return false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
