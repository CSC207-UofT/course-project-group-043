package InterfaceAdapters;

import Entities.Schedule;
import UseCaseClasses.FriendAdder;
import UseCaseClasses.ScheduleComparer;
import UseCaseClasses.ScheduleEditor;
import UseCaseClasses.UserList;

import java.io.IOException;
import java.util.concurrent.ExecutionException;


/**
 * Represents and manages entire system of users and schedules.
 */
public class ScheduleManager {

    private ScheduleEditor editor;
    private ScheduleComparer comparer;
    private UserList data;
    private FriendAdder adder;
    private final InformationSaver saver;

    /**
     * Creates a new instance of ScheduleManager
     *
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public ScheduleManager() throws IOException, ExecutionException, InterruptedException {
        editor = new ScheduleEditor();
        comparer = new ScheduleComparer();
        saver = new InformationSaver();
        adder = new FriendAdder();
        data = new UserList();
        saver.retrieveUsers(data);

        //populating the UserList with users from the database
        for (String username : data.getUsers().keySet()) {
            saver.retrieveFriends(username, data);
            saver.retrieveOutgoing(username, data);
            saver.retrieveIncoming(username, data);
            saver.retrieveEvents(username, data);
        }
    }

    /**
     * Compares two people's schedules and returns a schedule showing what times both
     * are busy.
     *
     * @param user1 the main user's Person
     * @param user2 the friend's Person
     */
    public Schedule compare(String user1, String user2) {
        return comparer.compare(data.getUser(user1), data.getUser(user2));
    }

    /**
     * Calls on methods in ScheduleEditor to add an Event to a user's schedule, then
     * saves the updated user information in the database.
     *
     * @param eventType The type of event
     * @param eventName The name of the event
     * @param eventDay The day of the week the event happens on
     * @param eventStartTime The time (on 24 hour clock) that the event starts at
     * @param eventEndTime The time (on a 24 hour clock) that the event ends at
     * @param user The username of the user that this event is being added to the schedule of
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void addEvent(String eventType, String eventName, String eventDay, int eventStartTime,
                         int eventEndTime, String user) throws ExecutionException, InterruptedException {
        editor.addEvent(eventType, eventName, eventDay, eventStartTime, eventEndTime, data.getUser(user));
        saver.saveUserEvent(eventType, eventName, eventDay, eventStartTime, eventEndTime, data.getUser(user));
    }

    /**
     * Removes an event from a user's schedule.
     *
     * @param eventName The name of the event
     * @param eventDay The day of the week the event occurs on
     * @param startTime The time at which the event starts
     * @param user The username of the user that is having this event removed
     */
    public void removeEvent(String eventName, String eventDay, int startTime, String user) {
        editor.removeEvent(eventName, eventDay, startTime, data.getUser(user));
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
