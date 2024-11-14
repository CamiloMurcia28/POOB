package iceepeecee;
/**
 * Clase NormalFlight, una subclase de Flight.
 * Esta clase representa un vuelo normal, que puede usar todas las caracteristicas de flight.
 * @author Camilo Murcia y Jeisson Casallas
 * @version 5.0 10/31/23
 */
public class NormalFlight extends Flight{

    /**
     * Constructor for objects of class normalFlight
     * 
     * @param String color, el valor del color del vuelo
     * @param int[] from, arreglo con las coordenadas de inicio del vuelo
     * @param int[] tom arreglo con las coordenadas de fin del vuelo
     */
    public NormalFlight(String color, int[] from , int[] to){
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
        if(teta < 90){
            Photograph p = new Photograph(this.color,teta, this.from, this.to);
            fotos.put(this.color, p);
            p.makeVisible();
            bandera = true;
        }
        return bandera;
    }
    
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
    
}
