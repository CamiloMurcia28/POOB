package test;
import iceepeecee.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class IceepeeceeCC1Test.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class IceepeeceeCC1Test
{
    @BeforeEach
    public void setUp() {
        iceepeecee = new Iceepeecee(1000,1000); // Crea una instancia de Iceepeecee para las pruebas
    }

    @Test

    public void accordingBHShouldCreateACorrectIceepeecee(){

        Iceepeecee map;

        map = new Iceepeecee(600,600);

        assertTrue(map.ok());

    }

    @Test

    public void accordingBHshouldAddFlight() {

        Iceepeecee map = new Iceepeecee(600,600);

        String[] vuelos = {"blue","red"};

        int[] from = {45,55};

        int[] to = {67,90};

        int[] from2 = {75,20};

        int[] to2 = {120,75};

        map.addFlight("red",from,to);

        map.addFlight("blue",from2,to2);

        assertArrayEquals(vuelos, map.flights());

    }

    

    @Test

    public void accordingBHshouldAddIsland() {

        Iceepeecee map = new Iceepeecee(600,600);

        int[][] polygon = {{100, 50}, {150, 80}, {200, 100}};

        map.addIsland("blue", polygon);

        String[] islands = map.islands();

        assertEquals(1, islands.length);

        assertEquals("blue", islands[0]);

    }

    

    @Test

    public void accordingBHshouldEraseFlight() {

        Iceepeecee map = new Iceepeecee(600,600);

        String[] vuelos = {"red","yellow"};

        int[] from = {45,55};

        int[] to = {67,90};

        int[] from2 = {75,20};

        int[] to2 = {120,75};

        int[] from3 = {200,210};

        int[] to3 = {145,196};

        map.addFlight("red",from,to);

        map.addFlight("blue",from2,to2);

        map.addFlight("yellow",from3,to3);

        map.delFlight("blue");

        assertArrayEquals(map.flights(),vuelos);

        map.delFlight("red");

        String[] vuelos2 = {"yellow"};

        assertArrayEquals(map.flights(),vuelos2);

    }

    

    @Test

    public void accordingBHshouldEraseIsland() {

        Iceepeecee map = new Iceepeecee(600,600);

        map = new Iceepeecee(600,600);

        int[][] polygon = {{100, 50}, {150, 80}, {200, 100}};

        map.addIsland("blue", polygon);

        map.delIsland("blue");

        String[] islands = map.islands();

        assertEquals(0, islands.length);



    }

    

    @Test

    public void accordingBHshouldNotDeleteNonExistentFlight() {

        Iceepeecee map = new Iceepeecee(600,600);

        int[] from = {100, 200};

        int[] to = {300, 400};

        map.addFlight("yellow",from,to);

        map.delFlight("red"); // Intenta eliminar un vuelo que no existe

        String[] vuelos = map.flights();

        assertEquals(1, vuelos.length); // El vuelo original no debe haberse eliminado

        assertEquals("yellow", vuelos[0]);

    }

    

    @Test

    public void accordingBHshouldNotDeleteNonExistentIsland() {

        Iceepeecee map = new Iceepeecee(600,600);

        int[][] polygon = {{100, 50}, {150, 80}, {200, 100}};

        map.addIsland("blue", polygon);

        map.delIsland("green"); // Intentar eliminar una isla que no existe

        String[] islands = map.islands();

        assertEquals(1, islands.length); // La isla original no debe haberse eliminado

        assertEquals("blue", islands[0]);

    }

    

    @Test

    public void accordingBHshouldNotAddInvalidFlight() {

        Iceepeecee map = new Iceepeecee(600,600);

        int[] from = {100, 200};

        int[] to = {700, 800};

        map.addFlight("red",from,to);

        String[] vuelos = map.flights();

        assertEquals(0, vuelos.length); // No se debe agregar el vuelo fuera del mapa

    }

    

    @Test

    public void accordingBHshouldGiveFlightLocation() {

        Iceepeecee map = new Iceepeecee(600,600);

        int[] from = {100,400,10};

        int[] to = {150,450,18};

        map.addFlight("red",from,to);

        int[][] informacionEsperada = {{100,400,10},{150,450,18}};

        assertArrayEquals(map.flightLocation("red"),informacionEsperada);

    }

    

    @Test

    public void accordingBHshouldGetIslandLocation() {

        Iceepeecee map = new Iceepeecee(600,600);

        int[][] polygon = {{100, 50}, {150, 80}, {200, 100}};

        map.addIsland("blue", polygon);

        int[][] expectedLocation = {{100, 50}, {150, 80}, {200, 100}};

        int[][] islandLocation = map.islandLocation("blue");

        assertArrayEquals(expectedLocation, islandLocation);

    }

    

    @Test

    public void accordingBHshouldReturnZeroAngleForNonExistentFlight() {

        Iceepeecee map = new Iceepeecee(600, 600);

        String color = "blue";

        int angle = map.flightCamera(color);

        assertEquals(0, angle);

    }

    

    @Test



    public void accordingBHShouldFlightCamera() {

        Iceepeecee map = new Iceepeecee(600,600);

        int[] from = {100, 100, 0}; 

        int[] to = {200, 200, 0};   

        map.addFlight("blue", from, to);

        int angle = map.flightCamera("blue");

        assertTrue(map.ok());

    }



    @Test



    public void accordingBHShouldNotFlightCamera() {

        Iceepeecee map = new Iceepeecee(600,600);

        int[] from = {100, 100, 0}; 

        int[] to = {200, 200, 0};   

        map.addFlight("blue", from, to);

        int angle = map.flightCamera("red");

        assertFalse(map.ok());

    }

    

    @Test

    public void accordingBHshouldPhotographForDeterminedFlight() {

        Iceepeecee map = new Iceepeecee(600,600);

        int[] from = {100, 100, 10};

        int[] to = {200, 200, 15};  

        map.addFlight("red", from, to);

        map.photograph("red", 30);

        assertTrue(map.ok());

    }

    

    @Test

    public void accordingBHshouldNotPhotographForDeterminedFlight() {

        Iceepeecee map = new Iceepeecee(600,600);

        int[] from = {100, 100, 0};

        int[] to = {200, 200, 0};  

        map.addFlight("red", from, to);

        map.photograph("blue", 91);

        assertFalse(map.ok());

    }

    

    @Test



    public void accordingBHshouldPhotographForAllFlights() {

        Iceepeecee map = new Iceepeecee(600,600);

        int[] from = {100, 100, 9};

        int[] to = {200, 200, 7};  

        map.addFlight("green", from, to);

        int[] from1 = {200, 200, 17};

        int[] to1 = {300, 300, 17};  

        map.addFlight("blue", from1, to1);

        map.photograph(30);

        assertTrue(map.ok());

    }



     @Test



    public void accordingBHShouldMakeVisible(){

        Iceepeecee map = new Iceepeecee(600,600);

        int[] from = {100, 100,10};

        int[] to = {200, 200,10};  

        map.addFlight("green", from, to);

        int[][] polygon = {{100, 50}, {150, 80}, {200, 100}};

        map.addIsland("red",polygon);

        map.makeVisible();

        assertEquals(true, map.ok());

        map.makeInvisible();

    }

    

    @Test



    public void accordingBHShouldMakeInvisible(){

        Iceepeecee map = new Iceepeecee(600,600);

        int[] from = {100, 100,10};

        int[] to = {200, 200,10};  

        map.addFlight("green", from, to);

        int[][] polygon = {{100, 50}, {150, 80}, {200, 100}};

        map.addIsland("red",polygon);

        map.makeVisible();

        map.makeInvisible();

        assertEquals(true, map.ok());


    }

//Lesmes
 @BeforeEach

    public void setUp() {

        iceepeecee = new Iceepeecee(500, 500);

    }

//AddFLight

    @Test

    public void accordingLPShouldAddFlight() {

        int[] from = {100, 100, 0};

        int[] to = {200, 200, 0};

        iceepeecee.addFlight("#EA3333", from, to);

        assertTrue(iceepeecee.ok());

    }

    @Test

    public void accordingLPShouldNotAddFlight() {

        int[] from = {100, 100, 0};

        int[] to = {200, 200, 0};

        iceepeecee.addFlight("#EA3333", from, to);

        int[] from1 = {100, 100, 0};

        int[] to1 = {200, 200, 0};

        iceepeecee.addFlight("#EA3333", from1, to1);

        assertFalse(iceepeecee.ok());

    }



//AddIsland

    @Test

    public void accordingLPShouldAddIsland() {

        int[][] polygon = {{50, 50, 0}, {100, 50, 0}, {100, 100, 0}, {50, 100, 0}}; 

        iceepeecee.addIsland("#EA3333", polygon);

        assertTrue(iceepeecee.ok()); 

    }

    @Test

    public void accordingLPShouldNotAddIsland() {

        int[][] polygon = {{50, 50, 0}, {100, 50, 0}, {100, 100, 0}, {50, 100, 0}}; 

        iceepeecee.addIsland("#5AEA33", polygon);

        int[][] polygon1 = {{400, 200, 0}, {300, 400, 0}, {100, 100, 0}, {50, 100, 0}}; 

        iceepeecee.addIsland("#5AEA33", polygon1);

        assertFalse(iceepeecee.ok());

    }

//Photograph(color, teta)

    @Test

    public void accordingLPShouldPhotograph() {

        int[] from = {100, 100, 0};

        int[] to = {200, 200, 0};  

        iceepeecee.addFlight("#EA4F33", from, to);

        iceepeecee.photograph("#EA4F33", 30);

        assertTrue(iceepeecee.ok());

    }

    @Test

    public void accordingLPShouldNotPhotograph() {

        int[] from = {100, 100, 0};

        int[] to = {200, 200, 0};  

        iceepeecee.addFlight("#EA4F33", from, to);

        iceepeecee.photograph("#EA4F33", 91);

        assertFalse(iceepeecee.ok());

    }

//Photograph(teta)

    @Test

    public void accordingLPShouldPhotograph3() {

        int[] from = {100, 100, 0};

        int[] to = {200, 200, 0};  

        iceepeecee.addFlight("#EA4F33", from, to);

        int[] from1 = {200, 200, 0};

        int[] to1 = {300, 300, 0};  

        iceepeecee.addFlight("#5AEA33", from1, to1);

        iceepeecee.photograph(30);

        assertTrue(iceepeecee.ok());

    }

    @Test

    public void accordingLPShouldNotPhotograph5() {

        int[] from = {100, 100, 0};

        int[] to = {200, 200, 0};  

        iceepeecee.addFlight("#EA4F33", from, to);

        int[] from1 = {200, 200, 0};

        int[] to1 = {300, 300, 0};  

        iceepeecee.addFlight("#5AEA33", from1, to1);

        iceepeecee.photograph(91);

        assertFalse(iceepeecee.ok());

    }

//DelFLight

    @Test

    public void accordingLPShouldDelFlight() {

        int[] from = {100, 100, 0};

        int[] to = {200, 200, 0};

        iceepeecee.addFlight("#EA4F33", from, to);

        iceepeecee.delFlight("#EA4F33");

        assertTrue(iceepeecee.ok());

    }

    @Test

    public void accordingLPShouldNotDelFlight() {

        int[] from = {100, 100, 0};

        int[] to = {200, 200, 0};

        iceepeecee.addFlight("#EA4F33", from, to);

        iceepeecee.delFlight("#5AEA23");

        assertFalse(iceepeecee.ok());

    }

//DelIsland

    @Test

    public void accordingLPShoulddelIsland() {

        int[][] polygon = {{50, 50}, {100, 50}, {100, 100}, {50, 100}}; // Example island polygon

        iceepeecee.addIsland("#5AEA33", polygon);

        iceepeecee.delIsland("#5AEA33");

        assertTrue(iceepeecee.ok());

    }

    @Test

    public void accordingLPShouldNotdelIsland() {

        int[][] polygon = {{50, 50}, {100, 50}, {100, 100}, {50, 100}}; // Example island polygon

        iceepeecee.addIsland("#5AEA33", polygon);

        iceepeecee.delIsland("#5AEA32");

        assertFalse(iceepeecee.ok());

    }

//IslandLocation

    @Test

    public void accordingLPShouldIslandLocation() {

        int[][] polygon = {{50, 50}, {100, 50}, {100, 100}, {50, 100}};

        iceepeecee.addIsland("#5AEA33", polygon);

        int[][] location = iceepeecee.islandLocation("#5AEA33");

        assertTrue(iceepeecee.ok());

    }

    @Test

    public void accordingLPShouldNotIslandLocation() {

        int[][] polygon = {{50, 50}, {100, 50}, {100, 100}, {50, 100}};

        iceepeecee.addIsland("#5AEA33", polygon);

        int[][] location = iceepeecee.islandLocation("#5AEA23");

        assertFalse(iceepeecee.ok());

    }

//FlightLocation

    @Test

    public void accordingLPShouldFlightLocation() {

        int[] from = {100, 100, 0};

        int[] to = {200, 200, 0};

        iceepeecee.addFlight("#EA5733", from, to);

        int[][] location = iceepeecee.flightLocation("#EA5733");

        assertNotNull(iceepeecee.ok());

    }

    @Test

    public void accordingLPShouldNotFlightLocation() {

        int[] from = {100, 100, 0};

        int[] to = {200, 200, 0};

        iceepeecee.addFlight("#EA5733", from, to);

        int[][] location = iceepeecee.flightLocation("#5AEA33");

        assertNotNull(iceepeecee.ok());

    }

//FlightCamera

    @Test

    public void accordingLPShouldFlightCamera() {

        int[] from = {100, 100, 0}; 

        int[] to = {200, 200, 0};   

        iceepeecee.addFlight("#EA5733", from, to);

        int angle = iceepeecee.flightCamera("#EA5733");

        assertTrue(iceepeecee.ok());

    }

    @Test

    public void accordingLPShouldNotFlightCamera() {

        int[] from = {100, 100, 0}; 

        int[] to = {200, 200, 0};   

        iceepeecee.addFlight("#EA5733", from, to);

        int angle = iceepeecee.flightCamera("#5AEA33");

        assertFalse(iceepeecee.ok());

    }

FORERO-MURCIA[EDITAR]
@Test

    public void accordingFMShouldCreateACorrectIceepeecee(){

        Iceepeecee manage;

        manage = new Iceepeecee((int)Math.pow(10,6),(int)Math.pow(10,6));

    }

    

    @Test

    public void accordingFMShouldCreateAddAndDeleteACorrectIsland(){

        Iceepeecee manage;

        manage = new Iceepeecee((int)Math.pow(10,6),(int)Math.pow(10,6));

        int[][] maxCoords = {{(int) Math.pow(10, 6), (int) Math.pow(10, 6)}, {0, 0}, {10, 10}};

        manage.addIsland("Red",maxCoords);

        assertTrue(manage.OK());

        String [] islas = manage.islands();

        assertTrue(manage.OK());

        assertEquals("Red",islas[0]);

        manage.delIsland("Red");

        assertTrue(manage.OK());

        islas = manage.islands();

        assertTrue(manage.OK());

        assertArrayEquals(islas,new String[]{});

        manage.addIsland("Red",maxCoords);

        assertTrue(manage.OK());

    }

    

    @Test

    public void accordingFMshouldNotAddIslandsBecauseIsItersectingWithOtherIsland(){

        Iceepeecee manage = new Iceepeecee((int)Math.pow(10,6),(int)Math.pow(10,6));

        manage.addIsland("Pale Turquoise",new int[][]{{20,0},{60,0},{60,60},{20,60}});

        assertEquals(true, manage.OK());

        //Try to add an island where another island is located.

        manage.addIsland("Black",new int[][]{{-20,20},{80,20},{80,40},{-20,40}});

        assertEquals(false, manage.OK());

    }

    

    

    @Test

    public void accordingFMShouldCreateAddAndDeleteACorrectFlight(){

        Iceepeecee manage;

        manage = new Iceepeecee((int)Math.pow(10,6),(int)Math.pow(10,6));

        int[] maxCoords = {(int) Math.pow(10, 6), (int) Math.pow(10, 6),100};

        manage.addFlight("Red",new int[]{0,0,20},maxCoords);

        assertTrue(manage.OK());

        String [] vuelos = manage.flights();

        assertTrue(manage.OK());

        assertEquals("Red",vuelos[0]);

        manage.delFlight("Red");

        assertTrue(manage.OK());

        vuelos = manage.flights();

        assertTrue(manage.OK());

        assertArrayEquals(vuelos,new String[]{});

        manage.addFlight("Red",new int[]{0,0,20},maxCoords);

        assertTrue(manage.OK());

    }

    

    @Test

    public void accordingFMshouldNotAddTwoFlightsAtTheSamePoint(){

        Iceepeecee manage = new Iceepeecee((int)Math.pow(10,6),(int)Math.pow(10,6));

        manage.addFlight("Pale Turquoise",new int[]{0,0,20},new int[]{20,20,20});

        assertEquals(true, manage.OK());

        //Try to add an island where another island is located.

        manage.addFlight("Red",new int[]{0,0,20},new int[]{20,20,20});

        assertEquals(false, manage.OK());

    }

    

    @Test

    public void accordingFMshouldMakeAPhotograph(){

        Iceepeecee manage = new Iceepeecee((int)Math.pow(10,6),(int)Math.pow(10,6));

        manage.addFlight("Pale Turquoise",new int[]{0,0,20},new int[]{20,20,20});

        assertEquals(true, manage.OK());

        int[][] maxCoords = {{(int) Math.pow(10, 6), (int) Math.pow(10, 6)}, {0, 0}, {10, 10}};

        manage.addIsland("Red",maxCoords);

        assertEquals(true, manage.OK());

        manage.photograph(45);

        assertEquals(true, manage.OK());

        manage.photograph("Pale Turquoise",20.0);

        assertEquals(true, manage.OK());

        assertEquals(20, manage.flightCamera("Pale Turquoise"));

    }

    

    @Test

    public void accordingFMshouldNotMakeAPhotograph(){

        Iceepeecee manage = new Iceepeecee((int)Math.pow(10,6),(int)Math.pow(10,6));

        int[][] maxCoords = {{(int) Math.pow(10, 6), (int) Math.pow(10, 6)}, {0, 0}, {10, 10}};

        manage.addIsland("Red",maxCoords);

        assertEquals(true, manage.OK());

        manage.photograph(45);

        assertEquals(false, manage.OK());

    }

    

    @Test

    public void accordingFMshouldConsultFlightAndIslandLocation(){

        Iceepeecee manage = new Iceepeecee((int)Math.pow(10,6),(int)Math.pow(10,6));

        int [][] location = {{0,0},{50,50},{100,50}};

        manage.addIsland("Red",location);

        assertTrue(manage.OK());

        int [][] newLocation = manage.islandLocation("Red");

        for (int i = 0 ; i < location.length ;i++){

            assertEquals(location[i][0],newLocation[i][0]);

            assertEquals(location[i][1],newLocation[i][1]);

        }

        assertTrue(manage.OK());

        int [] from = new int[]{0,0,20};

        int [] to = new int[]{20,20,20};

        manage.addFlight("Red",from,to);

        assertTrue(manage.OK());

        newLocation = manage.flightLocation("Red");

        assertEquals(from[0],newLocation[0][0]);

        assertEquals(from[1],newLocation[0][1]);

        assertEquals(from[2],newLocation[0][2]);

        assertEquals(to[0],newLocation[1][0]);

        assertEquals(to[1],newLocation[1][1]);

        assertEquals(to[2],newLocation[1][2]);

        assertTrue(manage.OK());

    }

    

    @Test

    public void accordingFMshouldNotConsultFlightAndIslandLocation(){

        Iceepeecee manage = new Iceepeecee((int)Math.pow(10,6),(int)Math.pow(10,6));

        manage.islandLocation("Red");

        assertFalse(manage.OK());

    }

    

    @Test

    public void accordingFMShouldMakeVisibleTheIceepeecee(){

        Iceepeecee manage = new Iceepeecee(new int[][][]{{{20,30},{50,50},{10,50}},{{40,20},{60,10},{75,20},{60,30}},

                                {{45,60},{55,55},{60,60},{55,65}}},new int[][][]{{{0,30,20},{78,70,5}},{{55,0,20},{70,60,10}}});

        assertEquals(true, manage.OK());

        manage.photograph(48.032);

        assertEquals(true, manage.OK());

        manage.makeVisible();

        assertEquals(true, manage.OK());

        manage.makeInvisible();

        assertEquals(true, manage.OK());

        manage.photograph(40);

        assertEquals(true, manage.OK());

        manage.makeVisible();

        assertEquals(true, manage.OK());

    }

    

    @Test

    public void accordingFMShouldNotMakeVisibleAErrasedIslandTheIceepeecee(){

        Iceepeecee manage = new Iceepeecee(100000,100000);

        manage.addIsland("Red",new int[][]{{40,20},{60,10},{75,20},{60,30}});

        assertEquals(true, manage.OK());

        manage.delIsland("Red");

        assertEquals(true, manage.OK());

        manage.makeVisible();

    }



CASALLAS-MURCIA[EDITAR]
@Test

    public void accordingCMShouldAddIslandSuccess() {

        String color="#FF0000";

        int[][] polygon = {{10, 10}, {20, 10}, {20, 20}, {10, 20}};

        iceepeecee.addIsland(color, polygon);

        assertTrue(iceepeecee.ok());

    }



@Test

    public void accordingCMShouldNotAddIslandLessThanThree(){

        int[][] polygon1 = {{10, 10},{20, 20}};

        iceepeecee.addIsland("#FF0000",polygon1);

        assertFalse(iceepeecee.ok());

    }



@Test

    public void accordingCMShouldAddFlightSuccess() {

        String color="#FF0000";

        int[] from = {10,10,10};

        int[] to = {70,78,5};

        iceepeecee.addFlight(color, from, to);

        assertTrue(iceepeecee.ok());

    }



@Test

    public void accordingCMShouldAddFlightSuccess() {

        String color="#FF0000";

        int[] from = {10,10,10};

        int[] to = {70,78,5};

        iceepeecee.addFlight(color, from, to);

        assertTrue(iceepeecee.ok());

    }



@Test

    public void accordingCMShouldDelIslandSuccess() {

        int[][] polygon = {{10, 10}, {20, 10}, {20, 20}, {10, 20}};

        iceepeecee.addIsland("#FF0000", polygon);

        iceepeecee.makeVisible();

        iceepeecee.delIsland("#FF0000");

        assertTrue(iceepeecee.ok());

    }



@Test

    public void accordingCMShouldNotDelNotExistentIsland() {

        int[][] polygon = {{10, 10}, {20, 10}, {20, 20}, {10, 20}};

        iceepeecee.addIsland("#FF0000", polygon);

        iceepeecee.makeVisible();

        iceepeecee.delIsland("#0000FF");

        assertFalse(iceepeecee.ok());

    }



@Test

    public void accordingCMShouldDelFlightSuccess() {

        int[] from = {10,10,10};

        int[] to = {70,78,20};

        iceepeecee.addFlight("#FF0000", from, to);

        iceepeecee.delFlight("#FF0000");

        assertTrue(iceepeecee.ok());

    }



@Test

    public void accordingCMShouldNotDelNotExistentFlight() {

        int[] from = {10,10,10};

        int[] to = {70,78,50};

        iceepeecee.addFlight("#FF0000", from, to);

        iceepeecee.delFlight("#0000FF");

        assertFalse(iceepeecee.ok());

    }



@Test

    public void accordingCMShouldLocateIslandSuccess(){

        int[][] polygon = {{10, 10}, {20, 10}, {20, 20}, {10, 20}};

        iceepeecee.addIsland("#FF0000", polygon);

        iceepeecee.makeVisible();

        iceepeecee.islandLocation("#FF0000");

        assertTrue(iceepeecee.ok());

    }



@Test

    public void accordingCMShouldNotLocateIslandNotExistent(){

        int[][] polygon = {{10, 10}, {20, 10}, {20, 20}, {10, 20}};

        iceepeecee.addIsland("#FF0000", polygon);

        iceepeecee.makeVisible();

        iceepeecee.islandLocation("#0000FF");

        assertFalse(iceepeecee.ok());

    }



@Test

    public void accordingCMShouldLocateFlightSuccess(){

        int[] from = {10,10,10};

        int[] to = {70,78,50};

        iceepeecee.addFlight("#FF0000", from, to);

        iceepeecee.flightLocation("#FF0000");

        assertTrue(iceepeecee.ok());

    }



@Test

    public void accordingCMShouldNotLocateFlightNotExistent(){

        int[] from = {10,10,10};

        int[] to = {70,78,50};

        iceepeecee.addFlight("#FF0000", from, to);

        iceepeecee.flightLocation("#0000FF");

        assertFalse(iceepeecee.ok());

    }



@Test

    public void accordingCMShouldTakeAPhotoWithDifferentTetha(){

        int[]from={0,30,20}; 

        int[]to={78,70,5};

        String color="#FF0000";

        iceepeecee.addFlight(color,from,to);

        iceepeecee.photograph(color,48);

        iceepeecee.photograph(color,60);

        assertTrue(iceepeecee.ok());

    }



@Test

    public void accordingCMShouldNotTakeAPhotoWithNegativeAngles(){

        int[]from={0,30,20}; 

        int[]to={78,70,5};

        String color="#FF0000";

        iceepeecee.addFlight(color,from,to);

        iceepeecee.photograph(color,-1);

        assertFalse(iceepeecee.ok());

    }



@Test

    public void accordingCMShouldTakePhotosSuccess(){

        int[]from={0,30,20}; 

        int[]to={78,70,5};

        int[]from1={55,0,20};

        int[]to1={70,60,10};

        String color="#FF0000";

        String color1="#FFFFFF";

        iceepeecee.addFlight(color,from,to);

        iceepeecee.addFlight(color1,from1,to1);

        iceepeecee.photograph(48);

        assertTrue(iceepeecee.ok());

    }



@Test

    public void accordingCMShouldNotNotTakePhotosWithAnglesMoreThan90(){

        int[]from={0,30,20}; 

        int[]to={78,70,5};

        int[]from1={55,0,20};

        int[]to1={70,60,10};

        String color="#FF0000";

        String color1="#FFFFFF";

        iceepeecee.addFlight(color,from,to);

        iceepeecee.addFlight(color1,from1,to1);

        iceepeecee.photograph(100);

        assertFalse(iceepeecee.ok());

    }



@Test

    public void accordingCMShouldFlightCameraSuccess(){

        int[]from={0,30,20}; 

        int[]to={78,70,5};

        String color="#FF0000";

        iceepeecee.addFlight(color,from,to);

        iceepeecee.photograph(48);

        iceepeecee.flightCamera(color);

        assertTrue(iceepeecee.ok());

    }



@Test

    public void accordingCMShouldNotFlightCameraPhotoNotExistent(){

        int[]from={0,30,20}; 

        int[]to={78,70,5};

        String color="#FF0000";

        iceepeecee.photograph(48);

        iceepeecee.flightCamera(color);

        assertFalse(iceepeecee.ok());

    }

// DÍAZ - MONROY[EDITAR]
@BeforeEach

    public void setUp() {

        icpc = new Iceepeecee(2000, 2000);

        color1 = "AA";

        color2 = "BB";

        color3 = "CC";

        color4 = "DD";

        color5 = "EE";

        color6 = "FF";

        color7 = "GG";

        color8 = "HH";

        polygon1 = new int[][]{{10,-60,1500},{-10,-50,70}};

        polygon2 = new int[][]{{1000,980,950},{20,60,40}};

        polygon3 = new int[][]{{400,450,500,450},{400,390,400,450}};

        polygon4 = new int[][]{{1100,1200,1150},{30,50,60}};

        polygon5 = new int[][]{{250,400,500},{75,100,60}};

        from1 = new int[]{300,500,100};

        to1 = new int[]{720,200,60};

        from2 = new int[]{-300,500,30};

        to2 = new int[]{720,2500,60};

        from3 = new int[]{20,50,20};

        to3 = new int[]{300,400,60};

        from4 = new int[]{200,150,250};

        to4 = new int[]{700,50,100};

        from5 = new int[]{800,100,200};

        to5 = new int[]{950,500,90};

        tetaI1 = 120;

        tetaI2 = 60;

    }

   @Test

    public void accordingDMShouldCreate() {

        icpc = new Iceepeecee(2000, 2000);

    }

    @Test

    public void accordingDMShouldAddIslands() {

        icpc.addIsland(color1, polygon2);

        icpc.addIsland(color2, polygon3);

        icpc.makeVisible();

        assertEquals(true,icpc.ok());

    }

    @Test

    public void accordingDMShouldNotAddIslands1() {

        //No deberia agregar una isla ya creada

        icpc.addIsland(color2, polygon2);

        icpc.addIsland(color2, polygon3);

        assertEquals(false,icpc.ok());

    }

@Test

    public void accordingDMShouldDeleteIslands() {

        icpc.addIsland(color1, polygon2);

        icpc.delIsland(color1);

        assertEquals(true,icpc.ok());

    }

    @Test

    public void accordingDMShouldNotDeleteIslands() {

        //No puede eliminar una isla que no existe

        icpc.delIsland(color1);

        assertEquals(false,icpc.ok());        

    }

@Test

    public void accordingDMShouldAddFlight() {

        icpc.addFlight(color1, from1, to1);

        icpc.addFlight(color2, from3, to3);

        icpc.makeVisible();

        assertEquals(true,icpc.ok());

    }

    @Test

    public void accordingDMShouldNotAddFlight1() {

        //No deberia agregar un vuelo ya creado

        icpc.addFlight(color1, from1, to1);

        icpc.addFlight(color1, from3, to3);

        assertEquals(false,icpc.ok());

    }

@Test

    public void accordingDMShouldDeleteFlight() {

        icpc.addFlight(color1, from1, to1);

        icpc.delFlight(color1);

        assertEquals(true,icpc.ok()); 

    }

    @Test

    public void accordingDMShouldNotDeleteFlight() {

        //No puede eliminar un vuelo que no existe

        icpc.delFlight(color1);

        assertEquals(false,icpc.ok());

    }

@Test

    public void accordingDMShouldPhotographInt() {

        icpc.addIsland(color4, polygon3);

        icpc.addFlight(color5, from1, to1);

        icpc.photograph(color5, tetaI1);

        icpc.makeVisible();

        assertEquals(true,icpc.ok());

    }

@Test

    public void accordingDMShouldNotPhotographInt2() {

        //No toma foto con un ángulo negativo

        icpc.addIsland(color4, polygon5);

        icpc.addFlight(color5, from4, to4);

        icpc.photograph(color5, -tetaI2);

        assertEquals(false,icpc.ok());

    }

@Test

    public void accordingDMShouldPhotographAllFlightsInt() {

        icpc.addIsland(color1, polygon2);

        icpc.addIsland(color2, polygon3);

        icpc.addIsland(color3, polygon4);

        icpc.addIsland(color4, polygon5);

        icpc.addFlight(color5, from1, to1);

        icpc.addFlight(color6, from3, to3);

        icpc.addFlight(color7, from4, to4);

        icpc.addFlight(color8, from5, to5);

        icpc.photograph(tetaI1);

        icpc.makeVisible();

        assertEquals(true,icpc.ok());

    }

    @Test

    public void accordingDMShouldNotPhotographAllFlightsInt() {

        icpc.addIsland(color1, polygon2);

        icpc.addIsland(color2, polygon3);

        icpc.addIsland(color3, polygon4);

        icpc.addIsland(color4, polygon5);

        icpc.addFlight(color5, from1, to1);

        icpc.addFlight(color6, from3, to3);

        icpc.addFlight(color7, from4, to4);

        icpc.addFlight(color8, from5, to5);

        icpc.photograph(-tetaI1);

        icpc.makeVisible();

        assertEquals(false,icpc.ok());

    }

@Test

    public void accordingDMShouldConsultIslandLocation(){

        icpc.addIsland(color1, polygon2);

        icpc.addIsland(color2, polygon3);

        assertTrue(icpc.islandLocation(color1).equals(polygon2));

        assertTrue(icpc.islandLocation(color2).equals(polygon3));        

    }

    @Test

    public void accordingDMShouldNotConsultIslandLocation(){

        //No puede consultar la localización de una isla que no existe

        icpc.islandLocation(color1);

        assertEquals(false,icpc.ok());

    }

    @Test

    public void accordingDMShouldConsultFlightLocation(){

        icpc.addFlight(color1, from1, to1);

        icpc.addFlight(color2, from3, to3);

        assertTrue(icpc.flightLocation(color1)[0].equals(from1));

        assertTrue(icpc.flightLocation(color2)[0].equals(from3));     

    }

    @Test

    public void accordingDMShouldNotConsultFlightLocation(){

        //No puede consultar la localización de un vuelo que no existe

        icpc.flightLocation(color1);

        assertEquals(false,icpc.ok());

    }

    @Test

    public void accordingDMShouldConsultFlightCamera(){

        icpc.addFlight(color1, from1, to1);

        icpc.addFlight(color2, from3, to3);

        icpc.photograph(color1, 120);

        icpc.photograph(color2, 50);

        assertTrue(icpc.flightCamera(color1) == 120);

        assertTrue(icpc.flightCamera(color2) == 50);

    }

    @Test

    public void accordingDMShouldNotConsultFlightCamera(){

        //No puede consultar la camara de un vuelo que no existe

        icpc.flightCamera(color1);

        assertEquals(false,icpc.ok());

    }

@Test

    public void accordingDMShouldMakeVisible() {

        icpc.addIsland(color1, polygon1);

        icpc.addIsland(color2, polygon2);

        icpc.addIsland(color3, polygon3);

        icpc.addIsland(color4, polygon4);

        icpc.addIsland(color5, polygon5);

        icpc.addFlight(color4, from1, to1);

        icpc.addFlight(color5, from2, to2);

        icpc.addFlight(color6, from3, to3);

        icpc.addFlight(color7, from4, to4);

        icpc.addFlight(color8, from5, to5);

        icpc.photograph(tetaI1);

        icpc.makeVisible();

        assertEquals(true,icpc.ok());

    }

@Test

    public void accordingDMShouldNotMakeVisible3() {

        //El vuelo se sale del canvas

        icpc.addIsland(color1, polygon2);

        icpc.addIsland(color2, polygon3);

        icpc.addFlight(color3, from1, to1);

        icpc.addFlight(color4, from2, to2);

        assertEquals(false,icpc.ok());

    }

//Silva-Suarez: 

@Test

    

    

    public void shouldAddIsland() {

        iceepeecee = new Iceepeecee(100, 100);

        int[][] vertices = {{50, 50}, {60, 60}, {90, 90}};

        iceepeecee.addIsland("blue", vertices);

        assertTrue(iceepeecee.ok());

    }

    

    @Test

    

    public void shouldNotAddIslandOutsideBounds() {

        iceepeecee = new Iceepeecee(100, 100);

        int[][] vertices = {{120, 120}, {150, 150}, {180, 180}};

        iceepeecee.addIsland("Blue", vertices);

        assertFalse(iceepeecee.ok());

    }

    

    @Test

    

    

    public void shouldNotAddIslandSameColor() {

        iceepeecee = new Iceepeecee(100, 100);

        int[][] vertices1 = {{5, 5}, {15, 5}, {10, 15}};

        iceepeecee.addIsland("yellow", vertices1);

        assertTrue(iceepeecee.ok());



        int[][] vertices2 = {{20, 20}, {30, 20}, {25, 30}};

        iceepeecee.addIsland("yellow", vertices2);

        assertFalse(iceepeecee.ok());

    }



    @Test



    public void shouldDelIsland() {

        iceepeecee = new Iceepeecee(100, 100);

        int[][] vertices = {{5, 5}, {15, 5}, {10, 15}};

        iceepeecee.addIsland("blue", vertices);

        iceepeecee.delIsland("blue");

        assertTrue(iceepeecee.ok());

    }



    

    @Test

    

    

    public void shouldAddFlight() {

        iceepeecee = new Iceepeecee(100, 100);

        int[] from = {10, 10};

        int[] to = {20, 20};

        iceepeecee.addFlight("red", from, to);

        assertTrue(iceepeecee.ok());

    }



    @Test

    

    public void shouldNotAddFlightSameColor() {

        iceepeecee = new Iceepeecee(100, 100);

        int[] from1 = {10, 10, 10};

        int[] to1 = {20, 20, 20};

        int[] from2 = {30, 30, 30};

        int[] to2 = {40, 40, 30};

        iceepeecee.addFlight("blue", from1, to1);

        assertTrue(iceepeecee.ok());

        iceepeecee.addFlight("blue", from2, to2);

        assertFalse(iceepeecee.ok());  

    }

    

    @Test

    

    public void shouldDelFlight() {

        iceepeecee = new Iceepeecee(100, 100);

        int[] from = {10, 10};

        int[] to = {20, 20};

        iceepeecee.addFlight("red", from, to);

        iceepeecee.delFlight("red");

        assertTrue(iceepeecee.ok());

    }

    

    

    @Test

    

    public void shouldNotFlightOutsideBounds() {

        iceepeecee = new Iceepeecee(100, 100);

        int[] from = {105, 106};

        int[] to = {200, 260};

        iceepeecee.addFlight("blue", from , to);

        assertFalse(iceepeecee.ok());

    }

    

    @Test

    public void shouldPhotographSingleFlight() {

        iceepeecee = new Iceepeecee(100, 100);

        int[] from = {10, 10,5};

        int[] to = {20, 20,30};

        iceepeecee.addFlight("red", from, to);

    

        assertTrue(iceepeecee.ok()); 



        iceepeecee.photograph("red", 45);

        assertTrue(iceepeecee.ok()); 

    }

    

    @Test

    public void shouldNotPhotographInvalidAngle() {

        iceepeecee = new Iceepeecee(100, 100);

        int[] from = {10, 10};

        int[] to = {20, 20};

        iceepeecee.addFlight("red", from, to);

    

        assertTrue(iceepeecee.ok());  



        iceepeecee.photograph("red", 95); 

        assertFalse(iceepeecee.ok()); 

    }

TORRES-VALENCIA[EDITAR]
@Test

    public void testAddIsland() {

            iceepeecee.addIsland("62B2AB", new int[][]{{40, 20}, {60, 10}, {75, 20}, {60, 30}});

            assertTrue(iceepeecee.ok());

            iceepeecee.addIsland("62B2AB", new int[][]{{20, 30}, {50, 50}, {10, 50}});

            assertFalse(iceepeecee.ok()); //Debería no añadirla

            iceepeecee.addIsland("6283B2", new int[][]{{40, 20}, {60, 10}, {75, 20}, {60, 30}});

            assertFalse(iceepeecee.ok()); //Debería no añadirla

        

        

    }



    @Test

    public void testDelIsland() {

            iceepeecee.addIsland("7E62B2", new int[][]{{20, 30}, {50, 50}, {10, 50}});

            iceepeecee.delIsland("7E62B2");

            assertTrue(iceepeecee.ok());

            iceepeecee.delIsland("B262A2");

            assertFalse(iceepeecee.ok());

            assertEquals(false,iceepeecee.ok());

        

    }



    @Test

    public void testAgregarVuelo() {

            iceepeecee.addFlight("00997F", new int[]{55, 0, 20}, new int[]{70, 60, 10});

            assertTrue(iceepeecee.ok());

            iceepeecee.addFlight("00997F", new int[]{55, 5, 60}, new int[]{80, 60, 100});

            assertFalse(iceepeecee.ok()); //No deberia agregar vuelo

            iceepeecee.addFlight("008D99", new int[]{55, 0, 20}, new int[]{70, 60, 10});

            assertFalse(iceepeecee.ok()); //No debería agregar vuelo

            assertEquals(false,iceepeecee.ok());        

    }



    @Test

    public void testDelFlight() {

            iceepeecee.addFlight("F08080", new int[]{0,30,20}, new int[]{78, 70, 5});

            iceepeecee.delFlight("F08080");

            assertTrue(iceepeecee.ok());

            iceepeecee.delFlight("560099"); //Se elimina un vuelo que no existe

            assertFalse(iceepeecee.ok());

        

        

    }



    @Test

    public void testIslandLocation() {

            iceepeecee.addIsland("FFA07A", new int[][]{{45,60},{55,55},{60,60},{55,65}});

            int[][] location = iceepeecee.islandLocation("FFA07A");

            assertEquals(45, location[0][0]);

            assertEquals(60, location[0][1]);

            assertEquals(55, location[1][0]);

            assertEquals(55, location[1][1]);

            assertEquals(60, location[2][0]);

            assertEquals(60, location[2][1]);

            assertEquals(55, location[3][0]);

            assertEquals(65, location[3][1]);

            

            iceepeecee.islandLocation("990025");

            assertFalse(iceepeecee.ok());

        

    }

    @Test

    public void testFlightLocation() {

            iceepeecee.addFlight("green", new int[]{5, 5, 10}, new int[]{15, 5, 10});

            int[][] location = iceepeecee.flightLocation("green");

            assertEquals(5, location[0][0]);

            assertEquals(5, location[0][1]);

            assertEquals(10, location[0][2]);

            assertEquals(15, location[1][0]);

            assertEquals(5, location[1][1]);

            assertEquals(10, location[1][2]);

            //test con un vuelo que no existe

            int[][] location2 = iceepeecee.flightLocation("000000");

            assertFalse(iceepeecee.ok());

        

    }



    @Test

    public void testPhotograph() {

            iceepeecee.addFlight("009499", new int[]{55, 0, 20}, new int[]{70, 60, 10});

            iceepeecee.photograph("009499", 45);

            assertTrue(iceepeecee.ok());

            iceepeecee.photograph("009499", 80);

            assertTrue(iceepeecee.ok());

            iceepeecee.addFlight("279900", new int[]{8, 3, 10}, new int[]{15, -1, 5});

            iceepeecee.photograph("279900", 75);

            assertTrue(iceepeecee.ok());

            //test fotografiando un vuelo que no existe

            iceepeecee.photograph("orange", 75);

            assertFalse(iceepeecee.ok());

            

            //test photograph(int teta)

            iceepeecee.photograph(30);

            assertTrue(iceepeecee.ok());

            iceepeecee.photograph(20);

            assertTrue(iceepeecee.ok());

            

            //test photograph(float teta)

            iceepeecee.photograph(48.031693036f);

            assertTrue(iceepeecee.ok());

            iceepeecee.photograph(55.45987f);

            assertTrue(iceepeecee.ok());

            iceepeecee.photograph(65.5f);

            assertTrue(iceepeecee.ok());

        

    }

//CHICUAZUQUE-SIERRA[EDITAR]


    

    //pruebas para los metodos en general de icepece

    @Test

    public void testAgregarIslaYAgregarVueloChicuazuqueSierra() throws Exception {

        // Agregar tres islas al escenario.

        iceepeecee = new Iceepeecee(100, 100);

        iceepeecee.addIsland("red", new int[][]{{20, 30}, {50, 50}, {10, 50}});

        iceepeecee.addIsland("green", new int[][]{{40, 20}, {60, 10}, {75, 20}, {60, 30}});

        iceepeecee.addIsland("blue", new int[][]{{45, 60}, {55, 55}, {60, 60}, {55, 65}});

        

        // Verificar que las islas se agregaron correctamente.

        assertTrue(iceepeecee.ok());



        // Agregar dos vuelos al escenario.

        iceepeecee.addFlight("fligth1", new int[]{0, 30, 20}, new int[]{78, 70, 5});

        iceepeecee.addFlight("fligth2", new int[]{55, 0, 20}, new int[]{70, 60, 10});



        // Verificar que los vuelos se agregaron correctamente.

        assertTrue(iceepeecee.ok());



    }

    

    @Test

    public void testEliminarIslaChicuazuqueSierra() throws Exception {

        // Agregar una isla al escenario.

        iceepeecee = new Iceepeecee(100, 100);

        iceepeecee.addIsland("red", new int[][]{{20, 30}, {50, 50}, {10, 50}});



        // Eliminar la isla.

        iceepeecee.delIsland("red");



        // Verificar que la isla se eliminó correctamente.

        assertTrue(iceepeecee.ok());



    }



    @Test

    public void testEliminarVueloChicuazuqueSierra() throws Exception {

        // Agregar un vuelo al escenario.

        iceepeecee = new Iceepeecee(100, 100);

        iceepeecee.addFlight("fligth1", new int[]{0, 30, 20}, new int[]{78, 70, 5});



        // Eliminar el vuelo.

        iceepeecee.delFlight("fligth1");



        // Verificar que el vuelo se eliminó correctamente.

        assertTrue(iceepeecee.ok());



        // Intentar eliminar un vuelo que no existe.

        iceepeecee.delFlight("red");



        // Verificar que se maneja correctamente el caso en que el vuelo no existe.

        assertFalse(iceepeecee.ok());

    }

    

    @Test

    public void testMostrarLaZonaFotografiadaChicuazuqueSierra() throws Exception{

        iceepeecee = new Iceepeecee(100, 100);

        iceepeecee.addFlight("fligth1", new int[]{0, 30, 20}, new int[]{78, 70, 5});

        iceepeecee.photograph("fligth1",45);

        assertTrue(iceepeecee.ok());

    }

    

    @Test

    public void testInput1IslandsChicuazuqueSierra() throws Exception {

        iceepeecee = new Iceepeecee(100, 100);

        iceepeecee.addIsland("red", new int[][]{{20, 30}, {50, 50}, {10, 50}});



        String[] expectedIslands = {"red"};

        String[] result = iceepeecee.islands();



        assertArrayEquals(expectedIslands, result);

    }



    @Test

    public void testInput1FlightsChicuazuqueSierra() throws Exception {

        iceepeecee = new Iceepeecee(100, 100);

        iceepeecee.addFlight("fligth1", new int[]{0, 30, 20}, new int[]{78, 70, 5});

        String[] expectedFlights = {"fligth1"};

        String[] result = iceepeecee.flights();



        assertArrayEquals(expectedFlights, result);

    }

    

    @Test

    public void testIslandsChicuazuqueSierra() throws Exception{

        iceepeecee = new Iceepeecee(100, 100);

        // Agregar islas al escenario

        iceepeecee.addIsland("red", new int[][]{{20, 30}, {50, 50}, {10, 50}});

        iceepeecee.addIsland("green", new int[][]{{40, 20}, {60, 10}, {75, 20}});

        iceepeecee.addIsland("blue", new int[][]{{45, 60}, {55, 55}, {60, 60}});



        // Obtener los nombres de las islas y verificar

        String[] expectedIslands = {"red", "green", "blue"};

        assertArrayEquals(expectedIslands, iceepeecee.islands());

    }



    @Test

    public void testFlightsChicuazuqueSierra() throws Exception{

        iceepeecee = new Iceepeecee(100, 100);

        // Agregar vuelos al escenario

        iceepeecee.addFlight("fligth1", new int[]{0, 30, 20}, new int[]{78, 70, 5});

        iceepeecee.addFlight("fligth2", new int[]{55, 0, 20}, new int[]{70, 60, 10});



        // Obtener los nombres de los vuelos y verificar

        String[] expectedFlights = {"fligth1", "fligth2"};

        assertArrayEquals(expectedFlights, iceepeecee.flights());

    }

    @Test

    public void testVisibleChicuazuqueSierra() throws Exception{

        iceepeecee = new Iceepeecee(100, 100);

        iceepeecee.addFlight("fligth1", new int[]{0, 30, 20}, new int[]{78, 70, 5});

        iceepeecee.addIsland("red", new int[][]{{20, 30}, {50, 50}, {10, 50}});

        iceepeecee.photograph(45);

        iceepeecee.makeVisible();

        assertTrue(iceepeecee.ok());

    }

    

    @Test

    public void testInvisibleChicuazuqueSierra() throws Exception{

        iceepeecee = new Iceepeecee(100, 100);

        iceepeecee.addFlight("fligth1", new int[]{0, 30, 20}, new int[]{78, 70, 5});

        iceepeecee.addIsland("red", new int[][]{{20, 30}, {50, 50}, {10, 50}});

        iceepeecee.makeVisible();

        iceepeecee.makeInvisible();

        assertTrue(iceepeecee.ok());

    }

MILTON ANDRES GUTIERREZ - JHON SEBASTIAN SOSA

/**

 * The test class IceepeeceeC1Test.

 *

 * @author  Milton Gutierrez - Jhon Sosa.

 * @version 4/11/2023

 */

public class IceepeeceeC1Test



{



private Iceepeecee iceepeecee1;







    @BeforeEach



    public void setUp() {

        iceepeecee1 = new Iceepeecee(300, 300);

    }



    //Creacion de Iceepeecee

    @Test



    public void accordingGSShouldCreateMapofIceepeeceeCorrectly(){

        int width = 1000;

        int length = 1000;

        Iceepeecee iceepeecee2 = new Iceepeecee(width, length);

        assertTrue(iceepeecee2.ok());



    }

    

    @Test

    public void accordingGSShouldNotAddTablero(){

        int width = 99;

        int length = 120;

        Iceepeecee iceepeecee2 = new Iceepeecee(width, length);

        assertFalse(iceepeecee2.ok()); 



    }

    

    @Test



    //Creacion de Island

    public void accordingGSShouldAddIsland(){

        //Definimos variables para la isla 

        int[][] polygon = {{5,50},{50,100},{100,200}};

        String color = "red";

        iceepeecee1.addIsland(color, polygon);

        assertTrue(iceepeecee1.ok()); //Debe verificar que se haya creado correctamente

        Island islaPrueba = new NormalIsland(color, polygon);

        assertTrue(iceepeecee1.getIsland(color).equals(islaPrueba)); //La isla creada debe estar almacenada y debe ser igual a una creada con las mismas caracteristicas.



    }



    @Test

    public void accordingGSShouldNotAddIslandsWithLessThanThreeVertices(){

        //Creamos los parametros para la isla

        int[][] polygon = {{5,50},{50,100}}; //tiene menos de tres vertices.

        String color = "red";

        iceepeecee1.addIsland(color, polygon);

        assertFalse(iceepeecee1.ok());

    }

    

    @Test

    public void accordingGSShouldNotAddIslandsWithUsedColor(){

        //Creamos los parametros para la isla

        int[][] polygon1 = {{5,50},{50,100}, {100,20}};

        String color = "red";

        iceepeecee1.addIsland(color, polygon1); // debe aÃ±adir la isla ( para pruebas)

        assertTrue(iceepeecee1.ok());

        int[][] polygon2 = {{200,0}, {250,200}, {300,300}};

        iceepeecee1.addIsland(color, polygon2); // no se pues aÃ±adir una isla con un color ya usado.

        assertFalse(iceepeecee1.ok());

    }

    

    @Test

    public void accordingGSShouldNotAddIslandsWithLargerThanTheMap(){

        //Creamos los parametros para la isla

        int[][] polygon3 = {{301,100},{0,100}, {0,100}, {100,400}};

        String color = "blue";

        iceepeecee1.addIsland(color, polygon3); //no puede aÃ±adir una isla que se salga de los limites del tablero

        assertFalse(iceepeecee1.ok());

    }

    

    @Test

    public void accordingGSShouldNotAddIslandThatIntersectsWithCreatedOnes(){

        int[][] polygon4 = {{250,30},{250,50},{275,60}};

        String color1 = "blue";

        iceepeecee1.addIsland(color1, polygon4);

        assertTrue(iceepeecee1.ok()); //Debe poder aÃ±adir la isla (para pruebas)

        int[][] polygon5 = {{50, 50}, {200,200}, {150,100}};

        String color2 = "black";

        iceepeecee1.addIsland(color1, polygon5); // No aÃ±ade la isla ya que interseca con la definida anteriormente

        assertFalse(iceepeecee1.ok());

    }



    



    @Test



    //Creacion de flight



    public void accordingGSShouldAddFlight(){

        //Definimos variables para el vuelo

        int[] from = {5, 50, 10};

        int[] to = {50, 100, 10};

        String color = "red";

        iceepeecee1.addFlight(color, from, to);

        assertTrue(iceepeecee1.ok()); //Debe verificar que se haya creado correctamente

        Flight vueloPrueba = new Flight(color, from, to);

        assertTrue(iceepeecee1.getFlight(color).equals(vueloPrueba)); //El vuelo creado debe estar almacenado y debe ser igual a uno creado con las mismas caracteristicas.

    }



    



    @Test 

    public void accordingGSShouldNotaddFlightLargerThanTablero(){

        //Definimos variables para el caso 1

        int[] from = {5, 50, 20};

        int[] to = {301, 100, 10};

        String color = "red";

        iceepeecee1.addFlight(color, from, to); //No aÃ±ade el vuelo ya que se pasa de los limites del tablero.

        assertFalse(iceepeecee1.ok());

    }

    

    @Test

    public void accordingGSShouldNotaddFlightWithUsedColors(){

        //Definimos variables para caso 2

        int[] from1 = {5, 50, 100};

        int[] to1 = {30, 100, 100};

        String color = "red";

        iceepeecee1.addFlight(color, from1, to1); //debe aÃ±adir el vuelo correctamente

        assertTrue(iceepeecee1.ok());

        int[] from2 = {0, 0, 20};

        int[] to2 = {34, 100, 20};

        iceepeecee1.addFlight(color, from2, to2); //No aÃ±ade el vuelo ya que el color ya se uso.

        assertFalse(iceepeecee1.ok());



    }



    @Test

    public void accordingGSShouldNotaddFlightWithIncorrectNumberOfCoordinates(){

        int[] from3 = {5, 50};

        int[] to3 = {301};

        String color3 = "blue";

        iceepeecee1.addFlight(color3, from3, to3); //No aÃ±ade el vuelo ya que este debe tener tres coordenas exactamente.

        assertFalse(iceepeecee1.ok());

    }



    



    //flightLocation



    @Test



    public void accordingGSShouldReturnflightLocation(){

        int[] from = {5,50, 10};

        int[] to = {30,100, 10};

        String color = "red";

        iceepeecee1.addFlight(color, from, to);

        assertEquals(iceepeecee1.flightLocation(color).length, 2);

    }



    



    @Test

    public void accordingGSShouldReturnEmptyGflightLocation(){

        assertEquals(iceepeecee1.flightLocation("red").length, 0); //Nos retorna un arreglo vacio. Debidp no se cumplio correctamente el objetivo de la funcion.

    }

    

    //islandLocation



    @Test

    public void accordingGSShouldReturnIslandLocation(){

        int[][] polygon = {{0,1},{20,20},{10,10}};

        String color = "red";

        iceepeecee1.addIsland(color, polygon);

        assertEquals(iceepeecee1.islandLocation(color).length, 3);

    }



    

    @Test

    public void accordingGSSouldReturnEmptyIslandLocation(){

        String color = "red";

        assertEquals(iceepeecee1.islandLocation(color).length, 0);

    }



    //delIsland



    @Test



    public void accordingGSShouldDeleteIsland(){

        //Definimos variables para la isla 

        int[][] polygon = {{5,50},{50,100},{100,200}};

        String color = "red";

        iceepeecee1.addIsland(color, polygon);   

        iceepeecee1.delIsland(color);

        assertTrue(iceepeecee1.ok());

        assertEquals(iceepeecee1.islandLocation(color).length, 0);

    }



    



    @Test



    public void accordingGSShouldnotDeleteIslandThatDoesntExist(){

        //Definimos variables

        String color = "red";

        iceepeecee1.delIsland(color);

        assertFalse(iceepeecee1.ok());

    }



    



    @Test



    //delflight



    public void accordingGSShouldDelFlight(){

        //Definimos variables para el vuelo

        int[] from = {5, 50, 10};

        int[] to = {50, 100, 10};

        String color = "red";

        iceepeecee1.addFlight(color, from, to);

        iceepeecee1.delFlight(color);

        assertTrue(iceepeecee1.ok()); //Debe verificar que se haya creado correctamente

        }



        

    @Test

    public void accordingGSShouldnotDelFlight(){

        //Definimos variables para el vuelo

        int[] from = {5, 50, 10};

        int[] to = {50, 100, 10};

        String color = "red";

        iceepeecee1.delFlight(color);

        assertFalse(iceepeecee1.ok()); //Debe verificar que se haya creado correctamente

        }



    //Photograp(flight,teta) - photograph(teta)



    @Test



    public void accordingGSShouldtakePhoto(){

        int[] from = {5, 50, 10};

        int[] to = {50, 100, 10};

        String color = "red";

        iceepeecee1.addFlight(color, from, to);

        assertTrue(iceepeecee1.ok());

        iceepeecee1.photograph(color, 50);

        assertTrue(iceepeecee1.ok());

        int[] from1 = {100, 100, 40};

        int[] to1 = {200, 200, 30};

        String color1 = "blue";

        iceepeecee1.addFlight(color1, from1, to1);

        assertTrue(iceepeecee1.ok());

        iceepeecee1.photograph(50);

        assertTrue(iceepeecee1.ok());

        iceepeecee1.makeInvisible();

    }



    @Test

    public void accordingGSShouldNottakePhoto(){

        int[] from = {5, 50, 10};

        int[] to = {50, 100, 10};

        String color = "red";

        iceepeecee1.addFlight(color, from, to); // Debe aÃ±adir el vuelo

        assertTrue(iceepeecee1.ok());

        iceepeecee1.photograph(color, -1); // No debe tomar la fotografÃ­a.

        assertFalse(iceepeecee1.ok());



        int[] from1 = {100, 100, 40};

        int[] to1 = {200, 200, 30};

        String color1 = "blue";

        iceepeecee1.addFlight(color1, from1, to1);// Debe aÃ±adir el vuelo

        assertTrue(iceepeecee1.ok());

        iceepeecee1.photograph(100); // No debe tomar la fotografÃ­a.

        assertFalse(iceepeecee1.ok());

    }



    @Test



    public void accordingGSShouldReturnFlightCamera(){

        int[] from = {5,50, 10};

        int[] to = {30,100, 10};

        String color = "red";

        iceepeecee1.addFlight(color, from, to);

        iceepeecee1.flightCamera(color);

        assertTrue(iceepeecee1.ok());

    }



    @Test



    public void accordingGSShouldReturnEmptyFlightCamera(){

        int width = 300;

        int length = 300;

        Iceepeecee iceepeecee1 = new Iceepeecee(length, width); //Creamos el Iceepeecee

        String color = "red"; //Definimos el color que servirÃ¡ para buscar el vuelo inexistete

        assertEquals(iceepeecee1.flightCamera(color), -1);

    

    }

}

//Mendivelso_Rodriguez



public class Pruebas

{   

    private iceepeecee Icep;

    /**

     * Default constructor for test class Pruebas

     */

    @Before

    public void setUp(){

        Icep = new iceepeecee (1000,1000);

    }



    /**

     * Prueba de unidad para verificar la funcionalidad de agregar una isla en el iceepeecee.

     */

    @Test

    public void shouldAddIsland() {

        int[][] vertices = {{100, 100}, {200, 100}, {150, 200}};

        Icep.addIsland("blue", vertices);

        assertEquals(true, Icep.ok()); // Verifica que Icep.ok() devuelva true

    }



    /**

     * Prueba de unidad para verificar la funcionalidad de agregar un vuelo en el iceepeecee.

     */

    @Test

    public void shouldAddFlight(){

        int[]from={0,20,40}; 

        int[]to={70,80,50};

        int cameraAngle = 90;

        String color="purple";

        Icep.addFlight(color,from,to,cameraAngle);

        assertEquals(true,Icep.ok()); // Verifica que Icep.ok() devuelva true

    }

    



    /**

     * Prueba de unidad para verificar que no se crea un vuelo en el sistema Icep cuando se proporcionan datos incorrectos.

     */



    @Test

    public void shouldNotAddFlight() {

        int[] from = {30,30};

        int[] to = {45};

        int cameraAngle = 30;

        // Intentamos agregar un vuelo con datos insuficientes o incorrectos

        // Esto debería resultar en un fallo y que Icep.ok() devuelva false

        Icep.addFlight("red",from, to,cameraAngle);

        assertEquals(false,Icep.ok());// Verifica que Icep.ok() devuelva false

    }

    

    /**

     * Prueba de unidad para verificar que no se crea una isla en el sistema Icep cuando se proporcionan vértices insuficientes.

     */

    @Test 

    public void shouldNotAddIsland() {

        int[][] vertices = {{100,100},{100,200}};

        // Intentamos agregar una isla con vértices insuficientes

        // Esto debería resultar en un fallo y que Icep.ok() devuelva false

        Icep.addIsland("blue",vertices);

        assertEquals(false,Icep.ok());

    }

    

    /**

     * Prueba de unidad para verificar que no se crea una isla en el iceepeecee si se intenta agregar una isla

     * con vértices idénticos(isla sobrepuesta en otra)

     */

    @Test

    public void shouldnotAddIsland_2(){

        int[][] vertices = {{40,40},{120,120},{50,30}};

        Icep.addIsland("green",vertices);

        int[][] vertices2 = {{40,40},{120,120},{10,20}};

        Icep.addIsland("yellow",vertices);

        assertEquals(false,Icep.ok());

    }

    

    /**

     * Prueba de unidad para verificar que no se crea una isla en el iceepeecee si se intenta agregar una isla

     * con un mismo color que ya existe.

     */

    @Test

    public void shouldnotAddIsland_3(){

        int[][] vertices = {{20,20},{100,50},{80,30}};

        Icep.addIsland("blue",vertices);

        int[][] vertices3 = {{20,20},{100,50},{20,20}};

        Icep.addIsland("blue",vertices3);

        assertEquals(false,Icep.ok());

    }

    

    /**

     * Prueba de unidad para verificar que el iceepeecee puede consultar las islas existentes 

     */

    @Test

    public void shouldGetAllIslands() {

        

        int[][] isla1={{100, 100}, {200, 100}, {150, 200}};

        Icep.addIsland("blue", isla1);

        int[][] isla2 = {{300, 300}, {400, 300}, {350, 400}};

        Icep.addIsland("red", isla2);

        Icep.islands();

        assertEquals(true,Icep.ok());

    }

    

    @Test

    public void shouldPhotograph(){

        int[]from={0,20,40}; 

        int[]to={70,80,50};

        int cameraAngle = 90;

        String color="purple";

        Icep.addFlight(color,from,to,cameraAngle);

        Icep.photograph(color,30);

        assertTrue(Icep.ok());

    }

    

    @Test

    public void shouldnotPhotograph(){

        int[]from={10,15,13}; 

        int[]to={56,70,95};

        int[]from2={55,50,36};

        int[]to2={80,60,70};

        Icep.addFlight("red",from,to,30);

        Icep.addFlight("yellow",from2,to2,35);

        Icep.photograph(-63.1f);

        assertFalse(Icep.ok());

    }

    

}





// Montero-Villamizar

@Test

    public void shouldAddIsland() {

        int[][] vertices = {{20, 20}, {100, 50}, {80, 30}};

        ip.addIsland("green", vertices);

        assertTrue(ip.ok());

    }



    @Test

    public void shouldNotAddIsland() {

        ip.delIsland("red");

        assertFalse(ip.ok());

    }



    @Test

    public void shouldDelIsland() {

        int[][] vertices = {{20, 20}, {100, 50}, {80, 30}};

        ip.addIsland("green", vertices);

        ip.makeVisible();

        ip.delIsland("green");

        assertTrue(ip.ok());

    }

 

    

    @Test

    public void shouldNotDelIsland() {

        int[][] vertices = {{20, 20}, {100, 50}, {80, 30}};

        ip.addIsland("green", vertices);

        ip.makeVisible();

        ip.delIsland("red");

        assertFalse(ip.ok());

    }

    

    @Test

    public void shouldAddFlight() {

        int[] from = {20, 20, 10};

        int[] to = {50, 10, 15};

        ip.addFlight("green", from, to);

        assertTrue(ip.ok());

    }



    @Test

    public void shouldNotAddFlight() {

        int[] from = {20, 20};

        int[] to = {10};

        ip.addFlight("magenta", from, to);

        assertFalse(ip.ok());

    }



    @Test

    public void shouldNotDelFlight() {

        int[] from = {20, 20, 10};

        int[] to = {50, 10, 15};

        ip.addFlight("green", from, to);

        ip.delFlight("red");

        assertFalse(ip.ok());

    }



    @Test

    public void shouldDelFlight() {

        int[] from = {20, 20, 10};

        int[] to = {50, 10, 15};

        ip.addFlight("green", from, to);

        ip.delFlight("green");

        assertTrue(ip.ok());

    }

    

    @Test

    public void shouldMakeVisible(){

        ip.makeVisible();

        assertTrue(ip.ok());        

    }

    

    @Test

    public void shouldMakeInvisible(){

        ip.makeInvisible();

        assertTrue(ip.ok());        

    }



    

    

    

    @Test

    public void shouldAddIslandAndFlight() {

        int[][] vertices = {{20, 20}, {100, 50}, {80, 30}};

        int[] from = {20, 20, 10};

        int[] to = {50, 10, 15};

        ip.addIsland("green", vertices);

        ip.addFlight("green", from, to);

        assertTrue(ip.ok());

    }



    @Test

    public void shouldNotAddIslandAndFlight() {

        int[][] vertices = {{20, 20}, {100, 50}};

        int[] from = {20, 20};

        int[] to = {10};

        ip.addIsland("green", vertices);

        ip.addFlight("magenta", from, to);

        assertFalse(ip.ok());

    }



    @Test

    public void shouldGetIslandLocation() {

        int[][] vertices = {{20, 20}, {100, 50}, {80, 30}};

        ip.addIsland("green", vertices);

        ip.makeVisible();

        ip.islandLocation("green");

        assertTrue(ip.ok());

    }







    @Test

    public void shouldGetPhotographedZone() {

        int[] from = {20, 20, 20};

        int[] to = {50, 10, 10};

        ip.addFlight("magenta", from, to);

        float teta = 1.5708f; // 90 degrees

        ip.photograph("magenta", teta);



        // Verify the photographed zone

        PhotographedZone photo = ip.getPhotographedZoneForFlight("magenta");

        assertNotNull(photo);

        assertArrayEquals(from, photo.getFrom());

        assertArrayEquals(to, photo.getTo());

    }



    @Test

    public void shouldUselessFlights() {

        int[][] vertices = {{20, 20}, {100, 50}, {80, 30}};

        int[] from = {20, 20, 10};

        int[] to = {50, 10, 15};

        ip.addIsland("green", vertices);

        ip.addFlight("green", from, to);



        int[][] vertices2 = {{30, 30}, {110, 50}, {90, 30}};

        int[] from2 = {30, 30, 10};

        int[] to2 = {60, 20, 15};

        ip.addIsland("blue", vertices2);

        ip.addFlight("blue", from2, to2);



        ip.uselessFlights();



        assertTrue(ip.ok());

    }

//Robinson - Yaya

@Test

    public void shouldAddIsland(){

        int[][] vertix = {{10,10},{50,30},{20,20}};

        String color  = "250,0,0,250";

        icp.addIsland(color,vertix);

        assertTrue(icp.ok());

    }

    

    @Test

    public void shouldnotAddIsland(){///////////////////////////////////

        int[][] vertix = {{20,20},{100,50}};

        icp.addIsland("green",vertix);

        assertFalse(icp.ok());

    }

    

    @Test

    public void shouldnotAddIsland2(){/////////////////////////////////////

        int[][] vertixor = {{20,20},{100,50},{80,30}};

        icp.addIsland("green",vertixor);

        int[][] vertix = {{20,20},{100,50},{20,20}};

        icp.addIsland("yellow",vertix);

        assertFalse(icp.ok());

    }

    

    @Test

    public void shouldAddFlight(){

        int[] from = {10, 10};

        int[] to = {20, 20};

        icp.addFlight("blue", from, to);

        assertTrue(icp.ok());

        //assertTrue("El método addFlight no agrega un vuelo correctamente.", flightColors.length > 0);

    }

    

    @Test 

    public void shouldgetLocationIsland(){

        int[][] vertix = {{20,20},{100,50},{80,30}};

        icp.addIsland("green",vertix);

        int[][] expectedResult = {{20,20},{100,50},{80,30}};

        int[][] result = icp.islandLocation("green");

        

        //for (int i = 0 ; i < vertix.length ; i ++){

            //if (expectedResult[i][0] != result[i][0] && expectedResult[i][1] != result[i][1] ){

                //throw new IllegalArgumentException("No es valido");

            //}

        //}

    }

    

    @Test

    public void shouldgetLocationflight(){

        int[] from = {20,20};

        int[] to = {50,10};

        icp.addFlight("magenta",from,to);

        int[][] expectedResult ={{20,20},{50,10}};

        int[][] result = icp.flightLocation("magenta");

        

        //for (int i = 0 ; i < expectedResult.length ; i ++){

            //if (expectedResult[i][0] != result[i][0] && expectedResult[i][1] != result[i][1] ){

                //throw new IllegalArgumentException("No es valido");

            //}

        //}

    }

    

    @Test 

    public void shouldnotgetLocationIsland(){

        int[][] vertix = {{20,20},{100,50},{80,30}};

        icp.addIsland("green",vertix);

        icp.islandLocation("magenta");

        //int[][] expectedResult = {{20,20},{100,50},{80,30}}; 

        //int[][] result = icp.IslandLocation("magenta");

        //for (int i = 0 ; i < vertix.length ; i ++){

            //if (expectedResult[i][0] != result[i][0] && expectedResult[i][1] != result[i][1] ){

              //  throw new IllegalArgumentException("No es valido");

            //}

        // }

    }

    

    @Test 

    public void shouldnotgetLocationFlight(){

        int[] from = {20,20};

        int[] to = {50,10};

        icp.addFlight("magenta",from,to);

        //int[][] expectedResult ={{20,20},{50,10}};

        icp.flightLocation("green");

        //int[][] result = icp.flightLocation("magenta");

        // for (int i = 0 ; i < expectedResult.length ; i ++){

           // if (expectedResult[i][0] != result[i][0] && expectedResult[i][1] != result[i][1] ){

            //    throw new IllegalArgumentException("No es valido");

            //}

        //}

        assertFalse(icp.ok());

    }

    

    @Test

    public void shouldDelIsland(){

        int[][] vertix = {{20,20},{100,50},{80,30}};

        icp.addIsland("green",vertix);

        icp.delIsland("green");

    }

    

    @Test

    public void shouldnotdelIsland(){///////////////////////////////////////////////

        int[][] vertix = {{20,20},{100,50},{80,30}};

        icp.addIsland("green",vertix);

        icp.delIsland("white");

        assertFalse(icp.ok());

    }

    

    @Test

    public void testFlightCamera() {

        Iceepeecee simulator = new Iceepeecee(100, 100);

        String color1 = "255,0,0";

        int[] from1 = {10, 10};

        int[] to1 = {20, 20};

        



        String color2 = "0,0,255";

        int[] from2 = {30, 30};

        int[] to2 = {40, 40};

        



        simulator.addFlight(color1, from1, to1);

        simulator.addFlight(color2, from2, to2);



        int cameraAngleForColor1 = simulator.flightCamera(color1);

        int cameraAngleForColor2 = simulator.flightCamera(color2);

    }

    

   @Test

    public void shouldPhotograph(){

        int[]from={0,20,40}; 

        int[]to={70,80,50};

        String color="blue";

        icp.addFlight(color,from,to);

        icp.photograph(color,30);

        assertTrue(icp.ok());

    }

    

    @Test

        public void ShouldNotDelFlight(){

    

        // Agregar un vuelo con un color

        icp.addFlight("blue", new int[]{50, 50, 0}, new int[]{100, 100, 0});

        // Intentar agregar otro vuelo con el mismo color

        icp.delFlight("red");

    

        // Debe asegurarse de que el segundo vuelo no se agregó y devuelve false

        assertFalse(icp.ok());

    }

    

        @Test

        public void ShouldDelFlight(){

    

        // Agregar un vuelo con un color

        icp.addFlight("blue", new int[]{50, 50, 0}, new int[]{100, 100, 0});

        // Intentar agregar otro vuelo con el mismo color

        icp.delFlight("blue");

    

        // Debe asegurarse de que el segundo vuelo no se agregó y devuelve false

        assertTrue(icp.ok());

    }

    

    @Test

    public void ShouldVisible() {

        // Verificar que el simulador inicialmente no es visible

        icp.makeVisible();

        assertTrue(icp.ok());

    } 

    

    @Test

    public void ShouldInVisible() {

        // Verificar que el simulador inicialmente se vuelve invisible

        icp.makeInvisible();

        assertTrue(icp.ok());

    }



Torres.Fernandez

private Iceepeecee i;
    @BeforeEach
    public void setUpFT() {
        int width = 500;
        int height = 500;
        i = new Iceepeecee(width, height);
        int[][] island1 = {{45,60},{50,45},{80,67}};
        int[][] island2 = {{100,70},{34,89},{45,89},{90,34}};
        int[][] island3 = {{45,60},{55,60},{60,65},{80,45}};
        int[] FligthFrom1 = {0, 30,20};
        int[] FligthTo1 = {78,70,5};
        int[] FligthFrom2 = {55, 0,20};
        int[] FligthTo2 = {70,60,10};
        String color1 = "green";
        String color2 = "red";
        String color3 = "blue";
        String color4 = "magenta";
        i.addIsland(color1, island1);
        i.addIsland(color2, island2);
        i.addIsland(color3, island3);
        i.addFligth(color1, FligthFrom1, FligthTo1);
        i.addFligth(color2, FligthFrom2, FligthTo2);
    }
    
    @Test
    public void shouldFTAddIsland(){
        int[][] island1 = {{20,30},{10,50},{30,50}};
        i.addIsland("magenta",island1);
        assertTrue(i.ok());
    }
    
    @Test
    public void ShouldFTNotAddOsland(){
        int[][] island1 = {{50,30},{10,60},{40,70}};
        i.addIsland("magenta",island1);
        assertFalse(i.ok());
    }
     
    @Test
    public void shouldFTAddFligth(){
        int[] FligthFrom1 = {0, 30,20};
        int[] FligthTo1 = {78,70,5};
        i.addFligth("black", FligthFrom1, FligthTo1);
        assertTrue(i.ok());
    }
    
     @Test
    public void shouldFTNotAddFligth(){
        int[] FligthFrom1 = {-10, 30,20};
        int[] FligthTo1 = {78,70,-5};
        i.addFligth("black", FligthFrom1, FligthTo1);
        assertFalse(i.ok());
    }
 @Test
    public void shouldFTNotDelIsland(){
        i.delIsland("gray");
        assertFalse(i.ok());
    }
    
    @Test
    public void islandFTLocation(){        
        int [][] test = {{45,60},{50,45},{80,67}};
        Assert.assertArrayEquals(test, i.islandLocation("green"));
        assertTrue(i.ok());
    }
 @Test
    public void shouldFTDelFligth(){
        i.delFligth("green");
        assertTrue(i.ok());
    }
    
    @Test
    public void shouldFTNotDelFligth(){    
        i.delFligth("purple");
        assertFalse(i.ok());
    }

@Test
    public void shouldFTMakeVisible(){
        assertTrue(i.ok());
        i.makeVisible();
        assertFalse(i.ok());
    }
    
    @Test
    public void shouldFTMakeInvisible(){
        assertTrue(i.ok());
        i.makeInvisible();
        assertFalse(i.ok());
    }

@Test
    public void shouldFTPhotographColor(){
        Iceepeecee prueba = new Iceepeecee(300, 300);
        int[] from = {55,70,110};
        int[] to = {130,130,70};
        prueba.addFligth(“green", from, to);
        prueba.photograph(“green", 48);
        Assert.assertTrue(prueba.ok());
    }
    
    @Test
    public void shouldFTNotPhotographColor(){
         int[] from = {55,70,110};
        int[] to = {130,130,70};
        i.addFligth("red", from, to);
        i.photograph("green", 48); 
        i.photograph("magenta", -100); 
        Assert.assertFalse(i.ok());
    }
    
    @Test
    public void shouldFTPhotographtatinI(){
         int[] from = {55,70,110};
        int[] to = {130,130,70};
        i.addFligth("red", from, to);
        i.photograph("red", 48);
        Assert.assertTrue(i.ok());
    }
    
    @Test
    public void shouldFTNotPhotographII(){
        i int[] from = {55,70,110};
        int[] to = {130,130,70};
        i.addFligthblueF (", from, to);
        i.photograph("green", 48); 
        i.photograph(“green", -105,48); 
        Assert.assertFalse(i.ok());
    }
 
    @Test
    public void shouldFTPhotographIII(){
          i int[] from = {55,70,110};
        int[] to = {130,130,70};
        i.addFligth(“orange", from, to);
        i.photograph(“orange", 48);
        Assert.assertTrue(i.ok());
    }
    
    @Test
    public void shouldFTNotPhotograph(){
         i int[] from = {55,70,110};
        int[] to = {130,130,70};
        i.addFligth(“purple", from, to);
        i.photograph(“purple" 48); 
        Assert.assertFalse(i.ok());
    }

//Rodríguez Ortegón

// Tests related to adding islands

    @Test

    public void testLRShouldAddIsland() {

        int[][] islandCoordinates = { { 100, 100, 200, 200 }, { 100, 200, 200, 100 } };

        iceepeecee.addIsland("blue", islandCoordinates);

        int[][] islandLocation = iceepeecee.islandLocation("blue");

        assertTrue(iceepeecee.ok());

    }

    

    @Test

    public void testLRShouldNotAddIsland_InvalidCoordinates() {

        int[][] islandCoordinates1 = { { 100, 100, 200, 200 }, { 100, 200, 200, 100 } };

        int[][] islandCoordinates2 = { { 150, 150, 250, 250 }, { 150, 250, 250, 150 } };

        iceepeecee.addIsland("blue", islandCoordinates1);

        iceepeecee.addIsland("red", islandCoordinates2);

        assertFalse(iceepeecee.ok());

    }

    

    // Tests related to adding flights

    @Test

    public void testLRShouldNotAddFlight_ColorExists() {

        iceepeecee.addFlight("blue", new int[]{50, 50}, new int[]{150, 150});

        assertFalse(iceepeecee.ok());

    }



    // Tests related to taking photographs

    @Test

    public void testLRShouldPhotograph() {

        Iceepeecee map = new Iceepeecee();

        int[] from = { 100, 100, 0 };

        int[] to = { 200, 200, 0 };

        String color = "blue";

        map.addFlight(color, from, to);

        int teta = 45;

        map.photograph(color, teta);

        assertTrue(map.ok());

    }

    

    @Test

    public void testLRShouldNotPhotograph() {

        Iceepeecee map = new Iceepeecee();

        int[] from = { 100, 100, 0 };

        int[] to = { 200, 200, 0 };

        String color = "blue";

        map.addFlight(color, from, to);

        int teta = 45;

        map.photograph(color, teta);

        assertFalse(map.ok());

    }

    

    // Tests related to taking photographs with angles

    @Test

    public void testLRShouldNotPhotographWithAngle() {

        iceepeecee.addFlight("red", new int[]{-50, 50}, new int[]{-150, 150});

        int invalidAngle = -500000;

        iceepeecee.photograph(invalidAngle);

        assertFalse(iceepeecee.ok());

    }

    

    // Tests related to deleting flights

    @Test

    public void testLRShouldNotDelFlight() {

        int[] from = { 50, 50 };

        int[] to = { 250, 250 };

        iceepeecee.addFlight("red", from, to);

        iceepeecee.delFlight("red");

        int[][] flightLocation = iceepeecee.flightLocation("red");

        assertNull(flightLocation); 

        assertFalse(iceepeecee.ok()); 

    }

    

    // Tests related to island and flight locations

    @Test

    public void testLRShouldIslandLocation_Exists() {

        int[][] islandCoordinates = { { 100, 100, 200, 200 }, { 100, 200, 200, 100 } };

        iceepeecee.addIsland("blue", islandCoordinates);

        int[][] location = iceepeecee.islandLocation("blue");

        assertNotNull(location);

        assertTrue(iceepeecee.ok());

    }

    

    @Test

    public void testLRShouldNotIslandLocation() {

        assertNull(iceepeecee.islandLocation("blue"));

        assertFalse(iceepeecee.ok());

    }

   

    // Tests related to flight location

    @Test

    public void testLRShouldFlightLocation_Exists() {

        int[] from = { 50, 50 };

        int[] to = { 250, 250 };

        iceepeecee.addFlight("red", from, to);

        int[][] location = iceepeecee.flightLocation("red");

        assertTrue(iceepeecee.ok());

        assertTrue(location != null);

    }



    @Test

    public void testLRShouldNotFlightLocation() {

        int[] from = { 50, 50 };

        int[] to = { 250, 250 };

        iceepeecee.addFlight("red", from, to);

        int[][] location = iceepeecee.flightLocation("blue");

        assertNull(location);

        assertFalse(iceepeecee.ok());

    }

    // Tests related to flight camera    

    @Test

    public void testLRShouldNotFlightCamera_NonExistentFlight() {

        int cameraAngle = iceepeecee.flightCamera("nonexistent_color");

        assertFalse(iceepeecee.ok());

        assertEquals(0, cameraAngle);

    }

ACHURI-GIL[EDITAR]


//Ciclo 1



private Iceepeecee ip;

    @Before

    public void setUp() {

        ip = new Iceepeecee (500,500);

    }

//AddIsland

@Test

    public void shouldnotAddIslandGivenSameVerticesforIslandAchuri_Gil(){

        int[][] vertixor = {{20,20},{100,50},{80,30}};

        ip.addIsland("green",vertixor);

        int[][] vertix = {{20,20},{100,50},{20,20}};

        ip.addIsland("yellow",vertix);

        assertFalse(ip.ok());

    }

    @Test

    public void shouldnotAddIslandGivenSameColorAchuri_Gil(){

        int[][] vertixor = {{20,20},{100,50},{80,30}};

        ip.addIsland("green",vertixor);

        int[][] vertix = {{10,10},{30,40},{60,80}};

        ip.addIsland("green",vertix);

        assertFalse(ip.ok());

    }

@Test

    public void testWholeAddIslandAchuri_Gil() {

        int[][] validPolygon = {{100, 100}, {200, 100}, {200, 200}, {100, 200}};

        ip.addIsland("gray", validPolygon);

        assertTrue(ip.ok());

        int[][] sameColorPolygonDiffPos = {{300, 300}, {400, 300}, {400, 400}, {300, 400}};

        ip.addIsland("gray", sameColorPolygonDiffPos);

        assertFalse(ip.ok());

        int[][] diffColorPolygonSamePos = {{100, 100}, {200, 100}, {200, 200}, {100, 200}};

        ip.addIsland("purple", diffColorPolygonSamePos);

        assertFalse(ip.ok());

        int[][] sameColorPolygonSamePos = {{100, 100}, {200, 100}, {200, 200}, {100, 200}};

        ip.addIsland("gray", sameColorPolygonSamePos);

        assertFalse(ip.ok());

        int[][] diffColorPolygonDiffPos = {{300, 300}, {400, 300}, {400, 400}, {300, 400}};

        ip.addIsland("purple", diffColorPolygonDiffPos);

        assertTrue(ip.ok());

    }

  //addFlight

    @Test

    public void shouldAddFlightBasicAchuri_Gil(){

        int[] from = {20,20,10};

        int[] to = {50,10,15};

        ip.addFlight("green",from,to);

        assertTrue(ip.ok());

    }

    @Test

    public void shouldAddFlightBasic2Achuri_Gil(){

        int[] from = {15,15,15};

        int[] to = {77,70,5};

        ip.addFlight("magenta",from,to);

        assertTrue(ip.ok());

    }

    @Test

    public void shouldnotAddFlightGivenWrongCoordinatesAchuri_Gil(){

        int[] from = {20,20};

        int[] to = {10};

        ip.addFlight("magenta",from,to);

        assertFalse(ip.ok());

    }

    @Test

    public void shouldnotAddFlightGivenWrongCoordinates2Achuri_Gil(){

        int[] from = {20,20,10};

        int[] to = {10,15};

        ip.addFlight("magenta",from,to);

        assertFalse(ip.ok());

    }

    @Test

    public void shouldAddFlightGivenDifferentHeightAchuri_Gil(){

        int[] from = {20,20,10};

        int[] to = {10,15,30};

        ip.addFlight("magenta",from,to);

        int[] from2 = {20,20,20};

        int[] to2 = {10,15,50};

        ip.addFlight("blue",from2,to2);

        assertTrue(ip.ok());

    }

//Photograph

@Test

    public void shouldtakePhotographBasic4AchuriGil(){

        int[]from={0,20,40};

        int[]to={70,80,50};

        int[]from2={45,10,20};

        int[]to2={90,60,30};

        String color="orange";

        String color2="red";

        ip.addFlight(color,from,to);

        ip.addFlight(color2,from2,to2);

        ip.photograph(30);

        ip.photograph(60);

        assertTrue(ip.ok());

    }

    @Test

    public void shouldnotPhotographonInexistentFlightAchuri_Gil(){

        int[]from={0,20,40};

        int[]to={70,80,50};

        String color="blue";

        ip.photograph(color,50);

        assertFalse(ip.ok());

    }

    @Test

    public void shouldnotPhotographWithNegativeAngle(){

        int[]from={0,20,40};

        int[]to={70,80,50};

        String color="pink";

        ip.addFlight(color,from,to);

        ip.photograph(color,-50);

        assertFalse(ip.ok());

    }

    @Test

    public void shouldnotPhotographInAnyFlightWithNegativeAngleAchuri_Gil(){

        int[]from={0,20,40};

        int[]to={70,80,50};

        int[]from2={45,10,20};

        int[]to2={90,60,30};

        String color="orange";

        String color2="red";

        ip.addFlight(color,from,to);

        ip.addFlight(color2,from2,to2);

        ip.photograph(30);

        ip.photograph(-60);

        assertFalse(ip.ok());

    }

//delIsland

@Test

    public void shoulddelValidIslandAchuriGil(){

        int[][] vertix = {{20,20},{100,50},{80,30},{50,20}};

        ip.addIsland("green",vertix);

        ip.makeVisible();

        ip.delIsland("green");

        assertTrue(ip.ok());

    }

    @Test

    public void shouldnotdelInexistingIslandAchuriGil(){

        int[][] vertix = {{20,20},{100,50},{80,30},{10, 20}};

        ip.addIsland("green",vertix);

        ip.makeVisible();

        ip.delIsland("white");

        assertFalse(ip.ok());

    }

    @Test

    public void shouldnotdelIslandThatWasntCreatedAchuriGil(){

        int[][] vertix = {{20,20},{100,50}};

        ip.addIsland("green",vertix);

        ip.makeVisible();

        ip.delIsland("green");

        assertFalse(ip.ok());

    }

//delFlight

     @Test

    public void shoulddelValidFlightAchuri_Gil(){

        int[] from = {15,15,15};

        int[] to = {77,70,5};

        ip.addFlight("magenta",from,to);

        ip.delFlight("magenta");

        assertTrue(ip.ok());

    }

    @Test

    public void shouldnotdelInexistingFlightAchuri_Gil(){

        int[] from = {10,10,10};

        int[] to = {70,70,70};

        ip.addFlight("yellow",from,to);

        ip.delFlight("red");

        assertFalse(ip.ok());

    }

//flight e IslandLocation

@Test

    public void shouldgetLocationIslandAchuri_Gil(){

        int[][] vertix = {{20,20},{100,50},{80,30}};

        ip.addIsland("green",vertix);

        ip.makeVisible();

        ip.islandLocation("green");

        assertTrue(ip.ok());

    }

    @Test

    public void shouldgetLocationIslandBasic2Achuri_Gil(){

        int[][] vertix = {{10, 10}, {30, 10}, {20, 20}, {15, 15}};

        ip.addIsland("red", vertix);

        ip.makeVisible();

        ip.islandLocation("red");

        assertTrue(ip.ok());

    }

    @Test 

    public void shouldnotgetLocationOfInexistentIslandAchuri_Gil(){

        int[][] vertix = {{20},{100,50},{80,30}};

        ip.addIsland("green",vertix);

        ip.makeVisible();

        ip.islandLocation("green");

        assertFalse(ip.ok());

    }  

    @Test

    public void shouldnotgetLocationOfInexistentIsland2Achuri_Gil(){

        int[][] vertix = {{10,10}, {30, 10}, {20,20}, {15, 15}};

        ip.addIsland("red", vertix);

        ip.makeVisible();

        ip.islandLocation("blue");

        assertFalse(ip.ok());

    }

    @Test

    public void shouldnotgetLocationIslandOfIslandNeverCreatedAchuri_Gil(){

        int[][] vertix = {{10,20}, {30, 10}, {20,10}, {15, 15}};

        ip.addIsland("red", vertix);

        ip.makeVisible();

        ip.islandLocation("yellow");

        assertFalse(ip.ok());

    }

    @Test

    public void shouldgetLocationFlightBasicAchuri_Gil(){

        int[] from = {20,20,20};

        int[] to = {50,10,10};

        ip.addFlight("magenta",from,to);

        ip.flightLocation("magenta");

        assertTrue(ip.ok());

    }

    @Test

    public void shouldnotgetLocationOfNeverCreatedFlightAchuri_Gil(){

        int[] from = {20};

        int[] to = {50,10};

        ip.addFlight("magenta",from,to);

        ip.flightLocation("magenta");

        assertFalse(ip.ok());

    }

//FlightCamera

    @Test

    public void shouldgetFlightCameraBasicAchuri_Gil() {

        String color = "255,0,0";

        int[] from = {10, 10,10};

        int[] to = {20, 20,10};

        ip.addFlight(color,from,to);

        ip.photograph(color,30);

        ip.flightCamera(color);

        assertTrue(ip.ok());

    }

    @Test

    public void shouldnotGetFlightCameraOfInexistingPhotoAchuri_Gil() {

        String color = "255,0,0";

        int[] from = {10, 10,10};

        int[] to = {20, 20,20};

        int cameraAngle = 90;

        ip.addFlight(color,from,to);

        ip.flightCamera("red");

        assertFalse(ip.ok());

    }

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**

 * The test class testCiclo1.

 *

 * @author  (Felipe A. Martinez - Thomas Ramirez)

 * @version (1.0 - 10/sep/2023)

 */

public class testCiclo1 {

    @Test

    public void testConstructorSuccess() {

        // Prueba que pasará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        assertNotNull(iceepeecee);

        assertEquals(10, iceepeecee.length);

        assertEquals(10, iceepeecee.width);

    }

    @Test

    public void testConstructorFailure() {

        // Prueba que fallará

        Iceepeecee iceepeecee = new Iceepeecee(-10, -10);

        assertNull(iceepeecee);

    }

    

    @Test

    public void testAddIslandSuccess() {

        // Prueba que pasará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[][] coordinates = {{0, 0}, {0, 1}, {1, 1}, {1, 0}};

        iceepeecee.addIsland("Red", coordinates);

        assertEquals(1, iceepeecee.islands.size());

        assertEquals("Red", iceepeecee.islands.get(0).getColor());

    }

    @Test

    public void testAddIslandFailure() {

        // Prueba que fallará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[][] coordinates = {{0, 0}, {0, 1}, {1, 1}, {1, 0}};

        iceepeecee.addIsland("Red", coordinates);

        iceepeecee.addIsland("Red", coordinates);

        assertEquals(1, iceepeecee.islands.size());

    }

    

    @Test

    public void testAddFligthSuccess() {

        // Prueba que pasará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[] from = {0, 0, 10};

        int[] to = {1, 1, 10};

        iceepeecee.addFligth("Red", from, to);

        assertEquals(1, iceepeecee.fligths.size());

        assertEquals("Red", iceepeecee.fligths.get(0).getColor());

    }

    @Test

    public void testAddFligthFailure() {

        // Prueba que fallará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[] from = {0, 0, 10};

        int[] to = {1, 1, 10};

        iceepeecee.addFligth("Red", from, to);

        iceepeecee.addFligth("Red", from, to);

        assertEquals(1, iceepeecee.fligths.size());

    }

    

     @Test

    public void testPhotographSuccess() {

        // Prueba que pasará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[] from = {0, 0, 10};

        int[] to = {1, 1, 10};

        iceepeecee.addFligth("Red", from, to);

        iceepeecee.photograph("Red", 45);

        assertTrue(iceepeecee.ok);

    }

    @Test

    public void testPhotographFailure() {

        // Prueba que fallará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[] from = {0, 0, 10};

        int[] to = {1, 1, 10};

        iceepeecee.addFligth("Red", from, to);

        iceepeecee.photograph("Red", 90);

        assertFalse(iceepeecee.ok);

    }

    

    @Test

    public void testPhotographAllFlightsSuccess() {

        // Prueba que pasará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[] from = {0, 0, 10};

        int[] to = {1, 1, 10};

        iceepeecee.addFligth("Red", from, to);

        iceepeecee.addFligth("Blue", from, to);

        iceepeecee.photograph(45);

        assertTrue(iceepeecee.ok);

    }

    @Test

    public void testPhotographAllFlightsFailure() {

        // Prueba que fallará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[] from = {0, 0, 10};

        int[] to = {1, 1, 10};

        iceepeecee.addFligth("Red", from, to);

        iceepeecee.addFligth("Blue", from, to);

        iceepeecee.photograph(95);

        assertFalse(iceepeecee.ok);

    }

    

    @Test

    public void testDelIslandSuccess() {

        // Prueba que pasará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[][] coordinates = {{0, 0}, {0, 1}, {1, 1}, {1, 0}};

        iceepeecee.addIsland("Red", coordinates);

        iceepeecee.delIsland("Red");

        assertEquals(0, iceepeecee.islands.size());

    }

    @Test

    public void testDelIslandFailure() {

        // Prueba que fallará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[][] coordinates = {{0, 0}, {0, 1}, {1, 1}, {1, 0}};

        iceepeecee.addIsland("Red", coordinates);

        iceepeecee.delIsland("Blue");

        assertEquals(1, iceepeecee.islands.size());

    }

    

    @Test

    public void testDelFligthSuccess() {

        // Prueba que pasará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[] from = {0, 0, 10};

        int[] to = {1, 1, 10};

        iceepeecee.addFligth("Red", from, to);

        iceepeecee.delFligth("Red");

        assertEquals(0, iceepeecee.fligths.size());

        assertTrue(iceepeecee.ok);

    }

    @Test

    public void testDelFligthFailure() {

        // Prueba que fallará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[] from = {0, 0, 10};

        int[] to = {1, 1, 10};

        iceepeecee.addFligth("Red", from, to);

        iceepeecee.delFligth("Blue");

        assertEquals(1, iceepeecee.fligths.size());

        assertFalse(iceepeecee.ok);

    }

    

    @Test

    public void testIslandLocationSuccess() {

        // Prueba que pasará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[][] coordinates = {{0, 0}, {0, 1}, {1, 1}, {1, 0}};

        iceepeecee.addIsland("Red", coordinates);

        int[][] location = iceepeecee.islandLocation("Red");

        assertArrayEquals(coordinates, location);

        assertTrue(iceepeecee.ok);

    }

    @Test

    public void testIslandLocationFailure() {

        // Prueba que fallará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[][] coordinates = {{0, 0}, {0, 1}, {1, 1}, {1, 0}};

        iceepeecee.addIsland("Red", coordinates);

        int[][] location = iceepeecee.islandLocation("Blue");

        assertNull(location);

        assertFalse(iceepeecee.ok);

    }

    

    @Test

    public void testFligthLocationSuccess() {

        // Prueba que pasará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[] from = {0, 0, 10};

        int[] to = {1, 1, 10};

        iceepeecee.addFligth("Red", from, to);

        int[][] location = iceepeecee.fligthLocation("Red");

        assertArrayEquals(new int[][]{from, to}, location);

        assertTrue(iceepeecee.ok);

    }

    @Test

    public void testFligthLocationFailure() {

        // Prueba que fallará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[] from = {0, 0, 10};

        int[] to = {1, 1, 10};

        iceepeecee.addFligth("Red", from, to);

        int[][] location = iceepeecee.fligthLocation("Blue");

        assertNull(location);

        assertFalse(iceepeecee.ok);

    }

    

    @Test

    public void testFligthCameraSuccess() {

        // Prueba que pasará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[] from = {0, 0, 10};

        int[] to = {1, 1, 10};

        iceepeecee.addFligth("Red", from, to);

        int cameraAngle = iceepeecee.fligthCamera("Red");

        assertNotEquals(-1, cameraAngle);

    }

    @Test

    public void testFligthCameraFailure() {

        // Prueba que fallará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int cameraAngle = iceepeecee.fligthCamera("Blue");

        assertEquals(-1, cameraAngle);

    }

    

    @Test

    public void testMakeVisibleSuccess() {

        // Prueba que pasará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[][] coordinates = {{0, 0}, {0, 1}, {1, 1}, {1, 0}};

        iceepeecee.addIsland("Red", coordinates);

        int[] from = {0, 0, 10};

        int[] to = {1, 1, 10};

        iceepeecee.addFligth("Blue", from, to);

        iceepeecee.makeVisible();

        assertTrue(iceepeecee.ok);

    }

    @Test

    public void testMakeVisibleFailure() {

        // Prueba que fallará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        iceepeecee.makeVisible();

        assertFalse(iceepeecee.ok);

    }

    

    @Test

    public void testMakeInvisibleSuccess() {

        // Prueba que pasará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[][] coordinates = {{0, 0}, {0, 1}, {1, 1}, {1, 0}};

        iceepeecee.addIsland("Red", coordinates);

        int[] from = {0, 0, 10};

        int[] to = {1, 1, 10};

        iceepeecee.addFligth("Blue", from, to);

        iceepeecee.makeInvisible();

        assertTrue(iceepeecee.ok);

    }

    @Test

    public void testMakeInvisibleFailure() {

        // Prueba que fallará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        iceepeecee.makeInvisible();

        assertFalse(iceepeecee.ok);

    }

}

8.A.MARTINEZ - RAMIREZ

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**

 * The test class testCiclo1.

 *

 * @author  (Felipe A. Martinez - Thomas Ramirez)

 * @version (1.0 - 10/sep/2023)

 */

public class testCiclo1 {

    @Test

    public void testConstructorSuccess() {

        // Prueba que pasará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        assertNotNull(iceepeecee);

        assertEquals(10, iceepeecee.length);

        assertEquals(10, iceepeecee.width);

    }

    @Test

    public void testConstructorFailure() {

        // Prueba que fallará

        Iceepeecee iceepeecee = new Iceepeecee(-10, -10);

        assertNull(iceepeecee);

    }

    

    @Test

    public void testAddIslandSuccess() {

        // Prueba que pasará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[][] coordinates = {{0, 0}, {0, 1}, {1, 1}, {1, 0}};

        iceepeecee.addIsland("Red", coordinates);

        assertEquals(1, iceepeecee.islands.size());

        assertEquals("Red", iceepeecee.islands.get(0).getColor());

    }

    @Test

    public void testAddIslandFailure() {

        // Prueba que fallará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[][] coordinates = {{0, 0}, {0, 1}, {1, 1}, {1, 0}};

        iceepeecee.addIsland("Red", coordinates);

        iceepeecee.addIsland("Red", coordinates);

        assertEquals(1, iceepeecee.islands.size());

    }

    

    @Test

    public void testAddFligthSuccess() {

        // Prueba que pasará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[] from = {0, 0, 10};

        int[] to = {1, 1, 10};

        iceepeecee.addFligth("Red", from, to);

        assertEquals(1, iceepeecee.fligths.size());

        assertEquals("Red", iceepeecee.fligths.get(0).getColor());

    }

    @Test

    public void testAddFligthFailure() {

        // Prueba que fallará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[] from = {0, 0, 10};

        int[] to = {1, 1, 10};

        iceepeecee.addFligth("Red", from, to);

        iceepeecee.addFligth("Red", from, to);

        assertEquals(1, iceepeecee.fligths.size());

    }

    

     @Test

    public void testPhotographSuccess() {

        // Prueba que pasará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[] from = {0, 0, 10};

        int[] to = {1, 1, 10};

        iceepeecee.addFligth("Red", from, to);

        iceepeecee.photograph("Red", 45);

        assertTrue(iceepeecee.ok);

    }

    @Test

    public void testPhotographFailure() {

        // Prueba que fallará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[] from = {0, 0, 10};

        int[] to = {1, 1, 10};

        iceepeecee.addFligth("Red", from, to);

        iceepeecee.photograph("Red", 90);

        assertFalse(iceepeecee.ok);

    }

    

    @Test

    public void testPhotographAllFlightsSuccess() {

        // Prueba que pasará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[] from = {0, 0, 10};

        int[] to = {1, 1, 10};

        iceepeecee.addFligth("Red", from, to);

        iceepeecee.addFligth("Blue", from, to);

        iceepeecee.photograph(45);

        assertTrue(iceepeecee.ok);

    }

    @Test

    public void testPhotographAllFlightsFailure() {

        // Prueba que fallará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[] from = {0, 0, 10};

        int[] to = {1, 1, 10};

        iceepeecee.addFligth("Red", from, to);

        iceepeecee.addFligth("Blue", from, to);

        iceepeecee.photograph(95);

        assertFalse(iceepeecee.ok);

    }

    

    @Test

    public void testDelIslandSuccess() {

        // Prueba que pasará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[][] coordinates = {{0, 0}, {0, 1}, {1, 1}, {1, 0}};

        iceepeecee.addIsland("Red", coordinates);

        iceepeecee.delIsland("Red");

        assertEquals(0, iceepeecee.islands.size());

    }

    @Test

    public void testDelIslandFailure() {

        // Prueba que fallará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[][] coordinates = {{0, 0}, {0, 1}, {1, 1}, {1, 0}};

        iceepeecee.addIsland("Red", coordinates);

        iceepeecee.delIsland("Blue");

        assertEquals(1, iceepeecee.islands.size());

    }

    

    @Test

    public void testDelFligthSuccess() {

        // Prueba que pasará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[] from = {0, 0, 10};

        int[] to = {1, 1, 10};

        iceepeecee.addFligth("Red", from, to);

        iceepeecee.delFligth("Red");

        assertEquals(0, iceepeecee.fligths.size());

        assertTrue(iceepeecee.ok);

    }

    @Test

    public void testDelFligthFailure() {

        // Prueba que fallará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[] from = {0, 0, 10};

        int[] to = {1, 1, 10};

        iceepeecee.addFligth("Red", from, to);

        iceepeecee.delFligth("Blue");

        assertEquals(1, iceepeecee.fligths.size());

        assertFalse(iceepeecee.ok);

    }

    

    @Test

    public void testIslandLocationSuccess() {

        // Prueba que pasará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[][] coordinates = {{0, 0}, {0, 1}, {1, 1}, {1, 0}};

        iceepeecee.addIsland("Red", coordinates);

        int[][] location = iceepeecee.islandLocation("Red");

        assertArrayEquals(coordinates, location);

        assertTrue(iceepeecee.ok);

    }

    @Test

    public void testIslandLocationFailure() {

        // Prueba que fallará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[][] coordinates = {{0, 0}, {0, 1}, {1, 1}, {1, 0}};

        iceepeecee.addIsland("Red", coordinates);

        int[][] location = iceepeecee.islandLocation("Blue");

        assertNull(location);

        assertFalse(iceepeecee.ok);

    }

    

    @Test

    public void testFligthLocationSuccess() {

        // Prueba que pasará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[] from = {0, 0, 10};

        int[] to = {1, 1, 10};

        iceepeecee.addFligth("Red", from, to);

        int[][] location = iceepeecee.fligthLocation("Red");

        assertArrayEquals(new int[][]{from, to}, location);

        assertTrue(iceepeecee.ok);

    }

    @Test

    public void testFligthLocationFailure() {

        // Prueba que fallará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[] from = {0, 0, 10};

        int[] to = {1, 1, 10};

        iceepeecee.addFligth("Red", from, to);

        int[][] location = iceepeecee.fligthLocation("Blue");

        assertNull(location);

        assertFalse(iceepeecee.ok);

    }

    

    @Test

    public void testFligthCameraSuccess() {

        // Prueba que pasará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[] from = {0, 0, 10};

        int[] to = {1, 1, 10};

        iceepeecee.addFligth("Red", from, to);

        int cameraAngle = iceepeecee.fligthCamera("Red");

        assertNotEquals(-1, cameraAngle);

    }

    @Test

    public void testFligthCameraFailure() {

        // Prueba que fallará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int cameraAngle = iceepeecee.fligthCamera("Blue");

        assertEquals(-1, cameraAngle);

    }

    

    @Test

    public void testMakeVisibleSuccess() {

        // Prueba que pasará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[][] coordinates = {{0, 0}, {0, 1}, {1, 1}, {1, 0}};

        iceepeecee.addIsland("Red", coordinates);

        int[] from = {0, 0, 10};

        int[] to = {1, 1, 10};

        iceepeecee.addFligth("Blue", from, to);

        iceepeecee.makeVisible();

        assertTrue(iceepeecee.ok);

    }

    @Test

    public void testMakeVisibleFailure() {

        // Prueba que fallará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        iceepeecee.makeVisible();

        assertFalse(iceepeecee.ok);

    }

    

    @Test

    public void testMakeInvisibleSuccess() {

        // Prueba que pasará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[][] coordinates = {{0, 0}, {0, 1}, {1, 1}, {1, 0}};

        iceepeecee.addIsland("Red", coordinates);

        int[] from = {0, 0, 10};

        int[] to = {1, 1, 10};

        iceepeecee.addFligth("Blue", from, to);

        iceepeecee.makeInvisible();

        assertTrue(iceepeecee.ok);

    }

    @Test

    public void testMakeInvisibleFailure() {

        // Prueba que fallará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        iceepeecee.makeInvisible();

        assertFalse(iceepeecee.ok);

    }

}
}
