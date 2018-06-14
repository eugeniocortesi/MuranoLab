package it.polimi.ingsw.LM26.model.PublicPlayerZone;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowFramePlayerBoard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;

public class PlayerZone implements PlayerZoneInt {

    private String type;

    private  String namePlayer;

    private  int numberPlayer;

    private int IDPlayer;

    private  WindowFramePlayerBoard playerBoard;

    private WindowPatternCard windowPatternCard;

    private PlayerState playerState;

    private Token token;

    private ScoreMarker scoreMarker;

    private int privatePoints;

    private int lastRoundTurn;

    private ActionHistory actionHistory;


    public PlayerZone(String namePlayer, int IDPlayer) {

        this.namePlayer=namePlayer;
        this.IDPlayer=IDPlayer;
        this.playerState=PlayerState.ENDING;
        this.actionHistory = new ActionHistory();
        this.playerBoard= null;
        this.windowPatternCard = null;
        this.token = null;
        this.scoreMarker= null;
        this.privatePoints= 0;
        this.lastRoundTurn= 0;

    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public Token getToken() { return token; }

    public int getIDPlayer() { return IDPlayer; }

    public void setIDPlayer(int IDPlayer) { this.IDPlayer = IDPlayer; }

    public void setToken(Token token) {
            this.token = token;
        }

    public void setScoreMarker(ScoreMarker scoreMarker) {
        this.scoreMarker = scoreMarker;
        }

        public String getName() {
            return namePlayer;
        }

        public int getNumber() { return numberPlayer; }

        public WindowFramePlayerBoard getPlayerBoard() {
            return playerBoard;
        }

        public WindowPatternCard getWindowPatternCard() {
            return windowPatternCard;
        }

        public PlayerState getPlayerState() {
            return playerState;
        }

    public int getPrivatePoints() { return privatePoints; }

        public void setPrivatePoints(int privatePoints) { this.privatePoints = privatePoints; }

        public ScoreMarker getScoreMarker() { return scoreMarker; }

    public void setNumberPlayer(int numberPlayer) { this.numberPlayer = numberPlayer; }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public void setPlayerBoard(WindowFramePlayerBoard playerBoard) {
        this.playerBoard = playerBoard;
    }

    public void setWindowPatternCard(WindowPatternCard windowPatternCard) { this.windowPatternCard = windowPatternCard; }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }

    public int getLastRoundTurn() {
        return lastRoundTurn;
    }

    public void setLastRoundTurn(int lastRoundTurn) {
        this.lastRoundTurn = lastRoundTurn;
    }

    public ActionHistory getActionHistory() { return actionHistory; }

    @Override
    public void rewrite() {

        this.type="PlayerZone";

    }

    //get carta obbiettivo privato
}

