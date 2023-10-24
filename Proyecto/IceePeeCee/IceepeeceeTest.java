import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class IceepeeceeTest {
    private Iceepeecee iceepeecee;
    
    @BeforeEach
    public void setUp() {
        iceepeecee = new Iceepeecee(150,150); // Crea una instancia de Iceepeecee para las pruebas
    }
    //Metodo addIsland
    @Test
    public void accordingCMShouldAddIslandSuccess() {
        String color="red";
        int[][] polygon = {{10, 10}, {20, 10}, {20, 20}, {10, 20}};
        iceepeecee.addIsland(color, polygon);
        assertTrue(iceepeecee.lastOperationSuccess);
    }
    @Test
    public void accordingCMShouldAddIslandSuccess1() {
        String color="blue";
        int[][] polygon = {{0,0}, {0, 10}, {10, 0}, {10, 10}};
        iceepeecee.addIsland(color, polygon);
        assertTrue(iceepeecee.lastOperationSuccess);
    }

    @Test
    public void accordingCMShouldNotAddIslandDuplicateColor() {
        int[][] polygon1 = {{10, 10}, {20, 10}, {20, 20}, {10, 20}};
        int[][] polygon2 = {{30, 30}, {40, 30}, {40, 40}, {30, 40}};
        
        iceepeecee.addIsland("blue", polygon1);
        iceepeecee.addIsland("blue", polygon2);
        assertFalse(iceepeecee.lastOperationSuccess);
    }
    
    @Test
    public void accordingCMShouldNotAddIslandLessThanThree(){
        int[][] polygon1 = {{10, 10},{20, 20}};
        iceepeecee.addIsland("red",polygon1);
        assertFalse(iceepeecee.lastOperationSuccess);
    }
    
    //Metodo addFlight
    @Test
    public void accordingCMShouldAddFlightSuccess() {
        String color="red";
        int[] from = {10,10,10};
        int[] to = {70,78,5};
        iceepeecee.addFlight(color, from, to);
        assertTrue(iceepeecee.lastOperationSuccess);
    }
    
    @Test
    public void accordingCMShouldAddFlightSuccess1() {
        String color="yellow";
        int[] from = {10,10,5};
        int[] to = {50,21,10};
        iceepeecee.addFlight(color, from, to);
        assertTrue(iceepeecee.lastOperationSuccess);
    }

    @Test
    public void accordingCMShouldNotAddFlightDuplicateColor() {
        int[] from1 = {10,10,10};
        int[] to1 = {70,78,5};
        int[] from2 = {0,0,0};
        int[] to2 = {50,50,50};
        iceepeecee.addFlight("red", from1, to1);
        iceepeecee.addFlight("red", from2, to2);
        assertFalse(iceepeecee.lastOperationSuccess);
    }
    
    @Test
    public void accordingCMShouldNotAddFlightWithLessThanThreeCoordinates(){
        int[] from1 = {100,100};
        int[] to1 = {700,780};
        iceepeecee.addFlight("red", from1, to1);
        assertFalse(iceepeecee.lastOperationSuccess);
    }
    
    //Metodo delIsland
    @Test
    public void accordingCMShouldDelIslandSuccess() {
        int[][] polygon = {{10, 10}, {20, 10}, {20, 20}, {10, 20}};
        iceepeecee.addIsland("red", polygon);
        iceepeecee.makeVisible();
        iceepeecee.delIsland("red");
        assertTrue(iceepeecee.lastOperationSuccess);
    }
    
    @Test
    public void accordingCMShouldDelIslandSuccess1() {
        int[][] polygon = {{20,30},{50,50},{10,50}};
        iceepeecee.addIsland("magenta", polygon);
        iceepeecee.makeVisible();
        iceepeecee.delIsland("magenta");
        assertTrue(iceepeecee.lastOperationSuccess);
    }
    
    @Test
    public void accordingCMShouldNotDelNotExistentIsland() {
        int[][] polygon = {{10, 10}, {20, 10}, {20, 20}, {10, 20}};
        iceepeecee.addIsland("red", polygon);
        iceepeecee.makeVisible();
        iceepeecee.delIsland("blue");
        assertFalse(iceepeecee.lastOperationSuccess);
    }
    
    @Test
    public void accordingCMShouldNotDelNotExistentIsland1() {
        int[][] polygon = {{45,60},{55,55},{60,60},{55,65}};
        iceepeecee.addIsland("black", polygon);
        iceepeecee.makeVisible();
        iceepeecee.delIsland("blue");
        assertFalse(iceepeecee.lastOperationSuccess);
    }
    
    //Metodo delFlight
    @Test
    public void accordingCMShouldDelFlightSuccess() {
        int[] from = {10,10,10};
        int[] to = {70,78,20};
        iceepeecee.addFlight("red", from, to);
        iceepeecee.delFlight("red");
        assertTrue(iceepeecee.lastOperationSuccess);
    }
    
    @Test
    public void accordingCMShouldDelFlightSuccess1() {
        int[] from = {0,0,0};
        int[] to = {88,78,50};
        iceepeecee.addFlight("blue", from, to);
        iceepeecee.delFlight("blue");
        assertTrue(iceepeecee.lastOperationSuccess);
    }
    
    @Test
    public void accordingCMShouldNotDelNotExistentFlight() {
        int[] from = {10,10,10};
        int[] to = {70,78,50};
        iceepeecee.addFlight("red", from, to);
        iceepeecee.delFlight("blue");
        assertFalse(iceepeecee.lastOperationSuccess);
    }
    
    @Test
    public void accordingCMShouldNotDelNotExistentFlight1() {
        int[] from = {0,0,0};
        int[] to = {88,78,50};
        iceepeecee.addFlight("blue", from, to);
        iceepeecee.delFlight("red");
        assertFalse(iceepeecee.lastOperationSuccess);
    }
    
    //Metodo islandLocation
    @Test
    public void accordingCMShouldLocateIslandSuccess(){
        int[][] polygon = {{10, 10}, {20, 10}, {20, 20}, {10, 20}};
        iceepeecee.addIsland("red", polygon);
        iceepeecee.makeVisible();
        iceepeecee.islandLocation("red");
        assertTrue(iceepeecee.lastOperationSuccess);
    }
    
    @Test
    public void accordingCMShouldLocateIslandSuccess1(){
        int[][] polygon = {{20,30},{50,50},{10,50}};
        iceepeecee.addIsland("yellow", polygon);
        iceepeecee.makeVisible();
        iceepeecee.islandLocation("yellow");
        assertTrue(iceepeecee.lastOperationSuccess);
    }
    
    @Test
    public void accordingCMShouldNotLocateIslandNotExistent(){
        int[][] polygon = {{10, 10}, {20, 10}, {20, 20}, {10, 20}};
        iceepeecee.addIsland("red", polygon);
        iceepeecee.makeVisible();
        iceepeecee.islandLocation("blue");
        assertFalse(iceepeecee.lastOperationSuccess);
    }
    
    @Test
    public void accordingCMShouldNotLocateIslandNotExistent1(){
        int[][] polygon = {{20,30},{50,50},{10,50}};
        iceepeecee.addIsland("blue", polygon);
        iceepeecee.makeVisible();
        iceepeecee.islandLocation("red");
        assertFalse(iceepeecee.lastOperationSuccess);
    }
    
    //Metodo FlightLocation
    @Test
    public void accordingCMShouldLocateFlightSuccess(){
        int[] from = {10,10,10};
        int[] to = {70,78,50};
        iceepeecee.addFlight("red", from, to);
        iceepeecee.flightLocation("red");
        assertTrue(iceepeecee.lastOperationSuccess);
    }
    
    @Test
    public void accordingCMShouldLocateFlightSuccess1(){
        int[] from = {20,50,30};
        int[] to = {88,78,50};
        iceepeecee.addFlight("blue", from, to);
        iceepeecee.flightLocation("blue");
        assertTrue(iceepeecee.lastOperationSuccess);
    }
    
    @Test
    public void accordingCMShouldNotLocateFlightNotExistent(){
        int[] from = {10,10,10};
        int[] to = {70,78,50};
        iceepeecee.addFlight("red", from, to);
        iceepeecee.flightLocation("blue");
        assertFalse(iceepeecee.lastOperationSuccess);
    }
    
    @Test
    public void accordingCMShouldNotLocateFlightNotExistent1(){
        int[] from = {20,10,20};
        int[] to = {88,78,30};
        iceepeecee.addFlight("blue", from, to);
        iceepeecee.flightLocation("yellow");
        assertFalse(iceepeecee.lastOperationSuccess);
    }
    
    //Metodo Photograph
    @Test
    public void accordingCMShouldTakeAPhotoSuccess(){
        int[]from={0,30,20}; 
        int[]to={78,70,5};
        String color="red";
        iceepeecee.addFlight(color,from,to);
        iceepeecee.photograph(color,48);
        assertTrue(iceepeecee.lastOperationSuccess);
    }
    
    @Test
    public void accordingCMShouldTakeAPhotoWithDifferentTetha(){
        int[]from={0,30,20}; 
        int[]to={78,70,5};
        String color="red";
        iceepeecee.addFlight(color,from,to);
        iceepeecee.photograph(color,48);
        iceepeecee.photograph(color,60);
        assertTrue(iceepeecee.lastOperationSuccess);
    }
    
    @Test
    public void accordingCMShouldNotTakeAPhotoFlightNotExistent(){
        int[]from={0,30,20}; 
        int[]to={78,70,5};
        String color="red";
        iceepeecee.photograph(color,48);
        assertFalse(iceepeecee.lastOperationSuccess);        
    }
    
    @Test
    public void accordingCMShouldNotTakeAPhotoWithNegativeAngles(){
        int[]from={0,30,20}; 
        int[]to={78,70,5};
        String color="red";
        iceepeecee.addFlight(color,from,to);
        iceepeecee.photograph(color,-1);
        assertFalse(iceepeecee.lastOperationSuccess);
    }

    @Test
    public void accordingCMShouldNotNotTakeAPhotoWithAnglesMoreThan90(){
        int[]from={0,30,20}; 
        int[]to={78,70,5};
        String color="red";
        iceepeecee.addFlight(color,from,to);
        iceepeecee.photograph(color,100);
        assertFalse(iceepeecee.lastOperationSuccess);
    }
    
    //Metodo Photograph
    @Test
    public void accordingCMShouldTakePhotosSuccess(){
        int[]from={0,30,20}; 
        int[]to={78,70,5};
        int[]from1={55,0,20};
        int[]to1={70,60,10};
        String color="red";
        String color1="white";
        iceepeecee.addFlight(color,from,to);
        iceepeecee.addFlight(color1,from1,to1);
        iceepeecee.photograph(48);
        assertTrue(iceepeecee.lastOperationSuccess);
    }
    
    @Test
    public void accordingCMShouldTakePhotosWithDifferentTetha(){        
        
        int[]from={0,30,20}; 
        int[]to={78,70,5};
        int[]from1={55,0,20};
        int[]to1={70,60,10};
        String color="red";
        String color1="white";
        iceepeecee.addFlight(color,from,to);
        iceepeecee.addFlight(color1,from1,to1);
        iceepeecee.photograph(48);
        iceepeecee.photograph(60);
        assertTrue(iceepeecee.lastOperationSuccess);

    }
    
    @Test
    public void accordingCMShouldNotTakePhotosFlightsNotExistent(){
        int[]from={0,30,20}; 
        int[]to={78,70,5};
        String color="red";
        iceepeecee.photograph(48);
        assertFalse(iceepeecee.lastOperationSuccess); 
    }
    
    @Test
    public void accordingCMShouldNotTakePhotosWithNegativeAngles(){
        int[]from={0,30,20}; 
        int[]to={78,70,5};
        int[]from1={55,0,20};
        int[]to1={70,60,10};
        String color="red";
        String color1="white";
        iceepeecee.addFlight(color,from,to);
        iceepeecee.addFlight(color1,from1,to1);
        iceepeecee.photograph(-1);
        assertFalse(iceepeecee.lastOperationSuccess);
    }
    
    @Test
    public void accordingCMShouldNotNotTakePhotosWithAnglesMoreThan90(){
        int[]from={0,30,20}; 
        int[]to={78,70,5};
        int[]from1={55,0,20};
        int[]to1={70,60,10};
        String color="red";
        String color1="white";
        iceepeecee.addFlight(color,from,to);
        iceepeecee.addFlight(color1,from1,to1);
        iceepeecee.photograph(100);
        assertFalse(iceepeecee.lastOperationSuccess);
    }    
    
    //Metodo flightCamera
    @Test
    public void accordingCMShouldFlightCameraSuccess(){
        int[]from={0,30,20}; 
        int[]to={78,70,5};
        String color="red";
        iceepeecee.addFlight(color,from,to);
        iceepeecee.photograph(48);
        iceepeecee.flightCamera(color);
        assertTrue(iceepeecee.lastOperationSuccess);
    }
    @Test
    public void accordingCMShouldFlightCameraSuccess1(){
        int[]from1={55,0,20};
        int[]to1={70,60,10};
        String color1="white";
        iceepeecee.addFlight(color1,from1,to1);
        iceepeecee.photograph(60);
        iceepeecee.flightCamera(color1);
        assertTrue(iceepeecee.lastOperationSuccess);
    }
    @Test
    public void accordingCMShouldNotFlightCameraPhotoNotExistent(){
        int[]from={0,30,20}; 
        int[]to={78,70,5};
        String color="red";
        iceepeecee.photograph(48);
        iceepeecee.flightCamera(color);
        assertFalse(iceepeecee.lastOperationSuccess);
    }
    @Test
    public void accordingCMShouldNotFlightCameraPhotoNotExistent1(){
        int[]from1={55,0,20};
        int[]to1={70,60,10};
        String color1="white";
        iceepeecee.photograph(60);
        iceepeecee.flightCamera(color1);
        assertFalse(iceepeecee.lastOperationSuccess);
    }
    //CICLO 2
    
    //photograph3
    @Test
    public void accordingCMShouldTakePhotosSuccessFloat(){
        int[]from={0,30,20}; 
        int[]to={78,70,5};
        int[]from1={55,0,20};
        int[]to1={70,60,10};
        String color="red";
        String color1="white";
        iceepeecee.addFlight(color,from,to);
        iceepeecee.addFlight(color1,from1,to1);
        iceepeecee.photograph(40.2f);
        assertTrue(iceepeecee.lastOperationSuccess);
    }
    
    @Test
    public void accordingCMShouldTakePhotosWithDifferentTethaFloat(){        
        
        int[]from={0,30,20}; 
        int[]to={78,70,5};
        int[]from1={55,0,20};
        int[]to1={70,60,10};
        String color="red";
        String color1="white";
        iceepeecee.addFlight(color,from,to);
        iceepeecee.addFlight(color1,from1,to1);
        iceepeecee.photograph(48.6f);
        iceepeecee.photograph(61.3f);
        assertTrue(iceepeecee.lastOperationSuccess);

    }
    
    @Test
    public void accordingCMShouldNotTakePhotosFlightsNotExistentFloat(){
        int[]from={0,30,20}; 
        int[]to={78,70,5};
        String color="red";
        iceepeecee.photograph(48.6f);
        assertFalse(iceepeecee.lastOperationSuccess); 
    }
    
    @Test
    public void accordingCMShouldNotTakePhotosWithNegativeAnglesFloat(){
        int[]from={0,30,20}; 
        int[]to={78,70,5};
        int[]from1={55,0,20};
        int[]to1={70,60,10};
        String color="red";
        String color1="white";
        iceepeecee.addFlight(color,from,to);
        iceepeecee.addFlight(color1,from1,to1);
        iceepeecee.photograph(-30.5f);
        assertFalse(iceepeecee.lastOperationSuccess);
    }
    
    @Test
    public void accordingCMShouldNotNotTakePhotosWithAnglesMoreThan90Float(){
        int[]from={0,30,20}; 
        int[]to={78,70,5};
        int[]from1={55,0,20};
        int[]to1={70,60,10};
        String color="red";
        String color1="white";
        iceepeecee.addFlight(color,from,to);
        iceepeecee.addFlight(color1,from1,to1);
        iceepeecee.photograph(100.9f);
        assertFalse(iceepeecee.lastOperationSuccess);
    }
    
    //Islands
    @Test
    public void accordingCMShouldConsultIslands(){
        int[][]polygon={{20,30},{50,50},{10,50}};
        String color="orange";
        iceepeecee.addIsland(color,polygon);
        iceepeecee.islands();
        assertTrue(iceepeecee.lastOperationSuccess);
    }
    @Test
    public void accordingCMShouldConsultIslands1(){
        int[][]polygon={{20,30},{50,50},{10,50}};
        String color="orange";
        int[][]polygon1={{15,45},{45,45},{25,35}};
        String color1="gray";
        int[][]polygon2={{40,20},{60,10},{75,20},{60,30}};
        String color2="white";
        iceepeecee.addIsland(color,polygon);
        iceepeecee.addIsland(color1,polygon1);
        iceepeecee.addIsland(color2,polygon2);
        iceepeecee.islands();
        assertTrue(iceepeecee.lastOperationSuccess);
    }
    @Test
    public void accordingCMShouldConsultNotExistentIslands(){
        iceepeecee.islands();
        assertTrue(iceepeecee.lastOperationSuccess);
    }  
    //Flights
    @Test
    public void accordingCMShouldConsultFlights(){
        int[]from={0,30,20}; 
        int[]to={78,70,5};
        String color="blue";
        iceepeecee.addFlight(color,from,to);
        iceepeecee.flights();
        assertTrue(iceepeecee.lastOperationSuccess);
    }
    @Test
    public void accordingCMShouldConsultFlights1(){
        int[]from={0,30,20}; 
        int[]to={78,70,5};
        int[]from1={55,0,20};
        int[]to1={70,60,10};
        int[]from2={100,130,20};
        int[]to2={178,170,5};
        String color="blue";
        String color1="pink";
        String color2="green";
        iceepeecee.addFlight(color,from,to);
        iceepeecee.addFlight(color1,from1,to1);
        iceepeecee.addFlight(color2,from2,to2);
        iceepeecee.flights();
        assertTrue(iceepeecee.lastOperationSuccess);
    }
    @Test
    public void accordingCMShouldConsultNotExistentFlights(){
        iceepeecee.flights();
        assertTrue(iceepeecee.lastOperationSuccess);
    }
    
    //observedIslands
    @Test
    public void accordingCMShouldConsultObservedIslandsIsNull(){
        iceepeecee.observedIslands();
        assertTrue(iceepeecee.lastOperationSuccess);
    }
    
    public void accordingCMShouldConsultObservedIslandsSuccess(){        
        int[][]polygon={{20,30},{50,50},{10,50}};
        String color="orange";
        int[][]polygon1={{15,45},{45,45},{25,35}};
        String color1="gray";
        int[]from={0,30,20}; 
        int[]to={78,70,5};
        String color3="blue";
        iceepeecee.addIsland(color,polygon);
        iceepeecee.addIsland(color1,polygon1);
        iceepeecee.addFlight(color3,from,to);
        iceepeecee.photograph(60);
        iceepeecee.observedIslands();
        assertTrue(iceepeecee.lastOperationSuccess);  
    }
    
    public void accordingCMShouldConsultObservedIslandsSuccess1(){
        int[][]polygon={{20,30},{50,50},{10,50}};
        String color="orange";
        int[][]polygon1={{15,45},{45,45},{25,35}};
        String color1="gray";
        int[][]polygon2={{40,20},{60,10},{75,20},{60,30}};
        String color2="white";
        iceepeecee.addIsland(color,polygon);
        iceepeecee.addIsland(color1,polygon1);
        iceepeecee.addIsland(color2,polygon2);
        int[]from={0,30,20}; 
        int[]to={78,70,5};
        int[]from1={55,0,20};
        int[]to1={70,60,10};
        int[]from2={100,130,20};
        int[]to2={178,170,5};
        String color3="blue";
        String color4="pink";
        String color5="green";
        iceepeecee.addFlight(color3,from,to);
        iceepeecee.addFlight(color4,from1,to1);
        iceepeecee.addFlight(color5,from2,to2);
        iceepeecee.observedIslands();
        assertTrue(iceepeecee.lastOperationSuccess);
    }
    
    //uselessFlights
    
    public void accordingCMShouldConsultUselessFlightsIsNull(){
        iceepeecee.uselessFlights();
        assertTrue(iceepeecee.lastOperationSuccess);
    }
    
    public void accordingCMShouldConsultUselessFlightsSuccess(){
        int[][]polygon={{40,20},{60,10},{75,20},{60,30}};
        String color="white";
        iceepeecee.addIsland(color,polygon);
        int[]from={100,130,20};
        int[]to={178,170,5};
        String color1="green";
        iceepeecee.addFlight(color1,from,to);
        iceepeecee.uselessFlights();
        assertTrue(iceepeecee.lastOperationSuccess);
    }
    
    public void accordingCMShouldConsultUselessFlightsSuccess1(){
        int[][]polygon={{20,30},{50,50},{10,50}};
        String color="orange";
        int[][]polygon1={{15,45},{45,45},{25,35}};
        String color1="gray";
        int[][]polygon2={{40,20},{60,10},{75,20},{60,30}};
        String color2="white";
        iceepeecee.addIsland(color,polygon);
        iceepeecee.addIsland(color1,polygon1);
        iceepeecee.addIsland(color2,polygon2);
        int[]from={0,30,20}; 
        int[]to={78,70,5};
        int[]from1={55,0,20};
        int[]to1={70,60,10};
        int[]from2={100,130,20};
        int[]to2={178,170,5};
        String color3="blue";
        String color4="pink";
        String color5="green";
        iceepeecee.addFlight(color3,from,to);
        iceepeecee.addFlight(color4,from1,to1);
        iceepeecee.addFlight(color5,from2,to2);
        iceepeecee.uselessFlights();
        assertTrue(iceepeecee.lastOperationSuccess);
    }
}