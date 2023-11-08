package iceepeecee;
import shapes.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.*;
import java.awt.*;
import javax.swing.JOptionPane;
import java.lang.reflect.InvocationTargetException;
 
/**
 * Clase para IceePeeCee (Islands from the Sky)
 * @author Camilo Murcia y Jeisson Casallas
 * @version 5.0 10/31/23
 */
public class Iceepeecee
{
    public boolean lastOperationSuccess;
    private boolean isVisible;
    private PolygonICPC tablero;
    HashMap<String, Island> islandDictionary = new HashMap<>();
    HashMap<String, Flight> flightDictionary = new HashMap<>();
    
    //Ciclo 1
    /**
     * Constructor de la clase Iceepeecee
     * 
     * @param lengthSize ingresa el largo del tablero
     * @param widthSize ingresa el ancho del tablero
    */
    public Iceepeecee(int lengthSize, int widthSize){       
        this.tablero = new PolygonICPC(lengthSize, widthSize);
        tablero.drawCanvas();
    }
        
    /**
     * Nos crea una isla con un color dado y unas coordenadas dadas
     * 
     * @param color ingresa un color tipo string
     * @param polygon ingresa una matriz con coordenadas x,y que crear el poligono
     */
    public void addIsland(String color, int[][] polygon) {
        if(islandDictionary.get(color)==null && polygon.length>=3){
            Island island = new NormalIsland(color,polygon);
            islandDictionary.put(color, island);
            lastOperationSuccess = true;
        }
        else{
            lastOperationSuccess = false;
            JOptionPane.showMessageDialog(null, "el color especificado ya existe,o el numero de vertices es menor a 3","Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Nos agrega un vuelo con un color, unas coordenadas de donde inicia y donde termina 
     * 
     * @param color color que va a tener nuestro vuelo
     * @param from coordenada donde inicia el vuelo dada como arreglo
     * @param to coordenada donde termina el vuelo dada como arreglo 
     */
    public void addFlight(String color, int [] from, int [] to){
        if(flightDictionary.get(color)==null && from.length>=3 && to.length>=3){
            Flight vuelo = new NormalFlight(color,from ,to);
            flightDictionary.put(color, vuelo);
            lastOperationSuccess = true;
        }
        else{
            lastOperationSuccess=false;
            JOptionPane.showMessageDialog(null, "el vuelo especificado ya existe,o el numero de coordenadas es menor a 3","Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Toma una fotografia de un vuelo con el color y angulo especificado
     * 
     * @param flightColor color del vuelo desde el cual toma la foto
     * @param teta angulo de apertura que va a tener la foto
    */
    public void photograph(String flightColor, int teta){
        lastOperationSuccess = false;
        Flight v = flightDictionary.get(flightColor);
        if(v!=null && teta>=0){
            lastOperationSuccess = v.photograph(teta);
        }
    }
    
    /**
     * Toma una fotografia desde todos los vuelos que se encuentren activos con un angulo especificado
     * 
     * @param teta angulo de apertura que va a tener la foto
    */
    public void photograph(int teta) {
        lastOperationSuccess = false;
        if (teta >= 0) {
            for (Flight f : flightDictionary.values()) {
                f.makeInvisible();
                lastOperationSuccess = f.photograph(teta);
                f.makeVisible();
            }
        }

    }

    /**
     * Nos elimina una isla creada a partir de su color
     * 
     * @param color ingresa un color tipo string segun la isla que se quiere borrar
     */
    public void delIsland(String color){
        Island isla = islandDictionary.get(color);
        if(isla!=null){
            isla.makeInvisible();
            if(isla.getIsVisible()){
                islandDictionary.remove(color);
                lastOperationSuccess = true;
            }else{
                lastOperationSuccess = false;
                makeVisible();
            }
        }
        else{
            lastOperationSuccess=false;
            JOptionPane.showMessageDialog(null, "No se puede eliminar una isla que no existe","Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Nos elimina un vuelo de los que se encuentran en el tablero
     * 
     * @param color Es el color del vuelo que deseamos eliminar
     */
    public void delFlight(String color){
        Flight vuelo = flightDictionary.get(color);
        if(vuelo!=null){
            vuelo.makeInvisible();
            flightDictionary.remove(color);
            lastOperationSuccess = true;
        }
        else{
            lastOperationSuccess = false;
            JOptionPane.showMessageDialog(null, "No se puede eliminar un vuelo que no existe","Error", JOptionPane.ERROR_MESSAGE);
        }
    }
     
    /**
     * Nos arroja las coordenadas de la isla del color indicado
     * 
     * @param is isla que queremos conocer sus coordenadas a partir de su color
     * @return nos retorna una matriz con las coordenadas que tiene nuestra isla
     */
    public int[][] islandLocation(String is){
        int[][] position = null;
        if(islandDictionary.get(is)!=null){
            if(islandDictionary.containsKey(is)){
                Island isla = islandDictionary.get(is);
                position = isla.getPolygon();
            }
            lastOperationSuccess = true;
        }
        else{
            lastOperationSuccess = false;
            JOptionPane.showMessageDialog(null, "No se encontro la isla especificada","Error", JOptionPane.ERROR_MESSAGE);
        }
        return position;
    }
    
    /**
     * Nos da las coordenadas de inicio y final de nuestro vuelo a partir del color
     *
     *@param fl Vuelo que queremos eliminar identificado por su color 
     *@return Matriz que contiene las coordenadas de inicio y fin de nuestro vuelo
     */
    public int[][] flightLocation(String fl){
        int[][] position = null;
        if(flightDictionary.get(fl)!=null){
            if(flightDictionary.containsKey(fl)){
                Flight vuelo = flightDictionary.get(fl);
                position = vuelo.getPosition();
            }
            lastOperationSuccess = true;
        }
        else{
            lastOperationSuccess = false;
            JOptionPane.showMessageDialog(null, "No se encontro el vuelo especificado","Error", JOptionPane.ERROR_MESSAGE);
        }
        return position;
    }    
    
    /**
     * Consulta el angulo de la foto que toma un vuelo
     * 
     * @param Color color del vuelo el cual queremos consultar el angulo de la foto
     * @return retorna el valor del angulo de la foto
    */
    public int flightCamera(String color){
        int angulo = 0;
        lastOperationSuccess=false;
        Flight f = flightDictionary.get(color);
        if(f != null){
            angulo = f.getTeta();
            lastOperationSuccess=true;
        }
        return angulo ;
    }
    
    /**
     * Nos hace visible la isla.
     */
    public void makeVisible(){
        lastOperationSuccess = false;
        for(Island i: islandDictionary.values()){
               i.makeVisible(); 
        }
        for(Flight f: flightDictionary.values()){
                f.makeVisible(); 
            }
        lastOperationSuccess = true;
    }
    
    /**
     * Nos hace invisible la isla.
     */
    public void makeInvisible(){
        lastOperationSuccess = false;
        for(Island i: islandDictionary.values()){
               i.makeInvisible(); 
        }
        for(Flight f: flightDictionary.values()){
                f.makeInvisible(); 
            }
        lastOperationSuccess = true;
    }
    
    /**
     * Termina el simulador
     * 
     */
    public void finish(){
        System.exit(0);
    }
    
    /**
     * Verifica que el ultimo metodo se halla ejecutado con exito
     */
    public boolean ok(){
        return lastOperationSuccess;
    }

    //--------------------------------------------------------------------------------------------------------------------------------
    //ciclo 2
    /**
     *Constructor de la clase Iceepecee que crea el tablero con las islas y los vuelos ingresados 
     *
     *@param islands arreglo de matrices donde se van a ingresar las islas que queremos que tenga nuestro tablero
     *@param flights arreglo de matrices donde se van a ingresar los vuelos que queremos que tenga nuestro tablero
     */
    
    public Iceepeecee(int[][][] islands, int[][][] flights) {
        lastOperationSuccess = false;
        int widthSize = 300;
        int lengthSize = 300;
        this.tablero = new PolygonICPC(lengthSize, widthSize);
        tablero.drawCanvas();
        ArrayList<String> coloresFlight = new ArrayList<>();
        ArrayList<String> coloresIsland = new ArrayList<>();
    
        for (int[][] island : islands) {
            String color = getRandomColor();
            while(coloresIsland.contains(color)){
                color=getRandomColor();
            }
            this.addIsland(color,island);
            coloresIsland.add(color);
        }
    
        for (int[][] flight : flights) {
            String color = getRandomColor();
            while(coloresFlight.contains(color)){
                color=getRandomColor();
            }
            int[] from = flight[0];
            int[] to = flight[1];
            this.addFlight(color, from, to);
            coloresFlight.add(color);
        }
    
        if (islands.length > 0 && flights.length > 0) {
            lastOperationSuccess = true;
        }
    }

    /**
     * Metodo para obtener un color random 
     * @return String, color random de un arreglo;
     */
    private String getRandomColor() {
        int red = (int)(Math.random() * 256);
        int green = (int)(Math.random() * 256);
        int blue = (int)(Math.random() * 256);
        String colorName = String.format("#%02X%02X%02X", red, green, blue);
        return colorName;
    }
    
    /**
     * Toma una fotografia desde todos los vuelos que se encuentren activos con un angulo especificado
     * 
     * @param teta angulo de apertura de tipo flotante que va a tener la foto
     */
    public void photograph(float teta){
        lastOperationSuccess = false;
        if (flightDictionary.size() > 0) {
            if (teta >= 0) {
                for (Flight f : flightDictionary.values()) {
                    f.makeInvisible();
                    lastOperationSuccess = f.photograph(teta);
                    f.makeVisible();
                }
            }
        }
        
    }
    
    /**
     * Consulta las islas que se encuentran en Iceepeecee
     * 
     * @return retorna un arreglo con las islas que se encuentran en el tablero
    */
    public String[] islands(){
        lastOperationSuccess = true;
        Set<String> claves = islandDictionary.keySet();
        String[] miArray = claves.toArray(new String[0]);
        return miArray;
    }

     /**
     * Consulta los vuelos que se encuentran en Iceepeecee
     * @return retorna un arreglo con los vuelos que se encuentran en el tablero
     */
    public String[] flights(){
        lastOperationSuccess = true;
        Set<String> claves = flightDictionary.keySet();
        String[] miArray = claves.toArray(new String[0]);
        return miArray;
    }
    
    /**
     * Consulta las islas registradas en las fotografias
     *@return retorna un arreglo con las islas que han sido registradas en la fotografia
    */
    public String[] observedIslands(){
        boolean flag = false;
        lastOperationSuccess=true;
        ArrayList<String> al = new ArrayList<>();
        for(Flight f : flightDictionary.values()){
             for(Island i : islandDictionary.values()){
                 flag = f.observedIsland(i);
                 if(flag){
                     al.add(i.getColor());
                 }
             }
        }
        String[] obsIslands = al.toArray(new String[0]);
        return obsIslands;
    }
    
    /**
     * Consulta los vuelos que han tomado una fotografia inutil
     * 
     * @return retorna un arreglo con los vuelos que no han fotografiado ninguna isla en su totalidad
    */
    public String[] uselessFlights(){
        boolean flag = false;
        lastOperationSuccess=true;
        int cont=0;
        ArrayList<String> al = new ArrayList<>();
        for(Flight f : flightDictionary.values()){
             cont=0;
             for(Island i : islandDictionary.values()){
                 flag = f.observedIsland(i);
                 if(!flag){
                     cont++;
                 }
             }
             if(cont==islandDictionary.values().size()){
                 al.add(f.getColor());
                }
        }
        String[] obsIslands = al.toArray(new String[0]);
        return obsIslands;
    }
    
    //CICLO 4
    /**
     * Metodo para agregar un tipo de isla a selección del usuario
     * 
     * @param type string que recibe el nombre del tipo de isla
     * @param color string que recibe el color que tendra la isla
     * @param polygon matriz que recibe las coordenadas de la isla
     * 
     */
    public void addIsland(String type, String color, int[][] polygon){
        if(islandDictionary.get(color)==null && polygon.length>=3){
            try {
                Class<?> claseIsland = Class.forName("iceepeecee." + type);
                Island island = (Island) claseIsland.getDeclaredConstructor(String.class, int[][].class).newInstance(color, polygon);
                islandDictionary.put(color, island);
                lastOperationSuccess = true;
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | ClassNotFoundException | InvocationTargetException e){
                lastOperationSuccess = false;
                JOptionPane.showMessageDialog(null, "No se pudo crear la isla: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            lastOperationSuccess=false;
        }
    }
    
    /**
     * Metodo para agregar un tipo de vuelo a selección del usuario
     * 
     * @param type string que recibe el nombre del tipo de vuelo
     * @param color string que recibe el color que tendra el vuelo
     * @param from arreglo que recibe las coordenadas de inicio del vuelo
     * @param to arreglo que recibe las coordenadas de finales del vuelo
     * 
     */
    public void addFlight(String type, String color, int[] from, int[] to) {
        if(flightDictionary.get(color)==null && from.length>=3 && to.length>=3){
            try {
                Class<?> claseFlight= Class.forName("iceepeecee." + type);
                Flight flight = (Flight)claseFlight.getDeclaredConstructor(String.class, int[].class, int[].class).newInstance(color, from, to);
                flightDictionary.put(color, flight);
                lastOperationSuccess = true;
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | ClassNotFoundException | InvocationTargetException e) {
                lastOperationSuccess = false;
                JOptionPane.showMessageDialog(null, "No se pudo crear el vuelo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            lastOperationSuccess=false;
        }
    }
}