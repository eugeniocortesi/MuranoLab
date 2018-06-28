package it.polimi.ingsw.LM26.model.Cards;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowFramePlayerBoard;
import it.polimi.ingsw.LM26.model.PlayArea.Color;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.model.Serialization.Elements.elements;

import static it.polimi.ingsw.LM26.model.Serialization.Elements.elements.*;

public class ObjectivePrivateCard extends CardInt {

    private elements colour= null;

    private boolean inUse= false;

    private PlayerZone player;

    int id;

    public ObjectivePrivateCard(int id, elements colour) {

        this.colour=colour;
        this.id=id;
        this.typeCard="ObjectivePrivateCard";
    }
    public Color getColour() {

        if(colour==BLUE)
            return Color.ANSI_BLUE;

        else if(colour==VIOLET)
            return Color.ANSI_PURPLE;

        else if(colour==RED)
            return Color.ANSI_RED;

        else if(colour==GREEN)
            return Color.ANSI_GREEN;

        else if(colour==YELLOW)
            return Color.ANSI_YELLOW;

        else return null;
    }

    public int checkPoints(WindowFramePlayerBoard board){
        Box[][] b = board.getBoardMatrix();
        int count=0;
        for(int i=0; i<4; i++)
            for(int j=0; j<5; j++)
                if(b[i][j].isIsPresent() && b[i][j].getDie().getColor().equals(this.getColour()))
                    count= count + b[i][j].getDie().getValue();

        return count;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public int getId() {
        return id;
    }

    public PlayerZone getPlayer() { return player; }

    public void setPlayer(PlayerZone player) { this.player = player; }

    public void printCard(){

        System.out.println(getId());
        System.out.println(getColour());
    }


    @Override
    public void rewrite() {

        this.typeCard="ObjectivePrivateCard";
    }
}
