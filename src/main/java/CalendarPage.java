import java.util.Objects;
import java.util.Scanner;

public class CalendarPage {
    public static void main (String[] args) {
        ScheduleManager manager = new ScheduleManager();
        Person user = new Person("user", "1234");
        Person Dennis = new Person("Dennis", "1234");
        Event work = new Event("Work", "Monday", 9, 17);
        manager.addEvent(work, Dennis);

        Scanner in = new Scanner (System.in);
        String input;
        System.out.println("What would you like to do?");
        System.out.println("'view' to see your schedule");
        System.out.println("'add' to add event");
        System.out.println("'remove' to remove event");
        System.out.println("'compare' to compare schedules with another user.");
        System.out.println("'quit' to quit");
        do {
            input = in.nextLine();
            if (Objects.equals(input, "add")) {
                System.out.println("What is the name of your event?");
                String eventName = in.nextLine();
                System.out.println("What day does your event happen on? (Monday, Tuesday, Wednesday, etc.)");
                String eventDay = in.nextLine();
                System.out.println("What time does your event start at?");
                int eventStartTime = in.nextInt();
                System.out.println("What time does your event end?");
                int eventEndTime = in.nextInt();
                /* use schedule manager to add this to the calendar*/
                Event event = new Event(eventName, eventDay, eventStartTime, eventEndTime);
                manager.addEvent(event, user);
                System.out.println("Event has been added.");

            } else if (Objects.equals(input, "remove")) {
                System.out.println("What is the name of the event you would like to remove?");
                String eventName = in.nextLine();
                System.out.println("What day would you like to remove the event from?");
                String eventDay = in.nextLine();
                /* use schedule manager to remove this from the calendar*/
                manager.removeEvent(eventName, eventDay, user);
                System.out.println("Event has been removed.");

            } else if (Objects.equals(input, "view")) {
                System.out.println(user.getUserSchedule());

            } else if (Objects.equals(input, "compare")) {
                System.out.println("Whose schedule are you comparing with? (Currently the only other Person " +
                        "we can compare with is 'Dennis'), who is busy on Monday morning.");
                /* We don't have a second user to compare with, so we manually create one*/
                /* use schedule manager to compare to another schedule*/
                /* print the conflict schedule*/
                String compareUser = in.nextLine();
                System.out.println(manager.compare(user, Dennis));
            }
        } while (!Objects.equals(input, "quit"));

    }
}
