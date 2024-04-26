package com.amirhn.GUI.Components;

import com.amirhn.GUI.Constants;

import javax.swing.*;
import java.awt.*;

public class PiecePanel extends JPanel {

    public static final Dimension Size = Constants.PieceSize;
    public JLabel label;

    public PiecePanel(PieceImageIcon icon) {
        super(new BorderLayout());
        this.setOpaque(false);
        this.label = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(Size.width, Size.height, Image.SCALE_SMOOTH)));
        this.setPreferredSize(Size);
        this.add(this.label, BorderLayout.CENTER);
        this.validate();
    }
}
