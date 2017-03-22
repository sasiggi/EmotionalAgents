package nl.uu.arnason.blockworld.model.agent.PlanSchemes;

import nl.uu.arnason.blockworld.U;
import nl.uu.arnason.blockworld.model.agent.Actuator;
import nl.uu.arnason.blockworld.model.agent.Triggers.DestinationGoal;
import nl.uu.arnason.blockworld.model.agent.Triggers.DestinationGoalPlanExecutionError;
import oo2apl.agent.AgentContextInterface;
import oo2apl.agent.PlanToAgentInterface;
import oo2apl.agent.Trigger;
import oo2apl.plan.Plan;
import oo2apl.plan.PlanExecutionError;
import oo2apl.plan.PlanScheme;
import oo2apl.plan.builtin.RunOncePlan;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by siggi on 22-Mar-17.
 */
public class DestinationGoalPlanExecutionErrorPlanScheme  implements PlanScheme {

    public Plan instantiate(final Trigger trigger, final AgentContextInterface contextInterface){
        if(trigger instanceof DestinationGoalPlanExecutionError){
            return new DestinationGoalPlanExecutionErrorPlan(((DestinationGoalPlanExecutionError) trigger).getDestinationGoal());
        }
        return null;
    }

    public final class DestinationGoalPlanExecutionErrorPlan extends RunOncePlan {

        private DestinationGoal destinationGoal;

        public DestinationGoalPlanExecutionErrorPlan(DestinationGoal destinationGoal) {
            this.destinationGoal = destinationGoal;
        }

        @Override
        public void executeOnce(PlanToAgentInterface planInterface) throws PlanExecutionError {
            U.p("makePlanExecutionErrorPlanScheme");
            int moveX = ThreadLocalRandom.current().nextInt(0, 1 + 1);
            int moveY = (moveX + 1)%2;
            if (!planInterface.getContext(Actuator.class).moveAgentBy(moveX, moveY)) {
                // if action fails
                //TODO: get emotional? return another plan
                destinationGoal.setEmotion(DestinationGoal.Emotion.SAD);
            } else {
                //get more hopeful
            }
        }
    }
}
