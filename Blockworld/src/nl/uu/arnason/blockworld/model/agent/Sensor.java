package nl.uu.arnason.blockworld.model.agent;

import nl.uu.arnason.blockworld.U;
import nl.uu.arnason.blockworld.model.Grid;
import nl.uu.arnason.blockworld.model.agent.Triggers.GridUpdateTrigger;
import oo2apl.agent.Context;
import oo2apl.agent.ExternalProcessToAgentInterface;

import java.util.Observable;
import java.util.Observer;

/**
 * An agent has a sensor to know about changes in the Grid
 */
public class Sensor implements Context, Observer {

    private ExternalProcessToAgentInterface agent;

    public Sensor() {

    }

    public void setAgent(ExternalProcessToAgentInterface agent) {
        this.agent = agent;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg == null)  {
            return;
        }
        else if(arg instanceof Grid) {
            // The agent only knows his own position in the grid

            GridUpdateTrigger gridUpdateTrigger = new GridUpdateTrigger((Grid) arg);
            agent.addExternalTrigger(gridUpdateTrigger);
        }
    }
}
