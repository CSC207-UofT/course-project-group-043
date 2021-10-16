import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ScheduleComparerTest {

    @Test(timeout = 50)
    public void TestCompare() {
        ScheduleEditor edit = new ScheduleEditor();
        Person DennisTat = new Person("twoDennis", "1234");
        Person DennisTwo = new Person("DennisTwo", "1234");

        Event Csc = new Event("CSC207", "Tuesday", 16, 17);
        Event Mat = new Event("MAT334", "Tuesday", 17, 21);
        Event Sta = new Event("STA257", "Tuesday", 16, 23);
        Event Jog = new Event("Jog", "Tuesday", 7, 8);

        edit.addEvent(Csc, DennisTat);
        edit.addEvent(Mat, DennisTat);
        edit.addEvent(Sta, DennisTwo);
        edit.addEvent(Jog, DennisTwo);

        ScheduleComparer compare = new ScheduleComparer();
        Schedule result = compare.Compare(DennisTat, DennisTwo);

        HashMap<Integer, String> testDay = new HashMap<>();
        for (int i = 0; i <= 23; ++i) {
            testDay.put(i, null);
        }
        for (int i = 16; i <= 22; ++i) {
            testDay.put(i, "busy");
        }
        testDay.put(7, "busy");

        assertEquals(testDay, result.schedule.get("Tuesday"));

    }
}