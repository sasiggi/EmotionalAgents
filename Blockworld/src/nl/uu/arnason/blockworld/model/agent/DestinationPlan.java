package nl.uu.arnason.blockworld.model.agent;

import oo2apl.agent.PlanToAgentInterface;
import oo2apl.plan.PlanExecutionError;
import oo2apl.plan.builtin.RunOncePlan;

/**
 * Created by siggi on 15-Mar-17.
 */
public class DestinationPlan extends RunOncePlan {

    private int x;
    private int y;

    public DestinationPlan(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void executeOnce(PlanToAgentInterface planInterface) throws PlanExecutionError {
        planInterface.adoptGoal(new DestinationGoal(x,y));
    }
}
