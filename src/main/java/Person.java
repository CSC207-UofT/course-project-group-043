import java.util.ArrayList;

public class Person {

    // style warnings -- all efforts to fix these have broken other parts of our code
    private String userName;
    private String userPassword;
    private Schedule userSchedule;
    private ArrayList<Person> userFriends;
    private String trustedAnswer;
    private ArrayList<Person> incomingRequests;
    private ArrayList<Person> outgoingRequests;

    public Person() {
        // need to create an empty person to work with for accountcreator
    }

    public Person(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userSchedule = new Schedule();
        this.userFriends = new ArrayList<>();
        this.incomingRequests = new ArrayList<>();
        this.outgoingRequests = new ArrayList<>();
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

    public String getUserName() {return userName;}

    public void setTrustedAnswer(String answer) {
        this.trustedAnswer = answer;
    }

    public ArrayList<Person> getIncomingRequests() { return incomingRequests;}

    public void setIncomingRequests(ArrayList<Person> incomingRequests) {
        this.incomingRequests = incomingRequests;}

    public ArrayList<Person> getOutgoingRequests() { return outgoingRequests;}

    public void setOutgoingRequests(ArrayList<Person> outgoingRequests) {
        this.outgoingRequests = outgoingRequests;}

    public ArrayList<Person> getUserFriends() { return userFriends;}

    public void setUserFriends(ArrayList<Person> userFriends) {
        this.userFriends = userFriends;
    }

}
