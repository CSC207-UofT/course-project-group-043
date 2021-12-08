import org.junit.Test;
import Entities.events.*;
import static org.junit.Assert.assertEquals;


public class EventTest {

    @Test()
    public void testEventFactory() {
        EventFactory factory = new EventFactory();

        // use factory to make a social event with varying String parameters of type
        Event social = factory.getEvent("sOciAl");

        // use factory to make a academic event with varying String parameters of type
        Event academic = factory.getEvent("academic");

        // use factory to make a course event with varying String parameters of type
        Event course = factory.getEvent("cOurse");

        // use factory to make a fitness event with varying String parameters of type
        Event fitness = factory.getEvent("FitNess");

        SocialEvent result1 = new SocialEvent();
        AcademicEvent result2 = new AcademicEvent();
        FitnessEvent result3 = new FitnessEvent();
        CourseEvent result4 = new CourseEvent();

        /* check that the correct event type was created regardless of contents
         */
        assertEquals(result1.getEventType(), social.getEventType());
        assertEquals(result2.getEventType(), academic.getEventType());
        assertEquals(result3.getEventType(), fitness.getEventType());
        assertEquals(result4.getEventType(), course.getEventType());



    }
}

