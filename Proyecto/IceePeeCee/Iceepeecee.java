import java.util.HashMap;
import java.util.ArrayList;
import java.util.*;
import java.awt.*;
import javax.swing.JOptionPane;
/**
 * @authors: Jeisson Casallas & Camilo murcia
 * @version 2.0 09/23/23
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
            Island island = new Island(color,polygon);
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
            Flight vuelo = new Flight(color,from ,to);
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
            islandDictionary.remove(color);
            lastOperationSuccess = true;
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
        isVisible = false;
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
        isVisible = false;
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
        Random random = new Random();
    
        ArrayList<String> colors = new ArrayList<>();
        colors.add("red");colors.add("black");colors.add("blue");
        colors.add("yellow");colors.add("green");colors.add("magenta");
        colors.add("white");colors.add("orange");colors.add("pink");
        colors.add("cyan");colors.add("gray");
    
        int index = random.nextInt(colors.size());
    
        return colors.get(index);
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
    
}