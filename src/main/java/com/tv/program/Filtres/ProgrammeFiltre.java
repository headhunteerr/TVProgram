package com.tv.program.Filtres;

import com.tv.program.model.Chaine;
import com.tv.program.model.Programme;

import java.util.ArrayList;
import java.util.List;

public class ProgrammeFiltre {

    public static  List<Programme> filtreChaine (List<Programme> ListeProgramme, Chaine Chaine){
        List<Programme> ProgrammesFiltres = new ArrayList<>();

        for (Programme UnProgramme : ListeProgramme)
        {
            if (UnProgramme.getChaine() == Chaine){

                ProgrammesFiltres.add(UnProgramme);
            }
        }
        return ProgrammesFiltres;
    }

}
