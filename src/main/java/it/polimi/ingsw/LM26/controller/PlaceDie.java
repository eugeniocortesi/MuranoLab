package it.polimi.ingsw.LM26.controller;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import static it.polimi.ingsw.LM26.model.PlayArea.Color.WHITE;
import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class PlaceDie {

    Model model = singletonModel();
    private DieInt die;
    private Box toBox;
    private PlayerZone player;
    private int i, j;
    private Box[][] board;

    public PlaceDie(DieInt dieFromDraft, Box toBox, PlayerZone player) {

        this.die=dieFromDraft;
        this.toBox=toBox;
        this.i = toBox.getI();
        this.j = toBox.getJ();
        this.player=player;

        this.board = player.getPlayerBoard().getBoardMatrix();
    }


    //ATTENZIONE
    //TODO
    //CHECK IF IT'S FIRST DIE ASSIGN THE RIGHT BOOLEAN in tutti i piazzamenti

    public boolean placeDie(){      //ATTENZIONE: togli il dado dalla riserva va fatto fuori

        if (player.getPlayerBoard().isEmpty()) return placeFirstDie();

        if(toBox.isIsPresent()){
            System.out.println("a die is already present here ");
            return false;
        }

        if (checkColorRestriction() && checkValueRestriction() && checkNearByRestrictions() ){
            die.toString();
             toBox.setDie(die);
             return true;}

        System.out.println("error in general place die ");
        return false;
    }


    public boolean placeFirstDie() {

        if (checkEdgeRestrictions()) {

            if (checkColorRestriction() && checkValueRestriction()) {
                toBox.setDie(die);
                player.getPlayerBoard().setEmpty(false);
                return true;
            } else System.out.println("error in place first die ");
            return false;


        }return false; //edge restrictions not respected
    }

public boolean checkEdgeRestrictions(){

        if(!(i==0 || i==3 || j==0 || j==4 )){
            System.out.println("error: first die must be placed on the edge");
            return false;
        }
        return true;

}


    public boolean checkColorRestriction(){
        if( toBox.getPatternBox().isShade()) {System.out.println("ok color restriction");
            return true;}
          else if (toBox.getPatternBox().getColor()==die.getColor() ||
                   toBox.getPatternBox().getColor()==WHITE)  {System.out.println("ok color restriction");
                   return true;}
        System.out.println("error in color restriction");
        return false;
    }



    public boolean checkValueRestriction(){
        if( toBox.getPatternBox().isColor())  {System.out.println("ok value restriction");
            return true;}
          else if (toBox.getPatternBox().getValue()==die.getValue()) {System.out.println("ok value restriction");
          return true;}
    System.out.println("error in value restriction");
    return false;
    }

     public boolean checkNearByRestrictions() {

         if (i == 0) {

             if (j == 0) { //angolo alto a sinistra

                 if (checkRight()) {
                     if (checkRightDie()) return true;
                     else return false;
                 }
                 if (checkDown()){
                     if (checkDownDie()) return true;
                     else return false;
                 }
                 if (checkRightDown()) return true;
             }

             if (j == 4) { //angolo in alto a destra

                 if (checkLeft()){
                     if (checkLeftDie()) return true;
                     else return false;
                 }
                 if (checkDown()){
                     if (checkDownDie()) return true;
                     else return false;
                 }
                 if (checkLeftDown()) return true;
             }

             else {      //tutti gli altri valori prima riga
                 if (checkRight()){
                     if (checkRightDie()) return true;
                     else return false;
                 }
                 if (checkLeft()){
                         if (checkLeftDie()) return true;
                         else return false;
                 }
                 if (checkDown()){
                     if (checkDownDie()) return true;
                     else return false;
                 }
                 if (checkRightDown()) return true;
                 if (checkLeftDown()) return true;
             }
         }
         else if(i ==3) {
             if (j == 0) { //angolo in basso a sinistra
                 if (checkRight()) {
                     if (checkRightDie()) return true;
                     else return false;
                 }
                 if (checkUp()) return true;
                 if (checkRightUp()){
                     if (checkUpDie()) return true;
                     else return false;
                 }

             }
             if (j == 4) {   // angolo in basso a destra
                 if (checkLeft()) {
                     if (checkLeftDie()) return true;
                     else return false;
                 }
                 if (checkUp()) return true;
                 if (checkLeftUp()) {
                     if (checkUpDie()) return true;
                     else return false;
                 }
             }


            else {   //tutti gli altri valori ultima riga
                 if (checkRight()) {
                            if (checkRightDie()) return true;
                            else return false;}
                 if (checkLeft()){
                            if (checkLeftDie()) return true;
                            else return false;}
                 if (checkUp()) return true;
                 if (checkRightUp()) return true;
                 if (checkLeftUp()) {
                     if (checkUpDie()) return true;
                     else return false;
                 }
             }
         }

         else if(j == 0) {         //tutti gli altri valori prima colonna
             //if (i != 0 && i != 3) {
                 if (checkRight()) {
                     if (checkRightDie()) return true;
                     else return false;
                 }
                 if (checkRightUp()) return true;
                 if (checkRightDown()) return true;
                 if (checkUp()) {
                     if (checkUpDie()) return true;
                     else return false;
                 }
                 if (checkDown()) {
                     if (checkDownDie()) return true;
                     else return false;
                 }
             //}
         }

         else if(j == 4) {          //tutti gli altri valori ultima colonna
             //if (i != 0 && i != 3) {
                 if (checkLeft()) {
                     if (checkLeftDie()) return true;
                     else return false;
                 }
                 if (checkLeftUp()) return true;
                 if (checkLeftDown()) return true;
                 if (checkUp()) {
                     if (checkUpDie()) return true;
                     else return false;
                 }
                 if (checkDown()) {
                     if (checkDownDie()) return true;
                     else return false;
                 }
             //}
         }

        else      //tutti i valori non di margine
             if (checkAll()) return true;

         System.out.println("error in position restriction");
         return false;
         }







    public boolean checkAll(){
    if(checkRight()){
        if  (checkRightDie() ) return true;
        else return false;
    }
    if(checkLeft()){
        if  (checkLeftDie() ) return true;
        else return false;
    }
     if( checkUp()) {
         if (checkUpDie()) return true;
         else return false;
     }
      if(checkDown()) {
          if (checkDownDie()) return true;
          else return false;
      }
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

    public boolean checkUpDie(){
        if(board[i-1][j].getDie().getColor()==die.getColor() ||
                board[i-1][j].getDie().getValue()==die.getValue())return false;
        else return true;
    }

    public boolean checkDownDie(){
        if(board[i+1][j].getDie().getColor()==die.getColor() ||
                board[i+1][j].getDie().getValue()==die.getValue())return false;
        else return true;
    }


}



