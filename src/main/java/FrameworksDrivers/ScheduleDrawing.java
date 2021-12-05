package FrameworksDrivers;

import Entities.Event;
import Entities.Schedule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class ScheduleDrawing extends JComponent {

    ArrayList<Event> schedule;
    HashMap<JButton, Event> buttons;

    private JTextField addEventName = new JTextField();
    private JComboBox addEventDate = new JComboBox(new String[]{"Monday", "Tuesday", "Wednesday",
            "Thursday", "Friday", "Saturday", "Sunday"});
    private String[] timesList = {"00:00", "1:00", "2:00", "3:00", "4:00", "5:00", "6:00",
            "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00",
            "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"};
    private JComboBox addEventStart = new JComboBox(timesList);
    private JComboBox addEventEnd = new JComboBox(timesList);

    EditEventListener editEventListener = new EditEventListener();




    public ScheduleDrawing(ArrayList<Event> schedule){
        this.schedule = schedule;
        this.buttons = new HashMap<>();



    }
    public void paintComponent(Graphics g){


        Color color0 = Color.white;
        Color color1 = new Color(110, 205, 250);
        Color color2 = new Color(39, 208, 245);
        Color color3 = new Color(0, 210, 231);
        Color color4 = new Color(0, 211, 207);
        Color color5 = new Color(0, 210, 175);
        Color color6 = new Color(0, 207, 137);
        Color color7 = new Color(65, 202, 94);

        Color[] colorsList = {color0, color1, color2, color3, color4, color5, color6, color7};

        String[] titles = {"Time", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        String[] timesList = {"00:00", "1:00", "2:00", "3:00", "4:00", "5:00", "6:00",
                "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00",
                "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"};


        // drawing the day of week header boxes & outlines
        for (int x = 0; x < 8; x++) {
            g.setColor(colorsList[x]); // different color for each day slot
            g.fillRect(25 + (x * 100), 100, 100, 40);
            g.setColor(Color.BLACK);
            g.drawString(titles[x],53 + (x * 100), 125);
        }
//
        g.setColor(Color.white);
        for (int x = 0; x < 8; x++) {
            g.drawRect(25 + (x * 100), 100, 100, 40);
        }
//
        // drawing the empty schedule outlines
        for (int x = 0; x < 8; x++) {
            for (int i = 0; i < 24; i++) {
                g.drawRect(25 + (x * 100), 140 + (i * 25), 100, 25);
            }
        }

        for (int x = 0; x < 24; x++) {
            g.setColor(Color.gray);
            g.fillRect(25, 140 + (x * 25), 100, 25);
            g.setColor(Color.BLACK);
            g.drawString(timesList[x], 50, 160 +(25 * x));
        }

        drawEvents(g, this.schedule);
    }

    private void drawEvents(Graphics g, ArrayList<Event> schedule) {

        for(int j = 0; j < schedule.size(); j++){
            String[] titles = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};


           Integer[] timesList = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
               20, 21, 22, 23, 24};

            int xcord = 0;
            for (int i = 0; i < 7; i++){
                if (Objects.equals(schedule.get(j).getEventDay(), titles[i])){
                    xcord = 125 + (i * 100);
                }
            }

            int yStartCord = 0;
            for (int i = 0; i < 24; i++){
                if (Objects.equals(schedule.get(j).getEventStartTime(), timesList[i])){
                    yStartCord = 140 + (i * 25);
                }
            }

            int yEndCord = 0;
            for (int i = 0; i < 24; i++){
                if (Objects.equals(schedule.get(j).getEventEndTime(), timesList[i])){
                    yEndCord = 140 + (i * 25);
                }
            }
            System.out.println(xcord);
            System.out.println(yStartCord);
            System.out.println(yEndCord);

            JButton eventbutton = new JButton(schedule.get(j).getEventName());

            eventbutton.setBounds(xcord,yStartCord,100,(yEndCord-yStartCord));
            eventbutton.addActionListener(editEventListener);
            add(eventbutton);
            this.buttons.put(eventbutton, schedule.get(j));
//            g.setColor(Color.PINK); // different color for each day slot
//            g.fillRect(xcord, yStartCord, 100, (yEndCord - yStartCord));
//            g.setColor(Color.BLACK);
//            g.drawString(schedule.get(j).getEventName(), (xcord + 10), (yStartCord + 15));
        }
    }

    private class EditEventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            Object source = e.getSource();

            for (JButton button: buttons.keySet()) {
                if (source == button) {

                    Object[] addEventText = {"Name:", addEventName, "Date:",
                            addEventDate, "Start time:", addEventStart, "End time:", addEventEnd};

                    int buttonChoice = JOptionPane.showConfirmDialog(null,
                            addEventText, "Edit Entities.Event", JOptionPane.OK_CANCEL_OPTION);

                    if (buttonChoice == JOptionPane.OK_OPTION) {
                        // will interact with Entities.Event class

                        // temporarily assigning the values to temporary variables
                        String eventName = addEventName.getText();
                        String eventDate = (String) addEventDate.getSelectedItem();

                        String eventStart = (String) addEventStart.getSelectedItem();
                        String eventStartString = eventStart.split(":")[0];
                        int eventStartInt = Integer.parseInt(eventStartString);

                        String eventEnd = (String) addEventEnd.getSelectedItem();
                        String eventEndString = eventEnd.split(":")[0];
                        int eventEndInt = Integer.parseInt(eventEndString);


                        Event event = new Event(eventName, eventDate, eventStartInt, eventEndInt);

                        schedule.add(event);
                        Event removedEvent = buttons.get(button);
                        schedule.remove(removedEvent);
                        repaint();
                    }
                }
            }

        }
    }
}
