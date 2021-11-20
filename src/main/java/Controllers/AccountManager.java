package Controllers;

import Entities.Person;
import UseCaseClasses.AccountCreator;
import UseCaseClasses.AccountEditor;
import UseCaseClasses.InformationSaver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class AccountManager {

    private HashMap<String, Person> users;

    public AccountManager() {
        users = new HashMap<>();
    }

    /**
     * Calls on UseCaseClasses.AccountCreator to create a new account
     *
     * @param name The username for the account, which must be unique
     * @param pass The password for the account
     * @param answer The answer to the security question that will be used for password changes / account recovery
     * @return true if the user account was successfully created and added
     */

    public boolean createAccount(String name, String pass, String answer) throws IOException, ExecutionException, InterruptedException {

        InformationSaver saver = new InformationSaver();
        AccountCreator creator = new AccountCreator();
        Person user = new Person();

        boolean result = creator.makeAccount(user, name, pass, answer, this.users);
        if (result) {
            users.put(name, user); // adds the user to the hashmap of users
            saver.saveUser(users.get(name)); // adds the user to the firestore database
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

    public boolean changePassword(String name, String answer, String newPass) throws IOException, ExecutionException, InterruptedException {

        InformationSaver saver = new InformationSaver();
        AccountEditor editor = new AccountEditor();

        if (editor.userExists(name, this.users)) { // check if user exists
            Person user = this.users.get(name);
            if (editor.securityCheck(user, answer)) {
                editor.changePassword(user, newPass);
                saver.saveUser(users.get(name)); // updates firebase with new password
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves all users from database and adds their names as keys in users, and the Entities.Person object as values.
     */

    public void populateUsers() throws ExecutionException, InterruptedException, IOException {
        InformationSaver saver = new InformationSaver();

        ArrayList<Person> people = saver.retrieveUsers();
        for (Person a : people) {
            this.users.put(a.getUserName(), a);
        }
    }

    public HashMap<String, Person> getUsers() {
        return users;
    }
}