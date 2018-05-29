package it.polimi.ingsw.LM26.controller;

import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;

public class ActionEvent {

    private int ID=1;
    private int player;
    private Box fromBox1=null;
    private Box toBox1=null;
    private Box fromBox2=null;
    private Box toBox2=null;
    private ToolCard card=null;
    private Die dieFromDraft=null;
    private Die dieFromRoundTrack=null;
    private String inDeCrement=null;
    private Boolean noAction=false;


        /*
        event 1: place die. to set: DieFromDraf, ToBox1, Player.
        event 2: use card 2, 3. to set: Card, FromBox1, ToBox1, Player.
        event 3: use card 4. to set: Card, FromBox1, ToBox1, FromBox2, toBox2, Player.
        event 4: use card 6,8,9. to set: Card, DieFromDraft, ToBox1, Player.
        event 5: use card 5. to set: Card, DieFromDraft, DieFromRoundTrack, Player.
        event 6: use card 1. to set: Card, DieFromDraft, InDeCrement, Player.
        event 7: use card 10, 11. to set: Card, DieFromDraft, Player.
        event 8: use card 7. to set: Card, Player.
        event 9: no action.
        */

        //TODO
    // ID=1 di default
    // setta l'impostazione degli in ID in setCard,
    //ID=9 se viene settato no action

    public int getId() { return ID; }

    public void setId(int id) {
        this.ID = id;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public Box getFromBox1() {
        return fromBox1;
    }

    public void setFromBox1(Box fromBox1) {
        this.fromBox1 = fromBox1;
    }

    public Box getToBox1() {
        return toBox1;
    }

    public void setToBox1(Box toBox1) {
        this.toBox1 = toBox1;
    }

    public Box getFromBox2() {
        return fromBox2;
    }

    public void setFromBox2(Box fromBox2) {
        this.fromBox2 = fromBox2;
    }

    public Box getToBox2() {
        return toBox2;
    }

    public void setToBox2(Box toBox2) {
        this.toBox2 = toBox2;
    }

    public ToolCard getCard() {
        return card;
    }

    public void setCard(ToolCard card) {

            if(card.getNum()==1)  ID=6 ;
            if(card.getNum()==2 ||card.getNum()==3 )  ID=2 ;
            if(card.getNum()==4)  ID= 3;
            if(card.getNum()==5)  ID=5 ;
            if(card.getNum()==10 || card.getNum()==11 )  ID= 7;
            if(card.getNum()==7)  ID= 8;
            if(card.getNum()==6 || card.getNum()==8 || card.getNum()==9)  ID= 4;
        this.card = card;
    }

    public Die getDieFromDraft() {
        return dieFromDraft;
    }

    public void setDieFromDraft(Die dieFromDraft) {
        this.dieFromDraft = dieFromDraft;
    }

    public Die getDieFromRoundTrack() {
        return dieFromRoundTrack;
    }

    public void setDieFromRoundTrack(Die dieFromRoundTrack) {
        this.dieFromRoundTrack = dieFromRoundTrack;
    }

    public String getInDeCrement() {
        return inDeCrement;
    }

    public void setInDeCrement(String inDeCrement) {
        this.inDeCrement = inDeCrement;
    }

    public Boolean getNoAction() { return noAction; }

    public void setNoAction(Boolean noAction) {

        this.noAction = noAction;

        if(noAction==false) ID=9;
    }
}
