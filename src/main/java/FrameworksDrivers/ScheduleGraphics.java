package FrameworksDrivers;

import InterfaceAdapters.AccountManager;
import InterfaceAdapters.ScheduleManager;
import InterfaceAdapters.InformationSaver;
import UseCaseClasses.FriendAdder;
import UseCaseClasses.UserList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class ScheduleGraphics extends JFrame {

    ScheduleManager manager = new ScheduleManager();
    AccountManager accountManager = new AccountManager();
    InformationSaver saver = new InformationSaver();
    String username;

    private final JButton addEventButton = new JButton("Add Event");
    private final JButton manageFriendsButton = new JButton("Manage Friends");
    private final JButton compareSchedulesButton = new JButton("Compare Schedules");
    private final JButton helpButton = new JButton("Help");
    private final JButton logoutButton = new JButton("Logout");

    private final JTextField addEventName = new JTextField();
    private final JTextField addFriendName = new JTextField();
    private final JComboBox addEventDate = new JComboBox(new String[]{"Monday", "Tuesday", "Wednesday",
            "Thursday", "Friday", "Saturday", "Sunday"});
    private final JComboBox addEventType = new JComboBox(new String[]{"Academic", "Course", "Fitness",
            "Social"});
    private final String[] timesList = {"00:00am", "1:00am", "2:00am", "3:00am", "4:00am", "5:00am", "6:00am",
            "7:00am", "8:00am", "9:00am", "10:00am", "11:00am", "12:00pm", "13:00pm", "14:00pm", "15:00pm",
            "16:00pm", "17:00pm", "18:00pm", "19:00pm", "20:00pm", "21:00pm", "22:00pm", "23:00pm"};
    private final JComboBox addEventStart = new JComboBox(timesList);
    private final JComboBox addEventEnd = new JComboBox(timesList);

    public ScheduleGraphics(String username) throws IOException, ExecutionException, InterruptedException {
        super("Schedule App");
        this.username = username;
        accountManager.populateUsers();
        saver.retrieveFriends(username, accountManager.getUserList());
        saver.retrieveOutgoing(username, accountManager.getUserList());
        saver.retrieveIncoming(username, accountManager.getUserList());
        saver.retrieveEvents(username, accountManager.getUserList());

        ScheduleDrawing scheduleDrawing = new ScheduleDrawing(username, accountManager);

        setSize(900, 900); // setting the default size of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // when pressing the x button, program will close
        setLayout(null);

        AddEventListener addEventListener = new AddEventListener();
        ManageFriendsListener manageFriendsListener = new ManageFriendsListener();
        CompareScheduleListener compareScheduleListener = new CompareScheduleListener();
        HelpListener helpListener = new HelpListener();
        LogOutListener logOutListener = new LogOutListener();

        addEventButton.addActionListener(addEventListener);
        manageFriendsButton.addActionListener(manageFriendsListener);
        compareSchedulesButton.addActionListener(compareScheduleListener);
        helpButton.addActionListener(helpListener);
        logoutButton.addActionListener(logOutListener);

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

            if (source == addEventButton) {
                Object[] addEventText = {"Type:", addEventType, "Name:", addEventName, "Date:", addEventDate, "Start time:", addEventStart, "End time:", addEventEnd};

                int buttonChoice = JOptionPane.showConfirmDialog(null, addEventText, "Add Entities.Event", JOptionPane.OK_CANCEL_OPTION);

                if (buttonChoice == JOptionPane.OK_OPTION) {

                    String eventType = (String) addEventType.getSelectedItem();
                    String eventName = addEventName.getText();
                    String eventDate = (String) addEventDate.getSelectedItem();

                    String eventStart = (String) addEventStart.getSelectedItem();
                    assert eventStart != null;
                    String eventStartString = eventStart.split(":")[0];
                    int eventStartInt = Integer.parseInt(eventStartString);

                    String eventEnd = (String) addEventEnd.getSelectedItem();
                    assert eventEnd != null;
                    String eventEndString = eventEnd.split(":")[0];
                    int eventEndInt = Integer.parseInt(eventEndString);

                    try {
                        manager.addEvent(eventType, eventName, eventDate, eventStartInt, eventEndInt, accountManager.getUserList().getUser(username));
                    } catch (ExecutionException | InterruptedException ex) {
                        ex.printStackTrace();
                    }
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

                if (buttonChoice == JOptionPane.YES_OPTION) { // view current friends
                    String allFriends = new String();

                    for (Person friend : user.getUserFriends()) {
                        allFriends = allFriends + friends.getUserName() + "\n";
                    }
                    if (allFriends.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "You currently have no friends.");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Current friends: \n" +allFriends);
                    }


                }

                if (buttonChoice == JOptionPane.NO_OPTION) { // view friend requests
                    Object[] friendRequests = user.getIncomingRequests().toArray();

                    String chosenFriendRequest = (String)JOptionPane.showInputDialog(null,
                            "Select an incomming friend request: \n(OK to accept, or CANCEL to deny)\n", "Manage Friend Requests", JOptionPane.PLAIN_MESSAGE,
                            null, friendRequests, null);

                    if (buttonChoice == JOptionPane.OK_OPTION) { // accept friend request
                        user.acceptFriendRequest(chosenFriendRequest, username);
                        JOptionPane.showInputDialog("You have become friends with " +chosenFriendRequest);
                    }
                    else { // deny friend request
                        user.denyFriendRequest(chosenFriendRequest, username);
                        JOptionPane.showInputDialog("You declined the friend request from " +chosenFriendRequest);
                    }

                }

                if (buttonChoice == JOptionPane.CANCEL_OPTION) { // send friend request
                    String friendRequestName = JOptionPane.showInputDialog(null, "Enter username: ", "Send friend request", JOptionPane.QUESTION_MESSAGE);
                    Object[] done = {"Done"};

                    if (UserList.containsUser(friendRequestName)) {
                        FriendAdder.sendFriendRequest(user, UserList.getUser(friendRequestName));
                        JOptionPane.showOptionDialog(null, "Friend request to " +friendRequestName +" has been sent.", "Friend request sent", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, done, null);
                    }
                    else { // if the user does not exist
                        JOptionPane.showOptionDialog(null, "User " +friendRequestName +" does not seem to exist, no request sent.", "No friend request sent", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, done, null);
                    }


                }

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

                    if (accountManager.getUserList().getUser(username).getUserFriends().contains(accountManager.getUserList().getUser(friendName))) {
                        try {
                            saver.retrieveEvents(friendName, accountManager.getUserList());
                        } catch (ExecutionException | InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            CompareGraphics gf = new CompareGraphics(username, friendName, accountManager);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "That user is not on your friends list");
                    }
                }
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

                JOptionPane.showMessageDialog(null, helpDialogue);
            }
        }
    }

    private class LogOutListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            Object source = e.getSource();

            if (source == logoutButton) {
                try {
                    setVisible(false);
                    new LogInScreen();
                } catch (IOException | ExecutionException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

        }
    }
}

