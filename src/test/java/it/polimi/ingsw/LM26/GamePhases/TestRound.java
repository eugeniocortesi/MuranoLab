package it.polimi.ingsw.LM26.GamePhases;

import it.polimi.ingsw.LM26.PlayArea.DieInt;
import it.polimi.ingsw.LM26.PlayArea.RoundTrack;
import it.polimi.ingsw.LM26.PublicPlayerZone.PlayerZone;
import org.junit.Test;

import java.util.ArrayList;

public class TestRound {

    private RoundTrack roundTrack = new RoundTrack();
    private RoundTrack roundTrack2 = new RoundTrack();
    private String name="name";
    private Round round;
    private int nrounds = 10;

    private ArrayList<PlayerZone> playerZones = new ArrayList<PlayerZone>();

    @Test
    public void checkAssignment4Players(){
        for(int i=0; i<4; i++){
            playerZones.add(new PlayerZone(name+"i", i));
        }
        round=new Round(roundTrack, playerZones, nrounds);
        for(int i=0; i<nrounds; i++){
            round.assignTurn(roundTrack, playerZones, nrounds);
            for(int j=0; j<playerZones.size(); j++){
                System.out.println(playerZones.get(j).getNumber());
            }
            System.out.println("\n");
            roundTrack.addDice(new ArrayList<DieInt>());
            roundTrack.update();
        }
    }

    @Test
    public void checkAssignment2and3Players(){
        for(int i=0; i<2; i++){
            playerZones.add(new PlayerZone(name+"i", i));
        }
        round=new Round(roundTrack, playerZones, nrounds);
        for(int i=0; i<nrounds; i++){
            round.assignTurn(roundTrack, playerZones, nrounds);
            for(int j=0; j<playerZones.size(); j++){
                System.out.println(playerZones.get(j).getNumber());
            }
            System.out.println("\n");
            roundTrack.addDice(new ArrayList<DieInt>());
            roundTrack.update();
        }
        playerZones.add(new PlayerZone(name, 2));
        Round round2=new Round(roundTrack, playerZones, nrounds);
        for(int i=0; i<nrounds; i++){
            round2.assignTurn(roundTrack, playerZones, nrounds);
            for(int j=0; j<playerZones.size(); j++){
                System.out.println(playerZones.get(j).getNumber());
            }
            System.out.println("\n");
            roundTrack2.addDice(new ArrayList<DieInt>());
            roundTrack2.update();
        }
    }

    @Test
    public void checkEndActionNextPlayer(){
        
    }
}
