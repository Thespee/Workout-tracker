package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ExerciseTest {
    Exercise test;

    @BeforeEach
    public void setup() {
        ArrayList<String> primary = new ArrayList<>();
        primary.add("primary1");
        primary.add("primary2");
        ArrayList<String> secondary = new ArrayList<>();
        secondary.add("secondary1");
        secondary.add("secondary2");

        test = new Exercise("tester", primary, secondary);
    }

    @Test
    public void testWorksPrimary() {
        assertTrue(test.worksPrimary("primary1"));
        assertTrue(test.worksPrimary("primary2"));
        assertFalse(test.worksPrimary("not in list"));
    }

    @Test
    public void testWorksSecondary() {
        assertTrue(test.worksSecondary("secondary1"));
        assertTrue(test.worksSecondary("secondary2"));
        assertFalse(test.worksSecondary("not in list"));
    }
}
