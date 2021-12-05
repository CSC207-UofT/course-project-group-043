package InterfaceAdapters;

//import Entities.Person;
//import Entities.Schedule;
import UseCaseClasses.FriendAdder;
import UseCaseClasses.ScheduleComparer;
import UseCaseClasses.ScheduleEditor;
import UseCaseClasses.UserList;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


/**
 * Represents and manages entire system of users and schedules
 */

public class ScheduleManager {

//    // attempts to fix these style warnings have resulted in problems in other classes
//    private HashMap<Person, Schedule> schedules;
    private ScheduleEditor editor;
    private ScheduleComparer comparer;
    private UserList data;
    private FriendAdder adder;

    public ScheduleManager() {
        editor = new ScheduleEditor();
        comparer = new ScheduleComparer();
//        schedules = new HashMap<>();
    }

//    /**
//     * Compares two people's schedules and returns a schedule showing what times both
//     * are busy.
//     * @param user1 the main user's Person
//     * @param user2 the friend's Person
//     */
    // currently uncertain how we're going to be using this class
//    public Schedule compare(String user1, String user2) {// creating hard dependencies -- i don't think we're supposed to do this?
//        return comparer.compare(user1, user2);
//    }

    public void addEvent(String eventType, String eventName, String eventDay, int eventStartTime,
                         int eventEndTime, String user){
        editor.addEvent(eventType, eventName, eventDay, eventStartTime, eventEndTime, data.getUser(user));
    }

    // should we change this to give it an event
    public void removeEvent(String eventName, String eventDay, String user) {
        editor.removeEvent(eventName, eventDay, data.getUser(user));
    }

    // todo: edit this to return a boolean if a friend is successfully added??
    public void sendFR(String user1, String username2){
        /* user 1 sends friend request to user2 using user 2's username
         */
        if(data.containsUser(username2) && data.containsUser(user1)) {
            adder.sendFriendRequest(data.getUser(user1), data.getUser(username2));
        }
//        Set<String> userset = data.getUsers().keySet();
//        ArrayList<Person> users = new ArrayList<>(userset);
//        Person user2 = new Person();
//        for (String user : userset) {
//            if (user1.equals(username2)) {
//                user2 = user;
//            }
//        }
//        FriendAdder newRequest = new FriendAdder();
//        adder.sendFriendRequest(user1, user2);
    }

    public void acceptFR(String user1, String user2){
        /* user 2 accepts user 1's friend request
         */
//        FriendAdder acceptNew = new FriendAdder();
        adder.acceptFriendRequest(data.getUser(user1), data.getUser(user2));
    }
}
