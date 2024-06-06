package com.amirhn.GUI.Components;

import com.amirhn.GUI.Constants;
import java.awt.*;

/**
 * The enum Location state.
 */
public enum LocationState {
  /**
   *Lastmove location state.
   */
LASTMOVE(Constants.YellowGreenColor),
  /**
   *Selected location state.
   */
SELECTED(Constants.GreenColor),
  /**
   *Suggested location state.
   */
SUGGESTED(Constants.GreenColor),
  /**
   *Normal location state.
   */
NORMAL(Constants.TransparentColor);

  /**
   * The Color.
   */
public final Color color;

  LocationState(Color color) {
    this.color = color;
  }
}
