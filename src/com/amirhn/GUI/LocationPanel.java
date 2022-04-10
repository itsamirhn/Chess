package com.amirhn.GUI;

import com.amirhn.Game.Location;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LocationPanel extends JLayeredPane {

    public static final Dimension Size = new Dimension(100, 100);
    public static final Color LightLocationColor = new Color(240, 217, 181);
    public static final Color DarkLocationColor = new Color(181, 136, 99);

    enum State {
        LASTMOVE(new Color(155, 199, 0, 90)),
        SELECTED(new Color(20,85,30,100)),
        SUGGESTED(new Color(20,85,30,100)),
        NORMAL(new Color(0, 0, 0, 0));

        private final Color color;

        State(Color color) {
            this.color = color;
        }
    }

    private State state;
    private final JPanel statePanel;

    public final Location location;

    public LocationPanel(Location location, LocationListener locationListener) {
        super();
        this.location = location;
        this.setPreferredSize(Size);

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                locationListener.locationSelected(location, e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        this.setBackground(this.location.isLight() ? LightLocationColor : DarkLocationColor);
        this.setOpaque(true);

        this.statePanel = new JPanel();
        this.statePanel.setLayout(null);
        this.statePanel.setPreferredSize(Size);
        this.statePanel.setBounds(0, 0, Size.width, Size.height);
        this.add(statePanel, Integer.valueOf(0));

        this.setState(State.NORMAL);

        this.validate();
    }

    public void setState(State state) {
        this.statePanel.setBackground(state.color);
        this.state = state;
        repaint();
    }

     public State getState() {
        return this.state;
    }
}
