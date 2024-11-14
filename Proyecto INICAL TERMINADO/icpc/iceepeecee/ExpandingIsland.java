package iceepeecee;
import shapes.*;

/**
 * Clase ExpandingIsland, una subclase de Island.
 * Esta clase representa una isla fija en un entorno de juego.
 * 
 * @author Camilo Murcia y Jeisson Casallas
 * @version 5.0 10/31/23
 */

public class ExpandingIsland extends Island {
    private double expansionFactor;
    /**
     * Constructor for objects of class fixedIsland
     * @param: String color, el valor del color del vuelo
     * @param int[] polygon, Matriz con las coordenadas de los vertices del poligono a dibujar
     */
    public ExpandingIsland(String color, int[][] polygon) {
        super(color, polygon);
        this.expansionFactor = 1.2;
    }
    
    private void expanding(){
        int[][] originalPolygon = this.getPolygon();
        makeInvisible();
        int fixedX = originalPolygon[0][0]; 
        int fixedY = originalPolygon[0][1];

        for (int i = 1; i < polygon.length; i++) {
            originalPolygon[i][0] = fixedX + (int) ((originalPolygon[i][0] - fixedX) * expansionFactor);
            originalPolygon[i][1] = fixedY + (int) ((originalPolygon[i][1] - fixedY) * expansionFactor);
        }

        this.polygon = originalPolygon;
        this.isla = new PolygonICPC(this.color, this.polygon);
    }
    
    /**
     * Hace visible la isla y la expande en tamaño.
     * 
     */
    public void makeVisible() {
        this.expanding();
        isVisible= true;
    }
    
    public int[][] getPolygon(){
        return this.polygon;
    }
    
    /**
     * Metodo para hacer invisible el vuelo
     */
    public void makeInvisible(){
        isVisible = true;
        isla.makeInvisible();
        isVisible=false;
    }
}



