package it.polimi.ingsw.LM26.view.cli;

import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ActionEventGenerator {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String input;
    ConsoleTools cTools;
    ToolsActionEventGenerator tceGenerator = new ToolsActionEventGenerator();

    /**
     * it asks for a die to put into the frame board, but the player can also return to the menù
     */
    public ActionEvent askForDiePlacing(){
        Box rc;
        DieInt d=null;
        ActionEvent actionEvent = new ActionEvent();
        d=tceGenerator.askForDie(true);
        actionEvent.setDieFromDraft(d);
        rc=tceGenerator.askForRowCol();
        actionEvent.setToBox1(rc);
        actionEvent.setPlayer(ConsoleTools.id);
        return  actionEvent;
    }

    public ActionEvent loseTurn(){
        ActionEvent ae=new ActionEvent();
        ae.setNoAction(true);
        return ae;
    }


    /**
     *it asks for the current menù screen
     */
    public ActionEvent askForMenu(){
        while(!tceGenerator.askEndMove()){}
        ActionEvent a= new ActionEvent();
        a.setId(10);
        a.setMenu(true);
        return a;
    }

    //controlli roundtrack vuota e frameboard vuota
    public void askToolCard(){
        int n=0;
        ActionEvent actionEvent=null;
        System.out.println("Scegli una Carta Utensile scrivendo il suo numero");
        while(n<1 || n>3){
            try {
                n=Integer.parseInt(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("inserire un intero!");
            }
            if(n<1 || n>3) System.out.println("numeri tra 1 e 3!");
        }
        int id=ConsoleTools.model.getOnBoardCards().getToolCardList().get(n-1).getNum();
        switch (id){
            case 2:
            case 3:
            {actionEvent=this.fromToBox1(n-1); break;}
            case 4:
            {actionEvent=this.fromToBox2(n-1); break;}
            case 6:
            case 8:
            case 9:
            {actionEvent=this.dfdToBox1(n-1); break;}
            case 5:
            {actionEvent=this.dfdDieFromRoundtrack(n-1); break;}
            case 1:
            {actionEvent=this.dfdIncrement(n-1); break;}
            case 10:
            case 11:
            {actionEvent=this.dieFromDraftPoolEvent(n-1); break;}
            case 7:
            {actionEvent=this.toolCEvent(n-1); break;}
        }
    }

    public ActionEvent toolCEvent(int tCardPosition){
        ActionEvent acEv = new ActionEvent();
        acEv.setPlayer(ConsoleTools.id);
        acEv.setCard(ConsoleTools.model.getOnBoardCards().getToolCardList().get(tCardPosition));
        return acEv;
    }

    public ActionEvent dieFromDraftPoolEvent(int tCardPos){
        ActionEvent a=toolCEvent(tCardPos);
        System.out.println("Dado dalla Riserva:");
        cTools.showInstructionsForPlacement();
        a.setDieFromDraft(tceGenerator.askForDie(true));
        return a;
    }

    public ActionEvent fromToBox1(int tCardPos){
        Box box;
        ActionEvent a=toolCEvent(tCardPos);
        System.out.println("Cella da cui vuoi prendere il dado:");
        box=tceGenerator.askForRowCol();
        a.setFromBox1(box);
        System.out.println("Cella in cui vuoi mettere il dado:");
        box=tceGenerator.askForRowCol();
        a.setToBox1(box);
        return a;
    }

    public ActionEvent fromToBox2(int tCardPos){
        Box box;
        ActionEvent ae=fromToBox1(tCardPos);
        System.out.println("Cella da cui vuoi prendere il dado:");
        box=tceGenerator.askForRowCol();
        ae.setFromBox2(box);
        System.out.println("Cella in cui vuoi mettere il dado:");
        box=tceGenerator.askForRowCol();
        ae.setToBox2(box);
        return ae;
    }

    public ActionEvent dfdToBox1(int tCardPos){
        Box box;
        ActionEvent ae=dieFromDraftPoolEvent(tCardPos);
        System.out.println("Cella in cui vuoi mettere il dado:");
        box=tceGenerator.askForRowCol();
        ae.setToBox1(box);
        return ae;
    }

    public ActionEvent dfdIncrement(int tCardPos){
        String str;
        ActionEvent ae=dieFromDraftPoolEvent(tCardPos);
        str=tceGenerator.askForIncDec();
        ae.setInDeCrement(str);
        return ae;
    }

    public ActionEvent dfdDieFromRoundtrack(int tCardPos){
        ActionEvent ae=dieFromDraftPoolEvent(tCardPos);
        System.out.println("Dado dal tracciato dei round:");
        ae.setDieFromRoundTrack(tceGenerator.askForDie(false));
        return ae;
    }
}
