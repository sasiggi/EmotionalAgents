package oo2apl.defaults.deliberationsteps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nl.uu.arnason.blockworld.model.agent.EState;
import nl.uu.arnason.blockworld.model.agent.Triggers.EGoal;
import oo2apl.agent.DeliberationStepToAgentInterface;
import oo2apl.agent.Trigger;
import oo2apl.deliberation.DeliberationStepException;
import oo2apl.plan.PlanScheme;
/**
 * Step that applies the goal plan schemes to the current goals.
 * @author Bas Testerink
 */

public final class ApplyGoalPlanSchemes extends DefaultDeliberationStep { 
	
	public  ApplyGoalPlanSchemes(final DeliberationStepToAgentInterface deliberationInterface){
		super(deliberationInterface);
	}
	
	/** First clears all achieved goals and then grabs the goals and goal plan schemes and tries to apply the plan schemes. */
	public final void execute() throws DeliberationStepException{
		super.deliberationInterface.clearAchievedGoals();

		List<? extends Trigger> triggers = super.deliberationInterface.getGoals();

		//@Sigurdur Arnason: apply the coping strategies
		applyCopingStrategies(triggers);

		super.applyTriggerInterceptors(triggers, super.deliberationInterface.getGoalInterceptors());
		List<PlanScheme> planSchemes = super.deliberationInterface.getGoalPlanSchemes();
		super.applyPlanSchemes(triggers, planSchemes);
	}

	//@Sigurdur Arnason:
	private void applyCopingStrategies(List<? extends Trigger> triggers) {
		// The only case where no EGoal is hopeFearful is if there is no neutral EGoal to make hopeFearful
		boolean hopeFearfulEGoal = false; // false until we know that there is at least one EGoal that is hopeFearful
		boolean neutralEGoal = false; // false until we know that there is at least one EGoal that is neutral
		// if there is no active EGoal then we try to find a neutral EGoal and activate it by making it hopeFearful
		for(Trigger trigger : triggers) {
			if(trigger instanceof EGoal) {
				if(((EGoal) trigger).getEState().isHopeFearful())
					hopeFearfulEGoal = true;
				if(((EGoal) trigger).getEState().isNeutral())
					neutralEGoal = true;
			}
		}
		if(!hopeFearfulEGoal && neutralEGoal)
		for(Trigger trigger : triggers) {
			if(trigger instanceof EGoal) {
				if(((EGoal) trigger).getEState().isNeutral()) {
					((EGoal) trigger).setEmotion(EState.Emotion.HOPE_FEAR);
					break;
				}
			}
		}
	}

} 