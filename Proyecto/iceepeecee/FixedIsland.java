package iceepeecee;
import javax.swing.JOptionPane;
/**
 * Clase fixedIsland, una subclase de Island.
 * Esta clase representa una isla fija en un entorno de juego.
 * @author Camilo Murcia y Jeisson Casallas
 * @version 5.0 10/31/23
 */

public class FixedIsland extends Island {
    /**
     * Constructor para objetos de la clase FixedIsland.
     * 
     * @param color el valor del color del vuelo.
     * @param polygon una matriz con las coordenadas de los vértices del polígono a dibujar.
     */
    public FixedIsland(String color, int[][] polygon) {
        super(color, polygon);
    }
    
    /**
     * No permite que se haga invisible la isla
     * 
     */
    public void makeInvisible(){
        isVisible = false;
        isla.makeInvisible();
        if (!isVisible) {
            JOptionPane.showMessageDialog(null, "FixedIsland","Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public int[][] getPolygon(){
        return this.polygon;
    }
    
    /**
     * Metodo para hacer visible el vuelo
     */
    public void makeVisible(){
        isVisible= true;
        isla.makeVisible();
    }
    
}