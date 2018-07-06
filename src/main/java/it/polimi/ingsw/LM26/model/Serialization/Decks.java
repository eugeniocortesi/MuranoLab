package it.polimi.ingsw.LM26.model.Serialization;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.LM26.Main;
import it.polimi.ingsw.LM26.fileConfiguration.RuntimeTypeAdapterFactory1;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCards.ObjectivePublicCard;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCards.PublicCardEffects.*;
import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.ToolCardInt;
import it.polimi.ingsw.LM26.controller.ToolCardsDecorator.*;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowFramePlayerBoard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.PlayArea.Color.*;

public class Decks implements Serializable {

    private ArrayList<ObjectivePublicCard> ObjectivePublicCardDeck;

    private ArrayList<ObjectivePrivateCard> ObjectivePrivateCardDeck;

    private ArrayList<WindowPatternCard> WindowPatternCardDeck;

    private ArrayList<WindowFramePlayerBoard> WindowFramePlayerBoardDeck;

    private ArrayList<ToolCardInt> ToolCardDeck;


    public ArrayList<ObjectivePublicCard> getObjectivePublicCardDeck() {
        return ObjectivePublicCardDeck;
    }

    public ArrayList<ToolCardInt> getToolCardDeck() {
        return ToolCardDeck;
    }

    public ArrayList<ObjectivePrivateCard> getObjectivePrivateCardDeck() {
        return ObjectivePrivateCardDeck;
    }

    public ArrayList<WindowPatternCard> getWindowPatternCardDeck() {
        return WindowPatternCardDeck;
    }

    public ArrayList<WindowFramePlayerBoard> getWindowFramePlayerBoardDeck() {
        return WindowFramePlayerBoardDeck;
    }

    public void setup() {

        WindowFramePlayerBoard frame1 = new WindowFramePlayerBoard(31, ANSI_RED);
        WindowFramePlayerBoard frame2 = new WindowFramePlayerBoard(32, ANSI_GREEN);
        WindowFramePlayerBoard frame3 = new WindowFramePlayerBoard(33, ANSI_BLUE);
        WindowFramePlayerBoard frame4 = new WindowFramePlayerBoard(34, ANSI_PURPLE);

        ToolCardInt tool1 = new ChangeDieValue1( new ToolCard(1));
        ToolCardInt tool2 = new MoveWithNoColorRestriction2( new ToolCard(2));
        ToolCardInt tool3 = new MoveWithNoValueRestriction3( new ToolCard(3));
        ToolCardInt tool4 = new MoveTwoDice4( new ToolCard(4));
        ToolCardInt tool5 = new ChangeDieFromDraftToRoundTrack5( new ToolCard(5));
        ToolCardInt tool6 = new RollAgainADie6( new ToolCard(6));
        ToolCardInt tool7 = new RollAllDraftDice7( new ToolCard(7));
        ToolCardInt tool8 = new DrawOneMoreDie8( new ToolCard(8));
        ToolCardInt tool9 = new PlaceWithNotInProximities9( new ToolCard(9));
        ToolCardInt tool10 = new RollToTheOppositeFace10( new ToolCard(10));
        ToolCardInt tool11 = new ChangeDieWithTheBag11( new ToolCard(11));
        ToolCardInt tool12 = new MoveTwoDiceWithSameColor12( new ToolCard(12));

        ArrayList<WindowFramePlayerBoard> frames = new ArrayList<WindowFramePlayerBoard>();
        ArrayList<ToolCardInt> tools = new ArrayList<ToolCardInt>();

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


        ToolCardDeck = tools;

        WindowFramePlayerBoardDeck = frames;

    }





    public void serialize() {

       // FileReader fr1 = null, fr2 = null, fr3=null;
        BufferedReader br1 = null, br2 = null, br3=null;

        try {
            /*fr1 = new FileReader("src/main/resources/PrivateCards");
            br1 = new BufferedReader(fr1);*/

            InputStream stream = Main.class.getResourceAsStream("PrivateCards");
            br1 = new BufferedReader(new InputStreamReader(stream));



            Type Private = new TypeToken<ArrayList<ObjectivePrivateCard>>() { }.getType();
            ArrayList<ObjectivePrivateCard> privateCards = new ArrayList<ObjectivePrivateCard>();

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

            privateCards = gson.fromJson(br1, Private);

            ObjectivePrivateCardDeck=privateCards;

        } finally {
            try {
                if (br1 != null)
                    br1.close();

                //if (fr1 != null)
                //    fr1.close();
            } catch (IOException e) {
            }
        }


        try {
            //fr2 = new FileReader("src/main/resources/PublicCards");
            InputStream stream = Main.class.getResourceAsStream("PublicCards");
            //br2 = new BufferedReader(fr2);
            br2 = new BufferedReader(new InputStreamReader(stream));

            Type Public = new TypeToken<ArrayList<ObjectivePublicCard>>() {
            }.getType();
            ArrayList<ObjectivePublicCard> publicCards = new ArrayList<ObjectivePublicCard>();

            RuntimeTypeAdapterFactory1<Effect> runtimeTypeAdapterFactory1 = RuntimeTypeAdapterFactory1
                    .of(Effect.class, "type")
                    .registerSubtype(DifferentColorShadeOnRowColomn.class)
                    .registerSubtype(DifferentColorShade.class)
                    .registerSubtype(Shades.class)
                    .registerSubtype(ColoredDiagonals.class);

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .registerTypeAdapterFactory(runtimeTypeAdapterFactory1)
                    .create();

            publicCards = gson.fromJson(br2, Public);

            ObjectivePublicCardDeck=publicCards;




        } finally {
            try {
                if (br2 != null)
                    br2.close();

               // if (fr2 != null)
                //    fr2.close();
            } catch (IOException e) {
            }


        }


        try {
            //fr3 = new FileReader("src/main/resources/WindowCards");
           // br3 = new BufferedReader(fr3);
            InputStream stream = Main.class.getResourceAsStream("WindowCards");
            br3 = new BufferedReader(new InputStreamReader(stream));


            Type Window = new TypeToken<ArrayList<WindowPatternCard>>() { }.getType();
            ArrayList<WindowPatternCard> WindowCards = new ArrayList<WindowPatternCard>();

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

            WindowCards = gson.fromJson(br3, Window);

          
            WindowPatternCardDeck=WindowCards;

            for(int i=0; i<WindowPatternCardDeck.size(); i++) WindowPatternCardDeck.get(i).createPattern();

        } finally {
            try {
                if (br3 != null)
                    br3.close();

                //if (fr3 != null)
                //    fr3.close();
            } catch (IOException e) {
            }
        }

    }

}
