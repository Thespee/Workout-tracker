package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

//extends the workout class for workouts that you already did
public class ActiveWorkout extends Workout implements Writable {
    String date; //will have form "YYYY-MM-DD" eg "2021-02-13"

    public ActiveWorkout(String name) {
        super(name);
    }

    public ActiveWorkout(String name, Workout workout) {
        super(name);
        this.name = workout.getName();
        this.workoutPlan = workout.getWorkoutPlan();
        this.date = "";
    }

    //REQUIRES exercise to be in  workout plan int set to be within the range specified when creating the working set
    //MODIFIES this: the working set's weight and reps
    //EFFECTS records the reps and weight for a specific set for a specific exercise
    public void recordExercise(String exercise, int set, int reps, double weight) {
        for (int i = 0; i < workoutPlan.size(); i++) {
            if (exercise == workoutPlan.get(i).getExercise().getName()) {
                workoutPlan.get(i).recordSet(set, reps, weight);
            }
        }
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @Override
    //EFFECTS returns a json object representing an active workout
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("date", date);
        json.put("workoutPlan", toJsonArray(workoutPlan));
        return json;
    }

    @Override
    public JSONArray toJsonArray(ArrayList<WorkingSet> workingSets) {
        JSONArray array = new JSONArray();
        for (WorkingSet ws : workingSets) {
            array.put(ws.toJson());
        }
        return array;
    }
}
