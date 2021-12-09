import Entities.Person;
import Entities.Schedule;
import InterfaceAdapters.ScheduleManager;
import UseCaseClasses.ScheduleEditor;
import UseCaseClasses.UserList;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

public class ScheduleManagerTest {

    @Test()
    public void testCompare() throws IOException, ExecutionException, InterruptedException {
        //test if ScheduleManager calls on ScheduleComparer correctly
        ScheduleManager manager = new ScheduleManager();
        Person Pri = new Person("Pri", "246810");
        Person PriTwo = new Person("PriTwo", "246810");

        manager.addEvent("course","CSC207", "Tuesday", 16, 17, "Pri");
        manager.addEvent("course", "MAT334", "Tuesday", 17, 21, "Pri");
        manager.addEvent("course", "STA257", "Tuesday", 16, 23, "PriTwo");
        manager.addEvent("fitness", "Jog", "Tuesday", 7, 8, "PriTwo");

        Schedule result = manager.compare("Pri", "PriTwo");

        assertEquals(result.getEvents().size(), 4);




    }

}
