package it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator;

import it.polimi.ingsw.LM26.controller.PlaceDie;
import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class PlaceWithNotInProximities9 extends ToolCardDecorator {

    private ToolCard toolcard = null;


    public PlaceWithNotInProximities9(ToolCard toolcard) {
        this.toolcard = toolcard;
        this.type="PlaceWithNotInProximities9";
        this.typeToolCard = "ToolCard";
    }

    public int getNum(){
        return toolcard.getNum();
    }

    @Override
    public void rewrite() {

        this.type="PlaceWithNotInProximities9";
        this.typeToolCard = "ToolCard";

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

    @Override
    public boolean play(ArrayList<Box> fromBoxList, ArrayList<Box> toBoxList, int player) {
        return false;
    }
    public boolean play(DieInt dieFromDraft, DieInt dieFromRoundTrack){return false;}
    public boolean play( DieInt dieFromDraft, String inDeCrement){return false;}
    public boolean play(DieInt dieFromDraft, int pl){return false;}

    @Override
    public boolean play(int number, Box toBox, int pl) {
        return false;
    }


    public boolean play( int player){return false;}

    @Override
    public boolean play(DieInt fromRoundTrack, ArrayList<Box> fromBoxList, ArrayList<Box> toBoxList, int player) {
        return false;
    }

    public boolean play (DieInt dieFromDraft, Box toBox, int pl ) { //piazza il dado in modo che non sia addiacente ad altri


      Model model=singletonModel();
      PlayerZone player =  model.getPlayerList().get(pl);
      Box[][] board = model.getPlayerList().get(pl).getPlayerBoard().getBoardMatrix();
      int i = toBox.getI();
      int j = toBox.getJ();
      PlaceDie placement = new PlaceDie(dieFromDraft, toBox, player);

      if (player.getActionHistory().isPlacement() || player.getActionHistory().isDieUsed()) {
          System.out.println("action expired");
          return false;
      }

      if(toBox.isIsPresent())return false;

      if (player.getPlayerBoard().isEmpty()){
          if(!(i==0 || i==3 || j==0 || j==4 )){
              System.out.println("error: first die must be placed on the edge");
              return false;
          }
      }

      if (!placement.checkColorRestriction() || !placement.checkValueRestriction()) return false;




        if (i==3 ) {

            if(j==0) {     //angolo basso a sinistra

                if (board[i][j + 1].isIsPresent() || board[i - 1][j].isIsPresent() || board[i - 1][j + 1].isIsPresent())
                    return false;
            } else if(j==4) {   //angolo basso a destra

                if (board[i][j - 1].isIsPresent() || board[i - 1][j].isIsPresent() || board[i - 1][j - 1].isIsPresent())
                    return false;
            }

            else     //tutti valori ultima riga
            {
                if(board[i][j-1].isIsPresent()  || board[i-1][j-i].isIsPresent() || board[i-1][j].isIsPresent() ||
                        board[i-1][j+1].isIsPresent() || board[i][j+i].isIsPresent())return false;
            }
        }

         else if (i==0 ) {

            if (j == 0) {  //angolo alto a sinistra

                if (board[i][j + 1].isIsPresent() || board[i + 1][j].isIsPresent() || board[i + 1][j + 1].isIsPresent())
                    return false;
            }else if (j == 4) {      //angolo alto a destra

                if (board[i][j - 1].isIsPresent() || board[i + 1][j - i].isIsPresent() || board[i + 1][j].isIsPresent())
                    return false;
            }

            else    //tutti valori prima riga
            {
                if (board[i][j - 1].isIsPresent() || board[i + 1][j - i].isIsPresent() || board[i + 1][j].isIsPresent() ||
                        board[i + 1][j + 1].isIsPresent() || board[i][j + 1].isIsPresent()) return false;
            }
        }


        else {
             if (j == 0) {
                 if (board[i][j + 1].isIsPresent() || board[i - 1][j + i].isIsPresent() || board[i - 1][j].isIsPresent() ||
                         board[i + 1][j + 1].isIsPresent() || board[i + 1][j].isIsPresent()) return false;

             }else if (j == 4) {
                 if (board[i][j - 1].isIsPresent() || board[i - 1][j - i].isIsPresent() || board[i - 1][j].isIsPresent() ||
                         board[i + 1][j - 1].isIsPresent() || board[i + 1][j].isIsPresent()) return false;

             } else if (board[i][j - 1].isIsPresent() || board[i + 1][j - 1].isIsPresent() ||
                        board[i + 1][j].isIsPresent() || board[i + 1][j + 1].isIsPresent() ||
                             board[i][j + 1].isIsPresent() || board[i - 1][j - 1].isIsPresent() ||
                             board[i - 1][j].isIsPresent() || board[i - 1][j + 1].isIsPresent()) return false;
         }


        toBox.setDie(dieFromDraft);
        model.getDraftPool().getInDraft().remove(dieFromDraft);
        player.getPlayerBoard().setEmpty(false);
        player.getPlayerBoard().incrementNumDice();
        player.getActionHistory().setDieUsed(true);
        player.getActionHistory().setPlacement(true);


        return true ;


    }
}
