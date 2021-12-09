package UseCaseClasses;

import Entities.Person;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

/**
 * Responsible for creating a new account for a user.
 */
public class AccountCreator {

    /**
     * Create a new account for a Person.
     *
     * @param name The username for the account, which must be unique
     * @param pass The password for the account
     * @param answer The answer to the security question that will be used for password changes / account recovery
     * @param data HashMap containing all usernames in use thus far, with associated Person
     * @return true if the user account was successfully created and added
     */
    public boolean makeAccount(String name, String pass, String answer, UserList data) {

        boolean check = isValidName(name, data);
        Person newUser = new Person();

        if (check) {
            newUser.setUserName(name);
            newUser.setUserPassword(pass);
            newUser.setTrustedAnswer(answer);
            data.addUser(name, newUser);
        }

        return check;
    }

    /**
     * Check if the user's desired name is taken
     *
     * @param name The desired username
     * @param data HashMap containing all usernames in use thus far, with associated Person
     * @return true if the user's desired name is not taken
     */
    private boolean isValidName(String name, UserList data) {
        return !data.containsUser(name);
    }

}
