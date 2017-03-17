package nl.uu.arnason.blockworld.view;

import nl.uu.arnason.blockworld.U;
import nl.uu.arnason.blockworld.model.Block;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Observable;

/**
 * Created by siggi on 13-Mar-17.
 */
public class Grid extends JPanel{

	private JButton[][] gridSquares;
	private int height;
	private int width;

	public Grid(int height, int width) {
		super(new GridLayout(height, width));
		this.height = height;
		this.width = width;
		this.gridSquares = new JButton[height][width];

		setBorder(new LineBorder(Color.BLACK));

		// create the chess board squares
		Insets buttonMargin = new Insets(0,0,0,0);
		for (int ii = 0; ii < height; ii++) {
			for (int jj = 0; jj < width; jj++) {
				GridBlock b = new GridBlock();
				b.setPosX(jj);
				b.setPosY(ii);
				b.setMargin(buttonMargin);
				// our chess pieces are 64x64 px in size, so we'll
				// 'fill this in' using a transparent icon..
				ImageIcon icon = new ImageIcon(
						new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
				b.setIcon(icon);
				b.setBackground(Color.WHITE);
				gridSquares[ii][jj] = b;

			}
		}

		for (int ii = 0; ii < height; ii++) {
			for (int jj = 0; jj < width; jj++) {
				add(gridSquares[ii][jj]);

			}
		}
	}


    public void setController(MouseListener controller) {
        // set controller as actionListener for all buttons that need it
        for (int ii = 0; ii < height; ii++) {
            for (int jj = 0; jj < width; jj++) {
                gridSquares[ii][jj].addMouseListener(controller);
            }
        }
    }

    public JButton getGridSquare(int x, int y) {
		return gridSquares[y][x];
	}


	public int getGridHeight() {
		return height;
	}

	public int getGridWidth() {
		return width;
	}
}
