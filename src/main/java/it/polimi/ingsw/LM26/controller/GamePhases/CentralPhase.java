package it.polimi.ingsw.LM26.controller.GamePhases;

import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DraftPool;
import it.polimi.ingsw.LM26.model.PlayArea.roundTrack.RoundTrackInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class CentralPhase implements PhaseInt{

    private RoundTrackInt roundTrack;

    private DraftPool draftPool;

    private final int nrounds=10;

    private ArrayList<Round> rounds;

    private Round round;

    private int[] turn; //stabilisce l'ordine dei giocatori all'interno del turno, es "12344321", "123321"..

    private ArrayList<PlayerZone> playerList;

    public CentralPhase(ArrayList<PlayerZone> playerZones) {

        Model model = singletonModel();

        this.roundTrack=model.getRoundTrackInt();

        this.round= new Round(roundTrack, playerZones, nrounds);

        rounds= new ArrayList<Round>();

        rounds.add(round);

        playerList= new ArrayList<PlayerZone>();

        /*roundTrack= new RoundTrack();
        model.setRoundTrackInt(roundTrack);

        draftPool= new DraftPool();
        model.setDraftPool(draftPool);*/

        playerList.addAll(playerZones);

        turn=setOrder(playerList.size());

    }

    //inizializza turn[]
    public int[] setOrder(int nplayers) {
        turn = new int[2*nplayers];
        for(int i=0; i<nplayers; i++) turn[i]=i+1;
        for(int i=nplayers, j=nplayers; j>0; i++, j--) turn[i]=j;
        return turn;
    }

    public int[] getTurn() {
        return turn;
    }

    //questo metodo si chiama da dopo il secondo round fino a dopo il decimo (dove verrà creato il prossimo stato)
    public void nextRound(Round round, Game game){
        if(rounds.size()<nrounds && round.getRoundState() == RoundState.FINISHED){
            this.round=new Round(roundTrack, playerList, nrounds);
            rounds.add(round);
        }
        else if(rounds.size() == nrounds) game.setPhase(new ScorePhase());
    }

    public ArrayList<Round> getRounds() {
        return rounds;
    }

    public Round getCurrentRound() { return round; }

    public void doAction(Game game, ArrayList<PlayerZone> playerList) {

    }

    public void doAction( ArrayList<PlayerZone> playerList) {

    }


}
