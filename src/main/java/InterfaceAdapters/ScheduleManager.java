package InterfaceAdapters;

import Entities.Schedule;
import UseCaseClasses.FriendAdder;
import UseCaseClasses.ScheduleComparer;
import UseCaseClasses.ScheduleEditor;
import UseCaseClasses.UserList;


/**
 * Represents and manages entire system of users and schedules
 */

public class ScheduleManager {

    private ScheduleEditor editor;
    private ScheduleComparer comparer;
    private UserList data;
    private FriendAdder adder;

    public ScheduleManager() {
        editor = new ScheduleEditor();
        comparer = new ScheduleComparer();
        adder = new FriendAdder();
    }

    /**
     * Compares two people's schedules and returns a schedule showing what times both
     * are busy.
     * @param user1 the main user's Person
     * @param user2 the friend's Person
     */
    public Schedule compare(String user1, String user2) {
        return comparer.compare(data.getUser(user1), data.getUser(user2));
    }

    public void addEvent(String eventType, String eventName, String eventDay, int eventStartTime,
                         int eventEndTime, String user){
        editor.addEvent(eventType, eventName, eventDay, eventStartTime, eventEndTime, data.getUser(user));
    }

    // should we change this to give it an event
    public void removeEvent(String eventName, String eventDay, String user) {
        editor.removeEvent(eventName, eventDay, data.getUser(user));
    }

    /**
     * Sends a friend request from one user to another user.
     *
     * @param user1 Username of the person sending the friend request
     * @param username2 Username of the person to receive the friend request
     * @return Whether the friend request was successfully sent
     */
    public boolean sendFR(String user1, String username2){
        return adder.sendFriendRequest(data.getUser(user1), data.getUser(username2), data);
    }

    /**
     * Accept a friend request from one user to another
     *
     * @param user1 Username of one of the two potential friends
     * @param user2 Username of one of the two potential friends
     * @return Whether the friend request was successfully accepted
     */
    public boolean acceptFR(String user1, String user2){
        return adder.acceptFriendRequest(data.getUser(user1), data.getUser(user2), data);
    }
}
