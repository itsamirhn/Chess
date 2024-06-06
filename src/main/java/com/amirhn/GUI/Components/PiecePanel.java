package com.amirhn.GUI.Components;

import com.amirhn.GUI.Constants;
import java.awt.*;
import javax.swing.*;

/**
 * The type Piece panel.
 */
public class PiecePanel extends JPanel {

  /**
   * The constant Size.
   */
public static final Dimension Size = Constants.PieceSize;
  /**
   * The Label.
   */
public JLabel label;

  /**
   * Instantiates a new Piece panel.
   *
   * @param icon the icon
   */
public PiecePanel(PieceImageIcon icon) {
    super(new BorderLayout());
    this.setOpaque(false);
    this.label =
        new JLabel(
            new ImageIcon(
                icon.getImage().getScaledInstance(Size.width, Size.height, Image.SCALE_SMOOTH)));
    this.setPreferredSize(Size);
    this.add(this.label, BorderLayout.CENTER);
    this.validate();
  }
}
