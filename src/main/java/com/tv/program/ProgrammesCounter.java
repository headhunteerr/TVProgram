package com.tv.program;

import com.tv.program.model.Chaine;
import com.tv.program.model.programmes.Programme;
import com.tv.program.parser.ProgrammeLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ProgrammesCounter {
    public static void main(String[] args) {

        List<Programme> programmes = new ArrayList<>();
        List<Chaine> chaines = new ArrayList<>();
        try (InputStream inputStream = ProgrammesCounter.class.getResourceAsStream("/tvguide.xml")){
            ProgrammeLoader.load(inputStream, chaines, programmes);
        } catch (IOException e) {
            throw new RuntimeException("Erreur", e);
        }

        System.out.println(String.format("Il y a %d chaines et %d programmes", chaines.size(), programmes.size()));
    }
    /**
     * TYPES IGNORÉES:
     *
     * talk-show
     * loterie
     * fin
     * divertissement-humour
     * jeunesse : dessin animé jeunesse
     * jeunesse : dessin animé manga
     * météo
     * débat parlementaire
     * fitness
     * humour
     * cérémonie
     * cirque
     * jeu
     * débat
     * one man show
     * hippisme
     * jeunesse : dessin animé
     * programme indéterminé
     * pièce de théâtre
     * divertissement
     * variétés
     * téléréalité
     * autre
     *
     */
}
