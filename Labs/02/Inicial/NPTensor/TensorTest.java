import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class TensorTest {

    
    @BeforeClass
    public static void beforeClass(){
    }
    
    @Before
    public void before(){
    }
    
    //Casos de prueba para ver como se comportan
    @Test
    public void shouldPass(){
        int a = 1;
        int b = 1;
        
        assertEquals (2 , a + b);
    }
    
    @Test
    public void shouldFail(){
        int a = 1;
        int b = 1;
        
        assertEquals (1 , a + b);
    }
    
    @Test
    public void shouldErr(){
        int a = 1;
        int b = 1;
        int c = 0;
        
        assertEquals (1 , a + b/c);
    }
    
    @Test
    public void shouldCreateTensor() {
        int [] shape = {1,2,3};
        Tensor t = new Tensor(shape,5);
        int [] one = {0,0,0};
        assertEquals(5,t.value(one));
        int [] two = {0,1,2};
        assertEquals(5,t.value(two));
    }


    @Test
    public void shouldKnowWhenTwoTensorAreEquals () {
        int [] shapeA= {3,4,5};
        int [] shapeB= {6,7,8};
        int [] shapeC= {3,4,5,6};
        assertTrue (new Tensor(shapeA,5).equals(new Tensor(shapeA,5)));
        assertFalse(new Tensor(shapeA,5).equals(new Tensor(shapeA,6)));
        assertFalse(new Tensor(shapeA,5).equals(new Tensor(shapeB,5)));
        assertFalse(new Tensor(shapeA,5).equals(new Tensor(shapeC,5)));
        assertEquals(new Tensor(shapeA,5), new Tensor(shapeA,5));
    }
    

    @Test
    public void shouldRepresentATensorAsAString() {
        int [] shapeA = {3, 3} ;
        int [] shapeB = {2, 2, 3};
        assertEquals("[[1, 1, 1], [1, 1, 1], [1, 1, 1]]", new Tensor(shapeA, 1).toString());
        assertEquals("[[[5, 5, 5], [5, 5, 5]], [[5, 5, 5],[5, 5, 5]]]", new Tensor(shapeB, 5).toString());
    }   
    

    @Test
    public void shouldAdd() {
        int [] shapeA = {3, 3};
        int [] shapeB = {2, 2, 3};
        Tensor tB= new Tensor(shapeB, 0);
        assertEquals(new Tensor(shapeA, 6), new Tensor(shapeA, 1).add(new Tensor(shapeA, 5)));
        assertEquals(tB, tB.add(new Tensor(shapeB, 0)));
    }

}
