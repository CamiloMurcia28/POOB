package domain;
import java.awt.Color;

/**
 * The entity class from colony
 *
 * @author  Jeisson Casallas & Camilo Murcia
 * @version 1.0
 */
public interface Entity{
  int ROUND = 1;
  int SQUARE = 2;
  int INSECT = 3;
  /**
   * realiza una acción la entidad.
   */
  void act();
  
  /**
   * le da una forma predeterminada a la entidad
   * 
   * @return valor entero que representa la forma.
   */
  default int shape(){
      return SQUARE;
  }
  
  /**
   * obtiene el color predeterminado de la entidad
   * 
   * @return retorna el color predeterminado.
   */
  default Color getColor(){
      return Color.orange;
  };
  
  /**
   * retorna un valor booleano con respecto a la entidad
   * 
   * @return retorna true
   */
  default boolean is(){
      return true;
  }
  
  
}
