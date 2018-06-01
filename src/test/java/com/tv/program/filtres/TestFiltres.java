package com.tv.program.filtres;

import com.tv.program.Filtres.ProgrammeFiltre;
import com.tv.program.model.Chaine;
import com.tv.program.model.Duree;
import com.tv.program.model.Emission;
import com.tv.program.model.Programme;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;
public class TestFiltres {

    @Test
    public void test1() {
        List<Programme> programmes = new ArrayList<>();

        /*
        TODO ajouter des programmes dans ta liste programmes
        programmes.add(new Emission(arguments...));
         */
        List<Programme> programmesFiltrees = ProgrammeFiltre.filtreChaine(programmes, null);

        List<Programme> expectedList = new ArrayList<>();
        /*
        TODO comme tu connais le resultat, tu construit ta liste expectedList qui contiendra le resultat attendu
        programmes.add(new Emission(arguments...));
        */

        assertEquals("Les listes ne sont pas égales", expectedList, programmesFiltrees);
    }


    @Test
    public void test2() {

    }

}
