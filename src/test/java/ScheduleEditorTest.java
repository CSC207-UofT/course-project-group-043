import Entities.Person;
import Entities.Schedule;
import UseCaseClasses.ScheduleEditor;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ScheduleEditorTest {

    @Test(timeout = 50)
    public void TestAddEvent() {
        ScheduleEditor edit = new ScheduleEditor();
        Person DennisTat = new Person("twoDennis", "1234");

        /* retrieve DennisTat's schedule
         */
        Schedule DennisSchedule = DennisTat.getUserSchedule();

        /* add the two Entities.events to the schedule
         */
        edit.addEvent("course","CSC207", "Tuesday", 16, 17 , DennisTat);
        edit.addEvent("course", "MAT334", "Tuesday", 17, 21, DennisTat);

        /* create a new schedule to compare DennisTat's schedule to
         */
        HashMap<Integer, String> testDay = new HashMap<>();

        for (int i = 0; i <= 23; ++i) {
            testDay.put(i, null);
        }
        testDay.put(16, "CSC207");
        testDay.put(17, "MAT334");
        testDay.put(18, "MAT334");
        testDay.put(19, "MAT334");
        testDay.put(20, "MAT334");

        assertEquals(testDay, DennisSchedule.getSchedule().get("Tuesday"));

        edit.removeEvent("CSC207", "Tuesday", DennisTat);

        testDay.put(16, null);

        assertEquals(testDay, DennisSchedule.getSchedule().get("Tuesday"));

        edit.removeEvent("MAT334", "Tuesday", DennisTat);

        testDay.put(17, null);
        testDay.put(18, null);
        testDay.put(19, null);
        testDay.put(20, null);

        assertEquals(testDay, DennisSchedule.getSchedule().get("Tuesday"));
    }
}
