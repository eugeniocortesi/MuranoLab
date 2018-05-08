package it.polimi.ingsw.LM26.GamePhases;

import it.polimi.ingsw.LM26.PlayArea.*;
import it.polimi.ingsw.LM26.PublicPlayerZone.PlayerState;
import it.polimi.ingsw.LM26.PublicPlayerZone.PlayerZone;
import java.util.ArrayList;

public class Round {

    private static final int[] t = {1, 2, 3, 4};

    private int turnCounter = 0;

    public Round(RoundTrack rTrack, ArrayList<PlayerZone> pZone, int[] turn, DraftPool dPool) {
        this.assignTurn(rTrack, pZone);
    }


    //assegna l'ordine con cui si gioca all'interno di un turno
    public void assignTurn(RoundTrack roundTrack, ArrayList<PlayerZone> playerList) { //roundtrack passata dalla centralPhase
        for (int i = 0; i < playerList.size(); i++) {
            playerList.get(i).setNumberPlayer(t[(i + roundTrack.getCurrentTurn() - 1) %playerList.size()]); //mi aspetto che getcurrentturn dia numeri da 1 a 10
        }
    }

    public PlayerZone nextPlayer(ArrayList<PlayerZone> player, int[] turn, int tCounter) throws IllegalArgumentException { //nextPlayer va usato dopo endAction, quando il contatore è già incrementato
      for(PlayerZone i : player){
          if(i.getNumber()==tCounter) {
              i.setPlayerState(PlayerState.BEGINNING);
              return i;
          }
      }
      throw new IllegalArgumentException("no player has the current turn");
    }

    public void useToolCard(){

    }

    //passa il turno al successivo, se è finito turno globale mette dadi nella casella della round track e ritorna FINISHED
    public PhaseState endAction(int[] turn, RoundTrack roundTrack, DraftPool draftPool, PlayerZone actingPlayer) {
        actingPlayer.setPlayerState(PlayerState.ENDING);
        turnCounter++;
        int i;
        i=roundTrack.getCurrentTurn();
        if(turnCounter == turn.length) {
            roundTrack.addDice(draftPool.getInDraft());
            turnCounter=0;
            return PhaseState.FINISHED;
        }
        return PhaseState.RUNNING;
    }

    //implementare metodo che prende l'ultimo giocatore per il pareggio

}