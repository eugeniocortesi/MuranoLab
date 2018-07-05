package it.polimi.ingsw.LM26.controller.controllerHandler;

import it.polimi.ingsw.LM26.controller.PlaceDie;
import it.polimi.ingsw.LM26.model.Cards.ToolCardInt;
import it.polimi.ingsw.LM26.controller.ToolCardsDecorator.ChangeDieWithTheBag11;
import it.polimi.ingsw.LM26.controller.ToolCardsDecorator.DrawOneMoreDie8;
import it.polimi.ingsw.LM26.controller.ToolCardsDecorator.RollAgainADie6;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import javax.tools.Tool;
import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class EventChecker {

    private Model model;

    public EventChecker(Model model) {

        this.model = model;
    }

    public boolean checkPlacement(DieInt dieFromDraft, Box toBox, PlayerZone player) {

        if( dieFromDraft==null ) return false;

        //this control is used for card 6 and 11 (if it's first attempt of placing goes wrong)

        if (model.getRestrictions().isNeedPlacement()) {

            if (!dieFromDraft.equals(model.getRestrictions().getDie())) {

                System.out.println("must use the rolled die");

                return false;
            }
        }

        if (player.getActionHistory().isPlacement() || player.getActionHistory().isDieUsed()) {

            System.out.println("action expired");

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

        else { System.out.println("check not passed");

            return false;
        }
    }

    public boolean checkActionAvailability(PlayerZone player, ToolCardInt card){

        if (player.getActionHistory().isCardUsed() && ! model.getRestrictions().isFirstPart()) {

            System.out.println("Action already done ");

            return false;
        }

        return (checkToken(player, card, false ));
    }


    public boolean checkCard(int i) {

        if( i>model.getDecks().getToolCardDeck().size()) return false;

        if (model.getOnBoardCards().getToolCardList().contains(model.getDecks().getToolCardDeck().get(i - 1)))

            return true;

        else System.out.println("this card is not one of the selected ones");

        return false;
    }

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

        System.out.println("not enough tokens " + player.getToken().getTokenNumber() + player.getWindowPatternCard().getToken() + toolCard.getToken());

        return false;
    }
}