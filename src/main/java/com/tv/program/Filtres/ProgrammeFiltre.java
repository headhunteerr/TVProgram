package com.tv.program.Filtres;

import com.tv.program.model.Chaine;
import com.tv.program.model.Programme;

import java.util.ArrayList;
import java.util.List;

public class ProgrammeFiltre {

    public List<Programme> FiltreChaine (List<Programme> ListeProgramme, Chaine Chaine){
        List<Programme> BonProgramme = new ArrayList<Programme>;

        for (Programme Unprogramme : ListeProgramme)
        {
            if (Unprogramme.getChaine() == Chaine){

                BonProgramme.add(Unprogramme);
            }
        }
        return BonProgramme;
    }

}
