package abexamples;
import oo2apl.agent.AgentBuilder;
import oo2apl.agent.AgentContextInterface;
import oo2apl.agent.PlanToAgentInterface;
import oo2apl.agent.Trigger;
import oo2apl.plan.Plan;
import oo2apl.plan.PlanExecutionError;
import oo2apl.plan.builtin.RunOncePlan;
import oo2apl.plan.builtin.SubPlanInterface;
public final class HelloWorldAgentBuilder extends AgentBuilder {
	private final Plan myInitPlan = new RunOncePlan(){
		public void executeOnce(PlanToAgentInterface planInterface)
				throws PlanExecutionError {
			HelloContext ctx = planInterface.getContext(HelloContext.class);
			ctx.greet("Bas"); 
		}
	};
	
//	private final Plan myInitPlan = new RunOncePlan(){
//		public void executeOnce(PlanToAgentInterface planInterface)
//				throws PlanExecutionError {
//			System.out.println("I am "+planInterface.getAgentID());
//		}
//	};
	
	public HelloWorldAgentBuilder(){
		addContext(new HelloContext("Hi "));
		addInitialPlan(this.myInitPlan);
		//addExternalTriggerPlanScheme(new HelloPlanScheme());
		addExternalTriggerPlanScheme(HelloWorldAgentBuilder::addHelloPlanScheme);
	}
	
	private static final SubPlanInterface addHelloPlanScheme(final Trigger trigger, final AgentContextInterface contextInterface){
		if(trigger instanceof SayHelloTrigger){
			final SayHelloTrigger sayHello = (SayHelloTrigger) trigger;
			return (planInterface) -> {
				planInterface.getContext(HelloContext.class)
				.greet(sayHello.target);
			};
		} else return SubPlanInterface.UNINSTANTIATED;
	}
}
