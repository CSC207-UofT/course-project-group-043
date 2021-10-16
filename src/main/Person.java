import java.util.ArrayList;

public class Person {

    private String userName;
    private String userPassword;
    private Schedule userSchedule;
    private ArrayList<String> userFriends;

    public Person(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userSchedule = new Schedule();
        this.userFriends = new ArrayList<String>();
    }

    public String getUserName() {
        return this.userName;
    }

    public String getUserPassword() {
        return this.userPassword;
    }

    public ArrayList<String> getUserFriends() {
        return this.userFriends;
    }

    public Schedule getUserSchedule() {
        return userSchedule;
    }
}
