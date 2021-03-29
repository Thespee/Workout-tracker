package ui.panels;

import model.Exercise;
import model.WorkingSet;
import model.Workout;
import ui.Tracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateWorkoutPanel extends JPanel implements ActionListener {
    private Workout workout;

    private JList workingSetList;
    private DefaultListModel listModel;

    private Tracker tracker;
    private JTextField nameField;
    private JTextField exerciseNameField;
    private JTextField setsField;
    private JTextField repsField;
    private JTextField rpeField;

    private JButton saveBtn;

    public CreateWorkoutPanel(Tracker tracker) {
        this.tracker = tracker;
        workout = new Workout("null");
        listModel = new DefaultListModel();

        setPreferredSize(new Dimension(tracker.WIDTH - tracker.MENU_SIZE, tracker.HEIGHT));
        setBackground(Color.WHITE);
        add(createNamePanel(), BorderLayout.NORTH);
        add(addExercisePanel(), BorderLayout.CENTER);
        add(createSavePanel(), BorderLayout.SOUTH);
    }

    //MODIFIES this plane
    //EFFECTS creates a panel that lets the user imput a workout name
    private JPanel createNamePanel() {
        JPanel namePanel = new JPanel();
        namePanel.setMinimumSize(new Dimension(tracker.WIDTH - tracker.MENU_SIZE, tracker.HEIGHT * 10 / 50));

        nameField = new JTextField("Enter Workout Name",20);

        Box nameBox = Box.createHorizontalBox();
        nameBox.add(new Label("Name: "));
        nameBox.add(Box.createHorizontalStrut(10));
        nameBox.add(nameField);

        namePanel.add(nameBox);
        return namePanel;
    }

    //REQUIRES
    //MODIFIES
    //EFFECTS creates a workout and adds exercises to that workout, saves workout to user
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "save" :
                workout.setName(nameField.getText());
                tracker.getUser().addSavedWorkout(workout);
                System.out.println("saved workout");
                break;
            case "addExercise" :
                WorkingSet ws = createWorkingSet();
                workout.addToPlan(ws, -1);
                listModel.addElement("<html>" + ws.toString() + "</html>");
                //only allow saving after 1 exercise is input
                saveBtn.setEnabled(true);
                break;
        }
    }

    //REQUIRES
    //MODIFIES this plane
    //EFFECTS creates a panel that lets the user save a workout to the user
    private JPanel createSavePanel() {
        JPanel savePanel = new JPanel();
        savePanel.setMinimumSize(new Dimension(tracker.WIDTH - tracker.MENU_SIZE, tracker.HEIGHT * 5 / 50));

        saveBtn = new JButton("Save Workout");
        saveBtn.setActionCommand("save");
        saveBtn.addActionListener(this);
        saveBtn.setEnabled(false);

        savePanel.add(saveBtn, BorderLayout.CENTER);

        return savePanel;
    }

    //REQUIRES
    //MODIFIES
    //EFFECTS creates a split pane to show exercises and let user add new ones
    private JSplitPane addExercisePanel() {
        JSplitPane exercisePanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        exercisePanel.setOneTouchExpandable(false);
        exercisePanel.setDividerLocation((tracker.WIDTH - tracker.MENU_SIZE) / 2);
        exercisePanel.setPreferredSize(new Dimension((tracker.WIDTH - tracker.MENU_SIZE), tracker.HEIGHT * 35 / 50));
        exercisePanel.setBackground(Color.BLACK);
        exercisePanel.setLeftComponent(showExercises());
        exercisePanel.setRightComponent(createExercise());
        return exercisePanel;
    }

    //EFFECTS shows the exercises
    private JScrollPane showExercises() {
        listModel.clear();

        workingSetList = new JList(listModel);
        workingSetList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        workingSetList.setSelectedIndex(0);

        JScrollPane listScrollPane = new JScrollPane(workingSetList);
        listScrollPane.setPreferredSize(
                new Dimension((tracker.WIDTH - tracker.MENU_SIZE) / 2, tracker.HEIGHT * 35 / 50));
        return listScrollPane;
    }


    //REQUIRES
    //MODIFIES this.workout.savedWorkouts
    //EFFECTS creates a small panel that lets the user add exercises to workout
    private JPanel createExercise() {
        JPanel exercisePanel = new JPanel();
        exercisePanel.setPreferredSize(
                new Dimension((tracker.WIDTH - tracker.MENU_SIZE) / 2, tracker.HEIGHT * 35 / 50));
        exercisePanel.setBackground(Color.GRAY);

        setUpFields();

        JButton addExercise = new JButton("Add Exercise");
        addExercise.setActionCommand("addExercise");
        addExercise.addActionListener(this);

        Box containerBox = Box.createVerticalBox();
        containerBox.add(new Label("Add Exercise:"), BorderLayout.CENTER);
        containerBox.add(exerciseNameField, BorderLayout.CENTER);
        containerBox.add(setsField, BorderLayout.CENTER);
        containerBox.add(repsField, BorderLayout.CENTER);
        containerBox.add(rpeField, BorderLayout.CENTER);
        containerBox.add(addExercise, BorderLayout.CENTER);

        exercisePanel.add(containerBox);
        return exercisePanel;
    }

    //REQUIRES
    //MODIFIES this
    //EFFECTS sets up field default values
    private void setUpFields() {
        exerciseNameField = new JTextField("exercise name",10);
        setsField = new JTextField("sets",10);
        repsField = new JTextField("reps",10);
        rpeField = new JTextField("RPE",10);

    }

    //REQUIRES
    //MODIFIES
    //EFFECTS creates a working set from info typed into fields, then resets the fields
    private WorkingSet createWorkingSet() {
        Exercise e = new Exercise(exerciseNameField.getText());
        int sets = Integer.parseInt(setsField.getText());
        int reps = Integer.parseInt(repsField.getText());
        int rpe = Integer.parseInt(rpeField.getText());

        exerciseNameField.setText("");
        setsField.setText("");
        repsField.setText("");
        rpeField.setText("");

        WorkingSet ws = new WorkingSet(e, sets, reps, rpe);
        return ws;
    }
}
