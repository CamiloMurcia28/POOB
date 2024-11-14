import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

public class NPTensorTest {
    private NPTensor npTensor;
    private NPTensor tensorManager;
    private NPTensor tensorManager1;
        
    // PRUEBAS CICLO 1
    @Before
    public void setUp() {
        npTensor = new NPTensor();
    }
    
    @Test
    public void testAssignWithValue() {
        int[] shape = {3, 3};
        int value = 5;
        npTensor.assign("A", shape, value);
    
        assertTrue(npTensor.ok());
        assertEquals(5, npTensor.getValue("A", new int[]{0, 0}));
        assertEquals(5, npTensor.getValue("A", new int[]{2, 2}));
    }
    
    @Test
    public void testAssignWithValue2() {
        int[] shape = {3, 3};
        int value = 10;
        npTensor.assign("B", shape, value);
    
        assertTrue(npTensor.ok());
        assertEquals(10, npTensor.getValue("B", new int[]{0, 0}));
        assertEquals(10, npTensor.getValue("B", new int[]{2, 2}));
    }
    
    @Test
    public void testAssignWithValue3() {
        int[] shape = {3, 3};
        int value = 12;
        npTensor.assign("C", shape, value);
    
        assertTrue(npTensor.ok());
        assertEquals(12, npTensor.getValue("C", new int[]{0, 0}));
        assertEquals(12, npTensor.getValue("C", new int[]{2, 2}));
    }
    
    @Test
    public void testAssignWithInvalidValues() {
        int[] shape = {2, 2};
        int[] values = {1, 2, 3}; // Invalid number of values
    
        npTensor.assign("C", shape, values);
    
        assertFalse(npTensor.ok());
    }
    
    @Test
    public void testAssignWithInvalidValues1() {
        int[] shape = {2, 2};
        int[] values = {1, 2, 3, 4, 5}; // Too many values
    
        npTensor.assign("D", shape, values);
    
        assertFalse(npTensor.ok());
    }
    
    @Test
    public void testAssignWithInvalidValues2() {
        int[] shape = {2, 2};
        int[] values = {1, 2, 3,3,3,3,3, 4}; // Invalid number of values
    
        npTensor.assign("E", shape, values);
    
        assertFalse(npTensor.ok());
    }
    
    @Test
    public void testGetValueWithInvalidName() {
        int[] shape = {2, 2};
        int value = 5;
    
        npTensor.assign("D", shape, value);
    
        assertEquals(-1, npTensor.getValue("InvalidName", new int[]{0, 0}));
    }
    
    @Test
    public void testGetValueWithInvalidName1() {
        int[] shape = {2, 2};
        int value = 5;
    
        npTensor.assign("D", shape, value);
    
        assertEquals(-1, npTensor.getValue(null, new int[]{0, 0}));
    }
    
    @Test
    public void testGetValueWithInvalidName2() {
        int[] shape = {2, 2};
        int value = 5;
    
        npTensor.assign("D", shape, value);
    
        assertEquals(-1, npTensor.getValue("X", new int[]{-1, 0}));
    }
    
    //PRUEBAS CICLO 2
    @Before
    public void setUp2() {
        tensorManager = new NPTensor();
    }
    
    @Test
    public void testShapeNonExistent() {
        String shapeStr = tensorManager.shape(tensorManager.npDictionary.get("non_existent_tensor"));
        assertEquals("Tensor nulo", shapeStr);
    }
    
    @Test
    public void testShuffleNonExistent() {
        Tensor shuffledTensor = tensorManager.shuffle("non_existent_tensor");
        assertNull(shuffledTensor);
    }
    
    //PRUEBAS CICLO 3
    @Before
    public void setUp3() {
        tensorManager = new NPTensor();
    }

    @Test
    public void testSlice() {
        int[] shape = {2, 3};
        int[] values = {1, 2, 3, 4, 5, 6};
        tensorManager.assign("tensor2", shape, values);

        Tensor slicedTensor = tensorManager.slice("tensor2", 2, 0, 2);
        assertNull(slicedTensor);
    }

    @Test
    public void testSliceInvalid() {
        int[] shape = {2, 3};
        int[] values = {1, 2, 3, 4, 5, 6};
        tensorManager.assign("tensor3", shape, values);

        Tensor slicedTensor = tensorManager.slice("tensor3", 0, 2, 4);
        assertNull(slicedTensor);
    }

