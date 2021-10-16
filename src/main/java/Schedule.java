import java.util.HashMap;

public class Schedule {

    // todo: make a toString method for Event to make this work better
    HashMap<String, HashMap<Integer, String>> schedule;  // day, start hour, event name

    public Schedule() {
        this.schedule = new HashMap<>();
        HashMap<Integer, String> monday = new HashMap<>();

        for (int i = 0; i <= 23; ++i) {
            monday.put(i, null);
        }

        HashMap<Integer, String> tuesday = new HashMap<>();

        for (int i = 0; i <= 23; ++i) {
            tuesday.put(i, null);
        }

        HashMap<Integer, String> wednesday = new HashMap<>();

        for (int i = 0; i <= 23; ++i) {
            wednesday.put(i, null);
        }

        HashMap<Integer, String> thursday = new HashMap<>();

        for (int i = 0; i <= 23; ++i) {
            thursday.put(i, null);
        }

        HashMap<Integer, String> friday = new HashMap<>();

        for (int i = 0; i <= 23; ++i) {
            friday.put(i, null);
        }

        HashMap<Integer, String> saturday = new HashMap<>();

        for (int i = 0; i <= 23; ++i) {
            saturday.put(i, null);
        }

        HashMap<Integer, String> sunday = new HashMap<>();

        for (int i = 0; i <= 23; ++i) {
            sunday.put(i, null);
        }

        this.schedule.put("Monday", monday);
        this.schedule.put("Tuesday", tuesday);
        this.schedule.put("Wednesday",wednesday);
        this.schedule.put("Thursday", thursday);
        this.schedule.put("Friday", friday);
        this.schedule.put("Saturday", saturday);
        this.schedule.put("Sunday", sunday);
    }

    public String toString() {
        StringBuilder rep = new StringBuilder();
        //todo: put this in constants?
        String[] keys = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        for (String key : keys) {
            rep.append(key + ":\n");
            for (int time = 0 ; time < 24 ; time++) {
                HashMap<Integer, String> oneDay = schedule.get(key);

                if (oneDay.get(time) != null) {
                    rep.append("\t" + oneDay.get(time) + " at " + time + "h\n");
                }
            }
        }

        return rep.toString();
    }

}
