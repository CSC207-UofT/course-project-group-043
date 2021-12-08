package Entities;

import java.util.ArrayList;
import Entities.events.*;

public class Schedule {

    private ArrayList<Event> events;

    public Schedule() {
        this.events = new ArrayList<>();
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> event) {
        events = event;
    }


}
