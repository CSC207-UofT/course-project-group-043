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

        /* add two events to the schedule
         */
        edit.addEvent("course","CSC207", "Tuesday", 16, 17 , DennisTat);
        edit.addEvent("course", "MAT334", "Tuesday", 17, 21, DennisTat);

        /* create a new schedule to compare DennisTat's schedule to
         */

        assertEquals(DennisSchedule.getEvents().size(), 2);

        edit.removeEvent("CSC207", "Tuesday", 16, DennisTat);


        assertEquals(DennisSchedule.getEvents().size(), 1);

        edit.removeEvent("MAT334", "Tuesday", 17, DennisTat);

        assertEquals(DennisSchedule.getEvents().size(), 0);
    }
}