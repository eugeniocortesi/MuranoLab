package it.polimi.ingsw.LM26.view.cli;

public class NotMyTurnMenu implements PlayerMenu {
    @Override
    public void showMenu() {
        System.out.println("E' il turno di un altro giocatore\n" +
                "");
    }
}
