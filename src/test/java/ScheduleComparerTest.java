import Entities.Person;
import Entities.Schedule;
import UseCaseClasses.ScheduleComparer;
import UseCaseClasses.ScheduleEditor;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ScheduleComparerTest {

    @Test(timeout = 50)
    public void TestCompare() {
        ScheduleEditor edit = new ScheduleEditor();
        Person DennisTat = new Person("twoDennis", "1234");
        Person DennisTwo = new Person("DennisTwo", "1234");

        edit.addEvent("course","CSC207", "Tuesday", 16, 17, DennisTat);
        edit.addEvent("course", "MAT334", "Tuesday", 17, 21, DennisTat);
        edit.addEvent("course", "STA257", "Tuesday", 16, 23, DennisTwo);
        edit.addEvent("fitness", "Jog", "Tuesday", 7, 8, DennisTwo);

        ScheduleComparer compare = new ScheduleComparer();
        Schedule result = compare.compare(DennisTat, DennisTwo);

        HashMap<Integer, String> testDay = new HashMap<>();
        for (int i = 0; i <= 23; ++i) {
            testDay.put(i, null);
        }
        for (int i = 16; i <= 22; ++i) {
            testDay.put(i, "busy");
        }
        testDay.put(7, "busy");

        assertEquals(testDay, result.getSchedule().get("Tuesday"));

    }
}