package test;
import iceepeecee.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class IceepeeceeC4Test {
    private Iceepeecee iceepeecee;
    
    @BeforeEach
    public void setUp() {
        iceepeecee = new Iceepeecee(1000,1000); // Crea una instancia de Iceepeecee para las pruebas
    }
    //Metodo addIsland
    @Test
    public void accordingCMShouldAddIslandSuccess() {
        String color="#FF0000";
        int[][] polygon = {{10, 10}, {20, 10}, {20, 20}, {10, 20}};
        iceepeecee.addIsland(color, polygon);
        assertTrue(iceepeecee.ok());
    }
    
    
    
    @Test
    public void accordingCMShouldAddIslandSuccess1() {
        String color="#0000FF";
        int[][] polygon = {{0,0}, {0, 10}, {10, 0}, {10, 10}};
        iceepeecee.addIsland(color, polygon);
        assertTrue(iceepeecee.ok());
    }

    @Test
    public void accordingCMShouldNotAddIslandDuplicateColor() {
        int[][] polygon1 = {{10, 10}, {20, 10}, {20, 20}, {10, 20}};
        int[][] polygon2 = {{30, 30}, {40, 30}, {40, 40}, {30, 40}};
        
        iceepeecee.addIsland("#0000FF", polygon1);
        iceepeecee.addIsland("#0000FF", polygon2);
        assertFalse(iceepeecee.ok());
    }
    
    @Test
    public void accordingCMShouldNotAddIslandLessThanThree(){
        int[][] polygon1 = {{10, 10},{20, 20}};
        iceepeecee.addIsland("#FF0000",polygon1);
        assertFalse(iceepeecee.ok());
    }
    
    //Metodo addFlight
    @Test
    public void accordingCMShouldAddFlightSuccess() {
        String color="#FF0000";
        int[] from = {10,10,10};
        int[] to = {70,78,5};
        iceepeecee.addFlight(color, from, to);
        assertTrue(iceepeecee.ok());
    }
    
    @Test
    public void accordingCMShouldAddFlightSuccess1() {
        String color="#FFFF00";
        int[] from = {10,10,5};
        int[] to = {50,21,10};
        iceepeecee.addFlight(color, from, to);
        assertTrue(iceepeecee.ok());
    }

    @Test
    public void accordingCMShouldNotAddFlightDuplicateColor() {
        int[] from1 = {10,10,10};
        int[] to1 = {70,78,5};
        int[] from2 = {0,0,0};
        int[] to2 = {50,50,50};
        iceepeecee.addFlight("#FF0000", from1, to1);
        iceepeecee.addFlight("#FF0000", from2, to2);
        assertFalse(iceepeecee.ok());
    }
    
    @Test
    public void accordingCMShouldNotAddFlightWithLessThanThreeCoordinates(){
        int[] from1 = {100,100};
        int[] to1 = {700,780};
        iceepeecee.addFlight("#FF0000", from1, to1);
        assertFalse(iceepeecee.ok());
    }
    
    //Metodo delIsland
    @Test
    public void accordingCMShouldDelIslandSuccess() {
        int[][] polygon = {{10, 10}, {20, 10}, {20, 20}, {10, 20}};
        iceepeecee.addIsland("#FF0000", polygon);
        iceepeecee.makeVisible();
        iceepeecee.delIsland("#FF0000");
        assertTrue(iceepeecee.ok());
    }
    
    @Test
    public void accordingCMShouldDelIslandSuccess1() {
        int[][] polygon = {{20,30},{50,50},{10,50}};
        iceepeecee.addIsland("#FF00FF", polygon);
        iceepeecee.makeVisible();
        iceepeecee.delIsland("#FF00FF");
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
    public void accordingCMShouldNotDelNotExistentIsland1() {
        int[][] polygon = {{45,60},{55,55},{60,60},{55,65}};
        iceepeecee.addIsland("#000000", polygon);
        iceepeecee.makeVisible();
        iceepeecee.delIsland("#0000FF");
        assertFalse(iceepeecee.ok());
    }
    
    //Metodo delFlight
    @Test
    public void accordingCMShouldDelFlightSuccess() {
        int[] from = {10,10,10};
        int[] to = {70,78,20};
        iceepeecee.addFlight("#FF0000", from, to);
        iceepeecee.delFlight("#FF0000");
        assertTrue(iceepeecee.ok());
    }
    
    @Test
    public void accordingCMShouldDelFlightSuccess1() {
        int[] from = {0,0,0};
        int[] to = {88,78,50};
        iceepeecee.addFlight("#0000FF", from, to);
        iceepeecee.delFlight("#0000FF");
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
    public void accordingCMShouldNotDelNotExistentFlight1() {
        int[] from = {0,0,0};
        int[] to = {88,78,50};
        iceepeecee.addFlight("#0000FF", from, to);
        iceepeecee.delFlight("#FF0000");
        assertFalse(iceepeecee.ok());
    }
    
    //Metodo islandLocation
    @Test
    public void accordingCMShouldLocateIslandSuccess(){
        int[][] polygon = {{10, 10}, {20, 10}, {20, 20}, {10, 20}};
        iceepeecee.addIsland("#FF0000", polygon);
        iceepeecee.makeVisible();
        iceepeecee.islandLocation("#FF0000");
        assertTrue(iceepeecee.ok());
    }
    
    @Test
    public void accordingCMShouldLocateIslandSuccess1(){
        int[][] polygon = {{20,30},{50,50},{10,50}};
        iceepeecee.addIsland("#FFFF00", polygon);
        iceepeecee.makeVisible();
        iceepeecee.islandLocation("#FFFF00");
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
    public void accordingCMShouldNotLocateIslandNotExistent1(){
        int[][] polygon = {{20,30},{50,50},{10,50}};
        iceepeecee.addIsland("#0000FF", polygon);
        iceepeecee.makeVisible();
        iceepeecee.islandLocation("#FF0000");
        assertFalse(iceepeecee.ok());
    }
    
    //Metodo FlightLocation
    @Test
    public void accordingCMShouldLocateFlightSuccess(){
        int[] from = {10,10,10};
        int[] to = {70,78,50};
        iceepeecee.addFlight("#FF0000", from, to);
        iceepeecee.flightLocation("#FF0000");
        assertTrue(iceepeecee.ok());
    }
    
    @Test
    public void accordingCMShouldLocateFlightSuccess1(){
        int[] from = {20,50,30};
        int[] to = {88,78,50};
        iceepeecee.addFlight("#0000FF", from, to);
        iceepeecee.flightLocation("#0000FF");
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
    public void accordingCMShouldNotLocateFlightNotExistent1(){
        int[] from = {20,10,20};
        int[] to = {88,78,30};
        iceepeecee.addFlight("#0000FF", from, to);
        iceepeecee.flightLocation("#FFFF00");
        assertFalse(iceepeecee.ok());
    }
    
    //Metodo Photograph
    @Test
    public void accordingCMShouldTakeAPhotoSuccess(){
        int[]from={0,30,20}; 
        int[]to={78,70,5};
        String color="#FF0000";
        iceepeecee.addFlight(color,from,to);
        iceepeecee.photograph(color,48);
        assertTrue(iceepeecee.ok());
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
    public void accordingCMShouldNotTakeAPhotoFlightNotExistent(){
        int[]from={0,30,20}; 
        int[]to={78,70,5};
        String color="#FF0000";
        iceepeecee.photograph(color,48);
        assertFalse(iceepeecee.ok());        
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
    public void accordingCMShouldNotNotTakeAPhotoWithAnglesMoreThan90(){
        int[]from={0,30,20}; 
        int[]to={78,70,5};
        String color="#FF0000";
        iceepeecee.addFlight(color,from,to);
        iceepeecee.photograph(color,100);
        assertFalse(iceepeecee.ok());
    }
    
    //Metodo Photograph
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
    public void accordingCMShouldTakePhotosWithDifferentTetha(){        
        
        int[]from={0,30,20}; 
        int[]to={78,70,5};
        int[]from1={55,0,20};
        int[]to1={70,60,10};
        String color="#FF0000";
        String color1="#FFFFFF";
        iceepeecee.addFlight(color,from,to);
        iceepeecee.addFlight(color1,from1,to1);
        iceepeecee.photograph(48);
        iceepeecee.photograph(60);
        assertTrue(iceepeecee.ok());

    }
    
    @Test
    public void accordingCMShouldNotTakePhotosFlightsNotExistent(){
        int[]from={0,30,20}; 
        int[]to={78,70,5};
        String color="#FF0000";
        iceepeecee.photograph(48);
        assertFalse(iceepeecee.ok()); 
    }
    
    @Test
    public void accordingCMShouldNotTakePhotosWithNegativeAngles(){
        int[]from={0,30,20}; 
        int[]to={78,70,5};
        int[]from1={55,0,20};
        int[]to1={70,60,10};
        String color="#FF0000";
        String color1="#FFFFFF";
        iceepeecee.addFlight(color,from,to);
        iceepeecee.addFlight(color1,from1,to1);
        iceepeecee.photograph(-1);
        assertFalse(iceepeecee.ok());
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
    
    //Metodo flightCamera
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
    public void accordingCMShouldFlightCameraSuccess1(){
        int[]from1={55,0,20};
        int[]to1={70,60,10};
        String color1="#FFFFFF";
        iceepeecee.addFlight(color1,from1,to1);
        iceepeecee.photograph(60);
        iceepeecee.flightCamera(color1);
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
    @Test
    public void accordingCMShouldNotFlightCameraPhotoNotExistent1(){
        int[]from1={55,0,20};
        int[]to1={70,60,10};
        String color1="#FFFFFF";
        iceepeecee.photograph(60);
        iceepeecee.flightCamera(color1);
        assertFalse(iceepeecee.ok());
    }
    //CICLO 2
    
    //photograph3
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
    public void accordingCMShouldTakePhotosWithDifferentTethaFloat(){        
        
        int[]from={0,30,20}; 
        int[]to={78,70,5};
        int[]from1={55,0,20};
        int[]to1={70,60,10};
        String color="#FF0000";
        String color1="#FFFFFF";
        iceepeecee.addFlight(color,from,to);
        iceepeecee.addFlight(color1,from1,to1);
        iceepeecee.photograph(48.6f);
        iceepeecee.photograph(61.3f);
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
    public void accordingCMShouldNotTakePhotosWithNegativeAnglesFloat(){
        int[]from={0,30,20}; 
        int[]to={78,70,5};
        int[]from1={55,0,20};
        int[]to1={70,60,10};
        String color="#FF0000";
        String color1="#FFFFFF";
        iceepeecee.addFlight(color,from,to);
        iceepeecee.addFlight(color1,from1,to1);
        iceepeecee.photograph(-30.5f);
        assertFalse(iceepeecee.ok());
    }
    
    @Test
    public void accordingCMShouldNotNotTakePhotosWithAnglesMoreThan90Float(){
        int[]from={0,30,20}; 
        int[]to={78,70,5};
        int[]from1={55,0,20};
        int[]to1={70,60,10};
        String color="#FF0000";
        String color1="#FFFFFF";
        iceepeecee.addFlight(color,from,to);
        iceepeecee.addFlight(color1,from1,to1);
        iceepeecee.photograph(100.9f);
        assertFalse(iceepeecee.ok());
    }
    
    //Islands
    @Test
    public void accordingCMShouldConsultIslands(){
        int[][]polygon={{20,30},{50,50},{10,50}};
        String color="#FFA500";
        iceepeecee.addIsland(color,polygon);
        iceepeecee.islands();
        assertTrue(iceepeecee.ok());
    }
    @Test
    public void accordingCMShouldConsultIslands1(){
        int[][]polygon={{20,30},{50,50},{10,50}};
        String color="#FFA500";
        int[][]polygon1={{15,45},{45,45},{25,35}};
        String color1="#808080";
        int[][]polygon2={{40,20},{60,10},{75,20},{60,30}};
        String color2="#FFFFFF";
        iceepeecee.addIsland(color,polygon);
        iceepeecee.addIsland(color1,polygon1);
        iceepeecee.addIsland(color2,polygon2);
        iceepeecee.islands();
        assertTrue(iceepeecee.ok());
    }
    @Test
    public void accordingCMShouldConsultNotExistentIslands(){
        iceepeecee.islands();
        assertTrue(iceepeecee.ok());
    }  
    //Flights
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
    public void accordingCMShouldConsultFlights1(){
        int[]from={0,30,20}; 
        int[]to={78,70,5};
        int[]from1={55,0,20};
        int[]to1={70,60,10};
        int[]from2={100,130,20};
        int[]to2={178,170,5};
        String color="#0000FF";
        String color1="#FFC0CB";
        String color2="#008000";
        iceepeecee.addFlight(color,from,to);
        iceepeecee.addFlight(color1,from1,to1);
        iceepeecee.addFlight(color2,from2,to2);
        iceepeecee.flights();
        assertTrue(iceepeecee.ok());
    }
    @Test
    public void accordingCMShouldConsultNotExistentFlights(){
        iceepeecee.flights();
        assertTrue(iceepeecee.ok());
    }
    
    //observedIslands
    @Test
    public void accordingCMShouldConsultObservedIslandsIsNull(){
        iceepeecee.observedIslands();
        assertTrue(iceepeecee.ok());
    }
    @Test
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
    @Test
    public void accordingCMShouldConsultObservedIslandsSuccess1(){
        int[][]polygon={{20,30},{50,50},{10,50}};
        String color="#FFA500";
        int[][]polygon1={{15,45},{45,45},{25,35}};
        String color1="#808080";
        int[][]polygon2={{40,20},{60,10},{75,20},{60,30}};
        String color2="#FFFFFF";
        iceepeecee.addIsland(color,polygon);
        iceepeecee.addIsland(color1,polygon1);
        iceepeecee.addIsland(color2,polygon2);
        int[]from={0,30,20}; 
        int[]to={78,70,5};
        int[]from1={55,0,20};
        int[]to1={70,60,10};
        int[]from2={100,130,20};
        int[]to2={178,170,5};
        String color3="#0000FF";
        String color4="#FFC0CB";
        String color5="#008000";
        iceepeecee.addFlight(color3,from,to);
        iceepeecee.addFlight(color4,from1,to1);
        iceepeecee.addFlight(color5,from2,to2);
        iceepeecee.photograph(50);
        iceepeecee.observedIslands();
        assertTrue(iceepeecee.ok());
    }
    
    //uselessFlights
    
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
    
    public void accordingCMShouldConsultUselessFlightsSuccess1(){
        int[][]polygon={{20,30},{50,50},{10,50}};
        String color="#FFA500";
        int[][]polygon1={{15,45},{45,45},{25,35}};
        String color1="#808080";
        int[][]polygon2={{40,20},{60,10},{75,20},{60,30}};
        String color2="#FFFFFF";
        iceepeecee.addIsland(color,polygon);
        iceepeecee.addIsland(color1,polygon1);
        iceepeecee.addIsland(color2,polygon2);
        int[]from={0,30,20}; 
        int[]to={78,70,5};
        int[]from1={55,0,20};
        int[]to1={70,60,10};
        int[]from2={100,130,20};
        int[]to2={178,170,5};
        String color3="#0000FF";
        String color4="#FFC0CB";
        String color5="#008000";
        iceepeecee.addFlight(color3,from,to);
        iceepeecee.addFlight(color4,from1,to1);
        iceepeecee.addFlight(color5,from2,to2);
        iceepeecee.uselessFlights();
        assertTrue(iceepeecee.ok());
    }
    
    //CICLO 4
    
    //addIsland2
    
    //fixedIsland
    @Test
    public void accordingCMShouldAddFixedIslandSuccess(){
        int[][] polygon = {{20,30},{50,50},{10,50}};
        String color="#FFFFF";
        iceepeecee.addIsland("FixedIsland",color, polygon);
        assertTrue(iceepeecee.ok());
    }
    
    @Test
    public void accordingCMShouldAddFixedIslandSuccess2(){
        int[][] polygon = {{40,20},{60,10},{75,20},{60,30}};
        String color="#FFFF00";
        iceepeecee.addIsland("FixedIsland",color, polygon);
        assertTrue(iceepeecee.ok());
    }
    
    
    @Test
    public void accordingCMShouldNotDeletedFixedIsland(){
        int[][] polygon = {{20,30},{50,50},{10,50}};
        iceepeecee.addIsland("FixedIsland","#FF4500", polygon);
        iceepeecee.delIsland("#FF4500");
        assertEquals(1, iceepeecee.islands().length);
    }
    
    @Test
    public void accordingCMShouldNotDeletedFixedIsland2(){
        int[][] polygon = {{20,30},{50,50},{10,50}};
        iceepeecee.addIsland("FixedIsland","#FF1493", polygon);
        iceepeecee.delIsland("#FF1493");
        assertEquals(1, iceepeecee.islands().length);
    }
    
    @Test
    public void accordingCMShouldNotAddFixedIslandDuplicateColor() {
        int[][] polygon1 = {{10, 10}, {20, 10}, {20, 20}, {10, 20}};
        int[][] polygon2 = {{30, 30}, {40, 30}, {40, 40}, {30, 40}};
        
        iceepeecee.addIsland("FixedIsland","#0000FF", polygon1);
        iceepeecee.addIsland("FixedIsland","#0000FF", polygon2);
        assertFalse(iceepeecee.ok());
    }
    
    @Test
    public void accordingCMShouldNotAddFixedIslandLessThanThree(){
        int[][] polygon1 = {{10, 10},{20, 20}};
        iceepeecee.addIsland("FixedIsland","#FF0000",polygon1);
        assertFalse(iceepeecee.ok());
    }
    
    //AddSuprisingIsland
    @Test
    public void accordingCMShouldAddSurprisingIslandSuccess(){
        int[][] polygon = {{45,60},{55,55},{60,60},{55,65}};
        iceepeecee.addIsland("SurprisingIsland","#800080", polygon);
        assertTrue(iceepeecee.ok());
    }
    @Test
    public void accordingCMShouldAddSurprisingIslandSuccess2(){
        int[][] polygon = {{20,30},{50,50},{10,50}};
        iceepeecee.addIsland("SurprisingIsland","#228B22", polygon);
        assertTrue(iceepeecee.ok());
    }
    @Test
    public void accordingCMShouldSurprisingIslandLessPoints(){
        int[][] polygon = {{20,30},{50,50},{10,50},{40,30}};
        iceepeecee.addIsland("SurprisingIsland","#800080", polygon);
        assertEquals(3, iceepeecee.islandLocation("#800080").length);
    }
    @Test
    public void accordingCMShouldSurprisingIslandSamePoints(){
        int[][] polygon = {{20,30},{50,50},{10,50}};
        iceepeecee.addIsland("SurprisingIsland","#191970", polygon);
        assertEquals(3, iceepeecee.islandLocation("#191970").length);
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
    public void accordingCMShouldNotAddSuprisingIslandLessThanThree(){
        int[][] polygon1 = {{10, 10},{20, 20}};
        iceepeecee.addIsland("SurprisingIsland","#FF0000",polygon1);
        assertFalse(iceepeecee.ok());
    }
    
    //AddExpandingIsland
    
    @Test
    public void accordingCMShouldAddExpandingIslandSuccess(){
        int[][] polygon = {{45,60},{55,55},{60,60},{55,65}};
        iceepeecee.addIsland("ExpandingIsland","#800080", polygon);
        assertTrue(iceepeecee.ok());
    }
    @Test
    public void accordingCMShouldAddExpandingIslandSuccess2(){
        int[][] polygon = {{20,30},{50,50},{10,50}};
        iceepeecee.addIsland("ExpandingIsland","#228B22", polygon);
        assertTrue(iceepeecee.ok());
    }
    @Test
    public void accordingCMShouldExpandingIslandMakeVisible(){
        int[][] polygon = {{20, 30}, {50, 50}, {10, 50}};
        iceepeecee.addIsland("ExpandingIsland", "#228B22", polygon);
        int[][] originalCoordinates = iceepeecee.islandLocation("#228B22");
        iceepeecee.makeVisible();
        int[][] expandedCoordinates = iceepeecee.islandLocation("#228B22");
        assertTrue(originalCoordinates==expandedCoordinates);
    }
    
    
    @Test
    public void accordingCMShouldNotAddExpandingIslandDuplicateColor() {
        int[][] polygon1 = {{10, 10}, {20, 10}, {20, 20}, {10, 20}};
        int[][] polygon2 = {{30, 30}, {40, 30}, {40, 40}, {30, 40}};
        
        iceepeecee.addIsland("ExpandingIsland","#0000FF", polygon1);
        iceepeecee.addIsland("ExpandingIsland","#0000FF", polygon2);
        assertFalse(iceepeecee.ok());
    }
    @Test
    public void accordingCMShouldNotAddExpandingIslandLessThanThree(){
        int[][] polygon1 = {{10, 10},{20, 20}};
        iceepeecee.addIsland("ExpandingIsland","#FF0000",polygon1);
        assertFalse(iceepeecee.ok());
    }
    
    //addFlight2
    
    //AddFlightLAZY
    @Test
    public void accordingCMShouldAddLazyFlightSuccess(){
        int[] from = {55,0,20};
        int[] to = {70,60,10};
        iceepeecee.addFlight("LazyFlight","#FF0000", from, to);
        assertTrue(iceepeecee.ok());
    }
    @Test
    public void accordingCMShouldAddLazyFlightSuccess2(){
        int[] from = {0,30,20};
        int[] to = {78,70,5};
        iceepeecee.addFlight("LazyFlight","#FFFFFF", from, to);
        assertTrue(iceepeecee.ok());
    }
    
    @Test
    public void accordingCMShouldLazyFlightTakeOnlyOnePhoto(){
        int[] from = {55,0,20};
        int[] to = {70,60,10};
        iceepeecee.addFlight("LazyFlight","#0000FF", from, to);
        iceepeecee.photograph(30);
        iceepeecee.photograph(60);
        
        assertEquals(30, iceepeecee.flightCamera("#0000FF"));
    }
    @Test
    public void accordingCMShouldLazyFlightTakeOnlyOnePhoto2(){
        int[] from = {0,30,20};
        int[] to = {78,70,5};
        iceepeecee.addFlight("LazyFlight","#CD853F", from, to);
        iceepeecee.photograph(30);
        iceepeecee.photograph(60);
        assertEquals(30, iceepeecee.flightCamera("#CD853F"));
    }
    
    
    @Test
    public void accordingCMShouldNotAddLazyFlightDuplicateColor() {
        int[] from1 = {10,10,10};
        int[] to1 = {70,78,5};
        int[] from2 = {0,0,0};
        int[] to2 = {50,50,50};
        iceepeecee.addFlight("LazyFlight","#FF0000", from1, to1);
        iceepeecee.addFlight("LazyFlight","#FF0000", from2, to2);
        assertFalse(iceepeecee.ok());
    }
    
    @Test
    public void accordingCMShouldNotAddLazyFlightWithLessThanThreeCoordinates(){
        int[] from1 = {100,100};
        int[] to1 = {700,780};
        iceepeecee.addFlight("LazyFlight","#FF0000",from1,to1);
        assertFalse(iceepeecee.ok());
    }
    
    
    //AddFlightFLAT
    @Test
    public void accordingCMShouldAddFlatFlightSuccess(){
        int[] from = {55,0,20};
        int[] to = {70,60,10};
        iceepeecee.addFlight("FlatFlight","#FF0000",from,to);
        assertTrue(iceepeecee.ok());
    }
    @Test
    public void accordingCMShouldAddFlatFlightSuccess2(){
        int[] from = {0,30,20};
        int[] to = {78,70,5};
        iceepeecee.addFlight("FlatFlight","#CD5C5C",from,to);
        assertTrue(iceepeecee.ok());
    }
    
    @Test
    public void accordingCMShouldFlatFlightSameAltitude(){
        int[] from = {0, 0, 100};
        int[] to = {100, 100, 200};
        iceepeecee.addFlight("FlatFlight","#FF0000", from, to);
        int altitude = iceepeecee.flightLocation("#FF0000")[1][2];
        assertEquals(from[2], altitude); 
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
    public void accordingCMShouldNotAddFlatFlightDuplicateColor() {
        int[] from1 = {10,10,10};
        int[] to1 = {70,78,5};
        int[] from2 = {0,0,0};
        int[] to2 = {50,50,50};
        iceepeecee.addFlight("FlatFlight","#FF0000", from1, to1);
        iceepeecee.addFlight("FlatFlight","#FF0000", from2, to2);
        assertFalse(iceepeecee.ok());
    }
    
    @Test
    public void accordingCMShouldNotAddFlatFlightWithLessThanThreeCoordinates(){
        int[] from1 = {100,100};
        int[] to1 = {700,780};
        iceepeecee.addFlight("FlatFlight","#FF0000",from1,to1);
        assertFalse(iceepeecee.ok());
    }    
}
