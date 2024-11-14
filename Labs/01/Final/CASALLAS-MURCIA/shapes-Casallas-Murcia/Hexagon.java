import java.awt.Polygon;
import java.awt.*;
import java.awt.geom.*;
import java.lang.Math;
/**
 * A hexagon that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Camilo Murcia and Jeisson Casallas
 * @version 1.0  (30 August 2023)
 */
public class Hexagon
{
    private int sideLength;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;

     /**
     * Create a new hexagon at default position with default color.
     */
    //Ciclo 1
    public Hexagon()
    {
       sideLength = 60;
       xPosition = 100;
       yPosition = 50;
       color = "magenta";
       isVisible = true; 
    }
    
    /**
     * Make this Hexagon visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Make this Hexagon invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }

     /*
     * Draw the hexagon with current specifications on screen.
     */
    private void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            int[] xpoints = new int[6];
            for (int i = 0; i < 6; i++) {
                xpoints[i] = (int)(sideLength * Math.sin(2 * Math.PI * i / 6)) + xPosition;
            }
            int[] ypoints = new int[6];
            for (int i = 0; i < 6; i++) {
                ypoints[i] = (int)(sideLength * Math.cos(2 * Math.PI * i / 6)) + yPosition;
            }
            canvas.draw(this, color, new Polygon(xpoints, ypoints, 6));
            canvas.wait(10);
        }
    }

    /*
     * Erase the Hexagon on screen.
     */
    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    
    //Fin del ciclo 1
    
    //Comienzo ciclo 2
    
    /**
     * Move the Hexagon a few pixels to the right.
     */
    public void moveRight(){
        moveHorizontal(20);
    }

    /**
     * Move the Hexagon a few pixels to the left.
     */
    public void moveLeft(){
        moveHorizontal(-20);
    }

    /**
     * Move the Hexagon a few pixels up.
     */
    public void moveUp(){
        moveVertical(-20);
    }

    /**
     * Move the Hexagon a few pixels down.
     */
    public void moveDown(){
        moveVertical(20);
    }

    /**
     * Move the hexagon horizontally.
     * @param distance the desired distance in pixels
     */
    public void moveHorizontal(int distance){
        xPosition += distance;
        draw();
    }

    /**
     * Move the hexagon vertically.
     * @param distance the desired distance in pixels
     */
    public void moveVertical(int distance){
        yPosition += distance;
        draw();
    }

    /**
     * Slowly move the hexagon horizontally.
     * @param distance the desired distance in pixels
     */
    public void slowMoveHorizontal(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            xPosition += delta;
            draw();
        }
    }

    /**
     * Slowly move the hexagon vertically
     * @param distance the desired distance in pixels
     */
    public void slowMoveVertical(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        }else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            yPosition += delta;
            draw();
        }
    }
    
    /**
     * Change the color. 
     * @param color the new color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor){
        color = newColor;
        draw();
    }
    //Fin ciclo 2
    
    //Comienzo ciclo 3
    
    /**
     * Change the size.
     * @param newDiameter the new size (in pixels). Size must be >=0.
     */
    public void changeSize(int newSideLength){
        erase();
        sideLength = newSideLength;
        draw();
    }

    
}
