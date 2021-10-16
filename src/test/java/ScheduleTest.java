import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScheduleTest {

    @Test(timeout = 50)
    public void testToString() {
        ScheduleEditor testEditor = new ScheduleEditor();
        Person testPerson1 = new Person("test", "1234");

        /* create two events to add to testPerson's schedule
         */
        Event csc = new Event("CSC207", "Monday", 3, 4);
        Event mat = new Event("MAT223", "Tuesday", 11, 12);
        Event phy = new Event("PHY151", "Wednesday", 16, 17);
        Event hps = new Event("HPS100", "Thursday", 17, 18);

        testEditor.addEvent(csc, testPerson1);
        testEditor.addEvent(mat, testPerson1);
        testEditor.addEvent(phy, testPerson1);
        testEditor.addEvent(hps, testPerson1);

        String expected = "Monday:\n\tCSC207 at 3h\nTuesday:\n\tMAT223 at 11h\nWednesday:\n" +
                "\tPHY151 at 16h\nThursday:\n\tHPS100 at 17h\nFriday:\nSaturday:\nSunday:\n";
        String actual = testPerson1.getUserSchedule().toString();

        assertEquals(expected, actual);
    }

}
