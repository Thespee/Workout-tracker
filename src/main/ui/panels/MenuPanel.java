package ui.panels;

import ui.Tracker;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MenuPanel extends JSplitPane implements ActionListener  {
    private Tracker tracker;
    private static final int BTN_HEIGHT = 50;
    private static final int BTN_WIDTH = 100;

    private boolean madeChoice = false;
    private JPanel contentPanel;

    private static final String LOGO_STORE = "./data/Images/save.jpg";
    private static Saved saveLogo;

    public MenuPanel(Tracker tracker) {
        super(JSplitPane.HORIZONTAL_SPLIT);
        setPreferredSize(new Dimension(tracker.WIDTH, tracker.HEIGHT));
        setDividerLocation(tracker.MENU_SIZE);
        setBackground(Color.WHITE);
        this.tracker = tracker;
        saveLogo = new Saved();
        contentPanel = new JPanel();
        contentPanel.setPreferredSize(new Dimension(tracker.WIDTH - tracker.MENU_SIZE, tracker.HEIGHT));
        contentPanel.setBackground(Color.WHITE);
        setLeftComponent(menuPanel());
        setRightComponent(contentPanel);

        setVisible(true);
    }

    //REQUIRES
    //MODIFIES
    //EFFECTS creates a menu panel
    private JPanel menuPanel() {
        JPanel menu = new JPanel();
        menu.setPreferredSize(new Dimension(tracker.MENU_SIZE, tracker.HEIGHT));
        menu.setBackground(Color.GRAY);

        menu.add(createMenu());
        menu.add(saveLogo);
        saveLogo.setVisible(false);

        return menu;
    }

    //REQUIRES
    //MODIFIES
    //EFFECTS creates a menu bar with many buttons
    private JMenuBar createMenu() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.Y_AXIS));
        for (JButton b : getButtons()) {
            b.setSize(BTN_WIDTH,BTN_HEIGHT);
            b.addActionListener(this);
            menuBar.add(Box.createVerticalStrut(10));
            menuBar.add(b);
        }

        menuBar.setBackground(Color.GRAY);
        saveLogo.setVisible(false);

        return menuBar;

    }

    private ArrayList<JButton> getButtons() {
        ArrayList<JButton> buttonList = new ArrayList<>();
        JButton createSaved = new JButton("Create Saved Workout");
        createSaved.setActionCommand("createSaved");
        buttonList.add(createSaved);

        JButton seeSaved = new JButton("See Saved Workouts");
        seeSaved.setActionCommand("seeSaved");
        buttonList.add(seeSaved);

        JButton runActiveWorkout = new JButton("Comeplete Active Workout");
        runActiveWorkout.setActionCommand("doActive");
        buttonList.add(runActiveWorkout);

        JButton saveUser = new JButton("Save Current User");
        saveUser.setActionCommand("save");
        buttonList.add(saveUser);

        JButton loadUser = new JButton("Load Saved User");
        loadUser.setActionCommand("load");
        buttonList.add(loadUser);

        JButton logIn = new JButton("Log Into New User");
        logIn.setActionCommand("logIn");
        buttonList.add(logIn);

        return buttonList;
    }

    //REQUIRES ActionCommand to be valid
    //MODIFIES right side of this spit plane
    //EFFECTS handles the creation of the right side 'active' panels
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "createSaved":
                saveLogo.setVisible(false);
                update(new CreateWorkoutPanel(tracker));
                break;
            case "seeSaved":
                update(new SavedPanel(tracker));
                break;
            case "doActive" :
                update(new ActiveWorkoutPanel(tracker));
                break;
            case "load" :
                tracker.loadUser();
                break;
            case "save" :
                //make the saved logo visible
                saveLogo.setVisible(true);
                updateUI();
                tracker.saveUser();
                break;
            case "logIn":
                update(new LogInPanel(tracker));
                break;
        }
    }

    //REQUIRES active panel to be able to fit in right component
    //MODIFIES the right component of the split plane
    //EFFECTS sets the right component to a new panel
    public void update(JPanel activePanel) {
        setVisible(false);
        contentPanel = activePanel;
        setRightComponent(contentPanel);
        setVisible(true);
    }

    //EFFECTS creates the saved image
    class Saved extends Component {
        BufferedImage img;

        public void paint(Graphics g) {
            g.drawImage(img, 0, 0, null);
        }

        public Saved() {
            try {
                img = ImageIO.read(new File(LOGO_STORE));
            } catch (IOException e) {
                System.out.println("Image not found");
            }

        }

        public Dimension getPreferredSize() {
            if (img == null) {
                return new Dimension(100,100);
            } else {
                return new Dimension(img.getWidth(null), img.getHeight(null));
            }
        }
    }
}
