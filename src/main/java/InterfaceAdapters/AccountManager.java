package InterfaceAdapters;

//import Entities.Person;
import UseCaseClasses.AccountCreator;
import UseCaseClasses.AccountEditor;
import UseCaseClasses.InformationSaver;
import UseCaseClasses.UserList;
import com.google.cloud.storage.Acl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

// TODO: is this a facade??

public class AccountManager {

//    private HashMap<String, Person> users;
    InformationSaver saver;
    AccountCreator creator;
    AccountEditor editor;
    UserList data; // todo: populate this with users from database?

    public AccountManager() throws IOException {
//        InformationSaver saver = new InformationSaver();
//        AccountCreator creator = new AccountCreator();
//        AccountEditor editor = new AccountEditor();
//        UserList data = new UserList();
    }

//    public AccountManager() {
//        users = new HashMap<>();
//    }

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

//        Person user = new Person();

        boolean result = creator.makeAccount(name, pass, answer, data);
        if (result) {
//            users.add(name, u); // adds the user to the hashmap of users
            saver.saveUser(data.getUser(name)); // adds the user to the firestore database
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
            throws IOException, ExecutionException, InterruptedException {

        InformationSaver saver = new InformationSaver();
        AccountEditor editor = new AccountEditor();

        if (data.containsUser(name)) { // check if user exists
//            Person user = this.users.get(name);
            if (editor.securityCheck(data.getUser(name), answer)) {
                editor.changePassword(data.getUser(name), newPass);
                saver.saveUser(data.getUser(name)); // updates firebase with new password
                return true;
            }
        }
        return false;
    }

//    /**
//     * Retrieves all users from database and adds their names as keys in users, and the Entities.Person object as values.
//     */
//    public void populateUsers(UserList data) throws ExecutionException, InterruptedException {
////        ArrayList<Person> people = saver.retrieveUsers();
//        for (String a : data.getUsers()) {
//            this.users.put(a.getUserName(), a);
//        }
//    }

//    public HashMap<String, Person> getUsers() {
//        return users;
//    }
}