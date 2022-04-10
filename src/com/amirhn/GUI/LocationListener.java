package com.amirhn.GUI;

import com.amirhn.Game.Location;

import java.awt.event.MouseEvent;

public interface LocationListener {
    void locationSelected(Location location, MouseEvent e);
}
