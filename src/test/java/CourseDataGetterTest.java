import InterfaceAdapters.CourseDataGetter;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class CourseDataGetterTest {

    @Test()
    public void testGettingCSC() throws URISyntaxException, IOException, InterruptedException {
        // note: this takes a while, relatively speaking (just under 2 seconds, apparently)
        CourseDataGetter getter = new CourseDataGetter("CSC");
        String courseIdResult = getter.getCourseId("CSC108H1-F-20219");
        assertEquals("51814", courseIdResult);
    }

    @Test()
    public void testGettingHPS() throws URISyntaxException, IOException, InterruptedException {
        CourseDataGetter getter = new CourseDataGetter("HPS");
        String courseIdResult = getter.getCourseId("HPS100H1-F-20219");
        assertEquals("53326", courseIdResult);
    }

    @Test()
    public void testGettingLectures() throws URISyntaxException, IOException, InterruptedException {
        CourseDataGetter getter = new CourseDataGetter("CSC");
        ArrayList<String> meetingsActual = getter.getAllLectureSections("CSC108H1-F-20219");
        ArrayList<String> meetingsExpected = new ArrayList<>();

        meetingsExpected.add("LEC-9901");
        meetingsExpected.add("LEC-9902");
        meetingsExpected.add("LEC-9903");
        meetingsExpected.add("LEC-9904");
        meetingsExpected.add("LEC-9905");
        meetingsExpected.add("LEC-9906");
        meetingsExpected.add("LEC-9907");
        meetingsExpected.add("LEC-9908");
        meetingsExpected.add("LEC-9909");
        meetingsExpected.add("LEC-9910");

        boolean actual = meetingsActual.containsAll(meetingsExpected) && meetingsExpected.containsAll(meetingsActual);
        assertEquals(true, actual);
    }

    @Test()
    public void testGettingMeeting() throws URISyntaxException, IOException, InterruptedException {
        CourseDataGetter getter = new CourseDataGetter("CSC");
        HashMap<String, String> meetingTime = getter.getMeetingTimes("CSC108H1-F-20219", "LEC-9901");
        boolean actual = meetingTime.containsKey("MO") && meetingTime.containsKey("WE");

        assertEquals(true, actual);
    }

}
