package test;
import iceepeecee.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
public class IceepeeceeCC4Test {
    private Iceepeecee iceepeecee;
    
    @BeforeEach
    public void setUp() {
        iceepeecee = new Iceepeecee(1000,1000); // Crea una instancia de Iceepeecee para las pruebas
    }
    
    
    /*Lesmes*/

@BeforeEach

    public void setUp() {

        iceepeecee = new Iceepeecee(500,500);

    }



//AddFlightLAZY

    @Test

    public void accordingLPShouldTakeOnlyAPhotoLAZY() throws java.lang.reflect.InvocationTargetException{

        int[] from = {0,30,20};

        int[] to = {78,70,5};

        iceepeecee.addFlight("LazyFlight","#EA3333", from, to);

        iceepeecee.photograph(30);

        iceepeecee.photograph(60);

        

        assertEquals(30, iceepeecee.flightCamera("#EA3333"));

    }

    @Test

    public void accordingLPNotShouldAddFlightTypeLazy() throws java.lang.reflect.InvocationTargetException {

        int[] from = {0,30,20};

        int[] to = {78,70,5};

        iceepeecee.addFlight("LazyFlight1","#EA3333", from, to);

        assertFalse(iceepeecee.ok());

    }

//AddFlightFLAT

    @Test

    public void accordingLPShouldAddFlightTypeFlat() throws java.lang.reflect.InvocationTargetException {

        int[] from = {0,30,20};

        int[] to = {78,70,5};

        iceepeecee.addFlight("FlatFlight","#EA3333", from, to);

        assertTrue(iceepeecee.ok());

    }

    @Test

    public void accordingLPShouldBeTheSameAltitudeFlat() throws java.lang.reflect.InvocationTargetException {

        int[] from = {0,30,20};

        int[] to = {78,70,5};        

        iceepeecee.addFlight("FlatFlight","#EA3333", from, to);

        int[][]location = iceepeecee.flightLocation("#EA3333");

        assertTrue(location[0][location[0].length-1] == location[1][location[0].length - 1 ]);

    }

//AddIslandFixed

    @Test

    public void accordindLPShouldNotBeDeletedIslandFIXED() throws java.lang.reflect.InvocationTargetException{

        int[][] polygon = {{20,30},{50,50},{10,50}};

        iceepeecee.addIsland("FixedIsland","#EA3333", polygon);

        iceepeecee.delIsland("#EA3333");

        assertEquals(1, iceepeecee.islands().length);

    }

    @Test

    public void accordingLPNotShouldAddIslandTypeFixed() throws java.lang.reflect.InvocationTargetException {

        int[][] polygon = {{20,30},{50,50},{10,50}};

        iceepeecee.addIsland("FixedIsland9","#EA3333", polygon);

        assertFalse(iceepeecee.ok());

    }

//AddIslandSurprising

    @Test

    public void accordindLPShouldBeLessPointsSurprising() throws java.lang.reflect.InvocationTargetException{

        int[][] polygon = {{20,30},{50,50},{10,50},{40,30}};

        iceepeecee.addIsland("SurprisingIsland","#EA3333", polygon);

        assertEquals(3, iceepeecee.islandLocation("#EA3333").length);

    }

    @Test

    public void accordingLPNotShouldAddIslandTypeSurprising() throws java.lang.reflect.InvocationTargetException {

        int[][] polygon = {{20,30},{50,50},{10,50}};

        iceepeecee.addIsland("SurprisingIsland0","#EA3333", polygon);

        assertFalse(iceepeecee.ok());

    }

/********/



//Forero-Murcia

@Test

    public void accordingFMShouldAddNormalIslandThatHaveNormalLocationAndCanBeDeleted() {

        int [][] location = {{0,0},{50,50},{100,50}};

        manage.addIsland("normal","Red",location);

        assertTrue(manage.OK());

        int [][] newLocation = manage.islandLocation("Red");

        for (int i = 0 ; i < location.length ;i++){

            assertEquals(location[i][0],newLocation[i][0]);

            assertEquals(location[i][1],newLocation[i][1]);

        }

        assertTrue(manage.OK());

        manage.delIsland("Red");

        assertTrue(manage.OK());

    }

    

    @Test

