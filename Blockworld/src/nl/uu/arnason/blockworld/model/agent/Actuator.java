package nl.uu.arnason.blockworld.model.agent;

import nl.uu.arnason.blockworld.model.Model;
import oo2apl.agent.Context;

/**
 * Created by siggi on 16-Mar-17.
 */
public class Actuator implements Context {

    private Model realWorld;

    public Actuator(Model realWorld) {
        this.realWorld = realWorld;
    }

    public boolean moveRight() {
        if(realWorld.getGrid().getAgentPosX() < realWorld.getGrid().getWidth()) {
            //try to move in real world/model, model change updates the agent
            return realWorld.getGrid().moveRight();
        }
        return false;
    }

    public void moveAgentBy(int x, int y) {
        realWorld.getGrid().moveAgentBy(x,y);
    }

}
