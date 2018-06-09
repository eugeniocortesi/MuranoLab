package it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator;

import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

public class PlaceWithNotInProximities9 implements ToolCardDecorator {

    private ToolCard toolcard = null;


    public PlaceWithNotInProximities9(ToolCard toolcard) {
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
    public boolean play( int player){return false;}

    public boolean play (DieInt dieFromDraft, Box toBox, int player ) { //piazza il dado in modo che non sia addiacente ad altri

        return false;
        //Die die1 =chooseFromTheDraft();
        //Box toBox = askTheBok();
        // int i = toBox.getI();
        //int j = toBox.getJ();
        //boolean placed=false;
        //box[][] board = player.getPlayerBoard();

        //while(!placed){

        //toBox = askTheBok();

        // if (player.getPlayerBoard().isEmpty())
        //              while(!(i==0 || i==3 || j==0 || j==4 )){
        //                          System.out.println("error: primo dado deve andare sul bordo");
        //                          toBox = askTheBok();
        //                          }
        //
        //if (i=0 ){
        //
        //      if(j=0) //angolo alto a sinistra

        //              if(!board[i][j+1].isIsPresent()  && !board[i+1][j].isIsPresent() && ! board[i+1][j+1].isIsPresent() &&
        //                  checkColorRestriction(die, toBox) && checkValueRestriction(die, toBox))
        //                      {placed=true; toBox.setDie(die); inDraft.remove(die);

        //      elseif(j=4)  //angolo in alto a destra
        //             if(!board[i][j-1].isIsPresent()  && !board[i+1][j-i].isIsPresent() && ! board[i+1][j].isIsPresent() &&
        //                  checkColorRestriction(die, toBox) && checkValueRestriction(die, toBox))
        //                             {placed=true; toBox.setDie(die); inDraft.remove(die);

        //      else       //tutti gli altri valori prima riga
        //             if(!board[i][j-1].isIsPresent()  && !board[i+1][j-i].isIsPresent() && ! board[i+1][j].isIsPresent() &&
        //                !board[i+1][j+1].isIsPresent() && ! board[i][j+1].isIsPresent() && checkColorRestriction(die, toBox) && checkValueRestriction(die, toBox))
        //                             {placed=true; toBox.setDie(die); inDraft.remove(die);

        //elseif (i=3 )
        //      if(j=0)  //angolo in basso a sinistra
        //              if(!board[i][j+1].isIsPresent()  && !board[i-1][j].isIsPresent() && ! board[i-1][j+1].isIsPresent() &&
        //                  checkColorRestriction(die, toBox) && checkValueRestriction(die, toBox))
        //                               {placed=true; toBox.setDie(die); inDraft.remove(die);
        //
        //     elseif(j=4)    // angolo in basso a destra
        //              if(!board[i][j-1].isIsPresent()  && !board[i-1][j].isIsPresent() && ! board[i-1][j-1].isIsPresent() &&
        //                  checkColorRestriction(die, toBox) && checkValueRestriction(die, toBox))
        //                               {placed=true; toBox.setDie(die); inDraft.remove(die);
        //
        //      else       //tutti gli altri valori ultima riga
        //             if(!board[i][j-1].isIsPresent()  && !board[i-1][j-i].isIsPresent() && ! board[i-1][j].isIsPresent() &&
        //                 !board[i-1][j+1].isIsPresent() && !board[i][j+i].isIsPresent() checkColorRestriction(die, toBox) && checkValueRestriction(die, toBox))
        //                            {placed=true; toBox.setDie(die); inDraft.remove(die);

        //elseif (j=0 )             //tutti gli altri valori prima colonna
        //      if(i!=0 && i!=3)
        //             if(!board[i][j+1].isIsPresent()  && !board[i-1][j+i].isIsPresent() && ! board[i-1][j].isIsPresent() &&
        //                 !board[i+1][j+1].isIsPresent() && !board[i][j+i].isIsPresent() checkColorRestriction(die, toBox) && checkValueRestriction(die, toBox))
        //                             {placed=true; toBox.setDie(die); inDraft.remove(die);

        //elseif (j=4 ){            //tutti gli altri valori ultima colonna
        //      if(i!=0 && i!=3)
        //             if(!board[i][j-1].isIsPresent()  && !board[i-1][j-i].isIsPresent() && ! board[i-1][j].isIsPresent() &&
        //                 !board[i+1][j-1].isIsPresent() && !board[i+1][j].isIsPresent() checkColorRestriction(die, toBox) && checkValueRestriction(die, toBox))
        //                             {placed=true; toBox.setDie(die); inDraft.remove(die);

        //else      //tutti i valori non di margine
        //          if(
        //          ! board[i][j-1].isIsPresent() && ! board[i+1][j-i].isIsPresent() &&
        //          ! board[i+1][j].isIsPresent() && ! board[i+1][j+1].isIsPresent() &&
        //          ! board[i][j+1].isIsPresent() && ! board[i-1][j-i].isIsPresent() &&
        //          ! board[i-1][j].isIsPresent() && ! board[i-1][j+1].isIsPresent() &&
        //          checkColorRestriction(die, toBox) && checkValueRestriction(die, toBox) ) {placed=true; toBox.setDie(die); inDraft.remove(die);
        //          }

        //}






    }
}
