import java.util.HashMap;

public class AccountCreator {

    // need to keep all our info about who has what accounts stored somewhere
    // consider a database?
    // store all users in a hashmap?

    // maybe accountmanager has a hashmap of all the users ( and their passwords ) of this program??
    // if yes, the following code works

    // todo: add javadoc, note it returns boolean if user successfully added
    public boolean makeAccount(Person user, String name, String pass, HashMap<String, String> data) {

        boolean check = isValidName(name, data);

        if (check) {
            user.setUserName(name);
            user.setUserPassword(pass);
        }

        return check;
    }

    private boolean isValidName(String name, HashMap<String, String> data) {
        return !data.containsKey(name);
    }

}
