package it.polimi.ingsw.LM26.controller.controllerHandler;

import it.polimi.ingsw.LM26.model.Cards.ToolCardInt;
import it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator.ChangeDieWithTheBag11;
import it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator.DrawOneMoreDie8;
import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator.RollAgainADie6;
import it.polimi.ingsw.LM26.model.Model;


public class EventHandler{

    private Model model;

   private EventChecker eventChecker;

   private Boolean result;

   private ActionEvent event;

    public EventHandler(ActionEvent event, Model model) {

        this.model=model;
        eventChecker = new EventChecker(model);
        this.event=event;
        result=handle(eventChecker);

    }

    private boolean handle(EventChecker eventChecker) {

        ToolCardInt toolCard;

        if (event.getId() == 1)
            return(eventChecker.check(event.getDieFromDraft(), event.getToBox1(), event.getPlayer()));

        if (event.getCardID() != -1)
            if (eventChecker.checkCard(event.getCardID())) {
                toolCard = getToolCard();
                if (event.getId() == 2)
                    return (eventChecker.check(toolCard, event.getFromBox1(), event.getToBox1(), event.getPlayer()));
                if (event.getId() == 3)
                    return (eventChecker.check(toolCard, event.getFromBoxList(), event.getToBoxList(), event.getPlayer()));
                if (event.getId() == 4)
                    return(eventChecker.check(toolCard, event.getDieFromDraft(), event.getToBox1(), event.getPlayer()));
                if (event.getId() == 5)
                    return(eventChecker.check(toolCard, event.getDieFromDraft(), event.getDieFromRoundTrack(), event.getPlayer()));
                if (event.getId() == 6)
                    return (eventChecker.check(toolCard, event.getDieFromDraft(), event.getInDeCrement(), event.getPlayer()));
                if (event.getId() == 7)
                    return(eventChecker.check(toolCard, event.getDieFromDraft(), event.getPlayer()));
                if (event.getId() == 8)
                    return(eventChecker.check(toolCard, event.getPlayer()));
                if (event.getId() == 9)
                    return(eventChecker.check(toolCard, event.getNumber(), event.getToBox1(), event.getPlayer()));
                if (event.getId() == 10)
                    return(eventChecker.check(toolCard, event.getDieFromRoundTrack(), event.getFromBoxList(), event.getToBoxList(), event.getPlayer()));
                }
        if (event.getId() == 11) {

            System.out.println("i'll pass ");
            return true;
        }
        if (event.getId() == 12)
            //view.showmenu;
            return false;
        return false;
    }


    public Boolean getResult() {
        return result; }

    public ToolCardInt getToolCard(){

        return model.getOnBoardCards().getToolCardList().get(event.getCardID()-1);
    }
}
