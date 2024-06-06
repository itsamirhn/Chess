package com.amirhn.GUI.Components;

import com.amirhn.GUI.Listeners.LocationListener;
import com.amirhn.Game.Location;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;
import javax.swing.*;

public class BoardPanel extends JLayeredPane implements MouseMotionListener, MouseListener {

  private final TablePanel tablePanel;
  private PiecePanel movingPiecePanel;
  private LocationListener locationListener;

  public BoardPanel(int rows, int columns) {
    super();
    this.tablePanel = new TablePanel(rows, columns);
    this.tablePanel.setBounds(
        0, 0, this.tablePanel.getPreferredSize().width, this.tablePanel.getPreferredSize().height);
    this.setPreferredSize(this.tablePanel.getPreferredSize());
    this.add(tablePanel, Integer.valueOf(0));
    this.addMouseListener(this);
    this.addMouseMotionListener(this);
    this.validate();
  }

  public void setLocationListener(LocationListener locationListener) {
    this.locationListener = locationListener;
  }

  public void addPiecePanel(PiecePanel piecePanel, Location location) {
    if (location == null) return;
    piecePanel.setBounds(
        this.tablePanel.pointOf(location).x,
        this.tablePanel.pointOf(location).y,
        piecePanel.getPreferredSize().width,
        piecePanel.getPreferredSize().height);
    this.add(piecePanel, Integer.valueOf(1));
  }

  public void removePiecePanels() {
    Arrays.stream(this.getComponents()).filter(c -> c instanceof PiecePanel).forEach(this::remove);
  }

  public void resetLocationStates() {
    this.tablePanel.resetLocationStates();
  }

  public void setLocationState(Location location, LocationState state) {
    this.tablePanel.getLocationPanel(location).setState(state);
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    if (this.movingPiecePanel == null) return;
    e.translatePoint(-movingPiecePanel.getWidth() / 2, -movingPiecePanel.getHeight() / 2);
    this.movingPiecePanel.setLocation(e.getPoint());
  }

  @Override
  public void mouseMoved(MouseEvent e) {}

  @Override
  public void mouseClicked(MouseEvent e) {
    this.locationListener.locationSelected(this.tablePanel.locationOf(e.getPoint()));
  }

  @Override
  public void mousePressed(MouseEvent e) {
    if (this.movingPiecePanel != null) return;
    Location grabbedLocation = this.tablePanel.locationOf(e.getPoint());
    if (!this.locationListener.isLocationDraggable(grabbedLocation)) return;
    this.locationListener.locationGrabbed(grabbedLocation);
    this.movingPiecePanel =
        Arrays.stream(this.getComponents())
            .filter(c -> c instanceof PiecePanel && c.getBounds().contains(e.getPoint()))
            .map(c -> (PiecePanel) c)
            .findFirst()
            .orElse(null);
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (this.movingPiecePanel == null) return;
    this.locationListener.locationDropped(this.tablePanel.locationOf(e.getPoint()));
    this.movingPiecePanel = null;
  }

  @Override
  public void mouseEntered(MouseEvent e) {}

  @Override
  public void mouseExited(MouseEvent e) {}
}