    public void accordingFMShouldAddFixedIslandThatCantBeDeleted() {

        manage.addIsland("fixed","Red",new int[][]{{0,0},{50,50},{100,50}});

        assertTrue(manage.OK());

        manage.delIsland("Red");

        assertFalse(manage.OK());

    }

    

    @Test

    public void accordingFMShouldAddSurprisingThatModifyTheLocation() {

        int [][] location = {{0,0},{50,0},{50,50},{25,60},{0,50}};

        manage.addIsland("surprising","Red",location);

        assertTrue(manage.OK());

        assertTrue(manage.OK());

        manage.islandLocation("Red");

        manage.islandLocation("Red");

    }

    @Test

    public void accordingFMShouldAddNormalFlightPassingTheTypeThatCanMakeManyPhotos() {

        int[] from = {0,0,20};

        int[] to = {20,20,40};

        manage.addFlight("normal","Red",from,to);

        assertTrue(manage.OK());

        manage.photograph("Red",45);

        assertTrue(manage.OK());

        manage.photograph("Red",70);

        assertTrue(manage.OK());

        manage.photograph("Red",15);

        assertTrue(manage.OK());

    }

    

    @Test

    public void accordingFMShouldNotAddFlatFlightPassingTheTypeThatGainOrLoseHeight() {

        int[] from = {0,0,20};

        int[] to = {20,20,30};

        manage.addFlight("flat","Red",from,to);

        assertFalse(manage.OK());

        to = new int[]{20,20,10};

        manage.addFlight("flat","Red",from,to);

        assertFalse(manage.OK());

    }

    

    @Test

    public void accordingFMShouldAddLazyFlightPassingTheTypeThatOnlyCantTakeOnePhoto() {

        int[] from = {0,0,20};

        int[] to = {20,20,40};

        manage.addFlight("lazy","Red",from,to);

        assertTrue(manage.OK());

        manage.photograph("Red",45);

        assertTrue(manage.OK());

        manage.photograph("Red",70);

        assertFalse(manage.OK());

        manage.photograph("Red",15);

        assertFalse(manage.OK());

    }



//CASALLAS-MURCIA

//CICLO 4



@Test

    public void accordingCMShouldSurprisingIslandLessPoints(){

        int[][] polygon = {{20,30},{50,50},{10,50},{40,30}};

        iceepeecee.addIsland("SurprisingIsland","#800080", polygon);

        assertEquals(3, iceepeecee.islandLocation("#800080").length);

    }



@Test

    public void accordingCMShouldNotAddSurprisingIslandDuplicateColor() {

        int[][] polygon1 = {{10, 10}, {20, 10}, {20, 20}, {10, 20}};

        int[][] polygon2 = {{30, 30}, {40, 30}, {40, 40}, {30, 40}};

        

        iceepeecee.addIsland("SurprisingIsland","#0000FF", polygon1);

        iceepeecee.addIsland("SurprisingIsland","#0000FF", polygon2);

        assertFalse(iceepeecee.ok());

    }



@Test

    public void accordingCMShouldFlatFlightSameAltitude2(){

        int[] from = {0,30,20};

        int[] to = {78,70,5};

        iceepeecee.addFlight("FlatFlight","#CD5C5C",from,to);

        int altitude = iceepeecee.flightLocation("#CD5C5C")[1][2];

        assertTrue(from[2]==altitude);

    }

@Test

    public void accordingCMShouldNotAddFlatFlightWithLessThanThreeCoordinates(){

        int[] from1 = {100,100};

        int[] to1 = {700,780};

        iceepeecee.addFlight("FlatFlight","#FF0000",from1,to1);

        assertFalse(iceepeecee.ok());

    }    
//Chicuazuque-Sierra

@Test

    public void agregarIslasPorTipoCorrectamenteChicuazuqueSierra()throws Exception{
         Iceepeecee input1 = new Iceepeecee(100, 100);

        input1.addIsland("Normal","blue", new int[][]{{20, 30}, {50, 50}, {10, 50}});

        assertTrue(input1.ok());

        input1.addIsland("Surprising","green", new int[][]{{40, 20}, {60, 10}, {75, 20}, {60, 30}});

        assertTrue(input1.ok());

        input1.addIsland("Fixed","red", new int[][]{{45, 60}, {55, 55}, {60, 60}, {55, 65}});

        assertTrue(input1.ok());

        input1.makeVisible();

        

    }

    



