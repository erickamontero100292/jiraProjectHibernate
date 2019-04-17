package com.jiraproject.menu;

import javax.swing.*;

public class WindowFile  extends JFileChooser {
    JFrame jFrame = new JFrame();
    public int selectionFile(){
        int selection = -1;
        selection =this.showOpenDialog(jFrame);
        return selection;
    }
}


