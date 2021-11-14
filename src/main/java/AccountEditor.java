import java.util.HashMap;
import java.util.Objects;

public class AccountEditor {

    public void changePassword(Person user, String newPass) {
        user.setUserPassword(newPass);
    }

    public boolean securityCheck(Person user, String answer) {
        return user.getTrustedAnswer().equals(answer);
    }

    public boolean userExists(String name, HashMap<String, Person> data) {
        return data.containsKey(name);
    }
}
