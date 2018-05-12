package it.polimi.ingsw.LM26.Cards;

public class Effect12Decorator implements ToolCardDecorator {

    private ToolCard toolcard = null;


    public Effect12Decorator(ToolCard toolcard) {
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
