package it.polimi.ingsw.LM26.view.cli;

import java.io.IOException;

public class MyTurnMenu implements PlayerMenu {
    @Override
    public void showMenu() throws IOException{
      System.out.println("E' il tuo turno");
    }



}
