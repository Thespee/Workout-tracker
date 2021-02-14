package model;

//extends the workout class for workouts that you already did
public class ActiveWorkout extends Workout {
    String date; //will have form "YYYY-MM-DD" eg "2021-02-13"

    public ActiveWorkout(String name) {
        super(name);
    }
}