    @Test

    public void agregarIslasPorTipoIncorrectamenteChicuazuqueSierra() throws Exception {
        Iceepeecee input1 = new Iceepeecee(100, 100);

        input1.addIsland("noexiste","blue", new int[][]{{40, 20}, {60, 10}, {75, 20}, {60, 30}});

        assertFalse(input1.ok());

    }

    

    @Test

    public void agregarVueloPorTipoCorrectamenteChicuazuqueSierra() throws Exception{
        Iceepeecee input1 = new Iceepeecee(100, 100);

        input1.addVuelo("Normal","fligth1", new int[]{0, 30, 20}, new int[]{78, 70, 5});

        assertTrue(input1.ok());

        input1.addVuelo("Lazy","fligth2", new int[]{55, 0, 20}, new int[]{70, 60, 10});

        assertTrue(input1.ok());

        input1.addVuelo("Flat","fligth2", new int[]{10, 0, 20}, new int[]{20, 30, 20});

        assertTrue(input1.ok());

        

    }

    

    @Test

    public void agregarVueloPorTipoIncorrectamenteChicuazuqueSierra() throws Exception{
        Iceepeecee input1 = new Iceepeecee(100, 100);

        input1.addVuelo("Noexiste","fligth1", new int[]{0, 30, 20}, new int[]{78, 70, 5});

        assertFalse(input1.ok());

    }

    

    @Test

    public void ConsultarDosVecesUnaIslaSurprisingChicuazuqueSierra() throws Exception{
       Iceepeecee input1 = new Iceepeecee(100, 100);

        input1.addIsland("Surprising","green", new int[][]{{40, 20}, {60, 10}, {75, 20}, {60, 30}});

        input1.islandLocation("green");

        input1.makeVisible();

    }

    

    @Test

    public void tomarDosFotosParaUnVueloLazyChicuazuqueSierra() throws Exception{
        Iceepeecee input1 = new Iceepeecee(100, 100);

        input1.addVuelo("Lazy","fligth2", new int[]{55, 0, 20}, new int[]{70, 60, 10});

        input1.photograph(10);

        input1.photograph(40);

        input1.makeVisible();

    }

Torres-Valencia

@Test

    public void testAddIslandwithType() {

           iceepeecee.addIsland("Surprising","62B2AB", new int[][]{{40, 20}, {60, 10}, {75, 20}, {60, 30}});

           assertEquals(true,iceepeecee.ok());

           iceepeecee.addIsland("Fixed","blue", new int[][]{{20, 30}, {50, 50}, {10, 50}});

           assertEquals(true,iceepeecee.ok());

           iceepeecee.addIsland("NormalIsland","991700", new int[][]{{100, 30}, {200, 50}, {80, 50}});

           assertEquals(true,iceepeecee.ok());

        

        

    }

    @Test

    public void testAddIslandwithTypeUnkown() {

           iceepeecee.addIsland("Surprising","62B2AB", new int[][]{{40, 20}, {60, 10}, {75, 20}, {60, 30}});

           assertEquals(true,iceepeecee.ok());

           iceepeecee.addIsland("Fixed","blue", new int[][]{{20, 30}, {50, 50}, {10, 50}});

           assertEquals(true,iceepeecee.ok());

           iceepeecee.addIsland("Surprising","991700", new int[][]{{100, 30}, {200, 50}, {80, 50}});

           assertEquals(true,iceepeecee.ok());

           iceepeecee.addIsland("Isla","magenta", new int[][]{{45, 60}, {55, 55}, {60, 60},{55, 65}});

           assertEquals(false,iceepeecee.ok());

    }

    @Test

    public void testAddFlightwithType() {

           iceepeecee.addFlight("Lazy","7DF032", new int[]{0,30,20},new int[]{78,70,5});

           assertTrue(iceepeecee.ok());

           iceepeecee.addFlight("Flat","991700", new int[]{5,5,10},new int[]{15,5,10});

           assertTrue(iceepeecee.ok());

           iceepeecee.addFlight("NormalFlight","magenta", new int[]{55,0,20},new int[]{70,60,10}); 

           assertTrue(iceepeecee.ok());

        

        

    }

    @Test

