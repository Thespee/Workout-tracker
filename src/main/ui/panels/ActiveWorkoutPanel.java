package ui.panels;

import model.Workout;
import ui.Tracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActiveWorkoutPanel extends JPanel implements ActionListener {
    private Tracker tracker;
    private JPanel contentPanel;
    private Workout workout;
    private DefaultListModel listModel;
    private JList workoutList;


    public ActiveWorkoutPanel(Tracker tracker) {
        System.out.println("WARNING: FEATURE IS INCOMPLETE BEWARE OF BUGS");
        this.tracker = tracker;
        setPreferredSize(new Dimension(tracker.WIDTH - tracker.MENU_SIZE, tracker.HEIGHT));
        setBackground(Color.WHITE);

        contentPanel = workoutSelection();
        updateActiveWorkoutPanel(contentPanel);
        setVisible(true);
    }

    //EFFECTS creates a panel that lets the user select a workout to do
    private JPanel workoutSelection() {
        listModel = tracker.getListModel();
        workoutList = tracker.getWorkoutList();
        workoutList.setSelectedIndex(0);
        JScrollPane selection = new JScrollPane(workoutList);

        JButton select = new JButton("Select Workout");
        select.setActionCommand("selectedWorkout");
        select.addActionListener(this);

        Box workoutDisplayBox = Box.createVerticalBox();
        workoutDisplayBox.add(new Label("Here are your workouts:"), BorderLayout.CENTER);
        workoutDisplayBox.add(Box.createVerticalStrut(50));
        workoutDisplayBox.add(selection, BorderLayout.CENTER);
        workoutDisplayBox.add(select, BorderLayout.CENTER);

        JPanel selectionPanel = new JPanel();
        selectionPanel.setPreferredSize(new Dimension(tracker.WIDTH - tracker.MENU_SIZE, tracker.HEIGHT));
        selectionPanel.setBackground(Color.WHITE);
        selectionPanel.add(workoutDisplayBox);

        return selectionPanel;
    }

    //MODIFIES this
    //EFFECTS changes from the 'select workout' pane to the 'doing a workout pane'
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "selectedWorkout" :
                System.out.println("selected a workout");
                int index = workoutList.getSelectedIndex();
                workout = tracker.getSavedWorkout(index);
                updateActiveWorkoutPanel(new DoingActiveWorkoutPanel());
                break;
        }
    }

    //EFFECTS changes the active workout panel
    private void updateActiveWorkoutPanel(JPanel panel) {
        remove(contentPanel);
        contentPanel = panel;
        add(contentPanel);
        setVisible(true);
        updateUI();
    }

    //Will be a panel that allows user to record a workout
    class DoingActiveWorkoutPanel extends JPanel implements ActionListener {
        private JList workingSetList;

        public DoingActiveWorkoutPanel() {
            setPreferredSize(new Dimension(tracker.WIDTH - tracker.MENU_SIZE, tracker.HEIGHT));
            setBackground(Color.WHITE);
            add(new Label(workout.getName()));
        }


        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
