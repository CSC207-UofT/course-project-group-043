package FrameworksDrivers;

import Controllers.ScheduleManager;
import Entities.Event;
import Entities.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ScheduleGraphics extends JFrame implements ActionListener {
    private Graphics g2g;

    ScheduleManager manager = new ScheduleManager();
    Person user = new Person("user", "1234"); // making a temporary person (for testing purposes)

    // declaring JButtons, JLabels, etc. that appear as graphics
    private JButton addEventButton;
    private JButton editEventButton;
    private JButton manageFriendsButton;
    private JButton compareSchedulesButton;
    private JButton helpButton;
    private JButton logoutButton;

    private JPanel buttonPanel;

    private JLabel titleLabel;

    // addEventButton popup
    // TODO: keep declaration here, move creation inside
    private JTextField addEventName = new JTextField();
    private JComboBox addEventDate = new JComboBox(new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"});
    private String[] timesList = {"00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"};
    private JComboBox addEventStart = new JComboBox(timesList);
    private JComboBox addEventEnd = new JComboBox(timesList);


    public ScheduleGraphics() {
        super("Scheduler App");
        setSize(850, 850); // setting the default size of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // when pressing the x button, progarm will close

        setLayout(null);

        // creation of the main control buttons
        addEventButton = new JButton("Add Event");
        editEventButton = new JButton("Edit Event");
        manageFriendsButton = new JButton("Manage Friends");
        compareSchedulesButton = new JButton("Compare Schedules");
        helpButton = new JButton("Help");
        logoutButton = new JButton("Logout");

        buttonPanel = new JPanel();

        titleLabel = new JLabel("Scheduler App");

        // adding ActionListeners (code at bottom of program will run when a given button is pressed)
        addEventButton.addActionListener(this);
        editEventButton.addActionListener(this);
        manageFriendsButton.addActionListener(this);
        compareSchedulesButton.addActionListener(this);
        helpButton.addActionListener(this);
        logoutButton.addActionListener(this);


        titleLabel.setFont(new Font("Verdana", Font.PLAIN, 18)); // setting different font for title


        //Adding to buttonPanel
        buttonPanel.add(addEventButton);
        buttonPanel.add(editEventButton);
        buttonPanel.add(manageFriendsButton);
        buttonPanel.add(compareSchedulesButton);
        buttonPanel.add(helpButton);
        buttonPanel.add(logoutButton);

        // setting the locations of objects
        titleLabel.setBounds(360, -5, 200, 50);
        buttonPanel.setBounds(80,35,700,200);

        // adding to JPanel
        add(titleLabel);

        add(buttonPanel);

        setLocationRelativeTo(null);

        setVisible(true);

    }

    public void paint(Graphics g) {
        super.paint(g);

        g2g = g;

        // creating custom colors
        Color color0 = Color.white;
        Color color1 = new Color(110, 205, 250);
        Color color2 = new Color(39, 208, 245);
        Color color3 = new Color(0, 210, 231);
        Color color4 = new Color(0, 211, 207);
        Color color5 = new Color(0, 210, 175);
        Color color6 = new Color(0, 207, 137);
        Color color7 = new Color(65, 202, 94);

        Color[] colorsList = {color0, color1, color2, color3, color4, color5, color6, color7};

        String[] titles = {"Time", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};


        // TODO: make schedule grid larger


        // drawing the day of week header boxes & outlines
        for (int x = 0; x < 8; x++) {
            g2g.setColor(colorsList[x]); // different color for each day slot
            g2g.fillRect(25 + (x * 100), 100, 100, 40);
            g2g.setColor(Color.BLACK);
            g2g.drawString(titles[x],53 + (x * 100), 125);
        }
//
        g2g.setColor(Color.white);
        for (int x = 0; x < 8; x++) {
            g2g.drawRect(25 + (x * 100), 100, 100, 40);
        }
//
        // drawing the empty schedule outlines
        for (int x = 0; x < 8; x++) {
            for (int i = 0; i < 24; i++) {
                g2g.drawRect(25 + (x * 100), 140 + (i * 25), 100, 25);
            }
        }

        for (int x = 0; x < 24; x++) {
            g2g.setColor(Color.gray);
            g2g.fillRect(25, 140 + (x * 25), 100, 25);
            g2g.setColor(Color.BLACK);
            g2g.drawString(timesList[x], 50, 160 +(25 * x));
        }

    }



    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(
                    "javax.swing.plaf.nimbus.NimbusLookAndFeel"
            );
        }
        catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }


    public static void main (String[] arguments) {
        setLookAndFeel();
        ScheduleGraphics gf = new ScheduleGraphics();
        gf.repaint(); // repaint is called so that the graphics are updated

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource(); // this method fully just does return an Object, it doesn't get more specific, apparently

        // the following code will run if a given button is pressed

        if (source == addEventButton) {
            // creating a JPanel that will go inside the dialog
            JPanel addEventPanel = new JPanel();
            addEventPanel.setLayout(new BoxLayout(addEventPanel, BoxLayout.Y_AXIS));
            addEventPanel.add(new JLabel("Name:"));
            addEventPanel.add(addEventName);
            addEventPanel.add(new JLabel("Date:"));
            addEventPanel.add(addEventDate);
            addEventPanel.add(new JLabel("Start Time:"));
            addEventPanel.add(addEventStart);
            addEventPanel.add(new JLabel("End Time:"));
            addEventPanel.add(addEventEnd);

            int buttonChoice = JOptionPane.showConfirmDialog(null, addEventPanel, "Add Entities.Event", JOptionPane.OK_CANCEL_OPTION);

            if (buttonChoice == JOptionPane.OK_OPTION) {
                // will interact with Entities.Event class

                // temporarily assigning the values to temporary variables
                String eventName = addEventName.getText();
                String eventDate = (String) addEventDate.getSelectedItem();

                String eventStart = (String) addEventStart.getSelectedItem();
                String eventStartString = eventStart.split(":")[0];
                int eventStartInt = Integer.parseInt(eventStartString);

                String eventEnd = (String) addEventEnd.getSelectedItem();
                String eventEndString = eventEnd.split(":")[0];
                int eventEndInt = Integer.parseInt(eventEndString);


                Event event = new Event(eventName, eventDate, eventStartInt, eventEndInt);
                try {
                    manager.addEvent(event, user);
                } catch (ExecutionException | InterruptedException | IOException ex) {
                    ex.printStackTrace();
                }

                //find blocks to color out
                // draw string in the middle with event name
//                String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
//
//
//
//                for (int i = 0; i < 7; i++){
//                    if (eventDate == days[i]){
//                        int x = 25 + 100 * i;
//                    }
//                }



                // TODO: delete line below (only there for testing)
                System.out.println("Entities.Event added: " +eventName +" will occur on " +eventDate +" from " +eventStart +" to " +eventEnd);

            }
        }

        if (source == editEventButton) {
            JOptionPane.showMessageDialog(this, "(Feature not added)");
            // TODO: add feature
        }

        if (source == manageFriendsButton) {
            String[] manageFriendType = { "View current friends", "View friend requests", "Send friend request"};

            int buttonChoice = JOptionPane.showOptionDialog(null, "Manage Friends",
                    "What type of friends do you want to manage: ",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, manageFriendType, manageFriendType[0]);


            if (buttonChoice == JOptionPane.YES_OPTION) { // view current friends

                String allFriends = new String();

                for (Person friend : user.getUserFriends()) {
                    allFriends = allFriends + friend.getUserName() + "\n";
                }

                if (user.getUserFriends().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "You currently have no friends.");
                }
                else {
                    JOptionPane.showMessageDialog(this, "Current friends: \n" +allFriends);
                }


            }

            if (buttonChoice == JOptionPane.NO_OPTION) { // view friend requests
                JOptionPane.showMessageDialog(this, "(Feature not added)");
                // TODO: add feature
            }

            if (buttonChoice == JOptionPane.CANCEL_OPTION) { // send friend request
                JTextField friendRequestField = new JTextField();
                JOptionPane.showInputDialog(null, "Enter username: ", "Send friend request", JOptionPane.QUESTION_MESSAGE);
                String friendRequestName = friendRequestField.getText();
                System.out.println("Sent a friend request");
            }
        }


        if (source == compareSchedulesButton) {
            JOptionPane.showMessageDialog(this, "(Feature not added) text box will appear asking for friends username.");
            // TODO: add feature
        }


        if (source == helpButton) {
            String helpDialogue = ("Add Entities.Event: Press this button to add a new weekly event, with a title, date." +
                    "\nEdit Entities.Event: Edit or delete event details of a previously created event." +
                    "\nManage Friends: Send and accept friend requests, and view current friends here." +
                    "\nCompare Schedules: Compare your schedule availability with any added friends." +
                    "\nLogout: Press this button when you are ready to log out.");
            JOptionPane.showMessageDialog(this, helpDialogue);
        }


        if (source == logoutButton) {
            System.exit(0); // this exits the program
            // TODO: bring user back to login screen once implemented
        }

    }
}
