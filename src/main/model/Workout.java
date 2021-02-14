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

    //REQUIRES int set to be within the range specified when creating the working set
    //MODIFIES this: the working set's weight and reps
    //EFFECTS records the reps and weight for a specific set for a specific exercise
    public void recordExercise(Exercise exercise, int set, int reps, float weight) {
        //stub
    }
}
