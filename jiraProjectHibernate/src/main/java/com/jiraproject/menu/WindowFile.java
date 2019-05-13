package com.jiraproject.menu;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class WindowFile  extends JFileChooser {
    JFrame jFrame = new JFrame();
    FileNameExtensionFilter filter =new FileNameExtensionFilter("xlsx","xlsx" );
    public int selectionFile(){
        int selection = -1;
        this.setFileSelectionMode(JFileChooser.FILES_ONLY);
        this.setFileFilter(filter);
        selection =this.showOpenDialog(jFrame);
        return selection;
    }
}


