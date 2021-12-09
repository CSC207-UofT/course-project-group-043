package Entities.events;

/**
 * Represents an event in the user's schedule related to fitness.
 */
public class FitnessEvent extends Event {

    private final String eventType;

    public FitnessEvent(){
        this.eventType = "FITNESS EVENT";
    }

    @Override
    public String getEventType() {
        return this.eventType;
    }

}
