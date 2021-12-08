package FrameworksDrivers;

import Entities.events.Event;
import InterfaceAdapters.AccountManager;
import InterfaceAdapters.ScheduleManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import java.io.IOException;
import java.util.Objects;

public class CompareGraphics extends JFrame implements ActionListener {
    private Graphics g2g;
    private final AccountManager accountManager;
    private final ScheduleManager scheduleManager;
    private final String user1;
    private final String user2;

    private final String[] timesList = {"00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"};


    public CompareGraphics(String user1, String user2, AccountManager accountManager) throws IOException {
        super("Schedule Compare");
        this.user1 = user1;
        this.user2 = user2;
        this.accountManager = accountManager;
        this.scheduleManager = new ScheduleManager();

        setSize(850, 850); // setting the default size of the frame

        setLayout(null);
        JLabel titleLabel = new JLabel("Comparing Schedule");
        titleLabel.setFont(new Font("Verdana", Font.PLAIN, 18)); // setting different font for title
        titleLabel.setBounds(360, -5, 200, 50);
        add(titleLabel);

        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void paint(Graphics g) {
        super.paint(g);

        g2g = g;

        // creating custom colors
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

        // drawing the day of week header boxes & outlines
        for (int x = 0; x < 8; x++) {
            g2g.setColor(colorsList[x]); // different color for each day slot
            g2g.fillRect(25 + (x * 100), 100, 100, 40);
            g2g.setColor(Color.BLACK);
            g2g.drawString(titles[x],53 + (x * 100), 125);
        }
//
        g2g.setColor(Color.white);
        for (int x = 0; x < 8; x++) {
            g2g.drawRect(25 + (x * 100), 100, 100, 40);
        }
//
        // drawing the empty schedule outlines
        for (int x = 0; x < 8; x++) {
            for (int i = 0; i < 24; i++) {
                g2g.drawRect(25 + (x * 100), 140 + (i * 25), 100, 25);
            }
        }

        for (int x = 0; x < 24; x++) {
            g2g.setColor(Color.gray);
            g2g.fillRect(25, 140 + (x * 25), 100, 25);
            g2g.setColor(Color.BLACK);
            g2g.drawString(timesList[x], 50, 160 +(25 * x));
        }
        for (Event event : scheduleManager.compare(accountManager.getUserList().getUser(user1), accountManager.getUserList().getUser(user2)).getEvents()) {
            paintEvent(event.getEventDay(), event.getEventStartTime(), event.getEventEndTime());
        }
    }

    public void paintEvent(String eventDay, int eventStartTime, int eventEndTime){

        String[] titles = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        // is titles iterable??

        Integer[] timesList = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
                20, 21, 22, 23, 24};

        int xCord = 0;
        for (int i = 0; i < 7; i++){
            if (Objects.equals(eventDay, titles[i])){
                xCord = 125 + (i * 100);
            }
        }

        int yStartCord = 0;
        for (int i = 0; i < 25; i++){
            if (Objects.equals(eventStartTime, timesList[i])){
                yStartCord = 140 + (i * 25);
            }
        }

        int yEndCord = 0;
        for (int i = 0; i < 25; i++){
            if (Objects.equals(eventEndTime, timesList[i])){
                yEndCord = 140 + (i * 25);
            }
        }
        g2g.setColor(Color.PINK); // different color for each day slot
        g2g.fillRect(xCord, yStartCord, 100, (yEndCord - yStartCord));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
