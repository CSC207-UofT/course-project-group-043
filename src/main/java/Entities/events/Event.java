package Entities.events;

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

    //do not need setter for event type cuz each event class has set type and cannot change
    public abstract String getEventType();

}
