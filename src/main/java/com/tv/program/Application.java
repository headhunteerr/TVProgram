package com.tv.program;

import com.tv.program.Filtres.ProgrammeFiltre;
import com.tv.program.model.Chaine;
import com.tv.program.model.programmes.Programme;
import com.tv.program.parser.ProgrammeLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
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
                "// infosEmission -'emission' : affiche les informations sur l'emission donnee en parametre\n" +
                "// emissionsAuMoment jj/mm/aaaa hh:mm : liste les emissions diffusees au moment donne en parametre\n" +
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
        String[] dateString = commandTab[2].split("/");
        int[] dateInts = new int[dateString.length];
        for(int i = 0; i < dateString.length; i++){
            dateInts[i] = Integer.parseInt(dateString[i]);
        }
        if (dateInts[2]>0 && (dateInts[1]>0 && dateInts[1]<=12) && (dateInts[0]>0 && dateInts[0]<=31)) {
            Date date = new Date(dateInts[2]-1900, dateInts[1], dateInts[0]);
            System.out.println(date.toString());
            List<Programme> programmes = ProgrammeFiltre.listeProgrammeChaine(this.getProgrammes(), date, chaine);
            for (Programme p : programmes) {
                p.toString();
            }
        }
        else{
            System.out.println("** Date incorrecte! **");
        }
    }

    public void printBrodcastInfo(String command){
        String[] commandTab = command.split("-");
        for(int i = 0; i <commandTab.length; i++){
            commandTab[i] = commandTab[i].trim();
        }
    }

    public void printCurrentBroadcasts(String command){
        String[] commandTab = command.split(" ");

        String[] dateString = commandTab[1].split("/");
        int[] dateInts = new int[dateString.length];
        for(int i = 0; i < dateString.length; i++){
            dateInts[i] = Integer.parseInt(dateString[i]);
        }

        String[] hourString = commandTab[2].split(":");
        int[] hourInts = new int[hourString.length];
        for (int i = 0; i < hourString.length; i++){
            hourInts[i] = Integer.parseInt(hourString[i]);
        }
        if (dateInts[2]>0 && (dateInts[1]>0 && dateInts[1]<=12) && (dateInts[0]>0 && dateInts[0]<=31) && (hourInts[0]>=0 && hourInts[0]<24) && (hourInts[1]>=0 && hourInts[1]<60)) {
            Date date = new Date(dateInts[2]-1900, dateInts[1], dateInts[0], hourInts[0], hourInts[1]);
            List<Programme> programmes = ProgrammeFiltre.listeEmissionsDate(this.programmes,date);
            for (Programme p : programmes){
                p.toString();
            }
        }
        else{
            System.out.println("** Date incorrecte! **");
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

                    case 6: a.stop();break;
                }
            }
            else{
                System.out.println("** Commande invalide! Tapez \'help\' pour obtenir la liste des commandes **");
            }
        }
    }
}
