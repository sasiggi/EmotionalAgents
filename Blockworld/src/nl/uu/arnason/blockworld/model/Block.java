package nl.uu.arnason.blockworld.model;

/**
 * A piece of the grid
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
