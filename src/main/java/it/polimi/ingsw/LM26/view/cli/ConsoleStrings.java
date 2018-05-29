
package it.polimi.ingsw.LM26.view.cli;

import it.polimi.ingsw.LM26.controller.ActionEvent;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCard;
import it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator.MoveTwoDice4;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.PatternBox;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowFramePlayerBoard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.Color;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerState;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.systemNetwork.clientConfiguration.DataClientConfiguration;
import it.polimi.ingsw.LM26.systemNetwork.clientConfiguration.DataClientImplementation;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.*;
import it.polimi.ingsw.LM26.view.ViewInt;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.io.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;

public class ConsoleStrings extends ViewInterface {


    static Model model;
    private ClientBase clientBase;
    private ClientView clientView;


    DataClientImplementation dataClientImplementation;
    DataClientConfiguration dataClientConfiguration;

    private ConsoleTools consoleTools = new ConsoleTools();
    private int id;
    //virtualview avrÃ  una coda di actionevent
    private ActionEvent actionEvent = new ActionEvent();
    private ActionEvent event;
    private ArrayList<ActionEvent> events = new ArrayList<ActionEvent>();
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private String s= "";
    private Observer observer;

    public static void main(String[] args) {
        //ConsoleStrings consoleStrings =new ConsoleStrings();
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //String s=consoleStrings.initialScreen();
        //String f=consoleStrings.showLogin();
        //System.out.println(f);


       /* AnsiConsole.systemInstall();

        Color c = Color.ANSI_YELLOW;
        Die d = new Die(c);
        d.roll();
        AnsiConsole.out().println("helo wrld");
        AnsiConsole.system_out.print("Test");
        AnsiConsole.out.print("Test2");
        System.out.flush();
        AnsiConsole.systemUninstall();
        Console console = System.console();
        console.printf("Hello World!");
       /* String escape = d.getColor().escape();
        String outputD = escape+"["+d.getFace()+"]" + Color.RESET;
        AnsiConsole.out().println(outputD);
        AnsiConsole.systemUninstall();*/
        //System.out.println("\u00AF"+"\u2310"+"\u00AC"+"\u2319"+"\u2310");
    }

   /* public ConsoleStrings(Model model, int id) {
        this.model=model;
        this.id=id;

    }*/



    /*@Override
    public void notifyObservers() {
        observer.update(this, event);
    }

    public void addObserver(Observer observer){
        this.observer=observer;
    }*/

    public ConsoleStrings(ClientBase clientBase) {

        this.clientBase = clientBase;
        dataClientImplementation = new DataClientImplementation();
        dataClientConfiguration = dataClientImplementation.implementation();
        System.out.println("SocketPort " +dataClientConfiguration.getClientSOCKETPORT()+ " ClientRMI " + dataClientConfiguration.getClientRMIPORT()
                + " ServerRMI "+ dataClientConfiguration.getServerRMIPORT());
        showNetChoise();
    }

    /**
     * first screen of the program: it asks for authentication method
     */
    @Override
    public void showNetChoise() {
        System.out.print(ansi().a("  Benvenuti in\n    ").fg(RED).a("S").fg(YELLOW).a("A").fg(MAGENTA).a("G").fg(GREEN).a("R").fg(BLUE).a("A").fg(YELLOW).a("D").fg(GREEN).a("A\n\n").reset());
        System.out.flush();
        System.out.println("Scegli uno tra i seguenti metodi di connessione:\nSocket: s\nRMI: r");
        while(!(s.equalsIgnoreCase("r") || s.equalsIgnoreCase("s"))){
            try{
                s = br.readLine();
            } catch (IOException e){
                e.printStackTrace();
            }
            if(!(s.equalsIgnoreCase("r") || s.equalsIgnoreCase("s"))) System.out.println("r o s!!");
        }
        if(s.equalsIgnoreCase("R")) {
            //TODO ricorda di cambiare i costruttori ai clientView e attributo di tipo consolestring
            clientView = new ClientViewRMI(this, dataClientConfiguration );
            clientBase.setConnection(true);
        }
        else {
            clientView = new ClientViewSocket(this, dataClientConfiguration);
            clientBase.setConnection(false);
        }
        clientView.connect();
    }


    public void initialScreen(){

    }

    /**
     * it shows login screen and asks for it
     */

