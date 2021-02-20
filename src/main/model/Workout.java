package model;
//has a name and a list of working sets

import java.util.ArrayList;

public class Workout {
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
            if (exerciseName == workoutPlan.get(i).getExercise().getName()) {
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
}