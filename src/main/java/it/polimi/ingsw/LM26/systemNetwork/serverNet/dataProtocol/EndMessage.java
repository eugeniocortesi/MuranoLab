package it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol;

import com.google.gson.Gson;
import it.polimi.ingsw.LM26.observers.serverController.VisitorInt;

/**
 * EndMessage class
 * @author Chiara Criscuolo
 * It contains for each player his score
 */

public class EndMessage extends ClassMessage {

    private String endId;

    private String username;

    private int score;

    private String winner;

    private int scoreWinner;

    /**
     * Constructor
     * @param endId id for the parse "endGame"
     * @param username username the player
     * @param score points of the player
     * @param winner username of the winner
     * @param scoreWinner points of the winner
     */

    public EndMessage(String endId, String username, int score, String winner, int scoreWinner) {
        this.endId = endId;
        this.username = username;
        this.score = score;
        this.winner = winner;
        this.scoreWinner = scoreWinner;
    }

    public String getUsername(){
        return username;
    }

    public String getWinner() {
        return winner;
    }

    public int getScore() {
        return score;
    }

    public int getScoreWinner() {
        return scoreWinner;
    }

    /**
     * Method that return from a string with json the EndMessage
     * @param protocolJson string to deserialize
     * @return EndMessage
     */

    static public EndMessage deserializeEndMessage(String protocolJson){
        Gson gson = new Gson();

        return gson.fromJson(protocolJson, EndMessage.class);
    }

    /**
     * Not used in this implementation
     * @param visitorInt instance of visitor
     */

    @Override
    public void accept(VisitorInt visitorInt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
