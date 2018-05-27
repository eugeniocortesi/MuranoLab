package it.polimi.ingsw.LM26.view;

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
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.fusesource.jansi.Ansi.ansi;

public class ConsoleStrings implements ViewInt {


    Model model;

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
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

    public String faces(int val){
        switch (val){
            case 1:
                return "\u2680";
            case 2:
                return "\u2681";
            case 3:
                return "\u2682";
            case 4:
                return "\u2683";
            case 5:
                return "\u2684";
            case 6:
                return "\u2685";
            default:
                return null;
        }
    }

    public void test(){

    }

    @Override
    public void showLoggedScreen() {
        AnsiConsole.out().println("Utente iscritto con successo");
    }

    @Override
    public void start() {

    }

    @Override
    public void showLoginScreen() {
        AnsiConsole.out().print("Utente: ");
        System.out.flush();
        try{
            String s = br.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }

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


    /**
     * it only shows the frame board withits dice and its  window pattern card
     */
    public void printFrameBoard(PlayerZone pl){
        WindowFramePlayerBoard frame = pl.getPlayerBoard();
        //AnsiConsole.systemInstall();
        String escape= pl.getPlayerBoard().getColor().escape();
        System.out.println(escape+"\u2588\u2588 "+pl.getName().toUpperCase()+" \u2588\u2588"+Color.RESET);
        for(int i=0; i<4; i++){
            for(int j=0; j<5; j++){
                Box box=frame.getBoardMatrix()[i][j];
                if(box.isIsPresent()){
                    System.out.print(box.getDie()+"|");
                    System.out.flush();
                }
               else printPatternBox(box.getPatternBox());
            }
            System.out.println();
        }
        //AnsiConsole.systemUninstall();
    }

    public void printPatternBox(PatternBox p){
        if(p.isColor()){
            String escape=p.getColor().escape();
            System.out.print(escape+"\u25A0"+"|");
            System.out.flush();
        }
        else if(p.isShade()){
            System.out.print(faces(p.getValue())+"|");
            System.out.flush();
        }
        else System.out.print(" |");
    }

    public void printPatternCard(String nameCard) throws IllegalArgumentException{
        WindowPatternCard windowPatternCard=null;
        for (WindowPatternCard i : model.getDecks().getWindowPatternCardDeck()) {
            if (i.getTitle().equals(nameCard)) windowPatternCard = i;
            }
        if(windowPatternCard==null) throw new IllegalArgumentException("Wrong name of window pattern card");
        System.out.println(nameCard.toUpperCase()+": "+windowPatternCard.getToken()+" tokens");
        for(int i=0;i<4;i++){
            for(int j=0;j<5;j++) {
               printPatternBox(windowPatternCard.getWindowPatter()[i][j]);
            }
            System.out.println();
        }
    }

    @Override
    public void showChooseCard() {

    }

    @Override
    public void showTurnEndPhase() {

    }

    @Override
    public void showPoints() {

    }
}
