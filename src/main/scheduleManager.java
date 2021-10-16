public class scheduleManager {

    public void addEvent(String eventName, String eventDay, int eventStartTime, int eventEndTime, Person user) {
        Event event = new Event(eventName, eventDay, eventStartTime, eventEndTime);
        ScheduleEditor editor = new ScheduleEditor();
        editor.addEvent(event, user);
    }

    public void removeEvent(Event event, Person user) {
        ScheduleEditor editor = new ScheduleEditor();
        editor.removeEvent(event, user);
    }

    public void editEvent(String oldName, String newName, String eventDay, int startTime, int endTime, Person user) {
        ScheduleEditor editor = new ScheduleEditor();
        Event event = new Event(newName, eventDay, startTime, endTime);
        editor.editEvent(oldName, event, user);
    }
}
