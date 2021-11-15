import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
        //The following line needs to be changed to the path of the key file for your device
        serviceAccount = new FileInputStream("C:/Users/denni/IdeaProjects/course-project-group-043/csc207-043-538e5a047423.json");
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
        data.put("userSchedule", user.getUserSchedule().schedule);
        data.put("userFriends", user.getUserFriends());
        DocumentReference docRef = db.collection("users").document(user.getUserName());
        ApiFuture<WriteResult> result = docRef.set(data);
        ApiFuture<QuerySnapshot> query = db.collection("users").get();
        QuerySnapshot querySnapshot = query.get();
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
}

