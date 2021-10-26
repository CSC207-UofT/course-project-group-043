import java.util.HashMap;

public class AccountCreator {

    // need to keep all our info about who has what accounts stored somewhere
    // consider a database?
    // store all users in a hashmap?

    // maybe accountmanager has a hashmap of all the users ( and their passwords ) of this program??
    // if yes, the following code works

    /**
     * Create a new account for a Person.
     *
     * @param user The Person object that this is an account for
     * @param name The username for the account, which must be unique
     * @param pass The password for the account
     * @param answer The answer to the 'trusted question' that will be used for password changes / account recovery
     * @param data HashMap containing all users created thus far
     * @return true if the user account was successfully created and added
     */
    public boolean makeAccount(Person user, String name, String pass, String answer, HashMap<String, String> data) {

        boolean check = isValidName(name, data);

        if (check) {
            user.setUserName(name);
            user.setUserPassword(pass);
            user.setTrustedAnswer(answer);
            data.put(name, pass);
        }

        return check;
    }

    private boolean isValidName(String name, HashMap<String, String> data) {
        return !data.containsKey(name);
    }

}
