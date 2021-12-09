package InterfaceAdapters;

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

/**
 * For fetching information about various U of T courses from a website.
 */
public class CourseDataGetter {

    private JSONObject courseData;

    /**
     * Creates a new instance of CourseDataGetter
     *
     * @param courseCode Three letters indicating the type of course data to retrieve
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    public CourseDataGetter(String courseCode) throws URISyntaxException, IOException, InterruptedException {
        this.courseData = fetchWebsiteInfo(courseCode);
    }

    /**
     * Return information on courses with names that start with the given three letters in courseCode.
     * @param courseCode Three letters indicating the type of course data to retrieve
     * @return JSONObject that contains all of the information on the course data page
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
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

    /**
     * Return a list of all the lecture sections that exist for a certain course.
     * @param courseName The name of the course that the lecture sections are for.
     * @return ArrayList with strings that are the names of lecture sections.
     */
    public ArrayList<String> getAllLectureSections(String courseName) {
        JSONObject lectureSecJSON = courseData.getJSONObject(courseName).getJSONObject("meetings");
        ArrayList<String> lectureKeys = new ArrayList<>();

        if (checkCourse(courseName)) {
            // iterating to get all the keys
            Iterator<String> iterator = lectureSecJSON.keys();
            while (iterator.hasNext()) {
                lectureKeys.add(iterator.next());
            }
        }

        return lectureKeys;

    }

    /**
     * Checks whether a course is present
     *
     * @param courseName
     * @return true if the course exists
     */
    private boolean checkCourse(String courseName) {
        for (String specificName : courseData.keySet()) {
            if (specificName.toLowerCase().contains(courseName.toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Return meeting times (day and time) for a particular lecture section of a particular course.
     * @param courseName The name of the course of interest
     * @param lectureSection The name of the lecture section of interest
     * @return HashMap that contains keys representing the day on which a meeting occurs, and values
     *          representing the start and end time of the meeting
     */
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
