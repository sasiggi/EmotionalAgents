package abexamples;
import oo2apl.agent.Trigger;
public final class SayHelloTrigger implements Trigger {
	public final String target;
	public SayHelloTrigger(final String target){
		this.target = target;
	}
}
