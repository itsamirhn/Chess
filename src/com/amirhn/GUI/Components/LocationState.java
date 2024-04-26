package com.amirhn.GUI.Components;

import com.amirhn.GUI.Constants;

import java.awt.*;

public enum LocationState {
    LASTMOVE(Constants.YellowGreenColor),
    SELECTED(Constants.GreenColor),
    SUGGESTED(Constants.GreenColor),
    NORMAL(Constants.TransparentColor);

    public final Color color;

    LocationState(Color color) {
        this.color = color;
    }
}
