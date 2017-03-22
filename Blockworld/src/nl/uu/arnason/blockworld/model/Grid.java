package nl.uu.arnason.blockworld.model;

import nl.uu.arnason.blockworld.U;

/**
 * Created by siggi on 13-Mar-17.
 */
public class Grid extends java.util.Observable {

    private int height;
    private int width;
    private Block[][] grid;
    private int agentPosX = 0;
    private int agentPosY = 0;

    public Grid(int height, int width) {
        this.height=height;
        this.width=width;
        this.grid = new Block[height][width];
        initializeGrid();
    }

    private void setBlockStatus(int x, int y, Block.Status status, boolean sendUpdate){
        // be careful about the difference between normal xy vs matrix yx
        grid[y][x].setStatus(status);

        if(sendUpdate) {
            setChanged();
            notifyObservers(this);
        }
    }

    public Block.Status getBlockStatus(int x, int y){
        // be careful about the difference between normal xy vs matrix yx
        return grid[y][x].getStatus();
    }

    public boolean hasWall(int x,int y) {
        return getBlockStatus(x,y).equals(Block.Status.WALL);
    }

    public void addWall(int x, int y){
        setBlockStatus(x,y,Block.Status.WALL, true);
    }
    public void removeWall(int x, int y){
        setBlockStatus(x,y,Block.Status.EMPTY, true);
    }

    private void moveAgentTo(int x, int y) throws ArrayIndexOutOfBoundsException {
        //use semaphores?
        setBlockStatus(agentPosX, agentPosY, Block.Status.EMPTY, true);
        agentPosX = x;
        agentPosY = y;
        setBlockStatus(agentPosX, agentPosY, Block.Status.AGENT, true);
        setChanged();
        notifyObservers(this);
    }

    public void moveAgentBy(int x, int y) throws ArrayIndexOutOfBoundsException{
        moveAgentTo(agentPosX + x, agentPosY + y);
    }

    @Override
    public void notifyObservers(Object arg) {
        U.p("notifyObservers in GridView: agentPos: "+((Grid) arg).getAgentPosX()+","+((Grid) arg).getAgentPosY());
        super.notifyObservers(arg);
    }

    public void initializeGrid() {
        for(int i=0; i<height; i++)  {
            for(int j=0; j<width; j++)  {
                grid[i][j] = new Block();
                setBlockStatus(j,i,Block.Status.EMPTY, false);
            }
        }
        setBlockStatus(agentPosX,agentPosY, Block.Status.AGENT, false);
    }

    public void makeLike(Grid toCopy) {
        for(int i=0; i<width; i++)  {
            for(int j=0; j<height; j++)  {
                setBlockStatus(i,j,toCopy.getBlockStatus(i,j), false);
            }
        }
        agentPosX = toCopy.getAgentPosX();
        agentPosY = toCopy.getAgentPosY();

    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getAgentPosX() {
        return agentPosX;
    }

    public void setAgentPosX(int agentPosX) {
        this.agentPosX = agentPosX;
    }

    public int getAgentPosY() {
        return agentPosY;
    }

    public void setAgentPosY(int agentPosY) {
        this.agentPosY = agentPosY;
    }


}

