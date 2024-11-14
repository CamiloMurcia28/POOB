package iceepeecee;

/**
 * Clase Normal, una subclase de Island.
 * Esta clase representa una isla normal, es decir puede usar todas las cararacteristica de Island.
 * @author Camilo Murcia y Jeisson Casallas
 * @version 5.0 10/31/23
 */
public class NormalIsland extends Island{
    /**
     * Constructor for objects of class normalIsland
     * @param: String color, el valor del color del vuelo
     * @param int[] polygon, Matriz con las coordenadas de los vertices del poligono a dibujar
     */
    public NormalIsland(String color,int[][] polygon){
        super(color,polygon);
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
    
    /**
     * Metodo para hacer invisible el vuelo
     */
    public void makeInvisible(){
        isVisible = true;
        isla.makeInvisible();
        isVisible=false;
    }
}
