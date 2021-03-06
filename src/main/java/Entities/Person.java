package Entities;

import java.util.ArrayList;

/**
 * Represents a user of the program, who uses one account
 * with their own schedule and list of friends.
 */
public class Person {

    private String userName;
    private String userPassword;
    private Schedule userSchedule;
    private ArrayList<Person> userFriends;

    private String trustedAnswer;
    private ArrayList<Person> incomingRequests;
    private ArrayList<Person> outgoingRequests;

    public Person() {
        // need to create an empty person to work with for accountcreator
        // however, the variables below need to be added since it as assumed all users have them
        this.userSchedule = new Schedule();
        this.userFriends = new ArrayList<>();
        this.incomingRequests = new ArrayList<>();
        this.outgoingRequests = new ArrayList<>();
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

    public String getTrustedAnswer() {
        return this.trustedAnswer;
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

    public String getUserPassword() {
        return userPassword;
    }

}
