package ui.menuPanels;

import model.Workout;
import ui.Tracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateWorkoutPanel extends JPanel {
    Workout workout;
    private Tracker tracker;

    public CreateWorkoutPanel(Tracker tracker) {
        this.tracker = tracker;
        setPreferredSize(new Dimension(tracker.WIDTH - tracker.MENU_SIZE, tracker.HEIGHT));
        setBackground(Color.WHITE);
        add(new Label("Test Creating"));
    }
}
