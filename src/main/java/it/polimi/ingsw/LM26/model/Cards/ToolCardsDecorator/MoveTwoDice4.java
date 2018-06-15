package it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator;

import it.polimi.ingsw.LM26.controller.PlaceDie;
import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class MoveTwoDice4 extends ToolCardDecorator {

    private ToolCard toolcard = null;

    public MoveTwoDice4(ToolCard toolcard) {
        this.toolcard = toolcard;
        this.type="MoveTwoDice4";
        this.typeToolCard = "ToolCard";
    }

    public int getNum(){
        return toolcard.getNum();
    }

    @Override
    public void rewrite() {

        this.type="MoveTwoDice4";
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
    public boolean play(DieInt dieFromDraft, Box toBox, int player){return false;}
    public boolean play(DieInt dieFromDraft, DieInt dieFromRoundTrack){return false;}
    public boolean play( DieInt dieFromDraft, String inDeCrement){return false;}
    public boolean play(DieInt dieFromDraft, int pl){return false;}

    @Override
    public boolean play(int number, Box toBox, int pl) {
        return false;
    }


    public boolean play(int player ){return false;}

    @Override
    public boolean play(DieInt fromRoundTrack, ArrayList<Box> fromBoxList, ArrayList<Box> toBoxList, int player) {
        return false;
    }

    public boolean play ( ArrayList<Box> fromBoxList, ArrayList<Box> toBoxList, int pl ) {



        Model model = singletonModel();
        PlayerZone player = model.getPlayerList().get(pl);
        ArrayList <DieInt> dieList=new ArrayList<DieInt>();

        for(int i=0; i<fromBoxList.size(); i++)

            if( !fromBoxList.get(i).isIsPresent()  )
                return false;
            else dieList.add(fromBoxList.get(i).getDie());


        for(int j=0; j<fromBoxList.size(); j++){

            PlaceDie placement = new PlaceDie(fromBoxList.get(j).getDie(), toBoxList.get(j) , player);

            fromBoxList.get(j).free();

            if (!placement.placeDie() ) {

                System.out.println("error " + j +" placement");

                for(int k=0; k<fromBoxList.size(); k++) {
                    fromBoxList.get(k).free();
                    fromBoxList.get(k).setDie(dieList.get(k));
                    toBoxList.get(k).free();
                }

                return false;
            }

        }
        return true;


    }
}




  /* Model model = singletonModel();
        PlayerZone player = model.getPlayerList().get(pl);
        DieInt die = fromBox1.getDie();
        DieInt die2 =fromBox2.getDie();

        PlaceDie placement = new PlaceDie(die, toBox1, player);

        fromBox1.free();

         if (placement.placeDie() ) {

             placement = new PlaceDie(die2, toBox2, player);
             fromBox2.free();
             if(!(placement.placeDie())){
                 System.out.println("error 2 placement");
                 fromBox2.setDie(die);
                 return false;
             }
             return true;
         }
        System.out.println("error 1 placement ");
        fromBox1.setDie(die);
        return false;
*/