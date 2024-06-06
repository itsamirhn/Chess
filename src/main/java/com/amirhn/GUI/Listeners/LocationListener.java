package com.amirhn.GUI.Listeners;

import com.amirhn.Game.Location;

/** The interface Location listener. */
public interface LocationListener {
  /**
   * Location selected.
   *
   * @param location the location
   */
  void locationSelected(Location location);

  /**
   * Location grabbed.
   *
   * @param location the location
   */
  void locationGrabbed(Location location);

  /**
   * Location dropped.
   *
   * @param location the location
   */
  void locationDropped(Location location);

  /**
   * Is location draggable boolean.
   *
   * @param location the location
   * @return the boolean
   */
  boolean isLocationDraggable(Location location);
}
