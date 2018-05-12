package it.polimi.ingsw.LM26.Cards;

public class Effect4Decorator implements ToolCardDecorator {

    private ToolCard toolcard = null;


    public Effect4Decorator(ToolCard toolcard) {
        this.toolcard = toolcard;
    }

    public int getNum(){
        return toolcard.getNum();
    }

    public void printCard(){
        toolcard.printCard();
    }

    public void Play () {





        toolcard.Play();
    }
}