    public void testAddFlightwithTypeUnkown() {

        

           iceepeecee.addFlight("Lazy","7DF032", new int[]{0,30,20},new int[]{78,70,5});

           assertTrue(iceepeecee.ok());

           iceepeecee.addFlight("Flat","991700", new int[]{5,5,10},new int[]{15,5,10});

           assertTrue(iceepeecee.ok());

           iceepeecee.addFlight("NormalFlight","magenta", new int[]{55,0,20},new int[]{70,60,10}); 

           assertTrue(iceepeecee.ok());

           iceepeecee.addFlight("Avion","blue", new int[]{4,70,20},new int[]{15,30,5});

           assertEquals(false,iceepeecee.ok());

        

    }

    @Test

    public void testAddFlatwithDiferentAltitude() {

           iceepeecee.addFlight("Flat","991700", new int[]{5,5,6},new int[]{15,5,10}); 

           assertEquals(false,iceepeecee.ok());

    }

@Test

    public void testAddIslandwithType() {

           iceepeecee.addIsland("Surprising","62B2AB", new int[][]{{40, 20}, {60, 10}, {75, 20}, {60, 30}});

           assertTrue(iceepeecee.ok());

           iceepeecee.addIsland("Fixed","blue", new int[][]{{20, 30}, {50, 50}, {10, 50}});

           assertTrue(iceepeecee.ok());

           iceepeecee.addIsland("Lazy","991700", new int[][]{{100, 30}, {200, 50}, {80, 50}});

           assertTrue(iceepeecee.ok());

        

        

    }

    @Test

    public void testAddIslandwithTypeUnkown() {

           iceepeecee.addIsland("Surprising","62B2AB", new int[][]{{40, 20}, {60, 10}, {75, 20}, {60, 30}});

           assertTrue(iceepeecee.ok());

           iceepeecee.addIsland("Fixed","blue", new int[][]{{20, 30}, {50, 50}, {10, 50}});

           assertTrue(iceepeecee.ok());

           iceepeecee.addIsland("Lazy","991700", new int[][]{{100, 30}, {200, 50}, {80, 50}});

           assertTrue(iceepeecee.ok());

           iceepeecee.addIsland("Isla","magenta", new int[][]{{45, 60}, {55, 55}, {60, 60},{55, 65}});

        

    }

    @Test

    public void testAddFlightwithType() {

           iceepeecee.addFlight("Lazy","7DF032", new int[]{0,30,20},new int[]{78,70,5});

           assertTrue(iceepeecee.ok());

           iceepeecee.addFlight("Flat","991700", new int[]{5,5,10},new int[]{15,5,10});

           assertTrue(iceepeecee.ok());

           iceepeecee.addFlight("NormalFlight","magenta", new int[]{55,0,20},new int[]{70,60,10}); 

           assertTrue(iceepeecee.ok());

        

        

    }

    @Test

    public void testAddFlightwithTypeUnkown() {

        

           iceepeecee.addFlight("Lazy","7DF032", new int[]{0,30,20},new int[]{78,70,5});

           assertTrue(iceepeecee.ok());

           iceepeecee.addFlight("Flat","991700", new int[]{5,5,10},new int[]{15,5,10});

           assertTrue(iceepeecee.ok());

           iceepeecee.addFlight("NormalFlight","magenta", new int[]{55,0,20},new int[]{70,60,10}); 

           assertTrue(iceepeecee.ok());

           iceepeecee.addFlight("Avion","blue", new int[]{4,70,20},new int[]{15,30,5});

           assertEquals(false,iceepeecee.ok());

        

    }

    @Test

