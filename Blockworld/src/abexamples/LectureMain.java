package abexamples;
import oo2apl.agent.ExternalProcessToAgentInterface;
import oo2apl.defaults.messenger.DefaultMessenger;
import oo2apl.platform.AdminToPlatformInterface;
import oo2apl.platform.Platform;

public final class LectureMain {
	public final static void main(final String[] args){ 
		AdminToPlatformInterface platform = Platform.newPlatform(1, new DefaultMessenger());
		
		ExternalProcessToAgentInterface agent = platform.newAgent(new HelloWorldAgentBuilder());
		for(int i = 0; i < 10000; i++)agent.addExternalTrigger(new SayHelloTrigger("Mehdi"));
	}
}
