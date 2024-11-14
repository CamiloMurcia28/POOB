package domain;
import java.util.Random;
import java.awt.Color;

/**
 * The CannibalAnt class from Ant
 *
 * @author  Jeisson Casallas & Camilo Murcia
 * @version 1.0
 */
public class CannibalAnt extends Ant{
    protected boolean yaComio;
    
    /**Create a new Ant (<b>row,column</b>) in the colony <b>colony</b>.
     * Every new Ant is going to be alive in the following state.
     * @param colony 
     * @param row 
     * @param column 
     */
    public CannibalAnt(Colony colony, int row, int column) {
        super(colony, row, column);
        color = Color.red;
        yaComio = false;
    }
    
    /**
     * Genera el movimiento que va a realizar la hormiga hacia otras hormigas.
     */
    public void move() {
        int minDistance = Integer.MAX_VALUE;
        int targetRow = -1;
        int targetColumn = -1;
    
        for (int r = 0; r < colony.getLength(); r++) {
            for (int c = 0; c < colony.getLength(); c++) {
                if (colony.getEntity(r, c) instanceof Ant && colony.getEntity(r, c) != this) {
                    int distance = Math.abs(row - r) + Math.abs(column - c);
                    if (distance < minDistance) {
                        minDistance = distance;
                        targetRow = r;
                        targetColumn = c;
                    }
                }
            }
        }
    
        if (targetRow != -1 && targetColumn != -1) {
            int newRow = row;
            int newColumn = column;
    
            if (targetRow < row) {
                newRow--;
            } else if (targetRow > row) {
                newRow++;
            }
    
            if (targetColumn < column) {
                newColumn--;
            } else if (targetColumn > column) {
                newColumn++;
            }
    
            if (newRow >= 0 && newRow < colony.getLength() && newColumn >= 0 && newColumn < colony.getLength()) {
                if (colony.getEntity(newRow, newColumn) == null || colony.getEntity(newRow,newColumn) instanceof Ant || colony.getEntity(newRow,newColumn) instanceof Poison) {
                    if (colony.getEntity(newRow, newColumn) instanceof Ant) {
                        yaComio = true;
                    }
                    if (colony.getEntity(newRow, newColumn) instanceof Poison) {
                        isPoison= true;
                    }
                    colony.setEntity(row, column, null);
                    row = newRow;
                    column = newColumn;
                    colony.setEntity(row, column, this);
                }
            }
        }
        turn();
    }
    
    /**
     * Genera la acción que va a realizar la hormiga.
     */
    public void act() {
        if (getAge() > 15) {
            nextState = Agent.DEAD;
        }
        if (nextState == Agent.ALIVE && !yaComio) {
            move();
        }
    }
    
    /**
     * Verifica si la hormiga ya comio o no
     * 
     * @return retorna un valor boolean segun si ya comio la hormiga.
     */
    public boolean hasEaten(){
        return yaComio;
    }
}

