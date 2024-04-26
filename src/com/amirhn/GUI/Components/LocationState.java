package com.amirhn.GUI.Components;

import java.awt.*;

public enum LocationState {
    LASTMOVE(new Color(155, 199, 0, 90)),
    SELECTED(new Color(20,85,30,100)),
    SUGGESTED(new Color(20,85,30,100)),
    NORMAL(new Color(0, 0, 0, 0));

    public final Color color;

    LocationState(Color color) {
        this.color = color;
    }
}
