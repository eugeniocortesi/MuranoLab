package it.polimi.ingsw.LM26.view.cli;


import it.polimi.ingsw.LM26.view.cli.PlayerMenuInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.fusesource.jansi.Ansi.ansi;

public class MyTurnMenu implements PlayerMenuInt {
    String input;
    ConsoleTools consoleTools= new ConsoleTools();
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    ActionEventGenerator ae= new ActionEventGenerator();

    @Override
    public void showMenu(){
        System.out.println(ansi().eraseScreen());
        System.out.println("E' il tuo turno, scrivi\n" +
                "'A' per vedere la zona di gioco di un altro giocatore\n" +
                "'T' per vedere la tua zona di gioco e la riserva\n" +
                "'C' per vedere una carta" +
                "'D' per posizionare un dado nella Plancia Vetrata" +
                "'U' per usare una carta utensile" +
                "'P' per passare il turno");
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
            consoleTools.printDraftPool();
        }
        else if(input.equalsIgnoreCase("C")){
            consoleTools.showCards();
        }
        else if (input.equalsIgnoreCase("D")) {
            System.out.println("Prima scegli il dado, poi indica le coordinate della Plancia Vetrata in cui vuoi piazzarlo");
            consoleTools.showYourplayerZone(ConsoleTools.id);
            consoleTools.printDraftPool();
            consoleTools.showInstructionsForPlacement();
            ae.askForDiePlacing();
        }
        else if(input.equalsIgnoreCase("U")){
            consoleTools.printToolCardsOnBoard();
            consoleTools.printRoundTrack();
            consoleTools.printDraftPool();
            ae.askToolCard();
        }
        else if(input.equalsIgnoreCase("P")){
            ae.loseTurn();
        }
        consoleTools.askEndMove();
    }


}
