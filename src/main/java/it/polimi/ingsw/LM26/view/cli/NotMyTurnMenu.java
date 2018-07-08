package it.polimi.ingsw.LM26.view.cli;

import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.observers.serverController.Observable;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientView;

import static org.fusesource.jansi.Ansi.ansi;

public class NotMyTurnMenu extends Observable implements PlayerMenuInt {

    private ConsoleTools consoleTools= new ConsoleTools();
    private ActionEventGenerator ae;
    private ActionEvent actionEvent;
    private ConsoleStrings cs;
    private ClientView clientView;

    public NotMyTurnMenu(ClientView clientView, ConsoleStrings cs) {
        ae= new ActionEventGenerator();
        register(clientView);
        this.cs = cs;
        this.clientView=clientView;
    }

    /**
     * it shows different menù options when it's not user's turn
     */
    @Override
    public void showMenu(){

        System.out.println("E' il turno di un altro giocatore, scrivi\n" +
                "'A' per vedere la zona di gioco di un altro giocatore\n" +
                "'T' per vedere la tua zona di gioco\n" +
                "'G' per vedere l'area comune di gioco\n" +
                "'C' per vedere una carta\n" +
                "'L' per lasciare la partita\n");

    }


    /**
     * @param input from the user in ConsoleStrings class
     * @return if it is one of menù choices
     */
    public boolean evaluateCondition(String input){
        return (!(input.equalsIgnoreCase("A") || input.equalsIgnoreCase("T")
                || input.equalsIgnoreCase("C") || input.equalsIgnoreCase("G")
                ||input.equalsIgnoreCase("L")));
    }


    /**
     * it calls methods from ActionEventGenerator class in order to create different action events
     * @param input the checked input from user
     */
    public void handleInput(String input){
        if(input.equalsIgnoreCase("L")){
            if(ae.disconnectConfirm()){clientView.disconnect();}
            else {
                actionEvent=ae.askForMenu(true);
                cs.notifyMessage(actionEvent);
            }
        }
        else{
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
            cs.notifyMessage(actionEvent);
        }
    }
}
