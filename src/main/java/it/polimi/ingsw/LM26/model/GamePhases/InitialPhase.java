package it.polimi.ingsw.LM26.model.GamePhases;

import it.polimi.ingsw.LM26.model.Cards.*;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowFramePlayerBoard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.Color;
import it.polimi.ingsw.LM26.model.PlayArea.OnBoardCards;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.ScoreMarker;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.Token;
import it.polimi.ingsw.LM26.model.Serialization.Decks;
import java.util.*;
import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class InitialPhase implements PhaseInt {

    private ArrayList<PlayerZone> playerList;
    private ObjectivePublicCard objectivePublicCard;
    private Random random =new Random();
    private OnBoardCards onBoardCards;
    private Decks decks;
    private final int cardsOnBoardsize=3; //se si ha tempo sarebbe meglio mettere questo limite nella "OnBoardCards"
    //e quello di 10 nella RoundTrack (che invece è nella classe Round)

    private int index;

    private int cardId; //sostituisce temporaneamente

    public int getCardsOnBoardsize() {
        return cardsOnBoardsize;
    }

    public InitialPhase(ArrayList<PlayerZone> playerList, Decks decks, OnBoardCards onBoardCards) {
        this.playerList = playerList;
        this.decks = decks;
        this.onBoardCards = onBoardCards;

    }

    /**
     * this methods assigns to each player his coloured score marker and window frame board, according to his ID
     * @param playerList
     */

    public void setScoreMarkerAndWindowFrame(ArrayList<PlayerZone> playerList,  Decks decks ){
        playerList.get(0).setPlayerBoard(decks.getWindowFramePlayerBoardDeck().get(0));
        playerList.get(0).setScoreMarker(new ScoreMarker(Color.ANSI_RED));
        playerList.get(1).setPlayerBoard(decks.getWindowFramePlayerBoardDeck().get(1));
        playerList.get(1).setScoreMarker(new ScoreMarker(Color.ANSI_GREEN));
        if(playerList.size()>2){
            playerList.get(2).setPlayerBoard(decks.getWindowFramePlayerBoardDeck().get(2));
            playerList.get(2).setScoreMarker(new ScoreMarker(Color.ANSI_BLUE));
            if(playerList.size()==4){
                playerList.get(3).setPlayerBoard(decks.getWindowFramePlayerBoardDeck().get(3));
                playerList.get(3).setScoreMarker(new ScoreMarker(Color.ANSI_PURPLE));
            }
        }
    }



    /*public void setScoreMarkerAndWindowFrame(ArrayList<PlayerZone> playerList){
        playerList.get(0).setPlayerBoard(new WindowFramePlayerBoard(0, Color.ANSI_RED));
        playerList.get(0).setScoreMarker(new ScoreMarker(Color.ANSI_RED));
        playerList.get(1).setPlayerBoard(new WindowFramePlayerBoard(1, Color.ANSI_GREEN));
        playerList.get(1).setScoreMarker(new ScoreMarker(Color.ANSI_GREEN));
        if(playerList.size()>2){
            playerList.get(2).setPlayerBoard(new WindowFramePlayerBoard(2, Color.ANSI_BLUE));
            playerList.get(2).setScoreMarker(new ScoreMarker(Color.ANSI_BLUE));
            if(playerList.size()==4){
                playerList.get(3).setPlayerBoard(new WindowFramePlayerBoard(3, Color.ANSI_PURPLE));
                playerList.get(3).setScoreMarker(new ScoreMarker(Color.ANSI_PURPLE));
            }
        }
    }*/

    //distribuisce i token a tutti i giocatori in base alla loro windowPatternCard
    public void setTokens(ArrayList<PlayerZone> playerZones){
        for(PlayerZone i : playerZones){
            Token token= new Token(i.getWindowPatternCard().getToken());
            i.setToken(token);
        }

    }

    public void setWindowPattern(Decks decks, ArrayList<PlayerZone> playerList){

        ArrayList<WindowPatternCard> temp = new ArrayList<WindowPatternCard>();
        ArrayList<WindowPatternCard> four = new ArrayList<WindowPatternCard>();

        temp.addAll(decks.getWindowPatternCardDeck());

        int count= temp.size();


        for(int i=0; i<playerList.size(); i++) {

            for(int j=0; j<4; j++) {

                Random rand = new Random();
                int index = rand.nextInt(count);
                four.add(temp.get(index));
                temp.remove(index);
                count=temp.size();
            }
            //TODO
            //ask te server which of the fpublic WindowPatternCard askWhichCard(ArrayList<WindowPatterCard> four);our cards is choosen by th player
            //
            //playerList.get(i).setWindowPatternCard(askWhichCard(four));
            //TEMPORANEAMENTE
            playerList.get(i).setWindowPatternCard(temp.get(i));
            playerList.get(i).getPlayerBoard().insertPatternIntoBoard(playerList.get(i).getWindowPatternCard().getWindowPatter());
            for(int k=0; k<4; k++)four.remove(0);
        }


    }

    public void setPublicCards(OnBoardCards onBoardCards, Decks decks){
        ArrayList<ObjectivePublicCard> publicCardsOnBoard= new ArrayList<ObjectivePublicCard>();
        while(publicCardsOnBoard.size() <cardsOnBoardsize){
            index=random.nextInt(decks.getObjectivePublicCardDeck().size()-1);
            objectivePublicCard = decks.getObjectivePublicCardDeck().get(index);
            if(!objectivePublicCard.isInUse()){
                objectivePublicCard.setInUse(true);
                publicCardsOnBoard.add(objectivePublicCard);
            }
        }
        onBoardCards.setObjectivePublicCardList(publicCardsOnBoard);
        onBoardCards.setToolCardList(decks.getToolCardDeck());
    }

    /*public ArrayList<ObjectivePrivateCard> setPrivateCard(ArrayList<PlayerZone> playerList, Decks decks){
        ArrayList<ObjectivePrivateCard>
    }*/
    //questo metodo va chiamato dopo aver assegnato la windowPatternCard
    public void doAction(Game game, ArrayList<PlayerZone> playerList) {
        setScoreMarkerAndWindowFrame(playerList, decks);
        setTokens(playerList);
        setPublicCards(onBoardCards, decks);
        setWindowPattern(decks, playerList);
        //TODO
        //private cards..
        game.setPhase(new CentralPhase(playerList));

    }

    public void doAction(ArrayList<PlayerZone> playerList) {

    }
}
