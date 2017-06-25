package nl.uu.arnason.blockworld.model.agent;

import nl.uu.arnason.blockworld.U;
import nl.uu.arnason.blockworld.model.agent.PlanSchemes.DestinationGoalPlanExecutionErrorPlanScheme;
import nl.uu.arnason.blockworld.model.agent.PlanSchemes.DestinationGoalPlanScheme;
import nl.uu.arnason.blockworld.model.agent.PlanSchemes.GoalUpdateTriggerPlanScheme;
import nl.uu.arnason.blockworld.model.agent.PlanSchemes.GridUpdateTriggerPlanScheme;
import oo2apl.agent.PlanToAgentInterface;
import oo2apl.plan.Plan;
import oo2apl.plan.PlanExecutionError;
import oo2apl.plan.builtin.RunOncePlan;

/**
 * Builds the agent
 */
public final class GridAgentBuilder extends oo2apl.agent.AgentBuilder {
    private final Plan myInitPlan = new RunOncePlan(){
        public void executeOnce(PlanToAgentInterface planInterface)
                throws PlanExecutionError {
            BeliefBase model = planInterface.getContext(BeliefBase.class);
            //use context for sth
            planInterface.getContext(Actuator.class).moveAgentBy(0,0);
        }
    };

    public GridAgentBuilder(BeliefBase model, GoalBase goalBase, Sensor sensor, Actuator actuator){

        // connect the agent to it's resources
        addContext(model);
        addContext(sensor);
        addContext(actuator);
        addContext(goalBase);
        addContext(new EState());

        // give the agent plans
        addInitialPlan(this.myInitPlan);
        addExternalTriggerPlanScheme(new GridUpdateTriggerPlanScheme());
        addExternalTriggerPlanScheme(new GoalUpdateTriggerPlanScheme());
        addGoalPlanScheme(new DestinationGoalPlanScheme());
        addInternalTriggerPlanScheme(new DestinationGoalPlanExecutionErrorPlanScheme());
    }

}

