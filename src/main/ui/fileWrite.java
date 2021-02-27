package ui;

import model.Exercise;
import persistence.ExerciseReader;
import persistence.ExerciseWriter;

import java.io.IOException;

public class fileWrite {
    private static final String JSON_STORE = "./data/test.json";
    private ExerciseWriter exerciseWriter;
    private ExerciseReader exerciseReader;
    private Exercise eIn;
    private Exercise eOut;

    public fileWrite() throws IOException {
        this.eIn = new Exercise("Pushup");

        this.exerciseWriter = new ExerciseWriter(JSON_STORE);
        exerciseWriter.open();
        exerciseWriter.write(eIn);
        exerciseWriter.close();

        exerciseReader = new ExerciseReader(JSON_STORE);
        eOut = exerciseReader.read();
        System.out.println("Loaded: " + eOut.getName());
    }
}
