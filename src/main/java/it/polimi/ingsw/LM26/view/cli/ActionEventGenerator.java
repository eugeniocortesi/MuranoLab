package it.polimi.ingsw.LM26.view.cli;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;

import java.util.ArrayList;

public class ActionEventGenerator {
    private ConsoleTools cTools=new ConsoleTools();
    private ToolsActionEventGenerator tceGenerator;
    public static boolean invalidActionEvent=false;

    ActionEventGenerator(){
        tceGenerator= new ToolsActionEventGenerator();
    }

    /**
     * it asks for a die to put into the frame board
     */
    public ActionEvent askForDiePlacing(){
        Box rc;
        DieInt d;
        ActionEvent actionEvent = new ActionEvent();
        d=tceGenerator.askForDie(true);
        actionEvent.setDieFromDraft(d);
        rc=tceGenerator.askForDieFromFrameboard(ConsoleTools.model.getPlayer(ConsoleTools.id).getPlayerBoard().getBoardMatrix(), false);
        actionEvent.setToBox1(rc);
        actionEvent.setPlayer(ConsoleTools.id);
        actionEvent.setId(1);
        return  actionEvent;
    }

    /**
     * it initialise an action event to say that the user pass the turn
     * @return the action event
     */
    public ActionEvent loseTurn(){
        ActionEvent ae=new ActionEvent();
        ae.setNoAction(true);
        ae.setPlayer(ConsoleTools.id);
        ae.setId(11);
        return ae;
    }

    /**
     * @return if the user want to quit
     */
    public boolean disconnectConfirm(){
        String s=tceGenerator.askDisconnection();
        return s.equalsIgnoreCase("S");
    }

    /**
     *it asks for the current menù screen
     */
    public ActionEvent askForMenu(boolean explicitly){
        if(explicitly) {
            tceGenerator.askEndMove();
        }
        ActionEvent a= new ActionEvent();
        a.setId(12);
        a.setPlayer(ConsoleTools.id);
        a.setMenu(true);
        return a;
    }

    /**
     * @return the action event required by the tool card chosen
     */
    public ActionEvent askToolCard(){
        ActionEvent actionEvent=null;
        int n=tceGenerator.askforToolCardOnboard();
        int id=ConsoleTools.model.getOnBoardCards().getToolArrayList().get(n-1);
        switch (id){
            case 2:
            case 3:
            {actionEvent=this.fromToBox1(n-1); break;}
            case 4:
            {actionEvent=this.fromToBox2(n-1, false); break;}
            case 6:
            {actionEvent=this.dieFromDraftPoolEvent(n-1);
            System.out.println("Il dado è stato riposto nella Riserva, accedivi per piazzarlo nella Plancia Vetrata.\n" +
                    "Se non puoi piazzarlo, passa il turno");
            break;}
            case 9:
            {actionEvent=this.addToBox1(this.dieFromDraftPoolEvent(n-1));
            actionEvent.setId(4); break;}
            case 5:
            {actionEvent=this.addDieFromRoundtrack(this.dieFromDraftPoolEvent(n-1));
            actionEvent.setId(5); break;}
            case 1:
            {actionEvent=this.dfdIncrement(n-1); break;}
            case 10: {actionEvent=this.dieFromDraftPoolEvent(n-1); break;}
            case 11:{
                String fs=tceGenerator.askFirstSecondPart();
                if(fs.equalsIgnoreCase("p")){
                    actionEvent=this.dieFromDraftPoolEvent(n-1);
                    System.out.println("Prendi di nuovo la carta 11 per la seconda parte");
                }
                else {
                    actionEvent=this.addToBox1(this.number(n-1));
                } break;}
            case 8:
            case 7:
            {actionEvent=this.toolCEvent(n-1); break;}
            case 12:
            {actionEvent=this.addDieFromRoundtrack(this.fromToBox2(n-1, true)); break;}
        }
        return actionEvent;
    }

    /**
     * @param tCardPosition index of the array of tool cards in on board cards
     * @return an action event with setted player, card id
     */
    private ActionEvent toolCEvent(int tCardPosition){
        ActionEvent acEv = new ActionEvent();
        acEv.setId(8);
        acEv.setPlayer(ConsoleTools.id);
        acEv.setCardID(ConsoleTools.model.getOnBoardCards().getToolArrayList().get(tCardPosition));
        return acEv;
    }

    /**
     * @param tCardPos index of the array of tool cards in on board cards
     * @return an action event with setted player, card id, die from draft pool
     */
    private ActionEvent dieFromDraftPoolEvent(int tCardPos){
        ActionEvent a=toolCEvent(tCardPos);
        a.setId(7);
        System.out.println("Dado dalla Riserva:");
        cTools.showInstructionsForPlacement();
        a.setDieFromDraft(tceGenerator.askForDie(true));
        return a;
    }

