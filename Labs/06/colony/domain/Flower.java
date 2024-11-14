package domain;
import java.awt.Color;
import java.io.*;

/**
 * The Flower class from entity
 *
 * @author  Jeisson Casallas & Camilo Murcia
 * @version 1.0
 */
public class Flower implements Entity,Serializable{
    protected Color color;
    private boolean isOpen;    
    protected int row,column;
    protected Colony colony;
    /**
     * Constructor de la entidad flower.
     */
    public Flower(Colony colony,int row, int column){
        this.colony=colony;
        this.row=row;
        this.column=column;
        color=Color.GREEN;
        isOpen = true;
        colony.setEntity(row,column,(Entity)this);
    }
    /**
     * Realiza la acción de abrir y cerrar de la flor
     */
    public void act(){
        if(isOpen){
            color = Color.RED;
        }else{
            color = Color.GREEN;
        }
        isOpen = !isOpen;
    }
    /**
     * Le da la forma a la flor.
     * 
     * @return retorna la figura que va a representar la flor.
     */
    public int shape(){
        return Entity.ROUND;
    }
    
    /**
     * Returns the color
     * 
     * @return el color de la flor 
     */
    public final Color getColor(){
        return color;
    }
    
    /**
     * Verifica si la flor esta abierta o cerrada
     * 
     * @return retorna un valor booleano segun el estado de la flor.
     */
    public boolean isOpen(){
        return isOpen;
    }
}
