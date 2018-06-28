package it.polimi.ingsw.LM26.controller.controllerHandler;

import it.polimi.ingsw.LM26.controller.Controller;
import it.polimi.ingsw.LM26.controller.ControllerInt;
import it.polimi.ingsw.LM26.model.Cards.ToolCardInt;
import it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator.ChangeDieWithTheBag11;
import it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator.DrawOneMoreDie8;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator.RollAgainADie6;
import it.polimi.ingsw.LM26.model.Model;
import org.omg.PortableInterceptor.LOCATION_FORWARD;

import java.util.ArrayList;
import java.util.NoSuchElementException;


public class EventHandler{

    private Model model;

   private EventChecker eventChecker;

   private Boolean result;

   private ActionEvent event;

   private ControllerInt controller;

    public EventHandler(ActionEvent event, Model model, ControllerInt controller ) {

        this.model=model;
        this.controller=controller;
        eventChecker = new EventChecker(model);
        this.event=event;
        result=handle(eventChecker);

    }

    private boolean handle(EventChecker eventChecker) {

        ToolCardInt toolCard;
        int pl= event.getPlayer();

        if (event.getId() == 1)
            return(eventChecker.check(getDraftDieCopy(event.getDieFromDraft()), getBoxCopy(event.getToBox1(), pl), pl));

        if (event.getCardID() != -1)
            if (eventChecker.checkCard(event.getCardID())) {
                toolCard = getToolCard();
                if (event.getId() == 2)
                    return (eventChecker.check(toolCard, getBoxCopy(event.getFromBox1(), pl), getBoxCopy(event.getToBox1(), pl), pl));
                if (event.getId() == 3)
                    return (eventChecker.check(toolCard, getBoxListCopy(event.getFromBoxList(),pl), getBoxListCopy(event.getToBoxList(),pl), pl));
                if (event.getId() == 4)
                    return(eventChecker.check(toolCard, getDraftDieCopy(event.getDieFromDraft()), getBoxCopy(event.getToBox1(),pl), pl));
                if (event.getId() == 5)
                    return(eventChecker.check(toolCard, getDraftDieCopy(event.getDieFromDraft()), event.getDieFromRoundTrack(), pl));
                if (event.getId() == 6)
                    return (eventChecker.check(toolCard, getDraftDieCopy(event.getDieFromDraft()), event.getInDeCrement(), pl));
                if (event.getId() == 7)
                    return(eventChecker.check(toolCard, getDraftDieCopy(event.getDieFromDraft()), pl));
                if (event.getId() == 8)
                    return(eventChecker.check(toolCard, pl));
                if (event.getId() == 9)
                    return(eventChecker.check(toolCard, event.getNumber(), getBoxCopy(event.getToBox1(),pl), pl));
                if (event.getId() == 10)
                    return(eventChecker.check(toolCard, event.getDieFromRoundTrack(), getBoxListCopy(event.getFromBoxList(),pl), getBoxListCopy(event.getToBoxList(), pl), pl));
                }
        if (event.getId() == 11) {

            System.out.println("i'll pass ");
            model.getPlayerList().get(pl).getActionHistory().setJump(true);
            return true;
        }
        if (event.getId() == 12) {
            System.out.println("Getted id 12 (action event)");
            controller.getViewGameInterface().showCurrentMenu(model.getPlayerList().get(pl).getName());
            return false;
        }
        return false;
    }


    public Boolean getResult() {
        return result; }

    public ToolCardInt getToolCard(){

       for(int i=0; i<model.getOnBoardCards().getToolCardList().size(); i++)
           if(model.getOnBoardCards().getToolCardList().get(i).getNum()==event.getCardID())
               return model.getOnBoardCards().getToolCardList().get(i);
       return null;

    }

    //TODO IMPLEMENT and change action event
   /*public DieInt getTrackDieCopy(){

    }*/

    public Box getBoxCopy(Box b, int pl)throws NoSuchElementException{
        try {
            return model.getPlayerList().get(pl).getPlayerBoard().getBoardMatrix()[b.getI()][b.getJ()];
        }catch (NoSuchElementException e){}

        return null;
    }

    public DieInt getDraftDieCopy(DieInt die) throws NoSuchElementException{

            for (int i = 0; i < model.getDraftPool().size(); i++)
                if (model.getDraftPool().get(i).getValue() == die.getValue() && model.getDraftPool().get(i).getColor().equals(die.getColor()))
                    return model.getDraftPool().get(i);
            throw new NoSuchElementException();

    }

    public ArrayList<Box> getBoxListCopy(ArrayList<Box> a, int pl)throws NoSuchElementException {

        ArrayList<Box> toReturn = new ArrayList<Box>();

        try {
            Box[][] board = model.getPlayerList().get(pl).getPlayerBoard().getBoardMatrix();

            for (int i = 0; i < a.size(); i++)
                toReturn.add(board[a.get(i).getI()][a.get(i).getJ()]);

        }catch (NoSuchElementException e){}

        return toReturn;

    }
}

