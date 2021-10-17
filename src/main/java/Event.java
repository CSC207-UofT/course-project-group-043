public class Event {

    // have been declared final to remove the style error
    // will add getters and setters as needed
    private final String eventName;
    private final String eventDay;
    private final int eventStartTime;
    private final int eventEndTime;

    public Event(String eventName, String eventDay, int eventStartTime, int eventEndTime){
        this.eventName = eventName;
        this.eventDay = eventDay;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
    }

    public String getEventName() {
        return this.eventName;
    }

    public String getEventDay() {
        return this.eventDay;
    }

    public int getEventStartTime(){
        return this.eventStartTime;
    }

    public int getEventEndTime(){
        return this.eventEndTime;
    }
}
