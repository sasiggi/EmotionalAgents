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
 * Created by siggi on 22-Mar-17.
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

            if(moveX == 0) {
                if (dy > cy)
                    moveY = 1;
                else if (dy < cy)
                    moveY = -1;
            }

            if (!planInterface.getContext(Actuator.class).moveAgentBy(moveX, moveY)) {
                // if action fails
                //TODO: get emotional? return another plan
                U.p("Obstacle for plan");
                throw new DestinationGoalPlanExecutionError(destinationGoal);
            } else {
                //get more hopeful
            }
        }
    }
}
