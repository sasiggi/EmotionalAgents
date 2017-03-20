package nl.uu.arnason.blockworld.model.agent;

import nl.uu.arnason.blockworld.U;
import nl.uu.arnason.blockworld.model.DestinationGoal;
import oo2apl.agent.PlanToAgentInterface;
import oo2apl.plan.Plan;
import oo2apl.plan.PlanExecutionError;
import oo2apl.plan.builtin.RunOncePlan;

/**
 * Created by siggi on 15-Mar-17.
 */
public final class GridAgentBuilder extends oo2apl.agent.AgentBuilder {
    private final Plan myInitPlan = new RunOncePlan(){
        public void executeOnce(PlanToAgentInterface planInterface)
                throws PlanExecutionError {
            BeliefBase model = planInterface.getContext(BeliefBase.class);
            //use context for sth
            planInterface.getContext(Actuator.class).moveAgentBy(0,0);
//            planInterface.adoptGoal(new DestinationGoal(5,5));
            U.p("InitPlan run");
        }
    };

    public GridAgentBuilder(BeliefBase model, GoalBase goalBase, Sensor sensor, Actuator actuator){

        addContext(model);
        addContext(sensor);
        addContext(actuator);
        addContext(goalBase);
        addInitialPlan(this.myInitPlan);

        addExternalTriggerPlanScheme(PlanSchemeBase::makeGridUpdateTriggerPlanScheme);
        addExternalTriggerPlanScheme(PlanSchemeBase::makeGoalUpdateTriggerPlanScheme);
        addGoalPlanScheme(PlanSchemeBase::destinationGoalPlanScheme);
    }

}

