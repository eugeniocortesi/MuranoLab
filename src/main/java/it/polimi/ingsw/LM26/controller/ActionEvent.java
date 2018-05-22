package it.polimi.ingsw.LM26.controller;

import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;

public class ActionEvent {

    private int id;
    private int player;
    private Box fromBox1;
    private Box toBox1;
    private Box fromBox2;
    private Box toBox2;
    private ToolCard card;
    private Die dieFromDraft;
    private Die dieFromRoundTrack;
    private String inDeCrement;

    public int getId() {
        return id;
    }

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
