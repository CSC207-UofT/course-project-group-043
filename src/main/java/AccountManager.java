import java.util.HashMap;
import java.util.Scanner;

public class AccountManager {

    private HashMap<String, Person> users;

    public AccountManager() {
        users = new HashMap<>();
    }

    public boolean createAccount(String name, String pass, String answer) {

        AccountCreator creator = new AccountCreator();
        Person user = new Person();

        boolean result = creator.makeAccount(user, name, pass, answer, this.users);
        if (result) {
            users.put(name, user); // adds the user to the hashmap of users
        }
        return result;
    }

    public boolean changePassword(String name, String answer, String newPass) {

        AccountEditor editor = new AccountEditor();
        if (editor.userExists(name, this.users)) { // check if user exists
            Person user = this.users.get(name);
            if (editor.securityCheck(user, answer)) {
                editor.changePassword(user, newPass);
                return true;
            }
        }
        return false;
    }
}