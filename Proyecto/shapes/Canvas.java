package shapes;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;

/**
 * Canvas is a class to allow for simple graphical drawing on a canvas.
 * This is a modification of the general purpose Canvas, specially made for
 * the BlueJ "shapes" example. 
 *
 * @author: Bruce Quig
 * @author: Michael Kolling (mik)
 *
 * @version: 1.6 (shapes)
 */
public class Canvas{
    // Note: The implementation of this class (specifically the handling of
    // shape identity and colors) is slightly more complex than necessary. This
    // is done on purpose to keep the interface and instance fields of the
    // shape objects in this project clean and simple for educational purposes.

    private static Canvas canvasSingleton;

    /**
     * Factory method to get the canvas singleton object.
     */
    public static Canvas getCanvas(){
        if(canvasSingleton == null) {
            canvasSingleton = new Canvas("BlueJ Shapes Demo", 300, 300, 
                                         Color.white);
        }
        canvasSingleton.setVisible(true);
        return canvasSingleton;
    }

    public static Canvas getCanvasIcpc(int heightActual, int widthActual){
        if(canvasSingleton == null) {
            canvasSingleton = new Canvas("BlueJ Shapes Demo", heightActual, widthActual, 
                                         new Color(179,255,255,255));
        }
        canvasSingleton.setVisible(true);
        return canvasSingleton;
    }
    
    //  ----- instance part -----

    private JFrame frame;
    private CanvasPane canvas;
    private Graphics2D graphic;
    private Color backgroundColour;
    private Image canvasImage;
    private List <Object> objects;
    private HashMap <Object,ShapeDescription> shapes;
    private static int heightActual;
    private static int widthActual;
    
    /**
     * Create a Canvas.
     * @param title  title to appear in Canvas Frame
     * @param width  the desired width for the canvas
     * @param height  the desired height for the canvas
     * @param bgClour  the desired background colour of the canvas
     */
    public Canvas(String title, int width, int height, Color bgColour){
        frame = new JFrame();
        canvas = new CanvasPane();
        frame.setContentPane(canvas);
        frame.setTitle(title);
        canvas.setPreferredSize(new Dimension(width, height));
        backgroundColour = bgColour;
        frame.pack();
        objects = new ArrayList <Object>();
        shapes = new HashMap <Object,ShapeDescription>();
    }

    public void canvasSize(int Width, int Height){
        canvas.setPreferredSize(new Dimension(Width, Height));
        heightActual = Height;
        widthActual = Width;
        
    }
    
    public Dimension getCanvasSize(){
        return canvas.getSize();
    }
    
    /**
     * Set the canvas visibility and brings canvas to the front of screen
     * when made visible. This method can also be used to bring an already
     * visible canvas to the front of other windows.
     * @param visible  boolean value representing the desired visibility of
     * the canvas (true or false) 
     */
    public void setVisible(boolean visible){
        if(graphic == null) {
            // first time: instantiate the offscreen image and fill it with
            // the background colour
            Dimension size = canvas.getSize();
            canvasImage = canvas.createImage(size.width, size.height);
            graphic = (Graphics2D)canvasImage.getGraphics();
            graphic.setColor(backgroundColour);
            graphic.fillRect(0, 0, size.width, size.height);
            graphic.setColor(Color.black);
        }
        frame.setVisible(visible);
    }

    /**
     * Draw a given shape onto the canvas.
     * @param  referenceObject  an object to define identity for this shape
     * @param  color            the color of the shape
     * @param  shape            the shape object to be drawn on the canvas
     */
     // Note: this is a slightly backwards way of maintaining the shape
     // objects. It is carefully designed to keep the visible shape interfaces
     // in this project clean and simple for educational purposes.
    public void draw(Object referenceObject, String color, Shape shape){
        objects.remove(referenceObject);   // just in case it was already there
        objects.add(referenceObject);      // add at the end
        shapes.put(referenceObject, new ShapeDescription(shape, color));
        redraw();
    }
 
    /**
     * Erase a given shape's from the screen.
     * @param  referenceObject  the shape object to be erased 
     */
    public void erase(Object referenceObject){
        objects.remove(referenceObject);   // just in case it was already there
        shapes.remove(referenceObject);
        redraw();
    }
    
