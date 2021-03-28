package ui.menuPanels;

import ui.Tracker;

import javax.swing.*;
import java.awt.*;

public class SavedPanel extends JPanel {
    public SavedPanel(Tracker tracker) {
        setPreferredSize(new Dimension(tracker.WIDTH - tracker.MENU_SIZE, tracker.HEIGHT));
        setBackground(Color.WHITE);
        add(new Label("Testing Saved"));
    }
}
