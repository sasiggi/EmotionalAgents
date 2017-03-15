package nl.uu.arnason.blockworld.view;

import javax.swing.*;

/**
 * Created by siggi on 13-Mar-17.
 */
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
