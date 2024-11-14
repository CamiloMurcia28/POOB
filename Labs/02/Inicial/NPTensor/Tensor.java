import java.util.Arrays;

public class Tensor {

    private int[] shape;
    private int[] values;
    
    public Tensor(int[] shape, int value) {
        this.shape = shape;
        this.values = new int[calculateSize(shape)];
        Arrays.fill(values, value);
    }

    public int[] getShape() {
        int[] res;
        res = this.shape;
        return res;
    }

    public int[] getValues() {
        int[] res;
        res = this.values;
        return res;
    }
    
    public Tensor(int[] shape, int[] values) {
        this.shape = shape;
        if (values.length != calculateSize(shape)) {
            throw new IllegalArgumentException("Length of values must match the size determined by the shape.");
        }
        this.values = Arrays.copyOf(values, values.length);
    }

    public int value(int[] index) {
        int flatIndex = calculateFlatIndex(index);
        return values[flatIndex];
    }

    public Tensor reshape(int[] newShape) {
        int newSize = calculateSize(newShape);
        if (newSize != values.length) {
            throw new IllegalArgumentException("New shape must have the same number of elements.");
        }
        return new Tensor(newShape, values);
    }

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

    public boolean equals(Tensor other) {
        if (!Arrays.equals(this.shape, other.shape)) {
            return false;
        }
        return Arrays.equals(this.values, other.values);
    }

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

    @Override
    public String toString() {
        return buildString(this, 0).replaceAll(", ,", ",");
    }
    
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
    
    private void validateShapeEquality(int[] shape1, int[] shape2) {
        if (!Arrays.equals(shape1, shape2)) {
            throw new IllegalArgumentException("Tensors must have the same shape for addition.");
        }
    }

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

    private int calculateSize(int[] shape) {
        int size = 1;
        for (int dim : shape) {
            size *= dim;
        }
        return size;
    }

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
}