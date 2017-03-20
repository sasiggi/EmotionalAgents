package nl.uu.arnason.blockworld.model.agent;

import nl.uu.arnason.blockworld.U;
import nl.uu.arnason.blockworld.model.DestinationGoal;
import nl.uu.arnason.blockworld.model.Grid;
import oo2apl.agent.AgentContextInterface;
import oo2apl.agent.Context;
import oo2apl.agent.PlanToAgentInterface;
import oo2apl.agent.Trigger;
import oo2apl.plan.builtin.SubPlanInterface;

/**
 * Created by siggi on 17-Mar-17.
 */
public class PlanSchemeBase implements Context{

    /**
     * Update grid
     */
    public static SubPlanInterface makeGridUpdateTriggerPlanScheme(Trigger trigger, AgentContextInterface contextInterface) {
        if(trigger instanceof GridUpdateTrigger){
            GridUpdateTrigger gridUpdateTrigger = (GridUpdateTrigger) trigger;
            return (PlanToAgentInterface planInterface) -> {
                // Obtain the contexts that are needed in this plan
                BeliefBase beliefBase = planInterface.getContext(BeliefBase.class);

                // update the belief base grid according to the triggered change from the model
                U.p("Model update triggered");
                Grid beliefGrid = beliefBase.getGrid();
                beliefGrid.makeLike(gridUpdateTrigger.getGrid());
            };
        } else return SubPlanInterface.UNINSTANTIATED;
    }

    public static SubPlanInterface makeGoalUpdateTriggerPlanScheme(Trigger trigger, AgentContextInterface contextInterface) {
        if(trigger instanceof GoalUpdateTrigger){
            GoalUpdateTrigger goalUpdateTrigger = (GoalUpdateTrigger) trigger;
            return (PlanToAgentInterface planInterface) -> {
//                DestinationGoal destinationGoal = new DestinationGoal(goalUpdateTrigger.getDestinationGoal().getX(), goalUpdateTrigger.getDestinationGoal().getY());
                //keep track of goals
                planInterface.adoptGoal(goalUpdateTrigger.getDestinationGoal());
            };
        } else return SubPlanInterface.UNINSTANTIATED;
    }

    // should return a FunctionalPlanSchemeInterface
    public static SubPlanInterface destinationGoalPlanScheme(Trigger trigger, AgentContextInterface contextInterface) {
        if(trigger instanceof DestinationGoal){
            DestinationGoal destinationGoal = (DestinationGoal) trigger;
            return (PlanToAgentInterface planInterface) -> {
                // Obtain the contexts that are needed in this plan
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

                if (!planInterface.getContext(Actuator.class).moveAgentBy(moveX, moveY)) {
                    // if action fails
                    //TODO: get emotional? return another plan
                    U.p("Obstacle for plan");
                    GoalBase goalBase = planInterface.getContext(GoalBase.class);
                    goalBase.makeSad(destinationGoal);
                }
            };
        } else return SubPlanInterface.UNINSTANTIATED;
    }
}
