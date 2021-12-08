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

    public void addEvent(String eventType, String eventName, String eventDay, int eventStartTime, int eventEndTime, Person user) {
        Schedule schedule = user.getUserSchedule();
        Event event = factory.getEvent(eventType);
        if (event != null) {
            event.setEventName(eventName);
            event.setEventDay(eventDay);
            event.setEventStartTime(eventStartTime);
            event.setEventEndTime(eventEndTime);

            ArrayList<Event> dayEvents = schedule.getSchedule().get(eventDay);
            dayEvents.add(event);
            schedule.setDayEvents(eventDay, dayEvents);
        }
    }

    /**
     * Removes all occurrences of eventName on eventDay from the schedule of user
     *
     * @param eventName the name of the event which is being removed from a schedule
     * @param eventDay the day of the event being removed
     * @param user the Person whose schedule is being changed
     */

    public void removeEvent(String eventName, String eventDay, int startTime, Person user) {
        Schedule schedule = user.getUserSchedule();

        ArrayList<Event> dayEvents = schedule.getSchedule().get(eventDay);
        for (int i = 0; i < dayEvents.size(); i++) {
            if (dayEvents.get(i).eventName.equals(eventName) && dayEvents.get(i).eventStartTime == startTime) {
                dayEvents.remove(i);
            }
        }
        schedule.setDayEvents(eventDay,dayEvents);


    }


}
