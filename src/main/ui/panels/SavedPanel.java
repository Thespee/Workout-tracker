package ui.panels;

import ui.Tracker;

import javax.swing.*;
import java.awt.*;

public class SavedPanel extends JPanel {
    public SavedPanel(Tracker tracker) {
        setPreferredSize(new Dimension(tracker.WIDTH - tracker.MENU_SIZE, tracker.HEIGHT));
        setBackground(Color.WHITE);

        JScrollPane listScrollPane = new JScrollPane(tracker.getWorkoutList());
        listScrollPane.setMinimumSize(
                new Dimension((tracker.WIDTH - tracker.MENU_SIZE) * 4 / 5, tracker.HEIGHT * 7 / 8));
        Box workoutDisplayBox = Box.createVerticalBox();
        workoutDisplayBox.add(new Label("Here are your workouts:"), BorderLayout.CENTER);
        workoutDisplayBox.add(Box.createVerticalStrut(50));
        workoutDisplayBox.add(listScrollPane, BorderLayout.CENTER);

        add(workoutDisplayBox);
    }
}
