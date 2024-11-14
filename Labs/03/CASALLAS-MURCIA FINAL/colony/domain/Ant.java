package domain;
import java.awt.Color;
import java.util.Random;

/**
 * The Ant class from entity and Agent
 *
 * @author  Jeisson Casallas & Camilo Murcia
 * @version 1.0
 */
public class Ant extends Agent implements Entity{
    public char nextState;
    protected Color color;
    protected Colony colony;
    protected int row,column;
    protected boolean isPoison;
    /**Create a new Ant (<b>row,column</b>) in the colony <b>colony</b>.
     * Every new Ant is going to be alive in the following state.
     * @param colony 
     * @param row 
     * @param column 
     */
    public Ant(Colony colony,int row, int column){
        this.colony=colony;
        this.row=row;
        this.column=column;
        nextState=Agent.ALIVE;
        colony.setEntity(row,column,(Entity)this);  
        color=Color.black;
        isPoison=false;
    }

    /**
     * Returns the shape
     * 
     * @return un entero que representa la forma de la hormiga
     */
    public final int shape(){
        return Entity.INSECT;
    }
    
    /**
     * Returns the row
     * 
     * @return un entero que nos da las fila 
     */
    public final int getRow(){
        return row;
    }

    /**
     * Returns tha column
     * 
     * @return retorna un entero que nos da la columna 
     */
    public final int getColumn(){
        return column;
    }

    
    /**
     * Returns the color
     * 
     * @return retorna el color de la hormiga. 
     */
    public final Color getColor(){
        return color;
    }
    
    /**
     * Genera la acción que va a realizar la hormiga.
     */
    public void act() {
        
        if (getAge() >50 || (isPoison) ) {
            nextState = Agent.DEAD;
        }
        if (nextState == Agent.ALIVE) {
                move();
        }
    }
    
    /**
     * Genera el movimiento aleatorio que va a realizar la hormiga
     */
    public void move(){
        Random rand=new Random();
        int number = rand.nextInt(8);
        int newRow = row;
        int newColumn = column;
        if (number == 1 && column < 30) {
            newColumn = column + 1;
        } else if (number == 2 && column > 1) {
            newColumn = column - 1;
        } else if (number == 3 && row > 1) {
            newRow = row - 1;
        } else if (number == 4 && row < 30) {
            newRow = row + 1;
        } else if (number == 5 && row > 1 && column < 30) {
            newRow = row - 1;
            newColumn = column + 1;
        } else if (number == 6 && row > 1 && column > 1) {
            newRow = row - 1;
            newColumn = column - 1;
        } else if (number == 7 && row < 30 && column > 1) {
            newRow = row + 1;
            newColumn = column - 1;
        } else if (number == 0 && row < 30 && column < 30) {
            newRow = row + 1;
            newColumn = column + 1;
        }
        if (newRow >= 1 && newRow < 30 && newColumn >= 1 && newColumn < 30) {
            if(colony.getEntity(newRow, newColumn) == null || colony.getEntity(newRow, newColumn) instanceof Food || colony.getEntity(newRow,newColumn) instanceof Poison) {
                if(colony.getEntity(newRow,newColumn) instanceof Poison){
                    isPoison=true;
                }
                colony.setEntity(row, column, null);
                row = newRow; 
                column = newColumn;
                colony.setEntity(row, column, this);
            }
        }
        turn();
    }
    
    /**
     * Verifica si la hormiga esta envenada
     * 
     * @return retorna un valor booleano segun si esta envenenada o no.
     */
    public boolean isPoison(){
        return isPoison;
    }
    
}
 