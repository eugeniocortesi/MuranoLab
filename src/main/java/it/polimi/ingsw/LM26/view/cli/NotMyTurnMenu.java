package it.polimi.ingsw.LM26.view.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.fusesource.jansi.Ansi.ansi;

public class NotMyTurnMenu implements PlayerMenuInt {

    String input;
    ConsoleTools consoleTools= new ConsoleTools();
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void showMenu(){
        System.out.println(ansi().eraseScreen());
        System.out.println("E' il turno di un altro giocatore, scrivi\n" +
                "'A' per vedere la zona di gioco di un altro giocatore\n" +
                "'T' per vedere la tua zona di gioco\n" +
                "'G' per vedere l'area comune di gioco\n" +
                "'C' per vedere una carta");
        try{
            input = br.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(ansi().eraseScreen());
        if(input.equalsIgnoreCase("A")){
            consoleTools.showAnotherPlayer();
        }
        else if(input.equalsIgnoreCase("T")){
            consoleTools.showYourplayerZone(ConsoleTools.id);
        }
        else if(input.equalsIgnoreCase("G")){
            consoleTools.printRoundTrack();
            consoleTools.printDraftPool();
        }
        else if(input.equalsIgnoreCase("C")){
            consoleTools.showCards();
        }
        consoleTools.askEndMove();
    }


}
