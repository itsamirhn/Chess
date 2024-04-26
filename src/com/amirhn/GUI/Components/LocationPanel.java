package com.amirhn.GUI.Components;

import javax.swing.*;
import java.awt.*;

public class LocationPanel extends JLayeredPane {

    public static final Dimension Size = new Dimension(100, 100);
    public static final Color LightLocationColor = new Color(240, 217, 181);
    public static final Color DarkLocationColor = new Color(181, 136, 99);

    private LocationState state;
    private final JPanel statePanel;


    public LocationPanel(boolean isLight) {
        super();
        this.setPreferredSize(Size);
        this.setBackground(isLight ? LightLocationColor : DarkLocationColor);
        this.setOpaque(true);

        this.statePanel = new JPanel();
        this.statePanel.setLayout(null);
        this.statePanel.setPreferredSize(Size);
        this.statePanel.setBounds(0, 0, Size.width, Size.height);
        this.add(statePanel, Integer.valueOf(0));

        this.setState(LocationState.NORMAL);

        this.validate();
    }

    public void setState(LocationState state) {
        this.statePanel.setBackground(state.color);
        this.state = state;
        repaint();
    }

     public LocationState getState() {
        return this.state;
    }
}
