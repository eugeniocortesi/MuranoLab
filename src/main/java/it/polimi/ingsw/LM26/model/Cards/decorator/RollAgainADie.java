package it.polimi.ingsw.LM26.model.Cards.decorator;

import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

public class RollAgainADie implements ToolCardDecorator {

    private ToolCard toolcard = null;


    public RollAgainADie(ToolCard toolcard) {
        this.toolcard = toolcard;
    }

    public int getNum(){
        return toolcard.getNum();
    }

    public void printCard(){
        toolcard.printCard();
    }

    public void play (PlayerZone player) {

        //Die die=chooseFromTheDraft();
        //Box toBox = askTheBok();
        //die.roll();

        //while ( ! placeDie(player, die, toBox)) {
        //          System.Out.Println("error");
        //          toBox = askTheBok();
        //          }
        // else  inDraft.remove(die);






        //public void placeDie(player player Die die, Box toBox){      //ATTENZIONE: togli il dado dalla riserva va fatto fuori
        // if (player.getPlayerBoard().isEmpty())
        //            while(!(i==0 || i==3 || j==0 || j==4 )){
        //                                 System.out.println("error: primo dado deve andare sul bordo");
        //                                 Box toBox = askTheBok();
        //                               }
        //if (checkColorRestriction(die, toBox) && checkValueRestriction(die, toBox) && checkNearByRestriction(player, die, toBox) ){
        //         toBox.setDie(die);
        //          return true;}
        //return false;
        //}




        //public boolean checkColorRestriction(Die die, Box toBox){
        //  if( toBox.getPatternBox().isValue()) return true;
        //      else if (toBox.getPatternBox().getColor()==die.getColor() ||
        //               toBox.getPatternBox().getColor()==WHITE) return true;
        //return false;
        //}



        //public boolean checkValueRestriction(Die die, Box toBox){
        //if( toBox.getPatternBox().isColor()) return true;
        //      else if (toBox.getPatternBox().getValue()==die.getValue()) return true;
        //return false;
        //}











    }
}
