package persistence;

import model.Exercise;
import model.WorkingSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class WorkingSetReadWriteTest {
    protected WorkingSetReader reader;
    protected WorkingSetWriter writer;
    public static final String JSON_STORE = "./data/persistence tests/test.json";

    @BeforeEach
    void testConstructor() {
        writer = new WorkingSetWriter(JSON_STORE);
        try {
            writer.open();
        } catch (FileNotFoundException e) {
            fail("Could not find source");
        }
        assertEquals(JSON_STORE, writer.getDestination());

        reader = new WorkingSetReader(JSON_STORE);
        try{
            reader.readFile(JSON_STORE);
        } catch (IOException e) {
            fail("error reading file");
        }
    }

    @Test
    void testNullFile() {
        writer = new WorkingSetWriter(".data/null.json");
        try {
            writer.open();
            fail("should throw exception");
        } catch (FileNotFoundException e) {
            assertTrue(e.getMessage().contains("(The system cannot find the path specified)"));
        }

        reader = new WorkingSetReader(".data/null.json");
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
        WorkingSet in = new WorkingSet(e,1 ,2,3);
        WorkingSet out = null;
        writer.write(in);
        writer.close();
        try {
            out = reader.read();
        } catch (IOException exception) {
            fail("should not throw exception");
        }
        assertEquals("Push up", out.getExercise().getName());
        assertEquals(1, out.getSets());
        assertEquals(2,out.getReps());
    }
}
