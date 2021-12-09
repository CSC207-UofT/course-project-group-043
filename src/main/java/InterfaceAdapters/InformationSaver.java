package InterfaceAdapters;

import Entities.Person;
import Entities.Review;
import Entities.events.*;
import UseCaseClasses.AccountCreator;
import UseCaseClasses.ScheduleEditor;
import UseCaseClasses.UserList;
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

/**
 * Class responsible for sending and receiving information stored in
 * the Firebase database.
 */
public class InformationSaver {
    private InputStream serviceAccount;
    private GoogleCredentials credentials;
    private FirebaseOptions options;
    private Firestore db;
    private ScheduleEditor scheduleEditor;
    private AccountCreator accountCreator;

    /**
     * Creates a new instance of InformationSaver.
     *
     * @throws IOException
     */
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
        scheduleEditor = new ScheduleEditor();
        accountCreator = new AccountCreator();
    }

    /**
     * Save a user's username, password, and security question / answer in the database.
     *
     * @param name The username of the person whose information is being saved
     * @param userList A collection of all the users who have accounts in this program
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void saveUser(String name, UserList userList) throws ExecutionException, InterruptedException {
        Map<String, Object> data = new HashMap<>();
        data.put("userName", userList.getUser(name).getUserName());
        data.put("userPassword", userList.getUser(name).getUserPassword());
        data.put("userSecurity", userList.getUser(name).getTrustedAnswer());
        DocumentReference docRef = db.collection("users").document(userList.getUser(name).getUserName());
        docRef.set(data);
        ApiFuture<QuerySnapshot> query = db.collection("users").get();
        query.get();
    }

    /**
     * Saves a user's list of friends in the database.
     *
     * @param user The username of the person whose friends will be saved
     * @param userList A list of all the users who have accounts in this program
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void saveUserFriends(String user, UserList userList) throws ExecutionException, InterruptedException {
        // unable to fix this use of Person since friendList is an ArrayList of Person
        for (Person friend : userList.getUser(user).getUserFriends()) {
            Map<String, Object> data = new HashMap<>();
            data.put("friendName", friend.getUserName());
            DocumentReference docRef = db.collection(user + "Friends").document(friend.getUserName());
            docRef.set(data);
            ApiFuture<QuerySnapshot> query = db.collection(user + "Friends").get();
            query.get();
        }
    }

    /**
     * Saves a user's list of outgoing friend requests.
     *
     * @param user The username of the person whose outgoing friend requests will be saved
     * @param userList A list of all the users who have accounts in this program
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void saveUserOutgoing(String user, UserList userList) throws ExecutionException, InterruptedException {
        for (Person friend : userList.getUser(user).getOutgoingRequests()) {
            Map<String, Object> data = new HashMap<>();
            data.put("friendName", friend.getUserName());
            DocumentReference docRef = db.collection(user + "Outgoing").document(friend.getUserName());
            docRef.set(data);
            ApiFuture<QuerySnapshot> query = db.collection(user + "Outgoing").get();
            query.get();
        }
    }

    /**
     * Saves a user's list of incoming friend requests.
     *
     * @param user The username of the person whose incoming friend requests are being saved
     * @param userList A list of all the users who have accounts in this program, and their associated information
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void saveUserIncoming(String user, UserList userList) throws ExecutionException, InterruptedException {
        for (Person friend : userList.getUser(user).getOutgoingRequests()) {
            Map<String, Object> data = new HashMap<>();
            data.put("friendName", friend.getUserName());
            DocumentReference docRef = db.collection(user + "Incoming").document(friend.getUserName());
            docRef.set(data);
            ApiFuture<QuerySnapshot> query = db.collection(user + "Incoming").get();
            query.get();
        }
    }

    /**
     * Saves an Event in a user's schedule to the database.
     *
     * @param eventType The type of event being saved
     * @param eventName The name of the event being saved
     * @param eventDay The day of the week on which the event is
     * @param eventStartTime The time of day (on a 24 hour clock) that the event starts
     * @param eventEndTime The time of day (on a 24 hour clock) that the event ends
     * @param user The username for the user that has this event in their schedule
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void saveUserEvent(String eventType, String eventName, String eventDay, int eventStartTime,
                              int eventEndTime, Person user) throws ExecutionException, InterruptedException {
        Map<String, Object> data = new HashMap<>();
        data.put("eventType", eventType);
        data.put("eventName", eventName);
        data.put("eventDay", eventDay);
        data.put("eventStartTime", eventStartTime);
        data.put("eventEndTime", eventEndTime);
        data.put("user", user.getUserName());
        DocumentReference docRef = db.collection(user.getUserName() + "Events").document(eventName + eventDay + eventStartTime);
        docRef.set(data);
        ApiFuture<QuerySnapshot> query = db.collection(user.getUserName() + "Events").get();
        query.get();
    }

    /**
     * Deletes an event from a user's schedule in the database
     *
     * @param user The username of the user
     * @param event The event to remove
     */
    public void deleteUserEvent(String user, Event event) {
        db.collection(user + "Events").document(event.getEventName() + event.getEventDay() + event.getEventStartTime()).delete();

    }

    /**
     * Gets all the events in a user's schedule, along with associated times and type of event,
     * and stores it in the user's schedule
     *
     * @param user The username of the user whose schedule is being retrieved
     * @param users A list of all the users in the program
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void retrieveEvents(String user, UserList users) throws ExecutionException, InterruptedException {

        ApiFuture<QuerySnapshot> query = db.collection(user + "Events").get();
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            String eventType = document.getString("eventType");
            String eventName = document.getString("eventName");
            String eventDay = document.getString("eventDay");
            Long eventStartTime = document.getLong("eventStartTime");
            Long eventEndTime = document.getLong("eventEndTime");
            int intEventStartTime = eventStartTime.intValue();
            int intEventEndTime = eventEndTime.intValue();
            if (eventType != null && eventName != null && eventDay != null) {
                scheduleEditor.addEvent(eventType, eventName, eventDay, intEventStartTime,
                        intEventEndTime, users.getUser(user));
            }
        }
    }

    /**
     * Gets a given user's friends, as stored in the database.
     *
     * @param user The user whose friends are being retrieved
     * @param users A list of all the users in the program
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void retrieveFriends(String user, UserList users) throws ExecutionException, InterruptedException {

        ApiFuture<QuerySnapshot> query = db.collection(user + "Friends").get();
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            String friendName = document.getString("friendName");
            users.getUser(user).getUserFriends().add(users.getUser(friendName));
        }
    }

    /**
     * Gets a given user's outgoing friend requests, as stored in the database.
     *
     * @param user The user whose friends are being retrieved
     * @param users A list of all the users in the program
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void retrieveOutgoing(String user, UserList users) throws ExecutionException, InterruptedException {

        ApiFuture<QuerySnapshot> query = db.collection(user + "Outgoing").get();
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            String friendName = document.getString("friendName");
            users.getUser(user).getUserFriends().add(users.getUser(friendName));
        }
    }

    /**
     * Gets a user's incoming friend requests, as stored in the database.
     *
     * @param user The username of the account whose incoming requests are being retrieved
     * @param users A list of all users currently in the program
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void retrieveIncoming(String user, UserList users) throws ExecutionException, InterruptedException {

        ApiFuture<QuerySnapshot> query = db.collection(user + "Incoming").get();
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            String friendName = document.getString("friendName");
            users.getUser(user).getUserFriends().add(users.getUser(friendName));
        }
    }

    /**
     * Gets a collection of all the users stored in the database.
     *
     * @param users A list in which to store the users
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void retrieveUsers(UserList users) throws ExecutionException, InterruptedException {

        ApiFuture<QuerySnapshot> query = db.collection("users").get();
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            String Username = document.getString("userName");
            String Password = document.getString("userPassword");
            String Security = document.getString("userSecurity");
            accountCreator.makeAccount(Username, Password, Security, users);
        }
    }

    /**
     * Stores a Review and associated information in the database
     *
     * @param review The Review to be stored
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws IllegalArgumentException
     */
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

    /**
     *  Gets all the Reviews from the database.
     *
     * @return A list of all the Reviews stored in the database.
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws IllegalArgumentException
     */
    public ArrayList<Review> retrieveReviews() throws ExecutionException, InterruptedException, IllegalArgumentException {

        ArrayList<Review> allReviews = new ArrayList<>();

        ApiFuture<QuerySnapshot> query = db.collection("reviews").get();
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            String Location = document.getString("userName");
            String reviewBody = document.getString("reviewBody");
            String Rating = document.getString("rating");
            Integer rating = Integer.parseInt(Rating);
            Review a = new Review(Location, reviewBody, rating);
            allReviews.add(a);
        }
        return allReviews;
    }
}

