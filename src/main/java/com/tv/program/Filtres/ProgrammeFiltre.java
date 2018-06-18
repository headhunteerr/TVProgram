package com.tv.program.Filtres;

import com.tv.program.model.Chaine;
import com.tv.program.model.Personne;
import com.tv.program.model.programmes.Programme;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class ProgrammeFiltre {

	
	
	public static List<Programme> listeTypeProgramme(List<Programme> ListeProgramme, String typeProgramme){
		List<Programme> ProgrammesFiltres = new ArrayList<>();
		
		for (Programme UnProgramme : ListeProgramme) {
    		if (UnProgramme.getType().contains(typeProgramme)) {
    			ProgrammesFiltres.add(UnProgramme);	
    		}       
    }
		
		return ProgrammesFiltres;
		
	}

    public static List<Chaine> listeChaine(List<Programme> ListeProgramme) {
        boolean AjouterChaine = true;
        Chaine ChaineAAjouter;
        List<Chaine> ToutesLesChaines = new ArrayList<>();

        for (Programme UnProgramme : ListeProgramme) {

            AjouterChaine = true;
            ChaineAAjouter = UnProgramme.getChaine();

            for (Chaine UneChaine : ToutesLesChaines) {
                if ((UneChaine.getNom() == ChaineAAjouter.getNom()) && (UneChaine.getId() == ChaineAAjouter.getId())) {

                    AjouterChaine = false;
                }
            }
            if (AjouterChaine) {
                ToutesLesChaines.add(ChaineAAjouter);
            }
        }

        return ToutesLesChaines;
    }

    public static List<Date> listeJour(List<Programme> ListeProgramme) {
   
    	boolean AjouterDebut = true;
        boolean AjouterFin = true;
        SimpleDateFormat JourAAjouter1;
        SimpleDateFormat JourAAjouter2;
        
        List<Date> TousLesJours = new ArrayList<>();

        for (Programme UnProgramme : ListeProgramme) {

            AjouterDebut = true;
            AjouterFin = true;
            
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            
            Date debut = null;
            Date fin = null;
            
            try
            
            {            
            	debut= df.parse(String.valueOf(UnProgramme.getDateDeDebut().getDay()) + "-" + String.valueOf(UnProgramme.getDateDeDebut().getMonth()+1) + "-" + String.valueOf(UnProgramme.getDateDeDebut().getYear()+1900));
            	fin= df.parse(String.valueOf(UnProgramme.getDateDeFin().getDay()) + "-" + String.valueOf(UnProgramme.getDateDeFin().getMonth()+1) + "-" + String.valueOf(UnProgramme.getDateDeFin().getYear()+1900));
                
            } catch (ParseException e){
            
            	e.fillInStackTrace();
            }
            
           
            for (Date UnJour : TousLesJours) {
                if (UnJour.equals(debut)) {

                	AjouterDebut = false;
                }

                if (UnJour.equals(fin)) {
                    {

                    	AjouterFin = false;
                    }
                }
            }
            
            AjouterFin = (AjouterFin && !debut.equals(fin));
            if (AjouterDebut) {
                TousLesJours.add(debut);
            }
            if (AjouterFin) {
                TousLesJours.add(fin);
            }
		
        }
        return TousLesJours;
    }

    public static List<Programme> listeProgrammeChaine(String Chaine, Date Date, List<Programme> ListeProgramme) {

        List<Programme> ProgrammesFiltres = new ArrayList<>();
                
        for (Programme UnProgramme : ListeProgramme) {
        	Date debut = UnProgramme.getDateDeDebut();
        	Date fin = UnProgramme.getDateDeFin();
        	
        	
            if (UnProgramme.getChaine().getNom().equals(Chaine)) {

            	if (((debut.getYear() == Date.getYear()) && (debut.getMonth() == Date.getMonth()) && (debut.getDate()== Date.getDate()) ) || ((fin.getYear() == Date.getYear()) && (fin.getMonth() == Date.getMonth()) && (fin.getDate() == Date.getDate() ))) {
            		
            		ProgrammesFiltres.add(UnProgramme);
            		
            	}
            }
        }
        return ProgrammesFiltres;
    }

    
    
    /*public static List<Programme> ficheEmission(List<Programme> ListeProgramme, Chaine Chaine) {
        List<Programme> ProgrammesFiltres = new ArrayList<>();

        for (Programme UnProgramme : ListeProgramme) {
            if (UnProgramme.getChaine() == Chaine) {

                ProgrammesFiltres.add(UnProgramme);
            }
        }
        return ProgrammesFiltres;
    }

    
    */
    
    
    public static List<Programme> listeEmissionsDate(List<Programme> ListeProgramme, Date momentDonne, String typeProgramme) {
        List<Programme> ProgrammesFiltres = new ArrayList<>();

        for (Programme UnProgramme : ListeProgramme) {
        		if (UnProgramme.getType().contains(typeProgramme)) {
        			     			
        			if(UnProgramme.getDateDeDebut().before(momentDonne) && UnProgramme.getDateDeFin().after(momentDonne)) {
        				ProgrammesFiltres.add(UnProgramme);
        			}
        			
        		}       
        }
        return ProgrammesFiltres;
    }
    
    public static List<Programme> listeEmissionsDate(List<Programme> ListeProgramme, Date momentDonne) {
        List<Programme> ProgrammesFiltres = new ArrayList<>();

        for (Programme UnProgramme : ListeProgramme) {
        		      			     			
    			if(UnProgramme.getDateDeDebut().before(momentDonne) && UnProgramme.getDateDeFin().after(momentDonne)) {
    				ProgrammesFiltres.add(UnProgramme);
    			}      
        }
        return ProgrammesFiltres;
    }
  
    public static List<Programme> listeFilmActeur(List<Programme> ListeProgramme, String Acteur_Realisateur) {
    	List<Programme> ProgrammeFiltres = new ArrayList<>();
    	List<Personne> PersonneFilm = new ArrayList<>();
    	boolean ajouterFilm;
        
        for (Programme UnProgramme : ListeProgramme) {
        	ajouterFilm = false;
            if (UnProgramme.getType().contains("film")) {
	        	PersonneFilm.addAll(UnProgramme.getCredits());
	        	
	        	for (Personne UnePersonne : PersonneFilm) {
	        		
	        		if((UnePersonne.getNom().equals(Acteur_Realisateur)) && ((UnePersonne.getRole().equals("actor")) || (UnePersonne.getRole().equals("director")))) {
	        			ajouterFilm = true;
	        		}
	        	}
	        	if (ajouterFilm) {
	        		ProgrammeFiltres.add(UnProgramme);
	        	}
	        	PersonneFilm.clear();
            }		
        }
                 
        return ProgrammeFiltres;
    }





/*
    public static List<Personne> listeActeurs(List<Programme> ListeProgramme, Chaine Chaine) {
        List<Personne> PersonneFiltres = new ArrayList<>();

        for (Programme UnProgramme : ListeProgramme) {
            if (UnProgramme.getChaine() == Chaine) {

            	PersonneFiltres.addAll(UnProgramme.getCredits());
            }
        }
        return PersonneFiltres;
    }

    public static List<Personne> nombreEmisionsParJour(List<Programme> ListeProgramme, Chaine Chaine) {
        List<Personne> PersonneFiltres = new ArrayList<>();
        //List<>
        
        for (Programme UnProgramme : ListeProgramme) {
            if (UnProgramme.getChaine() == Chaine) {

            	//PersonneFiltres.add(UnProgramme);
            }
        }
        return PersonneFiltres;
    }*/

    public static List<Programme> rechercheMotsCles(List<Programme> ListeProgramme, String motCle) {
        List<Programme> ProgrammesFiltres = new ArrayList<>();

        for (Programme UnProgramme : ListeProgramme) {
        	if (UnProgramme.getType().contains("emission")) {
        		if (UnProgramme.getDescription() != null) {
		            if (UnProgramme.getDescription().contains(motCle)) {
		                ProgrammesFiltres.add(UnProgramme);
		            }
        		}
        	}
        }
        return ProgrammesFiltres;
    }

    }