package it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.LM26.controller.ToolCardsDecorator.*;
import it.polimi.ingsw.LM26.fileConfiguration.RuntimeTypeAdapterFactory1;
import it.polimi.ingsw.LM26.model.Cards.CardInt;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCards.ObjectivePublicCard;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCards.PublicCardEffects.*;
import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.ToolCardInt;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.ScoreTrack;
import it.polimi.ingsw.LM26.model.PlayArea.ScoreTrackInt;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PlayArea.roundTrack.RoundTrack;
import it.polimi.ingsw.LM26.model.PlayArea.roundTrack.RoundTrackInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZoneInt;
import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.observers.serverController.VisitorInt;

import java.lang.reflect.Type;

/**
 * EventMessage class
 * @author Chiara Criscuolo
 * It contains actionEvent as field1
 */

public class EventMessage extends ClassMessage {

    private String idEvent;

    private ActionEvent actionEvent;

    public EventMessage(String idEvent, ActionEvent actionEvent) {
        this.idEvent = idEvent;

        this.actionEvent = actionEvent;
    }

    public ActionEvent getActionEvent() {
        return actionEvent;
    }

    public void setActionEvent(ActionEvent actionEvent) {
        this.actionEvent = actionEvent;
    }

    /**
     * Method that return from a string with json the EventMessage
     * @param protocolJson string to deserialize
     * @return EventMessage
     */

    static public EventMessage deserializeEventMessage(String protocolJson){

        RuntimeTypeAdapterFactory1<Effect> runtimeTypeAdapterFactory8 = RuntimeTypeAdapterFactory1
                .of(Effect.class, "typeEffect")
                .registerSubtype(DifferentColorShadeOnRowColomn.class, "DifferentColorShadeOnRowColomn")
                .registerSubtype(DifferentColorShade.class, "DifferentColorShade")
                .registerSubtype(Shades.class, "Shades")
                .registerSubtype(ColoredDiagonals.class, "ColoredDiagonals");

        RuntimeTypeAdapterFactory1<RoundTrackInt> runtimeTypeAdapterFactory1 = RuntimeTypeAdapterFactory1.of(RoundTrackInt.class, "typeRoundTrack")
                .registerSubtype(RoundTrack.class, "RoundTrack");
        RuntimeTypeAdapterFactory1<ScoreTrackInt> runtimeTypeAdapterFactory2 = RuntimeTypeAdapterFactory1.of(ScoreTrackInt.class, "typeScoreTrack")
                .registerSubtype(ScoreTrack.class, "ScoreTrack");
        RuntimeTypeAdapterFactory1<DieInt> runtimeTypeAdapterFactory3 = RuntimeTypeAdapterFactory1.of(DieInt.class, "typeDie")
                .registerSubtype(Die.class, "Die");
        RuntimeTypeAdapterFactory1<ToolCardInt> runtimeTypeAdapterFactory4 = RuntimeTypeAdapterFactory1.of(ToolCardInt.class, "typeToolCard")
                .registerSubtype(ToolCard.class, "ToolCard")
                .registerSubtype(ToolCardDecorator.class, "ToolCardDecorator");

        RuntimeTypeAdapterFactory1<ToolCardDecorator> runtimeTypeAdapterFactory5 = RuntimeTypeAdapterFactory1.of(ToolCardDecorator.class, "type")
                .registerSubtype(ChangeDieWithTheBag11.class, "ChangeDieFromTheBag11")
                .registerSubtype(ChangeDieFromDraftToRoundTrack5.class, "ChangeDieFromDraftToRoundTrack5")
                .registerSubtype(ChangeDieValue1.class, "ChangeDieValue1")
                .registerSubtype(DrawOneMoreDie8.class, "DrawOneMoreDie8")
                .registerSubtype(MoveTwoDice4.class, "MoveTwoDice4")
                .registerSubtype(MoveTwoDiceWithSameColor12.class, "MoveTwoDiceWithTheSameColor12")
                .registerSubtype(MoveWithNoColorRestriction2.class, "MoveWithNoColorRestriction2")
                .registerSubtype(MoveWithNoValueRestriction3.class, "MoveWithNoValueRestriction3")
                .registerSubtype(PlaceWithNotInProximities9.class, "PlaceWithNotInProximities9")
                .registerSubtype(RollAgainADie6.class, "RollAgainADie6")
                .registerSubtype(RollAllDraftDice7.class, "RollAllDraftDice7")
                .registerSubtype(RollToTheOppositeFace10.class, "RollToTheOppositeFace10");


        RuntimeTypeAdapterFactory1<PlayerZoneInt> runtimeTypeAdapterFactory6 = RuntimeTypeAdapterFactory1.of(PlayerZoneInt.class, "typePlayerZone")
                .registerSubtype(PlayerZone.class, "PlayerZone");

        RuntimeTypeAdapterFactory1<CardInt> runtimeTypeAdapterFactory7 = RuntimeTypeAdapterFactory1.of(CardInt.class, "typeCard")
                .registerSubtype(ObjectivePrivateCard.class, "ObjectivePrivateCard")
                .registerSubtype(ObjectivePublicCard.class, "ObjectivePublicCard")
                .registerSubtype(WindowPatternCard.class, "WindowPatternCard");

        Type type = new TypeToken<Model>(){}.getType();

        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(runtimeTypeAdapterFactory1)
                .registerTypeAdapterFactory(runtimeTypeAdapterFactory2)
                .registerTypeAdapterFactory(runtimeTypeAdapterFactory3)
                .registerTypeAdapterFactory(runtimeTypeAdapterFactory4)
                .registerTypeAdapterFactory(runtimeTypeAdapterFactory5)
                .registerTypeAdapterFactory(runtimeTypeAdapterFactory6)
                .registerTypeAdapterFactory(runtimeTypeAdapterFactory7)
                .registerTypeAdapterFactory(runtimeTypeAdapterFactory8)
                .create();


        return gson.fromJson(protocolJson, EventMessage.class);
    }

    /**
     * Calls the visitor pattern on the message
     * @param visitorInt instance of visitor
     */

    @Override
    public void accept(VisitorInt visitorInt) {
        visitorInt.visitActionEvent(actionEvent);
    }
}
