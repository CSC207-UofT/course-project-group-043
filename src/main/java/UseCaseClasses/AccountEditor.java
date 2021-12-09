package UseCaseClasses;

import Entities.Person;

/**
 * Responsible for editing account details like passwords.
 */
public class AccountEditor {

    /**
     * Sets a new password for a Person
     *
     * @param user The Person that is changing their password
     * @param newPass The new password associated with user
     */
    public void changePassword(Person user, String newPass) {
        user.setUserPassword(newPass);
    }

    /**
     * Check if the given security answer matches the security answer associated with user
     *
     * @param user The Person whose security answer is being checked
     * @param answer The given security answer
     */
    public boolean securityCheck(Person user, String answer) {
        return user.getTrustedAnswer().equals(answer);
    }

    /**
     * Check if the given user exists
     *
     * @param name The name of the account being checked for existence
     * @param data HashMap containing all usernames in use thus far, with associated Entities.Person
     * @return true if a user with the given name currently exists
     */
    public boolean userExists(String name, UserList data) {
        return data.containsUser(name);
    }
}
