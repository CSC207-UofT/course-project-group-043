package Entities.events;

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
