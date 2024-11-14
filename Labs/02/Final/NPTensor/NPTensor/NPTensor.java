import java.util.HashMap;
import java.util.Arrays;
import java.util.Random;


public class NPTensor {

    public HashMap<String, Tensor> npDictionary;
    private boolean lastOperationSuccess;
    // Ciclo 1: Operaciones básicas de tensores
    /**
     * Constructor de la clase NPTensor el cual lo inicializa 
     * dando paso a la configuracion de este por medio de sus metodos.
     * 
     */
    public NPTensor() {
        npDictionary = new HashMap<>();
        lastOperationSuccess = false;
    }

    /**
     * Asigna al tensor una forma especificada y un valor constante 
     *
     * @param name nombre que tendra el tensor que se va a crear
     * @param shape forma del tensor representada en forma de arreglo de enteros
     * @param value valor constante que va a tener el tensor
     */
    public void assign(String name, int[] shape, int value) {
        Tensor tensor = new Tensor(shape, value);
        npDictionary.put(name, tensor);
        lastOperationSuccess = true;
    }
    
    /**
     * Asigna al tensor una forma especificada y los valores que tendra
     *
     * @param name nombre que tendra el tensor que se va a crear
     * @param shape forma del tensor representada en forma de arreglo de enteros
     * @param values valores que van a llevar el tensor ingresados como arreglo de enteros
     */
    public void assign(String name, int[] shape, int[] values) {
        if (shape.length == 0 || shape.length != values.length) {
            lastOperationSuccess = false;
            return;
        }
    
        Tensor tensor = new Tensor(shape, values);
        npDictionary.put(name, tensor);
        lastOperationSuccess = true;
    }
    
    /**
     * Obtiene el valor de una posición especificada de un tensor dado su nombre
     *
     * @param name  Nombre del tensor que se desea obtener el valor.
     * @param index arreglo entero donde se va a espeficiar la posición cuyo valor se quiere conocer.
     * @return retorna el valor del tensor
     */
    public int getValue(String name, int[] index) {
        int result = -1;
        if (npDictionary.containsKey(name)) {
            Tensor tensor = npDictionary.get(name);
            result = tensor.value(index);
        }
    
        lastOperationSuccess = result != -1;
        return result;
    }


    /**
     * Verifica que la ultima operación se haya realizado con exito.
     *
     * @return Retorna un booleano segun sea el caso.
     */
    public boolean ok() {
        return lastOperationSuccess;
    }
     
    // Ciclo 2
    /**
     * Obtiene la forma del tensor
     * @param t nombre del tensor que se quiere consultar
     * @return resultado resulta la forma del tensor en caso de que este no sea nulo
     */
    public String shape(Tensor t) {
        String result;
    
        if (t == null) {
            result = "Tensor nulo";
        } else {
            int[] shapeT = t.getShape();
            result = Arrays.toString(shapeT);
        }
    
        return result;
    }

    
    /**
     * Modifica la forma de un tensor creado
     *
     * @param t nombre del tensor que se quiere modificar la forma
     * @param newShape arreglo de enteros que especifica la nueva forma que tendra
     */
    public void reshape(Tensor t, int[] newShape){
        // Verifica si el nuevo tamaño es compatible con la cantidad de elementos
        int [] data = t.getValues();
        int newSize = 1;
        int[] shape = t.getShape();

        for (int dim : newShape) {
            newSize *= dim;
        }

        if (newSize != data.length) {
            throw new IllegalArgumentException("La nueva forma no es compatible con la cantidad de elementos.");
        }

        // Actualiza la forma del tensor
        shape = newShape;
    }

    /**
     * Mezcla los valores de forma aleatoria de un tensor dado
     *
     * @param name nombre del tensor que se desea mezclar aleatoriamente.
     * @return Retorna el nuevo tensor con los valores mezclados
     *       
     */
    public Tensor shuffle(String name) {
        Tensor originalTensor = npDictionary.get(name);
    
        if (originalTensor == null) {
            lastOperationSuccess = false;
            return null;
        }
    
        int[] values = originalTensor.getValues();
        int size = values.length;
    
        int[] shuffledValues = Arrays.copyOf(values, size);
        Random rand = new Random();
    
        for (int i = size - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = shuffledValues[i];
            shuffledValues[i] = shuffledValues[j];
            shuffledValues[j] = temp;
        }
    
        Tensor shuffledTensor = new Tensor(originalTensor.getShape(), shuffledValues);
        npDictionary.put(name, shuffledTensor);
        lastOperationSuccess = true;
    
        return shuffledTensor;
    }

