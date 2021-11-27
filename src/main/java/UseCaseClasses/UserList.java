package UseCaseClasses;

import Entities.Person;

import java.util.HashMap;

public class UserList {

    private HashMap<String, Person> users;

    // potentially could have something that adds more than one user at a time?
    // todo: have the constructor add everything from the database?

    public HashMap<String, Person> getUsers() {
        return users;
    }

    public void addUser(String name, Person user) {
        users.put(name, user);
    }

    public Person getUser(String name) {
        return users.get(name);
    }

}