    public void testAddFlatwithDiferentAltitude() {

           iceepeecee.addFlight("Flat","991700", new int[]{5,5,6},new int[]{15,5,10}); 

           assertEquals(false,iceepeecee.ok());

//Silva-Suarez: 

@Test

    public void shouldNewAddIsland() {

        iceepeecee = new Iceepeecee(100, 100);

        String color = "Blue";

        int[][] polygons = {{1, 1}, {2, 2}, {3, 3}};



        iceepeecee.addIsland("NormalIsland", color, polygons);

        assertTrue(iceepeecee.ok());



        iceepeecee.addIsland("FixedIsland", color, polygons);

        assertFalse(iceepeecee.ok());



        iceepeecee.addIsland("SurprisingIsland", color, polygons);

        assertFalse(iceepeecee.ok());

    }

    

     @Test

    public void shouldNotAddIslandWithExistingColor() {

        iceepeecee = new Iceepeecee(100, 100);

        String color = "Green";

        int[][] polygons = {{1, 1}, {2, 2}, {3, 3}};

    

        iceepeecee.addIsland("NormalIsland", color, polygons);

        assertTrue(iceepeecee.ok());



        iceepeecee.addIsland("FixedIsland", color, polygons);

        assertFalse(iceepeecee.ok()); 

    }

    

    @Test

    public void shouldAddNewFlight() {

        iceepeecee = new Iceepeecee(100, 100);

        String color1 = "Red";

        String color2 = "Blue";

        String color3 = "Green";

        int[] from = {10, 10, 10};

        int[] to = {20, 20, 20};



        iceepeecee.addFlight("FlatFlight", color1, from, to);

        assertTrue(iceepeecee.ok());



        iceepeecee.addFlight("LazyFlight", color2, from, to);

        assertTrue(iceepeecee.ok());



        iceepeecee.addFlight("NormalFlight", color3, from, to);

        assertTrue(iceepeecee.ok());

    }



    @Test

    public void shouldNotAddFlightWithExistingColor() {

        iceepeecee = new Iceepeecee(100, 100);

        String color = "red";

        int[] from1 = {10, 10, 10};

        int[] to1 = {20, 20, 20};

        int[] from2 = {60, 10, 10};

        int[] to2 = {50, 20, 20};



        iceepeecee.addFlight("FlatFlight", color, from1, to1);

        iceepeecee.addFlight("LazyFlight", color, from2, to2);

        assertFalse(iceepeecee.ok()); 

    }

/*Milton Gutierrez - Jhon Sosa*/

 

    @BeforeEach

    public void setUp(){

        iceepeecee = new Iceepeecee(300,300);

    }



    @Test

    public void sholdAddEveryTypeOfNewIsland(){

        iceepeecee.addIsland("Normal", "#FFFFFF", new int[][]{{20,30},{50,50},{10,50}});

        assertTrue(iceepeecee.ok());

        assertEquals(iceepeecee.islands().length, 1);

        iceepeecee.addIsland("Fixed", "#FFFF00", new int[][]{{40,20},{60,10},{75,20},{60,30}});

        assertTrue(iceepeecee.ok());

        assertEquals(iceepeecee.islands().length, 2);

        iceepeecee.addIsland("Surprising", "#FF0000", new int[][]{{45,60},{55,55},{60,60},{55,65}});

        assertTrue(iceepeecee.ok());

        assertEquals(iceepeecee.islands().length, 3);

    }

    

    @Test

    public void sholdAddEveryTypeOfFligth(){

        iceepeecee.addFlight("Normal", "#FFFFFF", new int[]{55,0,20}, new int[]{70,60,10});

        assertTrue(iceepeecee.ok());

        assertEquals(iceepeecee.flights().length, 1);

        iceepeecee.addFlight("Flat", "#FFFF00", new int[]{100,130,20}, new int[]{178,170,5});

        assertTrue(iceepeecee.ok());

        assertEquals(iceepeecee.flights().length, 2);

        iceepeecee.addFlight("Lazy", "#08FF00", new int[]{5,5,10}, new int[]{15,5,10});

        assertTrue(iceepeecee.ok());

        assertEquals(iceepeecee.flights().length, 3);

    }

    

    @Test

    public void flatFlightShouldBeFlat(){

        iceepeecee.addFlight("Flat", "#FFFFFF", new int[]{55,0,20}, new int[]{70,60,9});

        assertEquals(iceepeecee.flightLocation("#FFFFFF")[0][2], iceepeecee.flightLocation("#FFFFFF")[1][2]);

    }

    

    @Test

    public void lazyFlightShoulTakeAphoto(){

        iceepeecee.addFlight("Lazy", "#08FF00", new int[]{5,5,10}, new int[]{15,5,10});

        iceepeecee.photograph("#08FF00", 30);

        assertTrue(iceepeecee.ok());

        iceepeecee.makeInvisible();

    }

    

    @Test

    public void lazylazyFlightShouldnotTakeMoreThanOnePhoto(){

        iceepeecee.addFlight("Lazy", "#08FF00", new int[]{5,5,10}, new int[]{15,5,10});

        iceepeecee.photograph("#08FF00", 30);

        assertTrue(iceepeecee.ok());

        iceepeecee.photograph("#08FF00", 40);

        assertTrue(iceepeecee.ok()); // se considera que la accion se pudo realizar

        assertNotEquals(iceepeecee.getFlight("#08FF00").flightCamera(), 40); // Verificamos que la camara no cambie su angulo

        assertEquals(iceepeecee.getFlight("#08FF00").flightCamera(), 30);  // Verificamos que la camara no cambie su angulo

    }

 

    @Test

    public void fixedIslandShouldBeFixed(){

        iceepeecee.addIsland("Fixed", "#FFFF00", new int[][]{{40,20},{60,10},{75,20},{60,30}});

        iceepeecee.delIsland("#FFFF00");

        assertFalse(iceepeecee.ok());

        assertTrue(iceepeecee.getIsland("#FFFF00") != null);

    }

 

    @Test

    public void surprisingIslandShouldBeSurprising(){

        iceepeecee.addIsland("Surprising", "#FF0000", new int[][]{{45,60},{55,55},{60,60},{55,65},{55,70},{60,75},{65,60}});

        assertEquals(iceepeecee.islandLocation("#FF0000").length, 6); //Se elimina un vertice. 

        assertTrue(iceepeecee.ok()); // se considera que la accion se pudo realizar

        assertEquals(iceepeecee.islandLocation("#FF0000").length, 5); //Se elimina un vertice. 

        assertTrue(iceepeecee.ok()); // se considera que la accion se pudo realizar

        assertEquals(iceepeecee.islandLocation("#FF0000").length, 4); //Se elimina un vertice. 

        assertTrue(iceepeecee.ok()); // se considera que la accion se pudo realizar

        assertEquals(iceepeecee.islandLocation("#FF0000").length, 3); //Se elimina un vertice. 

        assertTrue(iceepeecee.ok()); // se considera que la accion se pudo realizar

        assertEquals(iceepeecee.islandLocation("#FF0000").length, 3); //Llega al limite de vertices eliminados

        assertTrue(iceepeecee.ok()); // se considera que la accion se pudo realizar

    }

}





//Mendivelso-Rpdriguez



public class IceepeeceeC4test{

