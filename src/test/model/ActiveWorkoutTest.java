package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ActiveWorkoutTest {
    ActiveWorkout test;

    @BeforeEach
    public  void setup() {
        this.test = new ActiveWorkout("test");
    }

    @Test
    public void testRecordExercise() {
        Exercise testExercise = new Exercise("test");
        WorkingSet workingSet = new WorkingSet(testExercise,1,1,1);
        test.addToPlan(workingSet, -1);
        test.recordExercise("test", 1, 5,10.0);
        assertEquals(5, test.getWorkingSet(1).getWorkingReps(1));
    }
}
