package domain;

import javax.swing.JButton;
import java.awt.Color;

public abstract class Casilla extends JButton {

    private int posX;
    private int posY;
    protected Color emptyPiedraColor = new Color(153,76,0);
    private boolean ocupada;

    public Casilla(int posiX, int posiY){
        this.posX = posiX;
        this.posY = posiY;
        this.setBackground(emptyPiedraColor);
        this.ocupada = false;
    }

    public int getPositionX() {
        return posX;
    }

    public int getPositionY() {
        return posY;
    }

    public boolean getOcupada(){
        return this.ocupada;
    }

    public void setOcupada(boolean aux){
        this.ocupada = aux;
    }

    public void autoDestruirse(){
        this.remove(this);
    }

    public void setFondo(Color color){
        this.setBackground(color);
    }

    public void limpiarCasilla() {
        this.setBackground(emptyPiedraColor);
    }
}
