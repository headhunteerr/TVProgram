package com.tv.program;

import com.tv.program.model.Chaine;
import com.tv.program.model.programmes.Programme;
import com.tv.program.parser.ProgrammeLoader;

import com.tv.program.windows.FenetreChaines;
import com.tv.program.windows.FenetreListeJours;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Application extends Frame{

    private List<Programme> programmes = new ArrayList<>();
    private List<Chaine> chaines = new ArrayList<>();

    Application(){
        try (InputStream inputStream = ProgrammesCounter.class.getResourceAsStream("/tvguide.xml")){
            ProgrammeLoader.load(inputStream, chaines, programmes);
        } catch (IOException e) {
            throw new RuntimeException("Erreur", e);
        }

        setSize(400,200);
        this.setTitle("Consultation des programmes");
        this.setLocation((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-this.getWidth()/2,(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2-this.getHeight()/2);
        this.setBackground(Color.gray);
        setLayout(null);
        setVisible(true);

        Button listeChaines = new Button("Liste des chaines");
        listeChaines.setSize(120,30);
        listeChaines.setLocation(this.getWidth()/2-listeChaines.getWidth()/2,listeChaines.getHeight());
        listeChaines.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FenetreChaines fenetreChaines = new FenetreChaines(chaines);
                fenetreChaines.addWindowListener(new WindowListener() {
                    @Override
                    public void windowOpened(WindowEvent e) {

                    }

                    @Override
                    public void windowClosing(WindowEvent e) {
                        fenetreChaines.dispose();
                    }

                    @Override
                    public void windowClosed(WindowEvent e) {

                    }

                    @Override
                    public void windowIconified(WindowEvent e) {

                    }

                    @Override
                    public void windowDeiconified(WindowEvent e) {

                    }

                    @Override
                    public void windowActivated(WindowEvent e) {

                    }

                    @Override
                    public void windowDeactivated(WindowEvent e) {

                    }
                });
            }
        });
        add(listeChaines);

        Button listeJours = new Button("Liste des jours disposant de programmes");
        listeJours.setSize(250,30);
        listeJours.setLocation(this.getWidth()/2-listeJours.getWidth()/2,listeChaines.getHeight()+listeJours.getHeight());
        listeJours.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FenetreListeJours fenetreListeJours = new FenetreListeJours(programmes);
                fenetreListeJours.addWindowListener(new WindowListener() {
                    @Override
                    public void windowOpened(WindowEvent e) {

                    }

                    @Override
                    public void windowClosing(WindowEvent e) {
                        fenetreListeJours.dispose();
                    }

                    @Override
                    public void windowClosed(WindowEvent e) {

                    }

                    @Override
                    public void windowIconified(WindowEvent e) {

                    }

                    @Override
                    public void windowDeiconified(WindowEvent e) {

                    }

                    @Override
                    public void windowActivated(WindowEvent e) {

                    }

                    @Override
                    public void windowDeactivated(WindowEvent e) {

                    }
                });
            }
        });
        add(listeJours);

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    public List<Programme> getProgrammes(){return this.programmes;}

    public List<Chaine> getChaines(){return this.chaines;}

    public static void main(String[] args) {
        Application a = new Application();
    }
}
