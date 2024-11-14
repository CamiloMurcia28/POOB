package domain;
import java.util.*;
import java.io.File;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * The Colony class
 * @author  Jeisson Casallas & Camilo Murcia
 * @version 1.0
 */
public class Colony implements Serializable{
    static private int LENGTH=30;
    private Entity[][] colony;
    boolean hayErrores;
    
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
        // ESTAN COMO COMENTARIOS PARA QUE NO INTERFIERAN CON LAS PRUEBAS DE UNIDAD :D
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
    
    /**
     * Abre un archivo.
     *
     * @param file Archivo que se va a abrir.
     * @throws ColonyException Si ocurre un error al abrir el archivo.
     */
    public static  Colony open(File file) throws ColonyException {
        throw new ColonyException(ColonyException.open_File + "Archivo " + file.getName());
    }

    /**
     * Guarda un archivo.
     *
     * @param file Archivo que se va a guardar.
     * @throws ColonyException Si ocurre un error al guardar el archivo.
     */
    public void save(File file) throws ColonyException {
        throw new ColonyException(ColonyException.save_File + "Archivo " + file.getName());
    }

    /**
     * Importa datos desde un archivo.
     *
     * @param file Archivo del cual se van a importar los datos.
     * @throws ColonyException Si ocurre un error al importar desde el archivo.
     */
    public void importA(File file) throws ColonyException {
        throw new ColonyException(ColonyException.import_File + "Archivo " + file.getName());
    }

    /**
     * Exporta datos a un archivo.
     *
     * @param file Archivo al cual se van a exportar los datos.
     * @throws ColonyException Si ocurre un error al exportar a archivo.
     */
    public void export(File file) throws ColonyException {
        throw new ColonyException(ColonyException.export_File + "Archivo " + file.getName());
    }
    
    //GUARDAR Y ABRIR
    
