package nl.uu.arnason.blockworld.model.agent;

/**
 * Holds information about an agent's emotional state.
 */
public class EState implements oo2apl.agent.Context{

    private Emotion emotion;

    public enum Emotion {
        JOY, DISTRESS, NEUTRAL, HOPE_FEAR
    }

    public EState() {
        emotion = Emotion.NEUTRAL;
    }


    public String getDescription() {
        if (emotion.equals(Emotion.JOY))
            return "Joy";
        if (emotion.equals(Emotion.DISTRESS))
            return "Distress";
        if (emotion.equals(Emotion.NEUTRAL))
            return "Neutral";
        if (emotion.equals(Emotion.HOPE_FEAR))
            return "Hope/Fear";
        return "";
    }

    public void setEmotion(Emotion emotion) {
        if (emotion.equals(Emotion.JOY))
            makeHappy();
        if (emotion.equals(Emotion.DISTRESS))
            makeSad();
        if (emotion.equals(Emotion.NEUTRAL))
            makeNeutral();
        if (emotion.equals(Emotion.HOPE_FEAR))
            makeHopeFearful();
    }


    public void makeSad() {
        emotion = Emotion.DISTRESS;
    }
    public void makeHappy() {
        emotion = Emotion.JOY;
    }
    public void makeNeutral() {
        emotion = Emotion.NEUTRAL;
    }
    public void makeHopeFearful() {
        emotion = Emotion.HOPE_FEAR;
    }

    public boolean isHopeFearful() {
        return emotion.equals(Emotion.HOPE_FEAR);
    }
    public boolean isNeutral() {
        return emotion.equals(Emotion.NEUTRAL);
    }
    public boolean isHappy() {
        return emotion.equals(Emotion.JOY);
    }
    public boolean isSad() {
        return emotion.equals(Emotion.DISTRESS);
    }

}
