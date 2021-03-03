package persistence;

import model.Workout;
import org.json.JSONObject;

public class WorkoutWriter extends Writer {
    public WorkoutWriter(String source) {
        super(source);
    }

    public void write(Workout w) {
        JSONObject json = w.toJson();
        save(json.toString(TAB));
    }
}
