package it.polimi.ingsw.LM26.model.Cards;

import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

public class Effect2Decorator implements ToolCardDecorator {

    private ToolCard toolcard = null;


    public Effect2Decorator(ToolCard toolcard) {
        this.toolcard = toolcard;
    }

    public int getNum(){
        return toolcard.getNum();
    }

    public void printCard(){
        toolcard.printCard();
    }

    public void play (PlayerZone player) {


        //Box fromBox = askTheBox();
        //Box toBox = askTheBok();
        //Die die = fromBox.getDie();
        //while (! ( checkValueRestriction(die, toBox) && checkNearByRestriction(player, die, toBox)) ){
        //          System.Out.println("error");
        //          toBox = askTheBok();
        //          }
        //toBox.setDie(die);
        //fromBox.free();




        // public void checkNearByRestrictions(Player player, Die die, Box toBox){
        // Box[][] board= player.getPlayerBoard().getBoardMatrix();
        // int i=toBox.getI();
        // int j=toBox.getJ()

        //if (i=0 ){
        //
        //      if(j=0) //angolo alto a sinistra

        //              if(checkRight(board, i, j))
        //                          if  (checkRightDie(board, i, j, die)) return true;
        //              elseif(checkDown(board, i, j)) return true;
        //              elseif(checkRightDown(board, i, j)) return true;

        //      elseif(j=4)  //angolo in alto a destra
        //              if(checkLeft(board, i, j))
        //                          if  (checkLeftDie(board, i, j, die)) return true;
        //              elseif(checkDown(board, i, j)) return true;
        //              elseif(checkLeftDown(board, i, j)) return true;

        //      else       //tutti gli altri valori prima riga
        //              if(checkRight(board, i, j))
        //                           if  (ccheckRightDie(board, i, j, die)) return true;
        //              elseif(checkLeft(board, i, j))
        //                          if  (checkLeftDie(board, i, j, die)) return true;
        //              elseif(checkDown(board, i, j))return true;
        //              elseif(checkRightDown(board, i, j))return true;
        //              elseif(checkLeftDown(board, i, j))return true;

        //elseif (i=3 )
        //      if(j=0)  //angolo in basso a sinistra
        //              if(checkRight(board, i, j))
        //                               if  (checkRightDie(board, i, j, die) ) return true;
        //              elseif(checkUp(board, i, j)) return true;
        //              elseif(checkRightUp(board, i, j)) return true;
        //
        //     elseif(j=4)    // angolo in basso a destra
        //              if(checkLeft(board, i, j))
        //                                    if  (checkLeftDie(board, i, j, die)) return true;
        //              elseif(checkUp(board, i, j)) return true;
        //              elseif(checkLeftUp(board, i, j)) return true;
        //
        //              else     //tutti gli altri valori ultima riga
        //                      if(checkRight(board, i, j))
        //                                   if  (checkRightDie(board, i, j, die) ) return true;
        //                     elseif(checkLeft(board, i, j))
        //                                 if  (checkLeftDie(board, i, j, die)) return true;
        //                      elseif(checkUp(board, i, j))return true;
        //                      elseif(checkRightUp(board, i, j))return true;
        //                      elseif(checkLeftUp(board, i, j))return true;

        //elseif (j=0 ){              //tutti gli altri valori prima colonna
        //      if(i!=0 && i!=3)
        //                      if(checkRight(board, i, j))
        //                                   if  (checkRightDie(board, i, j, die)) return true;
        //                      elseif(checkRightUp(board, i, j)) return true;
        //                      elseif(checkRightDown(board, i, j)) return true;
        //                      elseif(checkUp(board, i, j))return true;
        //                      elseif(checkDown(board, i, j))return true;

        //elseif (j=4 ){            //tutti gli altri valori ultima colonna
        //      if(i!=0 && i!=3)
        //                      if(checkLeft(board, i, j))
        //                                   if  (checkLeftDie(board, i, j, die) ) return true;
        //                      elseif(checkLeftUp(board, i, j)) return true;
        //                      elseif(checkLeftDown(board, i, j)) return true;
        //                      elseif(checkUp(board, i, j))return true;
        //                      elseif(checkDown(board, i, j))return true;

        //else      //tutti i valori non di margine
        //          if(checlAll(box, i, j))return true;
        //return false;
         //}




        //public void checkAll(Box[][] board,int i, j){
        //if(checkRight(board, i, j)) if  (checkRightDie(board, i, j, die) ) return true;
        //if(checkLeft(board, i, j)) if  (checkLeftDie(board, i, j, die) ) return true;
        // if( checkUp(board, i, j))  return true;
        //  if(checkDown(board, i, j)) return true;
        //  if(checkRightUp(board, i, j))  return true;
        //  if(checkRightDown(board, i, j)) return true;
        //  if(checkLeftUp(board, i, j)) return true;
        //  if(checkLeftDown(board, i, j)) return true;
        // return false;
        //}


        //public void checkRight(Box[][] board, int i, int j){
        // return (board[i][j+1].isIsPresent())}

        //public void checkLeft(Box[][] board, int i, int j){
        // return (board[i][j-1].isIsPresent())}

        //public void checkUp(Box[][] board, int i, int j){
        // return (board[i-1][j].isIsPresent())}

        //public void checkDown(Box[][] board, int i, int j){
        // return (board[i+1][j].isIsPresent())}

        //public void checkRightUp(Box[][] board, int i, int j){
        // return (board[i-1][j+1].isIsPresent())}

        //public void checkRightDown(Box[][] board, int i, int j){
        // return (board[i+1][j+1].isIsPresent())}

        //public void checkLeftUp(Box[][] board, int i, int j){
        // return (board[i-1][j-1].isIsPresent())}

        //public void checkLeftDown(Box[][] board, int i, int j){
        // return (board[i+1][j-1].isIsPresent())}

        //public void checkLeftDie(Box[][] board, int i, int j, Die die ){
        //if(board[i][j-1].getDie().getColor()==die.getColor() ||
        //          board[i][j-1].getDie().getValue()==die.getValue())return false
        //else return true;
        //}

        //public void checkRightDie(Box[][] board, int i, int j, Die die ){
        //if(board[i][j+1].getDie().getColor()==die.getColor() ||
        //          board[i][j+1].getDie().getValue()==die.getValue())return false
        //else return true;
        //}






    }
}
