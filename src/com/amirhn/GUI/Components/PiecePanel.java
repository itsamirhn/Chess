package com.amirhn.GUI.Components;

import javax.swing.*;
import java.awt.*;

public class PiecePanel extends JPanel {

    public static final Dimension Size = new Dimension(100, 100);
    public JLabel label;

    public PiecePanel(PieceImageIcon icon) {
        super(new BorderLayout());
        this.setOpaque(false);
        this.label = new JLabel(icon);
        this.setPreferredSize(Size);
        this.add(this.label, BorderLayout.CENTER);
        this.validate();
    }
}
