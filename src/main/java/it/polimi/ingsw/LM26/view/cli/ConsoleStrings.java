package it.polimi.ingsw.LM26.view.cli;

import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.observers.serverController.ActionEventWindow;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerState;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.observers.modelView.ObservableSimple;
import it.polimi.ingsw.LM26.observers.serverController.Observable;
import it.polimi.ingsw.LM26.systemNetwork.netConfiguration.DataClientConfiguration;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.*;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.clientRMI.ClientViewRMI;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.clientSocket.ClientViewSocket;
import org.fusesource.jansi.AnsiConsole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Observer;
import java.util.concurrent.CountDownLatch;

import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;

public class ConsoleStrings extends ViewInterface {



    private ClientInt clientBase;
    private ClientView clientView;

    DataClientConfiguration dataClientConfiguration;

    private ConsoleTools consoleTools = new ConsoleTools();
    private PlayerMenuInt playerMenu;
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private String s= "";
    private InputLoop inputLoop;
    private ActionEventGenerator aeGenerator;
    private CountDownLatch countDownLatch;

    public static void main(String[] args) {
        //System.out.println("\u00AF"+"\u2310"+"\u00AC"+"\u2319"+"\u2310");
        /*Decks deck=singletonDecks();
        ArrayList<WindowPatternCard> wList= new ArrayList<WindowPatternCard>();
        for(int i=0; i<4; i++){
            wList.add( deck.getWindowPatternCardDeck().get(i));
        }
        ConsoleStrings cs;
        cs.showWindowPattern("string", 1, wList);*/
    }

    public ConsoleStrings(ClientInt clientBase) {
        System.setProperty("jansi.passthrough", "true"); //TODO remove later
        AnsiConsole.systemInstall();
        playerMenu=null;
        aeGenerator=new ActionEventGenerator();
        this.clientBase = clientBase;
        dataClientConfiguration = new DataClientConfiguration();
        dataClientConfiguration = dataClientConfiguration.implementation();
        System.out.println("SocketPort " +dataClientConfiguration.getClientSOCKETPORT()
                + " ServerRMI "+ dataClientConfiguration.getServerRMIPORT());
        inputLoop = new InputLoop();
        //showNetChoise();
    }

    /**
     * first screen of the program: it asks for authentication method
     */
    @Override
    public void showNetChoise(){

        initialScreen();
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
        register(clientView);
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

    public void showAlreadyLoggedScreen() {
        AnsiConsole.out().println("E' già  presente un giocatore col tuo nome utente, scegline un altro");
        showLoginScreen();
    }

    @Override
    public void showTooManyUsersScreen() {
        AnsiConsole.out().println("Partita già in corso");
    }

    @Override
    public void showAddedPlayer(String s) { AnsiConsole.out().println("Nuovo giocatore aggiunto: "+s);}


    public void showDisconnectScreen() {
        AnsiConsole.out().println(); //è il messaggio che viene a tutti i connessi
    }

    public void showWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck){
        int n=-1;
        ConsoleTools.setId(id);
        for(WindowPatternCard i : windowDeck){
            consoleTools.printPatternCard(i);
        }
        System.out.println("scegli una di queste carte mappa con un indice da 1 a 4");
        while(n<1 || n>4){
            try{
                n=Integer.parseInt(br.readLine());
            } catch (IOException e){
                e.printStackTrace();
            }
            if(n<1 || n>4) System.out.println("Indice tra 1 e 4!!");
        }
        ActionEventWindow actionEventWindow = new ActionEventWindow(user, windowDeck.get(n-1));
        clientView.chosenWindowPattern(actionEventWindow);
    }

    @Override
    public void startAcceptor(it.polimi.ingsw.LM26.observers.serverController.Observer observer, ObservableSimple model) {
        //Necessary for Server
        throw new UnsupportedOperationException("not supported yet");
    }

    @Override
    public void showSetPlayerMenu(String name, PlayerZone player) {
        try{
            countDownLatch.countDown();
        }
        catch(NullPointerException e){ }
        if (player.getPlayerState().equals(PlayerState.BEGINNING)) {
            this.setPlayerMenu(new MyTurnMenu(clientView, this));
        }
        else this.setPlayerMenu(new NotMyTurnMenu(clientView, this));

        if(!inputLoop.isRunning()){
            inputLoop = new InputLoop();
            inputLoop.start();
        }
        else showCurrentMenu(null);
    }

    public void handleInput(String input){
        playerMenu.handleInput(input);
    }

    public boolean evaluateCondition(String input){
        return playerMenu.evaluateCondition(input);
    }

    @Override
    public void showAnswerFromController(String name, String answer) {
        System.out.println(answer);
        try{
            countDownLatch.countDown();
        }
        catch(NullPointerException e){ }
    }

    @Override
    public void showEndGame(String name, int score, String winnner, int scoreWinner) {

    }

    /**
     * this method, currentMenù and the attribute playerMenu make the "Context" class of StatePattern
     * @param playerMenu is the current player's menù
     */
    private void setPlayerMenu(PlayerMenuInt playerMenu) {
        this.playerMenu = playerMenu;
    }

    /**
     * called by controller to show one of the two possible menùs
     */
    @Override
    public void showCurrentMenu(String name){
        System.out.println("Show current menu");
        playerMenu.showMenu();
    }


    @Override
    public void update(Model m) {
        if(m == null)
            System.out.println("Model null");

        System.out.println("playerlist "+ m.getPlayerList());
        for(int i = 0; i<m.getPlayerList().size(); i++){
            System.out.println(m.getPlayerList().get(i)+ "player");
        }
        ConsoleTools.setModel(m);
    }

    @Override
    public void showPrivateCard(String name, ObjectivePrivateCard privateCard) {
        ConsoleTools.setPrivateCard(privateCard);
        consoleTools.printPrivateCard();
    }

    public void notifyMessage(ActionEvent ae){
        if(ConsoleTools.model.getPlayer(ConsoleTools.id).getPlayerState()==PlayerState.BEGINNING && !ActionEventGenerator.invalidActionEvent){
            notify(ae);
            System.out.println("notify1");
        }
        else {
            ActionEventGenerator.invalidActionEvent=false;
            notify(aeGenerator.askForMenu(false));
            System.out.println("notify2");
        }
        countDownLatch = new CountDownLatch(1);
        try {
            countDownLatch.await();
        }
        catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    private class InputLoop extends Thread{

        private boolean isRunning = false;
        @Override
        public void run() {
            isRunning = true;
            String playerInput = "";

            do {
                showCurrentMenu(null);
                try {
                    playerInput = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } while (evaluateCondition(playerInput));
            handleInput(playerInput);
            isRunning = false;

        }

        public boolean isRunning(){
            return isRunning;
        }
    }
}



