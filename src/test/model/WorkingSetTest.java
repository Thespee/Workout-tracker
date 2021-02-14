package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WorkingSetTest {
    WorkingSet test;

    @BeforeEach
    public void setup() {
        Exercise testExercise = new Exercise("test");
        test = new WorkingSet(testExercise, 3, 10, 8);
    }

    @Test
    public void testRecordSet() {
        test.recordSet(1, 8,20.0);
        assertEquals(8,test.getWorkingReps(1));
        assertEquals(20.0, test.getWorkingWeight(1));
    }
}
