package abexamples;
import oo2apl.agent.AgentContextInterface;
import oo2apl.agent.Trigger;
import oo2apl.plan.Plan;
import oo2apl.plan.PlanScheme;
public class HelloPlanScheme implements PlanScheme {
	public Plan instantiate(Trigger trigger, AgentContextInterface contextInterface){
		if(trigger instanceof SayHelloTrigger){
			SayHelloTrigger sayHello = (SayHelloTrigger) trigger;
			return new SayHelloPlan(sayHello.target);
		}
		return Plan.UNINSTANTIATED;
	}
}