     @Test

    public void testFixedShould() {

        // Prueba cuando no se puede eliminar una isla fija

        Island island = new Fixed("red", new int[][]{{200, 100}, {150, 100}, {150, 200}});

        island.delIsland("red"); // Esto debería mostrar un mensaje en la consola, no eliminar la isla

    }

    

     @Test

    public void testSurprisingShould() {

        // Prueba cuando una isla sorprendente pierde un vértice

        Island surprisingIsland = new Surprising("green", new int[][]{{0, 0}, {0, 10}, {10, 10}, {10, 0}});

        int[][] originalPolygon = surprisingIsland.getPolygon();

        surprisingIsland.islandLocation("green");

        

        int[][] newPolygon = surprisingIsland.getPolygon();

        assertEquals(originalPolygon.length - 1, newPolygon.length);

    }

    

    

    @Test

    public void testFlightShould() {

        // Prueba la creación de una zona fotografiada por Flat

        Flat flatFlight = new Flat("red", new int[]{0, 0, 0}, new int[]{10, 10, 10},90);

        boolean zone = flatFlight.photograph("red",45);

        assertTrue(zone);



        // Prueba la creación de una zona fotografiada por Lazy

        Lazy lazyFlight = new Lazy("green", new int[]{0, 0, 0}, new int[]{10, 10, 10},90);

        boolean lazyZone = lazyFlight.photograph("green",30);

        assertTrue(lazyZone);

    }



  

}



//Alvarez-Morales



@Test

public void shouldNotAddFlightWithInsufficientCoordinates() {

    int[] from = {0, 30};

    int[] to = {78,70};

    iceepeecee.addFlight("IncompleteFlight", "#00FFFF", from, to);

   assertFalse(iceepeecee.ok());

}



@Test

public void shouldNotAddDuplicateIslandWithSameColor(){

    int[][] polygon1 = {{10, 10}, {20, 10}, {20, 20}, {10, 20}};

    int[][] polygon2 = {{30, 30}, {40, 30}, {40, 40}, {30, 40}};

    iceepeecee.addIsland("SurprisingIsland1", "#0000FF", polygon1);

    iceepeecee.addIsland("SurprisingIsland2", "#0000FF", polygon2);

    assertFalse(iceepeecee.ok());

}



@Test

public void flatFlightShouldMaintainSameAltitude() {

    int[] from = {0, 30, 20};

   int[] to = {78, 70, 5};

   iceepeecee.addFlight("FlatFlight1", "#FFFF00", from, to);

   int initialAltitude = iceepeecee.flightLocation("#FFFF00") [0][2];

   iceepeecee.addFlight("FlatFlight2", "#FF00FF", from, to);

   int newAltitude = iceepeecee.flightLocation("#FF00FF") [0][2];

   assertEquals(initialAltitude, newAltitude);

}



@Test

public void normalFlightCanTakeMultiplePhotos(){

    int[] from = {0, 0, 20};

    int[] to = {20, 20, 40};

    iceepeecee.addFlight("NormalFlight2", "#00FF00", from, to);

    iceepeecee.photograph("NormalFlight2", 45);

    assertTrue(iceepeecee.ok());

    iceepeecee.photograph("NormalFlight2", 70);

    assertTrue(iceepeecee.ok());

   iceepeecee.photograph("NormalFlight2", 15);

   assertTrue(iceepeecee.ok());

}

//CASTILLO - SUAREZ

package Iceepeecee;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;



/**

 * The test class IceepeeceeC4test.

 */

public class IceepeeceeC4test

{



