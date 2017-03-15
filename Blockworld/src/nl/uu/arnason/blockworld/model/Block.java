package nl.uu.arnason.blockworld.model;

/**
 * Created by siggi on 13-Mar-17.
 */
public class Block {
    public enum Status {
        WALL, AGENT, EMPTY
    }
    private Block.Status status;

    public Block.Status getStatus() {
        return status;
    }

    public void setStatus(Block.Status status) {
        this.status = status;
    }
}
