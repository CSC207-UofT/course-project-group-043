package FrameworksDrivers;

import Controllers.ScheduleManager;
import Entities.Event;
import Entities.Person;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class ScheduleGraphics extends JFrame implements ActionListener {
    // testing123 comment
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
    private JLabel mondayLabel;
    private JLabel tuesdayLabel;
    private JLabel wednesdayLabel;
    private JLabel thursdayLabel;
    private JLabel fridayLabel;
    private JLabel saturdayLabel;
    private JLabel sundayLabel;

    // addEventButton popup
    // TODO: keep declaration here, move creation inside
    private JTextField addEventName = new JTextField();
    private JComboBox addEventDate = new JComboBox(new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"});
    private String[] timesList = {"12:00am", "1:00am", "2:00am", "3:00am", "4:00am", "5:00am", "6:00am", "7:00am", "8:00am", "9:00am", "10:00am", "11:00am", "12:00pm", "1:00pm", "2:00pm", "3:00pm", "4:00pm", "5:00pm", "6:00pm", "7:00pm", "8:00pm", "9:00pm", "10:00pm", "11:00pm"};
    private JComboBox addEventStart = new JComboBox(timesList);
    private JComboBox addEventEnd = new JComboBox(timesList);


    public ScheduleGraphics() {
        super("Scheduler App");
        setSize(750, 750); // setting the default size of the frame
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

        mondayLabel = new JLabel("Monday");
        tuesdayLabel = new JLabel("Tuesday");
        wednesdayLabel = new JLabel("Wednesday");
        thursdayLabel = new JLabel("Thursday");
        fridayLabel = new JLabel("Friday");
        saturdayLabel = new JLabel("Saturday");
        sundayLabel = new JLabel("Sunday");

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
        titleLabel.setBounds(288, -5, 200, 50);
        buttonPanel.setBounds(7,35,700,200);

        mondayLabel.setBounds(116, 76, 70, 40);
        tuesdayLabel.setBounds(185, 76, 70, 40);
        wednesdayLabel.setBounds(248, 76, 70, 40);
        thursdayLabel.setBounds(325, 76, 70, 40);
        fridayLabel.setBounds(400, 76, 70, 40);
        saturdayLabel.setBounds(465, 76, 70, 40);
        sundayLabel.setBounds(536, 76, 70, 40);


        // adding to JPanel
        add(titleLabel);

        add(mondayLabel);
        add(tuesdayLabel);
        add(wednesdayLabel);
        add(thursdayLabel);
        add(fridayLabel);
        add(saturdayLabel);
        add(sundayLabel);

        add(buttonPanel);

        setLocationRelativeTo(null);

        setVisible(true);

    }

    public void paint(Graphics g) {
        super.paint(g);

        g2g = (Graphics2D)g;

        // creating custom colors
        Color color1 = new Color(110, 205, 250);
        Color color2 = new Color(39, 208, 245);
        Color color3 = new Color(0, 210, 231);
        Color color4 = new Color(0, 211, 207);
        Color color5 = new Color(0, 210, 175);
        Color color6 = new Color(0, 207, 137);
        Color color7 = new Color(65, 202, 94);

        Color[] colorsList = {color1, color2, color3, color4, color5, color6, color7};

        // TODO: make schedule grid larger


        // drawing the day of week header boxes & outlines
        for (int x = 0; x < 7; x++) {
            g2g.setColor(colorsList[x]); // different color for each day slot
            g2g.fillRect(25 + (x * 100), 100, 100, 40);
        }

        g2g.setColor(Color.white);
        for (int x = 0; x < 7; x++) {
            g2g.drawRect(25 + (x * 100), 100, 100, 40);
        }

        // drawing the empty schedule outlines
        for (int x = 0; x < 7; x++) {
            for (int i = 0; i < 24; i++) {
                g2g.drawRect(25 + (x * 100), 140 + (i * 25), 100, 25);
            }
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
        Object source = e.getSource();

        // the following code will run if a given button is pressed


        if (source == addEventButton) {
            Object[] addEventText = {"Name:", addEventName, "Date:", addEventDate, "Start time:", addEventStart, "End time:", addEventEnd};

            int buttonChoice = JOptionPane.showConfirmDialog(null, addEventText, "Add Entities.Event", JOptionPane.OK_CANCEL_OPTION);

            if (buttonChoice == JOptionPane.OK_OPTION) {
                // will interact with Entities.Event class

                // temporarily assigning the values to temporary variables
                String eventName = addEventName.getText();
                String eventDate = (String) addEventDate.getSelectedItem();

                String eventStart = (String) addEventStart.getSelectedItem();
                String eventStartString = eventStart.split(":")[0];
                int eventStartInt = Integer.valueOf(eventStartString);

                String eventEnd = (String) addEventEnd.getSelectedItem();
                String eventEndString = eventEnd.split(":")[0];
                int eventEndInt = Integer.valueOf(eventEndString);


                Event event = new Event(eventName, eventDate, eventStartInt, eventEndInt);
                try {
                    manager.addEvent(event, user);
                } catch (ExecutionException | InterruptedException | IOException ex) {
                    ex.printStackTrace();
                }

                // TODO: delete line below (only there for testing)
                System.out.println("Entities.Event added: " +eventName +" will occur on " +eventDate +" from " +eventStart +" to " +eventEnd);

            }
        }

        if (source == editEventButton) {
            JOptionPane.showMessageDialog(this, "(Feature not added)");
            // TODO: add feature
        }

        if (source == manageFriendsButton) {
            Object[] manageFriendType = { "View current friends", "View friend requests", "Send friend request"};

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
