package it.polimi.ingsw.LM26.model.Cards.decorator;

import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

public class PlaceWithNotInProximities implements ToolCardDecorator {

    private ToolCard toolcard = null;


    public PlaceWithNotInProximities(ToolCard toolcard) {
        this.toolcard = toolcard;
    }

    public int getNum(){
        return toolcard.getNum();
    }

    public void printCard(){
        toolcard.printCard();
    }

    public void play (PlayerZone player) { //piazza il dado in modo che non sia addiacente ad altri


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
