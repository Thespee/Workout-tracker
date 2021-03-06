package model;
//will have a history (list) of past workout and a list of stored workouts

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

public class User implements Writable {
    String name;
    ArrayList<ActiveWorkout> workoutHistory;
    ArrayList<Workout> savedWorkouts;

    public User(String name) {
        this.name = name;
        this.workoutHistory = new ArrayList<>();
        this.savedWorkouts = new ArrayList<>();
    }

    //REQUIRES
    //MODIFIES the array list of workouts
    //EFFECTS records workout in user's history
    public void addActiveWorkout(ActiveWorkout workout) {
        workoutHistory.add(workout);
    }

    //REQUIRES
    //MODIFIES the array list of saved workouts
    //EFFECTS saves this workout to the user's preset workouts
    public void addSavedWorkout(Workout workout) {
        savedWorkouts.add(workout);
    }

    //REQUIRES
    //MODIFIES
    //EFFECTS returns the last instance of doing a specific exercise, null if it's never been done before
    public WorkingSet lastTimeExercise(String exercise) {
        //go though all ast workouts except for last one (ie the one we're working on at the time)
        //System.out.println("Size: " + workoutHistory.size());
        for (int i = 0; i < workoutHistory.size() - 1; i++) {

            ArrayList<WorkingSet> currentWorkout = workoutHistory.get(i).getWorkoutPlan();
            //loops through every exercise in the workout
            for (int j = 0; j < currentWorkout.size(); j++) {
                if (exercise == currentWorkout.get(j).getExercise().getName()) {
                    return currentWorkout.get(j);
                }
            }
        }
        //if it doesn't find it, return null
        return null;
    }

    //REQUIRES name to be the name of a saved workout
    //MODIFIES workout history
    //EFFECTS add an entry to workout history from the list of saved ones
    public void startSavedWorkout(String name) {
        ActiveWorkout temp = new ActiveWorkout("temp");
        for (int i = 0; i < savedWorkouts.size(); i++) {
            if (name.equals(savedWorkouts.get(i).getName())) {
                temp = new ActiveWorkout(name, savedWorkouts.get(i));
            }
        }
        this.addActiveWorkout(temp);
    }

    public ArrayList<Workout> getSavedWorkouts() {
        return  savedWorkouts;
    }

    public ArrayList<ActiveWorkout> getWorkoutHistory() {
        return workoutHistory;
    }

    public String getName() {
        return name;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("savedWorkouts", savedToJsonArray(savedWorkouts));
        json.put("workoutHistory", historyToJsonArray(workoutHistory));
        return json;
    }

    //EFFECTS returns a json array of Workout history
    public JSONArray historyToJsonArray(ArrayList<ActiveWorkout> workouts) {
        JSONArray array = new JSONArray();
        for (ActiveWorkout aw : workouts) {
            array.put(aw.toJson());
        }
        return array;
    }

    //EFFECTS returns a json array of saved workouts
    public JSONArray savedToJsonArray(ArrayList<Workout> workouts) {
        JSONArray array = new JSONArray();
        for (Workout w : workouts) {
            array.put(w.toJson());
        }
        return array;
    }
}
