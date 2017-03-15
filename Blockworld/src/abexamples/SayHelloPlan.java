package abexamples;
import oo2apl.agent.PlanToAgentInterface;
import oo2apl.plan.PlanExecutionError;
import oo2apl.plan.builtin.RunOncePlan;

public final class SayHelloPlan extends RunOncePlan {  
	private final String target; 
	public SayHelloPlan(final String target){ 
		this.target = target;
	}
	public void executeOnce(PlanToAgentInterface planInterface) throws PlanExecutionError {
		planInterface.getContext(HelloContext.class)
			.greet(this.target);
	}
}
