package nl.uu.arnason.blockworld.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The view showing the grid where the agent moves.
 */
public class GridView extends JPanel{

	private JButton[][] gridSquares;
	private int height;
	private int width;
	private ImageIcon greyXIcon;
	private ImageIcon blueXIcon;
	private ImageIcon redXIcon;
	private ImageIcon blackXIcon;
	private ImageIcon greenXIcon;
	private ImageIcon agentIcon;
	private ImageIcon blankIcon;

	public GridView(int height, int width) {
		super(new GridLayout(height, width));
		this.height = height;
		this.width = width;
		this.gridSquares = new JButton[height][width];

		setBorder(new LineBorder(Color.BLACK));

		initializeIcons();

		// create the squares
		Insets buttonMargin = new Insets(0,0,0,0);
		for (int ii = 0; ii < height; ii++) {
			for (int jj = 0; jj < width; jj++) {
				GridBlock b = new GridBlock();
				b.setPosX(jj);
				b.setPosY(ii);
				b.setMargin(buttonMargin);
				setBackgroundEmpty(b);
				gridSquares[ii][jj] = b;
			}
		}

		for (int ii = 0; ii < height; ii++) {
			for (int jj = 0; jj < width; jj++) {
				add(gridSquares[ii][jj]);
			}
		}
	}

	public void setBackgroundHappyGoal(JButton btn) {
				btn.setIcon(greenXIcon);
	}
	public void setBackgroundSadGoal(JButton btn) {
		btn.setIcon(redXIcon);
	}
	public void setBackgroundNeutralGoal(JButton btn) {
		btn.setIcon(greyXIcon);
	}
	public void setBackgroundHopeFearfulGoal(JButton btn) {
		btn.setIcon(blackXIcon);
	}
	public void setBackgroundAgent(JButton btn) {
		btn.setIcon(agentIcon);
	}
	public void setBackgroundEmpty(JButton btn) {
		btn.setIcon(blankIcon);
		btn.setBackground(Color.WHITE);
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

	private void initializeIcons() {
		File imgBlueX = new File("./res/img/blue_x.png");
		File imgRedX = new File("./res/img/red_x.png");
		File imgGreyX = new File("./res/img/grey_x.png");
		File imgBlackX = new File("./res/img/black_x.png");
		File imgGreenX = new File("./res/img/green_x.png");
		File imgAgent = new File("./res/img/face.png");
		try {
			blueXIcon = new ImageIcon(ImageIO.read(imgBlueX));
			blackXIcon = new ImageIcon(ImageIO.read(imgBlackX));
			redXIcon = new ImageIcon(ImageIO.read(imgRedX));
			greyXIcon = new ImageIcon(ImageIO.read(imgGreyX));
			greenXIcon = new ImageIcon(ImageIO.read(imgGreenX));
			agentIcon = new ImageIcon(ImageIO.read(imgAgent));
		} catch (IOException e) {
			e.printStackTrace();
		}
		blankIcon = new ImageIcon(
				new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
	}


	public int getGridHeight() {
		return height;
	}

	public int getGridWidth() {
		return width;
	}

	public class GridBlock extends JButton {
		int posX;
		int posY;

		public int getPosX() {
			return posX;
		}

		public void setPosX(int posX) {
			this.posX = posX;
		}

		public int getPosY() {
			return posY;
		}

		public void setPosY(int posY) {
			this.posY = posY;
		}
	}
}
