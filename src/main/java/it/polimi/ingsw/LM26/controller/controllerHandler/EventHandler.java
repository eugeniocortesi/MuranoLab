package it.polimi.ingsw.LM26.controller.controllerHandler;

import it.polimi.ingsw.LM26.controller.ControllerInt;
import it.polimi.ingsw.LM26.model.Cards.ToolCardInt;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.timer.TimerTaskActionEvent;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * EventHandler class
 * @author EugenioCortesi
 * class the reads a new event, parse the type and select the right checks to do.
 */

public class EventHandler {

    private Model model;

    private Boolean result;

    private ActionEvent event;

    private ControllerInt controller;

    private PlayerZone player;

    private static final Logger LOGGER = Logger.getLogger(EventHandler.class.getName());


    /**
     * Constructor
     * it creates the eventChecker object, saves the result of the action, after have handling it with checkers
     * @param event got from server, to handle
     * @param model instance of model class
     * @param controller instance of controller class
     */

    public EventHandler(ActionEvent event, Model model, ControllerInt controller) {

        this.model = model;

        LOGGER.setLevel(Level.ALL);

        this.controller = controller;

        EventChecker eventChecker = new EventChecker(model);

        this.event = event;

        result = handle(eventChecker);
    }


    /**
     * this method is called from the RoundsHandler: it's used to say if the client action can be done or not.
     * it parses the id action and calls the specific action checks.
     * tokens for card usage are payed only if the action is done.
     * @param eventChecker instance of eventChecker object
     * @return the boolean returned is the ultimate acceptance of the action
     */

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

                        eventChecker.checkToken(player, toolCard, true);

                        return true;
                    } else return false;

                if (event.getId() == 5)

                    if (toolCard.play(getDraftDieCopy(event.getDieFromDraft()), getTrackDieCopy(event.getrTrackCoordinates()))) {

                        player.getActionHistory().setCardUsed(true);

                        eventChecker.checkToken(player, toolCard,true);

                        return true;
                    } else return false;

                if (event.getId() == 6)

                    if (toolCard.play(getDraftDieCopy(event.getDieFromDraft()), event.getInDeCrement())) {

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

                    if (toolCard.play(getTrackDieCopy(event.getrTrackCoordinates()), getBoxListCopy(event.getFromBoxList()), getBoxListCopy(event.getToBoxList()), player)) {

                        player.getActionHistory().setCardUsed(true);

                        eventChecker.checkToken(player, toolCard,true);

                        return true;
                    } else return false;
            } else return false;
        }

        if (event.getId() == 11) {

            LOGGER.log(Level.INFO, "i'll pass ");

            player.getActionHistory().setJump(true);

            TimerTaskActionEvent ttask1 = controller.getRoundsHandler().getTimerTaskActionEvent();

            if(ttask1 != null) {

                ttask1.setArrived(true);

                ttask1.cancelTimerTask();
            }

            controller.getRoundsHandler().getTimerActionPlayer().resetTimer();

            return true;
        }

        if (event.getId() == 12) {

            LOGGER.log(Level.INFO, "Getted id 12 (action event)");

            controller.getViewGameInterface().showSetPlayerMenu(player.getName(), player);

            return false;
        }

        return false;
    }

    public Boolean getResult() {

        return result;
    }


    /**
     * the method get the card from the cards on board
     * @return toolCard object with the id requested in the event
     */

    public ToolCardInt getToolCard() {

        for (int i = 0; i < model.getOnBoardCards().getToolCardList().size(); i++)

            if (model.getOnBoardCards().getToolCardList().get(i).getNum() == event.getCardID())

                return model.getOnBoardCards().getToolCardList().get(i);

        return null;
    }


    /**
     * @param c vector with round track coordinated
     * @return die from round track requested in the action
     */

    public DieInt getTrackDieCopy(int[] c) {

        if (model.getRoundTrackInt().getRoundTrackTurnList().size() < c[0] || model.getRoundTrackInt().getRoundTrackTurn(c[0] +1 ).size() < c[1])

            return null;

        return model.getRoundTrackInt().getRoundTrackTurn(c[0] + 1).get(c[1]);
    }


    /**
     * @param b copy of board cell sent from client
     * @return cell of the client board
     */

    public Box getBoxCopy(Box b)  {

        if(b==null)return null;

        return player.getPlayerBoard().getBoardMatrix()[b.getI()][b.getJ()];
    }


    /**
     * @param die copy of die sent from client
     * @return right instance of die requested
     */

    private DieInt getDraftDieCopy(DieInt die) {

        if(die==null) return null;

        for (int i = 0; i < model.getDraftPool().size(); i++)

            if (model.getDraftPool().get(i).getValue() == die.getValue() && model.getDraftPool().get(i).getColor().equals(die.getColor()))

                return model.getDraftPool().get(i);

        return null;
    }


    /**
     * @param a copy of Array of board cells sent from client
     * @return right instance of Array of board cells
     */

    public ArrayList<Box> getBoxListCopy(ArrayList<Box> a) {

        if(a==null || a.size()>2 ) return null;

        ArrayList<Box> toReturn = new ArrayList<>();

        Box[][] board = player.getPlayerBoard().getBoardMatrix();

        for (Box anA : a) toReturn.add(board[anA.getI()][anA.getJ()]);

        return toReturn;
    }
}
