	
package com.tv.program.filtres;

import com.tv.program.ProgrammesCounter;
import com.tv.program.Filtres.ProgrammeFiltre;
import com.tv.program.model.Chaine;
import com.tv.program.model.Duree;
import com.tv.program.model.Personne;
import com.tv.program.model.programmes.Film;
import com.tv.program.model.programmes.Programme;
import com.tv.program.parser.ProgrammeLoader;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
public class TestFiltres {


    //public void test1() {
	 public static void main(String[] args) {
              
        List<Programme> programmes = new ArrayList<>();
        List<Chaine> chaines = new ArrayList<>();
        try (InputStream inputStream = ProgrammesCounter.class.getResourceAsStream("/tvguide.xml")){
            ProgrammeLoader.load(inputStream, chaines, programmes);
        } catch (IOException e) {
            throw new RuntimeException("Erreur", e);
        }

        /*
        TODO ajouter des programmes dans ta liste programmes
        programmes.add(Programme.of(arguments...));
         */
		//programmes.add(Programme.film(chaine1 , "test", "Anglais", "Beau film", 2018, personne, 0426, 0426, d1, "France", "bien", "1080", "5"))
		

        List<Chaine> expectedList = new ArrayList<>();
        /*
        TODO comme tu connais le resultat, tu construit ta liste expectedList qui contiendra le resultat attendu
        programmes.add(Programme.of(arguments...));
        */

        //assertEquals("Les listes ne sont pas egales", expectedList, programmesFiltrees);
        
      DateFormat df = new SimpleDateFormat("dd MM yyyy hh:mm:ss");
      //DateFormat df = new SimpleDateFormat("dd MM yyyy");
        
        java.util.Date date=null;
        
        try
        
        {
        	date= df.parse("10 05 2018 17:00:00");
        	//date= df.parse("08 05 2018");
        
        } catch (ParseException e){
        
        	e.printStackTrace();        
        }
        
        //System.out.println(String.format("Il y a %d chaines et %d programmes", chaines.size(), programmes.size()));
        //System.out.println(ProgrammeFiltre.listeChaine(programmes));
        //System.out.println(ProgrammeFiltre.listeJour(programmes));
        //System.out.println(ProgrammeFiltre.listeProgrammeChaine("France 3", date, programmes));
        System.out.println(ProgrammeFiltre.listeEmissionsDate(programmes, date));
        //System.out.println(ProgrammeFiltre.listeFilmActeur(programmes, "Emmanuel Rigaut"));
        //System.out.println(ProgrammeFiltre.listeTypeProgramme(programmes, "emission"));
        //System.out.println(ProgrammeFiltre.rechercheMotsCles(programmes, "question"));
        
        //System.out.println(programmes);
        
        //ProgrammeFiltre.listeProgrammeChaine("France 3", date, programmes);
       
                
        
	 }

    //public void test2() {

    //}

}
