package Entities.events;

/**
 * Represents an event in the user's schedule related to
 * social activities, such as spending time with friends.
 */
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
