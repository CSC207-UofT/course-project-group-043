package UseCaseClasses;

import Entities.Person;
import Entities.Review;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class InformationSaver {
    private InputStream serviceAccount;
    private GoogleCredentials credentials;
    private FirebaseOptions options;
    private Firestore db;

    public InformationSaver() throws IOException {

        serviceAccount = new FileInputStream("./src/csc207-043-538e5a047423.json");
        credentials = GoogleCredentials.fromStream(serviceAccount);
        options = FirebaseOptions.builder()
                .setCredentials(credentials)
                .build();
        if(FirebaseApp.getApps().isEmpty()) { // check with this line to see if database has been initialized yet
            FirebaseApp.initializeApp(options);
        }
        db = FirestoreClient.getFirestore();
    }

    public void saveUser(Person user) throws ExecutionException, InterruptedException {
        Map<String, Object> data = new HashMap<>();
        data.put("userName", user.getUserName());
        data.put("userPassword", user.getUserPassword());
        data.put("userSchedule", user.getUserSchedule().getSchedule());
        data.put("userFriends", user.getUserFriends());
        DocumentReference docRef = db.collection("users").document(user.getUserName());
        ApiFuture<WriteResult> result = docRef.set(data);
        ApiFuture<QuerySnapshot> query = db.collection("users").get();
        QuerySnapshot querySnapshot = query.get(); // this appears to do nothing but it will not work if you remove this
    }

    public ArrayList<Person> retrieveUsers() throws ExecutionException, InterruptedException {

        ArrayList<Person> people = new ArrayList<>();

        ApiFuture<QuerySnapshot> query = db.collection("users").get();
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            String user = document.getId();
            String Username = document.getString("userName");
            String Password = document.getString("userPassword");
            Person a = new Person(Username, Password);
            people.add(a);
        }
        return people;
    }

    public void saveReview(Review review) throws ExecutionException, InterruptedException, IllegalArgumentException {
        Map<String, Object> data = new HashMap<>();
        data.put("Location", review.getLocation());
        data.put("reviewBody", review.getReviewBody());
        data.put("rating", review.getRating());
        data.put("FullReview", review.getFullReview());
        DocumentReference docRef = db.collection("reviews").document(review.getLocation());
        ApiFuture<WriteResult> result = docRef.set(data);
        ApiFuture<QuerySnapshot> query = db.collection("reviews").get();
        QuerySnapshot querySnapshot = query.get(); //
    }

    public ArrayList<Review> retrieveReviews() throws ExecutionException, InterruptedException, IllegalArgumentException {

        ArrayList<Review> allReviews = new ArrayList<>();

        ApiFuture<QuerySnapshot> query = db.collection("reviews").get();
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            String review = document.getId();
            String Location = document.getString("userName");
            String reviewBody = document.getString("reviewBody");
            Integer rating = document.getString("rating");
            Review a = new Review(Location, reviewBody, rating);
            allReviews.add(a);
        }
        return allReviews;
    }

}

