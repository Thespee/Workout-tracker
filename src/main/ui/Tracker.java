package ui;

import model.User;
import model.Workout;
import persistence.UserReader;
import persistence.UserWriter;

import javax.swing.*;

public class Tracker {
    public static final int HEIGHT = 500;
    public static final int WIDTH = 600;
    public static final int MENU_SIZE = 150;
    private User user1;
    private boolean loggedIn;


    public static final String JSON_STORE = "./data/userData.json";
    private UserReader userReader;
    private UserWriter userWriter = new UserWriter(JSON_STORE);

    private JList workoutList;
    private DefaultListModel listModel;

    public Tracker() {
        userReader = new UserReader(JSON_STORE);
        loggedIn = false;
        listModel = new DefaultListModel();
    }

    //REQUIRES
    //MODIFIES this.user1
    //EFFECTS loads a user from memory
    public void loadUser() {
        try {
            user1 = userReader.read();
            System.out.println("Loaded " + user1.getName() + " from file");
        } catch (Exception e) {
            System.out.println("Failed to load user from disk");
        }

    }

    //REQUIRES
    //MODIFIES this.user1
    //EFFECTS sets user to given user
    public void setUser(User u) {
        this.user1 = u;
        System.out.println("New User: " + u.getName());
    }

    public User getUser() {
        return user1;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean state) {
        this.loggedIn = state;
    }

    //REQUIRES correct file path
    //MODIFIES userData.JSON
    //EFFECTS saves user to JSON file
    public void saveUser() {
        try {
            userWriter.open();
        } catch (Exception e) {
            System.out.println("Error Could Not Find Save File");
        }

        userWriter.write(user1);
        userWriter.close();
        System.out.println("saved " + user1.getName() + " to file");
    }

    //Returns the user's saved workouts as a Jlist for display
    public JList getWorkoutList() {
        workoutList = new JList(getListModel());
        workoutList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        workoutList.setSelectedIndex(0);
        return workoutList;
    }

    public Workout getSavedWorkout(int i) {
        return user1.getSavedWorkouts().get(i);
    }

    //REQUIRES
    //MODIFIES
    //EFFECTS retruns the list of saved workouts as a ListModel for swing
    public DefaultListModel getListModel() {
        listModel.clear();
        for (Workout w : user1.getSavedWorkouts()) {
            listModel.addElement("<html>" + w.toString() + "</html>");
        }
        return listModel;
    }
}
