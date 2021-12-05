package Entities;

import Entities.events.Event;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Schedule {

    // todo: make a toString method for Entities.Event to make this work better
    //NOTE: had to make schedule public to access it in ScheduleEditor
    private HashMap<String, ArrayList<Event>> schedule;  // day, events

    public Schedule() {
        this.schedule = new HashMap<>();
        ArrayList<Event> monday = new ArrayList<Event>();
        ArrayList<Event> tuesday = new ArrayList<Event>();
        ArrayList<Event> wednesday = new ArrayList<Event>();
        ArrayList<Event> thursday = new ArrayList<Event>();
        ArrayList<Event> friday = new ArrayList<Event>();
        ArrayList<Event> saturday = new ArrayList<Event>();
        ArrayList<Event> sunday = new ArrayList<Event>();

        this.schedule.put("Monday", monday);
        this.schedule.put("Tuesday", tuesday);
        this.schedule.put("Wednesday",wednesday);
        this.schedule.put("Thursday", thursday);
        this.schedule.put("Friday", friday);
        this.schedule.put("Saturday", saturday);
        this.schedule.put("Sunday", sunday);
    }

    public HashMap<String, ArrayList<Event>> getSchedule() {
        return schedule;
    }


}
