import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * @author Camilo Murcia y Jeisson Casallas
 * @version 2.0 09/23/23
 */
public class Flight
{
    private String color;
    private boolean isVisible;
    private int[] from;
    private int[] to;
    private PolygonICPC vuelo;
    private HashMap<String, Photograph> fotos = new HashMap<>();
    //private ArrayList<Photograph> fotosAL = new ArrayList<>();
    
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
     * @return: boolean bandera, retorna si se puede crear la fotografia con el angulo especificado
     */
    public boolean photograph(int teta){
        boolean bandera = false;
        if(teta < 90){
            Photograph p = new Photograph(this.color,teta, this.from, this.to);
            fotos.put(this.color, p);
            p.makeVisible();
            bandera = true;
        }
        return bandera;
    }

    /**
     * Sobreescritura del metodo para crear un objeto de fotografia del avion especificado
     * 
     * @return: boolean bandera, retorna si se puede crear la fotografia con el angulo especificado
     */
    public boolean photograph(float teta){
        boolean bandera = false;
        if(teta < 90){
            Photograph p = new Photograph(teta, this.color, this.from, this.to);
            fotos.put(this.color, p);
            p.makeVisible();
            bandera = true;
        }
        return bandera;
    }
    
    /**
     * Metodo para obtener la posicion del vuelo especificado
     * 
     * @return returnMatrix, retorna una matriz con los valores de la posicion
     */
    public int[][] getPosition(){
        int [][] returnMatrix = new int[2][2];
        returnMatrix[0][0] = this.from[0];
        returnMatrix[0][1] = this.from[1];
        returnMatrix[1][0] = this.to[0];
        returnMatrix[1][1] = this.to[1];
        return returnMatrix;
    }
    
    public Photograph getPhoto(String color){
        return fotos.get(color);
    }
    
    /**
     * Metodo para obtener el angulo de la camara del vuelo
     * 
     * @return retorna el angulo de la cama del vuelo.
     */
    public int getTeta(){
        int tam = fotos.size();
        int angulo = 0;
        if(tam > 0){
            angulo = (int)fotos.get(this.color).getTeta();
        }
        return angulo;
    }
    
    public boolean observedIsland(Island i){
        return fotos.get(color).observedIsland(i);
    }
}
