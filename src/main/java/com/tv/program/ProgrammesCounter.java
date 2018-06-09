package com.tv.program;

import com.tv.program.model.Chaine;
import com.tv.program.model.programmes.Programme;
import com.tv.program.parser.ProgrammeLoader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ProgrammesCounter {
    public static void main(String[] args) {
        InputStream inputStream = ProgrammesCounter.class.getResourceAsStream("/tvguide.xml");
        List<Programme> programmes = new ArrayList<>();
        List<Chaine> chaines = new ArrayList<>();

        ProgrammeLoader.load(inputStream, chaines, programmes);
        System.out.println(String.format("Il y a %d chaines et %d programmes", chaines.size(), programmes.size()));
    }
}
