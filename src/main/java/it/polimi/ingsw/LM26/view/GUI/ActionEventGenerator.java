package it.polimi.ingsw.LM26.view.GUI;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PlayArea.roundTrack.RoundTrack;
import it.polimi.ingsw.LM26.model.PlayArea.roundTrack.RoundTrackInt;
import it.polimi.ingsw.LM26.model.PlayArea.roundTrack.RoundTrackTurn;
import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.observers.serverController.Observable;
import it.polimi.ingsw.LM26.view.GUI.controllers.GameController;

import java.util.ArrayList;

public class ActionEventGenerator {

    private GameController gController;
    private ActionEvent ae;
    private ArrayList<GameState> stateArray;
    private int idx;
    private int contCellsFrameBoard;
    private ArrayList<Box> fromBoxes;
    private ArrayList<Box> toBoxes;
    private int[] rTrackcoordinates;
    private View view;

    public ActionEventGenerator(GameController gController, View view) {
        this.gController=gController;
        this.view=view;
        reset();
    }

    private void reset(){
        stateArray=new ArrayList<GameState>();
        stateArray.add(GameState.DRAFTPOOL);
        stateArray.add(GameState.FRAMEBOARD);
    }

    public void endTurn(){
        ae=new ActionEvent();
        ae.setNoAction(true);
        ae.setPlayer(ModelManager.getId());
        ae.setId(11);
        gController.setInstructions("Fine turno");
        view.notifyActionEvent(ae);
    }

    public void cardEvent(int cardpos) throws IllegalArgumentException{
        idx=0;
        contCellsFrameBoard=0;
        if(cardpos<0 || cardpos>2) throw new IllegalArgumentException("wrong card position");
        ae=new ActionEvent();
        ae.setPlayer(ModelManager.getId());
        int cardid=ModelManager.getModel().getOnBoardCards().getToolArrayList().get(cardpos);
        ae.setCardID(cardid);
        if(cardid==6){
            ae.setId(7);
            stateArray.add(0, GameState.DRAFTPOOL);
            stateArray.add(1,GameState.ACTIONEVENT);
        }
        else{
            stateArray.clear();
            switch(cardid){
                case 7:
                case 8: {ae.setId(8);}break;
                case 12:{
                    ae.setId(10);
                    stateArray.add(GameState.ROUNDTRACK);
                    stateArray.add(GameState.FRAMEBOARD);
                    stateArray.add(GameState.FRAMEBOARD);
                    stateArray.add(GameState.FRAMEBOARD);
                    stateArray.add(GameState.FRAMEBOARD);
                }break;
                case 4:{
                    ae.setId(3);
                    stateArray.add(GameState.FRAMEBOARD);
                    stateArray.add(GameState.FRAMEBOARD);
                    stateArray.add(GameState.FRAMEBOARD);
                    stateArray.add(GameState.FRAMEBOARD);
                }break;
                case 2:
                case 3:{
                    ae.setId(2);
                    stateArray.add(GameState.FRAMEBOARD);
                    stateArray.add(GameState.FRAMEBOARD);
                }break;
                case 11:{
                    ae.setId(7);
                    stateArray.add(GameState.DRAFTPOOL);
                    stateArray.add(GameState.ACTIONEVENT);
                    stateArray.add(GameState.DIEVALUE);
                    stateArray.add(GameState.FRAMEBOARD);
                }break;
                case 9:{
                    ae.setId(4);
                    stateArray.add(GameState.DRAFTPOOL);
                    stateArray.add(GameState.FRAMEBOARD);
                }break;
                case 10:{
                    ae.setId(7);
                    stateArray.add(GameState.DRAFTPOOL);
                }break;
                case 5:{
                    ae.setId(5);
                    stateArray.add(GameState.DRAFTPOOL);
                    stateArray.add(GameState.ROUNDTRACK);
                }break;
                case 1:{
                    ae.setId(6);
                    stateArray.add(GameState.DRAFTPOOL);
                    stateArray.add(GameState.INDECREMENT);
                } break;
            }
        }
        if(stateArray.size()==0){
            sendActionEvent();
        }
        else gController.setUpState(stateArray.get(idx));
    }

