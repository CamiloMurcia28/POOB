package iceepeecee;
import javax.swing.JOptionPane;

/**
 * Clase LazyFlight, una subclase de Flight.
 * Esta clase representa un vuelo que solo toma una fotografia
 * @author Camilo Murcia y Jeisson Casallas
 * @version 5.0 10/31/23
 */
public class LazyFlight extends Flight{
    
    /**
     * Constructor for objects of class lazyFlight
     * 
     * @param: String color, el valor del color del vuelo
     * @param int[] from, arreglo con las coordenadas de inicio del vuelo
     * @param int[] tom arreglo con las coordenadas de fin del vuelo
     */
    public LazyFlight(String color, int[] from , int[] to){
        super(color,from,to);
    }
    
    /**
     * Metodo para crear un objeto de fotografia del avion especificado
     * 
     * @param teta angulo que va a tener la fotografia
     * @return bandera, retorna un booleano si se puede crear la fotografia con el angulo especificado
     */
    public boolean photograph(int teta){
        boolean bandera = false;
        if(teta < 90 && fotos.isEmpty()){
            Photograph p = new Photograph(this.color,teta, this.from, this.to);
            fotos.put(this.color, p);
            p.makeVisible();
            bandera = true;
        }
        else{
        JOptionPane.showMessageDialog(null, "No sé puede tomar mas de una foto","Error", JOptionPane.ERROR_MESSAGE);
        
        }
        return bandera;
    }

    /**
     * Sobreescritura del metodo para crear un objeto de fotografia del avion especificado
     * 
     * @param teta angulo que va a tener la fotografia
     * @return bandera, retorna un booleano si se puede crear la fotografia con el angulo especificado
     */
    public boolean photograph(float teta){
        boolean bandera = false;
        if(teta < 90 && fotos.isEmpty()){
            Photograph p = new Photograph(teta, this.color, this.from, this.to);
            fotos.put(this.color, p);
            p.makeVisible();
            bandera = true;
        }
        else{
            JOptionPane.showMessageDialog(null, "No sé puede tomar mas de una foto","Error", JOptionPane.ERROR_MESSAGE);
        
        }
        return bandera;
    }
    
}