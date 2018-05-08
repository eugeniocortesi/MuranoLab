package it.polimi.ingsw.LM26.PublicPlayerZone;

import it.polimi.ingsw.LM26.Cards.WindowFramePlayerBoard;
import it.polimi.ingsw.LM26.Cards.WindowPatternCard;

public class PlayerZone implements PlayerZoneInt {


    private  String namePlayer;

    private  int numberPlayer;

    private  WindowFramePlayerBoard playerBoard;

    private WindowPatternCard windowPatternCard;

    private PlayerState playerState;

    private Token token;

    private ScoreMarker scoreMarker;

    private boolean connected;

    private int privatePoints;


    public PlayerZone(String namePlayer, int numberPlayer) {

        this.namePlayer=namePlayer;
        this.numberPlayer=numberPlayer;
        this.playerState=PlayerState.STANDBY;
    }

    public Token getToken() {
        return token;
    }

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

    public int getPrivatePoints() {
        return privatePoints;
    }

    public void setPrivatePoints(int privatePoints) {
        this.privatePoints = privatePoints;
    }

    public ScoreMarker getScoreMarker() {
        return scoreMarker;
    }

    //get carta obbiettivo privato
}

