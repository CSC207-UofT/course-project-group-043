import java.util.ArrayList;

public class Person {

    // style warnings -- all efforts to fix these have broken other parts of our code
    private String userName;
    private String userPassword;
    private Schedule userSchedule;
    private ArrayList<String> userFriends;

    public Person(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userSchedule = new Schedule();
        this.userFriends = new ArrayList<>();
    }

    public Schedule getUserSchedule() {
        return userSchedule;
    }

}
