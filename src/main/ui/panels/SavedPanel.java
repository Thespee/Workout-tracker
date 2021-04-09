package ui.panels;

import ui.Tracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SavedPanel extends JPanel implements ActionListener {
    private static final String REMOVE_COMMAND = "remove";
    private JList workouts;
    private Tracker tracker;
    private JButton remove;

    public SavedPanel(Tracker tracker) {
        this.tracker = tracker;
        this.workouts = tracker.getWorkoutList();

        setPreferredSize(new Dimension(tracker.WIDTH - tracker.MENU_SIZE, tracker.HEIGHT));
        setBackground(Color.WHITE);

        JScrollPane listScrollPane = new JScrollPane(workouts);
        listScrollPane.setMinimumSize(
                new Dimension((tracker.WIDTH - tracker.MENU_SIZE) * 4 / 5, tracker.HEIGHT * 7 / 8));

        remove = new JButton("REMOVE SELECTED WORKOUT");
        remove.setActionCommand(REMOVE_COMMAND);
        remove.addActionListener(this);


        Box workoutDisplayBox = Box.createVerticalBox();
        workoutDisplayBox.add(new Label("Here are your workouts:"), BorderLayout.CENTER);
        workoutDisplayBox.add(Box.createVerticalStrut(50));
        workoutDisplayBox.add(listScrollPane, BorderLayout.CENTER);
        workoutDisplayBox.add(remove, BorderLayout.CENTER);

        add(workoutDisplayBox);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(REMOVE_COMMAND)) {
            int index = workouts.getSelectedIndex();
            System.out.println("removing selected element " + index);
            tracker.getUser().getSavedWorkouts().remove(index);
            workouts = tracker.getWorkoutList();
            updateUI();
        }
    }

    private void checkRemoveButton() {
        if (tracker.getUser().getSavedWorkouts().size() < 1) {
            remove.setEnabled(false);
        } else {
            remove.setEnabled(true);
        }
    }
}
