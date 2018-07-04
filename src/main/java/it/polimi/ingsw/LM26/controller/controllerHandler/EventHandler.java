package it.polimi.ingsw.LM26.controller.controllerHandler;

import it.polimi.ingsw.LM26.controller.Controller;
import it.polimi.ingsw.LM26.controller.ControllerInt;
import it.polimi.ingsw.LM26.model.Cards.ToolCardInt;
import it.polimi.ingsw.LM26.controller.ToolCardsDecorator.ChangeDieWithTheBag11;
import it.polimi.ingsw.LM26.controller.ToolCardsDecorator.DrawOneMoreDie8;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.controller.ToolCardsDecorator.RollAgainADie6;
import it.polimi.ingsw.LM26.model.Model;
import org.omg.PortableInterceptor.LOCATION_FORWARD;

import java.util.ArrayList;
import java.util.NoSuchElementException;


public class EventHandler {

    private Model model;

    private EventChecker eventChecker;

    private Boolean result;

    private ActionEvent event;

    private ControllerInt controller;

    PlayerZone player;

    public EventHandler(ActionEvent event, Model model, ControllerInt controller) {

        this.model = model;

        this.controller = controller;

        eventChecker = new EventChecker(model);

        this.event = event;

        result = handle(eventChecker);
    }

    public boolean handle(EventChecker eventChecker) {

        ToolCardInt toolCard;

        player = model.getPlayer(event.getPlayer());

        if (event.getId() == 1)

            return (eventChecker.checkPlacement(getDraftDieCopy(event.getDieFromDraft()), getBoxCopy(event.getToBox1()), player));

        if (event.getCardID() != -1) {

            if (eventChecker.checkCard(event.getCardID()))

                toolCard = getToolCard();

            else return false;

            if (eventChecker.checkActionAvailability(player, toolCard)) {

                if (event.getId() == 2)

                    if (toolCard.play(getBoxCopy(event.getFromBox1()), getBoxCopy(event.getToBox1()), player)) {

                        player.getActionHistory().setCardUsed(true);

                        eventChecker.checkToken(player, toolCard,true);

                        return true;
                    } else return false;

                if (event.getId() == 3)

                    if (toolCard.play(getBoxListCopy(event.getFromBoxList()), getBoxListCopy(event.getToBoxList()), player)) {

                        player.getActionHistory().setCardUsed(true);

                        eventChecker.checkToken(player, toolCard,true);

                        return true;
                    } else return false;

                if (event.getId() == 4)

                    if (toolCard.play(getDraftDieCopy(event.getDieFromDraft()), getBoxCopy(event.getToBox1()), player)) {

                        player.getActionHistory().setCardUsed(true);

                        eventChecker.checkToken(player, toolCard,true);

                        return true;
                    } else return false;

                if (event.getId() == 5)

                    if (toolCard.play(event.getDieFromDraft(), event.getDieFromRoundTrack())) {

                        player.getActionHistory().setCardUsed(true);

                        eventChecker.checkToken(player, toolCard,true);

                        return true;
                    } else return false;

                if (event.getId() == 6)

                    if (toolCard.play(event.getDieFromDraft(), event.getInDeCrement())) {

                        player.getActionHistory().setCardUsed(true);

                        eventChecker.checkToken(player, toolCard,true);

                        return true;
                    } else return false;

                if (event.getId() == 7)

                    if (toolCard.play(getDraftDieCopy(event.getDieFromDraft()), player)) {

                        player.getActionHistory().setCardUsed(true);

                        eventChecker.checkToken(player, toolCard,true);

                        return true;
                    } else return false;

                if (event.getId() == 8)

                    if (toolCard.play(player)) {

                        player.getActionHistory().setCardUsed(true);

                        eventChecker.checkToken(player, toolCard,true);

                        return true;
                    } else return false;

                if (event.getId() == 9)

                    if (toolCard.play(event.getNumber(), getBoxCopy(event.getToBox1()), player)) {

                        player.getActionHistory().setCardUsed(true);

                        return true;
                    }else return false;

                if (event.getId() == 10)

                    if (toolCard.play(event.getDieFromRoundTrack(), getBoxListCopy(event.getFromBoxList()), getBoxListCopy(event.getToBoxList()), player)) {

                        player.getActionHistory().setCardUsed(true);

                        eventChecker.checkToken(player, toolCard,true);

                        return true;
                    } else return false;
            } else return false;
        }

        if (event.getId() == 11) {

            System.out.println("i'll pass ");

            player.getActionHistory().setJump(true);

            return true;
        }

        if (event.getId() == 12) {

            System.out.println("Getted id 12 (action event)");

            controller.getViewGameInterface().showSetPlayerMenu(player.getName(), player);

            return false;
        }

        return false;
    }

    public Boolean getResult() {

        return result;
    }

    public ToolCardInt getToolCard() {

        for (int i = 0; i < model.getOnBoardCards().getToolCardList().size(); i++)

            if (model.getOnBoardCards().getToolCardList().get(i).getNum() == event.getCardID())

                return model.getOnBoardCards().getToolCardList().get(i);

        return null;
    }

    public DieInt getTrackDieCopy(int[] c) {

        if (model.getRoundTrackInt().getRoundTrackTurnList().size() < c[0] || model.getRoundTrackInt().getRoundTrackTurn(c[0]).size() < c[1])

            return null;

        return model.getRoundTrackInt().getRoundTrackTurn(c[0] + 1).get(c[1]);
    }

    public Box getBoxCopy(Box b) throws NoSuchElementException {

        if(b==null)return null;

        return player.getPlayerBoard().getBoardMatrix()[b.getI()][b.getJ()];
    }

    public DieInt getDraftDieCopy(DieInt die) {

        if(die==null) return null;

        for (int i = 0; i < model.getDraftPool().size(); i++)

            if (model.getDraftPool().get(i).getValue() == die.getValue() && model.getDraftPool().get(i).getColor().equals(die.getColor()))

                return model.getDraftPool().get(i);

        return null;
    }

    public ArrayList<Box> getBoxListCopy(ArrayList<Box> a) {

        if(a==null) return null;

        ArrayList<Box> toReturn = new ArrayList<Box>();

        Box[][] board = player.getPlayerBoard().getBoardMatrix();

            for (int i = 0; i < a.size(); i++)

                toReturn.add(board[a.get(i).getI()][a.get(i).getJ()]);

        return toReturn;
    }
}
