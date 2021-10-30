import java.util.ArrayList;

public class Person {

    // style warnings -- all efforts to fix these have broken other parts of our code
    private String userName;
    private String userPassword;
    private Schedule userSchedule;
    private ArrayList<String> userFriends;
    private String trustedAnswer;

    public Person() {
        // need to create an empty person to work with for accountcreator
    }

    public Person(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userSchedule = new Schedule();
        this.userFriends = new ArrayList<>();
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Schedule getUserSchedule() {
        return userSchedule;
    }

    public void setTrustedAnswer(String answer) {
        this.trustedAnswer = answer;
    }

    public String getTrustedAnswer() {
        return this.trustedAnswer;
    }

}
