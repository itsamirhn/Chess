package com.amirhn.GUI.Components;

import com.amirhn.GUI.Listeners.LocationListener;
import com.amirhn.Game.Location;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;
import javax.swing.*;

/** The type Board panel. */
public class BoardPanel extends JLayeredPane implements MouseMotionListener, MouseListener {

  private final TablePanel tablePanel;
  private PiecePanel movingPiecePanel;
  private LocationListener locationListener;

  /**
   * Instantiates a new Board panel.
   *
   * @param rows the rows
   * @param columns the columns
   */
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

  /**
   * Sets location listener.
   *
   * @param locationListener the location listener
   */
  public void setLocationListener(LocationListener locationListener) {
    this.locationListener = locationListener;
  }

  /**
   * Add piece panel.
   *
   * @param piecePanel the piece panel
   * @param location the location
   */
  public void addPiecePanel(PiecePanel piecePanel, Location location) {
    if (location == null) return;
    piecePanel.setBounds(
        this.tablePanel.pointOf(location).x,
        this.tablePanel.pointOf(location).y,
        piecePanel.getPreferredSize().width,
        piecePanel.getPreferredSize().height);
    this.add(piecePanel, Integer.valueOf(1));
  }

  /** Remove piece panels. */
  public void removePiecePanels() {
    Arrays.stream(this.getComponents()).filter(c -> c instanceof PiecePanel).forEach(this::remove);
  }

  /** Reset location states. */
  public void resetLocationStates() {
    this.tablePanel.resetLocationStates();
  }

  /**
   * Sets location state.
   *
   * @param location the location
   * @param state the state
   */
  public void setLocationState(Location location, LocationState state) {
    this.tablePanel.getLocationPanel(location).setState(state);
  }

  /**
   * Mouse dragged.
   *
   * @param e the e
   */
  @Override
  public void mouseDragged(MouseEvent e) {
    if (this.movingPiecePanel == null) return;
    e.translatePoint(-movingPiecePanel.getWidth() / 2, -movingPiecePanel.getHeight() / 2);
    this.movingPiecePanel.setLocation(e.getPoint());
  }

  /**
   * Mouse moved.
   *
   * @param e the e
   */
  @Override
  public void mouseMoved(MouseEvent e) {}

  /**
   * Mouse clicked.
   *
   * @param e the e
   */
  @Override
  public void mouseClicked(MouseEvent e) {
    this.locationListener.locationSelected(this.tablePanel.locationOf(e.getPoint()));
  }

  /**
   * Mouse pressed.
   *
   * @param e the e
   */
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

  /**
   * Mouse released.
   *
   * @param e the e
   */
  @Override
  public void mouseReleased(MouseEvent e) {
    if (this.movingPiecePanel == null) return;
    this.locationListener.locationDropped(this.tablePanel.locationOf(e.getPoint()));
    this.movingPiecePanel = null;
  }

  /**
   * Mouse entered.
   *
   * @param e the e
   */
  @Override
  public void mouseEntered(MouseEvent e) {}

  /**
   * Mouse exited.
   *
   * @param e the e
   */
  @Override
  public void mouseExited(MouseEvent e) {}
}