    /**
     * @param tCardPos index of the array of tool cards in on board cards
     * @return an action event with setted player, card id, die from frame board, die to put in frame board
     */
    private ActionEvent fromToBox1(int tCardPos){
        Box box;
        ActionEvent a=toolCEvent(tCardPos);
        a.setId(2);
        System.out.println("Cella da cui vuoi prendere il dado:");
        box=tceGenerator.askForDieFromFrameboard(ConsoleTools.model.getPlayer(ConsoleTools.id).getPlayerBoard().getBoardMatrix(), true);
        a.setFromBox1(box);
        System.out.println("Cella in cui vuoi mettere il dado:");
        box=tceGenerator.askForDieFromFrameboard(ConsoleTools.model.getPlayer(ConsoleTools.id).getPlayerBoard().getBoardMatrix(), false);
        a.setToBox1(box);
        return a;
    }

    /**
     * @param tCardPos index of the array of tool cards in on board cards
     * @return an action event with setted player, card id, die from frame board, die to put in frame board (in two ArrayList)
     */
    private ActionEvent fromToBox1Array(int tCardPos){
        Box box;
        ActionEvent a=toolCEvent(tCardPos);
        a.setId(3);
        System.out.println("Cella da cui vuoi prendere il dado:");
        box=tceGenerator.askForDieFromFrameboard(ConsoleTools.model.getPlayer(ConsoleTools.id).getPlayerBoard().getBoardMatrix(), true);
        ArrayList<Box> arrayFrom=new ArrayList<>();
        arrayFrom.add(box);
        a.setFromBoxList(arrayFrom);
        System.out.println("Cella in cui vuoi mettere il dado:");
        box=tceGenerator.askForDieFromFrameboard(ConsoleTools.model.getPlayer(ConsoleTools.id).getPlayerBoard().getBoardMatrix(), false);
        ArrayList<Box> arrayTo=new ArrayList<>();
        arrayTo.add(box);
        a.setToBoxList(arrayTo);
        return a;
    }

    /**
     * @param tCardPos index of the array of tool cards in on board cards
     * @param is12 if the card identifier is 12
     * @return an action event with setted player, card id, second die from frame board, second die to put in frame board (in two ArrayList)
     */
    private ActionEvent fromToBox2(int tCardPos, boolean is12){
        Box box;
        String reply;
        ActionEvent ae=fromToBox1Array(tCardPos);
        ae.setId(3);
        if(is12){
            reply=tceGenerator.askFor1or2();
            ae.setId(10);
        }
        else reply="A";
        if(reply.equalsIgnoreCase("A")){
            System.out.println("Cella da cui vuoi prendere il dado:");
            box=tceGenerator.askForDieFromFrameboard(ConsoleTools.model.getPlayer(ConsoleTools.id).getPlayerBoard().getBoardMatrix(), true);
            ArrayList<Box> arrayFrom= ae.getFromBoxList();
            arrayFrom.add(box);
            ae.setFromBoxList(arrayFrom);
            System.out.println("Cella in cui vuoi mettere il dado:");
            box=tceGenerator.askForDieFromFrameboard(ConsoleTools.model.getPlayer(ConsoleTools.id).getPlayerBoard().getBoardMatrix(), false);
            ArrayList<Box> arrayTo= ae.getFromBoxList();
            arrayTo.add(box);
            ae.setToBoxList(arrayTo);
        }
        return ae;
    }

    /**
     * @param ae action event generated by other methods in this class
     * @return the same action event with die to put in frame board setted
     */
    private ActionEvent addToBox1(ActionEvent ae){
        Box box;
        System.out.println("Cella in cui vuoi mettere il dado:");
        box=tceGenerator.askForDieFromFrameboard(ConsoleTools.model.getPlayer(ConsoleTools.id).getPlayerBoard().getBoardMatrix(), false);
        ae.setToBox1(box);
        return ae;
    }

    /**
     * @param tCardPos index of the array of tool cards in on board cards
     * @return an action event with setted player, card id, die from draft pool, string "increment" or "decrement"
     */
    private ActionEvent dfdIncrement(int tCardPos){
        String str;
        ActionEvent ae=dieFromDraftPoolEvent(tCardPos);
        ae.setId(6);
        str=tceGenerator.askForIncDec();
        ae.setInDeCrement(str);
        return ae;
    }

    /**
     * @param ae action event generated by other methods in this class
     * @return the same action event with die from round track setted
     */
    private ActionEvent addDieFromRoundtrack(ActionEvent ae){
        if(ConsoleTools.model.getRoundTrackInt().getRoundTrackTurnList().size()!=0){
            System.out.println("Dado dal tracciato dei round:");
            ae.setDieFromRoundTrack(tceGenerator.askForDie(false));
        }
        else{System.out.println("Tracciato dei round vuoto, la mossa non sarà ritenuta valida");
            invalidActionEvent=true;
        }
        return ae;
    }

    /**
     * @param tCardPos index of the array of tool cards in on board cards
     * @return an action event with setted player, card id, the number to chose for the new die
     */
    public ActionEvent number(int tCardPos){
        ActionEvent a=toolCEvent(tCardPos);
        a.setId(9);
        int n=tceGenerator.askNumber();
        a.setNumber(n);
        return a;
    }
}
