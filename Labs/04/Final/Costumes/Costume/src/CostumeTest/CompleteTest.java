package CostumeTest;
import domain.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CompleteTest{
    
    public CompleteTest(){
    }

    @Before
    public void setUp(){    
    }

    @After
    public void tearDown(){
    }
    
     //PRICE()
    @Test
    public void shouldCalculateTheCostOfACompleteCostume(){
        Complete c = new Complete("El zorro", 10000, 0);
        c.addBasic(new Basic("Camisa blanca", 20000, 0));
        c.addBasic(new Basic("Pantalon negro", 10000, 0));
        c.addBasic(new Basic("Capa negra", 10000, 0));
        try {
           assertEquals(50000,c.price());
        } catch (CostumeShopException e){
            fail("Threw a exception");
        }    
    }    
    
    @Test
    public void shouldCalculateTheCostOfACompleteCostumeWithDiscount(){
        Complete c = new Complete("El zorro", 10000, 10);
        c.addBasic(new Basic("Camisa blanca", 20000, 10));
        c.addBasic(new Basic("Pantalon negro", 10000, 10));
        c.addBasic(new Basic("Capa negra", 10000, 10));
        try {
           assertEquals(41400,c.price());
        } catch (CostumeShopException e){
            fail("Threw a exception");
        }    
    }  
    
    @Test
    public void shouldThrowExceptionIfACompleteCostumeHasNoBasicCustom(){
        Complete c= new Complete("El zorro", 10000, 10);
        try { 
           int price=c.price();
           fail("Did not throw exception");
        } catch (CostumeShopException e) {
            assertEquals(CostumeShopException.COMPLETE_EMPTY,e.getMessage());
        }    
    }    
    
    
   @Test
    public void shouldThrowExceptionIfThereIsErrorInPrice(){
        Complete c = new Complete("El zorro", 10000, 0);
        c.addBasic(new Basic("Camisa blanca", 20000, 0));
        c.addBasic(new Basic("Pantalon negro", -10000, 0));
        c.addBasic(new Basic("Capa negra", 10000, 0));
        try { 
           int price=c.price();
           fail("Did not throw exception");
        } catch (CostumeShopException e) {
            assertEquals(CostumeShopException.PRICE_ERROR,e.getMessage());
        }    
    }     
    
   @Test
    public void shouldThrowExceptionIfPriceIsNotKnown(){
        Complete c = new Complete("El zorro", 10000, 0);
        c.addBasic(new Basic("Camisa blanca", null, 0));
        c.addBasic(new Basic("Pantalon negro", 10000, 0));
        c.addBasic(new Basic("Capa negra", 10000, 0));
        try { 
           int price=c.price();
           fail("Did not throw exception");
        } catch (CostumeShopException e) {
            assertEquals(CostumeShopException.PRICE_EMPTY,e.getMessage());
        }    
    }  
    
    //PRICE(TYPE)
    @Test
    public void shouldCalculateThePriceWithATypeFirst() {
        Complete c = new Complete("El zorro", 10000, 0);
        c.addBasic(new Basic("Camisa blanca", 10000, 0));
        c.addBasic(new Basic("Pantalon negro", 10000, 0));
        c.addBasic(new Basic("Capa negra", 10000, 0));
        try {
            assertEquals(30000, c.price("first"));
        } catch (CostumeShopException e) {
            fail("Threw a exception");
        }
    }

    @Test
    public void shouldCalculateThePriceWithATypeLast() {
        Complete c = new Complete("El zorro", 10000, 0);
        c.addBasic(new Basic("Camisa blanca", 10000, 0));
        c.addBasic(new Basic("Pantalon negro", 10000, 0));
        c.addBasic(new Basic("Capa negra", 40000, 0));
        try {
            assertEquals(60000, c.price("last"));
        } catch (CostumeShopException e) {
            fail("Threw a exception");
        }
    }
    
    @Test
    public void shouldCalculateTheCostOfACompleteCostumeWithDiscountTYPE(){
        Complete c = new Complete("El zorro", 10000, 10);
        c.addBasic(new Basic("Camisa blanca", 20000, 10));
        c.addBasic(new Basic("Pantalon negro", 10000, 10));
        c.addBasic(new Basic("Capa negra", 10000, 10));
        try {
           assertEquals(36000,c.price("first"));
        } catch (CostumeShopException e){
            fail("Threw a exception");
        }    
    } 
    
   @Test
    public void shouldThrowExceptionIfACompleteCostumeHasNoBasicCustomType() {
        Complete c = new Complete("El zorro", 10000, 10);
        try {
            c.price("first");
            fail("Did not throw exception");
        } catch (CostumeShopException e) {
            assertEquals(CostumeShopException.COMPLETE_EMPTY, e.getMessage());
        }
    }

   @Test
    public void shouldThrowExceptionIfTypeIsDifferentToFirtOrLast() {
        Complete c = new Complete("El zorro", 10000, 0);
        c.addBasic(new Basic("Camisa blanca", 10000, 0));
        c.addBasic(new Basic("Pantalon negro", 10000, 0));
        c.addBasic(new Basic("Capa negra", 40000, 0));
        
        try {
            int price = c.price("invalidType"); // Cambia "type" a "invalidType"
        } catch (CostumeShopException e) {
            assertEquals(CostumeShopException.IMPOSSIBLE_COMPLETE, e.getMessage());
        }
    }
    
    //PRICE(makeUP)
    
    @Test
    public void shouldCalculatePriceMakeUp() {
        Complete c = new Complete("El zorro", 10000, 0);
        c.addBasic(new Basic("Camisa blanca", 20000, 0));
        c.addBasic(new Basic("Pantalon negro", 10000, 0));
        c.addBasic(new Basic("Capa negra", null, 0));
        try {
            int totalPrice = c.price(c.isMakeUp());
            assertEquals(40000, totalPrice); 
        } catch (CostumeShopException e) {
            fail("Threw a exception");
        }
    }
    
    @Test
    public void shouldCalculatePriceFalseMakeUp() {
        Complete c = new Complete("El zorro", 0, 0);
        c.addBasic(new Basic("Camisa blanca", 20000, 0));
        c.addBasic(new Basic("Pantalon negro", 10000, 0));
        try {
            int totalPrice = c.price(c.isMakeUp());
            assertEquals(30000, totalPrice); 
        } catch (CostumeShopException e) {
            fail("Threw a exception");
        }
    }
    
    @Test
    public void shouldNotCalculatePricePriceErrorMakeUp() {
        Complete c = new Complete("El zorro", 10000, 0);
        c.addBasic(new Basic("Camisa blanca", -10000, 0));
        c.addBasic(new Basic("Pantalon negro", 10000, 0));
        c.addBasic(new Basic("Capa negra", 10000, 0));
        try {
            int totalPrice = c.price(c.isMakeUp());
            fail("No arrojo la excepcion");
        } catch (CostumeShopException e) {
            assertEquals(CostumeShopException.PRICE_ERROR, e.getMessage());
        }
    }
    
    @Test
    public void shouldNotCalculatePriceEmptyErrorMakeUp() {
        Complete c = new Complete("El zorro", 10000, 0);
        try {
            int totalPrice = c.price(c.isMakeUp());
            fail("No arrojo la excepcion");
        } catch (CostumeShopException e) {
            assertEquals(CostumeShopException.COMPLETE_EMPTY, e.getMessage());
        }
    }
    
    @Test
    public void shouldThrowPriceUnknownExceptionWhenMakeUpIsFalse() {
        Complete c = new Complete("El zorro", 10000, 0);
        c.addBasic(new Basic("Camisa blanca", 10000, 0));
        c.addBasic(new Basic("Pantalon negro", 10000, 0));
        try {
            int price = c.price(false);
        } catch (CostumeShopException e) {
            assertEquals(CostumeShopException.PRICE_UNKNOWN, e.getMessage());
        }
    }

}
