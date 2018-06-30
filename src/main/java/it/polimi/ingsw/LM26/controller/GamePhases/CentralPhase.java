package it.polimi.ingsw.LM26.controller.GamePhases;

import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DraftPool;
import it.polimi.ingsw.LM26.model.PlayArea.roundTrack.RoundTrackInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class CentralPhase implements PhaseInt{

    private RoundTrackInt roundTrack;

    private final int nrounds=10;

    private ArrayList<Round> rounds;

    private Round round;

    private Boolean allInStandBy=false;

    private int[] turn; //stabilisce l'ordine dei giocatori all'interno del turno, es "12344321", "123321"..

    private ArrayList<PlayerZone> playerList;

    public CentralPhase(ArrayList<PlayerZone> playerZones) {

        Model model = singletonModel();

        this.roundTrack=model.getRoundTrackInt();

        this.round= new Round(roundTrack, playerZones, nrounds);

        rounds= new ArrayList<Round>();

        rounds.add(round);

        playerList= new ArrayList<PlayerZone>();

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

    @Override
    public int[] getTurn() {
        return turn;
    }

    @Override
    public PlayerZone getWinner() {

        throw new UnsupportedOperationException("Not supported here");
    }

    //questo metodo si chiama da dopo il secondo round fino a dopo il decimo (dove verrà creato il prossimo stato)

    @Override
    public void nextRound(Round round, Game game){

        if(rounds.size()<nrounds && round.getRoundState() == RoundState.FINISHED){

            this.round=new Round(roundTrack, playerList, nrounds);

            this.round.setCentralPhase(this);

            rounds.add(round);
        }

        else if(rounds.size() == nrounds || allInStandBy) {

            System.out.println("CentralPhase: creating finalPhase ");

            game.setPhase(new FinalPhase());
        }
    }

    public void setAllInStandBy(Boolean allInStandBy) {

        this.allInStandBy = allInStandBy;
    }

    @Override
    public Round getCurrentRound() {

        return round;
    }

    @Override
    public void doAction(Game game, ArrayList<PlayerZone> playerList) {

        throw new UnsupportedOperationException("Not supported here");
    }
}
