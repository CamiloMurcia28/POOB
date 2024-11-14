import java.util.Arrays;

public class Tensor {

    private int[] shape;
    private int[] values;
    /**
     * Crea un Tensor con una forma dada y un solo valor incial
     * @param shape da forma al tensor representado como un arreglo de enteros dada su dimensión
     * @param value le da un valor al tensor con el cual se inicializa
     */
    public Tensor(int[] shape, int value) {
        this.shape = shape;
        this.values = new int[calculateSize(shape)];
        Arrays.fill(values, value);
    }
    
    /**
     * Crea un Tensor con una forma dada y un solo valor incial
     * @param shape da forma al tensor representado como un arreglo de enteros dada su dimensión
     * @param values le da un arreglo de valores enteros al tensor con los cuales se inicializa
     */

    public Tensor(int[] shape, int[] values) {
        this.shape = shape;
        if (values.length != calculateSize(shape)) {
            throw new IllegalArgumentException("Length of values must match the size determined by the shape.");
        }
        this.values = Arrays.copyOf(values, values.length);
    }
    
    /**
     * Obtiene el valor de un elemento del tensor a partir de una posición indicada.
     * @param index Posición dentro del tensor donde se tiene un valor
     * @return Values Valor de la posición especificada del tensor.
     */

    public int value(int[] index) {
        int flatIndex = calculateFlatIndex(index);
        return values[flatIndex];
    }
    
    /**
     * Cambia la forma de un tensor a una nueva.
     * @param newShape ingresa un arreglo de enteros el cual le da la nueva forma al tensor
     * @return retorna el tensor con una nueva forma
     */

    public Tensor reshape(int[] newShape) {
        int newSize = calculateSize(newShape);
        if (newSize != values.length) {
            throw new IllegalArgumentException("New shape must have the same number of elements.");
        }
        return new Tensor(newShape, values);
    }

    /**
     * Agrega un tensor al que ya tenemos
     * @param t Nombre del tensor que sera agregado
     * @return retorna el tensor con la suma de los elementos del tensor de entrada
     */
    public Tensor add(Tensor t) {
        if (!Arrays.equals(this.shape, t.shape)) {
            throw new IllegalArgumentException("Tensors must have the same shape for addition.");
        }

        int[] resultValues = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            resultValues[i] = this.values[i] + t.values[i];
        }

        return new Tensor(this.shape, resultValues);
    }
    
    /**
     * Compara el tensor con otro tensor para ver si son iguales
     * @param Ingresa otro tensor el cual va a ser comparado con el que se tiene
     * @return retorna un booleano segun sean iguales o distintos
     */
    
    public boolean equals(Tensor other) {
        if (!Arrays.equals(this.shape, other.shape)) {
            return false;
        }
        return Arrays.equals(this.values, other.values);
    }

    /**
     * Compara el tensor con otro objeto para determinar si son iguales
     * @param ingresa un objeto el cual va a ser comparado con el tensor
     * @return retorna un booleano segun sean iguales o distintos
     */
    
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Tensor tensor = (Tensor) other;
        String thisString = this.toString().replaceAll("\\s+", "");
        String otherString = tensor.toString().replaceAll("\\s+", "");
        return thisString.equals(otherString);
    }

    /**
     * retorna el tensor como una representación de un string 
     *@return retorna la representacion de tensor como un string
     */
    @Override
    public String toString() {
        return buildString(this, 0).replaceAll(", ,", ",");
    }
    
    /*
     * Verifica el indice proporcionado para conocer si esta dentro de los limites del tensor
     * @param index posición del arreglo la cual queremos verificar
     */
    private void validateIndex(int[] index) {
        if (index.length != shape.length) {
            throw new IllegalArgumentException("The index must have the same length as the shape.");
        }
        for (int i = 0; i < shape.length; i++) {
            if (index[i] < 0 || index[i] >= shape[i]) {
                throw new IndexOutOfBoundsException("Index is out of bounds for dimension " + i);
            }
        }
    }
    
    /*
     * Valida que dos arreglos sean iguales
     * @param shape1 forma del primer tensor. 
     * @param shape2 forma del segundo tensor.
     */
    private void validateShapeEquality(int[] shape1, int[] shape2) {
        if (!Arrays.equals(shape1, shape2)) {
            throw new IllegalArgumentException("Tensors must have the same shape for addition.");
        }
    }

    /*
     * Construye recursivamente una representacion de la cadena de un tensor
     * @param tensor el tensor el cual va a ser representado como string
     * @param level nivel de recursion
     * @return retorna la representacion del tensor como un string
     */
    private String buildString(Tensor tensor, int level) {
        StringBuilder builder = new StringBuilder();
        if (level == tensor.shape.length - 1) {
            builder.append("[");
            for (int i = 0; i < tensor.shape[level]; i++) {
                builder.append(tensor.values[i]);
                if (i < tensor.shape[level] - 1) {
                    builder.append(", ");
                }
            }
            builder.append("]");
        } else {
            builder.append("[");
            for (int i = 0; i < tensor.shape[level]; i++) {
                builder.append(buildString(tensor, level + 1));
                if (i < tensor.shape[level] - 1) {
                    builder.append(", ");
                }
            }
            builder.append("]");
        }
        return builder.toString();
    }
    
    /*
     * Calcula el total de elementos del tensor basado en la forma
     * @param shape arreglo que da la forma del tensor a partir del cual se toman las dimensiones
     * @return retorna numero todal de elementos del tensor.
     */
    private int calculateSize(int[] shape) {
        int size = 1;
        for (int dim : shape) {
            size *= dim;
        }
        return size;
    }

    /*
     * Calcula un indice plano a partir de un indice multidimensional basado en la forma del tensor
     * @param index posición del arreglo multidimensional la cual queremos verificar
     * @return retorna el valor de la posicion del arreglo multidimensional la cual queremos verificar
     */
    private int calculateFlatIndex(int[] index) {
        if (index.length != shape.length) {
            throw new IllegalArgumentException("Index must have the same length as the shape.");
        }
        int flatIndex = 0;
        int stride = 1;
        for (int i = shape.length - 1; i >= 0; i--) {
            flatIndex += index[i] * stride;
            stride *= shape[i];
        }
        return flatIndex;
    }
    
    /**
     * obtiene la forma del tensor
     * @return retorna el arreglo de enteros que representa la forma del tensor
     */
    public int[] getShape() {
        int[] res;
        res = this.shape;
        return res;
    }
    
     /**
     * obtiene los valores del tensor
     * @return retorna el arreglo de enteros que representa los valores del tensor
     */
    public int[] getValues() {
        int[] res;
        res = this.values;
        return res;
    }
}