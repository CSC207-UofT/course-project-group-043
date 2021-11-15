import java.util.HashMap;

public class ScheduleComparer {

    /** Compares two Schedules and returns a Schedule with entries null if both user1 and user2 do not have
     * events during that time, and busy if user1 or user2 have events during that time.
     *
     *
     * @param user1 A Person whose schedule is being compared to user2's schedule
     * @param user2 A Person whose schedule is being compared to user1's schedule
     */
    public Schedule compare(Person user1, Person user2) {
        Schedule result = new Schedule();
        Schedule schedule1 = user1.getUserSchedule();
        Schedule schedule2 = user2.getUserSchedule();
        HashMap<Integer, String> monday1 = schedule1.schedule.get("Monday");
        HashMap<Integer, String> monday2 = schedule2.schedule.get("Monday");
        for (int i = 0; i <= 23; ++i) {
            HashMap<Integer, String> mondayCompare = result.schedule.get("Monday");
            if (monday1.get(i) != null || monday2.get(i) != null){
                mondayCompare.put(i, "busy");
            }
        }
        HashMap<Integer, String> tuesday1 = schedule1.schedule.get("Tuesday");
        HashMap<Integer, String> tuesday2 = schedule2.schedule.get("Tuesday");
        for (int i = 0; i <= 23; ++i) {
            HashMap<Integer, String> tuesdayCompare = result.schedule.get("Tuesday");
            if (tuesday1.get(i) != null || tuesday2.get(i) != null){
                tuesdayCompare.put(i, "busy");
            }
        }
        HashMap<Integer, String> wednesday1 = schedule1.schedule.get("Wednesday");
        HashMap<Integer, String> wednesday2 = schedule2.schedule.get("Wednesday");
        for (int i = 0; i <= 23; ++i) {
            HashMap<Integer, String> wednesdayCompare = result.schedule.get("Wednesday");
            if (wednesday1.get(i) != null || wednesday2.get(i) != null){
                wednesdayCompare.put(i, "busy");
            }
        }
        HashMap<Integer, String> thursday1 = schedule1.schedule.get("Thursday");
        HashMap<Integer, String> thursday2 = schedule2.schedule.get("Thursday");
        for (int i = 0; i <= 23; ++i) {
            HashMap<Integer, String> thursdayCompare = result.schedule.get("thursday");
            if (thursday1.get(i) != null || thursday2.get(i) != null){
                thursdayCompare.put(i, "busy");
            }
        }
        HashMap<Integer, String> friday1 = schedule1.schedule.get("Friday");
        HashMap<Integer, String> friday2 = schedule2.schedule.get("Friday");
        for (int i = 0; i <= 23; ++i) {
            HashMap<Integer, String> fridayCompare = result.schedule.get("Friday");
            if (friday1.get(i) != null || friday2.get(i) != null){
                fridayCompare.put(i, "busy");
            }
        }
        HashMap<Integer, String> saturday1 = schedule1.schedule.get("Saturday");
        HashMap<Integer, String> saturday2 = schedule2.schedule.get("Saturday");
        for (int i = 0; i <= 23; ++i) {
            HashMap<Integer, String> saturdayCompare = result.schedule.get("Saturday");
            if (saturday1.get(i) != null || saturday2.get(i) != null){
                saturdayCompare.put(i, "busy");
            }
        }
        HashMap<Integer, String> sunday1 = schedule1.schedule.get("Sunday");
        HashMap<Integer, String> sunday2 = schedule2.schedule.get("Sunday");
        for (int i = 0; i <= 23; ++i) {
            HashMap<Integer, String> sundayCompare = result.schedule.get("Sunday");
            if (sunday1.get(i) != null || sunday2.get(i) != null){
                sundayCompare.put(i, "busy");
            }
        }
        return result;
    }

}
