package model;

//extends the workout class for workouts that you already did
public class ActiveWorkout extends Workout {
    String date; //will have form "YYYY-MM-DD" eg "2021-02-13"

    public ActiveWorkout(String name) {
        super(name);
    }

    public ActiveWorkout(String name, String date, Workout workout) {
        super(name);
        this.date = date;
        //TODO add in thing to turn workout into active workout
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}
