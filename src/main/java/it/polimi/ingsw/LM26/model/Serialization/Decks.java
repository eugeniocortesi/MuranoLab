package it.polimi.ingsw.LM26.model.Serialization;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.LM26.Main;
import it.polimi.ingsw.LM26.controller.ToolCardsDecorator.*;
import it.polimi.ingsw.LM26.fileConfiguration.RuntimeTypeAdapterFactory1;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCards.ObjectivePublicCard;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCards.PublicCardEffects.*;
import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.ToolCardInt;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowFramePlayerBoard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static it.polimi.ingsw.LM26.model.PlayArea.Color.*;

/**
 * Decks class
 * @author Eugenio Cortesi
 * class that deserialize (read from files) the cards for the game, and stores them in decks
 */

public class Decks implements Serializable {

    private ArrayList<ObjectivePublicCard> objectivePublicCards;

    private ArrayList<ObjectivePrivateCard> objectivePrivateCards;

    private ArrayList<WindowPatternCard> windowPatternCardDeck;

    private ArrayList<WindowFramePlayerBoard> windowFramePlayerBoardDeck;

    private ArrayList<ToolCardInt> toolCardDeck;

    private transient static final Logger LOGGER = Logger.getLogger(MoveTwoDiceWithSameColor12.class.getName());

    public Decks() {

        LOGGER.setLevel(Level.ALL);
    }

    /**
     * method creates:
     * -five WindowFramePlayersBoards
     * -twelve ToolCard decorated
     */

    public void setup() {

        WindowFramePlayerBoard frame1 = new WindowFramePlayerBoard(31, ANSI_RED);

        WindowFramePlayerBoard frame2 = new WindowFramePlayerBoard(32, ANSI_GREEN);

        WindowFramePlayerBoard frame3 = new WindowFramePlayerBoard(33, ANSI_BLUE);

        WindowFramePlayerBoard frame4 = new WindowFramePlayerBoard(34, ANSI_PURPLE);

        ToolCardInt tool1 = new ChangeDieValue1(new ToolCard(1));

        ToolCardInt tool2 = new MoveWithNoColorRestriction2(new ToolCard(2));

        ToolCardInt tool3 = new MoveWithNoValueRestriction3(new ToolCard(3));

        ToolCardInt tool4 = new MoveTwoDice4(new ToolCard(4));

        ToolCardInt tool5 = new ChangeDieFromDraftToRoundTrack5(new ToolCard(5));

        ToolCardInt tool6 = new RollAgainADie6(new ToolCard(6));

        ToolCardInt tool7 = new RollAllDraftDice7(new ToolCard(7));

        ToolCardInt tool8 = new DrawOneMoreDie8(new ToolCard(8));

        ToolCardInt tool9 = new PlaceWithNotInProximities9(new ToolCard(9));

        ToolCardInt tool10 = new RollToTheOppositeFace10(new ToolCard(10));

        ToolCardInt tool11 = new ChangeDieWithTheBag11(new ToolCard(11));

        ToolCardInt tool12 = new MoveTwoDiceWithSameColor12(new ToolCard(12));

        ArrayList<WindowFramePlayerBoard> frames = new ArrayList<>();

        ArrayList<ToolCardInt> tools = new ArrayList<>();

        frames.add(frame1);

        frames.add(frame2);

        frames.add(frame3);

        frames.add(frame4);

        tools.add(tool1);

        tools.add(tool2);

        tools.add(tool3);

        tools.add(tool4);
        tools.add(tool5);


        tools.add(tool6);

        tools.add(tool7);

        tools.add(tool8);

        tools.add(tool9);

        tools.add(tool10);

        tools.add(tool11);

        tools.add(tool12);

        toolCardDeck = tools;

        windowFramePlayerBoardDeck = frames;
    }


    /**
     * method reads from file and creates decks for:
     * -WindowPatterCards
     * -ObjectivePrivateCards
     * -ObjectivePublicCards
     */

    public void serialize() {

        BufferedReader br1 = null;

        BufferedReader br2 = null;

        BufferedReader br3 = null;

        try {

            InputStream stream = Main.class.getResourceAsStream("PrivateCards");

            br1 = new BufferedReader(new InputStreamReader(stream));

            Type privateType = new TypeToken<ArrayList<ObjectivePrivateCard>>() {
            }.getType();

            ArrayList<ObjectivePrivateCard> privateCards = new ArrayList<>();

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            privateCards = gson.fromJson(br1, privateType);

            objectivePrivateCards = privateCards;
        } finally {

            try {

                if (br1 != null) br1.close();
            } catch (IOException e) {

                LOGGER.log(Level.INFO, "Impossible to read from file, reset and try again");
            }
        }

        try {

            InputStream stream = Main.class.getResourceAsStream("PublicCards");

            br2 = new BufferedReader(new InputStreamReader(stream));

            Type publicType = new TypeToken<ArrayList<ObjectivePublicCard>>() {
            }.getType();

            ArrayList<ObjectivePublicCard> publicCards = new ArrayList<>();

            RuntimeTypeAdapterFactory1<Effect> runtimeTypeAdapterFactory1 = RuntimeTypeAdapterFactory1
                    .of(Effect.class, "type")
                    .registerSubtype(DifferentColorShadeOnRowColomn.class)
                    .registerSubtype(DifferentColorShade.class)
                    .registerSubtype(Shades.class)
                    .registerSubtype(ColoredDiagonals.class);

            Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(runtimeTypeAdapterFactory1).create();

            publicCards = gson.fromJson(br2, publicType);

            objectivePublicCards = publicCards;
        } finally {

            try {

                if (br2 != null) br2.close();
            } catch (IOException e) {

                LOGGER.log(Level.INFO, "Impossible to read from file, reset and try again");
            }
        }

        try {

            InputStream stream = Main.class.getResourceAsStream("WindowCards");

            br3 = new BufferedReader(new InputStreamReader(stream));

            Type window = new TypeToken<ArrayList<WindowPatternCard>>() {
            }.getType();

            ArrayList<WindowPatternCard> windowCards = new ArrayList<>();

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            windowCards = gson.fromJson(br3, window);

            windowPatternCardDeck = windowCards;

            for (WindowPatternCard aWindowPatternCardDeck : windowPatternCardDeck)

                aWindowPatternCardDeck.createPattern();
        } finally {

            try {

                if (br3 != null)
                    br3.close();
            } catch (IOException e) {

                LOGGER.log(Level.INFO, "Impossible to read from file, reset and try again");
            }
        }
    }

    public ArrayList<ObjectivePublicCard> getObjectivePublicCardDeck() {

        return objectivePublicCards;
    }

    public ArrayList<ToolCardInt> getToolCardDeck() {

        return toolCardDeck;
    }

    public ArrayList<ObjectivePrivateCard> getObjectivePrivateCardDeck() {

        return objectivePrivateCards;
    }

    public ArrayList<WindowPatternCard> getWindowPatternCardDeck() {

        return windowPatternCardDeck;
    }

    public ArrayList<WindowFramePlayerBoard> getWindowFramePlayerBoardDeck() {

        return windowFramePlayerBoardDeck;
    }


}
