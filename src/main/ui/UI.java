package ui;

import model.*;
import java.io.*;

public class UI {
    BufferedReader consolIn;
    User user1;
    final int exitcode = 6;

    public UI() throws IOException {
        System.out.println("Hello World");
        this.consolIn = new BufferedReader(new InputStreamReader(System.in));
        this.createUser();
        this.menu();
    }

    private void createUser() throws IOException {
        System.out.println("Hello, please enter your name: ");
        String name = consolIn.readLine();
        user1 = new User(name);
        System.out.println("Hello " + user1.getName());
    }

    private void menu() throws IOException {
        System.out.println("What do you want to do (type in number): ");
        System.out.println("Only options 1 & 4 are available for demonstration");
        System.out.println("(1) Create Saved Workout");
        System.out.println("(2) Do Saved Workout");
        System.out.println("(3) Quick Workout");
        System.out.println("(4) See Saved Workouts");
        System.out.println("(5) See Workout History");
        System.out.println("(6) Exit");
        int choice = Integer.parseInt(consolIn.readLine());
        if (choice != exitcode) {
            this.menuChoice(choice);
            this.menu();
        }

    }

    private void menuChoice(int choice) throws IOException {
        switch (choice) {
            case 1:
                this.createWorkout();
                break;
            case 2:
                System.out.println("choice = " + choice);
                break;
            case 3:
                System.out.println("choice = " + choice);
                break;
            case 4:
                this.showSavedWorkouts();
                break;
            case 5:
                System.out.println("choice = " + choice);
                break;
        }
    }

    private void createWorkout() throws IOException {
        String choice = "y";

        System.out.println("Name of workout: ");
        String name = consolIn.readLine();
        Workout workout = new Workout(name);

        while (choice == "y") {
            workout.addToPlan(this.createWorkingSet(), -1);
            this.showWorkout(workout);
            System.out.println("Do you want to add exercise (y/n)");
            choice = consolIn.readLine();
        }

        user1.addSavedWorkout(workout);
    }

    private void showWorkout(Workout workout) throws IOException {
        for (WorkingSet ws : workout.getWorkoutPlan()) {
            this.printWorkingSet(ws);
        }
    }

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

    private Exercise createExercise() throws IOException {
        String name;
        System.out.println("What is the exrcise called: ");
        name = consolIn.readLine();
        return new Exercise(name);
    }

    private void printWorkingSet(WorkingSet ws) throws IOException {
        System.out.println(ws.getExercise().getName());
        System.out.println("Sets: " + ws.getSets());
        System.out.println("Reps: " + ws.getReps());
        System.out.println("RPE: " + ws.getRpe());
    }

    private void showSavedWorkouts() throws IOException {
        for (Workout w : user1.getSavedWorkouts()) {
            System.out.println(w.getName());
            this.showWorkout(w);
        }
    }
}
