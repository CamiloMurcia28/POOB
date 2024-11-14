package CostumeTest;
import domain.*;
import CostumeShop2023.*;
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
    
    //PRUEBAS DE ADICIONAR

    @Test
    public void shouldAddCompleteCostumeListSuccess() throws CostumeShopException{
        CostumeShop c = new CostumeShop();
        c.addBasic("Camisa rota", "20000", "0");
        c.addBasic("Pantalon sucio", "10000", "0");
        c.addBasic("Zapatos viejos", "10000", "0");

        String name = "Zombie";
        String makeUp = "10000";
        String discount = "10";
        String theBasics = "Camisa rota\nPantalon sucio\nZapatos viejos";
        c.addComplete(name, makeUp, discount, theBasics);
        assertEquals(4, c.getSizeCostumes());
    }
    
    @Test
    public void shouldAddSimpleCostumeListSuccess() throws CostumeShopException{
        CostumeShop c = new CostumeShop();
        c.addBasic("Camisa rota", "20000", "0");
        assertEquals(1, c.getSizeCostumes());
    }
    
    @Test
    public void ShouldAddAndListCostumes() throws CostumeShopException {
        CostumeShop costumeShop = new CostumeShop();
        costumeShop.addBasic("orejas perro", "20000", "0");
        costumeShop.addBasic("traje perro", "10000", "0");
        costumeShop.addBasic("Cola perro", "10000", "0");
    
        String name = "Perro";
        String makeUp = "10000";
        String discount = "10";
        String theBasics = "orejas perro\ntraje perro\nCola perro";
        costumeShop.addComplete(name, makeUp, discount, theBasics);
    
        String costumeList = costumeShop.toString();
        assertEquals(4, costumeShop.getSizeCostumes());
        String expectedCostumeList = costumeShop.toString();
        assertEquals(expectedCostumeList, costumeList);
    }
    
    //ADICIONAR
    //duplicate
    @Test
    public void shouldNotAddDuplicateCostume() throws CostumeShopException{
        CostumeShop c = new CostumeShop();
        try {
            c.addBasic("Camisa rota", "20000", "0");
            c.addBasic("Camisa rota", "20000", "0");
            fail("Se ingresaron duplicados");
        } catch (CostumeShopException e) {
            assertEquals(CostumeShopException.COSTUME_DUPLICATE, e.getMessage());
        }
    }
    
    //price error
    @Test
    public void shouldNotAddNegativePrice() throws CostumeShopException{
        CostumeShop costumeShop = new CostumeShop();
        try {
            costumeShop.addBasic("Camisa blanca", "-20000", "0");
            fail("Se agrego el precio negativo");
        } catch (CostumeShopException e) {
            assertEquals(CostumeShopException.PRICE_ERROR, e.getMessage());
        }
    }

    @Test
    public void shouldNotAddInvalidPrice()throws CostumeShopException {
        CostumeShop costumeShop = new CostumeShop();
        try {
            costumeShop.addBasic("Camisa Negra", "1o", "0");
            fail("Se agrego un precio invalido");
        } catch (CostumeShopException e) {
            assertEquals(CostumeShopException.PRICE_ERROR, e.getMessage());
        }
    }
    
    @Test
    public void shouldNotAddDiscountLessThanZero()throws CostumeShopException {
        CostumeShop costumeShop = new CostumeShop();
        try {
            costumeShop.addBasic("Camisa Negra", "10000", "-10");
            fail("Se permitió agregar un descuento invalido");
        } catch (CostumeShopException e) {
            assertEquals(CostumeShopException.NEGATIVE_DISCOUNT, e.getMessage());
        }
    }
    
    //Capa Dominio Buscar
    
    @Test
    public void shouldNotFindCostume() throws CostumeShopException{
        CostumeShop costumeShop = new CostumeShop();
        try{
            costumeShop.search("Camiseta");
        }catch(Exception e){
            fail("Hubo un fallo");
        }
    }
}
