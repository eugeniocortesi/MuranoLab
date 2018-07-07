package it.polimi.ingsw.LM26.view.cli;


import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.observers.serverController.Observable;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.fusesource.jansi.Ansi.ansi;

public class MyTurnMenu extends Observable implements PlayerMenuInt{
    private ConsoleTools consoleTools= new ConsoleTools();
    private ActionEventGenerator ae;
    private ActionEvent actionEvent;
    private ConsoleStrings cs;
    private ClientView cView;

    public MyTurnMenu(ClientView clientView, ConsoleStrings cs) {
        ae= new ActionEventGenerator();
        cView=clientView;
        this.cs = cs;
        register(clientView);
        System.out.println("Registered");
    }

    /**
     * it shows current turn menù options when it's user's turn
     */
    @Override
    public void showMenu(){

        System.out.println(ansi().eraseScreen());
        System.out.println("E' il tuo turno, scrivi\n" +
                "'A' per vedere la zona di gioco di un altro giocatore\n" +
                "'T' per vedere la tua zona di gioco e la riserva\n" +
                "'C' per vedere una carta\n" +
                "'D' per posizionare un dado nella Plancia Vetrata\n" +
                "'U' per usare una carta utensile\n" +
                "'P' per passare il turno\n" +
                "'L' per lasciare la partita\n");
    }

    /**
     * @param input from the user in main menù
     * @return if it is one of the menù chioces
     */
    public boolean evaluateCondition(String input){
        return  !(input.equalsIgnoreCase("A") || input.equalsIgnoreCase("T")
                || input.equalsIgnoreCase("C") || input.equalsIgnoreCase("D")
                ||input.equalsIgnoreCase("U")||input.equalsIgnoreCase("P")
                ||input.equalsIgnoreCase("L"));
    }

    /**
     * it calls diffrerent methods from action event generator in order to create different action events
     * @param input is the checked input from user
     */
    public void handleInput(String input){
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
            actionEvent=ae.askForDiePlacing();
            cs.notifyMessage(actionEvent);
        }
        else if(input.equalsIgnoreCase("U")){
            consoleTools.printToolCardsOnBoard();
            consoleTools.printRoundTrack();
            consoleTools.printDraftPool();
            actionEvent=ae.askToolCard();
            cs.notifyMessage(actionEvent);

        }
        else if(input.equalsIgnoreCase("P")){
            actionEvent=ae.loseTurn();
            cs.notifyMessage(actionEvent);
        }
        else if(input.equalsIgnoreCase("L")){
            if(ae.disconnectConfirm()){
                cView.disconnect();
            }
            else {
                actionEvent=ae.askForMenu(true);
                cs.notifyMessage(actionEvent);
            }
        }
        if(input.equalsIgnoreCase("A")||input.equalsIgnoreCase("T")||input.equalsIgnoreCase("C")){
            actionEvent=ae.askForMenu(true);
            cs.notifyMessage(actionEvent);
        }
    }

}
