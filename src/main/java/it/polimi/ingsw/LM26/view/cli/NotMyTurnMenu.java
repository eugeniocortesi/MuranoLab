package it.polimi.ingsw.LM26.view.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NotMyTurnMenu implements PlayerMenu {

    String input;
    ConsoleTools consoleTools= new ConsoleTools();
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void showMenu() throws IOException{
        System.out.println("E' il turno di un altro giocatore, scrivi\n" +
                "'A' per vedere la zona di gioco di un altro giocatore\n" +
                "'T' per vedere la tua zona di gioco\n" +
                "'G' per vedere l'area comune di gioco\n" +
                "'C per vedere una carta");
        try{
            input = br.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }
        if(input.equalsIgnoreCase("A")){
            System.out.println("Scegli tipo");
        }
        else if(input.equalsIgnoreCase("T")){
            consoleTools.showYourplayerZone(ConsoleStrings.id);
        }
        else if(input.equalsIgnoreCase("G")){
            consoleTools.printRoundTrack();
            consoleTools.printDraftPool();
        }
        else if(input.equalsIgnoreCase("C")){

        }
    }


}
