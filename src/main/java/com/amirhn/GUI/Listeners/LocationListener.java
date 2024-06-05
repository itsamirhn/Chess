package com.amirhn.GUI.Listeners;

import com.amirhn.GameTest.Location;

public interface LocationListener {
  void locationSelected(Location location);

  void locationGrabbed(Location location);

  void locationDropped(Location location);

  boolean isLocationDraggable(Location location);
}