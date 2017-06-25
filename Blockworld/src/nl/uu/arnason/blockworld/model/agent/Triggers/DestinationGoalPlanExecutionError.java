package nl.uu.arnason.blockworld.model.agent.Triggers;

import oo2apl.plan.PlanExecutionError;

/**
 *
 */
public class DestinationGoalPlanExecutionError extends PlanExecutionError {
    private DestinationGoal destinationGoal;

    public DestinationGoalPlanExecutionError(DestinationGoal destinationGoal) {
        this.destinationGoal = destinationGoal;
    }

    public DestinationGoal getDestinationGoal() {
        return destinationGoal;
    }
}
