package persistence;
import model.Exercise;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.ExerciseReader;
import persistence.ExerciseWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ExerciseReadWriteTest {
    protected ExerciseReader reader;
    protected ExerciseWriter writer;
    public static final String JSON_STORE = "./data/persistence tests/test.json";

    @BeforeEach
    void testConstructor() {
        writer = new ExerciseWriter(JSON_STORE);
        try {
            writer.open();
        } catch (FileNotFoundException e) {
            fail("Could not find source");
        }
        assertEquals(JSON_STORE, writer.getDestination());

        reader = new ExerciseReader(JSON_STORE);
        try{
            reader.readFile(JSON_STORE);
        } catch (IOException e) {
            fail("error reading file");
        }
    }

    @Test
    void testNullFile() {
        writer = new ExerciseWriter(".data/null.json");
        try {
            writer.open();
            fail("should throw exception");
        } catch (FileNotFoundException e) {
            assertTrue(e.getMessage().contains("(The system cannot find the path specified)"));
        }

        reader = new ExerciseReader(".data/null.json");
        try {
            reader.readFile(".data/null.json");
            fail("Should throw exception");
        } catch (IOException e) {
            assertTrue(e.getMessage().contains(".data"));
        }
    }

    @Test
    void testReadWrite() {
        Exercise in = new Exercise("Push up");
        Exercise out = null;
        writer.write(in);
        writer.close();
        try {
            out = reader.read();
        } catch (IOException e) {
            fail("should not throw exception");
        }
        assertEquals("Push up", out.getName());
    }
}
