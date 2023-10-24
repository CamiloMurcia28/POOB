import java.awt.Polygon;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;

/**
 * @authors: Camilo Murcia y Jeisson Casallas
 * @version 2.0 09/23/23
 */

public class PolygonICPC
{
    private int[][]polygon;
    private String color;
    private boolean isVisible;
    private int lengthSize;
    private int widthSize;
    private Polygon2D polygon2d;
    
    /**
     * Constructor for objects of class polygonICPC, shapes
     * @param: String color, contiene el color del objeto
     * @param: int[][] polygon, contiene los vertices del objeto a dibujar
    */
    public PolygonICPC(String color,int[][]polygon){
        this.color = color;
        this.polygon = polygon;
    }
    
    public PolygonICPC(String color, Polygon2D p){
        this.color = color;
        this.polygon2d = p;
    }
    
    /**
     * Constructor for objects of class polygonICPC, canvas
     * @param int lengthSize, contiene la altura del canvas
     * @param int widthSize, contiene el ancho del canvas
    */
    public PolygonICPC(int lengthSize, int widthSize){
        this.lengthSize = lengthSize;
        this.widthSize = widthSize;
    }
    
    /**
     * Metodo para dibujar los elementos de islas y vuelos
     */
    public void draw()
    {
        if(isVisible && polygon2d == null){
            Canvas canvas = Canvas.getCanvas();
            int vertices = this.polygon.length;
            float[] xPoints = new float[vertices];
            float[] yPoints = new float[vertices];
            for (int i = 0; i < this.polygon.length;i++){
                xPoints[i] = this.polygon[i][0];
                yPoints[i] = this.polygon[i][1];
            }
            this.polygon2d = new Polygon2D(xPoints, yPoints, vertices);
            canvas.draw(this, color, this.polygon2d);
        }
        else{
            draw2();
        }
    }
    
    public void draw2(){
        if(isVisible){
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, this.polygon2d);
        }
    }
    
    public boolean containIsland(Island i){
        int[][] puntos = i.getPolygon();
        boolean flag = false;
        boolean flagR = false;
        int cont = 0;
        for(int j = 0;j < puntos.length; j++){
            flag = polygon2d.contains(puntos[j][0], puntos[j][1]);
            if(flag){
                cont++;
            }
        }
        if(cont == puntos.length){
            flagR = true;
        }
        return flagR;
    }
    
    /**
     * Metodo para dibujar el tablero del IceePeeCee 
     */
    public void drawCanvas()
    {
        Canvas.getCanvasIcpc(lengthSize, widthSize);
    }
    
    /**
     * Metodo para borrar el objeto especificado
     */
    public void erase(){
        if(isVisible){
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    
    /**
     * Nos hace visible el poligono
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Nos hace Invisible el poligono
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
}
