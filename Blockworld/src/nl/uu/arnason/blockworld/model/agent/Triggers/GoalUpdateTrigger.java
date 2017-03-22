package nl.uu.arnason.blockworld.model.agent.Triggers;

import oo2apl.agent.Trigger;

/**
 * Created by siggi on 16-Mar-17.
 */
public final class GoalUpdateTrigger implements Trigger {

    private DestinationGoal destinationGoal;

    public GoalUpdateTrigger(DestinationGoal goalPoint) {
        this.destinationGoal = goalPoint;
    }

    public DestinationGoal getDestinationGoal() {
        return destinationGoal;
    }

    public void setDestinationGoal(DestinationGoal destinationGoal) {
        this.destinationGoal = destinationGoal;
    }
}
