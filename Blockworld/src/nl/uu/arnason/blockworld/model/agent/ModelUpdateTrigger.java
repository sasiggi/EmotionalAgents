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

public final class ModelUpdateTrigger implements Trigger, Observer {

    private Grid grid;

    public ModelUpdateTrigger(){
    }

    public Grid getGrid() {
        return grid;
    }

    @Override
    public void update(Observable o, Object arg) {
        U.p("Updating agent:"+ ((Grid)arg).getBlockStatus(0,0));
        this.grid = ((Grid)arg);

    }

}
