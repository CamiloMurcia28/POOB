package iceepeecee;
import shapes.*;
import javax.swing.JOptionPane;
/**
 * Clase SurprisingIsland, una subclase de Isla.
 * Esta clase representa una isla que si tiene mas de tres vertices, cuando le piden la ubicacion, pierde uno de sus vertices.
 * @author Camilo Murcia y Jeisson Casallas
 * @version 5.0 10/31/23
 */
public class SurprisingIsland extends Island{
    /**
     * Constructor for objects of class surprisingIsland
     * @param: String color, el valor del color del vuelo
     * @param int[] polygon, Matriz con las coordenadas de los vertices del poligono a dibujar
     */
    public SurprisingIsland(String color, int[][] polygon){
        super(color,polygon);

    }
    
    /**
     * Metodo para obtetener las coordenadas de una isla
     * @return polygon, contiene las coordenas de la isla especificada
     */
    public int[][] getPolygon(){
        this.makeInvisible();
        int[][] originalPolygon = this.polygon;
        int vertices = originalPolygon.length;
        
        
        if (vertices > 3) {
            int[][] newPolygon = new int[vertices - 1][2];
            for (int i = 0; i < vertices - 1; i++) {
                newPolygon[i] = originalPolygon[i];
            }
            this.polygon = newPolygon;
            this.isla = new PolygonICPC(this.color, this.polygon);
        }
        else{
            JOptionPane.showMessageDialog(null, "la cantidad de vertices es menor o igual a 3","Error", JOptionPane.ERROR_MESSAGE);
        }
        this.makeVisible();
        return this.polygon;
        
    }
    
    /**
     * Metodo para hacer visible el vuelo
     */
    public void makeVisible(){
        isVisible= true;
        isla.makeVisible();
    }
    
    /**
     * Metodo para hacer invisible el vuelo
     */
    public void makeInvisible(){
        isla.makeInvisible();
        isVisible=false;
    }

}