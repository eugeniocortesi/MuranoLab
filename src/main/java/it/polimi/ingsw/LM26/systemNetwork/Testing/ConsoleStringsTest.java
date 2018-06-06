package it.polimi.ingsw.LM26.systemNetwork.Testing;

import it.polimi.ingsw.LM26.ServerController.ActionEvent;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.systemNetwork.clientConfiguration.DataClientConfiguration;
import it.polimi.ingsw.LM26.systemNetwork.clientConfiguration.DataClientImplementation;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.*;
import it.polimi.ingsw.LM26.view.cli.ConsoleTools;
import org.fusesource.jansi.AnsiConsole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Observer;

import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;

public class ConsoleStringsTest extends ViewInterface {


    static Model model;
    static int id;
    static ObjectivePrivateCard objectivePrivateCard;
    private ClientBase clientBase;
    private ClientView clientView;


    DataClientImplementation dataClientImplementation;
    DataClientConfiguration dataClientConfiguration;

    private ConsoleTools consoleTools = new ConsoleTools();
    //virtualview avrÃ  una coda di actionevent
    private ActionEvent actionEvent = new ActionEvent();
    private ActionEvent event;
    private ArrayList<ActionEvent> events = new ArrayList<ActionEvent>();
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private String s= "";
    private Observer observer;

    public static void main(String[] args) {
        //System.out.println("\u00AF"+"\u2310"+"\u00AC"+"\u2319"+"\u2310");
    }

    public ConsoleStringsTest(ClientBase clientBase) {

        this.clientBase = clientBase;
        dataClientImplementation = new DataClientImplementation();
        dataClientConfiguration = dataClientImplementation.implementation();
        System.out.println("SocketPort " +dataClientConfiguration.getClientSOCKETPORT()+ " ClientRMI " + dataClientConfiguration.getClientRMIPORT()
                + " ServerRMI "+ dataClientConfiguration.getServerRMIPORT());
        //TODO ERASE IT
        //showNetChoise();
    }

    /**
     * first screen of the program: it asks for authentication method
     */
    @Override
    public void showNetChoise(){
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
            //TODO (CHIARA) ricorda di cambiare i costruttori ai clientView e attributo di tipo consolestring
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
        System.out.print(ansi().a("  Benvenuti in\n    ").fg(RED).a("S").fg(YELLOW).a("A").fg(MAGENTA).a("G").fg(GREEN).a("R").fg(BLUE).a("A").fg(YELLOW).a("D").fg(GREEN).a("A\n\n").reset());
        System.out.flush();
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

    @Override
    public void showAlreadyLoggedScreen() {
        AnsiConsole.out().println("E' già  presente un giocatore col tuo nome utente, scegline un altro");
        showLoginScreen();
    }

    @Override
    public void showTooManyUsersScreen() {
        AnsiConsole.out().println("Nella partita corrente ci sono già quattro giocatori");
    }

    @Override
    public void showDisconnectScreen() {
        AnsiConsole.out().println(); //è il messaggio che viene a tutti i connessi
    }

    @Override
    public void showWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck){
        int n=-1;
        //TODO warning: it doesn't print cards
        /*for(WindowPatternCard i : windowDeck){
            consoleTools.printPatternCard(i.getTitle());
        }*/
        System.out.println("scegli una di queste carte mappa con un indice da 1 a 4");
        /*while(n<1 && n>4){
            try{
                n=Integer.parseInt(br.readLine());
                //TODO n is not taken for keyboard...
                System.out.println(n);
            } catch (IOException e){
                e.printStackTrace();
            }
            if(n<1 && n>4) System.out.println("Indice tra 1 e 4!!");
        }*/
        //TODO erase it, delete comment
        n=2;
        clientView.chosenWindowPattern(user, windowDeck.get(n-1));
    }

    @Override
    public void startAcceptor() {

    }

}