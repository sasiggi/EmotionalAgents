package nl.uu.arnason.blockworld.model.agent;

import nl.uu.arnason.blockworld.U;
import nl.uu.arnason.blockworld.model.GoalList;
import nl.uu.arnason.blockworld.model.Grid;
import oo2apl.agent.Context;
import oo2apl.agent.ExternalProcessToAgentInterface;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by siggi on 16-Mar-17.
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
        U.p("Sensor.update()");
        if(arg == null)  {
            U.p("Sensor.update() arg == null");
            return;
        }
        else if(arg instanceof Grid) {
            ModelUpdateTrigger modelUpdateTrigger = new ModelUpdateTrigger((Grid) arg);
            agent.addExternalTrigger(modelUpdateTrigger);
        }
        else if(arg instanceof GoalList.GoalPoint) {
            GoalUpdateTrigger goalUpdateTrigger = new GoalUpdateTrigger((GoalList.GoalPoint) arg);
            U.p("Sensor:GoalPoint: "+(goalUpdateTrigger.getGoalPoint().getX()+","+goalUpdateTrigger.getGoalPoint().getY()));
            agent.addExternalTrigger(goalUpdateTrigger);
        }
    }
}
