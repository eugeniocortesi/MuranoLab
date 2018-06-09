package it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator;

import it.polimi.ingsw.LM26.controller.PlaceDie;
import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class DrawOneMoreDie8 implements ToolCardDecorator {

    private ToolCard toolcard = null;
    private boolean needPlacement=false;
    private boolean currentPlacement=false;


    public DrawOneMoreDie8(ToolCard toolcard) {
        this.toolcard = toolcard;
    }

    public int getNum(){
        return toolcard.getNum();
    }

    public void printCard(){
        toolcard.printCard();
    }

    public int getToken(){
        return toolcard.getToken();
    }

    public void setOneToken(PlayerZone player){}

    public void setTwoToken(PlayerZone player){}

    @Override
    public boolean isInUse() {
        return false;
    }

    @Override
    public void setInUse(boolean inUse) {

    }


    public boolean play(Box fromBox, Box toBox, int player){return false;}
    public boolean play(Box fromBox1, Box toBox1, Box fromBox2, Box toBox2, int player){return false;}
    public boolean play(DieInt dieFromDraft, DieInt dieFromRoundTrack){return false;}
    public boolean play( DieInt dieFromDraft, String inDeCrement){return false;}
    public boolean play(DieInt dieFromDraft, int pl){return false;}
    public boolean play (DieInt die, Box toBox, int pl){return false;}

    public boolean play ( int pl) {


        Model model = singletonModel();
        PlayerZone player = model.getPlayerList().get(pl);


        if(player.getActionHistory().isFirstTurn()  &&  !player.getActionHistory().isSecondTurn()) {

            //dopo questo primo turno (di due) il giocatore viene congelato: oltre che saltare il secondo tutrno, in questo turno non otrà piu fare azioni.
            //Ciò è corretto perchè sicuramente gli è stato già permesso di compierle:
            //se ha piazzato un dado come prima azione, quinidi sceglie la carta ora gli viene permesso un ulteriore piazzamento con il dado scelto
            //per un totale di due azioni standard piu una extra
            //nel caso in cui non avesse scelto il piazzamento come prima azione (o avesse passato la prima azione), ora scelgie la carta
            //e fa il piazzamento extra per un totale di due azioni,
            // una standard e una extra

            setNeedPlacement(true);
            return true;

                //problemi:
                // come gli faccio fare una tera mossa?? faccio ritornare false
                //come gli faccio saltare il secondo turno???
                // non deve poter scegliere questa carta come prima azione

        }
        else
                System.out.println("you can't use this card in second turn ");

        return false;

    }


    public boolean isNeedPlacement() {
        return needPlacement;
    }

    public void setNeedPlacement(boolean needPlacement) {
        this.needPlacement = needPlacement;
    }

    public boolean isCurrentPlacement() {
        return currentPlacement;
    }

    public void setCurrentPlacement(boolean currentPlacement) {
        this.currentPlacement = currentPlacement;
    }
}
