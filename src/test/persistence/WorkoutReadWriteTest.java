package persistence;

import model.Exercise;
import model.WorkingSet;
import model.Workout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkoutReadWriteTest {
    protected WorkoutReader reader;
    protected WorkoutWriter writer;
    public static final String JSON_STORE = "./data/persistence tests/test.json";

    @BeforeEach
    void testConstructor() {
        writer = new WorkoutWriter(JSON_STORE);
        try {
            writer.open();
        } catch (FileNotFoundException e) {
            fail("Could not find source");
        }
        assertEquals(JSON_STORE, writer.getDestination());

        reader = new WorkoutReader(JSON_STORE);
        try{
            reader.readFile(JSON_STORE);
        } catch (IOException e) {
            fail("error reading file");
        }
    }

    @Test
    void testNullFile() {
        writer = new WorkoutWriter(".data/null.json");
        try {
            writer.open();
            fail("should throw exception");
        } catch (FileNotFoundException e) {
            assertTrue(e.getMessage().contains("(The system cannot find the path specified)"));
        }

        reader = new WorkoutReader(".data/null.json");
        try {
            reader.readFile(".data/null.json");
            fail("Should throw exception");
        } catch (IOException e) {
            assertTrue(e.getMessage().contains(".data"));
        }
    }

    @Test
    void testReadWrite() {
        Exercise e = new Exercise("Push up");
        WorkingSet ws1 = new WorkingSet(e,1 ,2,3);
        Exercise e2 = new Exercise("Pull up");
        WorkingSet ws2 = new WorkingSet(e2,4,5,6);
        Workout in = new Workout("Push");
        in.addToPlan(ws2,-1);
        in.addToPlan(ws1,-1);

        Workout out = null;
        writer.write(in);
        writer.close();
        try {
            out = reader.read();
        } catch (IOException exception) {
            fail("should not throw exception");
        }
        assertEquals("Push", out.getName());
        assertEquals(2, out.getWorkoutPlan().size());
        assertEquals("Push up", out.getWorkingSet(1).getExercise().getName());
        assertEquals("Pull up", out.getWorkingSet(2).getExercise().getName());
    }
}
