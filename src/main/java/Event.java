public class Event {

    private String eventName;
    private String eventDay;
    private int eventStartTime;
    private int eventEndTime;

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

    public void setEventName(String eventName){
        this.eventName = eventName;
    }

    public void setEventDay(String eventDay) {
        this.eventDay = eventDay;
    }

    public void setEventStartTime(int eventStartTime){
        this.eventStartTime = eventStartTime;
    }

    public void setEventEndTime(int eventEndTime) {
        this.eventEndTime = eventEndTime;
    }
}
