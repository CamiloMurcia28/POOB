package ColonyTest;
import domain.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The test class ColonyTestTest.
 *
 * @author  Jeisson Casallas & Camilo Murcia
 * @version 1.0
 */
public class ColonyTest{
    private Colony colony;
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp(){
        colony=new Colony();
    }
    
    //CICLO 1
    @Test
    public void ShouldFlikAndMoltInitialPosition(){
        Ant flik= new Ant(colony, 10, 10);
        Ant molt= new Ant(colony, 15, 15);
        boolean flik1= colony.getEntity(10,10) instanceof Entity;
        boolean molt2= colony.getEntity(15,15) instanceof Entity;
        assertTrue(flik1);
        assertTrue(molt2);
    }
    @Test
    public void shouldMoveOfTheInitialPosition() {
        Ant flik= new Ant(colony, 10, 10);
        Ant molt= new Ant(colony, 15, 15);
        colony.ticTac();
        assertFalse(colony.getEntity(10,10) instanceof Entity);
        assertFalse(colony.getEntity(15,15) instanceof Entity);
    }

    @Test
    public void ShouldChangeAntStateAfter50TicTacs(){
        Ant flik= new Ant(colony, 10, 10);
        Ant molt= new Ant(colony, 15, 15);
        for (int i = 0; i < 50; i++) {
            colony.ticTac();
        }
        for (int r = 0; r < colony.getLength(); r++) {
            for (int c = 0; c < colony.getLength(); c++) {
                Entity entity = colony.getEntity(r, c);
                if (entity instanceof Ant) {
                    assertFalse((flik).isAlive());
                    assertFalse((molt).isAlive());
                }
            }
        }
    }
    @Test
    public void ShouldIncrementAgeTicTac(){
        Ant flik= new Ant(colony, 10, 10);
        for (int i = 0; i < 20; i++) {
            colony.ticTac();
        }
        for (int r = 0; r < colony.getLength(); r++) {
            for (int c = 0; c < colony.getLength(); c++) {
                Entity entity = colony.getEntity(r, c);
                if (entity instanceof Ant) {
                    assertTrue(flik.getAge()>0);
                }
            }
        }
    }
    
    //CICLO 2
    @Test
    public void ShouldExistFood(){
        assertTrue(colony.getEntity(0,0) instanceof Food);
        assertTrue(colony.getEntity(0,1) instanceof Food);
        assertTrue(colony.getEntity(0,2) instanceof Food);
        assertTrue(colony.getEntity(1,0) instanceof Food);
        assertTrue(colony.getEntity(1,1) instanceof Food);   
    }
    @Test
    public void shouldNotExistFood() {
        HungryAnt rachel= new HungryAnt(colony,9,9);
        HungryAnt monica= new HungryAnt(colony,8,8);
        for (int i = 0; i < 50; i++) {
            colony.ticTac();
        }
        for (int r = 0; r < colony.getLength(); r++) {
            for (int c = 0; c < colony.getLength(); c++) {
                Entity entity = colony.getEntity(r, c);
                assertFalse(entity instanceof Food);
            }
        }
    }
    
    //CICLO 3
    @Test
    public void shouldBeClosed() {
        Flower rose = new Flower(colony, 29, 0);
        Flower violet = new Flower(colony, 29, 29);
        colony.ticTac();
        assertFalse(rose.isOpen());
        assertFalse(violet.isOpen());

    }
    @Test
    public void shouldBeOpen() {
        Flower rose= new Flower(colony,29,0);
        Flower violet= new Flower(colony,29,29);
        colony.ticTac();
        colony.ticTac();
        assertTrue((rose).isOpen());
        assertTrue((violet).isOpen());
    
    }
    
    //CICLO 4
    @Test
    public void ShouldDieWhenEatAnotherAnt(){
        Ant flik= new Ant(colony, 10, 10);
        CannibalAnt murcia= new CannibalAnt(colony,10,9);
        for (int i = 0; i < 10; i++) {
            colony.ticTac();
        }
        assertTrue(murcia.hasEaten());
        assertFalse(murcia.isAlive());
    }
    @Test
    public void ShouldDieAfter15TicTacs(){
        CannibalAnt jeisson= new CannibalAnt(colony,29,29);
        for (int i = 0; i < 15; i++) {
            colony.ticTac();
        }
        assertFalse(jeisson.isAlive());
        assertFalse(jeisson.hasEaten());
    }
    
    //CICLO 5
    @Test
    public void shouldBeCannibalPoisoned(){
        Ant flik= new Ant(colony, 5,5);
        CannibalAnt murcia= new CannibalAnt(colony,29,29);
        Poison veneno0 = new Poison(colony, 28,29);
        Poison veneno1 = new Poison(colony, 29,28);
        Poison veneno2 = new Poison(colony, 28,28);
        colony.ticTac();
        assertTrue(murcia.isPoison());
        assertTrue(murcia.nextState == Agent.ALIVE);
        }
    
    @Test
    public void shouldBeDeadByPoison() {
        Ant flik= new Ant(colony, 10, 10);
        Poison veneno0 = new Poison(colony, 9,9);
        Poison veneno1 = new Poison(colony, 10,9);
        Poison veneno2 = new Poison(colony, 11,9);
        Poison veneno3 = new Poison(colony, 9,10);
        Poison veneno4 = new Poison(colony, 11,10);
        Poison veneno5 = new Poison(colony, 9,11);
        Poison veneno6 = new Poison(colony, 10,11);
        Poison veneno7 = new Poison(colony, 11,11);
        colony.ticTac();
        assertFalse(flik.isAlive());
    }
}
