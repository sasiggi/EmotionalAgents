package nl.uu.arnason.blockworld.model.agent.PlanSchemes;

import nl.uu.arnason.blockworld.model.agent.Triggers.DestinationGoal;
import nl.uu.arnason.blockworld.model.agent.Triggers.GoalUpdateTrigger;
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
public class GoalUpdateTriggerPlanScheme implements PlanScheme {

    public Plan instantiate(final Trigger trigger, final AgentContextInterface contextInterface){
        if(trigger instanceof GoalUpdateTrigger){
            return new GoalUpdateTriggerPlan(((GoalUpdateTrigger) trigger).getDestinationGoal());
        }
        return null;
    }

    public final class GoalUpdateTriggerPlan extends RunOncePlan {

        private DestinationGoal destinationGoal;

        public GoalUpdateTriggerPlan(DestinationGoal destinationGoal) {
            this.destinationGoal = destinationGoal;
        }

        @Override
        public void executeOnce(PlanToAgentInterface planInterface) throws PlanExecutionError {
            planInterface.adoptGoal(destinationGoal);
        }
    }
}
