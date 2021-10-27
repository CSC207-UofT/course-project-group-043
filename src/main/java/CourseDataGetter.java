import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

public class CourseDataGetter {

    public static void main (String [] args) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://timetable.iit.artsci.utoronto.ca/api/20219/courses?code=csc"))
                .timeout(Duration.of(10, SECONDS))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

}


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
