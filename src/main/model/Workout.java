package model;
//has a name and a list of working sets

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

public class Workout implements Writable {
    String name;
    ArrayList<WorkingSet> workoutPlan;

    public Workout(String name) {
        this.name = name;
        workoutPlan = new ArrayList<>();
        workoutPlan.clear();
    }

    //REQUIRES
    //MODIFIES the working set plan
    //EFFECTS either inserts exercise at given position or tacks it onto the end if pos = -1
    public void addToPlan(WorkingSet exercise, int pos) {
        if (pos < 0) {
            workoutPlan.add(exercise);
        } else {
            workoutPlan.add(pos - 1,exercise);
        }
    }

    //REQUIRES exercise to be in plan
    //MODIFIES the workout plan
    //effects removes the matching exercise from the workout plan
    public void removeFromPlan(String exerciseName) {
        for (int i = 0; i < workoutPlan.size();i++) {
            if (exerciseName.equals(workoutPlan.get(i).getExercise().getName())) {
                workoutPlan.remove(i);
            }
        }
    }

    public ArrayList<WorkingSet> getWorkoutPlan() {
        return workoutPlan;
    }

    public WorkingSet getWorkingSet(int pos) {
        return workoutPlan.get(pos - 1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //REQUIRES toRemove be an exercise in the workout
    //MODIFIES this
    //EFFECTS replaces exercise toRemove with toAdd
    public void replaceInPlan(String toRemove, WorkingSet toAdd) {
        for (int i = 0; i < workoutPlan.size();i++) {
            if (toRemove.equals(workoutPlan.get(i).getExercise().getName())) {
                workoutPlan.remove(i);
                this.addToPlan(toAdd, i + 1);
            }
        }
    }

    @Override
    //EFFECTS returns a json object representation of a workout
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("workoutPlan", toJsonArray(workoutPlan));
        return json;
    }

    //EFFECTS returns a JSONArray of the workout plan
    public JSONArray toJsonArray(ArrayList<WorkingSet> workingSets) {
        JSONArray array = new JSONArray();
        for (WorkingSet ws : workingSets) {
            array.put(ws.toJson());
        }
        return array;
    }

    @Override
    public String toString() {
        String workoutString = "";
        workoutString += name;
        for (WorkingSet ws : workoutPlan) {
            workoutString += "<br/>";
            workoutString += ws.toString();
        }

        return workoutString;
    }
}
