package it.polimi.ingsw.LM26.controller;

import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.model.Serialization.Decks;


import static it.polimi.ingsw.LM26.model.Serialization.SingletonDecks.singletonDecks;
import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;


public class Controller implements ControllerInt {

    private Model model;
    public Controller(Model model) {

        this.model = singletonModel();
    }

    public boolean check(Die dieFromDraft, Box toBox, int player){

        PlaceDie placement = new PlaceDie(dieFromDraft, toBox, player);
        if(placement.placeDie()){
            model.getDraftPool().getInDraft().remove(dieFromDraft);
            return true;}
        else return false;
    }

    public boolean check(ToolCard twoThree, Box fromBox, Box toBox, int player){



        if(checkToken(model.getPlayerList().get(player),twoThree))

            if(twoThree.play(fromBox, toBox, player))
                return true;

        return false;
    }
    public boolean check(ToolCard four, Box fromBox1, Box toBox1, Box fromBox2, Box toBox2, int player){

        if(checkToken(model.getPlayerList().get(player),four))

            if(four.play(fromBox1, toBox1, fromBox2, toBox2, player))
                return true;

        return false;
    }
    public boolean check(ToolCard sixEightNine, Die dieFromDraft, Box toBox, int player){

        if(checkToken(model.getPlayerList().get(player),sixEightNine))

            if(sixEightNine.play(dieFromDraft, toBox, player))
                return true;

        return false;
    }
    public boolean check(ToolCard five, Die dieFromDraft, Die dieFromRoundTrack, int player){

        if(checkToken(model.getPlayerList().get(player),five))

            if(five.play(dieFromDraft, dieFromRoundTrack))
                return true;

        return false;
    }
    public boolean check(ToolCard one, Die dieFromDraft, String inDeCrement, int player){

        if(checkToken(model.getPlayerList().get(player),one))

            if(one.play(dieFromDraft, inDeCrement))
                return true;

        return false;
    }
    public boolean check(ToolCard tenEleven, Die dieFromDraft, int player){

        if(checkToken(model.getPlayerList().get(player),tenEleven))

            if(tenEleven.play( dieFromDraft))
                return true;

        return false;
    }
    public boolean check(ToolCard seven,  int player){

        if(checkToken(model.getPlayerList().get(player),seven))

            if(seven.play(player))
                return true;

        return false;
    }


    public boolean check( String noAction, int player){

        return false;
    }

    public boolean checkToken(PlayerZone player, ToolCard toolCard){

        if(toolCard.getToken()>0)
            if(player.getToken().getTokenNumber()>1){
                toolCard.setTwoToken(player);
                return true;}
        else if(player.getToken().getTokenNumber()>0){
        toolCard.setOneToken(player);
        return true;}
        return false;
    }

    public void update(){

    }

    public void close(){

    }

}

