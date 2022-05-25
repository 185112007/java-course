package edu.javacourse.sample.gui2;

import javax.swing.*;
import java.awt.*;

public class OvalComponent extends JComponent {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawOval(5,5,getWidth()-10,getHeight()-10);
    }
}
