package UseCaseClasses;

import Entities.Person;

/**
 * Responsible for logging a user in.
 */
public class AccountLogIn {

    /**
     * Log a user in based on the provided credentials.
     *
     * @param username The given username
     * @param password The given password
     * @param users List of all usernames in use thus far, with associated Person
     * @return true if the provided username and password are associated with an account
     */
    public boolean logIn(String username, String password, UserList users) {
        if (users.getUsers().containsKey(username)) {
            Person user = users.getUser(username);
            return user.getUserPassword().equals(password);
        }
        else {
            return false;
        }
    }
}
