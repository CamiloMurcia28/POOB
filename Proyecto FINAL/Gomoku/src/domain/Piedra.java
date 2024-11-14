package domain;

import java.awt.Color;
import javax.swing.JButton;
/**
 * Write a description of class Piedra here.
 *
 * @author  Camilo Murcia Jeisson Casallas
 * @version (a version number or a date)
 */
public abstract class Piedra extends JButton{
    protected int posX;
    protected int posY;
    protected Color emptyPiedraColor = new Color(153,76,0);
    protected Color color;
    
    public Piedra(int posiX, int posiY) {
        super();
        this.posX = posiX;
        this.posY = posiY;
        this.color = emptyPiedraColor;
        this.setBackground(emptyPiedraColor);
    }

    public int getPositionX() {
        return posX;
    }

    public int getPositionY() {
        return posY;
    }
    
    public void ponerPiedraEncima(Color color){
        this.color = color;
        this.setBackground(color);
    }
    
    public void limpiarPiedra() {
        this.color = emptyPiedraColor;
        this.setBackground(emptyPiedraColor);
    }
    
    public Color getStoneColor() {
        return color;
    }
    public abstract int getPuntuacion();

}
