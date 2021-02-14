package model;
//has a name and a list of working sets

import java.util.ArrayList;

public class Workout {
    String name;
    ArrayList<WorkingSet> workoutPlan;

    public Workout(String name) {
        this.name = name;
    }

    //REQUIRES
    //MODIFIES the working set plan
    //EFFECTS either inserts exercise at given position or tacks it onto the end if pos = -1
    public void addToPlan(WorkingSet exercise, int pos) {
        //stub
    }

    //REQUIRES exercise to be in plan
    //MODIFIES the workout plan
    //effects removes the matching exercise from the workout plan
    public void removeFromPlan(Exercise exercise) {
        //stub
    }

    //REQUIRES exercise to be in  workout plan int set to be within the range specified when creating the working set
    //MODIFIES this: the working set's weight and reps
    //EFFECTS records the reps and weight for a specific set for a specific exercise
    public void recordExercise(Exercise exercise, int set, int reps, float weight) {
        //stub
    }

    public ArrayList<WorkingSet> getWorkoutPlan() {
        return workoutPlan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
