package UseCaseClasses;

import Entities.Person;

import java.util.HashMap;

public class UserList {

    public HashMap<String, Person> users;

    // potentially could have something that adds more than one user at a time?
    // todo: have the constructor add everything from the database? plus a constructor for empty list for testing?

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
