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
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.view.ViewInt;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;

public class ConsoleStrings implements ViewInt {


    static Model model;
    private ConsoleTools consoleTools = new ConsoleTools();


    //virtualview avrà una coda di actionevent
    private ActionEvent actionEvent = new ActionEvent();
    private ArrayList<ActionEvent> events = new ArrayList<ActionEvent>();
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private String s= "";

    public static void main(String[] args) {
            ConsoleStrings consoleStrings =new ConsoleStrings();
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



    /**
     * first screen of the program: it asks for authentication method
     */
    public String initialScreen(){
        System.out.print(ansi().a("  Benvenuti in\n    ").fg(RED).a("S").fg(YELLOW).a("A").fg(MAGENTA).a("G").fg(GREEN).a("R").fg(BLUE).a("A").fg(YELLOW).a("D").fg(GREEN).a("A\n\n").reset());
        System.out.flush();
        System.out.println("Scegli uno tra i seguenti metodi di connessione:\nSocket: s\nRMI: r");
        while(!(s.equalsIgnoreCase("r") || s.equalsIgnoreCase("s"))){
            try{
                s = br.readLine();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return s;
    }

    /**
     * it shows login screen and asks for it
     */

    @Override
    public void showLoginScreen() {
    }

    public String showLogin(){
        AnsiConsole.out().print("Utente: ");
        System.out.flush();
        try{
            s = br.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }
        return s;
    }

    @Override
    public void showLoggedScreen() {
        AnsiConsole.out().println("Utente iscritto con successo");
    }


    @Override
    public void start() {

    }


    @Override
    public void showAlreadyLoggedScreen() {
        AnsiConsole.out().println("È già presente un giocatore col tuo nome utente, scegline un altro");
    }

    @Override
    public void showTooManyUsersScreen() {
        AnsiConsole.out().println("Nella partita corrente ci sono già quattro giocatori");
    }

    @Override
    public void showDisconnectScreen() {
        AnsiConsole.out().println(); //è il messaggio che viene a tutti?
    }

    @Override
    public void showAddedPlayer() {

    }

    @Override
    public void showTurnInitialPhase() {

    }

    @Override
    public void showPlaceDie() {

        AnsiConsole.systemInstall();

        System.out.println( ansi().eraseScreen().bold().a("RISERVA").boldOff());
        for(DieInt d : model.getDraftPool().getInDraft()){
            System.out.print(d+"\n");
        }
        System.out.println();

        AnsiConsole.systemUninstall();
        AnsiConsole.out().println("Scegli un dado tra quelli della riserva e inseriscilo nella tua plancia vetrata");

    }


    @Override
    public void showChooseCard() {

    }



    @Override
    public void showTurnEndPhase() {
    }

    /**
     * it shows the frame board updated at the end of the current player's turn
     */
    public void showTurnEnd(int id) {
        System.out.println("La tua area di gioco: ");
        consoleTools.printFrameBoard(model.getPlayerList().get(id));
        for(int i=0; i< model.getPlayerList().get(id).getToken().getTokenNumber();i++){
            System.out.print("\u25CB ");
            System.out.flush();
        }
        System.out.println();
    }

    public void showOtherPlayer(int id){
        System.out.println("Area di gioco di "+model.getPlayerList().get(id).getName());
        consoleTools.printFrameBoard(model.getPlayerList().get(id));
        for(int i=0; i< model.getPlayerList().get(id).getToken().getTokenNumber();i++){
            System.out.print("\u25CB ");
            System.out.flush();
        }
        System.out.println();
    }

    @Override
    public void showPoints() {

    }
}
