package nl.uu.arnason.blockworld;

import nl.uu.arnason.blockworld.controller.GridController;
import nl.uu.arnason.blockworld.model.Model;
import nl.uu.arnason.blockworld.model.agent.AgentModel;
import nl.uu.arnason.blockworld.model.agent.ModelUpdateTrigger;
import nl.uu.arnason.blockworld.model.agent.GridAgentBuilder;
import nl.uu.arnason.blockworld.view.MainWindow;
import oo2apl.agent.ExternalProcessToAgentInterface;
import oo2apl.defaults.messenger.DefaultMessenger;
import oo2apl.platform.AdminToPlatformInterface;
import oo2apl.platform.Platform;

import javax.swing.*;

public class Blockworld {

	private final MainWindow view;
	private Model model;
	private AgentModel agentModel;
	private GridController controller;

	private AdminToPlatformInterface adminInterface;
	private ExternalProcessToAgentInterface agent;

	private int height;
	private int width;

	public Blockworld() {
	    this.height = 10;
	    this.width = 15;
	    this.model = new Model(height,width);
	    this.view = new MainWindow(height,width);
	    this.controller = new GridController();

        model.getGrid().addObserver(view.getGrid());

        controller.addModel(model);
        controller.addView(view);
//        controller.initModel(start_value);

        view.addGridController(controller);

        // AGENT IMPLEMENTATION:
		// Create the platform. Returns an interface to the platform to register new factories and create new agents
		adminInterface = Platform.newPlatform(1, new DefaultMessenger());

		//initialize the model for the agent
		agentModel = new AgentModel();
		agentModel.setGrid(model.getGrid());
		agentModel.setRealWorld(model.getGrid());

		// Create a new agent. The platform will return an interface to the agent so that we can send it triggers
		agent = adminInterface.newAgent(new GridAgentBuilder(agentModel));

		// Our external process to interact with the agent
		// TODO: connect to gui here? merge this with Blockworld class? and make an ExternalTrigger for a controller
		// This is how you interact with an agent. You send external triggers through the ExternalProcessToAgentInterface
		ModelUpdateTrigger modelUpdateTrigger = new ModelUpdateTrigger();
		model.getGrid().addObserver(modelUpdateTrigger);
		agent.addExternalTrigger(modelUpdateTrigger);

	}


	public final JComponent getGui() {
		return view;
	}

    public Model getModel() {
        return model;
    }

    // not sure about this...
	@Override
	protected void finalize() throws Throwable {
		adminInterface.haltPlatform();
		super.finalize();
	}





}