    /**
     * Set the foreground colour of the Canvas.
     * @param  newColour   the new colour for the foreground of the Canvas 
     */
    public void setForegroundColor(String color){
        
        if(color.equals("red"))
            graphic.setColor(Color.red);
        else if(color.equals("black"))
            graphic.setColor(Color.black);
        else if(color.equals("blue"))
            graphic.setColor(Color.blue);
        else if(color.equals("yellow"))
            graphic.setColor(Color.yellow);
        else if(color.equals("green"))
            graphic.setColor(Color.green);
        else if(color.equals("magenta"))
            graphic.setColor(Color.magenta);
        else if(color.equals("white"))
            graphic.setColor(Color.white);
        else if(color.equals("cyan"))
            graphic.setColor(Color.cyan);
        else if(!(color.charAt(color.length() - 1) == '%')){
            color = color.substring(1);
            int red = Integer.parseInt(color.substring(0, 2), 16);
            int green = Integer.parseInt(color.substring(2, 4), 16);
            int blue = Integer.parseInt(color.substring(4, 6), 16);
            graphic.setColor(new Color(red, green, blue, 128));
        }
        else
            setForegroundColorOther(color);
        
    }
    
    private void setForegroundColorOther(String color){
        if(color.equals("red%"))
            graphic.setColor(new Color(255, 0, 0, 100));
        else if(color.equals("black%"))
            graphic.setColor(new Color(0, 0, 0, 100));
        else if(color.equals("blue%"))
            graphic.setColor(new Color(0, 0, 255, 100));
        else if(color.equals("yellow%"))
            graphic.setColor(new Color(255, 255, 0, 100));
        else if(color.equals("green%"))
            graphic.setColor(new Color(0, 128, 0, 100));
        else if(color.equals("magenta%"))
            graphic.setColor(new Color(255, 0, 255, 100));
        else if(color.equals("white%"))
            graphic.setColor(new Color(255, 255, 255, 100));
        else{
            color = color.substring(1);
            int red = Integer.parseInt(color.substring(0, 2), 16);
            int green = Integer.parseInt(color.substring(2, 4), 16);
            int blue = Integer.parseInt(color.substring(4, 6), 16);
            graphic.setColor(new Color(red, green, blue, 128));
        }
    }
    
    /**
     * Wait for a specified number of milliseconds before finishing.
     * This provides an easy way to specify a small delay which can be
     * used when producing animations.
     * @param  milliseconds  the number 
     */
    public void wait(int milliseconds){
        try{
            Thread.sleep(milliseconds);
        } catch (Exception e){
            // ignoring exception at the moment
        }
    }

    /**
     * Redraw ell shapes currently on the Canvas.
     */
    private void redraw(){
        erase();
        for(Iterator i=objects.iterator(); i.hasNext(); ) {
                       shapes.get(i.next()).draw(graphic);
        }
        canvas.repaint();
    }
       
    /**
     * Erase the whole canvas. (Does not repaint.)
     */
    private void erase(){
        Color original = graphic.getColor();
        graphic.setColor(backgroundColour);
        Dimension size = canvas.getSize();
        graphic.fill(new java.awt.Rectangle(0, 0, size.width, size.height));
        graphic.setColor(original);
    }


    /************************************************************************
     * Inner class CanvasPane - the actual canvas component contained in the
     * Canvas frame. This is essentially a JPanel with added capability to
     * refresh the image drawn on it.
     */
    private class CanvasPane extends JPanel{
        public void paint(Graphics g){
            g.drawImage(canvasImage, 0, 0, null);
        }
    }
    
    /************************************************************************
     * Inner class CanvasPane - the actual canvas component contained in the
     * Canvas frame. This is essentially a JPanel with added capability to
     * refresh the image drawn on it.
     */
    private class ShapeDescription{
        private Shape shape;
        private String colorString;

        public ShapeDescription(Shape shape, String color){
            this.shape = shape;
            colorString = color;
        }

        public void draw(Graphics2D graphic){
            setForegroundColor(colorString);
            graphic.draw(shape);
            graphic.fill(shape);
        }
    }

}
