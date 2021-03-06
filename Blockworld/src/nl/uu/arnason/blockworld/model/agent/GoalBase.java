package nl.uu.arnason.blockworld.model.agent;

import nl.uu.arnason.blockworld.U;
import nl.uu.arnason.blockworld.model.agent.Triggers.DestinationGoal;
import nl.uu.arnason.blockworld.model.agent.Triggers.GoalUpdateTrigger;
import oo2apl.agent.Context;
import oo2apl.agent.ExternalProcessToAgentInterface;

import java.util.ArrayList;

/**
 * We keep references to the agent's goals. oo2apl does not offer an explicit way to manipulate an agent's
 * goals from outside it but this is added here so we can show and manipulate the goals in the GoalView.
 * Use this layer to add and remove goals for the agent.
 */
public class GoalBase extends java.util.Observable implements Context {


    private ArrayList<DestinationGoal> destinationGoals;

    private ExternalProcessToAgentInterface agent;

    public GoalBase() {
        this.destinationGoals = new ArrayList<>(10);

    }

    public int getNumberOfGoals() {
        return destinationGoals.size();
    }

    public boolean addGoal(int x, int y) {
        DestinationGoal goal = new DestinationGoal(x,y);
        goal.setGoalBase(this);
        if(destinationGoals.contains(goal))
            return false;
        if(destinationGoals.add(goal)) {
            GoalUpdateTrigger goalUpdateTrigger = new GoalUpdateTrigger(goal);
            agent.addExternalTrigger(goalUpdateTrigger);
            setChanged();
            notifyObservers(this);
            return true;
        } else
            return false;
    }

    public void removeGoal(int x, int y) {
        DestinationGoal goal = new DestinationGoal(x, y);
        // remove all goals for x,y. Should be just one.
        while(true) {
            if (!(destinationGoals.remove(goal))) break;
        }
        setChanged();
        notifyObservers(this);
    }

    public boolean hasGoal(DestinationGoal dgoal) {
        return destinationGoals.contains(dgoal);
    }

    public void setAgent(ExternalProcessToAgentInterface agent) {
        this.agent = agent;
    }

    public int[][] getTargetList() {
        removeAchievedGoals();
        int[][] list = new int[destinationGoals.size()][2];
        for (int i = 0; i < destinationGoals.size(); i++) {
            list[i] = new int[]{destinationGoals.get(i).getX(), destinationGoals.get(i).getY()};
        }
        return list;
    }

    public EState[] getEStateList() {
        EState[] list = new EState[destinationGoals.size()];
        for (int i = 0; i < destinationGoals.size(); i++) {
            list[i] = destinationGoals.get(i).getEState();
        }
        return list;
    }

    public void removeAchievedGoals() {
        for (int i = 0; i < destinationGoals.size(); i++) {
            DestinationGoal goal = destinationGoals.get(i);
            if(goal.isCancelled()) {
                removeGoal(goal.getX(), goal.getY());
            }
        }
    }

    @Override
    public void notifyObservers() {
        setChanged();
        super.notifyObservers(this);
    }
}