    @Test

    public void testAddIslandShouldCreateIsland() {

        Iceepeece iceepeecee = new Iceepeece(500,500); 



        // Prueba con un tipo válido y datos válidos

        String type = "FixedIsland";

        String color = "blue";

        int[][] polygon = {{0, 0}, {0, 1}, {1, 1}, {1, 0}};

        iceepeecee.addIsland(type, color, polygon);

   



        // Verifica que se haya agregado una isla a la lista

        assertEquals(1, iceepeecee.getIslandCount());

    }

    

    @Test

    public void testAddIslandShouldDelIsland() {

        Iceepeece iceepeecee = new Iceepeece(500,500);



        // Prueba con un tipo válido y datos válidos

        String type = "FixedIsland";

        String color = "blue";

        int[][] polygon = {{0, 0}, {0, 1}, {1, 1}, {1, 0}};

        iceepeecee.addIsland(type, color, polygon);

        iceepeecee.delIsland("blue");



        // Verifica que se haya eliminado una isla de la lista

        assertEquals(0, iceepeecee.getIslandCount());

    }

    

      @Test

    public void ShouldAddDifferentIslands() {

        Iceepeece iceepeecee = new Iceepeece(500,500); 



        // Prueba con un tipo válido y datos válidos

        String type = "FixedIsland";

        String color = "blue";

        int[][] polygon = {{10, 10}, {10, 20}, {20, 20}, {10, 20}};

        iceepeecee.addIsland(type, color, polygon);

        int [][] polygon2 = {{40,20},{60,10},{75,20},{60,30}};

        iceepeecee.addIsland("blue",polygon2);



        // Verifica que se haya agregado unicamente una isla sin importar que sean de distintos tipos

        assertEquals(1, iceepeecee.getIslandCount());

    }



    @Test

    public void testAddIslandShouldNotCreateIsland() {

        Iceepeece iceepeecee = new Iceepeece(500,500); 



        // Prueba con un tipo inválido

        String type = "InvalidType";

        String color = "blue";

        int[][] polygon = {{0, 0}, {0, 1}, {1, 1}, {1, 0}};

        iceepeecee.addIsland(type, color, polygon);



        // Verifica que no se haya agregado ninguna isla

        assertEquals(0, iceepeecee.getIslandCount());



        // Prueba con datos inválidos

        type = "SurprisingIsland";

        color = "blue";

        int[][] invalidPolygon = {{0, 0}, {0, 1}, {1, 1}}; // Polígono inválido

        iceepeecee.addIsland(type, color, invalidPolygon);



        // Verifica que no se haya agregado ninguna isla

        assertEquals(1, iceepeecee.getIslandCount());

    }



    @Test

