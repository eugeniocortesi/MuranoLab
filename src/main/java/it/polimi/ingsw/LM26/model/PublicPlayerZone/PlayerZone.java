package it.polimi.ingsw.LM26.model.PublicPlayerZone;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowFramePlayerBoard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;

public class PlayerZone extends PlayerZoneInt {

    private String namePlayer;

    private int numberPlayer;

    private int IDPlayer;

    private WindowFramePlayerBoard playerBoard;

    private WindowPatternCard windowPatternCard;

    private PlayerState playerState;

    private Token token;

    private ScoreMarker scoreMarker;

    private int privatePoints;

    private ActionHistory actionHistory;

    public PlayerZone(){

    }

    public PlayerZone(String namePlayer, int IDPlayer) {

        this.namePlayer = namePlayer;
        this.IDPlayer = IDPlayer;
        this.playerState = PlayerState.ENDING;
        this.actionHistory = new ActionHistory();
        this.playerBoard = null;
        this.windowPatternCard = null;
        this.token = null;
        this.scoreMarker = null;
        this.privatePoints = 0;
        this.typePlayerZone = "PlayerZone";

    }

    public Token getToken() { return token; }

    public int getIDPlayer() { return IDPlayer; }

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

    public void setPlayerBoard(WindowFramePlayerBoard playerBoard) {
        this.playerBoard = playerBoard;
    }

    public void setWindowPatternCard(WindowPatternCard windowPatternCard) { this.windowPatternCard = windowPatternCard; }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }

    public ActionHistory getActionHistory() { return actionHistory; }

    @Override
    public void rewrite() {

        this.typePlayerZone = "PlayerZone";

        if(windowPatternCard!= null){
            windowPatternCard.rewrite();
        }
        if (playerBoard!= null && playerBoard.getBoardMatrix()!= null) {

            for(int i=0; i<4; i++) {
                for (int j = 0; j < 5; j++) {
                    if (playerBoard.getBoardMatrix()[i][j].getDie()!=null)
                        playerBoard.getBoardMatrix()[i][j].getDie().rewrite();
                }
            }
        }

    }
}
