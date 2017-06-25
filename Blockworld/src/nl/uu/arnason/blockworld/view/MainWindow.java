package nl.uu.arnason.blockworld.view;

import nl.uu.arnason.blockworld.U;
import nl.uu.arnason.blockworld.controller.GoalController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.Observable;

/**
 *
 */
public class MainWindow extends JPanel implements java.util.Observer {

    private GridView gridView;
    private GoalView goalView;
//    private final JLabel message = new JLabel(
//            "Press start to begin");

    private int height;
    private int width;

    public MainWindow(int height, int width){
        super(new BorderLayout(3, 3));
        this.height = height;
        this.width = width;


        // set up the main GUI
        setBorder(new EmptyBorder(5, 5, 5, 5));
//        JToolBar tools = new JToolBar();
//        tools.setFloatable(false);
//        add(tools, BorderLayout.PAGE_START);
//        tools.add(new JButton("New")); // TODO - add functionality!
//        tools.add(new JButton("Save")); // TODO - add functionality!
//        tools.add(new JButton("Restore")); // TODO - add functionality!
//        tools.addSeparator();
//        tools.add(new JButton("Resign")); // TODO - add functionality!
//        tools.addSeparator();
//        tools.add(message);

        add(new JLabel("Left click to add a wall, right click to add a goal, left click a goal in the list to remove it."), BorderLayout.NORTH);

        gridView = new GridView(height,width);
        gridView.setBorder(new LineBorder(Color.BLACK));
        add(gridView,BorderLayout.WEST);

        goalView = new GoalView();
        goalView.setBorder(new LineBorder(Color.BLACK));
        add(goalView,BorderLayout.EAST);


    }

    public void addGridController(MouseListener controller){
        gridView.setController(controller);
    }

    public void addGoalController(GoalController controller) {
        goalView.setController(controller);
    }

    public GridView getGridView() {
        return gridView;
    }

    public GoalView getGoalView() {
        return goalView;
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
