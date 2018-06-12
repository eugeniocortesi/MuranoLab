package it.polimi.ingsw.LM26.controller.controllerHandler;

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

    private boolean handle(EventChecker eventChecker){

        if (event.getId()==1)
            if (eventChecker.check(event.getDieFromDraft(), event.getToBox1(), event.getPlayer())) {  System.out.println("checked");model.hasChanged();
                            return true; }
            else return false;
        if (event.getId()==2)
            if(eventChecker.check(event.getCard(), event.getFromBox1(), event.getToBox1(), event.getPlayer())) {   model.hasChanged();    return true;}
            else return false;
        if (event.getId()==3)
            if(eventChecker.check(event.getCard(), event.getFromBoxList(), event.getToBoxList(),event.getPlayer())) { model.hasChanged();     return true;}
            else return false;
        if (event.getId()==4)
            if(eventChecker.check(event.getCard(), event.getDieFromDraft(), event.getToBox1(), event.getPlayer())) {   model.hasChanged();    return true;}
            else return false;
        if (event.getId()==5)
            if(eventChecker.check(event.getCard(), event.getDieFromDraft(), event.getDieFromRoundTrack(), event.getPlayer())) {   model.hasChanged();    return true;}
            else return false;
        if (event.getId()==6)
            if(eventChecker.check(event.getCard(), event.getDieFromDraft(), event.getInDeCrement(), event.getPlayer())) {  model.hasChanged();    return true;}
            else return false;
        if (event.getId()==7)
            if(eventChecker.check(event.getCard(), event.getDieFromDraft(), event.getPlayer())) {   model.hasChanged();    return true;}
            else return false;
        if (event.getId()==8)
            if(eventChecker.check(event.getCard(), event.getPlayer())) { model.hasChanged();     return true;}
            else return false;
        if (event.getId()==9)
            if(eventChecker.check(event.getCard(), event.getNumber(), event.getToBox1(), event.getPlayer())) {   model.hasChanged();    return true;}
            else return false;
        if (event.getId()==10)
            if(eventChecker.check(event.getCard(), event.getDieFromRoundTrack(), event.getFromBoxList(), event.getToBoxList(), event.getPlayer())) {   model.hasChanged();    return true;}
            else return false;
        if (event.getId()==11) {

            System.out.println("i'll pass ");

            RollAgainADie6 tool6=(RollAgainADie6)model.getDecks().getToolCardDeck().get(5);
            if(tool6.isNeedPlacement()) tool6.setNeedPlacement(false);
            DrawOneMoreDie8 tool8=(DrawOneMoreDie8)model.getDecks().getToolCardDeck().get(7);
            if(tool8.isNeedPlacement()) tool6.setNeedPlacement(false);
            if(tool8.isCurrentPlacement()) tool6.setNeedPlacement(false);
            ChangeDieWithTheBag11 tool11=(ChangeDieWithTheBag11)model.getDecks().getToolCardDeck().get(10);
            if(tool11.isNeedPlacement()) tool6.setNeedPlacement(false);

            return true;
        }
        if (event.getId()==12)
            //view.showmenu;
            return false;
        return false;

    }

    public Boolean getResult() {
        return result; }
}
