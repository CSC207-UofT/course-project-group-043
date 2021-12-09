import Entities.Person;
import Entities.Schedule;
import InterfaceAdapters.ScheduleManager;
import UseCaseClasses.ScheduleEditor;
import UseCaseClasses.UserList;
import Entities.events.*;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ScheduleManagerTest {



    @Test()
    public void testCompareAndAddEvent() throws IOException, ExecutionException, InterruptedException {
        /*test if ScheduleManager calls on ScheduleComparer's compare method and ScheduleEditors's
        addEvent method correctly
         */
        ScheduleManager manager = new ScheduleManager();

        //this is the expected list of events to test with, this user's schedule has 4 events
        Person pripri = new Person();
        ScheduleEditor editor = new ScheduleEditor();
        editor.addEvent("course","CSC207", "Tuesday", 16, 17, pripri);
        editor.addEvent("course", "MAT334", "Tuesday", 17, 21, pripri);
        editor.addEvent("course", "STA257", "Tuesday", 16, 23, pripri);
        editor.addEvent("fitness", "Jog", "Tuesday", 7, 8, pripri);
        assertEquals(4, pripri.getUserSchedule().getEvents().size());

        // using ScheduleManager's addEvent on existing users in database "PriPri" and "PriPri2"
        manager.addEvent("course","CSC207", "Tuesday", 16, 17, "PriPri");
        manager.addEvent("course", "MAT334", "Tuesday", 17, 21, "PriPri");
        manager.addEvent("course", "STA257", "Tuesday", 16, 23, "PriPri2");
        manager.addEvent("fitness", "Jog", "Tuesday", 7, 8, "PriPri2");

        //using ScheduleManager's compare method (method being tested)
        Schedule result = manager.compare("PriPri", "PriPri2");

        //checking the compared schedule contains the events from both users using their event names
        assertEquals(result.getEvents().get(0).getEventName(), pripri.getUserSchedule().getEvents().get(0).getEventName());
        assertEquals(result.getEvents().get(1).getEventName(), pripri.getUserSchedule().getEvents().get(1).getEventName());
        assertEquals(result.getEvents().get(2).getEventName(), pripri.getUserSchedule().getEvents().get(3).getEventName());
        assertEquals(result.getEvents().get(3).getEventName(), pripri.getUserSchedule().getEvents().get(3).getEventName());
    }

}
