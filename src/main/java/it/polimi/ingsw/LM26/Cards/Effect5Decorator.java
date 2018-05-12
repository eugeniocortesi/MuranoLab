package it.polimi.ingsw.LM26.Cards;

public class Effect5Decorator implements ToolCardDecorator {

    private ToolCard toolcard = null;


    public Effect5Decorator(ToolCard toolcard) {
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
