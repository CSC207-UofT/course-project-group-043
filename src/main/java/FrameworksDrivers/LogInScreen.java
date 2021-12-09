package FrameworksDrivers;

import InterfaceAdapters.AccountManager;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Starting point for running the program. Responsible for
 * creating a UI that allows the user to log into their account.
 */
public class LogInScreen extends JFrame {

    AccountManager manager = new AccountManager();

    /**
     * Creates a screen for the user to log in or create a new account
     *
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public LogInScreen() throws IOException, ExecutionException, InterruptedException { // This will run the Log In screen of the Program
        String[] buttons = {"Log In", "Create an Account", "Exit"};

        int rc = JOptionPane.showOptionDialog(null, "Welcome to Group 043's Scheduling App", "Welcome",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, buttons[1]);

        JTextField addUserName = new JTextField();
        JTextField addUserPassword = new JTextField();
        if (rc == JOptionPane.YES_OPTION) { // If the user presses Log In
            Object[] addEventText = {"Username:", addUserName, "Password:", addUserPassword};
            int buttonChoice = JOptionPane.showConfirmDialog(null, addEventText, "Schedule Log In", JOptionPane.OK_CANCEL_OPTION);
            if (buttonChoice == JOptionPane.OK_OPTION) {
                String username = addUserName.getText();
                String password = addUserPassword.getText();
                manager.populateUsers();
                if (manager.runLogin(username, password)) {
                    new ScheduleGraphics(username);
                }
                else {
                    JOptionPane.showConfirmDialog(null, "Your username or password is incorrect", "Error", JOptionPane.DEFAULT_OPTION);
                    new LogInScreen();
                }
            }
            if (buttonChoice == JOptionPane.CANCEL_OPTION) { // If the user presses cancel
                new LogInScreen();
            }
        }
        if (rc == JOptionPane.NO_OPTION) { // If the user presses Creat Account
            Object[] addEventText = {"Username:", addUserName, "Password:", addUserPassword};
            int buttonChoice = JOptionPane.showConfirmDialog(null, addEventText, "Create an Account", JOptionPane.OK_CANCEL_OPTION);
            String username = addUserName.getText();
            String password = addUserPassword.getText();
            if (buttonChoice == JOptionPane.OK_OPTION) { // If the user presses create account
                manager.populateUsers();
                if (manager.createAccount(username, password, "test")) {
                    ScheduleGraphics gf = new ScheduleGraphics(username);
                }
                else {
                    JOptionPane.showConfirmDialog(null, "That username is already taken", "Error", JOptionPane.DEFAULT_OPTION);
                    new LogInScreen();
                }
            }
            if (buttonChoice == JOptionPane.CANCEL_OPTION) { // If the user presses cancel
                new LogInScreen();
            }
        }
        if (rc == JOptionPane.CANCEL_OPTION) { // if the user presses exit
            System.exit(0);
        }
    }

    public static void main (String[] arguments) throws IOException, ExecutionException, InterruptedException {
        new LogInScreen();
    }
}
