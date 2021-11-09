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

import static java.time.temporal.ChronoUnit.SECONDS;

// roughly followed tutorials from here: https://www.baeldung.com/java-9-http-client and
// https://stackoverflow.com/questions/1359689/how-to-send-http-request-in-java

public class CourseDataGetter {

    public static void main (String [] args) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://timetable.iit.artsci.utoronto.ca/api/20219/courses?code=csc")) //todo: make this last part a variable
                .timeout(Duration.of(10, SECONDS))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject allCourseData  = new JSONObject(response.body());
//        System.out.println(jObject.get("CSC108H1-F-20219")); // it sure does print it but it's real ugly
        JSONObject oneCourse = allCourseData.getJSONObject("CSC108H1-F-20219");
        System.out.println(oneCourse.get("courseId")); // THIS PRINTS IT CORRECTLY
//        System.out.println(oneCourse.getJSONObject("meetings"));

        if (oneCourse.get("courseId").equals("51814")) {
            System.out.println("yep, that's the course code we wanted");
        }

//        System.out.println(response.body()); // this prints it out nicely
//        System.out.println(response.body().getClass());  // this is for sure a java string

        // all this is for json.simple library
//        JSONParser parser = new JSONParser();
//        JSONArray testObj = (JSONArray) parser.parse(response.body());
//        System.out.println(testObj.get(0));


//        String courseName = (String) testObj.get("CSC108H1-F-20219");
//        System.out.println(courseName);

//        System.out.println(testObj);

//        System.out.println(testObj.get("CSC108H1-F-20219")); // this returns null, which it decidedly should not

        // at minimum there are like 6 levels to this BS

//        JSONObject test2 = (JSONObject) parser.parse(String.valueOf(testObj));
        // don't know what that comment down there is about, this just gives you null
//        System.out.println(test2.get("code")); // this does work. it does give you that specific object.
    }

}

// this is the more old-fashioned method, but it better allows us to work with each individual line
//import java.net.*;
//import java.io.*;
//
//public class CourseDataGetter {
//    public static void main(String[] args) throws Exception {
//        URL timetableDataURL = new URL("https://timetable.iit.artsci.utoronto.ca/api/20219/courses?code=csc");
//        URLConnection connectionTimetable = timetableDataURL.openConnection();
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(
//                        connectionTimetable.getInputStream()));
//        String inputLine;
//
//        while ((inputLine = in.readLine()) != null)
//            System.out.println(inputLine);
//        in.close();
//    }
//}