    /**
     * Abre un archivo.
     *
     * @param file Archivo que se va a abrir.
     * @return Colony retorna la colonia que se desea abrir.
     * @throws ColonyException Si ocurre un error al abrir el archivo.
     * 
     */
    public static Colony open00(File file) throws ColonyException {
        Colony instancia= null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            instancia=(Colony) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new ColonyException("Error al cargar el archivo: " + e.getMessage());
        }
        return instancia;
    }

    /**
     * Guarda un archivo.
     *
     * @param file Archivo que se va a guardar.
     * @throws ColonyException Si ocurre un error al guardar el archivo.
     */
    public void save00(File file) throws ColonyException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ColonyException("Error al guardar el archivo: " + e.getMessage());
        }
    }
    
    
    //IMPORTAR Y EXPORTAR
    
    /**
     * Importa datos desde un archivo.
     *
     * @param file Archivo del cual se van a importar los datos.
     * @throws ColonyException Si ocurre un error al importar desde el archivo.
     */
    public void import00(File file) throws ColonyException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");
                if (parts.length >= 3) {
                    String entityType = parts[0];
                    int posX = Integer.parseInt(parts[1]);
                    int posY = Integer.parseInt(parts[2]);
    
                    Entity entity = createEntity(entityType, posX, posY);
                    if (entity != null) {
                        setEntity(posX, posY, entity);
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            throw new ColonyException("Error al guardar el archivo: " + e.getMessage());
            
        }
    }
    
    private Entity createEntity(String entityType, int posX, int posY) {
        switch (entityType) {
            case "Ant":
                return new Ant(this, posX, posY);
            case "HungryAnt":
                return new HungryAnt(this, posX, posY);
            case "CannibalAnt":
                return new CannibalAnt(this,posX,posY);
            case "Flower":
                return new Flower(this,posX,posY);
            case "Poison":
                return new Poison(this,posX,posY);
            case "Food":
                return new Food();
            default:
                return null;
        }
    }
    
   
    /**
     * Exporta datos a un archivo.
     *
     * @param file Archivo al cual se van a exportar los datos.
     * @throws ColonyException Si ocurre un error al exportar a archivo.
     */
    public void export00(File file) throws ColonyException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < LENGTH; i++) {
                for (int j = 0; j < LENGTH; j++) {
                    Entity entity = colony[i][j];
                    if (entity != null) {
                        writer.write(entity.getClass().getSimpleName() + " " + i + " " + j);
                        writer.newLine();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ColonyException("Error al guardar el archivo: " + e.getMessage());
        }
    }
    
    
    //PERFECIONANDO SALVAR Y ABRIR
    
    
    /**
     * Abre un archivo.
     *
     * @param file Archivo que se va a abrir.
     * @return Colony retorna la colonia que se desea abrir.
     * @throws ColonyException Si ocurre un error al abrir el archivo.
     * 
     */
    public static Colony open01(File file) throws ColonyException {
        Colony instancia= null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            instancia=(Colony) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new ColonyException(ColonyException.ARCHIVO_NO_ENCONTRADO);
        } catch (StreamCorruptedException e) {
            throw new ColonyException(ColonyException.ARCHIVO_CORRUPTO);
        } catch (ClassNotFoundException e) {
            throw new ColonyException(ColonyException.CLASE_NO_ENCONTRADA);
        } catch (InvalidClassException e) {
            throw new ColonyException(ColonyException.CLASE_INVALIDA);
        } catch (OptionalDataException e) {
            throw new ColonyException(ColonyException.DATOS_PRIMITIVOS);
        } catch (IOException e) {
            throw new ColonyException(ColonyException.ERROR_ENTRADA);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ColonyException(ColonyException.ERROR_AL_ABRIR);
        }
        return instancia;
    }

    /**
     * Guarda un archivo.
     *
     * @param file Archivo que se va a guardar.
     * @throws ColonyException Si ocurre un error al guardar el archivo.
     */
    public void save01(File file) throws ColonyException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(this);
        } catch (FileNotFoundException e) {
            throw new ColonyException(ColonyException.ARCHIVO_NO_ENCONTRADO);
        } catch (InvalidClassException e) {
            throw new ColonyException(ColonyException.CLASE_INVALIDA);
        } catch (NotSerializableException e) {
            throw new ColonyException(ColonyException.CLASE_NO_SERIALIZABLE);
        } catch (IOException e) {
            throw new ColonyException(ColonyException.ERROR_SALIDA);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ColonyException(ColonyException.ERROR_AL_GUARDAR);
        }
    }
    
    //PERFECIONANDO IMPORTAR Y EXPORTAR
    
    /**
     * Importa datos desde un archivo.
     *
     * @param file Archivo del cual se van a importar los datos.
     * @throws ColonyException Si ocurre un error al importar desde el archivo.
     */
    public void import01(File file) throws ColonyException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");
                if (parts.length >= 3) {
                    String entityType = parts[0];
                    int posX = Integer.parseInt(parts[1]);
                    int posY = Integer.parseInt(parts[2]);
    
                    Entity entity = createEntity(entityType, posX, posY);
                    if (entity != null) {
                        setEntity(posX, posY, entity);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new ColonyException(ColonyException.ARCHIVO_NO_ENCONTRADO);
        } catch (IOException e) {
            throw new ColonyException(ColonyException.ERROR_AL_LEER_ARCHIVO);
        } catch (NumberFormatException e) {
            throw new ColonyException(ColonyException.FORMATO_INVALIDO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ColonyException(ColonyException.ERROR_AL_IMPORTAR);
        }
    }
    
    /**
     * 
     * Exporta datos a un archivo.
     *
     * @param file Archivo al cual se van a exportar los datos.
     * @throws ColonyException Si ocurre un error al exportar a archivo.
     */
    public void export01(File file) throws ColonyException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < LENGTH; i++) {
                for (int j = 0; j < LENGTH; j++) {
                    Entity entity = colony[i][j];
                    if (entity != null) {
                        writer.write(entity.getClass().getSimpleName() + " " + i + " " + j);
                        writer.newLine();
                    }
                }
            }
       } catch (FileNotFoundException e) {
            throw new ColonyException(ColonyException.ARCHIVO_NO_ENCONTRADO);
        } catch (IOException e) {
            throw new ColonyException(ColonyException.ERROR_DE_ESCRITURA);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ColonyException(ColonyException.ERROR_AL_EXPORTAR);
        }
    }
    
    //IMPORTAR Y EXPORTAR MINICOMPILADOR
    
    /**
     * Importa datos desde un archivo.
     *
     * @param file Archivo del cual se van a importar los datos.
     * @throws ColonyException Si ocurre un error al importar desde el archivo.
     */
    public void import02(File file) throws ColonyException {
        hayErrores = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter errorWriter = new BufferedWriter(new FileWriter("ColonyErr.txt", true))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                processLine(line, lineNumber, errorWriter);
            }
    
        } catch (FileNotFoundException e) {
            throw new ColonyException(ColonyException.ARCHIVO_NO_ENCONTRADO);
        } catch (IOException e) {
            throw new ColonyException(ColonyException.ERROR_AL_LEER_ARCHIVO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ColonyException(ColonyException.ERROR_AL_IMPORTAR);
        }
    
        if (hayErrores) {
            throw new ColonyException(ColonyException.ERROR_AL_IMPORTAR_CON_DETALLES);
        }
    }
    
    /*
     * Procesa una línea del archivo, dividiéndola en partes y realizando validaciones.
     *
     * @param line        La línea a procesar.
     * @param lineNumber  El número de línea actual.
     * @param errorWriter El escritor para mensajes de error.
     * @throws IOException Si ocurre un error de escritura.
     */
    private void processLine(String line, int lineNumber, BufferedWriter errorWriter) throws IOException {
        String[] parts = line.split("\\s+");
        if (parts.length == 3) {
            String entityType = parts[0];
            try {
                int posX = Integer.parseInt(parts[1]);
                int posY = Integer.parseInt(parts[2]);
                validateAndSetEntity(entityType, posX, posY, lineNumber, errorWriter);
            } catch (NumberFormatException e) {
                writeError(errorWriter, lineNumber, "Las coordenadas deben ser enteros.");
            }
        } else {
            writeError(errorWriter, lineNumber, "Solo se deben ingresar 3 parámetros (Entidad, posX, PosY).");
        }
    }

    
    /*
     * Valida las coordenadas y configura una entidad si es válida.
     *
     * @param entityType   El tipo de entidad.
     * @param posX         La coordenada X.
     * @param posY         La coordenada Y.
     * @param lineNumber   El número de línea actual.
     * @param errorWriter  El escritor para mensajes de error.
     * @throws IOException Si ocurre un error de escritura.
     */
    private void validateAndSetEntity(String entityType, int posX, int posY, int lineNumber, BufferedWriter errorWriter)
            throws IOException {
        if (posX >= 0 && posX < LENGTH && posY >= 0 && posY < LENGTH) {
            Entity entity = createEntity(entityType, posX, posY);
            if (entity != null) {
                setEntity(posX, posY, entity);
            } else {
                writeError(errorWriter, lineNumber, "Tipo de entidad no reconocido.");
            }
        } else {
            writeError(errorWriter, lineNumber, "Coordenadas fuera de rango.");
        }
    }

    
    /*
     * Escribe un mensaje de error en el archivo de errores y marca la existencia de errores.
     *
     * @param errorWriter  El escritor para mensajes de error.
     * @param lineNumber   El número de línea actual.
     * @param errorMessage El mensaje de error a escribir.
     * @throws IOException Si ocurre un error de escritura.
     */
    private void writeError(BufferedWriter errorWriter, int lineNumber, String errorMessage) throws IOException {
        errorWriter.write("Error en la línea " + lineNumber + ": " + errorMessage);
        errorWriter.newLine();
        hayErrores = true;
    }



    /**
     * 
     * Exporta datos a un archivo.
     *
     * @param file Archivo al cual se van a exportar los datos.
     * @throws ColonyException Si ocurre un error al exportar a archivo.
     */
    public void export02(File file) throws ColonyException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < LENGTH; i++) {
                for (int j = 0; j < LENGTH; j++) {
                    Entity entity = colony[i][j];
                    if (entity != null) {
                        writer.write(entity.getClass().getSimpleName() + " " + i + " " + j);
                        writer.newLine();
                    }
                }
            }
       } catch (FileNotFoundException e) {
            throw new ColonyException(ColonyException.ARCHIVO_NO_ENCONTRADO);
        } catch (IOException e) {
            throw new ColonyException(ColonyException.ERROR_DE_ESCRITURA);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ColonyException(ColonyException.ERROR_AL_EXPORTAR);
        }
    }
    
    
    //BONO
    
    /*
     * Crea una entidad según el tipo y posición especificados.
     *
     * @param entityType Tipo de entidad.
     * @param posX Posición X.
     * @param posY Posición Y.
     * @return La entidad creada, o null si hay un error.
     */
    private Entity createEntity2(String entityType, int posX, int posY){
        try {
            Class<?> entityClass = Class.forName("domain." + entityType);
            return (Entity) entityClass.getDeclaredConstructor(Colony.class, int.class, int.class).newInstance(this, posX, posY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
   /**
     * 
     * Exporta datos a un archivo.
     *
     * @param file Archivo al cual se van a exportar los datos.
     * @throws ColonyException Si ocurre un error al exportar a archivo.
     */
    public void export03(File file) throws ColonyException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < LENGTH; i++) {
                for (int j = 0; j < LENGTH; j++) {
                    Entity entity = colony[i][j];
                    if (entity != null) {
                        writer.write(entity.getClass().getSimpleName() + " " + i + " " + j);
                        writer.newLine();
                    }
                }
            }
       } catch (FileNotFoundException e) {
            throw new ColonyException(ColonyException.ARCHIVO_NO_ENCONTRADO);
        } catch (IOException e) {
            throw new ColonyException(ColonyException.ERROR_DE_ESCRITURA);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ColonyException(ColonyException.ERROR_AL_EXPORTAR);
        }
    }
       
    /**
     * Importa datos desde un archivo.
     *
     * @param file Archivo del cual se van a importar los datos.
     * @throws ColonyException Si ocurre un error al importar desde el archivo.
     */
    public void import03(File file) throws ColonyException {
        hayErrores = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter errorWriter = new BufferedWriter(new FileWriter("ColonyErrG.txt", true))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                processLine2(line, lineNumber, errorWriter);
            }
        } catch (FileNotFoundException e) {
            throw new ColonyException(ColonyException.ARCHIVO_NO_ENCONTRADO);
        } catch (IOException e) {
            throw new ColonyException(ColonyException.ERROR_AL_LEER_ARCHIVO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ColonyException(ColonyException.ERROR_AL_IMPORTAR);
        }
    
        if (hayErrores) {
            throw new ColonyException(ColonyException.ERROR_AL_IMPORTAR_CON_DETALLES_BONO);
        }
    }
    
    /*
     * Procesa una línea del archivo, dividiéndola en partes y realizando validaciones.
     *
     * @param line        La línea a procesar.
     * @param lineNumber  El número de línea actual.
     * @param errorWriter El escritor para mensajes de error.
     * @throws IOException Si ocurre un error de escritura.
     */
    private void processLine2(String line, int lineNumber, BufferedWriter errorWriter) throws IOException {
        String[] parts = line.split("\\s+");
        if (parts.length == 3) {
            String entityType = parts[0];
            try {
                int posX = Integer.parseInt(parts[1]);
                int posY = Integer.parseInt(parts[2]);
                validateAndSetEntity(entityType, posX, posY, lineNumber, errorWriter);
            } catch (NumberFormatException e) {
                writeError(errorWriter, lineNumber, "Las coordenadas deben ser enteros.");
            }
        } else {
            writeError(errorWriter, lineNumber, "Solo se deben ingresar 3 parámetros (Entidad, posX, PosY).");
        }
    }

    
    /*
     * Valida las coordenadas y configura una entidad si es válida.
     *
     * @param entityType   El tipo de entidad.
     * @param posX         La coordenada X.
     * @param posY         La coordenada Y.
     * @param lineNumber   El número de línea actual.
     * @param errorWriter  El escritor para mensajes de error.
     * @throws IOException Si ocurre un error de escritura.
     */
    private void validateAndSetEntity2(String entityType, int posX, int posY, int lineNumber, BufferedWriter errorWriter)
            throws IOException {
        if (posX >= 0 && posX < LENGTH && posY >= 0 && posY < LENGTH) {
            Entity entity = createEntity2(entityType, posX, posY);
            if (entity != null) {
                setEntity(posX, posY, entity);
            } else {
                writeError(errorWriter, lineNumber, "Tipo de entidad no reconocido.");
            }
        } else {
            writeError(errorWriter, lineNumber, "Coordenadas fuera de rango.");
        }
    }

    
    /*
     * Escribe un mensaje de error en el archivo de errores y marca la existencia de errores.
     *
     * @param errorWriter  El escritor para mensajes de error.
     * @param lineNumber   El número de línea actual.
     * @param errorMessage El mensaje de error a escribir.
     * @throws IOException Si ocurre un error de escritura.
     */
    private void writeError2(BufferedWriter errorWriter, int lineNumber, String errorMessage) throws IOException {
        errorWriter.write("Error en la línea " + lineNumber + ": " + errorMessage);
        errorWriter.newLine();
        hayErrores = true;
    } 
    
}

