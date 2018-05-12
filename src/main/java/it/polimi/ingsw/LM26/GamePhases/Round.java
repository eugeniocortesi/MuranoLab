package it.polimi.ingsw.LM26.GamePhases;

import it.polimi.ingsw.LM26.PlayArea.*;
import it.polimi.ingsw.LM26.PublicPlayerZone.PlayerState;
import it.polimi.ingsw.LM26.PublicPlayerZone.PlayerZone;
import java.util.ArrayList;

public class Round {

    private static final int[] t = {1, 2, 3, 4};

    private static int turnCounter=0;

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

    //nextPlayer va usato dopo endAction, quando il contatore è già incrementato
    public PlayerZone nextPlayer(ArrayList<PlayerZone> player, int[] turn) throws IllegalArgumentException {
      for(PlayerZone i : player){
          if(i.getNumber()==turn[turnCounter]) {
              i.setPlayerState(PlayerState.BEGINNING);
              return i;
          }
      }
      throw new IllegalArgumentException("no player has the current turn");
    }


    //passa il turno al successivo, se è finito turno globale mette dadi nella casella della round track e ritorna FINISHED
    public RoundState endAction(int[] turn, RoundTrackInt roundTrack, DraftPool draftPool, PlayerZone actingPlayer) {
        actingPlayer.setPlayerState(PlayerState.ENDING);
        turnCounter++;
        int i;
        i=turnCounter;
        if(turnCounter == turn.length) {
            roundTrack.addDice(draftPool.getInDraft());
            turnCounter=0;
            return RoundState.FINISHED;
        }
        return RoundState.RUNNING;
    }

}