    @Override
    public void showLoginScreen() {
        AnsiConsole.out().print("Utente: ");
        System.out.flush();
        try{
            s = br.readLine();
            clientView.login(s);
            clientBase.setUsername(s);
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public void showLoggedScreen() {
        AnsiConsole.out().println("Utente iscritto con successo");
    }

    public void showAlreadyLoggedScreen() {
        AnsiConsole.out().println("E' gia'  presente un giocatore col tuo nome utente, scegline un altro");
        showLoginScreen();
    }

    @Override
    public void showTooManyUsersScreen() {
        AnsiConsole.out().println("Nella partita corrente ci sono giÃ  quattro giocatori");
    }

    @Override
    public void showInitialScreen(String name, int id) {

    }

    @Override
    public void showWindowPattern(int id, ArrayList<WindowPatternCard> windowDeck) {
        int n=-1;
        for(WindowPatternCard i : windowDeck){
            consoleTools.printPatternCard(i.getTitle());
        }
        System.out.println("scegli una di queste carte mappa con un indice da 1 a 4");
        while(n<1 && n>4){
            try{
                n=Integer.parseInt(br.readLine());
            } catch (IOException e){
                e.printStackTrace();
            }
            if(n<1 && n>4) System.out.println("Indice tra 1 e 4!!");
        }
        clientView.chosedWindow(windowDeck.get(n-1));
    }


    public void showDisconnectScreen() {
        AnsiConsole.out().println(); //Ã¨ il messaggio che viene a tutti i connessi
    }



    /**
     * it shows the draft pool, the player zone
     */
    public void showPlacementDice(){
        consoleTools.printDraftPool();
        this.showYourplayerZone();
    }

    public void askForDie(){
        int r, c;
        PlayerZone playing=null;
        for(PlayerZone i : model.getPlayerList()){
            if(i.getPlayerState()== PlayerState.BEGINNING) playing=i;
        }
        boolean dieOk=false;
        char[] string={};
       /* ArrayList<String> diceTranslations = new ArrayList<String>();
        AnsiConsole.out().println("Scegli un dado tra quelli della riserva e inseriscilo nella tua plancia vetrata");
        for(int i=0; i<model.getDraftPool().getInDraft().size(); i++){
            diceTranslations.add(consoleTools.diceTranslate(model.getDraftPool().getInDraft().get(i)));
        }

        while(!dieOk){
            //consoleTools.showInstructionsForPlacement();
            System.out.println("Scegli dado numero: ");
            try{
                s = br.readLine();
            } catch (IOException e){
                e.printStackTrace();
            }
            for(int i=0; i<diceTranslations.size(); i++){
                if(s.equalsIgnoreCase(diceTranslations.get(i))) {
                    dieOk=true;
                    s.getChars(0,s.length()-1, string, 0);
                }
            }
        }*/
        event = new ActionEvent();
        int id=0;
        BufferedReader read;


        System.out.println("insert id 1 o 9");
        read = new BufferedReader(new InputStreamReader(System.in));
        try {
            id = Integer.parseInt(read.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }


        if(id!=9) {

            //prendi indice dado
            //prendi indici i j

            int line = 0;
            System.out.println("insert line");
            read = new BufferedReader(new InputStreamReader(System.in));
            try {
                line = Integer.parseInt(read.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }

            int col = 0;
            System.out.println("insert col");
            read = new BufferedReader(new InputStreamReader(System.in));
            try {
                col = Integer.parseInt(read.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }

            int die = 0;
            System.out.println("insert die");
            read = new BufferedReader(new InputStreamReader(System.in));
            try {
                die = Integer.parseInt(read.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }

            //prendi gli oggetti
            Box[][] board = playing.getPlayerBoard().getBoardMatrix();
            event.setId(id);
            event.setDieFromDraft((Die) model.getDraftPool().getInDraft().get(die - 1));
            event.setToBox1(board[line - 1][col - 1]);
            event.setPlayer(playing.getIDPlayer());
        }
        event.setId(id);
        //notifyObservers();
    }

    /**
     * it shows the frame board updated at the end of the current player's turn
     */
    public void showYourplayerZone() {
        /*int id=-1;
        for(PlayerZone i : model.getPlayerList()){
            if(i.getPlayerState()==PlayerState.BEGINNING) id=i.getIDPlayer();
        }
        if(id==-1) throw new IllegalArgumentException("no player is BEGINNING his turn");*/
        System.out.println("La tua area di gioco: ");
        consoleTools.printFrameBoard(model.getPlayerList().get(id));
        for(int i=0; i< model.getPlayerList().get(id).getToken().getTokenNumber();i++){
            System.out.print("\u25CB ");
            System.out.flush();
        }
        System.out.println();
        //mettere carta privata
    }

    public void showAnotherPlayer(int id){
        System.out.println("Area di gioco di "+model.getPlayerList().get(id).getName());
        consoleTools.printFrameBoard(model.getPlayerList().get(id));
        System.out.print("Segnalini favore: ");
        System.out.flush();
        for(int i=0; i< model.getPlayerList().get(id).getToken().getTokenNumber();i++){
            System.out.print("\u25CB ");
            System.out.flush();
        }
        System.out.println();
    }
}