    @Test
    public void testMeanInvalid() {
        int[] shape = {3, 3};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        tensorManager.assign("tensor5", shape, values);

        double mean = tensorManager.mean("tensor5", 2);
        assertEquals(Double.NaN, mean, 0.01);
    }

    @Test
    public void testFind() {
        int[] shape = {2, 2};
        int[] values = {1, 2, 3, 4};
        tensorManager.assign("tensor7", shape, values);

        int[] indices = tensorManager.find("tensor7", 5);
        assertArrayEquals(new int[0], indices);
    }
    
    //PRUEBAS CICLO 4
    @Before
    public void setUp4() {
        tensorManager = new NPTensor();
        tensorManager1 = new NPTensor();
    }

    @Test
    public void testSum() {
        int[] shape1 = {1,2,3};
        int[] shape2 = {1,2,3};
        int values = 1;
        Tensor t1 = new Tensor(shape1, values);
        Tensor t2 = new Tensor(shape2, values);
        
        int[][][] tensorSuma = tensorManager.suma(t1, t2);
        
        for (int i = 0; i< shape1[0]; i++){
            for (int j = 0; j< shape1[1]; j++){
                for (int z = 0; z< shape1[2]; z++){
                    assertEquals(2, tensorSuma[i][j][z]);    
                }
            }
        }
    }

    @Test
    public void testRest() {
        int[] shape1 = {1,2,3};
        int[] shape2 = {1,2,3};
        int values = 1;
        Tensor t1 = new Tensor(shape1, values);
        Tensor t2 = new Tensor(shape2, values);
        
        int[][][] tensorResta = tensorManager.resta(t1, t2);
        
        for (int i = 0; i< shape1[0]; i++){
            for (int j = 0; j< shape1[1]; j++){
                for (int z = 0; z< shape1[2]; z++){
                    assertEquals(0, tensorResta[i][j][z]);    
                }
            }
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReshapeInvalid() {
        
        int[] initialShape = {2, 3};
        int[] testData = {1, 2, 3, 4, 5, 6};
        Tensor tensor = new Tensor(initialShape, testData);
    
            
        int[] newShape = {3, 4}; 
    
            
        tensor.reshape(newShape);
    }
    
    @Test
    public void testMulti() {
        int[] shape1 = {1,2,3};
        int[] shape2 = {1,2,3};
        int values = 1;
        Tensor t1 = new Tensor(shape1, values);
        Tensor t2 = new Tensor(shape2, values);
        
        int[][][] tensorMul = tensorManager.multiplicacion(t1, t2);
        
        for (int i = 0; i< shape1[0]; i++){
            for (int j = 0; j< shape1[1]; j++){
                for (int z = 0; z< shape1[2]; z++){
                    assertEquals(1, tensorMul[i][j][z]);    
                }
            }
        }
    }
    
    @Test
    public void testDiv() {
        int[] shape1 = {1,2,3};
        int[] shape2 = {1,2,3};
        int values = 1;
        Tensor t1 = new Tensor(shape1, values);
        Tensor t2 = new Tensor(shape2, values);
        
        int[][][] tensorDiv = tensorManager.division(t1, t2);
        
        for (int i = 0; i< shape1[0]; i++){
            for (int j = 0; j< shape1[1]; j++){
                for (int z = 0; z< shape1[2]; z++){
                    assertEquals(1, tensorDiv[i][j][z]);    
                }
            }
        }
    }
    
    @Test
    public void testSort() {
        Tensor tensor = new Tensor(new int[]{3, 3}, new int[]{1, 5, 2, 3, 6, 4, 7, 8, 9});
        
        Tensor sortedTensor = tensorManager.sort(tensor);
        
        int[] expectedValues = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        
        for (int i = 0; i < sortedTensor.getShape()[0]; i++) {
            for (int j = 0; j < sortedTensor.getShape()[1]; j++) {
                assertEquals(expectedValues[i * 3 + j], sortedTensor.value(new int[]{i, j}));
            }
        }
    }
    
    @Test
    public void testCubeRoot() {
        int[] shape1 = {1,2,3};
        int values = 27;

        Tensor t1 = new Tensor(shape1, values);

        int[][][] res = tensorManager.cubeRoot(t1);

        for(int i = 0; i < res.length; i++){
            for(int j = 0; j < res[0].length;j++){
                for(int k = 0; k < res[0][0].length; k++){
                    assertEquals(3, res[i][j][k]);    
                }
            }
        }
    }
}
    
  
