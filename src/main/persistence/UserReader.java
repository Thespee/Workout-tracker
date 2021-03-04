package persistence;

import model.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class UserReader extends Reader {
    public UserReader(String source) {
        super(source);
    }

    /*
    //EFFECTS: reads workout from file and returns workout object
    public Workout read() throws IOException {
        String jsonData = readFile(getSource());
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkout(jsonObject);
    }

     */
    public User read() throws IOException {
        String jsonData = readFile(getSource());
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUser(jsonObject);
    }

    /*
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
     */
    public User parseUser(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        User user = new User(name);

        WorkoutReader savedReader = new WorkoutReader("null");
        JSONArray arr = jsonObject.getJSONArray("savedWorkouts");
        for (int i = 0; i < arr.length();i++) {
            user.addSavedWorkout(savedReader.parseWorkout(arr.getJSONObject(i)));
        }

        ActiveWorkoutReader historyReader = new ActiveWorkoutReader("null");
        arr = jsonObject.getJSONArray("workoutHistory");
        for (int i = 0; i < arr.length(); i++) {
            user.addActiveWorkout(historyReader.parseActiveWorkout(arr.getJSONObject(i)));
        }
        return user;
    }
}
