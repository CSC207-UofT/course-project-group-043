import java.util.HashMap;

public class Main {
    public static void main(String args[]){
        scheduleManager manager = new scheduleManager();

        Person Dennis = new Person("Dennis", "1234");

        manager.addEvent("MAT334", "Tuesday", 17, 21, Dennis);
        manager.addEvent("STA257", "Wednesday", 17, 21, Dennis);

        HashMap<Integer, String> Tuesday = Dennis.getUserSchedule().schedule.get("Tuesday");
        HashMap<Integer, String> Thursday = Dennis.getUserSchedule().schedule.get("Tuesday");
        System.out.println(Tuesday);
        System.out.println(Thursday);

        manager.editEvent("MAT334", "CSC207", "Tuesday",10, 12, Dennis);

        System.out.println(Tuesday);
    }
}
