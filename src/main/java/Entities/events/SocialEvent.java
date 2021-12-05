package Entities.events;

public class SocialEvent extends Event {

    private final String eventType;

    public SocialEvent(){
        this.eventType = "SOCIAL EVENT";
    }

    @Override
    public String getEventType() {
        return this.eventType;
    }
}