    public void draftPoolEvent(DieInt die, GameState gameState) throws IllegalArgumentException{
        if(gameState==GameState.BEGINMOVE){
            idx=0;
            contCellsFrameBoard=0;
            ae=new ActionEvent();
            ae.setPlayer(ModelManager.getId());
            ae.setId(1);
        }
        if(die==null) throw new IllegalArgumentException("null die from draft pool");
        else{
            ae.setDieFromDraft(die);
            nextAction();
        }
    }

    public void frameBoardEvent(Box box) throws IllegalArgumentException{
        if(box==null) throw new IllegalArgumentException("null box from frame board");
        else{
            if(contCellsFrameBoard==0){
                if(idx==stateArray.size()-1){
                    ae.setToBox1(box);
                }
                else ae.setFromBox1(box);
            }
            else if(contCellsFrameBoard==1){
                ae.setToBox1(box);
            }
            else if(contCellsFrameBoard==2){
                fromBoxes =new ArrayList<Box>();
                fromBoxes.add(ae.getFromBox1());
                fromBoxes.add(box);
                ae.setFromBoxList(fromBoxes);
                ae.setFromBox1(null);
            }
            if(contCellsFrameBoard==3){
                toBoxes =new ArrayList<Box>();
                toBoxes.add(ae.getToBox1());
                toBoxes.add(box);
                ae.setFromBoxList(toBoxes);
                ae.setToBox1(null);

            }
            contCellsFrameBoard++;
            nextAction();
        }
    }

    public void dieValueEvent(int val) throws IllegalArgumentException{
        if(val>6 || val<1) throw new IllegalArgumentException("wrong die value");
        else{
            ae.setNumber(val);
            nextAction();
        }
    }

    public void roundTrackEvent(DieInt die, int turnIdx, int dieListIdx) throws IllegalArgumentException{
        ArrayList<RoundTrackTurn> rTrack=ModelManager.getModel().getRoundTrackInt().getRoundTrackTurnList();
        if(die==null || turnIdx<0 || turnIdx>rTrack.size() || dieListIdx<0 || dieListIdx>rTrack.get(turnIdx).getDiceList().size()) throw new IllegalArgumentException("null die from round track or wrong coordinates");
        else{
            ae.setDieFromRoundTrack(die);
            rTrackcoordinates=new int[2];
            rTrackcoordinates[0]=turnIdx;
            rTrackcoordinates[1]=dieListIdx;
            ae.setrTrackCoordinates(rTrackcoordinates);
            nextAction();
        }
    }

    public void indecrementEvent(String increment) throws IllegalArgumentException{
        if(!(increment.equalsIgnoreCase("increment") || increment.equalsIgnoreCase("decrement"))) throw new IllegalArgumentException("wrong increment string");
        else{
            ae.setInDeCrement(increment);
            ae.setId(6);
            nextAction();
        }
    }

    private void sendActionEvent(){
        view.notifyActionEvent(ae);
        System.out.println("action event:");
        System.out.println(ae.getId());
        System.out.println(ae.getDieFromDraft());

        if(idx!=stateArray.size()){
            idx++;
            if(ae.getCardID()==11){
                ae.setId(9);
                ae.setDieFromDraft(null);
            }
            else if(ae.getCardID()==6){
                ae.setId(1);
            }
            gController.setUpState(stateArray.get(idx));
        }

    }

    private void nextAction(){
        idx++;
        if(idx==stateArray.size() || stateArray.get(idx)==GameState.ACTIONEVENT){
            sendActionEvent();
        }
        else{
            gController.setUpState(stateArray.get(idx));
        }
    }
}
