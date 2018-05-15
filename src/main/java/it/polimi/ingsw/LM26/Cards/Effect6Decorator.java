package it.polimi.ingsw.LM26.Cards;

import it.polimi.ingsw.LM26.PublicPlayerZone.PlayerZone;

public class Effect6Decorator implements ToolCardDecorator {

    private ToolCard toolcard = null;


    public Effect6Decorator(ToolCard toolcard) {
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

        //MANCA: se è il primo dado devo rimetterlo in un bordo

        //if ( ! placeDie(die, toBox)) {
        //          System.Out.Println("error");

        //

        //          }


        // else    MANCA: togli il dado dalla riserva






        //public void placeDie(Die die, Box toBox){      //ATTENZIONE: il controllo se non puoi piazzarlo va fatto fuori
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
