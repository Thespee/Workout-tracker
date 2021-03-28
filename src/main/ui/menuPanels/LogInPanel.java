package ui.menuPanels;

import model.User;
import ui.Tracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInPanel extends JPanel implements ActionListener {
    private Tracker tracker;

    private JTextField field;

    private static final int BTN_WIDTH = 100;
    private static final int BTN_HEIGHT = 60;

    public LogInPanel(Tracker tracker) {
        this.tracker = tracker;
        setPreferredSize(new Dimension(tracker.WIDTH, tracker.HEIGHT));
        setBackground(Color.GRAY);

        JButton logInBtn = new JButton("Load Existing User");
        logInBtn.setSize(BTN_WIDTH, BTN_HEIGHT);
        logInBtn.setActionCommand("logIn");
        logInBtn.addActionListener(this);

        JButton newUserBtn = new JButton("Create User");
        newUserBtn.setSize(BTN_WIDTH,BTN_HEIGHT);
        newUserBtn.setActionCommand("newUser");
        newUserBtn.addActionListener(this);

        field = new JTextField("Enter Name",20);

        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalStrut(Tracker.HEIGHT * 1 / 3));
        box.add(logInBtn);
        box.add(Box.createVerticalStrut(50)); //spacer

        Box newUserBox = Box.createHorizontalBox();
        newUserBox.add(newUserBtn);
        newUserBox.add(Box.createHorizontalStrut(10));
        newUserBox.add(field);
        box.add(newUserBox);
        
        add(box);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("logIn")) {
            tracker.loadUser();
            tracker.setLoggedIn(true);
            //remove(this);

        } else if (e.getActionCommand().equals("newUser")) {
            tracker.setUser(new User(field.getText()));
            tracker.setLoggedIn(true);
            //remove(this);
        }
    }

    public JTextField getField() {
        return field;
    }
}
