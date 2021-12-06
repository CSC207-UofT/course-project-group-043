package UseCaseClasses;

import Entities.Person;
import Entities.Schedule;
import Entities.events.*;

import java.util.ArrayList;

public class ScheduleComparer {

    /** Compares two Schedules and returns a Entities.Schedule with entries null if both user1 and user2 do not have
     * Entities.events during that time, and busy if user1 or user2 have Entities.events during that time.
     *
     *
     * @param user1 A Entities.Person whose schedule is being compared to user2's schedule
     * @param user2 A Entities.Person whose schedule is being compared to user1's schedule
     */

    public Schedule compare(Person user1, Person user2) {
        String[] titles = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        Schedule result = new Schedule();
        Schedule schedule1 = user1.getUserSchedule();
        Schedule schedule2 = user2.getUserSchedule();
        for (String day : titles){
            ArrayList<Event> day1 = schedule1.getSchedule().get(day);
            ArrayList<Event> day2 = schedule2.getSchedule().get(day);
            ArrayList<Event> merged = new ArrayList<Event>();
            merged.addAll(day1);
            merged.addAll(day2);
            result.setDayEvents(day, merged);
        }
        return result;
    }

}
