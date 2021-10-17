import java.util.HashMap;

/**
 * Represents and manages entire system of users and schedules
 */

public class ScheduleManager {

    private HashMap<Person, Schedule> schedules;

    /**
     * Creates a ScheduleManager with a hashmap of Person as keys and Schedule as values that is empty
     */

    public ScheduleManager() {
        schedules = new HashMap<>();
    }

    /**
     * Adds an instance of Person and Schedule to the schedules hashmap
     * @param user the user
     * @param schedule the newly created schedule of this user
     */

    public void add(Person user, Schedule schedule){
        schedules.put(user, schedule);
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

    public void removeEvent(Event event, Person user) {
        ScheduleEditor editor = new ScheduleEditor();
        editor.removeEvent(event, user);
    }
}
