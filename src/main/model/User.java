package model;
//will have a history (list) of past workout and a list of stored workouts

import java.util.ArrayList;

public class User {
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
        //stub
    }

    //REQUIRES
    //MODIFIES the array list of saved workouts
    //EFFECTS saves this workout to the user's preset workouts
    public void addSavedWorkout(Workout workout) {
        //stub
    }

    //REQUIRES
    //MODIFIES
    //EFFECTS returns the last instance of doing a specific exercise, false if it's never been done before
    public WorkingSet lastTimeExercise(String exercise) {
        //stub
        return null;
    }

    //REQUIRES name to be the name of a saved workout
    //MODIFIES workout history
    //EFFECTS add an entry to workout histroy from the list of saved ones
    public void startSavedWorkout(String name) {
        //stub
    }

    public ArrayList<Workout> getSavedWorkouts() {
        return  savedWorkouts;
    }

    public ArrayList<ActiveWorkout> getWorkoutHistory() {
        return workoutHistory;
    }

}
