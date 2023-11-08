package test;

import iceepeecee.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class IceepeeceeCC3Test.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class IceepeeceeCC3Test
{
    private Iceepeecee iceepeecee;
    
    @BeforeEach
    public void setUp() {
        iceepeecee = new Iceepeecee(500,500); // Crea una instancia de Iceepeecee para las pruebas
    }
    
//Cortes - Vasquez

    @Test

    public void simulateShouldNot() {

        int[][][] islands = {{{300, 100},{100, 100},{100, 300},{300, 300}},

                             {{400, 500},{800, 500},{800, 700},{500, 700}}};

                                     

        int[][][] flights = {{{100, 100, 100}, {800, 800, 200}},

                            {{500, 100, 100}, {500, 500, 200}}};

        IceepeeceeContest solver = new IceepeeceeContest();

        solver.simulate(islands, flights);

        String[] observedIslands1 = solver.simulator.observedIslands();

        

        int[][][] islands2 = {{{300, 100},{100, 100},{100, 300},{300, 300}},

                             {{400, 500},{800, 500},{800, 700},{500, 700}}};

                             

        int[][][] flights2 = {{{100, 100, 100}, {400, 400, 200}},

                             {{450, 100, 100}, {450, 450, 200}}};

        IceepeeceeContest solver2 = new IceepeeceeContest();

        solver2.simulate(islands2, flights2);

        String[] observedIslands2 = solver2.simulator.observedIslands();

        assertFalse(Arrays.equals(observedIslands1, observedIslands2));

    }

    @Test

    public void simulateShould() {

        int[][][] islands = {{{20, 30},{50, 50},{10, 50}}, 

                             {{40, 20},{60, 10},{75, 20},{60, 30}},  

                             {{45,60},{55,55},{60,60},{55,65}}};

                             

        int[][][] flights = {{{0, 30, 20},{78, 70, 5}}, 

                            {{55, 0, 20}, {10, 60, 10}}};

        IceepeeceeContest solver = new IceepeeceeContest();

        solver.simulate(islands, flights);

        String[] observedIslands1 = solver.simulator.observedIslands();

        

        int[][][] islands2 = {{{20, 30},{50, 50},{10, 50}}, 

                             {{40, 20},{60, 10},{75, 20},{60, 30}},  

                             {{45,60},{55,55},{60,60},{55,65}}};

                             

        int[][][] flights2 = {{{0, 30, 20},{78, 70, 5}}, 

                            {{55, 0, 20}, {10, 60, 10}}};

        IceepeeceeContest solver2 = new IceepeeceeContest();

        solver2.simulate(islands2, flights2);

        String[] observedIslands2 = solver2.simulator.observedIslands();

        assertArrayEquals(observedIslands1, observedIslands2);

    }

    //Vasquez 



     @Test

    public void testSolveShould() {

        int[][][] islands = 

            {{{20,30}, {50,50},{10,50}},{{40,20},{60,10},{75,20},{60,30}},{{45,60},{55,55},{60,60},{55,65}}};

            

        int[][][] flights = {{{0,30,20},{78,70,5}},{{55,0,20},{70,60,10}}};

        

        IceepeeceeContest solver = new IceepeeceeContest();

        float result = solver.solve(islands, flights);

        

        assertEquals(48.0f, result, 0.00001);

    }

    

    @Test

    public void testSolveNotShould() {

        int[][][] islands =   {{{100, 100}, {200, 150}, {100, 200}},{{400, 500}, {700, 500}, {650, 600}}};

        

        int[][][] fligths = {{{50, 50, 20}, {450, 450, 80}}};



        IceepeeceeContest solver = new IceepeeceeContest();

        float result = solver.solve(islands, fligths);



        assertEquals(-999.0f, result, 0.00001); //Reemplazar por su respuesta esperada

    }

    

    @Test

    public void testSimulateShould() {

        int[][][] islands = 

            {{{20,30}, {50,50},{10,50}},{{40,20},{60,10},{75,20},{60,30}},{{45,60},{55,55},{60,60},{55,65}}};

        

        int[][][] flights = {{{0,30,20},{78,70,5}},{{55,0,20},{70,60,10}}};

        

        IceepeeceeContest solver = new IceepeeceeContest();

        solver.simulate(islands, flights);

        

        assertTrue(true);

    }

    

        @Test

    public void testSolveShouldReturnAngle() {

        int[][][] islands = 

            {{{20, 30}, {50, 50}, {10, 50}}, {{40, 20}, {60, 10}, {75, 20}, {60, 30}}, {{45, 60}, {55, 55}, {60, 60}, {55, 65}}};



        int[][][] flights = {{{0, 30, 20}, {78, 70, 5}}, {{55, 0, 20}, {70, 60, 10}}};



        IceepeeceeContest solver = new IceepeeceeContest();

        float result = solver.solve(islands, flights);



        assertTrue(result >= 0 && result < 90);

    }

    //Achuri-Gil

    @Test

    public void shouldSimulate(){

        int[][][] islandss = {{{20,30},{50,50},{10,50}},{{40,20},{60,10},{75,20},{60,30}},{{45,60},{55,55},{60,60},{55,65}}};

        int[][][] flightss = {{{0,30,20},{78,70,5}},{{55,0,20},{70,60,10}}};

        ipC = new IceepeeceContest();

        ipC.simulate(islandss,flightss);

    }

    

    @Test

    public void shouldnotSimulate(){

        int[][][] islandss = {{{20,30},{50,50}},{{40,20},{60,10},{75,20},{60,30}},{{45,60},{55,55},{60,60},{55,65}}};

        int[][][] flightss = {{{0,30,20},{78,70,5}},{{55,0,20},{70,60,10}}};

        ipC = new IceepeeceContest();

        ipC.simulate(islandss,flightss);

    }

    

    @Test

    public void shouldGetACorrectMinAngle() {

        int[][][]islands = new int[][][] { { { 20, 30 }, { 50, 50 }, { 10, 50 } }, { { 40, 20 }, { 60, 10 }, { 75, 20 }, { 60, 30 } }, { { 45, 60 }, { 55, 55 }, { 60, 60 }, { 55, 65 } } };

        int[][][]flights = new int[][][] { { { 0, 30, 20 }, { 78, 70, 5 } }, { { 55, 0, 20 }, { 70, 60, 10 } } };

        String ans = ipC.solve(islands, flights);

        

        // Comprobar que el resultado no es "impossible" (es decir, se encontró un ángulo)

        assertNotEquals("impossible", ans);

        

        // Convertir el resultado a un valor flotante

        float angle = Float.parseFloat(ans);

        

        // Comprobar que el ángulo está en el rango deseado

        assertTrue(angle >= 0 && angle <= 360);

    }

    //*Lesmes*//
    //SOLVE

    @Test

    public void accordingLPShouldTakeTheSmallestAngle() {

        int[][][] islands = {{{20,30},{50,50},{10,50}},{{40,20},{60,10},{75,20},{60,30}},{{45,60},{55,55},{60,60},{55,65}}};

        int[][][] flights = {{{0,30,20},{78,70,5}},{{55,0,20},{70,60,10}},{{100,130,20},{178,170,5}}};

        float menorAngulo_ = iceepeeceeC.solve(islands, flights);

        assertTrue(menorAngulo_ < 48.1f && menorAngulo_ > 48.0f);

    }

    @Test

    public void accordingLPShouldNotTakeTheSmallestAngle() {

        int[][][] islands = {{{0, 0},{10, 0},{10, 10},{0, 10}}};

        int[][][] flights = {{{5, 5, 10}, {15, 5, 10}}};

        float menorAngulo_ = iceepeeceeC.solve(islands, flights);

        assertTrue(menorAngulo_ == -1f);

    }

    //Forero-Murcia



    @Test

    public void accordingFMShouldGetACorrectMinAngle3() {

        int[][][] islands;

        int[][][] flights;

        double ans;

        islands = new int[][][] {{{-5,0},{5,0},{0,5}}};

        flights = new int[][][] {{{0,10,10},{10,0,10}}};

        ans = IceepeeceeContest.solve(islands,flights);

        assertEquals(46.686143342,ans);

    }

    @Test

    public void accordingFMShouldGetACorrectMinAngle4() {

        int[][][] islands;

        int[][][] flights;

        double ans;

        islands = new int[][][] {{{-5,0},{5,0},{0,5}}};

        flights = new int[][][] {{{0,10,5},{10,0,10}}};

        ans = IceepeeceeContest.solve(islands,flights);

        assertEquals(59.491041134,ans);

    }

    @Test

    public void accordingFMShouldGetACorrectMinAngle6() {

        int[][][] islands;

        int[][][] flights;

        double ans;

        islands = new int[][][]{{{-10,-10}, {10,-10}, {10,10},{-10,10}}};

        flights = new int[][][]{{{-100,0,10}, {100,0,10}},

                               {{0,100,10}, {0,-100,10}},

                               {{50,50,15}, {-50,-50,15}},

                               {{-50,50,15}, {50,-50,15}}};

        ans = IceepeeceeContest.solve(islands,flights);

        assertEquals(43.313856658,ans);

    }


    //CASALLAS-MURCIA
    
    //CICLO 3



    @Test

    public void accordingCMShouldSolveWithExampleInput3() {

        int[][][] islands = {{{20,30},{50,50},{10,50}},{{40,20},{60,10},{75,20},{60,30}},{{45,60},{55,55},{60,60},{55,65}}};

        int[][][] flights ={{{0,30,20},{78,70,5}},{{55,0,20},{70,60,10}},{{100,130,20},{178,170,5}}};



        float result = contest.solve(islands, flights);

        assertEquals(48.03169, result, 0.00001);

    }

    @Test

    public void accordingCMShouldNotSolveTheProblem(){

        int[][][] islands = {{{20,30},{50,50},{10,50}},{{40,20},{60,10},{75,20},{60,30}},{{45,60},{55,55},{60,60},{55,65}}};

        int[][][] flights ={{{100,130,20},{178,170,5}}};

        float result = contest.solve(islands, flights);

        assertTrue(result==-1.0f);

    }

    //Chicuazuque-Sierra

    @Test

    public void solveInput1MaratonChicuazuqueSierra(){

       // Crear una instancia de IceepeeceeContest con un escenario de 100x100

        IceepeeceeContest input1 = new IceepeeceeContest(100, 100);

        // Inicializar las variables de instancia

        islands1 = new int[][][] {

            {{20, 30}, {50, 50}, {10, 50}},

            {{40, 20}, {60, 10}, {75, 20}, {60, 30}},

            {{45, 60}, {55, 55}, {60, 60}, {55, 65}}

        };

    

        flights1 = new int[][][] {

            {{0, 30, 20}, {78, 70, 5}},

            {{55, 0, 20}, {70, 60, 10}}

        };

        

        float valorEsperado = 48.587036f;

        float valorObtenido = input1.solve(islands1, flights1);

        float delta = 1.0f; 

        assertEquals(valorEsperado, valorObtenido, delta);

    }

    

    @Test

    public void solveInput2MaratonChicuazuqueSierra(){

        

        IceepeeceeContest input2 = new IceepeeceeContest(100, 100);

        islands2 = new int[][][]{{

            {0, 0}, {10, 0}, {10, 10}, {0, 10}}

            };

        flights2 =  new int[][][]{

            

                {{5, 5, 10},{15, 5, 10}}

            };

        try {

            float result = input2.solve(islands2, flights2);

            assertEquals(-1.0f, result, 0.01); // Se espera que el resultado sea -1.0f (sin solución)

        } catch (Exception e) {

            System.out.println("Excepción manejada: " + e.getMessage());

        }

    }

    //Torres-Valencia

    @Test

    public void testEmptyInput() {

        IceepeeceeContest contest = new IceepeeceeContest();

        int[][][] emptyIslands = {};

        int[][][] emptyFlights = {};

        double result = contest.solve(emptyIslands, emptyFlights);

        assertEquals(-1.0, result, 0.000001);

    }



    @Test

    public void testNoIslands() {

        IceepeeceeContest contest = new IceepeeceeContest();

        int[][][] noIslands = {};

        int[][][] flights = {{{1, 1, 1}, {2, 2, 2}}};  

        float result = contest.solve(noIslands, flights);

        assertEquals(-1.0, result, 0.000001);

    }



    @Test

    public void testNoFlights() {

        IceepeeceeContest contest = new IceepeeceeContest();

        int[][][] islands = {{{1, 1}, {2, 2}, {3, 3}}}; 

        int[][][] noFlights = {};

        double result = contest.solve(islands, noFlights);

        assertEquals(-1.0, result, 0.000001);

    }



    @Test

    public void testSingleIslandSingleFlight() {

        IceepeeceeContest contest = new IceepeeceeContest();

        int[][][] islands = {{{20, 30}, {50, 50}, {10, 50}}};  

        int[][][] flights = {{{0, 30,20}, {78,70,5}}};   

        double result = contest.solve(islands, flights);

        assertEquals(38.036190032958984, result, 0.000001);

    }

    @Test

    public void testProblemMarathon() {

        IceepeeceeContest contest = new IceepeeceeContest();

        int[][][] islands = {{{20, 30}, {50, 50}, {10, 50}},{{40, 20}, {60, 10}, {75, 20}, {60, 30}},{{45, 60}, {55, 55}, {60, 60}, {55, 65}}};  

        int[][][] flights = {{{0, 30,20}, {78,70,5}},{{55,0,20}, {70,60,10}}};   

        double result = contest.solve(islands, flights);

        assertEquals(48.031693036, result, 1e-8);

        

        int[][][] islands2 = {{{0,0}, {10,0}, {10, 10},{0,10}}};  

        int[][][] flights2 = {{{5,5,10},{15,5,10}}};

        assertEquals(-1.0, result, 1e-8);

    }



    //Milton Gutierrez - Jhon Sosa

    @Test

    public void GSshouldSolveTheMaratonProblem(){

        int [][][] islands = {{{20,30},{50,50},{10,50}},{{40,20},{60,10},{75,20},{60,30}},{{45,60},{55,55},{60,60},{55,65}}};

        int [][][] flights = {{{0,30,20},{78,70,5}},{{55,0,20},{70,60,10}},{{100,130,20},{178,170,5}}};

        int width = 300;

        int length = 300;

        IceepeeceeContest JuegoPrueba = new IceepeeceeContest();

        assertTrue(JuegoPrueba.solve(islands,flights) == 48.031696f);

    }

    

    @Test

    public void GSshouldReturnWhenItsImpossible(){

        int [][][] islands = {{{0,0},{10,0},{10,10},{0,10}}};

        int [][][] flights = {{{5,5,10}, {15,5,10}}};

        int width = 300;

        int length = 300;

        IceepeeceeContest JuegoPrueba = new IceepeeceeContest();

        assertTrue(JuegoPrueba.solve(islands,flights) == -1);

    }

    

    @Test

    public void GSshouldnotSolveEmptyIslandsArrayItCountsItAsImpossibleReturningMinusOne(){

        int [][][] islands = {{}};

        int [][][] flights = {{{5,5,10}, {15,5,10}}};

        int width = 300;

        int length = 300;

        IceepeeceeContest JuegoPrueba = new IceepeeceeContest();

        assertTrue(JuegoPrueba.solve(islands,flights) == -1f);

    }







    // DIAZ - MONROY

    @Test

    public void accordingDMShouldSolve(){

        int[][][] islands = {{{20,30},{50,50},{10,50}},

                             {{40,20},{60,10},{75,20},{60,30}},

                             {{45,60},{55,55},{60,60},{55,65}}};

        int[][][] flights = {{{0,30,20}, {78,70,5}},

                            {{55,0,20}, {70,60,10}}};

        IceepeeceeContest icpcContest = new IceepeeceeContest();

        float resp = icpcContest.solve(islands,flights);

        float expectedOutput = 48.031693036f;

        assertEquals(expectedOutput, resp,1e-9f);

    }

    @Test

    public void accordingDMShouldNotSolve(){

        int[][][] islands = {{{0,0},{10,0},{10,10},{0,10}}};

        int[][][] flights = {{{5,5,10}, {15,5,10}}};

        IceepeeceeContest icpcContest = new IceepeeceeContest();

        float resp = icpcContest.solve(islands,flights);

        assertEquals(-1, resp, 0.0f);

    }



    //Mendivelso-Rodriguez

    @Test

    public void shouldSimulateMR(){

        int[][][] islandss = {{{20,30},{50,50},{10,50}},{{40,20},{60,10},{75,20},{60,30}},{{45,60},{55,55},{60,60},{55,65}}};

        int[][][] flightss = {{{0,30,20},{78,70,5}},{{55,0,20},{70,60,10}}};

        ipC = new iceepeeceeContest();

        ipC.simulate(islandss,flightss);

    }


    @Test

    public void shouldGetACorrectMinAngleMR() {

        islands = new int[][][] { { { 20, 30 }, { 50, 50 }, { 10, 50 } }, { { 40, 20 }, { 60, 10 }, { 75, 20 }, { 60, 30 } }, { { 45, 60 }, { 55, 55 }, { 60, 60 }, { 55, 65 } } };

        flights = new int[][][] { { { 0, 30, 20 }, { 78, 70, 5 } }, { { 55, 0, 20 }, { 70, 60, 10 } } };

        ans = iceepeeceeContest.solve(islands, flights);

        

        // Comprobar que el resultado no es "impossible" (es decir, se encontró un ángulo)

        assertNotEquals("impossible", ans);

        

        // Convertir el resultado a un valor flotante

        float angle = Float.parseFloat(ans);

        

        // Comprobar que el ángulo está en el rango deseado

        assertTrue(angle >= 0 && angle <= 360);

    }

    //Silva-Suarez. 

    @Test

    

    public void simulateShouldNotSS() {

        int[][][] islands = {{{300, 100},{100, 100},{100, 300},{300, 300}},

                             {{400, 500},{800, 500},{800, 700},{500, 700}}};

        int[][][] flights = {{{100, 100, 100}, {800, 800, 200}},

                            {{500, 100, 100}, {500, 500, 200}}};

        IceepeeceeContest solver = new IceepeeceeContest();

        solver.simulate(islands, flights);

        String[] observedIslands1 = solver.simulator.observedIslands();

        int[][][] islands2 = {{{300, 100},{100, 100},{100, 300},{300, 300}},

                             {{400, 500},{800, 500},{800, 700},{500, 700}}};

        int[][][] flights2 = {{{100, 100, 100}, {400, 400, 200}},

                             {{450, 100, 100}, {450, 450, 200}}};

        IceepeeceeContest solver2 = new IceepeeceeContest();

        solver2.simulate(islands2, flights2);

        String[] observedIslands2 = solver2.simulator.observedIslands();

        assertFalse(Arrays.equals(observedIslands1, observedIslands2));

    

    }

    @Test

    

    public void simulateShouldSS() {

        int[][][] islands = {{{20, 30},{50, 50},{10, 50}}, 

                             {{40, 20},{60, 10},{75, 20},{60, 30}},  

                             {{45,60},{55,55},{60,60},{55,65}}};

        int[][][] flights = {{{0, 30, 20},{78, 70, 5}}, 

                            {{55, 0, 20}, {10, 60, 10}}};

        IceepeeceeContest solver = new IceepeeceeContest();

        solver.simulate(islands, flights);

        String[] observedIslands1 = solver.simulator.observedIslands();

        int[][][] islands2 = {{{20, 30},{50, 50},{10, 50}}, 

                             {{40, 20},{60, 10},{75, 20},{60, 30}},  

                             {{45,60},{55,55},{60,60},{55,65}}};  

        int[][][] flights2 = {{{0, 30, 20},{78, 70, 5}}, 

                            {{55, 0, 20}, {10, 60, 10}}};

        IceepeeceeContest solver2 = new IceepeeceeContest();

        solver2.simulate(islands2, flights2);

        String[] observedIslands2 = solver2.simulator.observedIslands();

        assertEquals(observedIslands1.length, observedIslands2.length);

    

    }

     @Test



    public void testSolveShouldSS() {

        int[][][] islands = {{{20,30}, {50,50},{10,50}},{{40,20},{60,10},{75,20},{60,30}},{{45,60},{55,55},{60,60},{55,65}}};

        int[][][] flights = {{{0,30,20},{78,70,5}},{{55,0,20},{70,60,10}}};

        IceepeeceeContest solver = new IceepeeceeContest();

        float result = solver.solve(islands, flights);

        assertEquals(48.031693036f, result, 0.00001);

    }

    @Test



    public void testSolveNotShouldSS() {

        int[][][] islands =   {{{100, 100}, {200, 150}, {100, 200}},{{400, 500}, {700, 500}, {650, 600}}};

        int[][][] fligths = {{{50, 50, 20}, {450, 450, 80}}};

        IceepeeceeContest solver = new IceepeeceeContest();

        float result = solver.solve(islands, fligths);

        assertEquals(-1.0f, result, 0.00001);

    }

    @Test



    public void testSimulateShouldSS() {

        int[][][] islands = {{{20,30}, {50,50},{10,50}},{{40,20},{60,10},{75,20},{60,30}},{{45,60},{55,55},{60,60},{55,65}}};

        int[][][] flights = {{{0,30,20},{78,70,5}},{{55,0,20},{70,60,10}}};

        IceepeeceeContest solver = new IceepeeceeContest();

        solver.simulate(islands, flights);

        assertTrue(true);



    }

    

    @Test



    public void testSolveShouldReturnAngleSS() {

        int[][][] islands = {{{20, 30}, {50, 50}, {10, 50}}, {{40, 20}, {60, 10}, {75, 20}, {60, 30}}, {{45, 60}, {55, 55}, {60, 60}, {55, 65}}};

        int[][][] flights = {{{0, 30, 20}, {78, 70, 5}}, {{55, 0, 20}, {70, 60, 10}}};

        IceepeeceeContest solver = new IceepeeceeContest();

        float result = solver.solve(islands, flights);

        assertTrue(result >= 0 && result < 90);

    }



    // Montero-Villamizar

    @Test

    public void shouldSimulate() {

        int[][][] islands = {

            {{20, 30}, {50, 50}, {10, 50}},

            {{40, 20}, {60, 10}, {75, 20}, {60, 30}},

            {{45, 60}, {55, 55}, {60, 60}, {55, 65}}

        };

        int[][][] flights = {

            {{0, 30, 20}, {78, 70, 5}},

            {{55, 0, 20}, {70, 60, 10}}

        };



        assertDoesNotThrow(() -> ipC.simulate(islands, flights));

    }



    @Test

    public void shouldNotSimulate() {

        int[][][] islands = {

            {{20, 30}, {50, 50}},

            {{40, 20}, {60, 10}, {75, 20}, {60, 30}},

            {{45, 60}, {55, 55}, {60, 60}, {55, 65}}

        };

        int[][][] flights = {

            {{0, 30, 20}, {78, 70, 5}},

            {{55, 0, 20}, {70, 60, 10}}

        };



        assertDoesNotThrow(() -> ipC.simulate(islands, flights));

    }



    @Test

    public void shouldGetACorrectMinAngle() {

        int[][][] islands = {

            {{20, 30}, {50, 50}, {10, 50}},

            {{40, 20}, {60, 10}, {75, 20}, {60, 30}},

            {{45, 60}, {55, 55}, {60, 60}, {55, 65}}

        };

        int[][][] flights = {

            {{0, 30, 20}, {78, 70, 5}},

            {{55, 0, 20}, {70, 60, 10}}

        };

        String ans = ipC.solve(islands, flights);



        assertNotEquals("impossible", ans);



        float angle = Float.parseFloat(ans);



        assertTrue(angle >= 0 && angle <= 360);

    }



    @Test

    public void shouldNotSolve() {

        int[][][] islands = {

            {{100, 100}, {200, 150}, {100, 200}},

            {{400, 500}, {700, 500}, {650, 600}}

        };

        int[][][] flights = {

            {{150, 60, 20}, {400, 300, 90}}

        };



        String result = ipC.solve(islands, flights);



        assertEquals("impossible", result);

    }

    //Robinson - Yaya

    @Test

    public void shouldSimulate(){

        int[][][] islandss = {{{20,30},{50,50},{10,50}},{{40,20},{60,10},{75,20},{60,30}},{{45,60},{55,55},{60,60},{55,65}}};

        int[][][] flightss = {{{0,30,20},{78,70,5}},{{55,0,20},{70,60,10}}};

        ipC = new IceepeeceeContest();

        ipC.simulate(islandss,flightss);

    }

    @Test

    public void shouldnotSimulate(){

        int[][][] islandss = {{{20,30},{50,50}},{{40,20},{60,10},{75,20},{60,30}},{{45,60},{55,55},{60,60},{55,65}}};

        int[][][] flightss = {{{0,30,20},{78,70,5}},{{55,0,20},{70,60,10}}};

        ipC = new IceepeeceeContest();

        ipC.simulate(islandss,flightss);

    }

    

    @Test

    public void shouldGetACorrectMinAngle() {

        int[][][]islands = new int[][][] { { { 20, 30 }, { 50, 50 }, { 10, 50 } }, { { 40, 20 }, { 60, 10 }, { 75, 20 }, { 60, 30 } }, { { 45, 60 }, { 55, 55 }, { 60, 60 }, { 55, 65 } } };

        int[][][]flights = new int[][][] { { { 0, 30, 20 }, { 78, 70, 5 } }, { { 55, 0, 20 }, { 70, 60, 10 } } };

        float ans = ipC.solve(islands, flights);

        assertNotEquals(-1.0f, ans);

        float angle = ans;

        assertTrue(angle >= 0 && angle <= 360);

    }

    

    @Test



    public void testSolveNotShould() {



        int[][][] islands =   {{{100, 100}, {200, 150}, {100, 200}},{{400, 500}, {700, 500}, {650, 600}}};

        int[][][] fligths = {{{50, 50, 20}, {450, 450, 80}}};

        IceepeeceeContest solver = new IceepeeceeContest();

        float result = solver.solve(islands, fligths);

        assertEquals(result,-1.0f); //Reemplazar por su respuesta esperada

    }

//A.MARTINEZ - RAMIREZ

    @Test

    public void testSolvePass() {

        IceepeeceeContest iceepeeceeContest = new IceepeeceeContest();

        int[][][] newIslands = {{{20,30}, {50,50}, {10,50}},

                             {{40,20}, {60,10}, {75,20}, {60,30}},

                             {{45,60}, {55,55}, {60,60}, {55,65}}};

        int[][][] newFlights = {{{0,30,20}, {78,70,5}},

                             {{55,0,20}, {70,60,10}}};

        float expectedTheta = 48.1f;

        assertEquals(expectedTheta, iceepeeceeContest.solve(newIslands, newFlights));

    }

    @Test

    public void testSolveFail() {

        IceepeeceeContest iceepeeceeContest = new IceepeeceeContest();

        int[][][] newIslands = {{{20,30}, {50,50}, {10,50}},

                             {{40,20}, {60,10}, {75,20}, {60,30}},

                             {{45,60}, {55,55}, {60,60}, {55,65}}};

        int[][][] newFlights = {{{0,5,20},{18,50,5}}};

        float expectedTheta = 0f;

        assertEquals(expectedTheta, iceepeeceeContest.solve(newIslands, newFlights));

    }


}
    

