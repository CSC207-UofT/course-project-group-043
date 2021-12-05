package InterfaceAdapters;

import UseCaseClasses.AccountCreator;
import UseCaseClasses.AccountEditor;
import UseCaseClasses.InformationSaver;
import UseCaseClasses.UserList;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

// TODO: is this a facade??

public class AccountManager {

    private InformationSaver saver;
    private AccountCreator creator;
    private AccountEditor editor;
    private UserList data; // todo: populate this with users from database?


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

        if (data.containsUser(name)) { // check if user exists
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

}