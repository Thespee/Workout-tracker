package persistence;

import model.Workout;
import org.json.*;

import java.io.IOException;

public class WorkoutReader extends Reader {
    public WorkoutReader(String source) {
        super(source);
    }

    //EFFECTS: reads workout from file and returns workout object
    public Workout read() throws IOException {
        String jsonData = readFile(getSource());
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkout(jsonObject);
    }


    //EFFECTS: parses a JSON object into a workout object
    public Workout parseWorkout(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Workout w = new Workout(name);

        WorkingSetReader workingSetReader = new WorkingSetReader("null");
        JSONArray arr = jsonObject.getJSONArray("workoutPlan");
        for (int i = 0; i < arr.length();i++) {
            w.addToPlan(workingSetReader.parseWorkingSet(arr.getJSONObject(i)), + 1);
        }
        return w;
    }
}
