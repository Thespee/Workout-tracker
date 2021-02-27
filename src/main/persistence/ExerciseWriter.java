package persistence;

import org.json.JSONObject;
import model.Exercise;

import java.io.*;

public class ExerciseWriter extends Writer {

    // EFFECTS: constructs writer to write to destination file
    public ExerciseWriter(String destination) {
        super(destination);
    }

    public void write(Exercise e) {
        JSONObject json = e.toJson();
        save(json.toString(TAB));
    }
}
