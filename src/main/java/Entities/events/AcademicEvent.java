package Entities.events;

/**
 * Represents an event in the user's schedule that is
 * related to academics but is not necessarily a lecture.
 */
public class AcademicEvent extends Event {

    private final String eventType;

    public AcademicEvent(){
        this.eventType = "ACADEMIC EVENT";
    }

    @Override
    public String getEventType() {
        return this.eventType;
    }
}

