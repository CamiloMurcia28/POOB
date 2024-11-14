package domain;
import java.util.*;


/**
 * The Colony class
 *
 * @author  Jeisson Casallas & Camilo Murcia
 * @version 1.0
 */
public class Colony{
    static private int LENGTH=30;
    private Entity[][] colony;
    
    /**
     * Constructor de la clase Colony
     */
    public Colony() {
        colony=new Entity[LENGTH][LENGTH];
        for (int r=0;r<LENGTH;r++){
            for (int c=0;c<LENGTH;c++){
                colony[r][c]=null;
            }
        }
        setEntity(0,0,new Food());
        setEntity(0,1,new Food());
        setEntity(0,2,new Food());
        setEntity(1,0,new Food());
        setEntity(1,1,new Food());
        someEntities();
    }
    
    /**
     * Nos da la longitud del tablero
     * 
     * @return valor entero del tamaño
     */
    public int  getLength(){
        return LENGTH;
    }
    
    /**
     * Verifica si hay una entidad en la posición especificada
     * 
     * @return retorna un tipo entidad.
     */
    public Entity getEntity(int r,int c){
        return colony[r][c];
    }

    public void setEntity(int r, int c, Entity e){
        colony[r][c]=e;
    }
    
    /**
     * Permite agregar entidades al tablero
     */
    public void someEntities(){
        Ant flik= new Ant(this, 10, 10);
        Ant molt= new Ant(this, 15, 15);
        HungryAnt rachel= new HungryAnt(this,9,9);
        HungryAnt monica= new HungryAnt(this,8,8);
        Flower rose= new Flower(this,29,0);
        Flower violet= new Flower(this,29,29);
        CannibalAnt Murcia= new CannibalAnt(this,0,29);
        CannibalAnt Jeisson= new CannibalAnt(this,29,1);
        Poison Veneno= new Poison(this,9,11);
        Poison Veneno3= new Poison(this,11,11);
        Poison Veneno4= new Poison(this,21,21);
        Poison Veneno7= new Poison(this,11,15);
    }
    
    /**
     * Le da la funcionalidad al Botón de la interfaz a partir del cual se realizan acciones
     */
    public void ticTac(){
        ArrayList<Entity> moved = new ArrayList<Entity>();
        for (int i = 0; i < LENGTH; i++){
            for (int j = 0; j < LENGTH; j++){
                if(colony[i][j] instanceof Entity && !(moved.contains(colony[i][j]))){
                    moved.add(colony[i][j]);
                    colony[i][j].act();
                }
            }
        }
    }
    
}

