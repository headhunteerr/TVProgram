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

	
	/**
	 * fonction qui retourne tous les programmes du type choisi (Journal, emission, documentaire, ...)
	 * @param ListeProgramme liste de tous les programmes
	 * @param typeProgramme le type voulus sous forme d'une chaine de caract�re
	 * @return ProgrammesFiltres liste de programmes
	 */
	public static List<Programme> listeTypeProgramme(List<Programme> ListeProgramme, String typeProgramme){
		
		List<Programme> ProgrammesFiltres = new ArrayList<>();  //Liste de retour
		
		for (Programme UnProgramme : ListeProgramme) {
			//Pour tous les programmes on si leur type est celui voulu et le cas �ch�ant on l'ajoute dans la liste � retourner
    		if (UnProgramme.getType().contains(typeProgramme)) {
    			ProgrammesFiltres.add(UnProgramme);	
    		}       
    }
		
		return ProgrammesFiltres;
		
	}

	
	/**
	 * fonction qui retourne la liste des chaines disponnibles dans le programme TV
	 * @param ListeProgramme une liste de tous les programmes
	 * @return ToutesLesChaines liste de chaine
	 */
    public static List<Chaine> listeChaine(List<Programme> ListeProgramme) {
        boolean AjouterChaine = true;
        Chaine ChaineAAjouter;
        
        List<Chaine> ToutesLesChaines = new ArrayList<>();  //liste � retourner

        for (Programme UnProgramme : ListeProgramme) {
        	//On r�cup�re la chaine de tous les programme
            AjouterChaine = true;
            ChaineAAjouter = UnProgramme.getChaine();
            
            for (Chaine UneChaine : ToutesLesChaines) {
            	//Si la chaine n'est pas dans la liste de retour on l'y ajoute
                if ((UneChaine.getNom().equals(ChaineAAjouter.getNom()))  && (UneChaine.getId().equals(ChaineAAjouter.getId()))) {

                    AjouterChaine = false;
                }
            }
            if (AjouterChaine) {
                ToutesLesChaines.add(ChaineAAjouter);
            }
        }

        return ToutesLesChaines;
    }

    /**
     * fonction qui retoune la liste des jours proposant au moins un programme
     * @param ListeProgramme liste de tous les programmes
     * @return TousLesJours liste de jours
     */
    public static List<Date> listeJour(List<Programme> ListeProgramme) {
   
    	boolean AjouterDebut = true;
        boolean AjouterFin = true;
        SimpleDateFormat JourAAjouter1;
        SimpleDateFormat JourAAjouter2;
        
        List<Date> TousLesJours = new ArrayList<>(); //Liste � retourner

        for (Programme UnProgramme : ListeProgramme) {

            AjouterDebut = true;
            AjouterFin = true;
            
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy"); // Choix du format de la date (dans ce cas sans l'heure)
            
            Date debut = null;
            Date fin = null;
            
            
            //On retire l'heure pour ne garder que le jour de diffusion
            try
            
            {            
            	debut= df.parse(String.valueOf(UnProgramme.getDateDeDebut().getDay()) + "-" + String.valueOf(UnProgramme.getDateDeDebut().getMonth()+1) + "-" + String.valueOf(UnProgramme.getDateDeDebut().getYear()+1900));
            	fin= df.parse(String.valueOf(UnProgramme.getDateDeFin().getDay()) + "-" + String.valueOf(UnProgramme.getDateDeFin().getMonth()+1) + "-" + String.valueOf(UnProgramme.getDateDeFin().getYear()+1900));
                
            } catch (ParseException e){
            
            	e.fillInStackTrace();
            }
            
           
            for (Date UnJour : TousLesJours) {
            	//On v�rifi si les jours ne sont pas<d�ja dans la liste de retour
                if (UnJour.equals(debut)) {

                	AjouterDebut = false;
                }

                if (UnJour.equals(fin)) {
                    {

                    	AjouterFin = false;
                    }
                }
            }
            
            //Si le programme commence le m�me jour qu'il termine on n'ajoute la date qu'une seule fois
            AjouterFin = (AjouterFin && !debut.equals(fin));
            
            //Ajout dans la lliste des date qui n'y sont pas d�ja
            if (AjouterDebut) {
                TousLesJours.add(debut);
            }
            if (AjouterFin) {
                TousLesJours.add(fin);
            }
		
        }
        return TousLesJours;
    }
    
    /**
     * fonction qui retoune la programmation d'une chaine pour un jour donn�
     * @param ListeProgramme liste de tous les programmes
     * @param date la date voulu
     * @param Chaine le nom de la chaine (chaine de caract�re)
     * @return ProgrammesFiltres une liste de programmes
     */
    public static List<Programme> listeProgrammeChaine(List<Programme> ListeProgramme, Date date, String Chaine) {

        List<Programme> ProgrammesFiltres = new ArrayList<>(); //Liste � retourner
                
        for (Programme UnProgramme : ListeProgramme) {
        	       	
            if (UnProgramme.getChaine().getNom().equals(Chaine)) {
            	//Si je progamme est sur la chaine voule, on r�cup�re la date de d�but et de fin de tous les ptogramme
            	Date debut = UnProgramme.getDateDeDebut();
            	Date fin = UnProgramme.getDateDeFin();

            	if (((debut.getYear() == date.getYear()) && (debut.getMonth() == date.getMonth()) && (debut.getDate()== date.getDate()) ) || ((fin.getYear() == date.getYear()) && (fin.getMonth() == date.getMonth()) && (fin.getDate() == date.getDate() ))) {
            		//Si je jour de diffusion correspond au jour voulu, on ajoute le programme � la liste � retourner
            		ProgrammesFiltres.add(UnProgramme);
            		
            	}
            }
        }
        return ProgrammesFiltres;
    }

        
    /**
     * fonction qui retourne la liste d'un type de programme selectionn� en cours de diffusion � un moment choisis
     * @param ListeProgramme liste de tous les programmes
     * @param momentDonne date avec l'heure
     * @param typeProgramme type du programme (chaine de caract�re)
     * @return ProgrammesFiltres une liste de programmes
     */
    public static List<Programme> listeEmissionsDate(List<Programme> ListeProgramme, Date momentDonne, String typeProgramme) {
        List<Programme> ProgrammesFiltres = new ArrayList<>(); // liste � retourner

        for (Programme UnProgramme : ListeProgramme) {
        		if (UnProgramme.getType().contains(typeProgramme)) {
        			//Si le programme � le bon type et qu'il commence avant et se termine apr�s le moment choisis, on l'ajoute � la liste des programmes en cours de diffusion
        			if(UnProgramme.getDateDeDebut().before(momentDonne) && UnProgramme.getDateDeFin().after(momentDonne)) {
        				ProgrammesFiltres.add(UnProgramme);
        			}
        			
        		}       
        }
        return ProgrammesFiltres;
    }
    
    /**
     * fonction qui retourne la liste des programmes en cours de diffusion � un moment choisis
     * @param ListeProgramme liste de tous les programmes
     * @param momentDonne l'instant voulu (date avec l'heure)
     * @return ProgrammesFiltres une liste de programmes
     */
	public static List<Programme> listeEmissionsDate(List<Programme> ListeProgramme, Date momentDonne) {
        List<Programme> ProgrammesFiltres = new ArrayList<>();//liste � retourner

        for (Programme UnProgramme : ListeProgramme) {
        		      			     			
    			if(UnProgramme.getDateDeDebut().before(momentDonne) && UnProgramme.getDateDeFin().after(momentDonne)) {
    				//Si le programme commence avant et se termine apr�s le moment choisis, on l'ajoute � la liste des programmes en cours de diffusion
        			
    				ProgrammesFiltres.add(UnProgramme);
    			}      
        }
        return ProgrammesFiltres;
    }
  
    /**
     * fonction qui renvois la liste des film dans lesquels une personne est soit acteur soit r�alisateur
     * @param ListeProgramme liste de tous les programmes
     * @param Acteur_Realisateur le nom de la personne (chaine de caract�res)
     * @return ProgrammesFiltres une liste de programmes
     */
    public static List<Programme> listeFilmActeur(List<Programme> ListeProgramme, String Acteur_Realisateur) {
    	List<Programme> ProgrammeFiltres = new ArrayList<>(); //liste � retourer
    	List<Personne> PersonneFilm = new ArrayList<>();
    	boolean ajouterFilm;
        
        for (Programme UnProgramme : ListeProgramme) {
        	ajouterFilm = false;
            if (UnProgramme.getType().contains("film")) {
            	
            	//Pour chaque film, on ajoute toutes les personnes ayant un rapport avec le film dans une liste
	        	PersonneFilm.addAll(UnProgramme.getCredits());
	        	
	        	for (Personne UnePersonne : PersonneFilm) {
	        		//si cette liste contient la personne choisis avec le r�le d'acteur ou de r�alisateur, on ajoute le film � la liste 
	        		if((UnePersonne.getNom().equals(Acteur_Realisateur)) && ((UnePersonne.getRole().equals("actor")) || (UnePersonne.getRole().equals("director")))) {
	        			ajouterFilm = true;
	        		}
	        	}
	        	if (ajouterFilm) {
	        		ProgrammeFiltres.add(UnProgramme);
	        	}
	        	//on vide la liste des personnes car on passe au film suivant
	        	PersonneFilm.clear();
            }		
        }
                 
        return ProgrammeFiltres;
    }


    /**
     * fonction qui retourne la liste des emissions comportant des mots cl�s choisis dans leurs d�scription
     * @param ListeProgramme liste de tous les programmes
     * @param motCle les mots cl�s de la description (chaine de caract�re)
     * @return ProgrammesFiltres une liste de programmes
     */
    public static List<Programme> rechercheMotsCles(List<Programme> ListeProgramme, String motCle) {
        List<Programme> ProgrammesFiltres = new ArrayList<>(); //Liste � retourner

        for (Programme UnProgramme : ListeProgramme) {
        	if (UnProgramme.getType().contains("emission")) {
        		//Pour toutes les emissions
        		if (UnProgramme.getDescription() != null) {
        			//si elles ont une description
		            if (UnProgramme.getDescription().contains(motCle)) {
		            	//Et que la description contient les mots cl�, on ajoute l'emission � la liste
		                ProgrammesFiltres.add(UnProgramme);
		            }
        		}
        	}
        }
        return ProgrammesFiltres;
    }

    }