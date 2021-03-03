package persistence;

import model.Exercise;
import model.WorkingSet;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.ExerciseReader;

import java.io.IOException;

public class WorkingSetReader extends Reader {
    public WorkingSetReader(String source) {
        super(source);
    }

    public WorkingSet read() throws IOException {
        String jsonData = readFile(getSource());
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkingSet(jsonObject);
    }

    public WorkingSet parseWorkingSet(JSONObject jsonObject) {
        ExerciseReader exerciseReader = new ExerciseReader("null");
        Exercise e = exerciseReader.parseExercise(jsonObject.getJSONObject("exercise"));
        int sets = jsonObject.getInt("sets");
        int reps = jsonObject.getInt("reps");
        int rpe = jsonObject.getInt("rpe");
        WorkingSet ws = new WorkingSet(e,sets,reps,rpe);

        JSONArray arr = jsonObject.getJSONArray("workingReps");
        int[] workingReps = new int[arr.length()];
        for (int i = 0; i < arr.length(); i++) {
            workingReps[i] = arr.getInt(i);
        }
        ws.setWorkingReps(workingReps);

        arr = jsonObject.getJSONArray("workingWeight");
        double[] workingWeight = new double[arr.length()];
        for (int i = 0;i < arr.length(); i++) {
            workingWeight[i] = arr.getDouble(i);
        }
        ws.setWorkingWeight(workingWeight);

        return ws;
    }
}
