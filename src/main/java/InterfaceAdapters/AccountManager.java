package InterfaceAdapters;

import UseCaseClasses.AccountCreator;
import UseCaseClasses.AccountEditor;
import UseCaseClasses.AccountLogIn;
import UseCaseClasses.UserList;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class AccountManager {

    private final InformationSaver saver;
    private final AccountCreator creator;
    private final AccountEditor editor;
    private final AccountLogIn logIn;
    private final UserList data;

    public AccountManager() throws IOException {
        saver = new InformationSaver();
        creator = new AccountCreator();
        editor = new AccountEditor();
        data = new UserList();
        logIn = new AccountLogIn();
    }


    /**
     * Calls on UseCaseClasses.AccountCreator to create a new account
     *
     * @param name The username for the account, which must be unique
     * @param pass The password for the account
     * @param answer The answer to the security question that will be used for password changes / account recovery
     * @return true if the user account was successfully created and added
     */
    public boolean createAccount(String name, String pass, String answer)
            throws ExecutionException, InterruptedException {


        boolean result = creator.makeAccount(name, pass, answer, data);
        if (result) {
            saver.saveUser(name, data); // adds the user to the firestore database
        }
        return result;
    }

    /**
     * Calls on UseCaseClasses.AccountEditor to change the user's password
     *
     * @param name The username for the account that wishes to change their password
     * @param newPass The password for the account
     * @param answer The answer to the security question that will be used for password changes / account recovery
     * @return true if the user was able to change their password
     */
    public boolean changePassword(String name, String answer, String newPass, UserList data)
            throws ExecutionException, InterruptedException {

        if (data.containsUser(name)) { // check if user exists
            if (editor.securityCheck(data.getUser(name), answer)) {
                editor.changePassword(data.getUser(name), newPass);
                saver.saveUser(name, data); // updates firebase with new password
                return true;
            }
        }
        return false;
    }

    /**
     * Calls on AccountLogIn to verify the user's username and password
     *
     * @param username The username for the account that is being logged in to
     * @param password The password for the account that is being logged in to
     * @return true if the user was able to log in
     */
    public boolean runLogin(String username, String password) {
        return logIn.logIn(username, password, data);
    }

    /**
     * Adds every user in the database to UserList
     */
    public void populateUsers() throws ExecutionException, InterruptedException {
        saver.retrieveUsers(data);
    }

    public UserList getUserList() {
        return data;
    }

}