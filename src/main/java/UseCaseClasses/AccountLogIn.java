package UseCaseClasses;

import Entities.Person;

public class AccountLogIn {

    /**
     * Create a new account for a Person.
     *
     * @param username The given username
     * @param password The given password
     * @param users HashMap containing all usernames in use thus far, with associated Entities.Person
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
