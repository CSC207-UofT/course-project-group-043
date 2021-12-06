package UseCaseClasses;

import Entities.Person;

import java.util.HashMap;

public class UserList {

    public static HashMap<String, Person> users;

    // potentially could have something that adds more than one user at a time?
    // todo: have the constructor add everything from the database? plus a constructor for empty list for testing?

    public UserList() {
        users = new HashMap<>();
    }

    public static HashMap<String, Person> getUsers() {
        return users;
    }

    public static void addUser(String name, Person user) {
        users.put(name, user);
    }

    public static Person getUser(String name) {
        return users.get(name);
    }

    public static boolean containsUser(String name) {
        return users.containsKey(name);
    }

}
