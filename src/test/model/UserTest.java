package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    User test;

    @BeforeEach
    public void setup() {
        test = new User("John");
    }

    @Test
    public void testAddActiveWorkout() {
        Exercise exercise = new Exercise("test");
        WorkingSet workingSet = new WorkingSet(exercise,1,1,1);
        ActiveWorkout activeWorkout = new ActiveWorkout("Push");
        activeWorkout.addToPlan(workingSet, -1);
        test.addActiveWorkout(activeWorkout);
        assertTrue(test.getWorkoutHistory().contains(activeWorkout));
    }

    @Test
    public void testAddSavedWorkout() {
        Exercise exercise = new Exercise("test");
        WorkingSet workingSet = new WorkingSet(exercise,1,1,1);
        Workout workout = new Workout("Push");
        workout.addToPlan(workingSet, -1);
        test.addSavedWorkout(workout);
        assertTrue(test.getSavedWorkouts().contains(workout));
    }

    @Test
    public void testLastTimeExercise() {
        Exercise exercise = new Exercise("test");
        WorkingSet workingSet1 = new WorkingSet(exercise,1,1,1);
        WorkingSet workingSet2 = new WorkingSet(exercise,2,2,2);
        ActiveWorkout activeWorkout = new ActiveWorkout("Push");
        activeWorkout.addToPlan(workingSet1, -1);
        activeWorkout.addToPlan(workingSet2, -1);
        test.addActiveWorkout(activeWorkout);

        //should return the working set of the most recent time we did exercise "test"
        assertEquals(workingSet2, test.lastTimeExercise("test"));

    }

    @Test
    public void testStartSavedWorkout() {
        Exercise exercise = new Exercise("test");
        WorkingSet workingSet = new WorkingSet(exercise,1,1,1);
        Workout workout = new Workout("Push");
        workout.addToPlan(workingSet, -1);
        test.addSavedWorkout(workout);
        test.startSavedWorkout("Push");
        assertTrue(test.getWorkoutHistory().contains(workout));
    }
}
