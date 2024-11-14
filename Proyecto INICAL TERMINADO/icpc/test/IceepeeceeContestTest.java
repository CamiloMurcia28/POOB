package test;
import iceepeecee.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class IceepeeceeContestTest {
    private IceepeeceeContest contest;

    @Before
    public void setUp() {
        contest = new IceepeeceeContest();
    }

    @Test
    public void accordingCMShouldSolveExampleInput1() {
        int[][][] islands = {{{45,60},{55,55},{60,60},{55,65}}};
        int[][][] flights ={{{0,30,20},{78,70,5}}};

        float result = contest.solve(islands, flights);
        assertEquals(34.21260,result, 0.00001);
    }

    @Test
    public void accordingCMShouldSolveExampleInput2() {
        int[][][] islands = {{{20,30},{50,50},{10,50}},{{45,60},{55,55},{60,60},{55,65}}};
        int[][][] flights ={{{0,30,20},{78,70,5}},{{55,0,20},{70,60,10}}};

        float result = contest.solve(islands, flights);
        assertEquals(38.03618, result, 0.00001);
    }

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
    
    @Test
    public void accordingCMShouldNotSolveTheProblem2(){
        int[][][] islands = {{{20,30},{50,50},{10,50}},{{40,20},{60,10},{75,20},{60,30}},{{45,60},{55,55},{60,60},{55,65}}};
        int[][][] flights ={{{},{}}};
        float result = contest.solve(islands, flights);
        assertTrue(result==-1.0f);
    }
    
}