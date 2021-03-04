package persistence;

import model.ActiveWorkout;
import org.json.JSONObject;

public class ActiveWorkoutWriter extends Writer {
    public ActiveWorkoutWriter(String source) {
        super(source);
    }

    public void write(ActiveWorkout aw) {
        JSONObject json = aw.toJson();
        save(json.toString(TAB));
    }
}
