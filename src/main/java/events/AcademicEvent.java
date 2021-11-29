package events;


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

