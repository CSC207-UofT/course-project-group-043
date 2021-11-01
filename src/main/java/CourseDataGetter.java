import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

import static java.time.temporal.ChronoUnit.SECONDS;

// roughly followed tutorials from here: https://www.baeldung.com/java-9-http-client and
// https://stackoverflow.com/questions/1359689/how-to-send-http-request-in-java

public class CourseDataGetter {

    public static void main (String [] args) throws URISyntaxException, IOException, InterruptedException, ParseException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://timetable.iit.artsci.utoronto.ca/api/20219/courses?code=csc")) //todo: make this last part a variable
                .timeout(Duration.of(10, SECONDS))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

//        System.out.println(response.body()); // this prints it out nicely

        JSONParser parser = new JSONParser();
        JSONObject testObj = (JSONObject) parser.parse(response.body());

//        System.out.println(testObj);

//        System.out.println(testObj.get("CSC108H1-F-20219")); // this returns null, which it decidedly should not

        JSONObject test2 = (JSONObject) parser.parse(String.valueOf(testObj));
        System.out.println(test2.get("courseId")); // this does work. it does give you that specific object
    }

}

// this is the more old-fashioned method, but it better allows us to work with each individual line
//import java.net.*;
//import java.io.*;
//
//public class CourseDataGetter {
//    public static void main(String[] args) throws Exception {
//        URL yahoo = new URL("https://timetable.iit.artsci.utoronto.ca/api/20219/courses?code=csc");
//        URLConnection yc = yahoo.openConnection();
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(
//                        yc.getInputStream()));
//        String inputLine;
//
//        while ((inputLine = in.readLine()) != null)
//            System.out.println(inputLine);
//        in.close();
//    }
//}
