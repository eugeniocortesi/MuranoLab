package it.polimi.ingsw.LM26.PublicPlayerZone;

import it.polimi.ingsw.LM26.Cards.WindowFramePlayerBoard;
import it.polimi.ingsw.LM26.Cards.WindowPatternCard;

public class PlayerZone implements PlayerZoneInt {


        public String namePlayer;

        int numberPlayer;

        public WindowFramePlayerBoard playerBoard;

        public WindowPatternCard windowPatternCard;

        public PlayerState playerState;

        public Token token;

        public ScoreMarker scoreMarker;

        public boolean connected;


    public PlayerZone(String namePlayer, int numberPlayer) {

        this.namePlayer=namePlayer;
        this.numberPlayer=numberPlayer;
        this.playerState=PlayerState.STANDBY;
    }

        //getToken è nella classe Token

        public void setToken(Token token) {
            this.token = token;
        }

        public void setScoreMarker(ScoreMarker scoreMarker) {
        this.scoreMarker = scoreMarker;
        }

        public String getName() {
            return namePlayer;
        }

        public int getNumber() {
            return numberPlayer;
        }

        public WindowFramePlayerBoard getPlayerBoard() {
            return playerBoard;
        }

        public WindowPatternCard getWindowPatternCard() {
            return windowPatternCard;
        }

        public PlayerState getPlayerState() {
            return playerState;
        }

        public boolean isConnected() {
            return connected;
        }

        public void connectPlayer(){
        connected=true;
        }

        //get carta obbiettivo privato
}

