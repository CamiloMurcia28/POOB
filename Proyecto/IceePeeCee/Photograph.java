import java.util.*;
import java.awt.Polygon;
import java.awt.Point;

/**
 * @author Camilo Murcia y Jeisson Casallas
 * @version 2.0 09/23/23
 */
public class Photograph
{
    private float teta;
    private String color;
    private boolean isVisible;
    private int[][] coords;
    private float[][] coordsF;
    private PolygonICPC photo;
    private Polygon2D polygon2D;
    
    /**
     * Constructor for objects of class Photograph
     * 
     * @param: int teta, contiene el valor del angulo para la fotografia
     * @param: int[][]from, contiena las coordenadas de inicio del vuelo
     * @param: int[][]to, contiena las coordenadas de fin del vuelo
     * @param: String color, contiene el color del vuelo especificado
     */
    public Photograph(String colorVuelo,int teta, int[]from, int[]to){
        this.coords = this.trapezoidCoords(teta,from, to);
        this.teta = teta;
        this.photo = new PolygonICPC(changeColorName(colorVuelo), this.coords);
    }
    
    /**
     * Constructor for objects of class Photograph
     * 
     * @param: int teta, contiene el valor del angulo para la fotografia
     * @param: int[][]from, contiena las coordenadas de inicio del vuelo
     * @param: int[][]to, contiena las coordenadas de fin del vuelo
     * @param: String color, contiene el color del vuelo especificado
     */
    public Photograph(float tetaF, String colorVuelo, int[]from, int[]to){
        this.coordsF = trapezoidCoordsF(tetaF,from, to);
        float xPoints[] = getX(coordsF); float yPoints[] = getY(coordsF);
        Polygon2D p = new Polygon2D(xPoints, yPoints, coordsF.length);
        this.photo = new PolygonICPC(changeColorName(colorVuelo), p);
    }
    
    private float[] getX(float[][]coords){
        float[] retornox = new float[coords.length];
        for(int i = 0;i<coords.length;i++){
            retornox[i] = coords[i][0];
        }
        return retornox;
    }
    
    private float[] getY(float[][]coords){
        float[] retornoy = new float[coords.length];
        for(int i = 0;i<coords.length;i++){
            retornoy[i] = coords[i][1];
        }
        return retornoy;
    }
    
    /**
     * Metodo para hallar las coordenadas del trapecio de la foto
     * 
     * @param: int teta, contiene el valor del angulo para la fotografia
     * @param: int[][]from, contiena las coordenadas de inicio del vuelo
     * @param: int[][]to, contiena las coordenadas de fin del vuelo
     * @return: int[][]returnMatrix, retorna las coordenas del trapecio de la foto
     */
    private int[][] trapezoidCoords(int teta, int[]from, int[]to){
        int alturaInicial = from[2];
        int alturaFinal = to[2];
        
        double m1 = (to[1]-from[1])*Math.pow((to[0]-from[0]),-1);//m=y2-y1/x2-x1
        double m2 = -Math.pow(m1,-1);//pendiente perp
        
        double baseFrom = (double)((alturaInicial) * Math.tan(Math.toRadians(teta)));
        double baseTo = (double)((alturaFinal) * Math.tan(Math.toRadians(teta)));
        double interAngle = Math.toDegrees(Math.atan(m2));
        double ybasFrom = (double)(baseFrom*Math.sin(Math.toRadians(interAngle)));
        double xbasFrom = (double)(baseFrom*Math.cos(Math.toRadians(interAngle)));
        double ybasTo = (double)(baseTo*Math.sin(Math.toRadians(interAngle)));
        double xbasTo = (double)(baseTo*Math.cos(Math.toRadians(interAngle)));
        
        int[][]returnMatrix = new int[4][2];
        returnMatrix[0][0] = from[0] + (int)(Math.abs(xbasFrom));
        returnMatrix[0][1] = from[1] - (int)(Math.abs(ybasFrom));
        
        returnMatrix[1][0] = to[0] + (int)(Math.abs(xbasTo));
        returnMatrix[1][1] = to[1] - (int)(Math.abs(ybasTo));
        
        returnMatrix[2][0] = to[0] - (int)(Math.abs(xbasTo));
        returnMatrix[2][1] = to[1] + (int)(Math.abs(ybasTo));
        
        returnMatrix[3][0] = from[0] - (int)(Math.abs(xbasFrom));
        returnMatrix[3][1] = from[1] + (int)(Math.abs(ybasFrom));
        return returnMatrix;
    }
    