    public void testAddFlightShouldCreateFlight() {

        Iceepeece iceepeecee = new Iceepeece(500,500);



        // Prueba con un tipo válido y datos válidos

        String type = "LazyFlight";

        String color = "black";

        int[] from = {0, 0};

        int[] to = {1, 1};

        int cameraAngle = 45;

        iceepeecee.addFlight(type, color, from, to, cameraAngle);



        // Verifica que se haya agregado un vuelo a la lista

        assertEquals(1, iceepeecee.getFlightCount());

    }



    @Test

    public void testAddFlightShouldNotCreateFlight() {

        Iceepeece iceepeecee = new Iceepeece(500, 500); 

    

        // Prueba con un tipo inválido

        String type = "INVALIDO";

        String color = "red";

        int[] from = {0, 0};

        int[] to = {1, 1};

        int cameraAngle = 45;

        iceepeecee.addFlight(type, color, from, to, cameraAngle);

        System.out.println(iceepeecee.flights());

        // Verifica que no se haya agregado ningún vuelo

        assertEquals(0, iceepeecee.flights().length);

    }

 



    @Test

    public void testGetPolygonWhenVerticesAreLost() {

        Iceepeece iceepeecee = new Iceepeece(500, 500); 

    

        // Crea una isla del tipo SurprisingIsland

        String type = "SurprisingIsland";

        String color = "red";

        

        int[][] initialPolygon = {{0, 0}, {1, 0}, {1, 1}, {0, 1}};

        iceepeecee.addIsland(type, color, initialPolygon);



        // Carga la isla y comprueba si perdió uno de sus vertices al llamar el metodo getPolygon

        Island island = iceepeecee.loadIsland("red");

        int[][] updatedPolygon = island.getPolygon();

    

        assertEquals(initialPolygon.length - 1, updatedPolygon.length);

    }

    

     @Test

    public void testGetPolygonWhenVerticesAreNotLost() {

        Iceepeece iceepeecee = new Iceepeece(500, 500); 

    

        // Crea una isla del tipo SurprisingIsland

        String type = "SurprisingIsland";

        String color = "red";

        

        int[][] initialPolygon = { {0, 0}, {1, 0}, {1, 1} };

        iceepeecee.addIsland(type, color, initialPolygon);

    

        // Llama getPolygon para comprobar que la isla no perdió el vertice

        Island island = iceepeecee.loadIsland("red");

        int[][] updatedPolygon = island.getPolygon();

        

        assertArrayEquals(initialPolygon, updatedPolygon);

    }



    @Test

    public void testPhotographFirstCapture() {

        Iceepeece iceepeecee = new Iceepeece(500, 500);

        int[] from = {0, 0};

        int[] to = {1, 1};

        

        //crea la primera fotografía

        iceepeecee.addFlight("LazyFlight", "green", from, to, 30);

        

        Flight flight = iceepeecee.loadFlight("green");

        

        //intenta crear la segunda fotografía

        flight.photograph("green",45);

        

        assertTrue(iceepeecee.ok());

        

    }

}



// Montero Villamizar

 @Test

    public void testFixedIslandCannotBeDeleted() {

        // Prueba que no se puede eliminar una isla fija

        Island island = new Fixed("red", new int[][]{{0, 0}, {0, 10}, {10, 10}});

        assertFalse(island.delIsland("red"));

    }



    @Test

    public void testSurprisingIslandLosesVertex() {

        // Prueba que una isla sorprendente pierde un vértice

        Island surprisingIsland = new Surprising("green", new int[][]{{0, 0}, {0, 10}, {10, 10}, {10, 0}});

        int[][] originalPolygon = surprisingIsland.getPolygonCoordinates();

        surprisingIsland.getIslandCoordinates("green");



        int[][] newPolygon = surprisingIsland.getPolygonCoordinates();

        assertEquals(originalPolygon.length - 1, newPolygon.length);

    }



    @Test

    public void testFlatAndLazyFlightPhotograph() {

        // Prueba la creación de una zona fotografiada por Flat

        Flat flatFlight = new Flat("red", new int[]{0, 0, 0}, new int[]{10, 10, 10});

        boolean zone = flatFlight.photograph("red", 45);

        assertTrue(zone);



        // Prueba la creación de una zona fotografiada por Lazy

        Lazy lazyFlight = new Lazy("green", new int[]{0, 0, 0}, new int[]{10, 10, 10});

        boolean lazyZone = lazyFlight.photograph("green", 30);

        assertTrue(lazyZone);

    }
    
    
}
