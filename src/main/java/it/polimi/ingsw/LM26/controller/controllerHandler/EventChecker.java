package it.polimi.ingsw.LM26.controller.controllerHandler;

import it.polimi.ingsw.LM26.controller.PlaceDie;
import it.polimi.ingsw.LM26.model.Cards.ToolCardInt;
import it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator.ChangeDieWithTheBag11;
import it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator.DrawOneMoreDie8;
import it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator.RollAgainADie6;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class EventChecker {

    private Model model;

    public EventChecker(Model model) {

        this.model=model;
    }

    //TODO
    //OGNI CHECK DEVE CHIAMARE IL METODO checkNotSameAction() CHE CONTROLLA CHE NON VENGANO RICEVUTI DUE EVENTI UGUALI PER QUEL GIOCATORE IN QUEL TURNO
    //metti nei player dei boolean per sapere se hanno scelto una carta o piazzato un dado, alla fine del turno settali a false cosi
    //che rifunzionino per il secondo turno di quel giocatore in quel round
    //ATTENZIONE prima di ogni piazzamento controllare che non abbia gia piazzato dadi in quel turno, esempio con una toolcard

    public boolean check(DieInt dieFromDraft, Box toBox, int pl){

        PlayerZone player=model.getPlayerList().get(pl);
        RollAgainADie6 tool6=(RollAgainADie6)model.getDecks().getToolCardDeck().get(5);
        DrawOneMoreDie8 tool8=(DrawOneMoreDie8) model.getDecks().getToolCardDeck().get(7);
        ChangeDieWithTheBag11 tool11=(ChangeDieWithTheBag11) model.getDecks().getToolCardDeck().get(10);

        if(tool6.isNeedPlacement()){
            if(!dieFromDraft.equals(tool6.getDieCard6())){
                System.out.println("must use the rolled die");
                return false;
            }
        }
        if(tool11.isNeedPlacement()){
            if(!dieFromDraft.equals(tool11.getDieCard11())){
                System.out.println("must use the rolled die");
                return false;
            }
        }

        if (player.getActionHistory().isPlacement() || player.getActionHistory().isDieUsed()) {
            System.out.println("action expired");
            return false;
        }

        PlaceDie placement = new PlaceDie(dieFromDraft, toBox, player);

        if (placement.placeDie()) {
            model.getDraftPool().getInDraft().remove(dieFromDraft);
            player.getPlayerBoard().incrementNumDice();
            player.getActionHistory().setDieUsed(true);
            player.getActionHistory().setPlacement(true);

            if(tool6.isNeedPlacement()) {
                tool6.setNeedPlacement(false);
                tool6.removeDie();
            }
            if(tool8.isCurrentPlacement()) {
                tool8.setNeedPlacement(false);
                tool8.setCurrentPlacement(false);
            }
            if(tool11.isNeedPlacement()) {
                tool11.setNeedPlacement(false);
                tool11.removeDie();
                tool11.noFirstPart();
            }

            return true;
        }
        else{
            System.out.println("check not passed");
            return false;
        }
    }

    //ATTENZIONE ALL'ITERNO DI OGNI CARTA NON PERMETTO UN PIAZZAMENTO SE DADO è GIA ALREADY USED

    public boolean check(ToolCardInt twoThree, Box fromBox, Box toBox, int player){

        PlayerZone pl=model.getPlayerList().get(player);
        if ( pl.getActionHistory().isCardUsed()) {
            System.out.println("Action already done ");
            return false;}

        if(checkToken(model.getPlayerList().get(player),twoThree))

            if(twoThree.play(fromBox, toBox, player)){
                pl.getActionHistory().setCardUsed(true);
                return true;}

        return false;
    }
    public boolean check(ToolCardInt four, ArrayList<Box> fromBoxList, ArrayList<Box> toBoxList, int player){

        PlayerZone pl=model.getPlayerList().get(player);
        if ( pl.getActionHistory().isCardUsed() ) return false;

        if(checkToken(model.getPlayerList().get(player),four))

            if(four.play( fromBoxList,toBoxList, player)){
                pl.getActionHistory().setCardUsed(true);
                return true;}

        return false;
    }
    public boolean check(ToolCardInt Nine, DieInt dieFromDraft, Box toBox, int player){
//potrei creare un altro evento es 41 che con solo un paio di coordinate, il check passa l'id dell'evento e il check prova afare il piazzamento controllando
// solo che abbia un dado dispobile e non se ha gia fatto un piazzamento. retun true si che il piazzamento avvenga che no
        PlayerZone pl=model.getPlayerList().get(player);
        if ( pl.getActionHistory().isCardUsed()) return false;

        if(checkToken(model.getPlayerList().get(player),Nine))

            if(Nine.play(dieFromDraft, toBox, player)){
                pl.getActionHistory().setCardUsed(true);
                return true;}

        return false;
    }
    public boolean check(ToolCardInt five, DieInt dieFromDraft, DieInt dieFromRoundTrack, int player){

        PlayerZone pl=model.getPlayerList().get(player);
        if ( pl.getActionHistory().isCardUsed() ) return false;

        if(checkToken(model.getPlayerList().get(player),five))

            if(five.play(dieFromDraft, dieFromRoundTrack)){
                pl.getActionHistory().setCardUsed(true);
                return true; }

        return false;
    }
    public boolean check(ToolCardInt one, DieInt dieFromDraft, String inDeCrement, int player){

        PlayerZone pl=model.getPlayerList().get(player);
        if ( pl.getActionHistory().isCardUsed() ) return false;

        if(checkToken(model.getPlayerList().get(player),one))

            if(one.play(dieFromDraft, inDeCrement)) {
                pl.getActionHistory().setCardUsed(true);
                return true;
            }

        return false;
    }
    public boolean check(ToolCardInt sixTenEleven, DieInt dieFromDraft, int player){

        PlayerZone pl=model.getPlayerList().get(player);

        if ( pl.getActionHistory().isCardUsed() ) return false;

        if(checkToken(model.getPlayerList().get(player),sixTenEleven))

            if(sixTenEleven.play( dieFromDraft,player)){
                pl.getActionHistory().setCardUsed(true);
                return true; }
        return false;
    }

     public boolean check(ToolCardInt eleven, int number, Box toBox, int player){

        PlayerZone pl=model.getPlayerList().get(player);

         if(eleven.play(number, toBox, player)){
             pl.getActionHistory().setCardUsed(true);
             return true; }
         return false;

    }

    public boolean check(ToolCardInt sevenEight,  int player){

        PlayerZone pl=model.getPlayerList().get(player);
        if ( pl.getActionHistory().isCardUsed() ) return false;

        if(checkToken(model.getPlayerList().get(player),sevenEight))

            if(sevenEight.play(player)){
                pl.getActionHistory().setCardUsed(true);
                return true; }
        return false;
    }

    public boolean check(ToolCardInt eleven, DieInt fromRoundTrack, ArrayList<Box> fromBoxList, ArrayList<Box> toBoxList, int player){

        PlayerZone pl=model.getPlayerList().get(player);
        if ( pl.getActionHistory().isCardUsed()) {
            System.out.println("Action already done ");
            return false;}
        if(model.getRoundTrackInt().getRoundTrackTurnList().size()<1)return false;

        if(checkToken(model.getPlayerList().get(player),eleven))

            if(eleven.play(fromRoundTrack, fromBoxList, toBoxList, player)){
                pl.getActionHistory().setCardUsed(true);
                return true;}

        return false;
    }


    public boolean check( String noAction, int player){


        return false;
    }

    public boolean checkToken(PlayerZone player, ToolCardInt toolCard){


        if(toolCard.getToken()>0) {
            if (player.getToken().getTokenNumber() > 1) {
                toolCard.setTwoToken(player);
                return true;
            }
        }
        else if(player.getToken().getTokenNumber()>0){
            toolCard.setOneToken(player);
            return true;
        }
        System.out.println("not enough tokens " + player.getToken().getTokenNumber() + player.getWindowPatternCard().getToken()+ toolCard.getToken());
        return false;
    }
}
