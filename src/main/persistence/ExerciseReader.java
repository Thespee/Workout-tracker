package persistence;

import model.Exercise;

import java.io.IOException;

import org.json.*;

public class ExerciseReader extends Reader {
    // EFFECTS: constructs reader to read from source file
    public ExerciseReader(String source) {
        super(source);
    }

    public Exercise read() throws IOException {
        String jsonData = readFile(getSource());
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseExercise(jsonObject);
    }

    // EFFECTS: parses exercise from JSON
    public Exercise parseExercise(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Exercise e = new Exercise(name);
        return e;
    }

}
