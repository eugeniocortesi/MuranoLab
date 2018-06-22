package it.polimi.ingsw.LM26.view.cli;


import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.observers.serverController.Observable;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.fusesource.jansi.Ansi.ansi;

public class MyTurnMenu extends Observable implements PlayerMenuInt{
    String input;
    ClientView clientView;
    ConsoleTools consoleTools= new ConsoleTools();
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    ActionEventGenerator ae= new ActionEventGenerator();
    ActionEvent actionEvent;

    public MyTurnMenu(ClientView clientView) {
        this.clientView = clientView;
        register(clientView);
        System.out.println("Registered");
    }

    @Override
    public void showMenu(){
        System.out.println(ansi().eraseScreen());
        System.out.println("E' il tuo turno, scrivi\n" +
                "'A' per vedere la zona di gioco di un altro giocatore\n" +
                "'T' per vedere la tua zona di gioco e la riserva\n" +
                "'C' per vedere una carta\n" +
                "'D' per posizionare un dado nella Plancia Vetrata\n" +
                "'U' per usare una carta utensile\n" +
                "'P' per passare il turno\n");
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
            actionEvent=ae.askForDiePlacing();
            notify(actionEvent);
            //clientView.sendActionEventFromView(actionEvent);
        }
        else if(input.equalsIgnoreCase("U")){
            consoleTools.printToolCardsOnBoard();
            consoleTools.printRoundTrack();
            consoleTools.printDraftPool();
            actionEvent=ae.askToolCard();
            notify(actionEvent);
        }
        else if(input.equalsIgnoreCase("P")){
            actionEvent=ae.loseTurn();
            notify(actionEvent);
            //clientView.sendActionEventFromView(actionEvent);
        }
        if(input.equalsIgnoreCase("D")||input.equalsIgnoreCase("U")||input.equalsIgnoreCase("P")){
            actionEvent=ae.askForMenu(false);
            notify(actionEvent);
        }
        else{
            actionEvent=ae.askForMenu(true);
            notify(actionEvent);
            //clientView.sendActionEventFromView(actionEvent);
        }
    }


}
