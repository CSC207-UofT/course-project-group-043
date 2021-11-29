package events;

//For CourseEvent, if we are using the database/API then we won't need to have them put in the event details
public class CourseEvent extends Event {

    private final String eventType;

    //with the provided database/API do we not need them to input the event details?
    public CourseEvent(){
        this.eventType = "COURSE EVENT";
    }

    @Override
    public String getEventType() {
        return this.eventType;
    }
}
