package it.polimi.ingsw.LM26.controller;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import static it.polimi.ingsw.LM26.model.PlayArea.Color.WHITE;
import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class PlaceDie implements PlayerActionInt{

    Model model = singletonModel();
    private Die die;
    private Box toBox;
    private PlayerZone player;
    private int i, j;
    private Box[][] board;

    public PlaceDie(Die dieFromDraft, Box toBox, int idPlayer) {

        this.die=dieFromDraft;
        this.toBox=toBox;
        this.i = toBox.getI();
        this.j = toBox.getJ();
        player=model.getPlayerList().get(idPlayer);

        this.board = player.getPlayerBoard().getBoardMatrix();
    }

    public boolean placeDie(){      //ATTENZIONE: togli il dado dalla riserva va fatto fuori

        if (player.getPlayerBoard().isEmpty())
                while(!(i==0 || i==3 || j==0 || j==4 )){
                                     System.out.println("error: primo dado deve andare sul bordo");
                                     //come chiedere un'altra volta il dado???????
                                     return false;
                                   }
        if (checkColorRestriction() && checkValueRestriction() && checkNearByRestrictions() ){
             toBox.setDie(die);
              return true;}
    return false;
    }




    public boolean checkColorRestriction(){
      if( toBox.getPatternBox().isShade()) return true;
          else if (toBox.getPatternBox().getColor()==die.getColor() ||
                   toBox.getPatternBox().getColor()==WHITE) return true;
    return false;
    }



    public boolean checkValueRestriction(){
    if( toBox.getPatternBox().isColor()) return true;
          else if (toBox.getPatternBox().getValue()==die.getValue()) return true;
    return false;
    }

     public boolean checkNearByRestrictions() {

         if (i == 0) {

             if (j == 0) //angolo alto a sinistra

                 if (checkRight())
                     if (checkRightDie()) return true;
             else if(checkDown()) return true;
             else if(checkRightDown()) return true;

             else if(j == 4)  //angolo in alto a destra
             if (checkLeft())
                 if (checkLeftDie()) return true;
             else if(checkDown()) return true;
             else if(checkLeftDown()) return true;

          else       //tutti gli altri valori prima riga
             if (checkRight())
                 if (checkRightDie()) return true;
             else if(checkLeft())
             if (checkLeftDie()) return true;
             else if(checkDown()) return true;
             else if(checkRightDown()) return true;
             else if(checkLeftDown()) return true;

             else if(i ==3)
             if (j == 0)  //angolo in basso a sinistra
                 if (checkRight())
                     if (checkRightDie()) return true;
             else if(checkUp()) return true;
             else if(checkRightUp()) return true;

             else if(j == 4)    // angolo in basso a destra
             if (checkLeft())
                 if (checkLeftDie()) return true;
             else if(checkUp()) return true;
             else if(checkLeftUp()) return true;

                  else     //tutti gli altri valori ultima riga
             if (checkRight())
                 if (checkRightDie()) return true;
             else if(checkLeft())
             if (checkLeftDie()) return true;
             else if(checkUp()) return true;
             else if(checkRightUp()) return true;
             else if(checkLeftUp()) return true;

             else if(j == 0)              //tutti gli altri valori prima colonna
             if (i != 0 && i != 3)
                 if (checkRight())
                     if (checkRightDie()) return true;
             else if(checkRightUp()) return true;
             else if(checkRightDown()) return true;
             else if(checkUp()) return true;
             else if(checkDown()) return true;

             else if(j == 4)            //tutti gli altri valori ultima colonna
             if (i != 0 && i != 3)
                 if (checkLeft())
                     if (checkLeftDie()) return true;
             else if(checkLeftUp()) return true;
             else if(checkLeftDown()) return true;
             else if(checkUp()) return true;
             else if(checkDown()) return true;

    else      //tutti i valori non di margine
             if (checkAll()) return true;
             return false;
         }
         return false;

     }




    public boolean checkAll(){
    if(checkRight()) if  (checkRightDie() ) return true;
    if(checkLeft()) if  (checkLeftDie() ) return true;
     if( checkUp())  return true;
      if(checkDown()) return true;
      if(checkRightUp())  return true;
      if(checkRightDown()) return true;
      if(checkLeftUp()) return true;
      if(checkLeftDown()) return true;
     return false;
    }


    public boolean checkRight(){
     return (board[i][j+1].isIsPresent());}

    public boolean checkLeft(){
     return (board[i][j-1].isIsPresent());}

    public boolean checkUp(){
     return (board[i-1][j].isIsPresent());}

    public boolean checkDown(){
     return (board[i+1][j].isIsPresent());}

    public boolean checkRightUp(){
     return (board[i-1][j+1].isIsPresent());}

    public boolean checkRightDown(){
     return (board[i+1][j+1].isIsPresent());}

    public boolean checkLeftUp(){
     return (board[i-1][j-1].isIsPresent());}

    public boolean checkLeftDown(){
     return (board[i+1][j-1].isIsPresent());}

    public boolean checkLeftDie(){
    if(board[i][j-1].getDie().getColor()==die.getColor() ||
             board[i][j-1].getDie().getValue()==die.getValue())return false;
    else return true;
    }

    public boolean checkRightDie(){
    if(board[i][j+1].getDie().getColor()==die.getColor() ||
              board[i][j+1].getDie().getValue()==die.getValue())return false;
    else return true;
    }




}



