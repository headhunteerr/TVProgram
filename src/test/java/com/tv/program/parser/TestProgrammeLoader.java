package com.tv.program.parser;

import com.tv.program.model.Chaine;
import com.tv.program.model.programmes.Programme;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TestProgrammeLoader {

    @Test
    public void parse() {
        InputStream inputStream = TestProgrammeLoader.class.getResourceAsStream("/small_tvguide.xml");
        List<Programme> programmes = new ArrayList<>();
        List<Chaine> chaines = new ArrayList<>();

        ProgrammeLoader.load(inputStream, chaines, programmes);
        System.out.println(String.format("Il y a %d chaines et %d programmes", chaines.size(), programmes.size()));
    }
}
