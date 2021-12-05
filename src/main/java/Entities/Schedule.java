package Entities;

import java.util.ArrayList;
import java.util.HashMap;
import Entities.events.*;

public class Schedule {

    // todo: make a toString method for Entities.Event to make this work better
    private HashMap<String, HashMap<String, String>> schedule;  // day, start hour, event name
    private ArrayList<Event> events;

    public Schedule() {
        this.schedule = new HashMap<>();
        this.events = new ArrayList<>();
        HashMap<String, String> monday = new HashMap<>();

        for (int i = 0; i <= 23; ++i) {
            monday.put(String.valueOf(i), null);
        }

        HashMap<String, String> tuesday = new HashMap<>();

        for (int i = 0; i <= 23; ++i) {
            tuesday.put(String.valueOf(i), null);
        }

        HashMap<String, String> wednesday = new HashMap<>();

        for (int i = 0; i <= 23; ++i) {
            wednesday.put(String.valueOf(i), null);
        }

        HashMap<String, String> thursday = new HashMap<>();

        for (int i = 0; i <= 23; ++i) {
            thursday.put(String.valueOf(i), null);
        }

        HashMap<String, String> friday = new HashMap<>();

        for (int i = 0; i <= 23; ++i) {
            friday.put(String.valueOf(i), null);
        }

        HashMap<String, String> saturday = new HashMap<>();

        for (int i = 0; i <= 23; ++i) {
            saturday.put(String.valueOf(i), null);
        }

        HashMap<String, String> sunday = new HashMap<>();

        for (int i = 0; i <= 23; ++i) {
            sunday.put(String.valueOf(i), null);
        }

        this.schedule.put("Monday", monday);
        this.schedule.put("Tuesday", tuesday);
        this.schedule.put("Wednesday",wednesday);
        this.schedule.put("Thursday", thursday);
        this.schedule.put("Friday", friday);
        this.schedule.put("Saturday", saturday);
        this.schedule.put("Sunday", sunday);
    }

    public HashMap<String, HashMap<String, String>> getSchedule() {
        return schedule;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> event) {
        events = event;
    }

    public void setSchedule(HashMap<String, HashMap<String, String>> schedule) {
        this.schedule = schedule;
    }

    public String toString() {
        StringBuilder rep = new StringBuilder();
        //todo: put this in constants?
        String[] keys = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        for (String key : keys) {
            rep.append(key).append(":\n");
            for (int time = 0 ; time < 24 ; time++) {
                HashMap<String, String> oneDay = schedule.get(key);

                if (oneDay.get(String.valueOf(time)) != null) {
                    rep.append("\t").append(oneDay.get(String.valueOf(time))).append(" at ").append(time).append("h\n");
                }
            }
        }

        return rep.toString();
    }

}
