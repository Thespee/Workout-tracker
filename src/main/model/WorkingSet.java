package model;

public class WorkingSet {
    Exercise exercise;
    int sets;
    int reps;
    int repRange;
    int[] workingReps;
    float[] workingWeight;

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
        this.workingWeight = new float[sets];
    }

    //constructor when reps fall into range (eg 12-15)
    public WorkingSet(Exercise exercise, int sets, int reps, int repRange, int rpeNum) {
        this.exercise = exercise;
        this.sets = sets;
        this.reps = reps;
        this.rpe = rpeNum;
        this.repRange = repRange;
        this.workingReps = new int[sets];
        this.workingWeight = new float[sets];
    }

    //REQUIRES 0 < set <= # of sets for this object
    //MODIFIES working reps and weight
    //EFFECTS records a set in terms of how many reps were preformed at what weight
    public void recordSet(int set, int reps, float weight) {
        this.workingReps[set - 1] = reps;
        this.workingWeight[set - 1] = weight;
    }
}
