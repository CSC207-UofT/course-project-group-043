package Entities.events;

/**
 * Represents an event in the user's schedule that is
 * specifically for a course (i.e., a lecture).
 */
public class CourseEvent extends Event {

    private final String eventType;

    public CourseEvent(){
        this.eventType = "COURSE EVENT";
    }

    @Override
    public String getEventType() {
        return this.eventType;
    }
}
