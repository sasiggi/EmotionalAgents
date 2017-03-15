package abexamples;

import oo2apl.agent.Context;
public final class HelloContext implements Context{
	private final String greeting;
	public HelloContext(final String greeting){
		this.greeting = greeting;
	}
	public final void greet(String person){
		System.out.println(this.greeting+person);
	}
}
