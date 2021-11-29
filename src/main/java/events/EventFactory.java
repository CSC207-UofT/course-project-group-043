package events;

public class EventFactory {

    public Event getEvent(String eventType) {
        if (eventType.equalsIgnoreCase("Course")) {
            return new CourseEvent();
        } else if (eventType.equalsIgnoreCase("Academic")) {
            return new AcademicEvent();
        } else if (eventType.equalsIgnoreCase("Fitness")) {
            return new FitnessEvent();
        } else if (eventType.equals("Social")) {
            return new SocialEvent();
        }
        return null;
    }
}