    //Ciclo 3
    /**
     * Divide el tensor en pequeños tensores.
     *
     * @param name nombre del tensor que vamos a dividir
     * @param axis El eje sobre el cual se corta el tensor
     * @param start La posición desde la cual vamos a iniciar la division
     * @param end La posicioón desde en la cual vamos a terminar la division
     * @return Retorna un tensor contenido en el tensor 
     */
      public Tensor slice(String name, int axis, int start, int end) {
          Tensor originalTensor = npDictionary.get(name);
         Tensor slicedTensor = null;

        if (originalTensor != null && axis >= 0 && axis < originalTensor.getShape().length) {
            int[] originalShape = originalTensor.getShape();
            int[] originalValues = originalTensor.getValues();
            int[] newShape = Arrays.copyOf(originalShape, originalShape.length);
            int sliceSize = end - start;
            newShape[axis] = sliceSize;
            int[] newValues = new int[sliceSize];
            int originalStride = 1;
            for (int i = originalShape.length - 1; i > axis; i--) {
            originalStride *= originalShape[i];
            }
            int newStride = 1;
            for (int i = newShape.length - 1; i > axis; i--) {
                newStride *= newShape[i];
            }
            int sliceIndex = 0;
            int originalIndex = start * originalStride;
            while (sliceIndex < sliceSize) {
                for (int i = 0; i < originalStride; i++) {
                    newValues[sliceIndex] = originalValues[originalIndex + i];
                    sliceIndex++;
                }
                originalIndex += originalStride;
            }
            slicedTensor = new Tensor(newShape, newValues);
            lastOperationSuccess = true;
        } else {
            lastOperationSuccess = false;
        }

        return slicedTensor;
    }


    /**
     * Calcula el valor medio de un tensor a lo largo de un eje.
     *
     *@param name El nombre de la variable que contiene el tensor para calcular la media.
     * @param axis El eje a lo largo del cual se calculará la media.
     * @return El valor medio a lo largo del eje especificado.
     */
    public double mean(String name, int axis) {
        Tensor originalTensor = npDictionary.get(name);
        double result = Double.NaN;
    
        if (originalTensor != null) {
            int[] originalShape = originalTensor.getShape();
            int[] originalValues = originalTensor.getValues();
    
            if (axis >= 0 && axis < originalShape.length) {
                int axisSize = originalShape[axis];
                int stride = 1;
                for (int i = originalShape.length - 1; i > axis; i--) {
                    stride *= originalShape[i];
                }
                double sum = 0.0;
                int count = 0;
                
                for (int i = 0; i < originalValues.length; i++) {
                    if (i % stride == 0) {
                        int axisIndex = (i / stride) % axisSize;
                        sum += originalValues[i];
                        count++;
                    }
                }
                
                if (count > 0) {
                    result = sum / count;
                    lastOperationSuccess = true;
                } else {
                    lastOperationSuccess = false;
                }
            } else {
                lastOperationSuccess = false;
            }
        } else {
            lastOperationSuccess = false;
        }
    
        return result;
    }


    /**
    * Encuentra los índices de los elementos en un tensor que son iguales a un valor especificado.
    *
    * @param nombre El nombre de la variable que contiene el tensor en el que se va a buscar.
    * @param valor El valor que se busca en el tensor.
    * @return Un arreglo de índices donde se encuentra el valor especificado en el tensor.
    */
    public int[] find(String name, int value) {
        Tensor originalTensor = npDictionary.get(name);
        if (originalTensor == null) {
            lastOperationSuccess = false;
            return new int[0];
        }
        int[] originalValues = originalTensor.getValues();
        int[] indices = new int[0];
        for (int i = 0; i < originalValues.length; i++) {
            if (originalValues[i] == value) {
                indices = Arrays.copyOf(indices, indices.length + 1);
                indices[indices.length - 1] = i;
            }
        }
        lastOperationSuccess = true;
        return indices;
    }
    
    //Ciclo 4
    /**
    * Suma de dos tensores de 3 dimensiones.
    *
    * @param t1 Tensor número 1.
    * @param t2 Tensor número 2.
    * @return res devuelve la suma de los dos tensores con la misma forma.
    */
    public int[][][] suma(Tensor t1, Tensor t2){
        int[] shape1 = t1.getShape();
        int[] shape2 = t2.getShape();
        int[][][] res = new int[shape1[0]][shape1[1]][shape1[2]];
        if(shape1.length != shape2.length || shape1[0] != shape2[0] || shape1[1] != shape2[1] || shape1[2] != shape2[2]){
            throw new IllegalArgumentException("Los tensores deben tener la misma forma para la suma.");
        }
        int[] indice = new int[3];
        for (int i = 0; i < shape1[0]; i++) {
            indice[0] = i;
            for (int j = 0; j < shape1[1]; j++) {
                indice[1] = j;
                for (int z = 0; z < shape1[2]; z++) {
                    indice[2] = z;
                    res[i][j][z] = t1.value(indice) + t2.value(indice);
                }
            }
        }
        return res;
    }
    
