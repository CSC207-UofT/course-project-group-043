package UseCaseClasses;

import Entities.Person;

import java.util.HashMap;

/**
 * Stores all the users and their associated account information.
 */
public class UserList {

    public HashMap<String, Person> users;

    public UserList() {
        users = new HashMap<>();
    }

    public HashMap<String, Person> getUsers() {
        return users;
    }

    public void addUser(String name, Person user) {
        users.put(name, user);
    }

    public Person getUser(String name) {
        return users.get(name);
    }

    public boolean containsUser(String name) {
        return users.containsKey(name);
    }

}
