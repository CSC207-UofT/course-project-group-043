package UseCaseClasses;

import Entities.Person;

import java.util.HashMap;

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
