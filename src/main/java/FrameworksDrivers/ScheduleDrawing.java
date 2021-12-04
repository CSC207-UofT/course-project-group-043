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
    private String[] timesList = {"00:00am", "1:00am", "2:00am", "3:00am", "4:00am", "5:00am", "6:00am",
            "7:00am", "8:00am", "9:00am", "10:00am", "11:00am", "12:00pm", "13:00pm", "14:00pm", "15:00pm",
            "16:00pm", "17:00pm", "18:00pm", "19:00pm", "20:00pm", "21:00pm", "22:00pm", "23:00pm"};
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
        String[] timesList = {"12:00am", "1:00am", "2:00am", "3:00am", "4:00am", "5:00am", "6:00am", "7:00am",
                "8:00am", "9:00am", "10:00am", "11:00am", "12:00pm", "1:00pm", "2:00pm", "3:00pm", "4:00pm",
                "5:00pm", "6:00pm", "7:00pm", "8:00pm", "9:00pm", "10:00pm", "11:00pm"};


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
