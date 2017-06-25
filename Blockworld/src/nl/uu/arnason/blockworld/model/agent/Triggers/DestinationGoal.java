package nl.uu.arnason.blockworld.model.agent.Triggers;

import nl.uu.arnason.blockworld.U;
import nl.uu.arnason.blockworld.model.Grid;
import nl.uu.arnason.blockworld.model.agent.BeliefBase;
import nl.uu.arnason.blockworld.model.agent.EState;
import nl.uu.arnason.blockworld.model.agent.GoalBase;
import oo2apl.agent.AgentContextInterface;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * A goal to go to a destination. The agent gives up after a certain number of failed movements.
 * A DestinationGoal is sad if an action has failed too often to reach the goal, and happy when the goal is achieved.
 */
public class DestinationGoal extends EGoal {

    private static final AtomicInteger count = new AtomicInteger(1);    //Thread safe

    private GoalBase goalBase;

    private int goalId;
    private int x;
    private int y;
    private boolean cancelled = false;
    private int failCounter;
    private int maxFails;

    public DestinationGoal(int x, int y) {
        this.x = x;
        this.y = y;
        this.failCounter = 0;
        this.maxFails = 10;
        goalId = count.incrementAndGet();
    }

    @Override
    public boolean isAchieved(AgentContextInterface contextInterface) {
        BeliefBase beliefBase = contextInterface.getContext(BeliefBase.class);
        Grid grid = beliefBase.getGrid();
        GoalBase goalBase = contextInterface.getContext(GoalBase.class);
        if(( grid.getAgentPosX() == this.x && grid.getAgentPosY() == this.y )) {
            setEmotion(EState.Emotion.JOY);
            return true;
        } else
        // This is here to remove goals that were cancelled from the UI. It would be prettier to have a specific function to do this
        // that is implemented just like for achieved goals, but since for this project I want to show
        // how the framework is changed to incorporate emotions i keep it here for simplicity.
        if (cancelled) {
            return true;
        } else if (!goalBase.hasGoal(this)) {
            cancelled = true;
            return true;
        }
        else
            return false;
    }

    public void setGoalBase (GoalBase goalBase){
        this.goalBase = goalBase;
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

    @Override
    public void setEmotion(EState.Emotion emotion) {
        super.setEmotion(emotion);
        updateView();
    }

    /**
     * Run when an action fails.
     */
    public void onFailed() {
        failCounter++;
        if(maxFailuresReached()) {
            setEmotion(EState.Emotion.DISTRESS);
        }
        U.p("DestinationGoal.onFailed() failCounter="+failCounter+" , target:"+x+","+y);
        updateView();
    }

    public boolean maxFailuresReached() {
        return failCounter >= maxFails;
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