    /**
     * Sobreescritura del Metodo para hallar las coordenadas del trapecio de la foto
     * 
     * @param: float teta, contiene el valor del angulo en flotante para la fotografia
     * @param: int[][]from, contiena las coordenadas de inicio del vuelo
     * @param: int[][]to, contiena las coordenadas de fin del vuelo
     * @return: int[][]returnMatrix, retorna las coordenas del trapecio de la foto
     */
    
    public float[][] trapezoidCoordsF(float teta, int[]from, int[]to){
        int alturaInicial = from[2];
        int alturaFinal = to[2];
        
        double m1 = (to[1]-from[1])*Math.pow((to[0]-from[0]),-1);//m=y2-y1/x2-x1
        double m2 = -Math.pow(m1,-1);//pendiente perp
        
        double baseFrom = (double)((alturaInicial) * Math.tan(Math.toRadians(teta)));
        double baseTo = (double)((alturaFinal) * Math.tan(Math.toRadians(teta)));
        double interAngle = Math.toDegrees(Math.atan(m2));
        double ybasFrom = (double)(baseFrom*Math.sin(Math.toRadians(interAngle)));
        double xbasFrom = (double)(baseFrom*Math.cos(Math.toRadians(interAngle)));
        double ybasTo = (double)(baseTo*Math.sin(Math.toRadians(interAngle)));
        double xbasTo = (double)(baseTo*Math.cos(Math.toRadians(interAngle)));
        
        float[][]returnMatrix = new float[4][2];
        returnMatrix[0][0] = from[0] + (float)(Math.abs(xbasFrom));
        returnMatrix[0][1] = from[1] - (float)(Math.abs(ybasFrom));
        
        returnMatrix[1][0] = to[0] + (float)(Math.abs(xbasTo));
        returnMatrix[1][1] = to[1] - (float)(Math.abs(ybasTo));
        
        returnMatrix[2][0] = to[0] - (float)(Math.abs(xbasTo));
        returnMatrix[2][1] = to[1] + (float)(Math.abs(ybasTo));
        
        returnMatrix[3][0] = from[0] - (float)(Math.abs(xbasFrom));
        returnMatrix[3][1] = from[1] + (float)(Math.abs(ybasFrom));
        return returnMatrix;
    }
    
    /**
     * Metodo para verificar que una isla esta siendo observada por una foto
     * 
     * @param i Isla a verificar 
     * @return booleano que nos dice si esta siendo observada o no
     */
    public boolean observedIsland(Island i){
        int[][] coords = i.getPolygon();
        boolean flag = photo.containIsland(i);
        if(flag){
            i.isObserved = true;
        }   
        
        return flag;
    } 
   
    /**
     * Metodo para retornar el angulo de la foto especificada
     * 
     * @return teta, retorna el valor del angulo que se le especifica
     */
    public float getTeta(){
        return this.teta;
    }
    
    /**
     * Metodo para hacer visible la fotografia
     */
    public void makeVisible(){
        isVisible = true;
        photo.makeVisible();
    }
    
    /**
     * Metodo para hacer invisible la fotografia
     */
    public void makeInvisible(){
        photo.makeInvisible();
        isVisible = false;
    }
    
    /*
     * Metodo para transformar el color en uno con "menor" opacidad
     * 
     * @param String color, contiene el nombre del color original
     * @return String, retorna el string con el nombre "modificado"
     */
    private String changeColorName(String color){
        if(color=="red"){
            color="light red";
        }
        if(color=="black"){
            color="light black";
        }
        if(color=="blue"){
            color="light blue";
        }
        if(color=="yellow"){
            color="light yellow";
        }
        if(color=="green"){
            color="light green";
        }
        if(color=="magenta"){
            color="light magenta";
        }
        if(color=="white"){
            color="light white";
        }
        if(color=="cyan"){
            color="light cyan";
        }
        if(color=="orange"){
            color="light orange";
        }
        if(color=="gray"){
            color="light gray";
        }
        if(color=="pink"){
            color="light pink";
        }
        return color;
    }    
}