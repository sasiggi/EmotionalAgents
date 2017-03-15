package nl.uu.arnason.blockworld.model.agent;

import nl.uu.arnason.blockworld.U;
import nl.uu.arnason.blockworld.model.Grid;
import oo2apl.agent.AgentContextInterface;
import oo2apl.agent.PlanToAgentInterface;
import oo2apl.agent.Trigger;
import oo2apl.plan.Plan;
import oo2apl.plan.PlanExecutionError;
import oo2apl.plan.builtin.RunOncePlan;
import oo2apl.plan.builtin.SubPlanInterface;

/**
 * Created by siggi on 15-Mar-17.
 */
public final class GridAgentBuilder extends oo2apl.agent.AgentBuilder {
    private final Plan myInitPlan = new RunOncePlan(){
        public void executeOnce(PlanToAgentInterface planInterface)
                throws PlanExecutionError {
            AgentModel model = planInterface.getContext(AgentModel.class);
            //use context for sth
            model.moveRight();
            planInterface.adoptGoal(new DestinationGoal(5,5));
            U.p("InitPlan run");
        }
    };

    public GridAgentBuilder(AgentModel model){
        addContext(model);
        addInitialPlan(this.myInitPlan);
        addGoalPlanScheme(GridAgentBuilder::makeModelUpdateTriggerPlanScheme);
        addGoalPlanScheme(GridAgentBuilder::adoptDestinationGoalPlanScheme);
    }


    // should return a FunctionalPlanSchemeInterface
    private static final SubPlanInterface makeModelUpdateTriggerPlanScheme(Trigger trigger, AgentContextInterface contextInterface) {
            if(trigger instanceof ModelUpdateTrigger){
                ModelUpdateTrigger modelUpdateTrigger = (ModelUpdateTrigger) trigger;
                return (PlanToAgentInterface planInterface) -> {
                    // Obtain the contexts that are needed in this plan
                    AgentModel agentModel = planInterface.getContext(AgentModel.class);

                    // update the agent model according to the triggered change from the model
                    U.p("Updating the grid for agentModel");
                    agentModel.setGrid(modelUpdateTrigger.getGrid());
                };
            } else return SubPlanInterface.UNINSTANTIATED;
    }

    // should return a FunctionalPlanSchemeInterface
    private static final SubPlanInterface adoptDestinationGoalPlanScheme(Trigger trigger, AgentContextInterface contextInterface) {
        if(trigger instanceof DestinationGoal){
            DestinationGoal destinationGoal = (DestinationGoal) trigger;
            return (PlanToAgentInterface planInterface) -> {
                // Obtain the contexts that are needed in this plan
                AgentModel agentModel = planInterface.getContext(AgentModel.class);
                Grid realWorld = agentModel.getRealWorld();
                // move towards destination
                int dx = destinationGoal.getX();
                int dy = destinationGoal.getY();
                int cx = agentModel.getGrid().getAgentPosX();
                int cy = agentModel.getGrid().getAgentPosY();
                U.p("dx"+dx+" - dy"+dy+" - cx"+cx+" - cy"+cy);
                int moveX = 0;
                int moveY = 0;

                if(dx > cx)
                    moveX = 1;
                else if (dx < cx)
                    moveX = -1;

                if(dy > cy)
                    moveY = 1;
                else if (dy < cy)
                    moveY = -1;

                realWorld.moveAgentBy(moveX,moveY);
                // TODO: move in the real world, see how the goal is pursued, like fx from addGoalPlanScheme above
            };
        } else return SubPlanInterface.UNINSTANTIATED;
    }
}

