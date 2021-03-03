package ui;

import model.Exercise;
import model.WorkingSet;
import persistence.ExerciseReader;
import persistence.WorkingSetReader;
import persistence.WorkingSetWriter;

import java.io.IOException;

public class fileWrite {
    private static final String JSON_STORE = "./data/testWorkingSet.json";
    private WorkingSetWriter workingSetWriter;
    private WorkingSetReader workingSetReader;
    private Exercise e;
    private WorkingSet wsIn;
    private WorkingSet wsOut;

    public fileWrite() throws IOException {
        this.e = new Exercise("Pushup");
        this.wsIn = new WorkingSet(e,1,2,3);

        this.workingSetWriter = new WorkingSetWriter(JSON_STORE);
        workingSetWriter.open();
        workingSetWriter.write(wsIn);
        workingSetWriter.close();

        workingSetReader = new WorkingSetReader(JSON_STORE);
        wsOut = workingSetReader.read();
        System.out.println("Loaded: " + wsOut.getExercise().getName());
    }
}
