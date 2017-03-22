package nl.uu.arnason.blockworld.model.agent.PlanSchemes;

import nl.uu.arnason.blockworld.U;
import nl.uu.arnason.blockworld.model.Grid;
import nl.uu.arnason.blockworld.model.agent.BeliefBase;
import nl.uu.arnason.blockworld.model.agent.Triggers.GridUpdateTrigger;
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
public class GridUpdateTriggerPlanScheme implements PlanScheme {

    public Plan instantiate(final Trigger trigger, final AgentContextInterface contextInterface){
        if(trigger instanceof GridUpdateTrigger){
            return new GridUpdateTriggerPlanScheme.GridUpdateTriggerPlan(((GridUpdateTrigger) trigger).getGrid());
        }
        return null;
    }

    public final class GridUpdateTriggerPlan extends RunOncePlan {

        private Grid modelGrid;

        public GridUpdateTriggerPlan(Grid modelGrid) {
            this.modelGrid = modelGrid;
        }

        @Override
        public void executeOnce(PlanToAgentInterface planInterface) throws PlanExecutionError {
            // Obtain the contexts that are needed in this plan
            BeliefBase beliefBase = planInterface.getContext(BeliefBase.class);

            // update the belief base grid according to the triggered change from the model
            U.p("Model update triggered");
            Grid beliefGrid = beliefBase.getGrid();
            beliefGrid.makeLike(modelGrid);
        }
    }
}
