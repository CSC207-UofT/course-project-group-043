package FrameworksDrivers;


import Entities.Person;
import Entities.events.Event;
import InterfaceAdapters.ScheduleManager;
import UseCaseClasses.ScheduleEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class ScheduleGraphics extends JFrame {

    ScheduleManager manager = new ScheduleManager();
    Person user = new Person("user", "1234"); // making a temporary person (for testing purposes)

    private JButton addEventButton = new JButton("Add Event");
    private JButton manageFriendsButton = new JButton("Manage Friends");
    private JButton compareSchedulesButton = new JButton("Compare Schedules");
    private JButton helpButton = new JButton("Help");
    private JButton logoutButton = new JButton("Logout");

    private JTextField addEventName = new JTextField();
    private JTextField addFriendName = new JTextField();
    private JComboBox addEventDate = new JComboBox(new String[]{"Monday", "Tuesday", "Wednesday",
            "Thursday", "Friday", "Saturday", "Sunday"});
    private JComboBox addEventType = new JComboBox(new String[]{"Academic", "Course", "Fitness",
            "Social"});
    private String[] timesList = {"00:00am", "1:00am", "2:00am", "3:00am", "4:00am", "5:00am", "6:00am",
            "7:00am", "8:00am", "9:00am", "10:00am", "11:00am", "12:00pm", "13:00pm", "14:00pm", "15:00pm",
            "16:00pm", "17:00pm", "18:00pm", "19:00pm", "20:00pm", "21:00pm", "22:00pm", "23:00pm"};
    private JComboBox addEventStart = new JComboBox(timesList);
    private JComboBox addEventEnd = new JComboBox(timesList);


    ScheduleDrawing scheduleDrawing = new ScheduleDrawing(manager, user);

    public ScheduleGraphics(){

        super("Schedule App");

        setSize(900, 1200); // setting the default size of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // when pressing the x button, progarm will close

        setLayout(null);

        AddEventListener addEventListener = new AddEventListener();
        ManageFriendsListener manageFriendsListener = new ManageFriendsListener();
        CompareScheduleListener compareScheduleListener = new CompareScheduleListener();
        HelpListener helpListener = new HelpListener();


        addEventButton.addActionListener(addEventListener);
        manageFriendsButton.addActionListener(manageFriendsListener);
        compareSchedulesButton.addActionListener(compareScheduleListener);
        helpButton.addActionListener(helpListener);

        JPanel buttonPanel = new JPanel();

        JLabel titleLabel = new JLabel("Scheduler App");
        titleLabel.setFont(new Font("Verdana", Font.PLAIN, 18));


        buttonPanel.add(addEventButton);
        buttonPanel.add(manageFriendsButton);
        buttonPanel.add(compareSchedulesButton);
        buttonPanel.add(helpButton);
        buttonPanel.add(logoutButton);

        titleLabel.setBounds(400, -5, 200, 50);

        buttonPanel.setBounds(25,35,900,50);

        // adding to JPanel
        add(titleLabel);

        add(buttonPanel);

        scheduleDrawing.setBounds(25,0, 900, 1000);

        add(scheduleDrawing);


        setVisible(true);
        setLocationRelativeTo(null);

    }


    private class AddEventListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            Object source = e.getSource();

            // the following code will run if a given button is pressed


            if (source == addEventButton) {
                Object[] addEventText = {"Type:", addEventType, "Name:", addEventName, "Date:", addEventDate, "Start time:", addEventStart, "End time:", addEventEnd};

                int buttonChoice = JOptionPane.showConfirmDialog(null, addEventText, "Add Entities.Event", JOptionPane.OK_CANCEL_OPTION);

                if (buttonChoice == JOptionPane.OK_OPTION) {
                    // will interact with Entities.Event class

                    // temporarily assigning the values to temporary variables
                    String eventType = (String) addEventType.getSelectedItem();
                    String eventName = addEventName.getText();
                    String eventDate = (String) addEventDate.getSelectedItem();

                    String eventStart = (String) addEventStart.getSelectedItem();
                    String eventStartString = eventStart.split(":")[0];
                    int eventStartInt = Integer.parseInt(eventStartString);

                    String eventEnd = (String) addEventEnd.getSelectedItem();
                    String eventEndString = eventEnd.split(":")[0];
                    int eventEndInt = Integer.parseInt(eventEndString);

                    manager.addEvent(eventType, eventName, eventDate, eventStartInt, eventEndInt, user);

                    repaint();

                }
            }
        }

    }



    private class ManageFriendsListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            Object source = e.getSource();
            if (source == manageFriendsButton) {
                Object[] manageFriendType = {"View current friends", "View friend requests", "Send friend request"};

                int buttonChoice = JOptionPane.showOptionDialog(null, "Manage Friends",
                        "What type of friends do you want to manage: ", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, manageFriendType, manageFriendType[0]);


            }
        }
    }

    private class CompareScheduleListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            Object source = e.getSource();

            if (source == compareSchedulesButton) {
                JPanel compareSchedule = new JPanel();
                compareSchedule.setLayout(new BoxLayout(compareSchedule, BoxLayout.Y_AXIS));
                compareSchedule.add(new JLabel("Friend:"));
                compareSchedule.add(addFriendName);

                int buttonChoice = JOptionPane.showConfirmDialog(null, compareSchedule, "Compare Schedules", JOptionPane.OK_CANCEL_OPTION);
                if (buttonChoice == JOptionPane.OK_OPTION) {

                    String friendName = addFriendName.getText();
                    //adding a user with events for testing
                    Person test = new Person(friendName, "123");
                    manager.addEvent("Academic", "CSC207", "Friday", 16, 17, test);
                    manager.addEvent("Academic", "MAT334", "Friday", 17, 21, test);
                    user.getUserFriends().add(test);

                    for (Person user : user.getUserFriends()) {
                        if (user.getUserName().equals(friendName)) {
                            CompareGraphics gf = new CompareGraphics(user, test, manager);
                        }
                    }

//                    else {
//                        JOptionPane.showMessageDialog(this, "That user is not on your friends list");
//                    }
                }
            }

        }
    }

    private class HelpListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            Object source = e.getSource();

            if (source == helpButton) {
                String helpDialogue = ("Add Entities.Event: Press this button to add a new weekly event, with a title, date." +
                        "\nEdit Entities.Event: Edit or delete event details of a previously created event." +
                        "\nManage Friends: Send and accept friend requests, and view current friends here." +
                        "\nCompare Schedules: Compare your schedule availability with any added friends." +
                        "\nLogout: Press this button when you are ready to log out.");

            }

        }
    }

    public static void main (String[] arguments) {

        ScheduleGraphics gf = new ScheduleGraphics();


    }
}

