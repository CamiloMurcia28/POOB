import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

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
        assertEquals(37, result, 0.01); // The expected result should be within 0.01 of the actual result
    }

    @Test
    public void accordingCMShouldSolveExampleInput2() {
        int[][][] islands = {{{20,30},{50,50},{10,50}},{{45,60},{55,55},{60,60},{55,65}}};
        int[][][] flights ={{{0,30,20},{78,70,5}},{{55,0,20},{70,60,10}}};

        float result = contest.solve(islands, flights);
        assertEquals(41, result, 0.01); // The expected result should be within 0.01 of the actual result
    }

    @Test
    public void aacordingCMShouldSolveWithExampleInput3() {
        int[][][] islands = {{{20,30},{50,50},{10,50}},{{40,20},{60,10},{75,20},{60,30}},{{45,60},{55,55},{60,60},{55,65}}};
        int[][][] flights ={{{0,30,20},{78,70,5}},{{55,0,20},{70,60,10}},{{100,130,20},{178,170,5}}};

        float result = contest.solve(islands, flights);
        assertEquals(49, result, 0.01); // The expected result should be within 0.01 of the actual result
    }
}
