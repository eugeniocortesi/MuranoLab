package it.polimi.ingsw.LM26.model.Serialization;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.LM26.model.Cards.*;
import it.polimi.ingsw.LM26.model.Cards.decorator.*;
import it.polimi.ingsw.LM26.model.Cards.factory.CardCreator;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowFramePlayerBoard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.PlayArea.Color.*;
import static it.polimi.ingsw.LM26.model.Serialization.Elements.elements.*;

public class Decks {

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

    public void setup() throws IOException {


        //carte private
        CardInt private1 = CardCreator.createCard(11, RED);
        CardInt private2 = CardCreator.createCard(12, YELLOW);
        CardInt private3 = CardCreator.createCard(13, GREEN);
        CardInt private4 = CardCreator.createCard(14, BLUE);
        CardInt private5 = CardCreator.createCard(15, VIOLET);

        //carte pubbliche
        //Colore, Riga = true
        //Sfumature, Colonna = false
        CardInt public1 = CardCreator.createCard(21, 6, new DifferentColorShadeOnRowColomn(true, true));
        CardInt public2 = CardCreator.createCard(22, 5, new DifferentColorShadeOnRowColomn(true, false));
        CardInt public3 = CardCreator.createCard(23, 5, new DifferentColorShadeOnRowColomn(false, true));
        CardInt public4 = CardCreator.createCard(24, 4, new DifferentColorShadeOnRowColomn(false, false));
        CardInt public5 = CardCreator.createCard(25, 2, new Shades(LIGHT));
        CardInt public6 = CardCreator.createCard(26, 2, new Shades(MEDIUMS));
        CardInt public7 = CardCreator.createCard(27, 2, new Shades(DARK));
        CardInt public8 = CardCreator.createCard(28, 5, new DifferentColorShade(true));
        CardInt public9 = CardCreator.createCard(29, 4, new DifferentColorShade(false));
        CardInt public10 = CardCreator.createCard(210, new ColoredDiagonals());

        //carte schema

        CardInt window1 = CardCreator.createCard(3, "Sun Catcher", new Matrix(EMPTY, BLUE, LIGHT2, EMPTY, YELLOW,
                EMPTY, MEDIUMS4, EMPTY, RED, EMPTY,
                EMPTY, EMPTY, DARK5, YELLOW, EMPTY,
                GREEN, MEDIUMS3, EMPTY, EMPTY, VIOLET));

        CardInt window2 = CardCreator.createCard(3, "Bellesguard", new Matrix(BLUE, DARK6, EMPTY, EMPTY, YELLOW,
                EMPTY, MEDIUMS3, BLUE, EMPTY, EMPTY,
                EMPTY, DARK5, DARK6, LIGHT2, EMPTY,
                EMPTY, MEDIUMS4, EMPTY, LIGHT1, GREEN));

        CardInt window3 = CardCreator.createCard(3, "Fractal Drops", new Matrix(EMPTY, MEDIUMS4, EMPTY, YELLOW, DARK6,
                RED, EMPTY, LIGHT2, EMPTY, EMPTY,
                EMPTY, EMPTY, RED, VIOLET, LIGHT1,
                BLUE, YELLOW, EMPTY, EMPTY, EMPTY));


        CardInt window4 = CardCreator.createCard(3, "Lux Celestial", new Matrix(EMPTY, EMPTY, RED, DARK5, EMPTY,
                VIOLET, MEDIUMS4, EMPTY, GREEN, MEDIUMS3,
                DARK6, EMPTY, EMPTY, BLUE, EMPTY,
                EMPTY, YELLOW, LIGHT2, EMPTY, EMPTY));


        CardInt window5 = CardCreator.createCard(4, "Kaleidoscopic Dream", new Matrix(YELLOW, BLUE, EMPTY, EMPTY, LIGHT1,
                GREEN, EMPTY, DARK5, EMPTY, MEDIUMS4,
                MEDIUMS3, EMPTY, RED, EMPTY, GREEN,
                LIGHT2, EMPTY, EMPTY, BLUE, YELLOW));

        CardInt window6 = CardCreator.createCard(4, "Chromatic Splendor", new Matrix(EMPTY, EMPTY, GREEN, EMPTY, EMPTY,
                LIGHT2, YELLOW, DARK5, BLUE, LIGHT1,
                EMPTY, RED, MEDIUMS3, VIOLET, EMPTY,
                LIGHT1, EMPTY, DARK6, EMPTY, MEDIUMS4));

        CardInt window7 = CardCreator.createCard(4, "Via Lux", new Matrix(YELLOW, EMPTY, DARK6, EMPTY, EMPTY,
                EMPTY, LIGHT1, DARK5, EMPTY, LIGHT2,
                MEDIUMS3, YELLOW, RED, VIOLET, EMPTY,
                EMPTY, EMPTY, MEDIUMS4, MEDIUMS3, RED));

        CardInt window8 = CardCreator.createCard(4, "Aurora Sagradis", new Matrix(RED, EMPTY, BLUE, EMPTY, YELLOW,
                MEDIUMS4, VIOLET, MEDIUMS3, GREEN, LIGHT2,
                EMPTY, LIGHT1, EMPTY, DARK5, EMPTY,
                EMPTY, EMPTY, DARK6, EMPTY, EMPTY));

        CardInt window9 = CardCreator.createCard(5, "Fulgor del Cielo", new Matrix(EMPTY, BLUE, RED, EMPTY, EMPTY,
                EMPTY, MEDIUMS4, DARK5, EMPTY, BLUE,
                BLUE, LIGHT2, EMPTY, RED, DARK5,
                DARK6, RED, MEDIUMS3, LIGHT1, EMPTY));

        CardInt window10 = CardCreator.createCard(5, "Virtus", new Matrix(MEDIUMS4, EMPTY, LIGHT2, DARK5, GREEN,
                EMPTY, EMPTY, DARK6, GREEN, LIGHT2,
                EMPTY, MEDIUMS3, GREEN, MEDIUMS4, EMPTY,
                DARK5, GREEN, LIGHT1, EMPTY, EMPTY));

        CardInt window11 = CardCreator.createCard(5, "Aurorae Magnificus", new Matrix(DARK5, GREEN, BLUE, VIOLET, LIGHT2,
                VIOLET, EMPTY, EMPTY, EMPTY, YELLOW,
                YELLOW, EMPTY, DARK6, EMPTY, VIOLET,
                LIGHT1, EMPTY, EMPTY, GREEN, MEDIUMS4));

        CardInt window12 = CardCreator.createCard(5, "Firmitas", new Matrix(VIOLET, DARK6, EMPTY, EMPTY, MEDIUMS3,
                DARK5, VIOLET, MEDIUMS3, EMPTY, EMPTY,
                EMPTY, LIGHT2, VIOLET, LIGHT1, EMPTY,
                EMPTY, LIGHT1, DARK5, VIOLET, MEDIUMS4));

        CardInt window13 = CardCreator.createCard(5, "Industria", new Matrix(LIGHT1, RED, MEDIUMS3, EMPTY, DARK6,
                DARK5, MEDIUMS4, RED, LIGHT2, EMPTY,
                EMPTY, EMPTY, DARK5, RED, LIGHT1,
                EMPTY, EMPTY, EMPTY, MEDIUMS3, RED));

        CardInt window14 = CardCreator.createCard(5, "Shadow Thief", new Matrix(DARK6, VIOLET, EMPTY, EMPTY, DARK5,
                DARK5, EMPTY, VIOLET, EMPTY, EMPTY,
                RED, DARK6, EMPTY, VIOLET, EMPTY,
                YELLOW, RED, DARK5, MEDIUMS4, MEDIUMS3));

        CardInt window15 = CardCreator.createCard(5, "Batllo", new Matrix(EMPTY, EMPTY, DARK6, EMPTY, EMPTY,
                EMPTY, DARK5, BLUE, MEDIUMS4, EMPTY,
                MEDIUMS3, GREEN, YELLOW, VIOLET, LIGHT2,
                LIGHT1, MEDIUMS4, RED, DARK5, MEDIUMS3));

        CardInt window16 = CardCreator.createCard(5, "Gravitas", new Matrix(LIGHT1, EMPTY, MEDIUMS3, BLUE, EMPTY,
                EMPTY, LIGHT2, BLUE, EMPTY, EMPTY,
                DARK6, BLUE, EMPTY, MEDIUMS4, EMPTY,
                BLUE, DARK5, LIGHT2, EMPTY, LIGHT1));

        CardInt window17 = CardCreator.createCard(5, "Lux Astram", new Matrix(EMPTY, LIGHT1, GREEN, VIOLET, MEDIUMS4,
                DARK6, VIOLET, LIGHT2, DARK5, GREEN,
                LIGHT1, GREEN, DARK5, MEDIUMS3, VIOLET,
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY));

        CardInt window18 = CardCreator.createCard(5, "Firelight", new Matrix(MEDIUMS3, MEDIUMS4, LIGHT1, DARK5, EMPTY,
                EMPTY, DARK6, LIGHT2, EMPTY, YELLOW,
                EMPTY, EMPTY, EMPTY, YELLOW, RED,
                DARK5, EMPTY, YELLOW, RED, DARK6));

        CardInt window19 = CardCreator.createCard(5, "Ripples of Light", new Matrix(EMPTY, EMPTY, EMPTY, RED, DARK5,
                EMPTY, EMPTY, VIOLET, MEDIUMS4, BLUE,
                EMPTY, BLUE, MEDIUMS3, YELLOW, DARK6,
                YELLOW, LIGHT2, GREEN, LIGHT1, RED));

        CardInt window20 = CardCreator.createCard(5, "Comitas", new Matrix(YELLOW, EMPTY, LIGHT2, EMPTY, DARK6,
                EMPTY, MEDIUMS4, EMPTY, DARK5, YELLOW,
                EMPTY, EMPTY, EMPTY, YELLOW, DARK5,
                LIGHT1, LIGHT2, YELLOW, MEDIUMS3, EMPTY));

        CardInt window21 = CardCreator.createCard(6, "Symphony of Light", new Matrix(LIGHT2, EMPTY, DARK5, EMPTY, LIGHT1,
                YELLOW, DARK6, VIOLET, LIGHT2, RED,
                EMPTY, BLUE, MEDIUMS4, GREEN, EMPTY,
                EMPTY, MEDIUMS3, EMPTY, DARK5, EMPTY));

        CardInt window22 = CardCreator.createCard(6, "Water of Life", new Matrix(DARK6, BLUE, EMPTY, EMPTY, LIGHT1,
                EMPTY, DARK5, BLUE, EMPTY, EMPTY,
                MEDIUMS4, RED, LIGHT2, BLUE, EMPTY,
                GREEN, DARK6, YELLOW, MEDIUMS3, VIOLET));

        CardInt window23 = CardCreator.createCard(6, "Lux Mundi", new Matrix(EMPTY, EMPTY, LIGHT1, EMPTY, EMPTY,
                LIGHT1, GREEN, MEDIUMS3, BLUE, LIGHT2,
                BLUE, DARK5, MEDIUMS4, DARK6, GREEN,
                EMPTY, BLUE, DARK5, GREEN, EMPTY));

        CardInt window24 = CardCreator.createCard(6, "Sun Glory", new Matrix(LIGHT1, VIOLET, YELLOW, EMPTY, MEDIUMS4,
                VIOLET, YELLOW, EMPTY, EMPTY, DARK6,
                YELLOW, EMPTY, EMPTY, DARK5, MEDIUMS3,
                EMPTY, DARK5, MEDIUMS4, LIGHT2, LIGHT1));

        WindowFramePlayerBoard Frame1 = new WindowFramePlayerBoard(31, ANSI_RED);
        WindowFramePlayerBoard Frame2 = new WindowFramePlayerBoard(32, ANSI_GREEN);
        WindowFramePlayerBoard Frame3 = new WindowFramePlayerBoard(33, ANSI_BLUE);
        WindowFramePlayerBoard Frame4 = new WindowFramePlayerBoard(34, ANSI_PURPLE);

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

        ArrayList<CardInt> Private = new ArrayList<CardInt>();
        ArrayList<CardInt> Public = new ArrayList<CardInt>();
        ArrayList<CardInt> Windows = new ArrayList<CardInt>();
        ArrayList<WindowFramePlayerBoard> Frames = new ArrayList<WindowFramePlayerBoard>();
        ArrayList<ToolCardInt> tools = new ArrayList<ToolCardInt>();

        Private.add(private1);
        Private.add(private2);
        Private.add(private3);
        Private.add(private4);
        Private.add(private5);

        Public.add(public1);
        Public.add(public2);
        Public.add(public3);
        Public.add(public4);
        Public.add(public5);
        Public.add(public6);
        Public.add(public7);
        Public.add(public8);
        Public.add(public9);
        Public.add(public10);

        Windows.add(window1);
        Windows.add(window2);
        Windows.add(window3);
        Windows.add(window4);
        Windows.add(window5);
        Windows.add(window6);
        Windows.add(window7);
        Windows.add(window8);
        Windows.add(window9);
        Windows.add(window10);
        Windows.add(window11);
        Windows.add(window12);
        Windows.add(window13);
        Windows.add(window14);
        Windows.add(window15);
        Windows.add(window16);
        Windows.add(window17);
        Windows.add(window18);
        Windows.add(window19);
        Windows.add(window20);
        Windows.add(window21);
        Windows.add(window22);
        Windows.add(window23);
        Windows.add(window24);

        Frames.add(Frame1);
        Frames.add(Frame2);
        Frames.add(Frame3);
        Frames.add(Frame4);

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

        WindowFramePlayerBoardDeck = Frames;


        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        FileWriter writer1 = new FileWriter("src/main/resources/PrivateCards");
        FileWriter writer2 = new FileWriter("src/main/resources/PublicCards");
        FileWriter writer3 = new FileWriter("src/main/resources/WindowCards");

        gson.toJson(Private, writer1);
        gson.toJson(Public, writer2);
        gson.toJson(Windows, writer3);
        try {
            writer1.close();
            writer2.close();
            writer3.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    public void serialize() {

        FileReader fr1 = null, fr2 = null, fr3=null;
        BufferedReader br1 = null, br2 = null, br3=null;

        try {
            fr1 = new FileReader("src/main/resources/PrivateCards");
            br1 = new BufferedReader(fr1);

            //TODO
            //InputStream asdfghj = Main.class.getResourceAsStream("PrivateCards");



            Type Private = new TypeToken<ArrayList<ObjectivePrivateCard>>() { }.getType();
            ArrayList<ObjectivePrivateCard> privateCards = new ArrayList<ObjectivePrivateCard>();

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

            privateCards = gson.fromJson(br1, Private);

            /*System.out.println(privateCards.get(0).getColour());
            System.out.println(privateCards.get(1).getColour());
            System.out.println(privateCards.get(2).getColour());
            System.out.println(privateCards.get(3).getColour());
            System.out.println(privateCards.get(4).getColour());*/


            ObjectivePrivateCardDeck=privateCards;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br1 != null)
                    br1.close();

                if (fr1 != null)
                    fr1.close();
            } catch (IOException e) {
            }
        }


        try {
            fr2 = new FileReader("src/main/resources/PublicCards");
            br2 = new BufferedReader(fr2);

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

            /*System.out.println(publicCards.get(0).getPoints());
            System.out.println(publicCards.get(0).getEffect());
            System.out.println(publicCards.get(1).getPoints());
            System.out.println(publicCards.get(1).getEffect());
            System.out.println(publicCards.get(2).getPoints());
            System.out.println(publicCards.get(2).getEffect());
            System.out.println(publicCards.get(3).getPoints());
            System.out.println(publicCards.get(3).getEffect());
            System.out.println(publicCards.get(4).getPoints());
            System.out.println(publicCards.get(4).getEffect());
            System.out.println(publicCards.get(5).getPoints());
            System.out.println(publicCards.get(5).getEffect());
            System.out.println(publicCards.get(6).getPoints());
            System.out.println(publicCards.get(6).getEffect());
            System.out.println(publicCards.get(7).getPoints());
            System.out.println(publicCards.get(7).getEffect());
            System.out.println(publicCards.get(8).getPoints());
            System.out.println(publicCards.get(8).getEffect());
            System.out.println(publicCards.get(9).getEffect());*/

            ObjectivePublicCardDeck=publicCards;




        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br2 != null)
                    br2.close();

                if (fr2 != null)
                    fr2.close();
            } catch (IOException e) {
            }


        }


        try {
            fr3 = new FileReader("src/main/resources/WindowCards");
            br3 = new BufferedReader(fr3);



            Type Window = new TypeToken<ArrayList<WindowPatternCard>>() { }.getType();
            ArrayList<WindowPatternCard> WindowCards = new ArrayList<WindowPatternCard>();

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

            WindowCards = gson.fromJson(br3, Window);

            /*System.out.println(WindowCards.get(0).getTitle());
            System.out.println(WindowCards.get(0).getToken());
            WindowCards.get(0).printWindowPatter();
            System.out.println(WindowCards.get(1).getTitle());
            System.out.println(WindowCards.get(1).getToken());
            WindowCards.get(1).printWindowPatter();
            System.out.println(WindowCards.get(2).getTitle());
            System.out.println(WindowCards.get(2).getToken());
            WindowCards.get(2).printWindowPatter();
            System.out.println(WindowCards.get(3).getTitle());
            System.out.println(WindowCards.get(3).getToken());
            WindowCards.get(3).printWindowPatter();
            System.out.println(WindowCards.get(4).getTitle());
            System.out.println(WindowCards.get(4).getToken());
            WindowCards.get(4).printWindowPatter();
            System.out.println(WindowCards.get(5).getTitle());
            System.out.println(WindowCards.get(5).getToken());
            WindowCards.get(5).printWindowPatter();
            System.out.println(WindowCards.get(6).getTitle());
            System.out.println(WindowCards.get(6).getToken());
            WindowCards.get(6).printWindowPatter();
            System.out.println(WindowCards.get(7).getTitle());
            System.out.println(WindowCards.get(7).getToken());
            WindowCards.get(7).printWindowPatter();
            System.out.println(WindowCards.get(8).getTitle());
            System.out.println(WindowCards.get(8).getToken());
            WindowCards.get(8).printWindowPatter();
            System.out.println(WindowCards.get(9).getTitle());
            System.out.println(WindowCards.get(9).getToken());
            WindowCards.get(9).printWindowPatter();
            System.out.println(WindowCards.get(10).getTitle());
            System.out.println(WindowCards.get(10).getToken());
            WindowCards.get(10).printWindowPatter();
            System.out.println(WindowCards.get(11).getTitle());
            System.out.println(WindowCards.get(11).getToken());
            WindowCards.get(11).printWindowPatter();
            System.out.println(WindowCards.get(12).getTitle());
            System.out.println(WindowCards.get(12).getToken());
            WindowCards.get(12).printWindowPatter();
            System.out.println(WindowCards.get(13).getTitle());
            System.out.println(WindowCards.get(13).getToken());
            WindowCards.get(13).printWindowPatter();
            System.out.println(WindowCards.get(14).getTitle());
            System.out.println(WindowCards.get(14).getToken());
            WindowCards.get(14).printWindowPatter();
            System.out.println(WindowCards.get(15).getTitle());
            System.out.println(WindowCards.get(15).getToken());
            WindowCards.get(15).printWindowPatter();
            System.out.println(WindowCards.get(16).getTitle());
            System.out.println(WindowCards.get(16).getToken());
            WindowCards.get(16).printWindowPatter();
            System.out.println(WindowCards.get(17).getTitle());
            System.out.println(WindowCards.get(17).getToken());
            WindowCards.get(17).printWindowPatter();
            System.out.println(WindowCards.get(18).getTitle());
            System.out.println(WindowCards.get(18).getToken());
            WindowCards.get(18).printWindowPatter();
            System.out.println(WindowCards.get(19).getTitle());
            System.out.println(WindowCards.get(19).getToken());
            WindowCards.get(19).printWindowPatter();
            System.out.println(WindowCards.get(20).getTitle());
            System.out.println(WindowCards.get(20).getToken());
            WindowCards.get(20).printWindowPatter();
            System.out.println(WindowCards.get(21).getTitle());
            System.out.println(WindowCards.get(21).getToken());
            WindowCards.get(21).printWindowPatter();
            System.out.println(WindowCards.get(22).getTitle());
            System.out.println(WindowCards.get(22).getToken());
            WindowCards.get(22).printWindowPatter();
            System.out.println(WindowCards.get(23).getTitle());
            System.out.println(WindowCards.get(23).getToken());
            WindowCards.get(23).printWindowPatter();*/

            WindowPatternCardDeck=WindowCards;

            for(int i=0; i<WindowPatternCardDeck.size(); i++) WindowPatternCardDeck.get(i).createPattern();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br3 != null)
                    br3.close();

                if (fr3 != null)
                    fr3.close();
            } catch (IOException e) {
            }
        }

    }

}
