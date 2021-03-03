package model;

import org.json.JSONArray;
import org.json.JSONObject;

public class WorkingSet implements Writable {
    Exercise exercise;
    int sets;
    int reps;
    int repRange;
    int[] workingReps;
    double[] workingWeight;

    //not actually relevant to the code, but RPE stands for Rated Perceived Exertion. Either falls on a scale from
    //0-10 where a 6 mean you have 4 repetitions in reserve, 8 would mean 2, and 10 would mean working out until failure
    // important for progressive overload but I don't want to explain why that's good
    int rpe;

    //constructor when reps are fixed (eg 15)
    public WorkingSet(Exercise exercise, int sets, int reps, int rpeNum) {
        this.exercise = exercise;
        this.sets = sets;
        this.reps = reps;
        this.rpe = rpeNum;
        this.repRange = 0;
        this.workingReps = new int[sets];
        this.workingWeight = new double[sets];
    }

    //constructor when reps fall into range (eg 12-15)
    public WorkingSet(Exercise exercise, int sets, int reps, int repRange, int rpeNum) {
        this.exercise = exercise;
        this.sets = sets;
        this.reps = reps;
        this.rpe = rpeNum;
        this.repRange = repRange;
        this.workingReps = new int[sets];
        this.workingWeight = new double[sets];
    }

    //REQUIRES 0 < set <= # of sets for this object
    //MODIFIES working reps and weight
    //EFFECTS records a set in terms of how many reps were preformed at what weight
    public void recordSet(int set, int reps, double weight) {
        this.workingReps[set - 1] = reps;
        this.workingWeight[set - 1] = weight;
    }

    //MODIFIES this
    //EFFECTS sets the working reps array to passed in array
    public void setWorkingReps(int[] i) {
        workingReps = i;
    }

    //MODIFIES this
    //EFFECTS sets the working weight array to passed in array
    public void setWorkingWeight(double[] d) {
        workingWeight = d;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }

    public int getRepRange() {
        return repRange;
    }

    public int getRpe() {
        return rpe;
    }

    public int[] getAllWorkingReps() {
        return workingReps;
    }

    //requires set to be within set range (1-this.set)
    public int getWorkingReps(int set) {
        return workingReps[set - 1];
    }


    public double[] getAllWorkingWeight() {
        return workingWeight;
    }

    //REQUIRES set to be within set range (1-this.set)
    public double getWorkingWeight(int set) {
        return workingWeight[set - 1];
    }

    //EFFECTS returns a JSON object representing a working set
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("exercise", exercise.toJson());
        json.put("sets", sets);
        json.put("reps", reps);
        json.put("rpe", rpe);
        json.put("workingReps", toJsonArray(workingReps));
        json.put("workingWeight", toJsonArray(workingWeight));
        return json;
    }

    //EFFECTS returns a JSONArray of a int array
    private JSONArray toJsonArray(int[] array) {
        JSONArray jsonArray = new JSONArray();
        for (int i : array) {
            jsonArray.put(i);
        }
        return jsonArray;
    }

    //EFFECTS returns a JSONArray of a double array
    private JSONArray toJsonArray(double[] array) {
        JSONArray jsonArray = new JSONArray();
        for (double d : array) {
            jsonArray.put(d);
        }
        return jsonArray;
    }
}
