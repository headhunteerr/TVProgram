package com.tv.program.windows;

import com.tv.program.model.Chaine;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

public class FenetreChaines extends Frame implements WindowListener {

    public static final int buttonHeight = 30;
    public static final int buttonWidth = 80;

    FenetreChaines(List<Chaine> chaineList){
        Panel chainesContainer = new Panel();
        chainesContainer.setSize(4*(buttonWidth+5),((int)((chaineList.size()/4)+1)*(buttonHeight+3)));
        this.setTitle("Consultation des programmes");
        this.setLocation((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-this.getWidth()/2,(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2-this.getHeight()/2);
        this.setBackground(Color.gray);
        setLayout(null);
        setVisible(true);

        for (int i = 0; i<chaineList.size();i++){

        }
    }

    public void windowClosing(WindowEvent e) {
        dispose();
    }

    @Override
    public void windowOpened(WindowEvent e) {}
    @Override
    public void windowClosed(WindowEvent e) {}
    @Override
    public void windowIconified(WindowEvent e) {}
    @Override
    public void windowDeiconified(WindowEvent e) {}
    @Override
    public void windowActivated(WindowEvent e) {}
    @Override
    public void windowDeactivated(WindowEvent e) {}
}
