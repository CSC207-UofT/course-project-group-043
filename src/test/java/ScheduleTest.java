import Entities.Person;
import UseCaseClasses.ScheduleEditor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScheduleTest {

    @Test(timeout = 50)
    public void testToString() {
        ScheduleEditor testEditor = new ScheduleEditor();
        Person testPerson1 = new Person("test", "1234");

        testEditor.addEvent("course", "CSC207", "Monday", 3, 4, testPerson1);
        testEditor.addEvent("course", "MAT223", "Tuesday", 11, 12, testPerson1);
        testEditor.addEvent("course","PHY151", "Wednesday", 16, 17 , testPerson1);
        testEditor.addEvent("course", "HPS100", "Thursday", 17, 18, testPerson1);

        String expected = "Monday:\n\tCSC207 at 3h\nTuesday:\n\tMAT223 at 11h\nWednesday:\n" +
                "\tPHY151 at 16h\nThursday:\n\tHPS100 at 17h\nFriday:\nSaturday:\nSunday:\n";
        String actual = testPerson1.getUserSchedule().toString();

        assertEquals(expected, actual);
    }

}
