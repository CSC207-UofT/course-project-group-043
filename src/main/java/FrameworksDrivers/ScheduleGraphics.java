package FrameworksDrivers;


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
import java.util.ArrayList;

public class ScheduleGraphics extends JFrame {


    Person user = new Person("user", "1234"); // making a temporary person (for testing purposes)


    private JButton addEventButton = new JButton("Add Event");
    //    private JButton editEventButton = new JButton("Edit Event");
    private JButton manageFriendsButton = new JButton("Manage Friends");
    private JButton compareSchedulesButton = new JButton("Compare Schedules");
    private JButton helpButton = new JButton("Help");
    private JButton logoutButton = new JButton("Logout");

    private JTextField addEventName = new JTextField();
    private JComboBox addEventDate = new JComboBox(new String[]{"Monday", "Tuesday", "Wednesday",
            "Thursday", "Friday", "Saturday", "Sunday"});
    private String[] timesList = {"00:00", "1:00", "2:00", "3:00", "4:00", "5:00", "6:00",
            "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00",
            "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"};
    private String[] eventTypesList = {"Academic", "Course", "Fitness", "Social"};
    private JComboBox addEventStart = new JComboBox(timesList);
    private JComboBox addEventEnd = new JComboBox(timesList);
    private JComboBox addEventType = new JComboBox(eventTypesList);


    ArrayList<Event> eventsSchedule = new ArrayList<>();

    ScheduleDrawing scheduleDrawing = new ScheduleDrawing(eventsSchedule);

    public ScheduleGraphics(){

        super("Schedule App");

        setSize(900, 1200); // setting the default size of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // when pressing the x button, progarm will close

        setLayout(null);

        AddEventListener addEventListener = new AddEventListener();
//        EditEventListener editEventListener = new EditEventListener();
        ManageFriendsListener manageFriendsListener = new ManageFriendsListener();
        CompareScheduleListener compareScheduleListener = new CompareScheduleListener();
        HelpListener helpListener = new HelpListener();


        addEventButton.addActionListener(addEventListener);
//        editEventButton.addActionListener(editEventListener);
        manageFriendsButton.addActionListener(manageFriendsListener);
        compareSchedulesButton.addActionListener(compareScheduleListener);
        helpButton.addActionListener(helpListener);

        JPanel buttonPanel = new JPanel();

        JLabel titleLabel = new JLabel("Scheduler App");
        titleLabel.setFont(new Font("Verdana", Font.PLAIN, 18));


        buttonPanel.add(addEventButton);
//        buttonPanel.add(editEventButton);
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
//        for (int i = 0; i < scheduleDrawing.buttons.size(); i++){
//            scheduleDrawing.buttons.get(i).addActionListener(editEventListener);
//        }
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
                Object[] addEventText = {"Name:", addEventName, "Date:", addEventDate, "Start time:", addEventStart, "End time:", addEventEnd, "Event type:", addEventType};

                int buttonChoice = JOptionPane.showConfirmDialog(null, addEventText, "Add Event", JOptionPane.OK_CANCEL_OPTION);

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

                    // TODO: collect event type and save it


                    // TODO: change how event is created (using ScehduleManager)
                    Event event = new Event(eventName, eventDate, eventStartInt, eventEndInt);

                    scheduleDrawing.schedule.add(event);
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

                if (buttonChoice == JOptionPane.YES_OPTION) { // view currents friends
                    String allFriends = new String();

                    for (Person friend : user.getUserFriends()) {
                        allFriends = allFriends + friend.getUserName() + "\n";
                    }

                    if (allFriends.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "You currently have no friends.");
                    }

                    else {
                        JOptionPane.showMessageDialog(null, "Current friends: \n" +allFriends);
                    }
                }



                if (buttonChoice == JOptionPane.NO_OPTION) { // view friend requests
                    // TODO: do as comments say
                    /*
                    JComboBox listing all current friend requests
                    Cancel, decline, and accept buttons
                     */
                }

                if (buttonChoice == JOptionPane.CANCEL_OPTION) { // send friend request
                    JTextField friendRequestField = new JTextField();
                    JOptionPane.showInputDialog(null, "Enter username: ", "Send friend request", JOptionPane.QUESTION_MESSAGE);
                    String friendRequestName = friendRequestField.getText();
                    // TODO: call on FriendAdder to send friend request
                }



            }
        }
    }

    private class CompareScheduleListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            Object source = e.getSource();

            if (source == compareSchedulesButton) {

            }

        }
    }

    private class HelpListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            Object source = e.getSource();

            if (source == helpButton) {
                String helpDialogue = ("Add Event: Press this button to add a new weekly event, with a title, date." +
                        "\nEdit Event: Edit or delete event details of a previously created event." +
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

