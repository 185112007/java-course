package edu.javacourse.sample.gui2;

import javax.swing.*;

public class OvalFrame extends JFrame {
    public OvalFrame(){
        OvalComponent oc = new OvalComponent();
        add(oc);
        setBounds(200,200,300,250);
    }
}
