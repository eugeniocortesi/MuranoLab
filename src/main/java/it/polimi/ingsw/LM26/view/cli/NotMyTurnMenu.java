package it.polimi.ingsw.LM26.view.cli;

import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.observers.serverController.Observable;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.fusesource.jansi.Ansi.ansi;

public class NotMyTurnMenu extends Observable implements PlayerMenuInt {

    private ConsoleTools consoleTools= new ConsoleTools();
    private ClientView clientView;
    private ActionEventGenerator ae;
    private ActionEvent actionEvent;
    private ConsoleStrings cs;

    public NotMyTurnMenu(ClientView clientView, ConsoleStrings cs) {
        ae= new ActionEventGenerator();
        this.clientView = clientView;
        register(clientView);
        System.out.println("Registered");
        this.cs = cs;
    }

    @Override
    public void showMenu(){

        System.out.println(ansi().eraseScreen());
        System.out.println("E' il turno di un altro giocatore, scrivi\n" +
                "'A' per vedere la zona di gioco di un altro giocatore\n" +
                "'T' per vedere la tua zona di gioco\n" +
                "'G' per vedere l'area comune di gioco\n" +
                "'C' per vedere una carta");

    }

    public boolean evaluateCondition(String input){
        return (!(input.equalsIgnoreCase("A") || input.equalsIgnoreCase("T") || input.equalsIgnoreCase("C") || input.equalsIgnoreCase("G")));
    }

    public void handleInput(String input){
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
        actionEvent=ae.askForMenu(true);
        notify(actionEvent);
    }
}
