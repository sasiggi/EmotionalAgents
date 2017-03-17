package nl.uu.arnason.blockworld.model.agent;

import nl.uu.arnason.blockworld.model.GoalList;
import nl.uu.arnason.blockworld.model.Grid;
import oo2apl.agent.Trigger;

import java.util.Observable;

/**
 * Created by siggi on 16-Mar-17.
 */
public final class GoalUpdateTrigger implements Trigger {

    private GoalList.GoalPoint goalPoint;

    public GoalUpdateTrigger(GoalList.GoalPoint goalPoint) {
        this.goalPoint = goalPoint;
    }

    public GoalList.GoalPoint getGoalPoint() {
        return goalPoint;
    }

    public void setGoalPoint(GoalList.GoalPoint goalPoint) {
        this.goalPoint = goalPoint;
    }
}
