package InterfaceAdapters;

import Entities.Person;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class InformationSaver {
    private InputStream serviceAccount;
    private GoogleCredentials credentials;
    private FirebaseOptions options;
    private Firestore db;
    private ScheduleEditor scheduleEditor;
    private AccountCreator accountCreator;

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

    public void saveUser(String name, UserList userList) throws ExecutionException, InterruptedException {
        Map<String, Object> data = new HashMap<>();
        data.put("userName", userList.getUser(name).getUserName());
        data.put("userPassword", userList.getUser(name).getUserPassword());
        //data.put("userSchedule", user.getUserSchedule().getSchedule());
        //data.put("userFriends", user.getUserFriends());
        data.put("userSecurity", userList.getUser(name).getTrustedAnswer());
        DocumentReference docRef = db.collection("users").document(userList.getUser(name).getUserName());
        docRef.set(data);
        ApiFuture<QuerySnapshot> query = db.collection("users").get();
        query.get();
    }

    // unable to fix this use of Person since friendList is an ArrayList of Person
    public void saveUserFriends(String user, UserList userList) throws ExecutionException, InterruptedException {
        for (Person friend : userList.getUser(user).getUserFriends()) {
            Map<String, Object> data = new HashMap<>();
            data.put("friendName", friend.getUserName());
            DocumentReference docRef = db.collection(user + "Friends").document(friend.getUserName());
            docRef.set(data);
            ApiFuture<QuerySnapshot> query = db.collection(user + "Friends").get();
            query.get();
        }
    }

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

    public void saveUserEvent(String eventType, String eventName, String eventDay, int eventStartTime, int eventEndTime, Person user) throws ExecutionException, InterruptedException {
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

    public void deleteUserEvent(String user, Event event) {
        db.collection(user + "Events").document(event.getEventName() + event.getEventDay() + event.getEventStartTime()).delete();

    }

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
                scheduleEditor.addEvent(eventType, eventName, eventDay, intEventStartTime, intEventEndTime, users.getUser(user));
            }
        }
    }

    public void retrieveFriends(String user, UserList users) throws ExecutionException, InterruptedException {

        ApiFuture<QuerySnapshot> query = db.collection(user + "Friends").get();
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            String friendName = document.getString("friendName");
            users.getUser(user).getUserFriends().add(users.getUser(friendName));
        }
    }

    public void retrieveOutgoing(String user, UserList users) throws ExecutionException, InterruptedException {

        ApiFuture<QuerySnapshot> query = db.collection(user + "Outgoing").get();
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            String friendName = document.getString("friendName");
            users.getUser(user).getUserFriends().add(users.getUser(friendName));
        }
    }

    public void retrieveIncoming(String user, UserList users) throws ExecutionException, InterruptedException {

        ApiFuture<QuerySnapshot> query = db.collection(user + "Incoming").get();
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            String friendName = document.getString("friendName");
            users.getUser(user).getUserFriends().add(users.getUser(friendName));
        }
    }

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

}

