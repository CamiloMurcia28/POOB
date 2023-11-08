package test;
import iceepeecee.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class IceepeeceeCC2Test.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class IceepeeceeCC2Test
{
    private Iceepeecee iceepeecee;
    
    @BeforeEach
    public void setUp() {
        iceepeecee = new Iceepeecee(500,500); // Crea una instancia de Iceepeecee para las pruebas
    }
    
    
//FERNANDEZ - TORRES

    @Before

    public void setUp()

    {

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

    public void shouldFTObservedIsland(){

        String[] resultado = {"red"} ;

        i.photograph(48);

        boolean s = Arrays.equals(resultado, i.observedIsland());

        assertTrue(s);

        

    }

    @Test

    public void shouldFTNotObservedIsland(){

        String[] resultado = {"red","green"} ;

        i.photograph(48);

        boolean s = Arrays.equals(resultado, i.observedIsland());

        assertFalse(s);

    }



 @Test

    public void ShouldPhotograph(){

        float f = 48;

         i.photograph(f);

         assertTrue(i.ok());

    }

    

    @Test

    public void ShouldNotPhotograph(){

        float f = 181;

        i.photograph(f);

        assertFalse(i.ok());

    }

    

    @Test

    public void ShouldReturnObserverdIslands(){

        i.photograph(48);

        assertTrue(i.observedIsland().length == 0);

        assertTrue(i.ok());

    }

    

     @Test

    public void ShouldReturnUselessFligths(){

        i.photograph(48);

        assertTrue(i.uselessFligth().length == 2);

        assertTrue(i.ok());

    }


//Achuri-Gil

public void ShouldObservedIslands(){        

        int[][]vertix={{20,30},{50,50},{10,50}};

        int[][]vertix2={{15,45},{45,45},{25,35}};

        int[]from={0,30,20}; 

        int[]to={78,70,5};

        ip.addIsland("red",vertix);

        ip.addIsland("yellow",vertix2);

        ip.addFlight("black",from,to);

        ip.photograph(60);

        ip.observedIslands();

        assertTrue(ip.ok());  

    }

    

    public void ShouldObservedIslands2(){

        int[][]vertix={{20,30},{50,50},{10,50}};

        int[][]vertix2={{15,45},{45,45},{25,35}};

        int[][]vertix3={{15,55},{75,45},{15,35}};

        ip.addIsland("red",vertix);

        ip.addIsland("green",vertix2);

        ip.addIsland("orange",vertix3);

        int[]from={0,30,89}; 

        int[]to={45,0,5};

        int[]from2={55,50,20};

        int[]to2={40,68,10};

        int[]from3={100,50,9};

        int[]to3={16,180,45};

        ip.addFlight("yellow",from,to);

        ip.addFlight("gray",from2,to2);

        ip.addFlight("purple",from3,to3);

        ip.observedIslands();

        assertTrue(ip.ok());

    }

    

    public void ShouldUselessFlights(){

        ip.uselessFlights();

        assertTrue(ip.ok());

    }

    

    public void ShouldUselessFlights2(){

        int[][]vertix={{46,50},{70,50},{71,20},{67,12}};

        ip.addIsland("blue",vertix);

        int[]from={18,100,26};

        int[]to={12,100,50};

        ip.addFlight("yellow",from,to);

        ip.uselessFlights();

        assertTrue(ip.ok());

    }

    

    public void ShouldUselessFlights3(){

        int[][]vertix ={{98,78},{50,50},{40,60}};

        int[][]vertix2={{15,45},{45,45},{25,35}};

        int[][]vertix3={{40,20},{60,10},{75,20},{60,30}};

        ip.addIsland("blue",vertix);

        ip.addIsland("yellow",vertix2);

        ip.addIsland("orange",vertix3);

        int[]from={40,38,70}; 

        int[]to={98,70,50};

        int[]from2={55,0,20};

        int[]to2={80,40,1};

        int[]from3={56,140,80};

        int[]to3={18,100,50};

        ip.addFlight("gray",from,to);

        ip.addFlight("pink",from2,to2);

        ip.addFlight("magenta",from3,to3);

        ip.uselessFlights();

        assertTrue(ip.ok());

    }

    

    @Test

    public void shouldgetUseless(){

        int[][] vertix = {{100,20},{80,40},{30,20}};

        ip.addIsland("red",vertix);

        int[] from = {80,40,50};

        int[] to = {30,30,20};

        

        ip.addFlight("blue",from,to);

        ip.photograph("blue",30);

        ip.makeVisible();

        assertTrue(ip.uselessFlights().length > 0);

        

    }

    

    @Test

    public void shouldNotPhotographWithInvalidAngle() {

        // Crear una instancia de Iceepeeceulator

        ip = new Iceepeece (100, 100);



        // Definir valores de prueba

        String color = "blue";

        int[] from = {10, 10, 10};

        int[] to = {20, 20, 20};



        // Agregar un vuelo ficticio

        ip.addFlight(color, from, to);



        // Definir un ángulo no válido (negativo) para la fotografía

        float invalidTeta = -1.0f;



        // Intentar fotografiar el vuelo con el ángulo no válido

        ip.photograph(color, invalidTeta);



        // Verificar que la zona fotografiada sea nula

        PhotographedZone photo = ip.getPhotographedZoneForFlight(color);

        assertNull(photo);

    }





//Castillo - Suarez


@Test

    public void testShouldAddIsland() {

        simulator = new Iceepeece(500,500);

        String color = "255,0,0";

        int[][] polygon = {{10, 10}, {20, 10}, {15, 20}};

        simulator.addIsland(color, polygon);

        assertTrue(simulator.ok());

    }

    

    @Test

    public void testShouldAddMultsimulatorle() {

        // Ejecutar el método Multsimulatorle con matrices ficticias

        int[][][] islands = {{{20,30},{50,50},{10,50}},{{40,20},{60,10},{75,20},{60,30}},{{45,60},{55,55},{60,60},{55,65}}};

        int[][][] flights = {{{0,30,20},{78,70,5}},{{55,0,20},{70,60,10}}     };

        //Iceepeece simulatorm = new Iceepeece(islands,flights);

        

        // Verificar que se hayan agregado islas y vuelos aleatorios

        assertTrue(simulatorm.islands().length > 0);

        assertTrue(simulatorm.flights().length > 0);

    }

    

    @Test

    public void testShouldNotAddIslandWithSameColor() {

        int[][] validPolygon1 = {{50, 50}, {70, 50}, {55, 55}}; // Polígono válido

        int[][] validPolygon2 = {{20, 20}, {30, 20}, {25, 25}}; // Polígono válido



        simulator.addIsland("blue", validPolygon1);

        

        // Verifica que la isla se haya agregado correctamente

        assertTrue(simulator.ok());



        // Intenta agregar otra isla con el mismo color, lo cual no debería permitirse

        simulator.addIsland("blue", validPolygon2);



        // Verifica que la segunda isla no se haya agregado debido al mismo color

        assertTrue(simulator.ok());

        

        // Verifica que solo haya una isla en el simulador

        assertEquals(1, simulator.getIslandCount());

    }



    @Test

    public void testShouldNotAddIslandWithInvalidCoordinates() {

        int[][] invalidPolygon = {{0, 0}, {10, 0}}; // Polígono con solo dos puntos (inválido)

        simulator.addIsland("red", invalidPolygon);

        

        // Verifica que la isla no se haya agregado debido a las coordenadas inválidas

        assertTrue(simulator.ok());

        

        // Verifica que no haya islas en el simulador

        assertEquals(0, simulator.getIslandCount());

    }

    

    @Test

    public void testShouldNotAddIslandOverlapping() {

        int[][] island1 = { { 0, 0 }, { 0, 10 }, { 10, 10 }, { 10, 0 } };

        simulator.addIsland("red", island1);

        int[][] island2 = { { 5, 5 }, { 5, 15 }, { 15, 15 }, { 15, 5 } };

        simulator.addIsland("green", island2);

        



        assertTrue(simulator.ok());

    }



    @Test

    public void testShouldDelIsland() {

        Iceepeece simulator = new Iceepeece(100, 100);

        String color1 = "255,0,0";

        int[][] polygon1 = {{10, 10}, {20, 10}, {15, 20}};

        String color2 = "0,0,255";

        int[][] polygon2 = {{30, 30}, {40, 30}, {35, 40}};



        simulator.addIsland(color1, polygon1);

        simulator.addIsland(color2, polygon2);

        

        assertTrue(simulator.ok());

        

        simulator.delIsland(color1);

        simulator.delIsland(color2);

        

        assertTrue(simulator.ok());

        

    }

    

    @Test

    public void testShouldNotDelIsland() {

        

        // Agregar una isla al simulador

        int[][] vertix = {{200, 200}, {200, 300}, {180, 180}};

        simulator.addIsland("green", vertix);



        // Verificar que 'ok' sea true antes de intentar eliminar la isla

        assertTrue(simulator.ok());



        // Intentar eliminar una isla que no existe

        simulator.delIsland("white");



        // Verificar que 'ok' ahora sea false después de intentar eliminar la isla

        assertTrue(simulator.ok());

    }

    

     @Test

    public void testShouldReturnIslandLocation() {

        String color1 = "255,0,0";

        int[][] expectedLocation1 = {{10, 10}, {20, 10}, {15, 20}};

        simulator.addIsland(color1, expectedLocation1);

        int[][] actualLocation1 = simulator.islandLocation(color1);

        assertArrayEquals(expectedLocation1, actualLocation1);

    }

    

    

    @Test

    public void testShouldNotReturnIslandLocation() {

        // Crea una instancia de Iceepeece

        Iceepeece simulator = new Iceepeece(100, 100);



        // Intenta obtener la ubicación de un vuelo que no existe

        int[][] location = simulator.islandLocation("NonExistentColor");



        // Asegura que la ubicación sea nula (no se encontró el vuelo)

        assertNull(location);



    }



     @Test

    public void testShouldAddFlight(){

        int[] from = {20,20};

        int[] to = {50,10};

        simulator.addFlight("magenta",from,to,90);

        assertTrue(simulator.ok());

    }

    

    @Test

    public void ShouldNotAddFlightWithSameColor() {

        

        int[] from1 = {10,10};

        int[] to1 = {20, 20};

        

        simulator.addFlight("255,0,0",from1,to1,90); 

        

        int[] from2 = {40,40};

        int[] to2 = {30, 30};

        

        simulator.addFlight("255,0,0", from1, to1,90);



        // Get the list of flights

        String[] flightColors = simulator.flights();



        // Assert that there is only one flight with the "255,0,0" color

        assertEquals(1, flightColors.length);

        assertEquals("255,0,0", flightColors[0]);

        assertTrue(simulator.ok());

        

    



    }

       

    @Test 

    public void ShouldnotAddFlightWithInvalidCoordinates(){

        int[] from = {20,20};

        int[] to = {10,10};

        simulator.addFlight("magenta",from,to,90);

        assertTrue(simulator.ok());

    }

    

    @Test

    public void ShouldDelFlight() {

        Iceepeece simulator = new Iceepeece(100, 100);

        String color1 = "255,0,0";

        int[] from1 = {10, 10};

        int[] to1 = {20, 20};

        

        simulator.addFlight(color1, from1, to1,90);

       

        assertTrue(simulator.ok());





        simulator.delFlight("255,0,0");

        

        assert(simulator.ok());

        

    }

    

    @Test

    public void ShouldNotDelFlight() {

        Iceepeece simulator = new Iceepeece(100, 100);

        simulator.delFlight("255,0,0");

        assertTrue(simulator.ok());

        

    }

    

    @Test

    public void testShouldReturnFlightLocation() {

        Iceepeece simulator = new Iceepeece(100, 100);

        String color1 = "255,0,0";

        int[] from1 = {10, 10};

        int[] to1 = {20, 20};



        simulator.addFlight(color1, from1, to1,90);

 

        int[][] expectedLocation1 = {from1, to1};

        int[][] actualLocation1 = simulator.flightLocation(color1);



        assertArrayEquals(expectedLocation1, actualLocation1);

    

       

       

    }

    

    @Test

    public void testShouldNotReturnFlightLocation() {

        // Crea una instancia de Iceepeece

        Iceepeece simulator = new Iceepeece(100, 100);



        // Intenta obtener la ubicación de un vuelo que no existe

        int[][] location = simulator.flightLocation("NonExistentColor");



        // Asegura que la ubicación sea nula (no se encontró el vuelo)

        assertNull(location);



    }

    

    @Test

    public void testShouldReturnFlightCamera() {

        Iceepeece simulator = new Iceepeece(100, 100);

        int[] from = {20,20,0};

        int[] to = {50,10,20};

        

        simulator.addFlight("magenta",from,to,90);



        simulator.photograph("magenta",90);

        

        float angle = simulator.flightCamera("magenta");

       

        assertEquals(angle,90);



    }

    

    @Test

    public void testShouldNotReturnFlightCamera() {

        // Intentar obtener el ángulo de la cámara de un vuelo que no existe

        float angle = simulator.flightCamera("VueloInexistente");



        // Verificar que el ángulo obtenido sea igual a -1 (ya que el vuelo no existe)

        assertEquals(-1.0f, angle);

        assertTrue(simulator.ok());



    }

    

    

    @Test

    public void testShouldReturnIslands() {

        Iceepeece simulator = new Iceepeece(100, 100);



        // Agregar islas

        simulator.addIsland("red", new int[][]{{20, 30}, {50, 50}, {10, 50}});

        simulator.addIsland("green", new int[][]{{40, 20}, {60, 10}, {75, 20}, {60, 30}});



        String[] expectedColors = {"red", "green"};

        String[] actualColors = simulator.islands();



        assertArrayEquals(expectedColors, actualColors);

    }



    @Test

    public void testShouldNotReturnIslands() {

        Iceepeece simulator = new Iceepeece(100, 100);



        // No agregar islas



        String[] expectedColors = {};

        String[] actualColors = simulator.islands();



        assertArrayEquals(expectedColors, actualColors);

    }

    

    @Test

    public void testShouldReturnFlights() {

        Iceepeece simulator = new Iceepeece(100, 100);



        // Agregar vuelos

        simulator.addFlight("blue", new int[]{0, 30, 20}, new int[]{78, 70, 5},90);

        simulator.addFlight("red", new int[]{55, 0, 20}, new int[]{70, 60, 10},90);



        String[] expectedColors = {"blue", "red"};

        String[] actualColors = simulator.flights();



        assertArrayEquals(expectedColors, actualColors);

    }



    @Test

    public void testShouldNotReturnFlights() {

        Iceepeece simulator = new Iceepeece(100, 100);



        // No agregar vuelos



        String[] expectedColors = {};

        String[] actualColors = simulator.flights();



        assertArrayEquals(expectedColors, actualColors);

    }

    

     @Test

    public void testShouldReturnObservedIslands() {

        Iceepeece simulator = new Iceepeece(500,500);



        // Agregar islas y zonas fotografiadas

        simulator.addIsland("green", new int[][] {{20,30},{50,50},{10,50}});

        simulator.addIsland("red", new int[][]{{45,60},{55,55},{60,60},{55,65}});

        

        simulator.addFlight("blue",new int[] {0,30,20},new int[]{78,70,5},90);

        simulator.addFlight("magenta",new int[] {55,0,20},new int[]{70,60,10},90);

        

        simulator.photograph("blue",120);

        simulator.photograph("magenta",120);



        String[] expectedColors = {"green", "red"};

        String[] actualColors = simulator.observedIslands();



        assertArrayEquals(expectedColors, actualColors);

    }



    @Test

    public void testShouldNotReturnObservedIslands() {

        Iceepeece simulator = new Iceepeece(100, 100);



        // Agregar islas, pero no zonas fotografiadas

        simulator.addIsland("green", new int[][]{{20, 30}, {50, 50}, {10, 50}});

        simulator.addIsland("red", new int[][]{{40, 20}, {60, 10}, {75, 20}, {60, 30}});



        String[] expectedColors = {};

        String[] actualColors = simulator.observedIslands();



        assertArrayEquals(expectedColors, actualColors);

    }



    @Test

    public void testPhotograph() {

        Iceepeece simulator = new Iceepeece(100, 100);



        // Agregar un vuelo ficticio

        String color = "255,0,0";

        int[] from = {10, 10,10};

        int[] to = {20, 20,20};



        simulator.addFlight(color, from, to,90);



        // Fotografiar el vuelo

        int teta = 60;

        simulator.photograph(color, teta);



        // Verificar la zona fotografiada

        

        assertTrue(simulator.ok());

        

        }  

        

    

     @Test

    public void shouldTestPhotograph() {

        // Crear una instancia de Iceepeece

        Iceepeece simulator = new Iceepeece(100, 100);



        // Definir valores de prueba

        String color = "red";

        int[] from = {10, 10, 10};

        int[] to = {20, 20, 20};

        int cameraAngle = 90;



        // Agregar un vuelo ficticio

        simulator.addFlight(color, from, to,90);



        // Definir el ángulo de la fotografía

        float teta = 3.14159f;



        // Fotografiar el vuelo

        simulator.photograph(color, teta);

        

        assertTrue(simulator.ok());

    }

    

    @Test

    public void shouldNotPhotographWithInvalidAngle() {

        // Crear una instancia de Iceepeece

        Iceepeece simulator = new Iceepeece(100, 100);



        // Definir valores de prueba

        String color = "blue";

        int[] from = {10, 10, 10};

        int[] to = {20, 20, 20};



        // Agregar un vuelo ficticio

        simulator.addFlight(color, from, to,90);



        // Definir un ángulo no válido (negativo) para la fotografía

        float invalidTeta = -1.0f;



        // Intentar fotografiar el vuelo con el ángulo no válido

        simulator.photograph(color, invalidTeta);



        assertTrue(simulator.ok());

    }

    

    @Test

    public void shouldnotPhotograph(){

        int[]from={0,20,40}; 

        int[]to={70,80,50};

        String color="blue";

        simulator.photograph(color,50);

        assertTrue(simulator.ok());

    }

    

    @Test

    public void shouldgetUseless(){

        int[][] vertix = {{100,20},{80,40},{30,20}};

        simulator.addIsland("red",vertix);

        int[] from = {80,40,50};

        int[] to = {30,30,20};

        

        simulator.addFlight("blue",from,to,90);

        simulator.photograph("blue",30);

        assertTrue(simulator.uselessFlight().length > 0);

        

    }

    

    @Test

    public void testShouldNotReturnUselessFlights() {

        // Crear una instancia de Iceepeece

        Iceepeece simulator = new Iceepeece(100, 100);



        // Agregar islas y vuelos

        simulator.addIsland("red", new int[][]{{300, 300}, {450, 450}, {190, 350}});

        simulator.addIsland("green", new int[][]{{40, 20}, {60, 10}, {75, 20}, {60, 30}});

        simulator.addFlight("blue", new int[]{0, 30, 20}, new int[]{78, 70, 5},90);

        simulator.addFlight("magenta", new int[]{55, 0, 20}, new int[]{70, 60, 10},90);



        // Fotografiar todas las islas con los vuelos

        simulator.photograph("blue", 60);

        simulator.photograph("magenta", 60);



        // Obtener vuelos con fotografías inútiles

        String[] uselessFlights = simulator.uselessFlight();



        // Verificar que no se devuelvan vuelos inútiles

        assertEquals(0, uselessFlights.length);

        //se verifica que el método uselessFlight no devuelva vuelos inútiles, ya que todos los vuelos cubren las islas en este caso

    }

//Bernal-Hernandez


@Test

    public void shouldPhotographAllFlightsWithFloatTheta() {

        Iceepeecee map = new Iceepeecee(750, 750);

        int[] from1 = {50, 50, 100};

        int[] to1 = {100, 100, 100};

        int[] from2 = {200, 200, 100};

        int[] to2 = {250, 250, 100};

        String color1 = "blue";

        String color2 = "red";

        map.addFlight(color1, from1, to1);

        map.addFlight(color2, from2, to2);



        

        map.photograph(45.0f);



        String[] observedIslands = map.observedIslands();



        

        assertTrue(observedIslands.length > 0);

    }



    @Test

    public void shouldNotPhotographAnyFlightsWithFloatThetaWhenNoneExist() {

        Iceepeecee map = new Iceepeecee(750, 750);



        

        map.photograph(45.0f);



        String[] observedIslands = map.observedIslands();



        

        assertEquals(0, observedIslands.length);

    }

@Test

    public void shouldGiveFlights() {

        Iceepeecee map = new Iceepeecee(600,600);

        String[] vuelos = {"blue","red"};

        int[] from = {45,55};

        int[] to = {67,90};

        int[] from2 = {75,20};

        int[] to2 = {120,75};

        map.addFlight("red",from,to);

        map.addFlight("blue",from2,to2);

        assertArrayEquals(map.flights(),vuelos);

    }

@Test

    public void shouldNotReturnNonexistentFlights() {

    Iceepeecee map = new Iceepeecee(600, 600);

    int[] from1 = {50, 50, 100};

    int[] to1 = {100, 100, 100};

    int[] from2 = {200, 200, 100};

    int[] to2 = {250, 250, 100};

    map.addFlight("blue", from1, to1);

    map.addFlight("red", from2, to2);



    String[] expectedFlights = {"nonexistentFlightColor"};



    String[] actualFlights = map.flights();



    assertFalse(Arrays.stream(actualFlights).anyMatch(expectedFlights[0]::equals));

    }

@Test

    public void shouldReturnIslands() {

        Iceepeecee map = new Iceepeecee(600,600);

        int[][] polygon1 = {{100, 50}, {150, 80}, {200, 100}};

        int[][] polygon2 = {{300, 350}, {350, 380}, {400, 400}};

        map.addIsland("blue", polygon1);

        map.addIsland("red", polygon2);

        String[] expectedIslands = {"blue", "red"};

        assertArrayEquals(expectedIslands, map.islands());

    }



    @Test

    public void shouldNotReturnNonexistentIslands() {

        Iceepeecee map = new Iceepeecee(600,600);

        int[][] polygon = {{100, 50}, {150, 80}, {200, 100}};

        int[][] polygon2 = {{700, 50}, {150, 700}, {200, 900}};

        map.addIsland("blue", polygon);

        map.addIsland("red", polygon2);

        String[] expectedIslands = {"blue"};

        assertArrayEquals(expectedIslands, map.islands());

    }

    @Test

    public void shouldReturnObservedIsland() {

    Iceepeecee map = new Iceepeecee(600, 600);

    int[][] islandPolygon = {{100, 50}, {150, 80}, {200, 100}};

    int[] flightFrom = {75, 75, 200};

    int[] flightTo = {250, 250, 200};

    map.addIsland("blue", islandPolygon);

    Flight vuelo = new Flight(flightFrom, flightTo, "red");

    vuelo.showPhotograph(10); 

    String[] observedIslands = map.observedIslands();

    assertTrue(Arrays.asList(observedIslands).contains("blue"));

    }

    

    @Test

    public void shouldNotReturnObservedIsland() {

    Iceepeecee map = new Iceepeecee(600, 600);

    int[][] islandPolygon = {{100, 50}, {150, 80}, {200, 100}};

    int[] flightFrom = {75, 75, 200};

    int[] flightTo = {250, 250, 200};

    map.addIsland("blue", islandPolygon);

    

    // Crea un vuelo sin inicializar su zona de visión

    Flight vuelo = new Flight(flightFrom, flightTo, "red");

    String[] observedIslands = map.observedIslands();

    assertFalse(Arrays.asList(observedIslands).contains("blue"));

    }



//*Lesmes*//

@BeforeEach

    public void setUp() {

        int[][][] islands = {{{20,30},{50,50},{10,50}},{{40,20},{60,10},{75,20},{60,30}},{{45,60},{55,55},{60,60},{55,65}}};

        int[][][] flights = {{{0,30,20},{78,70,5}},{{55,0,20},{70,60,10}},{{100,130,20},{178,170,5}}};

        

        iceepeecee = new Iceepeecee(islands,flights);

    }



//Photograph(float teta)

    @Test

    public void accordingLPShouldPhotographFloat() {

        iceepeecee.photograph(60.4f);

        assertTrue(iceepeecee.ok());

    }

@Test

    public void accordingLPShouldPhotographFloat2() {

        iceepeecee.photograph(90.1f);

        assertFalse(iceepeecee.ok());

    }



//islands()

    @Test

    public void accordingLPShouldIslands() {

        iceepeecee.islands();

        assertTrue(iceepeecee.ok());

    }



//flights()

    @Test

    public void accordingLPShouldFlights() {

        iceepeecee.flights();

        assertTrue(iceepeecee.ok());

    }



//observedIslands()

    @Test

    public void accordingLPShouldObservedIslands(){

        iceepeecee.photograph(60);

        iceepeecee.makeVisible();

        String[] a = iceepeecee.observedIslands();

        assertEquals(3, a.length);

    }

    @Test

    public void accordingLPShouldNotObservedIslands(){

        iceepeecee.photograph(1);

        iceepeecee.makeVisible();

        String[] a = iceepeecee.observedIslands();

        assertEquals(0, a.length);

    }

//uselessFlights()

    @Test

    public void accordingLPShouldUselessFlights(){

        iceepeecee.photograph(60);

        iceepeecee.makeVisible();

        String[] a = iceepeecee.uselessFlights();

        assertEquals(1, a.length);

    }

    @Test

    public void accordingLPShouldNotObservedFlights(){

        iceepeecee.photograph(80);

        iceepeecee.makeVisible();

        String[] a = iceepeecee.observedIslands();

        assertEquals(5, a.length);

    }

//CASALLAS - MURCIA

//CICLO 2

@Test

    public void accordingCMShouldTakePhotosSuccessFloat(){

        int[]from={0,30,20}; 

        int[]to={78,70,5};

        int[]from1={55,0,20};

        int[]to1={70,60,10};

        String color="#FF0000";

        String color1="#FFFFFF";

        iceepeecee.addFlight(color,from,to);

        iceepeecee.addFlight(color1,from1,to1);

        iceepeecee.photograph(40.2f);

        assertTrue(iceepeecee.ok());

    }





@Test

    public void accordingCMShouldNotTakePhotosFlightsNotExistentFloat(){

        int[]from={0,30,20}; 

        int[]to={78,70,5};

        String color="#FF0000";

        iceepeecee.photograph(48.6f);

        assertFalse(iceepeecee.ok()); 

    }



@Test

    public void accordingCMShouldConsultIslands(){

        int[][]polygon={{20,30},{50,50},{10,50}};

        String color="#FFA500";

        iceepeecee.addIsland(color,polygon);

        iceepeecee.islands();

        assertTrue(iceepeecee.ok());

    }



@Test

    public void accordingCMShouldConsultNotExistentIslands(){

        iceepeecee.islands();

        assertTrue(iceepeecee.ok());

    }  



@Test

    public void accordingCMShouldConsultFlights(){

        int[]from={0,30,20}; 

        int[]to={78,70,5};

        String color="#0000FF";

        iceepeecee.addFlight(color,from,to);

        iceepeecee.flights();

        assertTrue(iceepeecee.ok());

    }



@Test

    public void accordingCMShouldConsultNotExistentFlights(){

        iceepeecee.flights();

        assertTrue(iceepeecee.ok());

    }



@Test

    public void accordingCMShouldConsultObservedIslandsIsNull(){

        iceepeecee.observedIslands();

        assertTrue(iceepeecee.ok());

    }



public void accordingCMShouldConsultObservedIslandsSuccess(){        

        int[][]polygon={{20,30},{50,50},{10,50}};

        String color="#FFA500";

        int[][]polygon1={{15,45},{45,45},{25,35}};

        String color1="#808080";

        int[]from={0,30,20}; 

        int[]to={78,70,5};

        String color3="#0000FF";

        iceepeecee.addIsland(color,polygon);

        iceepeecee.addIsland(color1,polygon1);

        iceepeecee.addFlight(color3,from,to);

        iceepeecee.photograph(60);

        iceepeecee.observedIslands();

        assertTrue(iceepeecee.ok());  

    }



public void accordingCMShouldConsultUselessFlightsIsNull(){

        iceepeecee.uselessFlights();

        assertTrue(iceepeecee.ok());

    }



public void accordingCMShouldConsultUselessFlightsSuccess(){

        int[][]polygon={{40,20},{60,10},{75,20},{60,30}};

        String color="#FFFFFF";

        iceepeecee.addIsland(color,polygon);

        int[]from={100,130,20};

        int[]to={178,170,5};

        String color1="#008000";

        iceepeecee.addFlight(color1,from,to);

        iceepeecee.uselessFlights();

        assertTrue(iceepeecee.ok());

    }

//Forero-Murcia

@Test

    public void accordingFMshouldCreateCase1WithNewConstructor() {

        Iceepeecee manage;

        manage = new Iceepeecee(new int[][][]{{{20,30},{50,50},{10,50}},{{40,20},{60,10},{75,20},{60,30}},

                                {{45,60},{55,55},{60,60},{55,65}}},new int[][][]{{{0,30,20},{78,70,5}},{{55,0,20},{70,60,10}}});

        assertEquals(true, manage.OK());

        manage.photograph(48.032);

        assertEquals(true, manage.OK());

        String[] res = manage.observedIslands();

        assertEquals(true, manage.OK());

        assertEquals(3,res.length);

        String[] res1 = manage.uselessFlights();

        assertEquals(true, manage.OK());

        assertEquals(0,res1.length);

        

    }

    @Test

    public void accordingFMShouldNotCreateAllIslandsWithNewConstructor() {

        Iceepeecee manage;

        manage = new Iceepeecee(new int[][][]{{{20,30,50},{50,50},{10,50}},{{40,20},{60,10},{75,20},{60,30}},

                                {{45,60},{55,55},{60,60},{55,65}}},new int[][][]{{{0,30,20},{78,70,5}},{{55,0,20},{70,60,10}}});

        assertEquals(true, manage.OK());

        String [] islas = manage.islands();

        assertEquals(2,islas.length);

    }

    

    @Test

    public void accordingFMshouldCanTakeAPhotoWithNotIntegerAngle() {

        Iceepeecee manage;

        manage = new Iceepeecee(new int[][][]{{{20,30},{50,50},{10,50}},{{40,20},{60,10},{75,20},{60,30}},

                                {{45,60},{55,55},{60,60},{55,65}}},new int[][][]{{{0,30,20},{78,70,5}},{{55,0,20},{70,60,10}}});

        assertEquals(true, manage.OK());

        manage.photograph(48.032);

        assertEquals(true, manage.OK());

        

    }

    @Test

    public void accordingFMshouldCanTakeAPhotoWithNotIntegerAngle2() {

        Iceepeecee manage;

        manage = new Iceepeecee(new int[][][]{{{20,30},{50,50},{10,50}},{{40,20},{60,10},{75,20},{60,30}},

                                {{45,60},{55,55},{60,60},{55,65}}},new int[][][]{{{0,30,20},{78,70,5}},{{55,0,20},{70,60,10}}});

        assertEquals(true, manage.OK());

        manage.photograph(48.031693036);

        assertEquals(true, manage.OK());

        

    }

    

    @Test

    public void accordingFMShouldCanGetTheIslandsAndTheFlightsColors(){

        Iceepeecee manage = new Iceepeecee((int)Math.pow(10,6),(int)Math.pow(10,6));

        manage.addIsland("Red",new int[][]{{20,30},{50,50},{10,50}});

        assertEquals(true, manage.OK());

        manage.addIsland("Blue",new int[][]{{40,20},{60,10},{75,20},{60,30}});

        assertEquals(true, manage.OK());

        manage.addIsland("Yellow",new int[][]{{45,60},{55,55},{60,60},{55,65}});

        assertEquals(true, manage.OK());

        manage.addFlight("Black",new int[]{0,30,20},new int[]{78,70,5});

        assertEquals(true, manage.OK());

        manage.addFlight("Blue",new int[]{55,0,20},new int[]{70,60,10});

        assertEquals(true, manage.OK());

        String[] islas = manage.islands();

        assertEquals(true, manage.OK());

        assertArrayEquals(new String[]{"Red","Blue","Yellow"},islas);

        String[] vuelos = manage.flights();

        assertEquals(true, manage.OK());

        assertArrayEquals(new String[]{"Blue","Black"},vuelos);

    }

    @Test

    public void accordingFMShouldCantGetAllTheIslandsAndTheFlightsColors(){

        Iceepeecee manage = new Iceepeecee((int)Math.pow(10,6),(int)Math.pow(10,6));

        manage.addIsland("Red",new int[][]{{20,30},{50,50},{10,50}});

        assertEquals(true, manage.OK());

        manage.addIsland("Blue",new int[][]{{40,20},{60,10},{75,20},{60,30}});

        assertEquals(true, manage.OK());

        manage.delIsland("Blue");

        assertEquals(true, manage.OK());

        manage.addIsland("Yellow",new int[][]{{45,60},{55,55},{60,60},{55,65}});

        assertEquals(true, manage.OK());

        manage.addFlight("Black",new int[]{0,30,20},new int[]{78,70,5});

        assertEquals(true, manage.OK());

        manage.addFlight("Blue",new int[]{55,0,20},new int[]{70,60,10});

        assertEquals(true, manage.OK());

        manage.delFlight("Blue");

        assertEquals(true, manage.OK());

        String[] islas = manage.islands();

        assertEquals(true, manage.OK());

        assertArrayEquals(new String[]{"Red","Yellow"},islas);

        String[] vuelos = manage.flights();

        assertEquals(true, manage.OK());

        assertArrayEquals(new String[]{"Black"},vuelos);

    }

    @Test

    public void accordingFMShouldObservAllIslands5(){

        Iceepeecee manage;

        int[][][] islands;

        int[][][] flights;

        islands = new int[][][] {{{-5,0}, {5,0}, {0,5}}};

        flights = new int[][][] {{{0,10,20}, {-10,0,10}}};

        manage = new Iceepeecee(islands,flights);

        manage.photograph(31.219698447);

        String[] res = manage.observedIslands();

        assertEquals(1,res.length);

        String[] res1 = manage.uselessFlights();

        assertEquals(0,res1.length);

    }

    

    @Test

    public void accordingFMShouldNotObservAllIslands(){

        Iceepeecee manage = new Iceepeecee((int)Math.pow(10,6),(int)Math.pow(10,6));

        manage.addIsland("Pale Turquoise",new int[][]{{20,30},{50,50},{10,50}});

        manage.addIsland("Blue",new int[][]{{40,20},{60,10},{75,20},{60,30}});

        manage.addIsland("Yellow",new int[][]{{45,60},{55,55},{60,60},{55,65}});

        manage.addFlight("Black",new int[]{0,30,20},new int[]{78,70,5});

        manage.addFlight("Blue",new int[]{55,0,20},new int[]{70,60,10});

        manage.photograph(48.031693035);

        String[] res = manage.observedIslands();

        assertEquals(2,res.length);

        String[] res1 = manage.uselessFlights();

        assertEquals(1,res1.length);

    }

    

    @Test

    public void accordingFMShouldNotHaveAnyUslessFlights(){

        Iceepeecee manage = new Iceepeecee((int)Math.pow(10,6),(int)Math.pow(10,6));

        manage.addIsland("Pale Turquoise",new int[][]{{20,30},{50,50},{10,50}});

        manage.addIsland("Blue",new int[][]{{40,20},{60,10},{75,20},{60,30}});

        manage.addIsland("Yellow",new int[][]{{45,60},{55,55},{60,60},{55,65}});

        manage.addFlight("Black",new int[]{0,30,20},new int[]{78,70,5});

        manage.addFlight("Blue",new int[]{55,0,20},new int[]{70,60,10});

        manage.photograph(50);

        String[] res = manage.observedIslands();

        assertEquals(3,res.length);

        String[] res1 = manage.uselessFlights();

        assertEquals(0,res1.length);

    }

    

    @Test

    public void accordingFMShouldHaveAllUslessFlights(){

        Iceepeecee manage = new Iceepeecee((int)Math.pow(10,6),(int)Math.pow(10,6));

        manage.addIsland("Pale Turquoise",new int[][]{{20,30},{50,50},{10,50}});

        manage.addIsland("Blue",new int[][]{{40,20},{60,10},{75,20},{60,30}});

        manage.addIsland("Yellow",new int[][]{{45,60},{55,55},{60,60},{55,65}});

        manage.addFlight("Black",new int[]{0,30,20},new int[]{78,70,5});

        manage.addFlight("Blue",new int[]{55,0,20},new int[]{70,60,10});

        manage.photograph(5);

        String[] res = manage.observedIslands();

        assertEquals(0,res.length);

        String[] res1 = manage.uselessFlights();

        assertEquals(2,res1.length);

    }



//Chicuazuque-Sierra    

    

    // Prueba para el primer escenario (input1) del problema F de la maratón.

    @Test

    public void testInput1FotografiaConAngulosNoEnterosParaUnVueloChicuazuqueSierra() {

Iceepeecee input1 = new Iceepeecee(100, 100);

        input1.addIsland("blue", new int[][]{{20, 30}, {50, 50}, {10, 50}});

        input1.addIsland("green", new int[][]{{40, 20}, {60, 10}, {75, 20}, {60, 30}});

        input1.addIsland("red", new int[][]{{45, 60}, {55, 55}, {60, 60}, {55, 65}});

        input1.addFlight("fligth1", new int[]{0, 30, 20}, new int[]{78, 70, 5});

        input1.addFlight("fligth2", new int[]{55, 0, 20}, new int[]{70, 60, 10});

        

// Tomar fotografías de los vuelos en el primer escenario (input1).

        input1.photograph("fligth1", 45.2f);

        input1.photograph("fligth2", 30.0f);

        input1.makeVisible();

        // Verificar que las fotografías se tomaron correctamente en el primer escenario (input1).

        assertTrue(input1.ok());

        

    }

    

    @Test

    public void testInput1FotografiaConAngulosNoEnterosChicuazuqueSierra() {

Iceepeecee input1 = new Iceepeecee(100, 100);

        input1.addIsland("blue", new int[][]{{20, 30}, {50, 50}, {10, 50}});

        input1.addIsland("green", new int[][]{{40, 20}, {60, 10}, {75, 20}, {60, 30}});

        input1.addIsland("red", new int[][]{{45, 60}, {55, 55}, {60, 60}, {55, 65}});

        input1.addFlight("fligth1", new int[]{0, 30, 20}, new int[]{78, 70, 5});

        input1.addFlight("fligth2", new int[]{55, 0, 20}, new int[]{70, 60, 10});

       

 // Tomar fotografías de los vuelos en el primer escenario (input1).

        input1.photograph(45.2f);

        input1.makeVisible();

        // Verificar que las fotografías se tomaron correctamente en el primer escenario (input1).

        assertTrue(input1.ok());

        

    }

    

    @Test

    public void testInput1IslandsChicuazuqueSierra() {

Iceepeecee input1 = new Iceepeecee(100, 100);

        input1.addIsland("blue", new int[][]{{20, 30}, {50, 50}, {10, 50}});

        input1.addIsland("green", new int[][]{{40, 20}, {60, 10}, {75, 20}, {60, 30}});

        input1.addIsland("red", new int[][]{{45, 60}, {55, 55}, {60, 60}, {55, 65}});

        input1.addFlight("fligth1", new int[]{0, 30, 20}, new int[]{78, 70, 5});

        input1.addFlight("fligth2", new int[]{55, 0, 20}, new int[]{70, 60, 10});

      

  String[] expectedIslands = {"blue", "green", "red"};

        String[] result = input1.islands();



        assertArrayEquals(expectedIslands, result);

    }



    @Test

    public void testInput1FlightsChicuazuqueSierra() {

Iceepeecee input1 = new Iceepeecee(100, 100);

        input1.addIsland("blue", new int[][]{{20, 30}, {50, 50}, {10, 50}});

        input1.addIsland("green", new int[][]{{40, 20}, {60, 10}, {75, 20}, {60, 30}});

        input1.addIsland("red", new int[][]{{45, 60}, {55, 55}, {60, 60}, {55, 65}});

        input1.addFlight("fligth1", new int[]{0, 30, 20}, new int[]{78, 70, 5});

        input1.addFlight("fligth2", new int[]{55, 0, 20}, new int[]{70, 60, 10});

        

String[] expectedFlights = {"fligth1", "fligth2"};

        String[] result = input1.flights();



        assertArrayEquals(expectedFlights, result);

    }



    @Test

    public void testIslasEnFotografiaEnInput1NoSalenTodasChicuazuqueSierra() {

Iceepeecee input1 = new Iceepeecee(100, 100);

        input1.addIsland("blue", new int[][]{{20, 30}, {50, 50}, {10, 50}});

        input1.addIsland("green", new int[][]{{40, 20}, {60, 10}, {75, 20}, {60, 30}});

        input1.addIsland("red", new int[][]{{45, 60}, {55, 55}, {60, 60}, {55, 65}});

        input1.addFlight("fligth1", new int[]{0, 30, 20}, new int[]{78, 70, 5});

        input1.addFlight("fligth2", new int[]{55, 0, 20}, new int[]{70, 60, 10});

      

  // Tomar una fotografía del vuelo "fligth1" en input1 que captura la isla "red".

        input1.photograph(10);

        input1.makeVisible();

        // Obtener la lista de islas observadas en la fotografía en input1.

        String [] observedIslands = input1.observedIslands();

        // Verificar que todas las islas ("red", "green", "blue") estén en la lista observada.

        System.out.println(Arrays.asList(observedIslands));

        assertFalse("Todas las islas deberían estar en la fotografía", 

                   Arrays.asList(observedIslands).contains("red") &&

                   Arrays.asList(observedIslands).contains("green") &&

                   Arrays.asList(observedIslands).contains("blue"));

        

    }

    

    @Test

    public void testInput1VuelosFotografiasInutilesChicuazuqueSierra() {

Iceepeecee input1 = new Iceepeecee(100, 100);

        input1.addIsland("blue", new int[][]{{20, 30}, {50, 50}, {10, 50}});

        input1.addIsland("green", new int[][]{{40, 20}, {60, 10}, {75, 20}, {60, 30}});

        input1.addIsland("red", new int[][]{{45, 60}, {55, 55}, {60, 60}, {55, 65}});

        input1.addFlight("fligth1", new int[]{0, 30, 20}, new int[]{78, 70, 5});

        input1.addFlight("fligth2", new int[]{55, 0, 20}, new int[]{70, 60, 10});

      

  // Tomar una fotografía del vuelo "fligth1" en input1 que captura la isla "red".

        input1.photograph(0);

        input1.makeVisible();

        // Obtener la lista de islas observadas en la fotografía en input1.

        String [] vuelosConFotosInutiles = input1.uselessFlights();

        // Verificar que todas las islas ("red", "green", "blue") estén en la lista observada.

        System.out.println(Arrays.asList(vuelosConFotosInutiles));

        assertTrue("Todos los vuelos no tienen fotografias Inutiles", 

                   Arrays.asList(vuelosConFotosInutiles).contains("fligth1") &&

                   Arrays.asList(vuelosConFotosInutiles).contains("fligth2"));

        

    }





/**

 * The test class IceepeeceeTest.

 *

 * @author  Milton Gutierrez - Jhon Sosa

 * @version 20/10/23

 */

public class IceepeeceeC2Test{

    private Iceepeecee iceepeecee;

    private Iceepeecee iceepeecee2;

    private Iceepeecee iceepeecee3;

    private Iceepeecee iceepeecee4;

    

    @BeforeEach

    public void setUp() {

        iceepeecee = new Iceepeecee(300, 300);

        int[][][] islands = {{{20,30},{50,50},{10,50}},{{40,20},{60,10},{75,20},{60,30}},{{45,60},{55,55},{60,60},{55,65}}};

        int[][][] flights = {{{0,30,20},{78,70,5}},{{55,0,20},{70,60,10}},{{100,130,20},{178,170,5}}};

        int[][][] islands2 = {};

        int[][][] flights2 = {};

        iceepeecee2 = new Iceepeecee(islands, flights);

        iceepeecee3 = new Iceepeecee(islands2, flights2);

    }

    

    @Test

    

    public void GSshouldCreateMapOfIceepeecee(){

        assertTrue(iceepeecee2.ok());

    }

    

    public void GSshouldnotCreateMapOfIceepeecee(){

        assertFalse(iceepeecee3.ok());

    }

    

    public void GSshouldtakePhoto2(){      

        int[] from = {5, 50, 10};

        int[] to = {50, 100, 10};

        String color = "red";

        iceepeecee.addFlight(color, from, to);

        assertTrue(iceepeecee.ok());    

        int[] from1 = {100, 100, 40};

        int[] to1 = {200, 200, 30};

        String color1 = "blue";

        iceepeecee.addFlight(color1, from1, to1);

        assertTrue(iceepeecee.ok());

        float teta = 50.5f;

        iceepeecee.photograph(teta);

        iceepeecee.makeInvisible();

        assertTrue(iceepeecee.ok());

    }

    

    @Test

    public void GSshouldNottakePhoto2(){       

        int[] from = {5, 50, 10};

        int[] to = {50, 100, 10};

        String color = "red";

        iceepeecee.addFlight(color, from, to); 

        assertTrue(iceepeecee.ok());

        float teta = -1.54f;

        iceepeecee.photograph(-1.54f); 

        assertFalse(iceepeecee.ok());       

        int[] from1 = {100, 100, 40};

        int[] to1 = {200, 200, 30};

        String color1 = "blue";

        iceepeecee.addFlight(color1, from1, to1);

        assertTrue(iceepeecee.ok());

        float teta1 = 99.4f;

        iceepeecee.photograph(teta1); 

        assertFalse(iceepeecee.ok());

        

    }

    

    @Test

    public void GSshouldReturnIslands(){

        assertTrue(iceepeecee2.ok());

        iceepeecee.islands();

        assertTrue(iceepeecee2.ok());

    }

    

    @Test

    public void GSshouldReturnEmptyIslands(){

        assertTrue(iceepeecee.ok());

        iceepeecee.islands();

        assertTrue(iceepeecee.ok());

    }

    

    @Test

    public void GSshouldReturnflights(){

        assertTrue(iceepeecee2.ok());

        iceepeecee.flights();

        assertTrue(iceepeecee2.ok());

    }

    

    @Test

    public void GSshouldReturnEmptyVuelos(){

        assertTrue(iceepeecee.ok());

        iceepeecee.flights();

        assertTrue(iceepeecee.ok());

    }

    

    @Test

    public void GSshouldReturnObservedIslands(){

        assertTrue(iceepeecee2.ok());

        iceepeecee2.photograph(50);

        iceepeecee2.makeInvisible();

        assertTrue(iceepeecee2.ok());

        String[] prueba =iceepeecee2.observedIslands();

        assertTrue(iceepeecee2.ok() && prueba.length == 3);

    }

    

    @Test

        public void GSshouldReturnEmptyObservedIslands(){

        assertTrue(iceepeecee2.ok());

        iceepeecee2.photograph(10);

        iceepeecee2.makeInvisible();

        assertTrue(iceepeecee2.ok());

        String[] prueba =iceepeecee2.observedIslands();

        assertTrue(iceepeecee2.ok() && prueba.length == 0);   

    }

    

    @Test

        public void GSshouldnotReturnObservedIslands(){

        String color1 = "red";

        int[][] isla1 = {{20,30},{50,50},{10,50}};

        assertTrue(iceepeecee.ok()); 

        iceepeecee.addIsland(color1, isla1);

        assertTrue(iceepeecee.ok()); 

        iceepeecee.observedIslands();

        assertFalse(iceepeecee.ok());

    }

    

    @Test

    public void GSshouldReturnUselessflights(){

        assertTrue(iceepeecee2.ok());

        iceepeecee2.photograph(50);

        iceepeecee2.makeInvisible();

        assertTrue(iceepeecee2.ok());

        String[] prueba = iceepeecee2.uselessFlights();

        assertTrue(iceepeecee2.ok() && prueba.length == 1);    

    }

    

    @Test

    public void GSshouldReturnEmptyUselessflights(){

        assertTrue(iceepeecee2.ok());

        iceepeecee2.photograph(50);

        iceepeecee2.makeInvisible();

        assertTrue(iceepeecee2.ok());

        String[] prueba = iceepeecee2.uselessFlights();

        assertTrue(iceepeecee2.ok() && prueba.length == 1);

    }

    

    @Test

    public void GSshouldnotReturnUselessflights(){

        String color1 = "red";

        String color2 = "blue";

        int[][] vuelo1 = {{0,30,20}, {78,70,5}};

        int[][] vuelo2 = {{70,60,10},{100,130,20}};

        iceepeecee.addFlight(color1,vuelo1[0], vuelo1[1]);

        assertTrue(iceepeecee.ok());

        iceepeecee.addFlight(color2,vuelo2[0], vuelo2[1]);

        assertTrue(iceepeecee.ok()); 

        iceepeecee.photograph(50);

        assertTrue(iceepeecee.ok());

        iceepeecee.uselessFlights();

        assertFalse(iceepeecee.ok());

        iceepeecee.makeInvisible();

    }

}

//Silva-Suarez: 

@Test

        public void shouldPhotographFloatValidAngle() {

        iceepeecee = new Iceepeecee(100, 100);

        int[] from = {10, 10, 0}; 

        int[] to = {20, 20,50};    

        iceepeecee.addFlight("red", from, to);



        assertTrue(iceepeecee.ok());  



        iceepeecee.photographFloat(45.5f); 

        assertTrue(iceepeecee.ok());  

    }



    @Test

    public void shouldNotPhotographFloatInvalidAngle() {

        iceepeecee = new Iceepeecee(100, 100);

        int[] from = {10, 10, 0}; 

        int[] to = {20, 20,50};

        iceepeecee.addFlight("red", from, to);



        assertTrue(iceepeecee.ok());



        iceepeecee.photographFloat(95.7f); 

        assertFalse(iceepeecee.ok()); 

    }   

    

    @Test

    public void shouldObservedIslandsWithPhotographedFlight() {



        iceepeecee = new Iceepeecee(100, 100);

        int[] from = {100, 100, 10}; 

        int[] to = {20, 20,50};

        iceepeecee.addFlight("red", from, to);



        int[][] vertices = {{50, 50}, {60, 60}, {90, 90}};

        iceepeecee.addIsland("blue", vertices);



        iceepeecee.photograph("red", 50);



        String[] observedIslands = iceepeecee.observedIslands();



        assertNotNull(observedIslands);

        assertEquals(1, observedIslands.length); 

        assertEquals("blue", observedIslands[0]); 

    }   

    

    @Test

    public void shouldObservedIslandsNoPhotographedFlight() {

        iceepeecee = new Iceepeecee(100, 100);

        int[][] vertices = {{50, 50}, {60, 60}, {90, 90}};

        iceepeecee.addIsland("blue", vertices);



        String[] observedIslands = iceepeecee.observedIslands();



        assertNotNull(observedIslands);

        assertEquals(0, observedIslands.length); 

    }   

    

    @Test

    public void shouldUselessFlightsWithUselessFlight() {

        iceepeecee = new Iceepeecee(100, 100);

        int[] from = {10, 10, 0}; 

        int[] to = {20, 20, 5};

        iceepeecee.addFlight("red", from, to);

    

        int[][] vertices = {{5, 5}, {15, 5}, {10, 15}};

        iceepeecee.addIsland("blue", vertices);

        

        iceepeecee.photograph("red", 10);

        String[] uselessFlights = iceepeecee.uselessFlights();



        assertNotNull(uselessFlights);

        assertEquals(1, uselessFlights.length); 

        assertEquals("red", uselessFlights[0]);

    }       



    @Test

    public void shouldUselessFlightsWithUsefulFlight() {

        iceepeecee = new Iceepeecee(200, 200);

        int[] from = {10, 10, 20}; 

        int[] to = {100,100,40};

        iceepeecee.addFlight("red", from, to);



        int[][] vertices = {{50, 50}, {60, 60}, {90, 90}};

        iceepeecee.addIsland("blue", vertices);

        

        iceepeecee.photograph("red", 60);

        String[] uselessFlights = iceepeecee.uselessFlights();



        assertNotNull(uselessFlights);

        assertEquals(0, uselessFlights.length); 

    }

//Torres-Valencia

@Test

    public void testIslands(){

            iceepeecee.addIsland("991700", new int[][]{{20, 30}, {50, 50}, {10, 50}});

            iceepeecee.addIsland("979900", new int[][]{{40, 20}, {60, 10}, {75, 20}, {60, 30}});

            iceepeecee.addIsland("519900", new int[][]{{45, 60}, {55, 55}, {60, 60},{55, 65}});

            iceepeecee.addIsland("009909", new int[][]{{0,0}, {10,0}, {10, 10},{0,10}});

            iceepeecee.addIsland("009953", new int[][]{{8,3}, {8,1}, {11,2}});

            iceepeecee.addIsland("009964", new int[][]{{70, 10}, {60, 30}, {100, 20},{90, 40}});

            iceepeecee.addIsland("009986", new int[][]{{20, 10}, {30, 10}, {30, 20},{20, 20}});

            iceepeecee.addIsland("008D99", new int[][]{{20, 30}, {50, 30}, {10, 50}});

            iceepeecee.addIsland("007699", new int[][]{{20, 20}, {40, 40}, {10, 40}});

            iceepeecee.addIsland("005A99", new int[][]{{80, 10}, {60, 20}, {70, 40}});

            assertEquals(10,iceepeecee.islands().size());

        

    }

    @Test

    public void testFlights(){

            iceepeecee.addFlight("991700", new int[]{0,30,20},new int[]{78,70,5});

            iceepeecee.addFlight("979900", new int[]{1,40,20},new int[]{80,80,5});

            iceepeecee.addFlight("519900", new int[]{2,50,20},new int[]{82,90,5});

            iceepeecee.addFlight("009909", new int[]{3,60,20},new int[]{84,100,5});

            iceepeecee.addFlight("009953", new int[]{4,70,20},new int[]{86,110,5});

            iceepeecee.addFlight("009964", new int[]{5,80,20},new int[]{88,120,5});

            iceepeecee.addFlight("009986", new int[]{6,90,20},new int[]{90,130,5});

            iceepeecee.addFlight("008D99", new int[]{7,100,20},new int[]{92,140,5});

            iceepeecee.addFlight("007699", new int[]{8,110,20},new int[]{94,150,5});

            iceepeecee.addFlight("005A99", new int[]{9,120,20},new int[]{96,200,5});

            assertEquals(10,iceepeecee.flights().size());

    }



    @Test

   @Test

    public void testFlightCamera(){

            iceepeecee.addFlight("7DF032", new int[]{0,10,20},new int[]{78,10,5});

            iceepeecee.addFlight("32F066", new int[]{0,40,20},new int[]{0,80,5});

            iceepeecee.addFlight("32F0B6", new int[]{2,60,20},new int[]{82,30,5});

            iceepeecee.addFlight("32F0E4", new int[]{6,50,20},new int[]{10,100,5});

            iceepeecee.addFlight("32B4F0", new int[]{4,70,20},new int[]{15,30,5});

            iceepeecee.photograph(55.5f);

            iceepeecee.photograph("32F0E4",50);

            iceepeecee.flightCamera("32F0E4");

            assertEquals(true,iceepeecee.ok());

            iceepeecee.flightCamera("blue");

            assertEquals(false,iceepeecee.ok());

    }



    @Test

    public void testObservedIslands(){

            iceepeecee.addIsland("991700", new int[][]{{20, 30}, {50, 50}, {10, 50}});

            iceepeecee.addIsland("979900", new int[][]{{40, 20}, {60, 10}, {75, 20}, {60, 30}});

            iceepeecee.addIsland("519900", new int[][]{{45, 60}, {55, 55}, {60, 60},{55, 65}});

            iceepeecee.addIsland("009909", new int[][]{{0,0}, {10,0}, {10, 10},{0,10}});

            iceepeecee.addFlight("7DF032", new int[]{0,30,20},new int[]{78,70,5});

            iceepeecee.addFlight("32F066", new int[]{55,0,20},new int[]{70,60,10});

            iceepeecee.addFlight("32F0B6", new int[]{5,5,10},new int[]{15,5,10});

            iceepeecee.photograph(50);

            assertEquals(3,iceepeecee.observedIslands().size());

            iceepeecee.delIsland("991700");

            iceepeecee.delIsland("979900");

            iceepeecee.delIsland("519900");

            iceepeecee.delIsland("009909");

            assertEquals(0,iceepeecee.observedIslands().size());

        

    }

    @Test

    public void testUselessFlights(){

            iceepeecee.addIsland("009909", new int[][]{{0,0}, {10,0}, {10, 10},{0,10}});

            iceepeecee.addFlight("7DF032", new int[]{0,30,20},new int[]{78,70,5});

            iceepeecee.addFlight("32F066", new int[]{55,0,20},new int[]{70,60,10});

            iceepeecee.addFlight("32F0B6", new int[]{5,5,10},new int[]{15,5,10});

            iceepeecee.photograph(50);

            assertEquals(3,iceepeecee.uselessFlights().size());



            iceepeecee.addIsland("991700", new int[][]{{20, 30}, {50, 50}, {10, 50}});

            iceepeecee.addIsland("979900", new int[][]{{40, 20}, {60, 10}, {75, 20}, {60, 30}});

            iceepeecee.addIsland("519900", new int[][]{{45, 60}, {55, 55}, {60, 60},{55, 65}});

            assertEquals(1,iceepeecee.uselessFlights().size());

            iceepeecee.delFlight("7DF032");

            iceepeecee.delFlight("32F066");

            iceepeecee.delFlight("32F0B6");

            assertEquals(0,iceepeecee.uselessFlights().size());

    }

// DIAZ - MONROY

@BeforeEach

    public void setUp() {

        color1 = "AA";

        color2 = "DD";

        color3 = "GG";

        color4 = "II";

        islands1 = new int[][][]{{{20,30},{50,50},{10,50}},

                                 {{40,20},{60,10},{75,20},{60,30}},

                                 {{45,60},{55,55},{60,60},{55,65}}};      

        flights1 = new int[][][]{{{0,30,20}, {78,70,5}},

                                 {{55,0,20}, {70,60,10}}};

        polygon1 = new int[][]{{100,110,200},{390,410,395}};

        polygon2 = new int[][]{{590,595,610},{160,180,170}};

        polygon3 = new int[][]{{600,610,700},{390,410,395}};

        polygon4 = new int[][]{{860,950,1000},{450,500,570}};

        to1 = new int[]{20,400,20};

        from1 = new int[]{300,400,60};

        to2 = new int[]{600,100,60};

        from2 = new int[] {600,300,90};

        to3 = new int[]{300,400,20};

        from3 = new int[]{650,700,60};

        to4 = new int[]{20,400,60};

        from4 = new int[] {600,300,90};

        tetaI1 = 20;

        tetaI2 = 60;

        tetaF1 = 47.264f;

    }

    @Test

    public void accordingDMShouldCreateProblemF(){

        Iceepeecee icpc = new Iceepeecee(islands1, flights1);

        icpc.makeVisible();

    }

    @Test

    public void accordingDMShouldNotCreateProblemFislandsOutOfCanvas(){

        Iceepeecee icpc = new Iceepeecee(islands1, flights1);

        icpc.addIsland(color1,new int[][]{{10,60,1500},{10,-50,-70}});

        assertFalse(icpc.ok());

    }

@Test

    public void accordingDMShouldPhotographAllFlightsFloat() {

        Iceepeecee icpc = new Iceepeecee(islands1, flights1);

        icpc.photograph(tetaF1);

        assertTrue(icpc.ok());

        icpc.makeVisible();

    }

    @Test

    public void accordingDMShouldNotPhotographAllFlightsFloat1() {

        Iceepeecee icpc = new Iceepeecee(2000, 2000);

        icpc.addIsland(color1, polygon1);

        icpc.addIsland(color2, polygon2);

        icpc.addIsland(color3, polygon3);

        icpc.photograph(tetaF1);

        assertFalse(icpc.ok());

        icpc.makeVisible();

    }

@Test

    public void accordingDMShouldConsultIslands(){

        String color1= "ZA";

        String color2= "AZ";

        String color3= "MZ";

        String color4= "MJ";

        String color5= "JH";

        Iceepeecee icpc = new Iceepeecee(2000, 2000);

        int[][] polygon1 = {{1000,980,950},{20,60,40}};

        int[][] polygon2 = {{1010,980,1040,1060},{50,80,90,70}};

        int[][] polygon3 = {{1100,1200,1150},{30,50,60}};

        int[][] polygon4 = {{10,60,90,30},{20,10,50,70}};

        int[][] polygon5 = {{700,520,460},{500,603,300}};

        icpc.addIsland(color1, polygon1);

        icpc.addIsland(color2, polygon2);

        icpc.addIsland(color3, polygon3);

        icpc.addIsland(color4, polygon4);

        icpc.addIsland(color5, polygon5);

        String[] colors = {"ZA","AZ","MZ","MJ","JH"};

        assertArrayEquals(colors,icpc.islands());

    }

    @Test

    public void accordingDMShouldNotConsultIslands(){

        String color1= "ZA";

        String color2= "AZ";

        String color3= "MZ";

        String color4= "MJ";

        String color5= "JH";

        Iceepeecee icpc = new Iceepeecee(2000, 2000);

        int[][] polygon1 = {{1000,980,950},{20,60,40}};

        int[][] polygon2 = {{1010,980,1040,1060},{50,80,90,70}};

        int[][] polygon3 = {{1100,1200,1150},{30,50,60}};

        int[][] polygon4 = {{10,60,90,30},{20,10,50,70}};

        int[][] polygon5 = {{700,520,460},{500,603,300}};

        icpc.addIsland(color1, polygon1);

        icpc.addIsland(color2, polygon2);

        icpc.addIsland(color3, polygon3);

        icpc.addIsland(color4, polygon4);

        icpc.addIsland(color5, polygon5);

        String[] colors = {"ZA","AZ","MK","MJ","JH"};

        assertFalse(Arrays.equals(colors,icpc.islands()));

    }

    @Test

    public void accordingDMShouldConsultFlights(){

        String color1= "ZA";

        String color2= "AZ";

        String color3= "MZ";

        Iceepeecee icpc = new Iceepeecee(2000, 2000);

        int[] from1 = {300,500,30};

        int[] to1 = {720,200,60};

        int[] from2 = {360,380,20};

        int[] to2 = {90,60,120};

        int[] from3 = {300,700,40};

        int[] to3 = {500,300,100};

        icpc.addFlight(color1, from1, to1);

        icpc.addFlight(color2, from2, to2);

        icpc.addFlight(color3, from3, to3);

        String[] colors = {"ZA","AZ","MZ"};

        assertArrayEquals(colors,icpc.flights());

    }

    @Test

    public void accordingDMShouldNotConsultFlights(){

        String color1= "ZA";

        String color2= "AZ";

        String color3= "MZ";

        Iceepeecee icpc = new Iceepeecee(2000, 2000);

        int[] from1 = {300,500,30};

        int[] to1 = {720,200,60};

        int[] from2 = {360,380,20};

        int[] to2 = {90,60,120};

        int[] from3 = {300,700,40};

        int[] to3 = {500,300,100};

        icpc.addFlight(color1, from1, to1);

        icpc.addFlight(color2, from2, to2);

        icpc.addFlight(color3, from3, to3);

        String[] colors = {"ZP","AZ","MZ"};

        assertFalse(Arrays.equals(colors,icpc.flights()));

    }

@Test

    public void shouldConsultObservedIslands(){

        Iceepeecee icpc = new Iceepeecee(2000, 2000);

        icpc.addIsland(color1, polygon1);

        icpc.addIsland(color2, polygon2);

        icpc.addFlight(color3, from1, to1);

        icpc.addFlight(color4, from2, to2);

        icpc.photograph(color3, tetaI1);

        icpc.photograph(color4, tetaI1);

        String[] colors = {"AA","DD"};

        assertArrayEquals(colors,icpc.observedIslands());

    }

    @Test

    public void shouldNotConsultObservedIslands(){

        Iceepeecee icpc = new Iceepeecee(2000, 2000);

        icpc.addIsland(color1, polygon1);

        icpc.addIsland(color2, polygon2);

        icpc.addFlight(color3, from1, to1);

        icpc.addFlight(color4, from2, to2);

        icpc.photograph(color3, tetaI1);

        icpc.photograph(color4, tetaI1);

        String[] colors = {"AD","DD"};

        assertFalse(Arrays.equals(colors,icpc.observedIslands()));

    }

    @Test

    public void shouldConsultUselessFlights(){

        Iceepeecee icpc = new Iceepeecee(2000, 2000);

        icpc.addIsland(color1, polygon3);

        icpc.addIsland(color2, polygon4);

        icpc.addFlight(color3, from3, to3);

        icpc.addFlight(color4, from4, to4);

        icpc.photograph(color3, tetaI1);

        icpc.photograph(color4, tetaI1);

        String[] colors = {"GG","II"};

        assertArrayEquals(colors,icpc.uselessFlights());

    }

    @Test

    public void shouldNotConsultUselessFlights(){

        Iceepeecee icpc = new Iceepeecee(2000, 2000);

        icpc.addIsland(color1, polygon3);

        icpc.addIsland(color2, polygon4);

        icpc.addFlight(color3, from3, to3);

        icpc.addFlight(color4, from4, to4);

        icpc.photograph(color3, tetaI1);

        icpc.photograph(color4, tetaI1);

        String[] colors = {"GI","GI"};

        assertFalse(Arrays.equals(colors,icpc.uselessFlights()));

    }



// Mendivelso-Rodriguez

public class Testiceepeecee2

{   

    private iceepeecee Icep_2;

    /**

     * Default constructor for test class Testiceepeecee2

     */

    @Before

    public void setUp(){

         Icep_2= new iceepeecee (200,200);

    }

    



    @Test

    public void shouldgetLocationFlight(){

        int[] from = {20,20,20};

        int[] to = {50,10,10};

        Icep_2.addFlight("magenta",from,to,90);

        Icep_2.flightLocation("magenta");

        assertTrue(Icep_2.ok());

    }

    

  @Test 

    public void shouldNotLocationflight(){

    int[] from = {30};

    int [] to = {20,20};

    Icep_2.addFlight("blue",from,to,30);

    int[][] expectedResult = {{30,40},{20,20}};

    int[][] result = Icep_2.flightLocation("blue");

    assertFalse(Icep_2.ok());

    

    }



    @Test

    public void shouldgetLocationIsland(){

        int[][] vertices = {{100,100},{200,100},{150,200}};

        Icep_2.addIsland ("blue",vertices);

        int[][] expectedResult = {{100,100},{200,100},{150,200}};

        int[][] result = Icep_2.islandLocation("blue");

        assertTrue(Icep_2.ok());

    }

    



@Test 

    public void shouldNotLocationIsland(){

    int[][] vertices = {{100},{100,200},{200,300}};

    Icep_2.addIsland("blue",vertices);

    int[][] expectedResult = {{100,100},{100,200},{200,300}};

    int[][] result = Icep_2.islandLocation("blue");

    assertFalse(Icep_2.ok());

    }



    

    @Test

    public void testFlightLocation() {

        iceepeecee icep_2 = new iceepeecee(100, 100);

        String color1 = "228,32,7";

        int[] from1 = {10, 10};

        int[] to1 = {90, 90};

        int cameraAngle1 = 60;



        String color2 = "165,36,195";

        int[] from2 = {30, 30};

        int[] to2 = {40, 40};

        int cameraAngle2 = 60;



        icep_2.addFlight(color1, from1, to1, cameraAngle1);

        icep_2.addFlight(color2, from2, to2, cameraAngle2);



        int[][] location1 = icep_2.flightLocation(color1);

        int[][] location2 = icep_2.flightLocation(color2);

    }



    

    @Test

    public void testFlightCamera() {

        iceepeecee Icep_2 = new iceepeecee(100, 100);

        String color1 = "78,255,51"; // green

        int[] from1 = {100, 100};

        int[] to1 = {170, 170};

        int cameraAngle1 = 60;



        String color2 = "51,135,255"; // blue

        int[] from2 = {150, 150};

        int[] to2 = {180, 180};

        int cameraAngle2 = 60;



        Icep_2.addFlight(color1, from1, to1, cameraAngle1);

        Icep_2.addFlight(color2, from2, to2, cameraAngle2);



        int cameraAngleForColor1 = Icep_2.angleCamera(color1);

        int cameraAngleForColor2 = Icep_2.angleCamera(color2);

    }    

    

    

        /**

     * Prueba de unidad para verificar que el iceepeecee puede consultar las islas existentes 

     */

    @Test

    public void shouldGetAllIslands() {

        

        int[][] isla1={{100, 100}, {200, 100}, {150, 200}};

        Icep_2.addIsland("blue", isla1);

        int[][] isla2 = {{300, 300}, {400, 300}, {350, 400}};

        Icep_2.addIsland("red", isla2);

        Icep_2.islands();

        assertEquals(true,Icep_2.ok());

    }    

    

        @Test

    public void ShouldGetAllIslands_2(){

        int[][] vertix ={{29,70},{60,50},{90,50}};

        int[][]vertix2={{85,45},{95,45},{15,35}};

        int[][]vertix3={{47,20},{60,17},{75,28},{60,37}};

        Icep_2.addIsland("white",vertix);

        Icep_2.addIsland("red",vertix2);

        Icep_2.addIsland("orange",vertix3);

        Icep_2.islands();

        assertTrue(Icep_2.ok());

    }

    @Test

    public void shouldPhotograph3(){

        int[]from={0,20,40}; 

        int[]to={70,80,50};

        int[]from2={45,10,20};

        int[]to2={90,60,30};

        String color="orange";

        String color2="red";

        Icep_2.addFlight(color,from,to,90);

        Icep_2.addFlight(color2,from2,to2,60);

        Icep_2.photograph(30);

        assertTrue(Icep_2.ok());

    }

    

    @Test

    public void shouldPhotograph4(){

        int[]from={0,20,40}; 

        int[]to={70,80,50};

        int[]from2={45,10,20};

        int[]to2={90,60,30};

        String color="orange";

        String color2="red";

        Icep_2.addFlight(color,from,to,90);

        Icep_2.addFlight(color2,from2,to2,60);

        Icep_2.photograph(30);

        Icep_2.photograph(60);

        assertTrue(Icep_2.ok());

    }

    

    @Test

    public void shouldnotPhotograph(){

        int[]from={0,20,40}; 

        int[]to={70,80,50};

        String color="blue";

        Icep_2.photograph(color,50);

        assertFalse(Icep_2.ok());

    }

    

    @Test

    public void shouldnotPhotograph2(){

        int[]from={0,20,40}; 

        int[]to={70,80,50};

        String color="pink";

        Icep_2.addFlight(color,from,to,90);

        Icep_2.photograph(color,-50);

        assertFalse(Icep_2.ok());

    }

    

    @Test

    public void shouldnotPhotograph3(){

        int[]from={0,20,40}; 

        int[]to={70,80,50};

        int[]from2={45,10,20};

        int[]to2={90,60,30};

        String color="orange";

        String color2="red";

        Icep_2.addFlight(color,from,to,90);

        Icep_2.addFlight(color2,from2,to2,60);

        Icep_2.photograph(30);

        Icep_2.photograph(-60);

        assertFalse(Icep_2.ok());

    }

    

    @Test

    public void testConst() {

        int[][][] islans = {{{20,30},{50,50},{10,50}},{{40,20},{60,10},{75,20},{60,30}},{{45,60},{55,55},{60,60},{55,65}}};

        int[][][] flighs = {{{0,30,20},{78,70,5}},{{55,0,20},{70,60,10}}};

        iceepeecee simulator = new iceepeecee(islans, flighs);

        assertTrue(simulator.islands().length > 0);

        assertTrue(simulator.flights().length > 0);

        

    }

     //ciclo 2

    @Test

    public void shouldTestPhotograph() {

        String color = "orange";

        int[] from = {10, 10,10};

        int[] to = {20, 20,20};

        int cameraAngle = 90;

        Icep_2.addFlight(color, from, to, cameraAngle);

        float teta = 3.14159f;

        Icep_2.photograph(color, teta);



        // Verificar la zona fotografiada

        Photograph_zone photo = Icep_2.getPhotographedZoneForFlight(color);

        assertNotNull(photo);

        assertArrayEquals(from, photo.getFrom());

        assertArrayEquals(to, photo.getTo());        

    }

    

    @Test

    public void shouldTestPhotograph2() {



        String color = "yellow";

        int[] from = {10, 10,10};

        int[] to = {20, 20,20};

        int cameraAngle = 90;

        Icep_2.addFlight(color, from, to, cameraAngle);

        int teta = 63;

        Icep_2.photograph(color, teta);



        // Verificar la zona fotografiada

        Photograph_zone photo = Icep_2.getPhotographedZoneForFlight(color);

        assertNotNull(photo);

        assertArrayEquals(from, photo.getFrom());

        assertArrayEquals(to, photo.getTo());

        //assertEquals(teta, (float)photo.getteta());

    }

    

    @Test

    public void shouldPhotograph5(){

        int[]from={10,15,13}; 

        int[]to={56,70,95};

        int[]from2={55,50,36};

        int[]to2={80,60,70};

        Icep_2.addFlight("red",from,to,30);

        Icep_2.addFlight("yellow",from2,to2,35);

        Icep_2.photograph(63.1f);

        assertTrue(Icep_2.ok());

    }

    

    @Test

    public void shouldPhotograph6(){

        int[]from={10,15,13}; 

        int[]to={56,70,95};

        int[]from2={55,50,36};

        int[]to2={80,60,70};

        Icep_2.addFlight("red",from,to,30);

        Icep_2.addFlight("yellow",from2,to2,35);

        Icep_2.photograph(63.1f);

        Icep_2.photograph(23.1f);

        assertTrue(Icep_2.ok());

    }

    

    @Test

    public void shouldnotPhotograph4(){

        int[]from={10,15,13}; 

        int[]to={56,70,95};

        String color="green";

        Icep_2.photograph(23.1f);

        assertFalse(Icep_2.ok());

    }

    

    @Test

    public void shouldnotPhotograph5(){

        int[]from={10,15,13}; 

        int[]to={56,70,95};

        int[]from2={55,50,36};

        int[]to2={80,60,70};

        Icep_2.addFlight("red",from,to,30);

        Icep_2.addFlight("yellow",from2,to2,35);

        Icep_2.photograph(-63.1f);

        assertFalse(Icep_2.ok());

    }

    

    @Test

    public void ShouldConsultIslands(){

        int[][] vertix ={{29,70},{60,50},{90,50}};

        Icep_2.addIsland("green",vertix);

        Icep_2.islands();

        assertTrue(Icep_2.ok());

    }

    

    @Test

    public void ShouldConsultIslands2(){

        int[][] vertix ={{29,70},{60,50},{90,50}};

        int[][]vertix2={{85,45},{95,45},{15,35}};

        int[][]vertix3={{47,20},{60,17},{75,28},{60,37}};

        Icep_2.addIsland("white",vertix);

        Icep_2.addIsland("red",vertix2);

        Icep_2.addIsland("orange",vertix3);

        Icep_2.islands();

        assertTrue(Icep_2.ok());

    }

    

    @Test

    public void ShouldnotConsultIslands(){

        Icep_2.islands();

        assertFalse(Icep_2.ok());

    }

    

    @Test

    public void ShouldConsultFlights(){

        int[] from = {20,20,30};

        int[] to = {10,10,40};

        Icep_2.addFlight("green",from,to,90);

        Icep_2.flights();

        assertTrue(Icep_2.ok());

    }

    

    @Test

    public void ShouldConsultFlight2(){

        int[]from={10,15,13}; 

        int[]to={56,70,95};

        int[]from2={55,50,36};

        int[]to2={80,60,70};

        int[]from3={105,30,50};

        int[]to3={18,70,50};

        Icep_2.addFlight("red",from,to,56);

        Icep_2.addFlight("white",from2,to2,89);

        Icep_2.addFlight("yellow",from3,to3,13);

        Icep_2.flights();

        assertTrue(Icep_2.ok());

    }

    

    @Test

    public void ShouldnotConsultFlights(){

        Icep_2.flights();

        assertFalse(Icep_2.ok());

    }

    

    @Test

    public void ShouldbservedIslands(){

        Icep_2.observedIslands();

        assertTrue(Icep_2.ok());

    }

    

    public void ShouldObservedIslands(){        

        int[][]vertix={{20,30},{50,50},{10,50}};

        int[][]vertix2={{15,45},{45,45},{25,35}};

        int[]from={0,30,20}; 

        int[]to={78,70,5};

        Icep_2.addIsland("red",vertix);

        Icep_2.addIsland("yellow",vertix2);

        Icep_2.addFlight("black",from,to,56);

        Icep_2.photograph(60);

        Icep_2.observedIslands();

        assertTrue(Icep_2.ok());  

    }

    

    public void ShouldObservedIslands2(){

        int[][]vertix={{20,30},{50,50},{10,50}};

        int[][]vertix2={{15,45},{45,45},{25,35}};

        int[][]vertix3={{15,55},{75,45},{15,35}};

        Icep_2.addIsland("red",vertix);

        Icep_2.addIsland("green",vertix2);

        Icep_2.addIsland("orange",vertix3);

        int[]from={0,30,89}; 

        int[]to={45,0,5};

        int[]from2={55,50,20};

        int[]to2={40,68,10};

        int[]from3={100,50,9};

        int[]to3={16,180,45};

        Icep_2.addFlight("yellow",from,to,89);

        Icep_2.addFlight("gray",from2,to2,56);

        Icep_2.addFlight("purple",from3,to3,23);

        Icep_2.observedIslands();

        assertTrue(Icep_2.ok());

    }

    

    public void ShouldUselessFlights(){

        Icep_2.uselessFlights();

        assertTrue(Icep_2.ok());

    }

    

    public void ShouldUselessFlights2(){

        int[][]vertix={{46,50},{70,50},{71,20},{67,12}};

        Icep_2.addIsland("blue",vertix);

        int[]from={18,100,26};

        int[]to={12,100,50};

        Icep_2.addFlight("yellow",from,to,23);

        Icep_2.uselessFlights();

        assertTrue(Icep_2.ok());

    }

    

    public void ShouldUselessFlights3(){

        int[][]vertix ={{98,78},{50,50},{40,60}};

        int[][]vertix2={{15,45},{45,45},{25,35}};

        int[][]vertix3={{40,20},{60,10},{75,20},{60,30}};

        Icep_2.addIsland("blue",vertix);

        Icep_2.addIsland("yellow",vertix2);

        Icep_2.addIsland("orange",vertix3);

        int[]from={40,38,70}; 

        int[]to={98,70,50};

        int[]from2={55,0,20};

        int[]to2={80,40,1};

        int[]from3={56,140,80};

        int[]to3={18,100,50};

        Icep_2.addFlight("gray",from,to,47);

        Icep_2.addFlight("pink",from2,to2,25);

        Icep_2.addFlight("magenta",from3,to3,12);

        Icep_2.uselessFlights();

        assertTrue(Icep_2.ok());

    }

    

    @Test

    public void shouldgetUseless(){

        int[][] vertix = {{100,20},{80,40},{30,20}};

        Icep_2.addIsland("red",vertix);

        int[] from = {80,40,50};

        int[] to = {30,30,20};

        

        Icep_2.addFlight("blue",from,to,65);

        Icep_2.photograph("blue",30);

        assertTrue(Icep_2.uselessFlights().length > 0);   

    }   

}



// Montero-Villamizar

@Test

    public void ShouldObservedIslands(){        



        

        int[][] vertices = {{20, 20}, {100, 50}, {80, 30}};



        int[] from = {20, 20, 10};

        int[] to = {50, 10, 15};



        ip.addIsland("blue",vertices);

        ip.addFlight("black",from,to);



        ip.photograph(60);



        ip.observedIslands();



        assertTrue(ip.ok());  



    }



    

    @Test

       public void ShouldUselessFlights(){

        ip.uselessFlights();

        assertTrue(ip.ok());



    }

    

    @Test

    public void ShouldUselessFlights2(){

    

        int[][] vertices={{46,50},{70,50},{71,20},{67,12}};

    

        ip.addIsland("red",vertices);

    

        int[]from={18,100,26};

    

        int[]to={12,100,50};

    

        ip.addFlight("black",from,to);

    

        ip.uselessFlights();

    

        assertTrue(ip.ok());

    }

    

    

    @Test



    public void shouldPhotograph() {

        Iceepeecee simulator = new Iceepeecee(100, 100);

        String color = "red";

        int[] from = {20, 20, 10};

        int[] to = {50, 10, 15};

        simulator.addFlight("blue", from, to);

        simulator.photograph(60);

        assertTrue(simulator.ok());

    }

    

    

    @Test



    public void shouldNotPhotograph() {

        Iceepeecee simulator = new Iceepeecee(100, 100);

        String color = "red";

        int[] from = {20, 20, 10};

        int[] to = {50, 10, 15};

        simulator.addFlight("blue", from, to);

        simulator.photograph(100);

        assertFalse(simulator.ok());

    }

    

    

    @Test



    public void shouldMakePhotoWithNoIntAngle() {

        ip.addFlight("red", new int[]{0, 0, 0}, new int[]{1, 1, 1});

        ip.photograph("red", 45.5f);

        assertTrue(ip.ok());        

    }

    

    

    @Test



    public void shouldNotAddCameraWithNoIntAngle() {

        ip.addFlight("red", new int[]{0, 0, 0}, new int[]{1, 1, 1});

        ip.photograph("red", -45.5f);

        assertFalse(ip.ok());  

    }

    

    

    @Test

    public void shouldConsultIslandsAndFlights() {

        int[][] vertices={{10,10},{60,30},{20,20}};



        ip.addIsland("blue",vertices);

        

        int[]from={18,100,26};

        int[]to={12,100,50};

        ip.addFlight("black",from,to);



        ip.islands();

        ip.flights();



        assertTrue(ip.ok());

    }

    

    

    @Test

    public void shouldNotConsultIslandsAndFlights() {

        ip.islands();

        ip.flights();

        

        assertFalse(ip.ok());

    }

    

    @Test

    public void shouldConsultsIslandsInPhotographs(){



        int[][] vertices1 = {{100, 100}, {200, 100}, {150, 200}};

        int[][] vertices2 = {{300, 300}, {400, 300}, {350, 400}};

        int[][] vertices3 = {{500, 500}, {600, 500}, {550, 600}};

        ip.addIsland("red", vertices1);

        ip.addIsland("blue", vertices2);

        ip.addIsland("green", vertices3);



        int[] from1 = {125, 125, 100};

        int[] to1 = {325, 325, 100};

        int[] from2 = {425, 425, 100};

        int[] to2 = {625, 625, 100};

        ip.addFlight("black", from1, to1);

        ip.addFlight("pink", from2, to2);



        ip.observedIslands();

        assertTrue(ip.ok());

    }

    

    @Test

    public void shouldNotConsultsIslandsInPhotographs(){



        int[][] vertices1 = {{100, 100}, {200, 100}, {150, 200}};

        int[][] vertices2 = {{300, 300}, {400, 300}, {350, 400}};

        int[][] vertices3 = {{500, 500}, {600, 500}, {550, 600}};

        ip.addIsland("red", vertices1);

        ip.addIsland("blue", vertices2);

        ip.addIsland("green", vertices3);



        int[] from = {5, 5, 10};

        int[] to = {10, 15, 20};

        ip.addFlight("black", from, to);



        ip.observedIslands();

        assertFalse(ip.ok());

    }

    

    @Test

    public void shouldConsultUselessFlights(){



        ip.uselessFlights();

        assertFalse(ip.ok());

    }

//Robinson - Yaya

    

    @Test

    public void ShouldAllowNonIntegerCameraAngles() {

        icp.addFlight("blue", new int[]{3, 5, 6}, new int[]{7, 4, 8});

            icp.photograph(69.9f);

            assertTrue(icp.ok());

    }

    

    @Test

    public void ShouldNotAllowNonIntegerCameraAngles() {

        icp.addFlight("blue", new int[]{3, 5, 6}, new int[]{7, 4, 8});

            icp.photograph(-87.f);

            assertFalse(icp.ok());

    }



    @Test

    public void ShouldAllowQueryAllIslandsAndFlights() {

        // Agregar algunas islas y vuelos

        icp.addIsland("red", new int[][]{{50, 50}, {60, 50}, {60, 60}});

        icp.addFlight("blue", new int[]{50, 50, 0}, new int[]{100, 100, 0});

    

        // Consultar todas las islas y vuelos

        String[] allIslands = icp.islands();

        String[] allFlights = icp.flights();

    

        // Verificar que las islas y vuelos se hayan consultado correctamente

        assertEquals(1, allIslands.length);

        assertEquals(1, allFlights.length);

        assertEquals("red", allIslands[0]);

        assertEquals("blue", allFlights[0]);

    }

    

    @Test

    public void ShouldNotAllowQueryAllIslandsAndFlights() {

        // Consultar todas las islas y vuelos antes de agregar ninguno

        String[] allIslands = icp.islands();

        String[] allFlights = icp.flights();

    

        // Verificar que los arreglos estén vacíos

        assertEquals(0, allIslands.length);

        assertEquals(0, allFlights.length);

    }

    

    @Test

    public void ShouldAllowQueryIslandsInPhotographs() {

    

        // Agregar una isla y un vuelo

        icp.addIsland("red", new int[][]{{50, 50}, {60, 50}, {60, 60}});

        icp.addFlight("blue", new int[]{50, 50, 0}, new int[]{100, 100, 0});

    

        // Fotografiar la isla registrada por el vuelo

        icp.photograph(45.0f);

    

        // Consultar las islas registradas en las fotografías

        String[] observedIslands = icp.observedIslands();

        assertTrue(icp.ok());

    }

    

    @Test

    public void ShouldNotAllowQueryIslandsInPhotographs() {

    

        // Agregar una isla y un vuelo

        icp.addIsland("red", new int[][]{{50, 50}, {60, 50}, {60, 60}});

        icp.addFlight("blue", new int[]{50, 50, 0}, new int[]{100, 100, 0});

    

        // No fotografiar la isla registrada por el vuelo

    

        // Consultar las islas registradas en las fotografías

        String[] observedIslands = icp.observedIslands();

        assertFalse(icp.ok());

    }

    

    @Test

    public void ShouldAllowQueryUselessFlights() {

        // Agregar una isla y dos vuelos

        icp.addIsland("red", new int[][]{{50, 50}, {60, 50}, {60, 60}});

        icp.addFlight("blue", new int[]{50, 50, 0}, new int[]{100, 100, 0});

        icp.addFlight("green", new int[]{70, 70, 0}, new int[]{120, 120, 0});

    

        // Fotografiar la isla registrada por el vuelo "blue"

        icp.photograph(45.0f);

    

        // Consultar los vuelos con fotografías inútiles

        String[] uselessFlights = icp.uselessFlights();

        assertTrue(icp.ok());



      

    }

    

    @Test

    public void ShouldNotAllowQueryUselessFlights() {

        // Agregar una isla y un vuelo

        icp.addIsland("red", new int[][]{{50, 50}, {60, 50}, {60, 60}});

        icp.addFlight("blue", new int[]{50, 50, 0}, new int[]{100, 100, 0});

    

        // Fotografiar la isla registrada por el vuelo "blue"

        icp.photograph(45.0f);

    

        // Consultar los vuelos con fotografías inútiles antes de agregar otro vuelo

        String[] uselessFlights1 = icp.uselessFlights();

    

        // Agregar otro vuelo

        icp.addFlight("green", new int[]{70, 70, 0}, new int[]{120, 120, 0});

    

        // Consultar los vuelos con fotografías inútiles después de agregar otro vuelo

        String[] uselessFlights2 = icp.uselessFlights();

        assertFalse(icp.ok());

        

        

    }





//A.MARTINEZ - RAMIREZ



    @Test

    public void testConstructorSuccess() {

        // Prueba que pasará

        int[][][] newIslands = {{{0, 0}, {0, 1}, {1, 1}, {1, 0}}};

        int[][][] newFlights = {{{0, 0, 0}, {1, 1, 0}}};

        Iceepeecee iceepeecee = new Iceepeecee(newIslands, newFlights);

        assertEquals(1, iceepeecee.islands.size());

        assertEquals(1, iceepeecee.fligths.size());

        assertTrue(iceepeecee.ok);

    }

    @Test

    public void testConstructorFailure() {

        // Prueba que fallará

        int[][][] newIslands = null;

        int[][][] newFlights = null;

        Iceepeecee iceepeecee = new Iceepeecee(newIslands, newFlights);

        assertEquals(0, iceepeecee.islands.size());

        assertEquals(0, iceepeecee.fligths.size());

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

        iceepeecee.photograph(45.0f);

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

        iceepeecee.photograph(95.0f);

        assertFalse(iceepeecee.ok);

    }

    

    @Test

    public void testIslandsSuccess() {

        // Prueba que pasará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[][] coordinates = {{0, 0}, {0, 1}, {1, 1}, {1, 0}};

        iceepeecee.addIsland("Red", coordinates);

        iceepeecee.addIsland("Blue", coordinates);

        String[] islands = iceepeecee.islands();

        assertEquals(2, islands.length);

        assertTrue(iceepeecee.ok);

    }

    @Test

    public void testIslandsFailure() {

        // Prueba que fallará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        String[] islands = iceepeecee.islands();

        assertEquals(0, islands.length);

        assertFalse(iceepeecee.ok);

    }

    

    @Test

    public void testFligthsSuccess() {

        // Prueba que pasará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[] from = {0, 0, 10};

        int[] to = {1, 1, 10};

        iceepeecee.addFligth("Red", from, to);

        iceepeecee.addFligth("Blue", from, to);

        String[] fligths = iceepeecee.fligths();

        assertEquals(2, fligths.length);

        assertTrue(iceepeecee.ok);

    }

    @Test

    public void testFligthsFailure() {

        // Prueba que fallará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        String[] fligths = iceepeecee.fligths();

        assertEquals(0, fligths.length);

        assertFalse(iceepeecee.ok);

    }

    

    @Test

    public void testObservedIslandsSuccess() {

        // Prueba que pasará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[][] coordinates = {{0, 0}, {0, 1}, {1, 1}, {1, 0}};

        iceepeecee.addIsland("Red", coordinates);

        iceepeecee.addIsland("Blue", coordinates);

        int[] from = {0, 0, 10};

        int[] to = {1, 1, 10};

        iceepeecee.addFligth("Green", from, to);

        iceepeecee.photograph(45.0f);

        String[] observedIslands = iceepeecee.observedIslands();

        assertEquals(2, observedIslands.length);

    }

    @Test

    public void testObservedIslandsFailure() {

        // Prueba que fallará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        String[] observedIslands = iceepeecee.observedIslands();

        assertEquals(0, observedIslands.length);

    }

    

    @Test

    public void testUselessFlightsSuccess() {

        // Prueba que pasará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[] from = {0, 0, 10};

        int[] to = {1, 1, 10};

        iceepeecee.addFligth("Red", from, to);

        iceepeecee.addFligth("Blue", from, to);

        iceepeecee.photograph(45.0f);

        String[] uselessFlights = iceepeecee.uselessFlights();

        assertEquals(2, uselessFlights.length);

    }

    @Test

    public void testUselessFlightsFailure() {

        // Prueba que fallará

        Iceepeecee iceepeecee = new Iceepeecee(10, 10);

        int[][] coordinates = {{0, 0}, {0, 1}, {1, 1}, {1, 0}};

        iceepeecee.addIsland("Green", coordinates);

        int[] from = {0, 0, 10};

        int[] to = {1, 1, 10};

        iceepeecee.addFligth("Red", from, to);

        iceepeecee.addFligth("Blue", from, to);

        iceepeecee.photograph(45.0f);

        String[] uselessFlights = iceepeecee.uselessFlights();

        assertEquals(0, uselessFlights.length);

    }

}
