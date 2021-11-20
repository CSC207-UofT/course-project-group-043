package UseCaseClasses;

import Entities.Person;

import java.util.HashMap;

public class AccountCreator {

    // need to keep all our info about who has what accounts stored somewhere
    // consider a database?
    // store all users in a hashmap?
    // how to store between runs of program? shared access to it?

    // maybe accountmanager has a hashmap of all the users' usernames (and the values are person objects) of this program??
    // if yes, the following code works

    /**
     * Create a new account for a Entities.Person.
     *
     * @param user The Entities.Person object that this is an account for
     * @param name The username for the account, which must be unique
     * @param pass The password for the account
     * @param answer The answer to the security question that will be used for password changes / account recovery
     * @param data HashMap containing all usernames in use thus far, with associated Entities.Person
     * @return true if the user account was successfully created and added
     */
    public boolean makeAccount(Person user, String name, String pass, String answer, HashMap<String, Person> data) {

        boolean check = isValidName(name, data);

        if (check) {
            user.setUserName(name);
            user.setUserPassword(pass);
            user.setTrustedAnswer(answer);
            data.put(name, user);
        }

        return check;
    }

    private boolean isValidName(String name, HashMap<String, Person> data) {
        return !data.containsKey(name);
    }

}
