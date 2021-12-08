package UseCaseClasses;

import Entities.Person;
import Entities.Schedule;
import Entities.events.*;

public class ScheduleComparer {

    /** Compares two Schedules and returns a Schedule with the combined events of both users
     *
     * @param user1 A Person whose schedule is being compared to user2's schedule
     * @param user2 A Person whose schedule is being compared to user1's schedule
     * @return A schedule with the events of both users
     */
    public Schedule compare(Person user1, Person user2) {
        Schedule result = new Schedule();
        Schedule schedule1 = user1.getUserSchedule();
        Schedule schedule2 = user2.getUserSchedule();
        for (Event event : schedule1.getEvents()) {
            result.getEvents().add(event);
        }
        for (Event event : schedule2.getEvents()) {
            result.getEvents().add(event);
        }
        return result;
    }

}