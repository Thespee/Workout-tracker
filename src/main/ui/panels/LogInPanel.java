package ui.panels;

import model.User;
import ui.Tracker;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//Creates a log in panel that lets the user select between loading the saved user or creating a new user
public class LogInPanel extends JPanel implements ActionListener {
    private Tracker tracker;

    private JTextField field;

    private static final int BTN_WIDTH = 100;
    private static final int BTN_HEIGHT = 60;
    private static final String LOGO_STORE = "./data/Images/logo.jpg";

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
        box.add(new Logo(), BorderLayout.SOUTH);

        add(box);
    }

    //MODIFIES this.tracker
    //EFFECTS loads the correct user or creates a new user
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

    //EFFECTS creates the logo image
    class Logo extends Component {
        BufferedImage img;

        public void paint(Graphics g) {
            g.drawImage(img, 100, 0, null);
        }

        public Logo() {
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
