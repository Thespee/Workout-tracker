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

    //REQUIRES exercise to be in  workout plan int set to be within the range specified when creating the working set
    //MODIFIES this: the working set's weight and reps
    //EFFECTS records the reps and weight for a specific set for a specific exercise
    public void recordExercise(String exercise, int set, int reps, double weight) {
        //stub
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}
