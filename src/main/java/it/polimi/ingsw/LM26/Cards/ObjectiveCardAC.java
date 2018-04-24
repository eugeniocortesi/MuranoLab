package it.polimi.ingsw.LM26.Cards;

public abstract class ObjectiveCardAC implements CardInt{
    public String Title;

    public String Content;

    public String getTitle(){
        String Title="ObjectiveCard";
        return Title;
    }

    public String getContent(){
        return "content";

    }
}
