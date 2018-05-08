package it.polimi.ingsw.LM26.GamePhases;

import it.polimi.ingsw.LM26.PlayArea.DraftPool;
import it.polimi.ingsw.LM26.PlayArea.RoundTrack;
import it.polimi.ingsw.LM26.PublicPlayerZone.PlayerZone;
import java.util.ArrayList;

public class CentralPhase implements PhaseInt{

    private RoundTrack roundTrack;

    private DraftPool draftPool;

    private final int nrounds=10;

    private Round round;

    private static int[] turn;

    private ArrayList<PlayerZone> playerList;

    public CentralPhase() {
        roundTrack= new RoundTrack();
        draftPool= new DraftPool();
        turn=setOrder(playerList.size());

    }

    public int[] setOrder(int nplayers) {
        for(int i=0; i<nplayers; i++) turn[i]=i+1;
        for(int i=nplayers, j=nplayers; j>0; i++, j--) turn[i]=j;
        return turn;
    }

    public void doAction (Game game, ArrayList<PlayerZone> playerList){
        for(int i=0; i<nrounds; i++){
            while(playerList.size()>1){
                round= new Round(roundTrack, playerList, turn, draftPool);
                //aggiungere azione del round (da coordinare con controller)
            }
        }
        if (playerList.size()==1){
            game.setPhase(new FinalPhase());
        }
        else game.setPhase(new ScorePhase());
    }
}