    /**
    * Resta de dos tensores.
    *
    * @param t1 Tensor número 1.
    * @param t2 Tensor número 2.
    * @return res devuelve la resta de los dos tensores con la misma forma.
    */
    public int[][][] resta(Tensor t1, Tensor t2){
        int[] shape1 = t1.getShape();
        int[] shape2 = t2.getShape();
        int[][][] res = new int[shape1[0]][shape1[1]][shape1[2]];
        if(shape1.length != shape2.length || shape1[0] != shape2[0] || shape1[1] != shape2[1] || shape1[2] != shape2[2]){
            throw new IllegalArgumentException("Los tensores deben tener la misma forma para la suma.");
        }
        int[] indice = new int[3];
        for (int i = 0; i < shape1[0]; i++) {
            indice[0] = i;
            for (int j = 0; j < shape1[1]; j++) {
                indice[1] = j;
                for (int z = 0; z < shape1[2]; z++) {
                    indice[2] = z;
                    res[i][j][z] = t1.value(indice) - t2.value(indice);
                }
            }
        }
        return res;
    }
    
    /**
    * Multiplicación de dos tensores.
    *
    * @param t1 Tensor número 1.
    * @param t2 Tensor número 2.
    * @return res devuelve la resta de los dos tensores con la misma forma.
    */
    public int[][][] multiplicacion(Tensor t1, Tensor t2){
        int[] shape1 = t1.getShape();
        int[] shape2 = t2.getShape();
        int[][][] res = new int[shape1[0]][shape1[1]][shape1[2]];
        if(shape1.length != shape2.length || shape1[0] != shape2[0] || shape1[1] != shape2[1] || shape1[2] != shape2[2]){
            throw new IllegalArgumentException("Los tensores deben tener la misma forma para la suma.");
        }
        int[] indice = new int[3];
        for (int i = 0; i < shape1[0]; i++) {
            indice[0] = i;
            for (int j = 0; j < shape1[1]; j++) {
                indice[1] = j;
                for (int z = 0; z < shape1[2]; z++) {
                    indice[2] = z;
                    res[i][j][z] = t1.value(indice) * t2.value(indice);
                }
            }
        }
        return res;
    }
    
    //Ciclo 5
        /**
        *Halla el tensor de forma organizada ascendentemente
        *
        *@param tensor, tensor al cual se le desea sacar la raiz cubica
        *@return retorna el tensor ordenado
        */
    
      public Tensor sort(Tensor tensor) {
            if (tensor.getShape().length > 2) {
                throw new UnsupportedOperationException("El ordenamiento de tensores de dimensión mayor a 2 no está implementado.");
            }
        
            int[] shape = tensor.getShape();
            int[] values = tensor.getValues();
        
            Arrays.sort(values);
        
            Tensor sortedTensor = new Tensor(shape, values);
        
            return sortedTensor;
      }
    
        /**
        *Halla la raiz cubica de un tensor
        *
        *@param t1 tensor al cual se le desea sacar la raiz cubica
        *@return retorna la raiz cubica del tensor
        */
      
      public int[][][] cubeRoot(Tensor t1){
        int[] shape1 = t1.getShape();
        int[][][] res = new int[shape1[0]][shape1[1]][shape1[2]];
        int[] indice = new int[3];

        for (int i = 0; i < shape1[0]; i++) {
            indice[0] = i;
            for (int j = 0; j < shape1[1]; j++) {
                indice[1] = j;
                for (int z = 0; z < shape1[2]; z++) {
                    indice[2] = z;
                    if(t1.value(indice) < 0){
                        throw new IllegalArgumentException("No se puede aplicar la raiz cubica a un valor menor a 0");
                    }
                    res[i][j][z] = (int) Math. cbrt(t1.value(indice));
                }
            }
        }
        return res;
      }
    
    /**
    *Halla la raiz cubica de un tensor
    *
    *@param t1 tensor al cual se le desea sacar la raiz cubica
    *@return retorna la raiz cubica del tensor
    */
    
    public int[][][] division(Tensor t1, Tensor t2){
        int[] shape1 = t1.getShape();
        int[] shape2 = t2.getShape();
        int[][][] res = new int[shape1[0]][shape1[1]][shape1[2]];
        if(shape1.length != shape2.length || shape1[0] != shape2[0] || shape1[1] != shape2[1] || shape1[2] != shape2[2]){
            throw new IllegalArgumentException("Los tensores deben tener la misma forma para la suma.");
        }
        int[] indice = new int[3];
        for (int i = 0; i < shape1[0]; i++) {
            indice[0] = i;
            for (int j = 0; j < shape1[1]; j++) {
                indice[1] = j;
                for (int z = 0; z < shape1[2]; z++) {
                    indice[2] = z;
                    if(t2.value(indice) == 0){
                        throw new IllegalArgumentException("No se puede dividir por 0");
                    }
                    res[i][j][z] = t1.value(indice) / t2.value(indice);
                }
            }
        }
        return res;
    }
}
    



