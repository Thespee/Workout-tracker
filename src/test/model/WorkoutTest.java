package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WorkoutTest {
    Workout test;

    @BeforeEach
    public void setup() {
        test = new Workout("Push");
    }

    @Test
    public void testAddToPlan() {
        Exercise testExercise = new Exercise("test");
        WorkingSet workingSet1 = new WorkingSet(testExercise, 1, 1, 1);
        WorkingSet workingSet2 = new WorkingSet(testExercise, 2, 1, 1);
        WorkingSet workingSet3 = new WorkingSet(testExercise, 3, 1, 1);
        //add the first one to the end
        test.addToPlan(workingSet1, -1);
        //add the third one to the end
        test.addToPlan(workingSet3,-1);
        //add the second one to the second position
        test.addToPlan(workingSet2,2);

        assertEquals(workingSet1, test.getWorkingSet(1));
        assertEquals(workingSet2, test.getWorkingSet(2));
        assertEquals(workingSet3, test.getWorkingSet(3));
    }

    @Test
    public void testRemoveFromPlan() {
        Exercise testExercise = new Exercise("test");
        WorkingSet workingSet = new WorkingSet(testExercise,1,1,1);
        test.addToPlan(workingSet, -1);
        test.removeFromPlan("test");
        assertEquals(0, test.getWorkoutPlan().size());
    }

    @Test
    public void testReplaceInPlan() {
        Exercise testExercise = new Exercise("test");
        WorkingSet workingSet1 = new WorkingSet(testExercise,1,1,1);
        WorkingSet workingSet2 = new WorkingSet(testExercise,2,2,2);
        test.addToPlan(workingSet1, -1);
        test.replaceInPlan("test", workingSet2);
        assertEquals(2,test.getWorkingSet(1).getSets());
    }
}
