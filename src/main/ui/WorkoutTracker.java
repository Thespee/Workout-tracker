package ui;

import ui.panels.LogInPanel;
import ui.panels.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class WorkoutTracker extends JFrame {
    private Tracker tracker;
    private LogInPanel lip;
    private MenuPanel mp;

    private static final int INTERVAL = 20;
    private Timer timer;

    public WorkoutTracker() {
        super("Workout Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        tracker = new Tracker();
        lip = new LogInPanel(tracker);
        mp = new MenuPanel(tracker);

        centreOnScreen();

        add(lip);
        pack();
        setVisible(true);
        addTimer();
        timer.start();
    }

    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    public static void main(String[] args) throws IOException  {
        new WorkoutTracker();
    }

    private void addTimer() {
        timer = new Timer(INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (tracker.isLoggedIn()) {
                    setVisible(false);
                    remove(lip);
                    timer.stop();
                    showMenu();
                }
            }
        });
    }

    private void showMenu() {
        add(mp);
        pack();
        setVisible(true);
    }
}
