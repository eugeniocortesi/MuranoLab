package it.polimi.ingsw.LM26.model.GamePhases;

import it.polimi.ingsw.LM26.model.PlayArea.*;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerState;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import java.util.ArrayList;

public class Round {

    private static final int[] t = {1, 2, 3, 4};

    private int turnCounter=0;

    private RoundState roundState=RoundState.RUNNING;

    public Round(RoundTrackInt rTrack, ArrayList<PlayerZone> pZone, int nrounds) {
        this.assignTurn(rTrack, pZone, nrounds);
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
        System.out.println("!");
        turnCounter=turnCounter+plStandby;
      for(PlayerZone i : player){
          for(int j=0; j<turnCounter; j++){
              System.out.println("o");
          }
          System.out.println("g");
          if(i.getNumber()==turn[turnCounter]) {
              if(i.getPlayerState()!=PlayerState.STANDBY){
                  i.setPlayerState(PlayerState.BEGINNING);
                  return i;
              }
              else return nextPlayer(player, turn, ++plStandby);
          }
      }
      throw new IllegalArgumentException("no player has the current turn");
    }


    //passa il turno al successivo, se è finito turno globale mette dadi nella casella della round track e ritorna FINISHED
    public void endAction(int[] turn, RoundTrackInt roundTrack, DraftPool draftPool, PlayerZone actingPlayer) {
        actingPlayer.setPlayerState(PlayerState.ENDING);
        turnCounter++;
        if(turnCounter == turn.length) {
            roundTrack.addDice(draftPool.getInDraft());
            turnCounter=0;
            roundState= RoundState.FINISHED;
        }
        else roundState= RoundState.RUNNING;
    }//aggiungere controlli e aggiornamenti di "second die/turn"

    public RoundState getRoundState() {
        return roundState;
    }

    public int getTurnCounter() {
        return turnCounter;
    }
}