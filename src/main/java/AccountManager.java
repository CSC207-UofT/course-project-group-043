import java.util.HashMap;
import java.util.Scanner;

public class AccountManager {

    private HashMap<String, Person> users;

    public AccountManager() {
        users = new HashMap<>();
    }

    public boolean createAccount() {

        AccountCreator creator = new AccountCreator();
        Person user = new Person();

        Scanner in = new Scanner (System.in);
        System.out.println("What is your desired user name?");
        String name = in.nextLine();
        System.out.println("What is your password?");
        String pass = in.nextLine();
        System.out.println("What is the answer to your security question?");
        String answer = in.nextLine();
        boolean result = creator.makeAccount(user, name, pass, answer, this.users);
        if (result) {
            users.put(name, user); // adds the user to the hashmap of users
        }
        return result;
    }

    public boolean changePassword() {

        AccountEditor editor = new AccountEditor();
        Scanner in = new Scanner (System.in);

        System.out.println("What is the name of your account?");
        String name = in.nextLine();
        if (editor.userExists(name, this.users)) {
            Person user = this.users.get(name);
            System.out.println("INSERT SECURITY QUESTION HERE");
            String answer = in.nextLine();
            if (editor.securityCheck(user, answer)) {
                System.out.println("Please enter your new password.");
                String newPass = in.nextLine();
                editor.changePassword(user, newPass);
                return true;
            }
        }
        return false;
    }
}
