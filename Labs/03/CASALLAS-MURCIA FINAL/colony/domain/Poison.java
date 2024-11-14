package domain;
import java.awt.Color;
/**
 * The Poison Class from Domain.
 *
 * @author  Jeisson Casallas & Camilo Murcia
 * @version 1.0
 */
public class Poison implements Entity{
    protected Color color; 
    protected int row,column;
    protected Colony colony;
    /**
     * Constructor de la clase Poison
     */
    public Poison(Colony colony,int row, int column){
        this.colony=colony;
        this.row=row;
        this.column=column;
        color=new Color(58, 15, 139);
        colony.setEntity(row,column,(Entity)this);
    }
    /**
     * Realiza una accción
     */
    public void act(){
    }
    /**
     * Le da la forma al veneno
     * 
     * @return entero el cual identifica a la forma
     */
    public int shape(){
        return Entity.SQUARE;
    }
    
    /**
     * retorna el color del veneno.
     * 
     * @return  retorna el color del cual va a ser el veneno.
     */
    public final Color getColor(){
        return color;
    }
}
