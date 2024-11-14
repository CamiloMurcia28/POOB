package domain;

/**
* Exception class for colony called ColonyException here.
*
* @author Jeisson Casallas & Camilo Murcia
* @version 1.0
*/
public class ColonyException extends Exception{
    public static final String open_File = "Opción Abrir en construcción.";
    public static final String  save_File= "Opción Guardar en construcción.";
    public static final String import_File = "Opción Importar en construcción.";
    public static final String export_File= "Opción Exportar en construcción.";
    
    //SAVE EXCEPTIONS
    public final static String ARCHIVO_NO_ENCONTRADO = "No se ha encontrado el archivo.";
    public final static String CLASE_INVALIDA = "La clase contiene errores.";
    public final static String CLASE_NO_SERIALIZABLE = "La clase a guardar o una clase referenciada no está marcada como serializable.";
    public final static String ERROR_SALIDA = "Error al guardar el archivo";
    public final static String ERROR_AL_GUARDAR = "Ha ocurrido un error al guardar el objeto.";
    
    
    //OPEN EXCEPTIONS
    
    public final static String ARCHIVO_CORRUPTO = "El archivo abierto se encuentra corrupto.";
    public final static String CLASE_NO_ENCONTRADA = "La clase que se desea construir no fué encontrada.";
    public final static String DATOS_PRIMITIVOS = "Se encontraron datos primitivos en lugar de objetos.";
    public final static String ERROR_ENTRADA = "Error al abrir el archivo";
    public final static String ERROR_AL_ABRIR = "Ha ocurrido un error al abrir el objeto.";
    
    //IMPORT EXCEPTIONS
    public static final String ERROR_AL_LEER_ARCHIVO = "Error al leer el archivo.";
    public static final String FORMATO_INVALIDO = "Formato de archivo a importar inválido.";
    public static final String ERROR_AL_IMPORTAR = "Error al importar desde el archivo.";
    
    //EXPORT EXCEPTIONS
    public static final String ERROR_DE_ESCRITURA = "Error de escritura al exportar.";
    public static final String ERROR_AL_EXPORTAR = "Error general al exportar.";

    //MiniCompilador
    public final static String ERROR_AL_IMPORTAR_CON_DETALLES = "Error al importar, puede encontrar. Detalles en el archivo 'ColonyErr.txt";

    //Bono
    public final static String ERROR_AL_IMPORTAR_CON_DETALLES_BONO = "Error al importar, puede encontrar. Detalles en el archivo 'ColonyErrG.txt";
    /**
     * Constructor for objects of class ColonyException
     * @param: message 
     */
    public ColonyException(String message){
        super(message);
    }
}
