package domain;
import CostumeTest.*;
/**
* Write a description of class ConstumeException here.
*
* @author Jeisson Casallas & Camilo Murcia
* @version 1.0
*/
public class CostumeShopException extends Exception{
    public static final String PRICE_EMPTY = "El precio esta vacio";
    public static final String PRICE_ERROR = "Error en el precio especificado";
    public static final String IMPOSSIBLE_COMPLETE = "Imposible de completar";
    public static final String COMPLETE_EMPTY= "Disfraz no esta completo";
    public static final String PRICE_UNKNOWN="Precio de la pieza basica desconocido, no es makeup";
    public static final String COSTUME_DUPLICATE ="El disfraz esta duplicado";
    public static final String NEGATIVE_DISCOUNT="El descuento no puede ser negativo";
    /**
     * Constructor for objects of class ConstumeException
     * @param: String message
     */
    public CostumeShopException(String message){
        super(message);
    }
    /**
     * Constructor for objects of class ConstumeExcepetion
     */
    public CostumeShopException(){
        super("Error en CustomShop");
    }

}