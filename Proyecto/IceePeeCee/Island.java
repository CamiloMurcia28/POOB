import java.util.*;
import java.awt.Polygon;

/**
 * @author Camilo Murcia y Jeisson Casallas
 * @version 2.0 09/23/23
 */
public class Island
{
    private String color;
    private boolean isVisible;
    private int[][]polygon;
    private PolygonICPC isla;
    public boolean isObserved;
    
    /**
     * Constructor for objects of class Island
     * @param: String color, el valor del color del vuelo
     * @param int[] polygon, Matriz con las coordenadas de los vertices del poligono a dibujar
     */
    public Island(String color,int[][]polygon){         
        isVisible=false;
        this.polygon = polygon;
        this.color = color;
        this.isla = new PolygonICPC(color, polygon);
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

    /*
     * Metodo para obtener las coordenadas de los vertices de la isla especificada
     * @param int[][]polygon, contiene las coordenadas del poligono a retornar
     */
    private int[][]getCoordinates(int[][]polygon){
        int vertices = polygon.length;
        int[] xPoints = new int[vertices];
        int[] yPoints = new int[vertices];
        for (int i = 0; i < polygon.length;i++){
            xPoints[i] = polygon[i][0];
            yPoints[i] = polygon[i][1];
        }
        int[][]coordinates={xPoints,yPoints};
        return coordinates;
    }
    
    /**
     * Metodo para obtener el color de una isla
     * @return color, contiene el color de la isla especificada
     */
    public String getColor(){
        return color;
    }
    
    /**
     * Metodo para obtetener las coordenadas de una isla
     * @return polygon, contiene las coordenas de la isla especificada
     */
    public int[][] getPolygon(){
        return this.polygon;
    }
    
    public boolean getisObserved(){
        return this.isObserved;
    }
}