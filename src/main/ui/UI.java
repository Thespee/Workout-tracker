package ui;

import model.*;
import java.io.*;

public class UI {
    BufferedReader consolIn;
    User user1;
    final int exitcode = 6;

    public UI() throws IOException {
        this.consolIn = new BufferedReader(new InputStreamReader(System.in));
        this.createUser();
        this.menu();
    }

    //REQUIRES
    //MODIFIES this
    //EFFECTS creates a new user with an entered name
    private void createUser() throws IOException {
        System.out.println("Hello, please enter your name: ");
        String name = consolIn.readLine();
        user1 = new User(name);
        System.out.println("Hello " + user1.getName());
    }

    //REQUIRES the user to only input accepted numbers (1-6)
    //MODIFIES
    //EFFECTS displays list of options and lets user pick one
    private void menu() throws IOException {
        System.out.println("What do you want to do (type in number): ");
        System.out.println("(1) Create Saved Workout");
        System.out.println("(2) Do Saved Workout");
        System.out.println("(3) Edit Workout");
        System.out.println("(4) See Saved Workouts");
        System.out.println("(5) See Workout History");
        System.out.println("(6) Exit");
        int choice = Integer.parseInt(consolIn.readLine());
        if (choice != exitcode) {
            this.menuChoice(choice);
            this.menu();
        }

    }

    //REQUIRES choice to be in range of options (1-5)
    //MODIFIES
    //EFFECTS after the user selects a choice, executes the appropriate function
    private void menuChoice(int choice) throws IOException {
        switch (choice) {
            case 1:
                this.createWorkout();
                break;
            case 2:
                this.doWorkout();
                break;
            case 3:
                this.edit();
                break;
            case 4:
                this.showSavedWorkouts();
                break;
            case 5:
                this.showWorkoutHistory();
                break;
        }
    }

    //REQUIRES user to input expected strings for menu navigation
    //MODIFIES user1's saved workouts
    //EFFECTS stores a new workout in user profile
    private void createWorkout() throws IOException {
        String choice = "y";
        System.out.println("Name of workout: ");
        String name = consolIn.readLine();
        Workout workout = new Workout(name);

        while (!choice.equals("n")) {
            workout.addToPlan(this.createWorkingSet(), -1);
            this.showWorkout(workout);
            System.out.println("Do you want to add exercise (y/n)");
            choice = consolIn.readLine();
        }
        user1.addSavedWorkout(workout);
    }

    //REQUIRES
    //MODIFIES
    //EFFECTS displays a workout
    private void showWorkout(Workout workout) throws IOException {
        for (WorkingSet ws : workout.getWorkoutPlan()) {
            this.printWorkingSet(ws);
        }
    }

    //REQUIRES user to only input integers when asked
    //MODIFIES this
    //EFFECTS walks the user though creating a working set
    private WorkingSet createWorkingSet() throws IOException {
        Exercise exercise = this.createExercise();
        System.out.println("Please enter in only integers for the following values");
        System.out.println("Sets: ");
        int sets = Integer.parseInt(consolIn.readLine());
        System.out.println("Reps: ");
        int reps = Integer.parseInt(consolIn.readLine());
        System.out.println("RPE: ");
        int rpe = Integer.parseInt(consolIn.readLine());
        return new WorkingSet(exercise,sets,reps,rpe);
    }

    //REQUIRES
    //MODIFIES this
    //EFFECTS walks user though creating an exercise
    private Exercise createExercise() throws IOException {
        String name;
        System.out.println("What is the exercise called: ");
        name = consolIn.readLine();
        return new Exercise(name);
    }

    //REQUIRES
    //MODIFIES
    //EFFECTS displays a working set
    private void printWorkingSet(WorkingSet ws) throws IOException {
        System.out.println(ws.getExercise().getName());
        System.out.println("Sets: " + ws.getSets() + " Reps: " + ws.getReps() + " RPE: " + ws.getRpe());
    }

