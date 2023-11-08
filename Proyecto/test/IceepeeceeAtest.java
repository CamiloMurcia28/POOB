package test;
import iceepeecee.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IceepeeceeAtest {
    private Iceepeecee iceepeecee;
    
    @BeforeEach
    public void setUp() {
        iceepeecee = new Iceepeecee(500,500); // Crea una instancia de Iceepeecee para las pruebas
    }
    
    //Prueba mutsia
    @Test
    public void AccordingMShouldPassIceepeecee(){
        int[][] polygon = {{100,250},{150,250},{200,200},{200,150},{150, 100},{100,100}, {50, 150}, {50, 200}};
        iceepeecee.addIsland("#FF0000", polygon);
        int[]from={0,0,40};int[]to={25,30,20};
        iceepeecee.addFlight("#CD5C5C",from,to);
        int[]from1={5,25,30};int[]to1={25,5,10};
        iceepeecee.addFlight("#CD5C5C",from1,to1);
        iceepeecee.photograph(45);
        iceepeecee.makeVisible();
        
        assertTrue(iceepeecee.observedIslands().length == 0);
    }
        
    //Prueba jeidsom
    @Test
    public void AccordingCShouldPassIceepeecee(){
        int[][]polygon={{100,200},{250,300},{300,200},{100,100},{200,100}};
        String color="#FFFFFF";
        iceepeecee.addIsland(color,polygon);
        int[]from={100,130,20};
        int[]to={178,170,5};
        String color1="#008000";
        iceepeecee.addFlight(color1,from,to);
        iceepeecee.photograph(45.2f);
        assertTrue(iceepeecee.uselessFlights().length==1);
    }
}