package it.polimi.ingsw.LM26.controller.GamePhases;

import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DraftPool;
import it.polimi.ingsw.LM26.model.PlayArea.roundTrack.RoundTrackInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerState;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerState.STANDBY;
import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class Round {

    private static final int[] t = {1, 2, 3, 4};

    private int turnCounter=0;

    private PlayerZone currentPlayer;

    private RoundState roundState=RoundState.RUNNING;

    public Round(RoundTrackInt rTrack, ArrayList<PlayerZone> pZone, int nrounds) {
        this.assignTurn(rTrack, pZone, nrounds);
        pullDice();
    }


    //assegna l'ordine con cui si gioca all'interno di un turno e salva l'ordine dell'ultimo turno per la parità di punteggio
    public void assignTurn(RoundTrackInt roundTrack, ArrayList<PlayerZone> playerList, int nrounds) { //roundtrack passata dalla centralPhase, l'nrounds da passare è la costante della centralPhase
        for (int i = 0; i < playerList.size(); i++) {
            int j=t[(i + roundTrack.getCurrentTurn() - 1) %playerList.size()];
            playerList.get(i).setNumberPlayer(j);
            if(roundTrack.getCurrentTurn() == nrounds) playerList.get(i).setLastRoundTurn(j);
        }
    }

    //nextPlayer va usato dopo endAction, quando il contatore è già incrementato. plStandby passato è sempre 0
    public PlayerZone nextPlayer(ArrayList<PlayerZone> player, int[] turn, int plStandby) throws IllegalArgumentException {
        //System.out.println("!");
        turnCounter=turnCounter+plStandby;
      for(int i=0; i<player.size(); i++){
          //System.out.println(player.get(i).getName());
          for(int j=0; j<turnCounter; j++){
              //System.out.println("o");
          }
          //System.out.println("g");
          if(player.get(i).getNumber()==turn[turnCounter]) {
              if(player.get(i).getPlayerState()!= STANDBY){
                  player.get(i).setPlayerState(PlayerState.BEGINNING);
                  if (turnCounter > 0) {
                      if(turn[turnCounter]==turn[turnCounter-1]) {
                          player.get(i).getActionHistory().setFirstTurn(false);
                          player.get(i).getActionHistory().setSecondTurn(true);
                      }else player.get(i).getActionHistory().setFirstTurn(true);
                  }else player.get(i).getActionHistory().setFirstTurn(true);
                  currentPlayer=player.get(i);
                  return player.get(i);
              }
              else return nextPlayer(player, turn, ++plStandby);
          }
      }
      throw new IllegalArgumentException("no player has the current turn");
    }


    //passa il turno al successivo, se è finito turno globale mette dadi nella casella della round track e ritorna FINISHED
    public void endAction(int[] turn, RoundTrackInt roundTrack, DraftPool draftPool, PlayerZone actingPlayer) {
        actingPlayer.setPlayerState(PlayerState.ENDING);
        actingPlayer.getActionHistory().deleteTurnHistory();
        //TODO
        actingPlayer.getActionHistory().setDieUsed(false);
        turnCounter++;
        if(turnCounter == turn.length) {
            roundTrack.addDice(draftPool.getInDraft());
            draftPool.removeAllDice();
            actingPlayer.getActionHistory().deleteRoundHistory();
            turnCounter=0;
            roundState= RoundState.FINISHED;
        }
        else roundState= RoundState.RUNNING;
    }
    //TODO
    // aggiungere controlli e aggiornamenti di "second die/turn"

    public void pullDice(){

        Model model = singletonModel();

        int contStandby=0;
        int contDice=0;
        DieInt die=null;

        for(int j=0; j<model.getPlayerList().size(); j++)
            if(model.getPlayerList().get(j).getPlayerState()==STANDBY)
                contStandby=contStandby+1;

        contDice=model.getPlayerList().size()-contStandby;

        for(int i=0; i<contDice; i++) {
            die=model.getBag().draw();
            die.roll();
            model.getDraftPool().addDie(die);
            die=model.getBag().draw();
            die.roll();
            model.getDraftPool().addDie(die);

        }
        die=model.getBag().draw();
        die.roll();
        model.getDraftPool().addDie(die);



    }

    public PlayerZone getCurrentPlayer() { return currentPlayer; }

    public RoundState getRoundState() {
        return roundState;
    }

    public int getTurnCounter() {
        return turnCounter;
    }

}