package ui.menuPanels;

import ui.Tracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JSplitPane implements ActionListener  {
    private Tracker tracker;
    private static final int BTN_HEIGHT = 50;
    private static final int BTN_WIDTH = 100;

    private boolean madeChoice = false;
    private JPanel contentPanel;

    public MenuPanel(Tracker tracker) {
        super(JSplitPane.HORIZONTAL_SPLIT);
        setPreferredSize(new Dimension(tracker.WIDTH, tracker.HEIGHT));
        setDividerLocation(tracker.MENU_SIZE);
        setBackground(Color.WHITE);

        this.tracker = tracker;
        contentPanel = new JPanel();
        contentPanel.setPreferredSize(new Dimension(tracker.WIDTH - tracker.MENU_SIZE, tracker.HEIGHT));
        contentPanel.setBackground(Color.WHITE);
        setLeftComponent(menuPanel());
        setRightComponent(contentPanel);


        setVisible(true);
    }

    private JPanel menuPanel() {
        JPanel menu = new JPanel();
        menu.setPreferredSize(new Dimension(tracker.MENU_SIZE, tracker.HEIGHT));
        menu.setBackground(Color.GRAY);

        menu.add(createMenu());

        return menu;
    }

    private JMenuBar createMenu() {
        JButton loadUser = new JButton("Load Saved User");
        loadUser.setSize(BTN_WIDTH,BTN_HEIGHT);
        loadUser.setActionCommand("load");
        loadUser.addActionListener(this);

        JButton seeSaved = new JButton("See Saved Workouts");
        seeSaved.setSize(BTN_WIDTH,BTN_HEIGHT);
        seeSaved.setActionCommand("seeSaved");
        seeSaved.addActionListener(this);

        JButton createSaved = new JButton("Create Saved Workout");
        createSaved.setSize(BTN_WIDTH,BTN_HEIGHT);
        createSaved.setActionCommand("createSaved");
        createSaved.addActionListener(this);

        JButton saveUser = new JButton("Save Current User");
        saveUser.setSize(BTN_WIDTH,BTN_HEIGHT);
        saveUser.setActionCommand("save");
        saveUser.addActionListener(this);

        JButton logIn = new JButton("Log Into New User");
        logIn.setSize(BTN_WIDTH,BTN_HEIGHT);
        logIn.setActionCommand("logIn");
        logIn.addActionListener(this);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.Y_AXIS));
        menuBar.add(Box.createVerticalStrut(10));
        menuBar.add(createSaved);
        menuBar.add(Box.createVerticalStrut(10));
        menuBar.add(seeSaved);
        menuBar.add(Box.createVerticalStrut(10));
        menuBar.add(loadUser);
        menuBar.add(Box.createVerticalStrut(10));
        menuBar.add(saveUser);
        menuBar.add(Box.createVerticalStrut(10));
        menuBar.add(logIn);

        menuBar.setBackground(Color.GRAY);

        return menuBar;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "createSaved":
                //new CreateWorkoutPanel();
                System.out.println("createSavedWorkout");
                update(new CreateWorkoutPanel(tracker));
                break;
            case "seeSaved":
                System.out.println("seeSaved");
                update(new SavedPanel(tracker));
                break;
            case "load" :
                tracker.loadUser();
                break;
            case "save" :
                tracker.saveUser();
                break;
            case "logIn":
                System.out.println("changing user");
                LogInPanel lip = new LogInPanel(tracker);
                lip.setSize(new Dimension(tracker.WIDTH - tracker.MENU_SIZE, tracker.HEIGHT));
                update(lip);
        }
    }

    public void update(JPanel activePanel) {
        setVisible(false);
        contentPanel = activePanel;
        setRightComponent(contentPanel);
        setVisible(true);
    }
}
