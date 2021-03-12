package persistence;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserReadWriteTest {
    protected UserReader reader;
    protected UserWriter writer;
    public static final String JSON_STORE = "./data/persistence tests/test.json";

    @BeforeEach
    void testConstructor() {
        writer = new UserWriter(JSON_STORE);
        try {
            writer.open();
        } catch (FileNotFoundException e) {
            fail("Could not find source");
        }
        assertEquals(JSON_STORE, writer.getDestination());

        reader = new UserReader(JSON_STORE);
        try{
            reader.readFile(JSON_STORE);
        } catch (IOException e) {
            fail("error reading file");
        }
    }

    @Test
    void testNullFile() {
        writer = new UserWriter(".data/null.json");
        try {
            writer.open();
            fail("should throw exception");
        } catch (FileNotFoundException e) {
            assertTrue(e.getMessage().contains("(The system cannot find the path specified)"));
        }

        reader = new UserReader(".data/null.json");
        try {
            reader.readFile(".data/null.json");
            fail("Should throw exception");
        } catch (IOException e) {
            assertTrue(e.getMessage().contains(".data"));
        }
    }

    @Test
    void testReadWrite() {
        Workout wo1 = new Workout("Workout 1");
        Workout wo2 = new Workout("Workout 2");
        User in = new User("Joe");
        in.addSavedWorkout(wo1);
        in.addSavedWorkout(wo2);
        ActiveWorkout activeWorkout1 = new ActiveWorkout("First Workout", wo1);
        ActiveWorkout activeWorkout2 = new ActiveWorkout("Second Workout", wo2);
        in.addActiveWorkout(activeWorkout1);
        in.addActiveWorkout(activeWorkout2);
        User out = null;
        writer.write(in);
        writer.close();
        try {
            out = reader.read();
        } catch (IOException exception) {
            fail("should not throw exception");
        }
        assertEquals("Joe", out.getName());
        assertEquals(2, out.getSavedWorkouts().size());
        assertEquals("Workout 1", out.getSavedWorkouts().get(0).getName());
        assertEquals("Workout 2", out.getSavedWorkouts().get(1).getName());
        assertEquals("Workout 1", out.getWorkoutHistory().get(0).getName());
        assertEquals("Workout 2", out.getWorkoutHistory().get(1).getName());
    }
}
