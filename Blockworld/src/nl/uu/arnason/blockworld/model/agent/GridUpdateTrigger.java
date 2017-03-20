package nl.uu.arnason.blockworld.model.agent;

/**
 * Created by siggi on 14-Mar-17.
 */

import nl.uu.arnason.blockworld.U;
import nl.uu.arnason.blockworld.model.Grid;
import oo2apl.agent.AgentContextInterface;
import oo2apl.agent.Goal;
import oo2apl.agent.Trigger;

import java.util.Observable;
import java.util.Observer;

public final class GridUpdateTrigger implements Trigger {

    private Grid grid;

    public GridUpdateTrigger(Grid grid){
        this.grid = grid;
    }

    public Grid getGrid() {
        return grid;
    }

}
