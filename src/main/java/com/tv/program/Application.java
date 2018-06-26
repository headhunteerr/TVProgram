package com.tv.program;

import com.tv.program.Filtres.ProgrammeFiltre;
import com.tv.program.model.Chaine;
import com.tv.program.model.Personne;
import com.tv.program.model.programmes.Programme;
import com.tv.program.parser.ProgrammeLoader;
import com.tv.program.tri.ProgrammeCountDailyList;
import com.tv.program.tri.ProgrammeTrieur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Application{

    private boolean running = true;

    private List<Programme> programmes = new ArrayList<>();
    private List<Chaine> chaines = new ArrayList<>();
    private List<String> commands = new ArrayList<>();

    Application() {
        try (InputStream inputStream = ProgrammesCounter.class.getResourceAsStream("/tvguide.xml")) {
            ProgrammeLoader.load(inputStream, chaines, programmes);
        } catch (IOException e) {
            throw new RuntimeException("Erreur", e);
        }
        commands.add("help");
        commands.add("chaines");
        commands.add("joursProgrammes");
        commands.add("listeProgrammes");
        commands.add("infosEmission");
        commands.add("emissionsAuMoment");
        commands.add("filmsDeAvec");
        commands.add("listeActeursOrdonnee");
        commands.add("nbEmissionsParType");
        commands.add("rechercher");
        commands.add("quit");

        commands = Collections.unmodifiableList(commands);
    }

    public List<Programme> getProgrammes(){return this.programmes;}

    public List<Chaine> getChaines(){return this.chaines;}

    public List<String> getCommands(){return this.commands;}

    public boolean isRunning() {
        return running;
    }

    public void start(){
        System.out.println("** Bienvenue dans l'application de programme TV **");
        System.out.println("** Tapez \'help\' pour avoir la liste des commandes **");
        this.running = true;
    }

    public void help(){
        System.out.println("// Liste des commandes:\n" +
                "// chaines : liste toutes les chaines contenues dans le programme TV \n" +
                "// joursProgrammes : liste de tous les jours disposant d'un programme TV \n" +
                "// listeProgrammes -'chaine' -jj/mm/aaaa : liste des programmes sur la chaine 'chaine' a la date choisie \n" +
                "// infosEmission /'emission' : affiche les informations sur l'emission donnee en parametre\n" +
                "// emissionsAuMoment -jj/mm/aaaa hh:mm : liste les emissions diffusees au moment donne en parametre\n" +
                "// filmsDeAvec /'nom' : liste les films d'un acteur ou d'un realisateur\n" +
                "// acteursParApparition : liste les ateurs ordonnes par leur nombre d'apparition,s dans des films\n" +
                "// nbEmissionsParType -jj/mm/aaaa -jj/mm/aaaa : affiche le nombre d'emissions de chaque type par jour et sur la periode\n" +
                "// rechercher /'motCle1' 'motCle2'... : affiche la liste de programmes qui contiennent les mots cles dans leurs descriptions\n" +
                "// quit : quitter l'application \n"

        );

    }

    public void printChannels(){
        for (Chaine chaine:chaines){
            System.out.println(chaine.toString());
        }
    }

    public void printDaysWithPrograms(){
        List<Date> jours = ProgrammeFiltre.listeJour(programmes);
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.FRANCE);
        for (Date jour:jours){
            System.out.println(df.format(jour));
        }
    }

    public void printChannelProgram(String command){
        String[] commandTab = command.split("-");
        for(int i = 0; i <commandTab.length; i++){
            commandTab[i] = commandTab[i].trim();
        }
        String chaine = commandTab[1];

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getDefault());
        Date date = new Date();
        try {
           date = sdf.parse(commandTab[2]);
        }
        catch (ParseException e){
            System.out.println("** Date incorrecte! **");
        }
        List<Programme> programmes = ProgrammeFiltre.listeProgrammeChaine(this.getProgrammes(), date, chaine);
        for (Programme p : programmes) {
            System.out.println(p.toString());
        }
    }

    public void printBrodcastInfo(String command){
        String[] commandTab = command.split("/");

        for (Programme p : this.programmes){
            if (p.getTitre().equals(commandTab[1])){
                System.out.println(p.toString());
                break;
            }
        }
    }

    public void printCurrentBroadcasts(String command){
        String[] commandTab = command.split("-");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        sdf.setTimeZone(TimeZone.getDefault());
        Date date = new Date();
        try {
            date = sdf.parse(commandTab[1]);
        }
        catch (ParseException e){
            System.out.println("** Date incorrecte! **");
        }
        List<Programme> programmes = ProgrammeFiltre.listeEmissionsDate(this.programmes,date);
        for (Programme p : programmes){
            System.out.println(p.toString());
        }
    }

    public void printMoviesPerson(String command){
        String[] commandTab = command.split("/");
        System.out.println(commandTab[1]);

        List<Programme> programmes = ProgrammeFiltre.listeFilmActeur(this.programmes,commandTab[1]);
        for (Programme p : programmes){
            System.out.println(p.toString());
        }
    }

    public void acteursApparition(){
        for(Map.Entry<Personne, Integer> map: ProgrammeTrieur.acteursParApparition(this.programmes)){
            System.out.println(map.getKey().getNom());
        }
    }

    public void nbEmissionParType(String command){
        String[] commandTab = command.split("-");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getDefault());
        Date dateDebut = new Date();
        try {
            dateDebut = sdf.parse(commandTab[1]);
        }
        catch (ParseException e){
            System.out.println("** Date incorrecte! **");
        }
        Date dateFin = new Date();
        try {
            dateFin = sdf.parse(commandTab[2]);
        }
        catch (ParseException e){
            System.out.println("** Date incorrecte! **");
        }

        ProgrammeCountDailyList o = ProgrammeTrieur.emissionByPeriode(this.programmes,dateDebut,dateFin);
        System.out.println(o.toString());
    }

    public void search(String command){
        String[] commandTab = command.split("/");
        String[] keyWords = commandTab[1].split(" ");

        List<Programme> programmes = new ArrayList<>();
        for(String kw : keyWords){
            programmes.addAll(ProgrammeFiltre.rechercheMotsCles(this.programmes,kw));
        }
        for(Programme p : programmes){
            System.out.println(p.toString());
        }
    }


    public void stop(){
        this.running = false;
    }


    public static void main(String[] args) {
        Application a = new Application();
        a.start();

        while(a.isRunning()){
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String commandString ="itialization";
            try{
                commandString = br.readLine();
            }
            catch (Exception e){
                System.err.println("Not a string");
            }
            int command = a.getCommands().indexOf(commandString.split(" ")[0]);
            if (command!=-1){
                switch (command){
                    case 0: a.help();break;

                    case 1: a.printChannels();break;

                    case 2: a.printDaysWithPrograms();break;

                    case 3: a.printChannelProgram(commandString);break;

                    case 4: a.printBrodcastInfo(commandString);break;

                    case 5: a.printCurrentBroadcasts(commandString);break;

                    case 6: a.printMoviesPerson(commandString);break;

                    case 7: a.acteursApparition();break;

                    case 8: a.nbEmissionParType(commandString);break;

                    case 9: a.search(commandString);break;

                    case 10: a.stop();break;
                }
            }
            else{
                System.out.println("** Commande invalide! Tapez \'help\' pour obtenir la liste des commandes **");
            }
        }
    }
}
