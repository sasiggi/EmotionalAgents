package nl.uu.arnason.blockworld.model.agent.PlanSchemes;

import nl.uu.arnason.blockworld.U;
import nl.uu.arnason.blockworld.model.agent.Triggers.DestinationGoal;
import nl.uu.arnason.blockworld.model.agent.Actuator;
import nl.uu.arnason.blockworld.model.agent.BeliefBase;
import nl.uu.arnason.blockworld.model.agent.Triggers.DestinationGoalPlanExecutionError;
import oo2apl.agent.AgentContextInterface;
import oo2apl.agent.PlanToAgentInterface;
import oo2apl.agent.Trigger;
import oo2apl.plan.Plan;
import oo2apl.plan.PlanExecutionError;
import oo2apl.plan.PlanScheme;
import oo2apl.plan.builtin.RunOncePlan;

/**
 * The main plan to reach the destination.
 * Try to move horizontally towards the destination. If that fails or is not applicable then try to move vertivally.
 * If both fail then throw DestinationGoalPlanExecutionError
 */
public class DestinationGoalPlanScheme implements PlanScheme {

    public Plan instantiate(final Trigger trigger, final AgentContextInterface contextInterface){
        if(trigger instanceof DestinationGoal){
            return new DestinationGoalExternalTriggerPlan((DestinationGoal) trigger);
        }
        return null;
    }

    public final class DestinationGoalExternalTriggerPlan extends RunOncePlan{

        DestinationGoal destinationGoal;

        public DestinationGoalExternalTriggerPlan(DestinationGoal destinationGoal) {
            this.destinationGoal = destinationGoal;
        }

        public final void executeOnce(final PlanToAgentInterface planInterface)
                throws PlanExecutionError {
            BeliefBase beliefBase = planInterface.getContext(BeliefBase.class);
            // move towards destination
            int dx = destinationGoal.getX();
            int dy = destinationGoal.getY();
            int cx = beliefBase.getGrid().getAgentPosX();
            int cy = beliefBase.getGrid().getAgentPosY();
            int moveX = 0;
            int moveY = 0;

            if (dx > cx)
                moveX = 1;
            else if (dx < cx)
                moveX = -1;

            if (dy > cy)
                moveY = 1;
            else if (dy < cy)
                moveY = -1;

            if(moveX != 0) {
                if (planInterface.getContext(Actuator.class).moveAgentBy(moveX, 0)) {
                    return;
                }
            }
            if(moveY != 0) {
                if (planInterface.getContext(Actuator.class).moveAgentBy(0, moveY)) {
                    return;
                }
            }
            // If the agent moved successfully neither vertically nor horizontally we throw an error and resort to the backup plan
            U.p("DestinationGoalPlanScheme: Obstacle for plan");
            destinationGoal.onFailed();
            throw new DestinationGoalPlanExecutionError(destinationGoal);
        }
    }
}
