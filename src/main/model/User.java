package model;
//will have a history (list) of past workout and a list of stored workouts

import java.util.ArrayList;

public class User {
    String name;
    ArrayList<ActiveWorkout> workoutHistory;
    ArrayList<Workout> savedWorkouts;
}
