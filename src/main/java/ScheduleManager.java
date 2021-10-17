import java.util.HashMap;

/**
 * Represents and manages entire system of users and schedules
 */

public class ScheduleManager {

    // attempts to fix these style warnings have resulted in problems in other classes
    private HashMap<Person, Schedule> schedules;

    /**
     * Creates a ScheduleManager with a hashmap of Person as keys and Schedule as values that is empty
     */

    public ScheduleManager() {
        schedules = new HashMap<>();
    }

    /**
     * Adds an instance of Person and Schedule to the schedules hashmap
     * @param user1 the main user's Person
     * @param user2 the friend's Person
     */

    public Schedule compare(Person user1, Person user2) {
        ScheduleComparer compare = new ScheduleComparer();
        return compare.Compare(user1, user2);
    }

    public void addEvent(Event event, Person user) {
        ScheduleEditor editor = new ScheduleEditor();
        editor.addEvent(event, user);
    }

    public void removeEvent(String eventName, String eventDay, Person user) {
        ScheduleEditor editor = new ScheduleEditor();
        editor.removeEvent(eventName, eventDay, user);
    }
}
