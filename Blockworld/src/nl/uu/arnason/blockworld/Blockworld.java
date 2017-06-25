package nl.uu.arnason.blockworld;

import nl.uu.arnason.blockworld.controller.GoalController;
import nl.uu.arnason.blockworld.controller.GridController;
import nl.uu.arnason.blockworld.model.Grid;
import nl.uu.arnason.blockworld.model.Model;
import nl.uu.arnason.blockworld.model.agent.*;
import nl.uu.arnason.blockworld.view.MainWindow;
import oo2apl.agent.ExternalProcessToAgentInterface;
import oo2apl.defaults.messenger.DefaultMessenger;
import oo2apl.platform.AdminToPlatformInterface;
import oo2apl.platform.Platform;

import javax.swing.*;

/**
 * Contains the MVC model, including the agents
 */
public class Blockworld {

	private final MainWindow view;	//Highest abstraction of the View
	private Model realWorld;	//Highest abstraction of the model. This is the real world.
	private GridController gridController;	//The Controller for the grid
	private GoalController goalController;	//The Controller for the list of goals
	private BeliefBase beliefBase;
	private GoalBase goalBase;

	private AdminToPlatformInterface adminInterface;
	private ExternalProcessToAgentInterface agent;

	private int height;
	private int width;

	/**
	 * Set up the mvc world.
	 * The model has an agent which has a BeliefBase and a GoalBase, and the real world Grid.
	 * The agent can actuate on and sense the real world Grid with an Actuator and a Sensor.
	 * The view is composed of a GridView and a GoalView.
	 * The view and the model communicate through GridController and GoalController.
	 */
	public Blockworld() {
	    this.height = 8;
	    this.width = 8;

	    // Create a new world model and the UI view
	    this.realWorld = new Model(height,width);
	    this.view = new MainWindow(height,width);

	    // Connect the model to the view with controllers:
	    this.gridController = new GridController();
	    this.goalController = new GoalController();
	    //Controller observes changes in the model:
	    //Make the gridController observe updates in gridModel
        realWorld.getGrid().addObserver(gridController);
        //Make the model react to commands from the view
        gridController.addModel(realWorld);
        gridController.addView(view);
        view.addGridController(gridController);

        // AGENT IMPLEMENTATION:
		// Create the platform. Returns an interface to the platform to register new factories and create new agents
		adminInterface = Platform.newPlatform(1, new DefaultMessenger());
		// Create a new agent. The platform will return an interface to the agent.
		// The agent has a sensor that listens for changes in the world, an actuator to act on it and an inner
		// model that represents it's beliefs and goals
		beliefBase = new BeliefBase();
		beliefBase.setGrid(new Grid(height,width));
		goalBase = new GoalBase();
		realWorld.setGoalBase(goalBase);
		realWorld.getGoalBase().addObserver(goalController);
		goalController.addGoalBase(goalBase);
		goalController.addView(view);
		view.addGoalController(goalController);
		realWorld.getGoalBase().addObserver(gridController);
		Sensor sensor = new Sensor();
		realWorld.getGrid().addObserver(sensor);
		realWorld.addObserver(sensor);	//TODO: decide who listens to what
		Actuator actuator = new Actuator(realWorld);
		agent = adminInterface.newAgent(new GridAgentBuilder(beliefBase, goalBase, sensor, actuator));
		sensor.setAgent(agent);
		goalBase.setAgent(agent);

	}

	public final JComponent getGui() {
		return view;
	}

	public void shutDownAgents(){
		adminInterface.haltPlatform();
	}

}