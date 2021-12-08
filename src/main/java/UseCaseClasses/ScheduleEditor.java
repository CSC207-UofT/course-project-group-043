package UseCaseClasses;

import Entities.Person;
import Entities.Schedule;
import Entities.events.*;

import java.util.ArrayList;

public class ScheduleEditor {

    private final EventFactory factory;

    public ScheduleEditor() {
        factory = new EventFactory();
    }

    /**
     * Adds an event to the user's schedule
     *
     * @param eventName the name of the event which is being removed from a schedule
     * @param eventDay the day of the event being removed
     * @param eventStartTime the time which this event begins at
     * @param eventEndTime the time which this event ends at
     * @param user the Person whose schedule the event is being added to
     */
    public void addEvent(String eventType, String eventName, String eventDay, int eventStartTime, int eventEndTime, Person user) {
        Schedule schedule = user.getUserSchedule();
        Event event = factory.getEvent(eventType);
        if (event != null) {
            event.setEventName(eventName);
            event.setEventDay(eventDay);
            event.setEventStartTime(eventStartTime);
            event.setEventEndTime(eventEndTime);
            ArrayList<Event> eventList = schedule.getEvents();
            eventList.add(event);
            user.getUserSchedule().setEvents(eventList);
        }
    }

    /**
     * Removes all occurrences of eventName on eventDay from the schedule of user
     *
     * @param eventName the name of the event which is being removed from a schedule
     * @param eventDay the day of the event being removed
     * @param startTime the time which this event begins at
     * @param user the Person whose schedule is being changed
     */
    public void removeEvent(String eventName, String eventDay, int startTime, Person user) {
        Schedule schedule = user.getUserSchedule();
        for (int i = 0; i < schedule.getEvents().size(); i++) {
            if (schedule.getEvents().get(i).getEventName().equals(eventName) && schedule.getEvents().get(i).getEventDay().equals(eventDay) && schedule.getEvents().get(i).getEventStartTime() == startTime) {
                schedule.getEvents().remove(schedule.getEvents().remove(i));
            }
        }
    }
}

