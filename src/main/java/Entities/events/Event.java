package Entities.events;

/**
 * Abstract class representing a particular event
 * in the user's schedule.
 */
public abstract class Event {
    public String eventName;
    public String eventDay;
    public int eventStartTime;
    public int eventEndTime;


    public void setEventName(String eventName) {
        this.eventName = eventName;

    }

    public void setEventDay(String day){
        this.eventDay = day;
    }


    public void setEventStartTime(int start) {
        this.eventStartTime = start;
    }

    public void setEventEndTime(int end) {
        this.eventEndTime = end;
    }


    public String getEventDay() {
        return this.eventDay;
    }

    public String getEventName() {
        return this.eventName;
    }

    public int getEventStartTime() {
        return this.eventStartTime;
    }

    public int getEventEndTime() {
        return this.eventEndTime;
    }

    public abstract String getEventType();

}
