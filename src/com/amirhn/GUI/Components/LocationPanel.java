package com.amirhn.GUI.Components;

import com.amirhn.GUI.Constants;
import java.awt.*;
import javax.swing.*;

public class LocationPanel extends JLayeredPane {

  public static final Dimension Size = Constants.TileSize;
  private final JPanel statePanel;

  public LocationPanel(boolean isLight) {
    super();
    this.setPreferredSize(Constants.TileSize);
    this.setBackground(isLight ? Constants.LightTileColor : Constants.DarkTileColor);
    this.setOpaque(true);

    this.statePanel = new JPanel();
    this.statePanel.setLayout(null);
    this.statePanel.setPreferredSize(Constants.TileSize);
    this.statePanel.setBounds(0, 0, Constants.TileSize.width, Constants.TileSize.height);
    this.add(statePanel, Integer.valueOf(0));

    this.setState(LocationState.NORMAL);

    this.validate();
  }

  public void setState(LocationState state) {
    this.statePanel.setBackground(state.color);
    repaint();
  }
}
