package ui;

import ui.menuPanels.LogInPanel;
import ui.menuPanels.MenuPanel;

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
    private Timer t;

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
        t.start();

        /*while (!tracker.isLoggedIn()) {
            t.start();
            //waiting until user is selected before continuing
            //System.out.println("waiting");
            //TimeUnit.SECONDS.sleep(1);
        }

        setVisible(false);
        remove(lip);
        add(mp);
        pack();
        setVisible(true);

         */
    }

    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    public static void main(String[] args) throws IOException  {
        new WorkoutTracker();
    }

    private void addTimer() {
        t = new Timer(INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (tracker.isLoggedIn()) {
                    setVisible(false);
                    remove(lip);
                    t.stop();
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
