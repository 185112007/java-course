package edu.javacourse.sample.gui3;

import edu.javacourse.sample.gui3.component.OvalComponent;
import edu.javacourse.sample.gui3.component.RectangleComponent;
import edu.javacourse.sample.gui3.component.TriangleComponent;

import javax.swing.*;
import java.awt.*;

/**
 * @author gafur
 */
public class ShapeFrame extends JFrame {
    public ShapeFrame(){
        setLayout(new GridLayout(2,3));

        add(new OvalComponent());
        add(new RectangleComponent());
        add(new TriangleComponent());
        add(new OvalComponent());
        add(new RectangleComponent());
        add(new TriangleComponent());

        setBounds(200,200,450,350);
    }
}
