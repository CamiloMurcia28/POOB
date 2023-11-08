package iceepeecee;
import shapes.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Clase Flight
 * Esta clase representa un vuelo.
 * @author Camilo Murcia y Jeisson Casallas
 * @version 5.0 10/31/23
 */
public abstract class Flight
{
    protected String color;
    protected boolean isVisible;
    protected int[] from;
    protected int[] to;
    protected PolygonICPC vuelo;
    protected HashMap<String, Photograph> fotos = new HashMap<>();
    
    /**
     * Constructor for objects of class Flight
     * 
     * @param: String color, el valor del color del vuelo
     * @param int[] from, arreglo con las coordenadas de inicio del vuelo
     * @param int[] tom arreglo con las coordenadas de fin del vuelo
     */
    public Flight(String color, int[] from , int[] to){
        isVisible=false;
        this.from = from;
        this.to = to;
        this.color = color;
        this.vuelo = new PolygonICPC(color, this.getPosition());
    }
    
    /**
     * Metodo para hacer visible el vuelo
     */
    public void makeVisible(){
        isVisible = true;
        vuelo.makeVisible();
        makePhotographVisible();
    }
    
    /**
     * Metodo para hacer invisible el vuelo
     */
    public void makeInvisible(){
        vuelo.makeInvisible();
        makePhotographInvisible();
        isVisible = false;
    }
    
    /*
     * Metodo para hacer visible la ultima fotografía si esta existe.
     */
    private void makePhotographVisible(){
        int tam = fotos.size();
        if(tam > 0){
            fotos.get(this.color).makeVisible();
        }
    }
    /*
     * Metodo para hacer invisible la ultima fotografía si esta existe.
     */
    private void makePhotographInvisible(){
        int tam = fotos.size();
        if(tam > 0){
            fotos.get(this.color).makeInvisible();
        }
    }
    
    /**
     * Metodo para retornar el color del vuelo
     * 
     * @return Color, retorna el color del vuelo
     */
    public String getColor(){
        return color;
    }
    
    /**
     * Metodo para crear un objeto de fotografia del avion especificado
     * 
     * @param teta angulo que va a tener la fotografia
     * @return bandera, retorna un booleano si se puede crear la fotografia con el angulo especificado
     */
    public abstract boolean photograph(int teta);

    /**
     * Sobreescritura del metodo para crear un objeto de fotografia del avion especificado
     * 
     * @param teta angulo que va a tener la fotografia
     * @return bandera, retorna un booleano si se puede crear la fotografia con el angulo especificado
     */
    public abstract boolean photograph(float teta);
    
    /**
     * Metodo para obtener la posicion del vuelo especificado
     * 
     * @return returnMatrix, retorna una matriz con los valores de la posicion
     */
    public int[][] getPosition(){
        int [][] returnMatrix = new int[2][3];
        returnMatrix[0][0] = this.from[0];returnMatrix[0][1] = this.from[1];returnMatrix[0][2] = this.from[2];
        returnMatrix[1][0] = this.to[0];returnMatrix[1][1] = this.to[1];returnMatrix[1][2] = this.to[2];
        return returnMatrix;
    }
    
    /**
     * Metodo para obtener el angulo de la camara del vuelo
     * 
     * @return retorna el angulo de la cama del vuelo.
     */
    public int getTeta(){
        int angulo = 0;
        if(fotos.size()> 0){
            angulo = (int)fotos.get(this.color).getTeta();
        }
        return angulo;
    }
    
    /**
     * Metodo para verificar si una isla es observada por una foto
     * 
     * @param i isla que se quiere verificar
     * @return retorna un booleano que verifica si es observada o no
     */
    public boolean observedIsland(Island i){
        return fotos.get(color).observedIsland(i);
    }
}