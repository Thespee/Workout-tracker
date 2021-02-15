package ui;

import com.sun.corba.se.spi.orbutil.threadpool.Work;
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
                System.out.println("choice = " + choice);
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
            System.out.println("Do you want to add exercise (y/n)");
            choice = consolIn.readLine();
            workout.addToPlan(this.createWorkingSet(), -1);
            this.showWorkout(workout);
        }
    }

    //TODO: everthing below this
    private void showWorkout(Workout workout) {
        System.out.println("Printing out workout");
    }

    private WorkingSet createWorkingSet() throws IOException {
        Exercise exercise = this.createExercise();
        return null;
    }

    private Exercise createExercise() throws IOException {
        //
        return null;
    }
}
