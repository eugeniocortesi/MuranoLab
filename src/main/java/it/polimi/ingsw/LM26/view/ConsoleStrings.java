package it.polimi.ingsw.LM26.view;

import it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator.MoveTwoDice4;
import it.polimi.ingsw.LM26.model.PlayArea.Color;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import org.fusesource.jansi.AnsiConsole;

import java.io.Console;

public class ConsoleStrings implements ViewInt {


    public static void main(String[] args){
        AnsiConsole.systemInstall();
        Console console = System.console();
        Color c = Color.ANSI_YELLOW;
        Die d = new Die(c);
        d.roll();
        AnsiConsole.out().println("helo wrld");
        AnsiConsole.system_out.print("Test");
        AnsiConsole.out.print("Test2");
        System.out.flush();
        //console.printf("Hello World!");
       // String escape = d.getColor().escape();
        //String outputD = escape+"["+d.getFace()+"]" + Color.RESET;
        //console.printf(outputD);


    }
    public void test(){

    }

    @Override
    public void showLoggedScreen() {

    }

    @Override
    public void start() {

    }

    @Override
    public void showLoginScreen() {

    }

    @Override
    public void showAlreadyLoggedScreen() {

    }

    @Override
    public void showTooManyUsersScreen() {

    }

    @Override
    public void showDisconnectScreen() {

    }

    @Override
    public void showAddedPlayer() {

    }

    @Override
    public void showTurnInitialPhase() {

    }

    @Override
    public void showPlaceDie() {

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
