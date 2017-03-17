package nl.uu.arnason.blockworld.model;

import java.util.ArrayList;

/**
 * Created by siggi on 17-Mar-17.
 */
public class GoalList extends ArrayList<GoalList.GoalPoint> {

    public GoalList(int initialCapacity) {
        super(initialCapacity);
    }

    public boolean hasGoal(int x, int y) {
        return contains(new GoalPoint(x,y));
    }

    public boolean addGoal(int x, int y) {
        GoalPoint point = new GoalPoint(x,y);
        if(!contains(point)) {
            add(point);
            return true;
        } else
            return false;
    }

    public boolean removeGoal(int x, int y) {
        GoalPoint point = new GoalPoint(x,y);
        if(contains(point)) {
            remove(point);
            return true;
        } else
            return false;
    }

    public class GoalPoint {

        private int x = 0;
        private int y = 0;



        public GoalPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void setLocation(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof GoalPoint) {
                GoalPoint p2 = (GoalPoint) obj;
                return p2.x == x && p2.y == y;
            } else
                return false;
        }
    }
}
