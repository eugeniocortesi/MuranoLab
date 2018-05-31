package it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator;

import it.polimi.ingsw.LM26.controller.PlaceDie;
import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class MoveWithNoColorRestriction2 implements ToolCardDecorator {

    private ToolCard toolcard = null;


    public MoveWithNoColorRestriction2(ToolCard toolcard) {
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


    public boolean play(Box fromBox1, Box toBox1, Box fromBox2, Box toBox2, int player){return false;}
    public boolean play(DieInt dieFromDraft, Box toBox, int player){return false;}
    public boolean play(DieInt dieFromDraft, DieInt dieFromRoundTrack){return false;}
    public boolean play( DieInt dieFromDraft, String inDeCrement){return false;}
    public boolean play(DieInt dieFromDraft){return false;}
    public boolean play( int player){return false;}

    public boolean play (Box fromBox,Box toBox, int pl) {

        Model model = singletonModel();
        PlayerZone player = model.getPlayerList().get(pl);
        DieInt die = fromBox.getDie();
        if(!fromBox.isIsPresent())return false;                      //added controller
        PlaceDie placement = new PlaceDie(die, toBox, player);
        fromBox.free();
        if(player.getPlayerBoard().getNumDice()==1) {
            if (placement.checkValueRestriction() && placement.checkEdgeRestrictions()) {
                toBox.setDie(die);
                return true;
            }
        }
        else if (placement.checkValueRestriction() && placement.checkNearByRestrictions() ){

            return true;
        }

        System.out.println("error card 2");
        fromBox.setDie(die);
        return false;

    }
}








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





         //return false;}




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






