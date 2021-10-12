public class Event {

    public String eventName;
    public String eventDay;
    public int eventStartTime;
    public int eventEndTime;

    public Event(String eventName, String eventDay, int eventStartTime, int eventEndTime){
        this.eventName = eventName;
        this.eventDay = eventDay;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
    }
}
