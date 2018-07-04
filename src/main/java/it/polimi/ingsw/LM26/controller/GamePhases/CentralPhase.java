package it.polimi.ingsw.LM26.controller.GamePhases;

import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DraftPool;
import it.polimi.ingsw.LM26.model.PlayArea.roundTrack.RoundTrackInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.ScoreMarker;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class CentralPhase implements PhaseInt{

    private final int nrounds=10;

    private ArrayList<Round> rounds;

    private Round round;

    private Boolean onePlayer=false;

    private int[] turn; //stabilisce l'ordine dei giocatori all'interno del turno, es "12344321", "123321"..

    Model model;

    public CentralPhase() {

        this.model = singletonModel();

        turn=setOrder(model.getPlayerList().size());

        this.round= new Round( turn);

        rounds= new ArrayList<Round>();

        rounds.add(round);
    }

    //inizializza turn[]

    public int[] setOrder(int nplayers) {

        turn = new int[2*nplayers];

        for(int i=0; i<nplayers; i++) turn[i]=model.getPlayer(i).getNumber();

        for(int i=nplayers, j=nplayers-1; j>=0; i++, j--) turn[i]=model.getPlayer(j).getNumber();

        for(int i=0; i<turn.length; i++)

            System.out.print(turn[i]);

        System.out.println();

        return turn;
    }

    public void resetOrder(int nplayers){

        int last = model.getPlayer(nplayers-1).getNumber();

        model.getPlayer(nplayers-1).setNumberPlayer(model.getPlayer(0).getNumber());

        for(int i=0; i<model.getPlayerList().size()-1; i++)

            model.getPlayer(i).setNumberPlayer(model.getPlayer(i+1).getNumber());

        model.getPlayer(nplayers-2).setNumberPlayer(last);

        turn = setOrder(nplayers);
    }

    @Override
    public PlayerZone getWinner() {

        throw new UnsupportedOperationException("Not supported here");
    }

    //questo metodo si chiama da dopo il secondo round fino a dopo il decimo (dove verrà creato il prossimo stato)

    @Override
    public void nextRound(Round round, Game game){

        if(rounds.size() == nrounds || onePlayer) {

            System.out.println("CentralPhase: creating finalPhase ");

            game.setPhase(new FinalPhase());
        }

        else  if(rounds.size()<nrounds && round.getRoundState() == RoundState.FINISHED){

            resetOrder(model.getPlayerList().size());

            this.round=new Round(turn);

            rounds.add(round);
        }
    }

    @Override
    public int[] getTurn() {

        return turn;
    }

    @Override
    public void endGame() {

        System.out.println("ONLY ONE PLAYER REMAINED");

        onePlayer=true;
    }

    @Override
    public int getNrounds() {

        return nrounds;
    }

    @Override
    public boolean getOnePlayer() {

        return onePlayer;
    }

    @Override
    public int getRoundNumber() {

        return rounds.size();
    }

    @Override
    public Round getCurrentRound() {

        return round;
    }

    @Override
    public void doAction(Game game) {

        throw new UnsupportedOperationException("Not supported here");
    }



}
