package ui;

import model.User;
import persistence.UserReader;
import persistence.UserWriter;

import java.io.BufferedReader;
import java.io.IOException;

public class Tracker {
    public static final int HEIGHT = 500;
    public static final int WIDTH = 600;
    public static final int MENU_SIZE = 150;
    private User user1;
    private boolean loggedIn;


    public static final String JSON_STORE = "./data/userData.json";
    private UserReader userReader;
    private UserWriter userWriter = new UserWriter(JSON_STORE);

    public Tracker() {
        userReader = new UserReader(JSON_STORE);
        loggedIn = false;
    }

    public void loadUser() {
        try {
            user1 = userReader.read();
            System.out.println("Loaded " + user1.getName() + " from file");
        } catch (Exception e) {
            System.out.println("Failed to load user from disk");
        }

    }

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
}