    //REQUIRES
    //MODIFIES
    //EFFECTS displays the users saved workouts
    private void showSavedWorkouts() throws IOException {
        for (Workout w : user1.getSavedWorkouts()) {
            System.out.println((user1.getSavedWorkouts().indexOf(w) + 1) + " " + w.getName());
            this.showWorkout(w);
        }
    }

    //REQUIRES user to input correct types when prompted, user inputs existing workout name
    //MODIFIES this
    //EFFECTS walks a user though a workout and saves to history
    private void doWorkout() throws IOException {
        showSavedWorkouts();
        System.out.println("Which which workout do you want to complete (Enter 'nvm' to go back");
        String workoutName = consolIn.readLine();
        if (!workoutName.equals("nvm")) {
            //will add the workout to the end of the active workout history for this user
            user1.startSavedWorkout(workoutName);
            ActiveWorkout workout = user1.getWorkoutHistory().get(user1.getWorkoutHistory().size() - 1);
            this.doWorkSet(workout);
            System.out.println("You have finished! What do you want to record this workout as?");
            String name = consolIn.readLine();
            workout.setName(name);
        }
    }

    //REQUIRES user to input correct types when prompted
    //MODIFIES this
    //EFFECTS walks user though recording sets, reps, and weight for an exercise
    private void doWorkSet(ActiveWorkout w) throws IOException {
        //going through each exercise
        for (WorkingSet ws : w.getWorkoutPlan()) {
            System.out.println("Recording " + ws.getExercise().getName());
            System.out.println("Target Sets: " + ws.getSets() + ", Reps: " + ws.getReps());
            System.out.println("RPE: " + ws.getRpe());
            //going though each set
            for (int set = 1; set <= ws.getSets(); set++) {
                System.out.println("How many reps for set " + set + "?");
                int reps = Integer.parseInt(consolIn.readLine());
                System.out.println("At what weight(lbs) for set " + set + "?");
                double weight = Double.parseDouble((consolIn.readLine()));
                w.recordExercise(ws.getExercise().getName(), set, reps, weight);
            }
            System.out.println("Next Exercise!");
        }
    }

    //REQUIRES
    //MODIFIES
    //EFFECTS displays the user's workout history
    private void showWorkoutHistory() throws IOException {
        for (ActiveWorkout w : user1.getWorkoutHistory()) {
            System.out.println((user1.getWorkoutHistory().indexOf(w) + 1) + " " + w.getName());
            this.showActiveWorkout(w);
        }
    }

    //REQUIRES
    //MODIFIES
    //EFFECTS displays an active workout, including actual reps and weight for each set
    private void showActiveWorkout(ActiveWorkout activeWorkout) throws IOException {
        for (WorkingSet ws : activeWorkout.getWorkoutPlan()) {
            System.out.println(ws.getExercise().getName());
            for (int i = 1; i <= ws.getSets(); i++) {
                int reps = ws.getWorkingReps(i);
                double weight = ws.getWorkingWeight(i);
                System.out.println(" - Set " + i + ": " + reps + " reps at" + weight + " lbs");
            }
        }
    }

    //REQUIRES user to input correct types when prompted, all names are valid
    //MODIFIES this
    //EFFECTS walks a user though editing a workout (simple replace an exercise with a new one)
    private void edit() throws IOException {
        this.showSavedWorkouts();
        String choice = "y";
        System.out.println("Which one did you want to edit?");
        String toEdit = consolIn.readLine();
        for (Workout w : user1.getSavedWorkouts()) {
            if (toEdit.equals(w.getName())) {
                while (!choice.equals("n")) {
                    this.showWorkout(w);
                    System.out.println("What exercise do you want to replace?");
                    String toChange = consolIn.readLine();
                    System.out.println("Enter new exercise");
                    WorkingSet ws = this.createWorkingSet();
                    w.replaceInPlan(toChange, ws);
                    System.out.println("Edited workout:");
                    this.showWorkout(w);
                    System.out.println("Do you want to change another one (y/n)?");
                    choice = consolIn.readLine();
                }
            }
        }
    }
}
