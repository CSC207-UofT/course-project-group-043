package UseCaseClasses;

import Entities.Person;

import java.util.HashMap;

public class AccountCreator {

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
    public boolean makeAccount(String name, String pass, String answer, UserList data, InformationSaver saver) {

        boolean check = isValidName(name, data);
        Person newUser = new Person();

        if (check) {
            newUser.setUserName(name);
            newUser.setUserPassword(pass);
            newUser.setTrustedAnswer(answer);
            data.addUser(name, newUser);
            saver.saveUser(data.getUser(name)); // adds the user to the firestore database
        }

        return check;
    }

    private boolean isValidName(String name, HashMap<String, Person> data) {
        return !data.containsKey(name);
    }

}
