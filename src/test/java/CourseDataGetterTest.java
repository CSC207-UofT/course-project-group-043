import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

public class CourseDataGetterTest {

    @Test()
    public void testGettingCSC() throws URISyntaxException, IOException, InterruptedException {
        // note: this takes a while, relatively speaking (just under 2 seconds, apparently)
        CourseDataGetter getter = new CourseDataGetter();
        JSONObject result = getter.fetchWebsiteInfo("CSC");
        String courseIdResult = getter.getCourseId(result, "CSC108H1-F-20219");
        assertEquals("51814", courseIdResult);
    }

}
