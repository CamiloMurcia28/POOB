import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 * @authors: Jeisson Casallas & Camilo Murcia
 * @version 3.0 10/9/23
 */
//CICLO 3
public class IceepeeceeContest {
    private Iceepeecee iceepeecee;

    /**
     * Constructor for objects of class IceepeeceeContest
     */
    public IceepeeceeContest() {
    }

    /**
     * Método que resuelve el problema de la maratón, donde se haya el mínimo ángulo que abarca todas las islas.
     *
     * @param islands arreglo de matrices que contiene a las islas que queremos agregar al tablero
     * @param flights arreglo de matrices que contiene a los vuelos que queremos agregar al tablero
     * @return retorna el mínimo ángulo que puede tomar la foto para abarcar a todas las islas
     */
    public float solve(int[][][] islands, int[][][] flights) {
        Iceepeecee iceepeecee = new Iceepeecee(islands, flights);
        String angulo="";
        boolean flag = true;
        int cantidadIslas = iceepeecee.islands().length;
        float anguloBusqueda= binarySearch(cantidadIslas, iceepeecee);
        return anguloBusqueda;
    }

    /**
     * Simula la solución del problema de la maratón, representándolo gráficamente.
     *
     * @param islands arreglo de matrices que contiene a las islas que queremos agregar al tablero
     * @param flights arreglo de matrices que contiene a los vuelos que queremos agregar al tablero
     */
    public void simulate(int[][][] islands, int[][][] flights) {
        float res = solve(islands, flights);
        Iceepeecee iceepeecee = new Iceepeecee(islands, flights);
        iceepeecee.photograph(res);
        iceepeecee.makeVisible();
    }
    
     /*
     *Busqueda binaria para buscar un angulo que permita que todas las islas sean observadas.
     *
     * @param cantidadIslas_ la cantidad de islas a observar
     * @param iceepeecee el objeto Iceepeecee que contiene las islas y vuelos
     * @return el ángulo mínimo para observar todas las islas
     */
    private float binarySearch(int cantidadIslas, Iceepeecee iceepeecee) {
        float max = 90.0f;
        float min = 0.0f;
        int islasObservadas = 0;
        float mid =0f;
        for (int contador = 0; contador < 100; contador++) {
            mid = (max + min) / 2.0f;
            iceepeecee.photograph(mid);
            iceepeecee.makeVisible();
            islasObservadas = iceepeecee.observedIslands().length;
    
            if (islasObservadas == cantidadIslas) {
                max = mid;
            } else {
                min = mid;
            }
    
            if (mid == 90.0f) {
                return -1.0f;
            }
        }
        return mid;
    }
}