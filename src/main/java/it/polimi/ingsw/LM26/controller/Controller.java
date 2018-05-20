package it.polimi.ingsw.LM26.controller;

import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.ToolCardInt;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.model.Serialization.Decks;
import it.polimi.ingsw.LM26.model.Serialization.SingletonDecks;

import static it.polimi.ingsw.LM26.model.Serialization.SingletonDecks.singletonDecks;


public class Controller implements ControllerInt {

    private Model model;

    private Decks decks=singletonDecks();

    public Controller(Model model) {

        this.model=model;
    }

    public boolean check(Die dieFromDraft, Box toBox, int player){

        PlaceDie placement = new PlaceDie(dieFromDraft, toBox, player);
        if(placement.placeDie()){
            model.getDraftPool().getInDraft().remove(dieFromDraft);
            return true;}
        else return false;
    }

    public boolean check(int toolCard23, Box fromBox, Box toBox, int player){

        ToolCardInt toolcard23 = decks.getToolCardDeck().get(toolCard23);

        if(checkToken(model.getPlayerList().get(player),toolcard23))

            //if(toolCard23.play(fromBox, toBox))
                return true;

        /*
        play(Box fromBox,Box toBox)
        play(Box fromBox1, Box toBox1, Box fromBox2, Box toBox2)
        play(Die dieFromDraft, Box toBox)
        play(Die dieFromDraft, Die dieFromRoundTrack)
        play( Die dieFromDraft, String inDeCrement)
        play(Die dieFromDraft)
        play()
         */

        return false;
    }
    public boolean check(ToolCard four, Box fromBox1, Box toBox1, Box fromBox2, Box toBox2, int player){

        return false;
    }
    public boolean check(ToolCard sixEightNine, Die dieFromDraft, Box toBox, int player){

        return false;
    }
    public boolean check(ToolCard five, Die dieFromDraft, Die dieFromRoundTrack, int player){

        return false;
    }
    public boolean check(ToolCard one, Die dieFromDraft, String inDeCrement, int player){

        return false;
    }
    public boolean check(ToolCard tenEleven, Die dieFromDraft, int player){

        return false;
    }
    public boolean check(ToolCard seven,  int player){

        return false;
    }

    public boolean check( String noAction, int player){

        return false;
    }

    public boolean checkToken(PlayerZone player, ToolCardInt toolCard){

       /* if(toolCard.getToken()>0)
            if(player.getToken().getTokenNumber()>1){
                toolCard.setTwoToken(player);
                return true;}
        else if(player.getToken().getTokenNumber()>0){
        toolCard.setOneToken(player);
        return true;}*/
        return false;
    }

    public void update(){

    }

    public void close(){

    }

}

