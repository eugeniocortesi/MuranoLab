package it.polimi.ingsw.LM26.Cards;

import it.polimi.ingsw.LM26.PublicPlayerZone.PlayerZone;

public class Effect9Decorator implements ToolCardDecorator {

    private ToolCard toolcard = null;


    public Effect9Decorator(ToolCard toolcard) {
        this.toolcard = toolcard;
    }

    public int getNum(){
        return toolcard.getNum();
    }

    public void printCard(){
        toolcard.printCard();
    }

    public void play (PlayerZone player) { //piazza il dado in modo che non sia addiacente ad altri


        //Die die1 =chooseFromTheDraft();
        //Box toBox = askTheBok();

        // se è il primo va messo su un bordo, senza altri controlli
        //se non è ul primo piazzalo non addiacente ad altri






    }
}
