package it.polimi.ingsw.LM26.controller;

import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;

public class ActionEvent {

    private int id;
    private int player;
    private Box fromBox1=null;
    private Box toBox1=null;
    private Box fromBox2=null;
    private Box toBox2=null;
    private ToolCard card=null;
    private Die dieFromDraft=null;
    private Die dieFromRoundTrack=null;
    private String inDeCrement=null;


        /*
        event 1: place die. to set: DieFromDraf, ToBox1, Player.
        event 2: use card 2, 3. to set: Card, FromBox1, ToBox1, Player.
        event 3: use card 4. to set: Card, FromBox1, ToBox1, FromBox2, toBox2, Player.
        event 4: use card 6,8,9. to set: Card, DieFromDraft, ToBox1, Player.
        event 5: use card 5. to set: Card, DieFromDraft, DieFromRoundTrack, Player.
        event 6: use card 1. to set: Card, DieFromDraft, InDeCrement, Player.
        event 7: use card 10, 11. to set: Card, DieFromDraft, Player.
        event 8: use card 7. to set: Card, Player.
        */

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
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
}
