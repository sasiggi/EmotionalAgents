package nl.uu.arnason.blockworld.model.agent.Triggers;

import nl.uu.arnason.blockworld.model.agent.EState;
import oo2apl.agent.AgentContextInterface;
import oo2apl.agent.Goal;

/**
 * A type of goal that has emotions.
 */
public class EGoal extends Goal {


    private EState eState;

    public EGoal() {
        this.eState = new EState();
    }

    @Override
    public boolean isAchieved(AgentContextInterface contextInterface) {
        return false;
    }

    public EState getEState() {
        return eState;
    }

    public void setEmotion(EState.Emotion emotion) {
        eState.setEmotion(emotion);
    }

}
