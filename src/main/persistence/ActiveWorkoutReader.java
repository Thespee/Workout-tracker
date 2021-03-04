package persistence;

import model.ActiveWorkout;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class ActiveWorkoutReader extends Reader {
    public ActiveWorkoutReader(String source) {
        super(source);
    }

    //EFFECTS: reads workout from file and returns workout object
    public ActiveWorkout read() throws IOException {
        String jsonData = readFile(getSource());
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseActiveWorkout(jsonObject);
    }


    //EFFECTS: parses a JSON object into a workout object
    public ActiveWorkout parseActiveWorkout(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        ActiveWorkout aw = new ActiveWorkout(name);

        String date = jsonObject.getString("date");
        aw.setDate(date);

        WorkingSetReader workingSetReader = new WorkingSetReader("null");
        JSONArray arr = jsonObject.getJSONArray("workoutPlan");
        for (int i = 0; i < arr.length();i++) {
            aw.addToPlan(workingSetReader.parseWorkingSet(arr.getJSONObject(i)), + 1);
        }
        return aw;
    }
}
