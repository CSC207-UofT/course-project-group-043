package UseCaseClasses;

import Entities.Event;
import Entities.Person;
import Entities.Schedule;

import java.util.HashMap;
import java.util.Objects;

public class ScheduleEditor {

    /**
     * Add Entities.Event to the schedule of user
     *
     * @param event the event being added to user's schedule
     * @param user the Entities.Person whose schedule is being changed
     */
    public void addEvent(Event event, Person user){
        Schedule schedule = user.getUserSchedule();

        String eventName = event.getEventName();
        String eventDay = event.getEventDay();
        int eventStartTime = event.getEventStartTime();
        int eventEndTime = event.getEventEndTime();

        HashMap<String, String> Day = schedule.getSchedule().get(eventDay);
        for (int i = eventStartTime; i < eventEndTime; ++i) {
            Day.put(String.valueOf(i), eventName);
        }
        schedule.getSchedule().put(eventDay, Day);
    }

    public void editEventStartTime(Event event, int newStart) {
        event.setEventStartTime(newStart);
    }

    public void editEventEndTime(Event event, int newEnd) {
        event.setEventEndTime(newEnd);
    }

    public void editEventName(Event event, String newName) {
        event.setEventName(newName);
    }

    public void editEventDay(Event event, String newDay) {
        event.setEventDay(newDay);
    }

    /**
     * Removes all occurrences of eventName on eventDay from the schedule of user
     *
     * @param eventName the name of the event which is being removed from a schedule
     * @param eventDay the day of the event being removed
     * @param user the Entities.Person whose schedule is being changed
     */
    public void removeEvent(String eventName, String eventDay, Person user) {
        Schedule schedule = user.getUserSchedule();

        HashMap<String, String> Day = schedule.getSchedule().get(eventDay);
        for (int i = 0; i <= 23; ++i) {
            if (Objects.equals(Day.get(String.valueOf(i)), eventName)){
                Day.put(String.valueOf(i), null);
            }
        }
    }

}