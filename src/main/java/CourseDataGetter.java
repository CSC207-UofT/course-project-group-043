import org.json.JSONArray;
import org.json.JSONObject;
// GOT THE ABOVE TO WORK USING THE LAST ANSWER ON HERE: https://stackoverflow.com/questions/34676940/importing-json-library-into-intellij-idea
// potentially also need this, but not sure: https://mvnrepository.com/artifact/org.json/json/20140107

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import static java.time.temporal.ChronoUnit.SECONDS;

// roughly followed tutorials from here: https://www.baeldung.com/java-9-http-client and
// https://stackoverflow.com/questions/1359689/how-to-send-http-request-in-java

public class CourseDataGetter {

    private JSONObject courseData;

    // are constructors allowed to throw exceptions??
    public CourseDataGetter(String courseCode) throws URISyntaxException, IOException, InterruptedException {
        this.courseData = fetchWebsiteInfo(courseCode);
    }

    // i truly do not remember how to handle these exceptions right now, help
    public JSONObject fetchWebsiteInfo(String courseCode) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://timetable.iit.artsci.utoronto.ca/api/20219/courses?code=" + courseCode.toLowerCase()))
                .timeout(Duration.of(10, SECONDS))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        return new JSONObject(response.body());
    }

    public ArrayList<String> getAllLectureSections(String courseName) {
        JSONObject lectureSecJSON = courseData.getJSONObject(courseName).getJSONObject("meetings");
        ArrayList<String> lectureKeys = new ArrayList<>();

        // iterating to get all the keys
        Iterator<String> iterator = lectureSecJSON.keys();
        while (iterator.hasNext()) {
            lectureKeys.add(iterator.next());
        }

        return lectureKeys;
    }

    public HashMap<String, String> getMeetingTimes(String courseName, String lectureSection) {
        JSONObject lectureSchedJSON = courseData.getJSONObject(courseName)
                .getJSONObject("meetings")
                .getJSONObject(lectureSection)
                .getJSONObject("schedule");
        HashMap<String, String> meetingTimes = new HashMap<>();

        Iterator<String> iterator = lectureSchedJSON.keys();
        while (iterator.hasNext()) {
            JSONObject meeting = lectureSchedJSON.getJSONObject(iterator.next());
            String day = meeting.getString("meetingDay");
            String time = meeting.getString("meetingStartTime") + " - " + meeting.getString("meetingEndTime");
            meetingTimes.put(day, time);
        }

        return meetingTimes;
    }

    public String getCourseId(String courseName) {
        // note that courseCode must exactly match what is in the data
        return courseData.getJSONObject(courseName).get("courseId").toString();
    }

    public void setCourseData(JSONObject courseData) {
        this.courseData = courseData;
    }

    public JSONObject getCourseData() {
        return courseData;
    }

}
