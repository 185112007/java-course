package edu.javacourse.sample.gui3.component;

import javax.swing.*;
import java.awt.*;

/**
 * @author gafur
 */
public class OvalComponent extends JComponent {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawOval(5, 5, getWidth() - 10, getHeight() - 10);
    }
}
