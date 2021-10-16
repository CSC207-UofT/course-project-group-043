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

        String eventName = event.getEventName();
        String eventDay = event.getEventDay();
        int eventStartTime = event.getEventStartTime();
        int eventEndTime = event.getEventEndTime();

        HashMap<Integer, String> Day = schedule.schedule.get(eventDay);
        for (int i = eventStartTime; i < eventEndTime; ++i) {
            Day.put(i, eventName);
        }
        schedule.schedule.put(eventDay, Day);
    }

    /**
     * Removes all occurrences of event on eventDay from the schedule of user
     *
     * @param event the event which is being removed from a schedule
     * @param user the Person whose schedule is being changed
     */
    public void removeEvent(Event event, Person user) {
        Schedule schedule = user.getUserSchedule();

        String eventName = event.getEventName();
        String eventDay = event.getEventDay();

        HashMap<Integer, String> Day = schedule.schedule.get(eventDay);
        for (int i = 0; i <= 23; ++i) {
            if (Objects.equals(Day.get(i), eventName)){
                Day.put(i, null);
            }
        }
    }
}
