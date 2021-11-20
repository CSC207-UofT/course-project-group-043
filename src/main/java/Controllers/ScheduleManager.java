package Controllers;

import Entities.Event;
import Entities.Person;
import Entities.Schedule;
import UseCaseClasses.FriendAdder;
import UseCaseClasses.InformationSaver;
import UseCaseClasses.ScheduleComparer;
import UseCaseClasses.ScheduleEditor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * Represents and manages entire system of users and schedules
 */

public class ScheduleManager {

    // attempts to fix these style warnings have resulted in problems in other classes
    private HashMap<Person, Schedule> schedules;

    /**
     * Creates a Controllers.ScheduleManager with a hashmap of Entities.Person as keys and Entities.Schedule as values that is empty
     */

    public ScheduleManager() {
        schedules = new HashMap<>();
    }

    /**
     * Adds an instance of Entities.Person and Entities.Schedule to the schedules hashmap
     * @param user1 the main user's Entities.Person
     * @param user2 the friend's Entities.Person
     */

    public Schedule compare(Person user1, Person user2) {
        ScheduleComparer compare = new ScheduleComparer();  // creating hard dependencies -- i don't think we're supposed to do this?
        return compare.compare(user1, user2);
    }

    public void addEvent(Event event, Person user) throws ExecutionException, InterruptedException, IOException {
        InformationSaver saver = new InformationSaver();
        ScheduleEditor editor = new ScheduleEditor();
        editor.addEvent(event, user);
        saver.saveUser(user);
    }

    // should we change this to give it an event
    public void removeEvent(String eventName, String eventDay, Person user) throws ExecutionException, InterruptedException, IOException {
        InformationSaver saver = new InformationSaver();
        ScheduleEditor editor = new ScheduleEditor();
        editor.removeEvent(eventName, eventDay, user);
        saver.saveUser(user);
    }

    public void sendFR(Person user1, String username2){
        /* user 1 sends friend request to user2 using user 2's username
         */

        Set<Person> userset = this.schedules.keySet();
        ArrayList<Person> users = new ArrayList<>(userset);
        Person user2 = new Person();
        for (Person user : users) {
            if (user.getUserName().equals(username2)) {
                user2 = user;
            }
        }
        FriendAdder newRequest = new FriendAdder();
        newRequest.sendFriendRequest(user1, user2);
    }

    public void acceptFR(Person user1, Person user2){
        /* user 2 accepts user 1's friend request
         */
        FriendAdder acceptNew = new FriendAdder();
        acceptNew.sendFriendRequest(user1, user2);
    }
}
