package UseCaseClasses;

import Entities.Person;
import Entities.Schedule;

import java.util.HashMap;

public class ScheduleComparer {

    /** Compares two Schedules and returns a Entities.Schedule with entries null if both user1 and user2 do not have
     * events during that time, and busy if user1 or user2 have events during that time.
     *
     *
     * @param user1 A Entities.Person whose schedule is being compared to user2's schedule
     * @param user2 A Entities.Person whose schedule is being compared to user1's schedule
     */
    public Schedule compare(Person user1, Person user2) {
        Schedule result = new Schedule();
        Schedule schedule1 = user1.getUserSchedule();
        Schedule schedule2 = user2.getUserSchedule();
        HashMap<String, String> monday1 = schedule1.getSchedule().get("Monday");
        HashMap<String, String> monday2 = schedule2.getSchedule().get("Monday");
        for (int i = 0; i <= 23; ++i) {
            HashMap<String, String> mondayCompare = result.getSchedule().get("Monday");
            if (monday1.get(String.valueOf(i)) != null || monday2.get(String.valueOf(i)) != null){
                mondayCompare.put(String.valueOf(i), "busy");
            }
        }
        HashMap<String, String> tuesday1 = schedule1.getSchedule().get("Tuesday");
        HashMap<String, String> tuesday2 = schedule2.getSchedule().get("Tuesday");
        for (int i = 0; i <= 23; ++i) {
            HashMap<String, String> tuesdayCompare = result.getSchedule().get("Tuesday");
            if (tuesday1.get(String.valueOf(i)) != null || tuesday2.get(String.valueOf(i)) != null){
                tuesdayCompare.put(String.valueOf(i), "busy");
            }
        }
        HashMap<String, String> wednesday1 = schedule1.getSchedule().get("Wednesday");
        HashMap<String, String> wednesday2 = schedule2.getSchedule().get("Wednesday");
        for (int i = 0; i <= 23; ++i) {
            HashMap<String, String> wednesdayCompare = result.getSchedule().get("Wednesday");
            if (wednesday1.get(String.valueOf(i)) != null || wednesday2.get(String.valueOf(i)) != null){
                wednesdayCompare.put(String.valueOf(i), "busy");
            }
        }
        HashMap<String, String> thursday1 = schedule1.getSchedule().get("Thursday");
        HashMap<String, String> thursday2 = schedule2.getSchedule().get("Thursday");
        for (int i = 0; i <= 23; ++i) {
            HashMap<String, String> thursdayCompare = result.getSchedule().get("Thursday");
            if (thursday1.get(String.valueOf(i)) != null || thursday2.get(String.valueOf(i)) != null){
                thursdayCompare.put(String.valueOf(i), "busy");
            }
        }
        HashMap<String, String> friday1 = schedule1.getSchedule().get("Friday");
        HashMap<String, String> friday2 = schedule2.getSchedule().get("Friday");
        for (int i = 0; i <= 23; ++i) {
            HashMap<String, String> fridayCompare = result.getSchedule().get("Friday");
            if (friday1.get(String.valueOf(i)) != null || friday2.get(String.valueOf(i)) != null){
                fridayCompare.put(String.valueOf(i), "busy");
            }
        }
        HashMap<String, String> saturday1 = schedule1.getSchedule().get("Saturday");
        HashMap<String, String> saturday2 = schedule2.getSchedule().get("Saturday");
        for (int i = 0; i <= 23; ++i) {
            HashMap<String, String> saturdayCompare = result.getSchedule().get("Saturday");
            if (saturday1.get(String.valueOf(i)) != null || saturday2.get(String.valueOf(i)) != null){
                saturdayCompare.put(String.valueOf(i), "busy");
            }
        }
        HashMap<String, String> sunday1 = schedule1.getSchedule().get("Sunday");
        HashMap<String, String> sunday2 = schedule2.getSchedule().get("Sunday");
        for (int i = 0; i <= 23; ++i) {
            HashMap<String, String> sundayCompare = result.getSchedule().get("Sunday");
            if (sunday1.get(String.valueOf(i)) != null || sunday2.get(String.valueOf(i)) != null){
                sundayCompare.put(String.valueOf(i), "busy");
            }
        }
        return result;
    }

}
