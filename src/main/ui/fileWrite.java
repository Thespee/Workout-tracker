package ui;

import model.Exercise;
import model.WorkingSet;
import model.Workout;
import persistence.WorkingSetReader;
import persistence.WorkoutReader;
import persistence.WorkoutWriter;

import java.io.IOException;

public class fileWrite {
    private static final String JSON_STORE = "./data/testWorkout.json";
    private WorkoutWriter workoutWriter;
    private WorkoutReader workoutReader;


    public fileWrite() throws IOException {
        Exercise e1 = new Exercise("Pushup");
        Exercise e2 = new Exercise("Pullup");
        WorkingSet ws1 = new WorkingSet(e1,1,2,3);
        WorkingSet ws2 = new WorkingSet(e2,4,5,6);
        Workout w = new Workout("test");
        w.addToPlan(ws1,-1);
        w.addToPlan(ws2,-1);

        this.workoutWriter = new WorkoutWriter(JSON_STORE);
        workoutWriter.open();
        workoutWriter.write(w);
        workoutWriter.close();

        workoutReader = new WorkoutReader(JSON_STORE);
        Workout wOut = workoutReader.read();
        System.out.println("Loaded: " + wOut.getName());

    }
}
