package it.polimi.ingsw.LM26.controller.controllerHandler;

import it.polimi.ingsw.LM26.controller.PlaceDie;
import it.polimi.ingsw.LM26.model.Cards.ToolCardInt;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Eugenio Cortesi
 * eventChecker class implements methods that checks if an action can be done, analyzing players and game situation.
 */
public class EventChecker {

    private Model model;

    private static final Logger LOGGER = Logger.getLogger(EventChecker.class.getName());

    public EventChecker(Model model) {

        this.model = model;

        LOGGER.setLevel(Level.ALL);
    }

    /**
     * method that checks if a die can be placed on the position choosen.
     * if it's second attempt of placing from card 6 or 11, a control makes sure that client uses the same die choosen or assigned in the last attempt of placing.
     * if a client already used his die the placement can't be done.
     * if the placement is done, this method update the player history in this half round, model restrictions, and it takes away die from draft pool.
     * @param dieFromDraft Die choosen by client from the draftPool
     * @param toBox Cell of the playerBoard, choosen by the client for the placement.
     * @param player client that sended the place die request
     * @return true if a placement went well, otherwise false
     */
    public boolean checkPlacement(DieInt dieFromDraft, Box toBox, PlayerZone player) {

        if( dieFromDraft==null ) return false;

        if (model.getRestrictions().isNeedPlacement() && !dieFromDraft.equals(model.getRestrictions().getDie())) {

                LOGGER.log(Level.INFO, "must use the rolled die");

                return false;
            }

        if (player.getActionHistory().isPlacement() || player.getActionHistory().isDieUsed()) {

            LOGGER.log(Level.INFO, "action expired");

            return false;
        }

        PlaceDie placement = new PlaceDie(dieFromDraft, toBox, player);

        if (placement.placeDie()) {

            model.getDraftPool().getInDraft().remove(dieFromDraft);

            player.getPlayerBoard().incrementNumDice();

            player.getActionHistory().setDieUsed(true);

            player.getActionHistory().setPlacement(true);

            if (model.getRestrictions().isCurrentPlacement()) {

                model.getRestrictions().setTool8needPlacement(false);

                model.getRestrictions().setCurrentPlacement(false);
            }

            if (model.getRestrictions().isNeedPlacement()) {

                model.getRestrictions().setNeedPlacement(false);

                model.getRestrictions().setDie(null);

                model.getRestrictions().setFirstPart(false);
            }

            return true;
        }

        else {

            LOGGER.log(Level.INFO, "check not passed");

            return false;
        }

    }

    /**
     * this checks if the client has already use a card in his turn.
     * If he has, but card is 11 this control is bypassed because this specific card needs more than one action.
     * @param player client of the action
     * @param card card selected by client
     * @return true, is controls are true
     */
    public boolean checkActionAvailability(PlayerZone player, ToolCardInt card){

        if (player.getActionHistory().isCardUsed() && ! model.getRestrictions().isFirstPart()) {

            LOGGER.log(Level.INFO, "Action already done ");

            return false;
        }

        return (checkToken(player, card, false ));
    }

    /**
     * it controls if the card requested in the action event is one of the three selected
     * @param i id of the card in the event
     * @return true, if the card is one the selected ones
     */
    public boolean checkCard(int i) {

        if( i>model.getDecks().getToolCardDeck().size()) return false;

        if (model.getOnBoardCards().getToolCardList().contains(model.getDecks().getToolCardDeck().get(i - 1)))

            return true;

        else LOGGER.log(Level.INFO, "this card is not one of the selected ones");

        return false;
    }

    /**
     * this check-method is used two times: the first one to check is the player has enough token to use the card requested,
     * the second one to put tokens on the card (pay)
     * @param player client of the action
     * @param toolCard card requested: it can require one token if it's never user, otherwise two tokens.
     * @param pay boolean that says if this is the first usage type (just controlling) or the second (paying)
     * @return true, if client has enough tokens and if the payment is done
     */
    public boolean checkToken(PlayerZone player, ToolCardInt toolCard, Boolean pay) {

        if (toolCard.getToken() > 0) {

            if (player.getToken().getTokenNumber() > 1) {

                if (pay ) toolCard.setTwoToken(player);

                return true;
            }
        } else if (player.getToken().getTokenNumber() > 0) {

            if (pay ){

                toolCard.setOneToken(player);

                model.getOnBoardCards().setSecondUsage(toolCard);
            }

            return true;
        }

        LOGGER.log(Level.INFO, "not enough tokens " + player.getToken().getTokenNumber() + player.getWindowPatternCard().getToken() + toolCard.getToken());

        return false;
    }
}