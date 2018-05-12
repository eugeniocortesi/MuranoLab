package it.polimi.ingsw.LM26.Cards;

public class Effect1Decorator implements ToolCardDecorator {

    private ToolCard toolcard = null;


    public Effect1Decorator(ToolCard toolcard) {
        this.toolcard = toolcard;
    }

    public int getNum(){
        return toolcard.getNum();
    }

    public void printCard(){
        toolcard.printCard();
    }

    public void Play () {

        System.out.println("eseguitoooo");
        toolcard.Play();


    }
}
