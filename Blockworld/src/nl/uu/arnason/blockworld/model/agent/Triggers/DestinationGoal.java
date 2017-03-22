package nl.uu.arnason.blockworld.model.agent.Triggers;

import nl.uu.arnason.blockworld.model.Grid;
import nl.uu.arnason.blockworld.model.agent.BeliefBase;
import nl.uu.arnason.blockworld.model.agent.GoalBase;
import oo2apl.agent.AgentContextInterface;
import oo2apl.agent.Goal;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by siggi on 15-Mar-17.
 */
public class DestinationGoal extends Goal {

    private static final AtomicInteger count = new AtomicInteger(1);    //Thread safe

    private GoalBase goalBase;

    private int goalId;
    private int x;
    private int y;
    private boolean cancelled = false;
    private Emotion emotion;

    public enum Emotion {
        HAPPY, SAD, NEUTRAL
    }

    public DestinationGoal(int x, int y) {
        this.x = x;
        this.y = y;
        this.emotion = Emotion.NEUTRAL;
        goalId = count.incrementAndGet();
    }

    @Override
    public boolean isAchieved(AgentContextInterface contextInterface) {
        BeliefBase beliefBase = contextInterface.getContext(BeliefBase.class);
        Grid grid = beliefBase.getGrid();
        GoalBase goalBase = contextInterface.getContext(GoalBase.class);
        if(( grid.getAgentPosX() == this.x && grid.getAgentPosY() == this.y )) {
            setEmotion(Emotion.HAPPY);
            return true;
        } else if (cancelled) {
            return true;
        } else if (!goalBase.hasGoal(this)) {
            cancel();
            return true;
        }
        else
            return false;
    }

    public void setGoalBase (GoalBase goalBase){
        this.goalBase = goalBase;
    }

    public GoalBase getGoalBase() {
        return goalBase;
    }

    public void updateView() {
        goalBase.notifyObservers();
    }


    @Override
    public boolean equals(Object obj) {
        if(obj instanceof DestinationGoal) {
            DestinationGoal g2 = (DestinationGoal) obj;
            return g2.getX() == x && g2.getY()== y;
        } else
            return false;
    }

    public int getGoalId() {
        return goalId;
    }

    public Emotion getEmotion() {
        return emotion;
    }

    public void setEmotion(Emotion emotion) {
        this.emotion = emotion;
        updateView();
    }

    public void cancel() {
        cancelled = true;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
