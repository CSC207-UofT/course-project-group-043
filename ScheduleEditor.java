import java.util.HashMap;
import java.util.Objects;

public class ScheduleEditor {

    /**
     * Add Event to the schedule of user
     *
     * @param event the event being added to user's schedule
     * @param user the Person whose schedule is being changed
     */
    public void addEvent(Event event, Person user){
        Schedule schedule = user.getUserSchedule();
        HashMap<Integer, String> Day = schedule.schedule.get(event.eventDay);
        for (int i = event.eventStartTime; i < event.eventEndTime; ++i) {
            Day.put(i, event.eventName);
        }
        schedule.schedule.put(event.eventDay, Day);
    }

    /**
     * Removes all occurrences of event on eventDay from the schedule of user
     *
     * @param event the event which is being removed from a schedule
     * @param user the Person whose schedule is being changed
     */
    public void RemoveEvent(Event event, Person user) {
        Schedule schedule = user.getUserSchedule();
        HashMap<Integer, String> Day = schedule.schedule.get(event.eventDay);
        for (int i = 0; i <= 23; ++i) {
            if (Objects.equals(Day.get(i), event.eventName)){
                Day.put(i, null);
            }
        }
    }
}
