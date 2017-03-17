package nl.uu.arnason.blockworld.view;

import nl.uu.arnason.blockworld.U;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Observable;

/**
 * Created by siggi on 13-Mar-17.
 */
public class MainWindow extends JPanel implements java.util.Observer {

    private Grid grid;
    private final JLabel message = new JLabel(
            "Press start to begin");

    private int height;
    private int width;

    public MainWindow(int height, int width){
        super(new BorderLayout(3, 3));
        this.height = height;
        this.width = width;

        // set up the main GUI
        setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        add(tools, BorderLayout.PAGE_START);
        tools.add(new JButton("New")); // TODO - add functionality!
        tools.add(new JButton("Save")); // TODO - add functionality!
        tools.add(new JButton("Restore")); // TODO - add functionality!
        tools.addSeparator();
        tools.add(new JButton("Resign")); // TODO - add functionality!
        tools.addSeparator();
        tools.add(message);

        add(new JLabel("?"), BorderLayout.NORTH);

        grid = new Grid(height,width);
        grid.setBorder(new LineBorder(Color.BLACK));
        add(grid,BorderLayout.WEST);

        GoalView goalView = new GoalView();
        goalView.setBorder(new LineBorder(Color.BLACK));
        add(goalView,BorderLayout.EAST);


    }

    public void addGridController(MouseListener controller){
        grid.setController(controller);
    }

    public Grid getGrid() {
        return grid;
    }

    @Override
    public void update(Observable o, Object arg) {
        U.p("update in view mainWindow");
    }
}
