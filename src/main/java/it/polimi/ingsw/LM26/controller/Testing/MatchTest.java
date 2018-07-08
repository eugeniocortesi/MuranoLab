package it.polimi.ingsw.LM26.controller.Testing;
import it.polimi.ingsw.LM26.controller.controllerHandler.RoundsHandler;
import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.controller.GamePhases.Game;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import static it.polimi.ingsw.LM26.controller.GamePhases.RoundState.FINISHED;
import static it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerState.STANDBY;

public class MatchTest {

    private PlayerZone playing;
    private boolean result = false;
    private Model model;
    private Game game;
    private ControllerTest controller;
    private CliTest cli;
    private static final Logger LOGGER = Logger.getLogger(RoundsHandler.class.getName());
    private ActionEvent event;
    private int i=0;


    public MatchTest(Model model, ControllerTest controller) {

        this.controller = controller;
        cli= new CliTest(playing, controller);
        this.game = new Game();  //initialPhase
        game.getPhase().doAction(game);    //centralPhase
        controller.setGamePhase(game.getPhase());
        this.model = model;
        this.model.hasChanged();

        play();
    }


    public void play() {

        for(int i=0; i<model.getOnBoardCards().getToolArrayList().size();i++)
            System.out.print(model.getOnBoardCards().getToolArrayList().get(i));

        while(i<game.getPhase().getNrounds() && !game.getPhase().getOnePlayer()) {

            playing = game.getPhase().getCurrentRound().nextPlayer();

            while (game.getPhase().getCurrentRound().getRoundState() != FINISHED) {

                //TODO DELETE
                System.out.println("              CHANGE TURN: " + playing.getName());
                playing.getPlayerBoard().printCard();
                System.out.println("DraftPool");
                model.getDraftPool().printDraftPool();

                System.out.println("\n        FIRST ACTION ");


                if (playing.getPlayerState() != STANDBY && !playing.getActionHistory().isJump() && !playing.getActionHistory().isFreezed()) {

                    waitCorrectPlayer();

                    firstAction();

                    if (playing.getPlayerState() != STANDBY && !playing.getActionHistory().isJump()) {

                        result = false;

                        System.out.println("\n        SECOND ACTION ");

                        waitCorrectPlayer();

                        if (playing.getPlayerState() != STANDBY && !playing.getActionHistory().isJump())

                            secondAction();
                    }

                } else if (playing.getActionHistory().isFreezed()) {

                    System.out.println("this turn you are freezed");
                }

                game.getPhase().getCurrentRound().endAction();

                playing = game.getPhase().getCurrentRound().nextPlayer();

                result = false;
            }

            //all turn ended
            System.out.println("end turn " + (i+1));

            model.getRoundTrackInt().dump();

            game.getPhase().nextRound(game.getPhase().getCurrentRound(), game);

            i++;
        }

        game.getPhase().doAction(game);

        System.out.println("Il vincitore è " + game.getPhase().getWinner().getName());  // final phase
    }

    public void firstAction() {

        while (!result) {

            if (playing.getPlayerState() != STANDBY && !playing.getActionHistory().isJump()) {
                if (controller.handler(event)) {
                    System.out.println("done");
                    playing.getPlayerBoard().printCard();
                    System.out.println("DraftPool");
                    model.getDraftPool().printDraftPool();
                    model.hasChanged();
                    result = true;
                } else {
                    System.out.println("match error 1 ");
                    waitCorrectPlayer();
                }

                //view.showNO()

                //view.showReduAction()

            } else result = true;
        }

    }

    public void secondAction() {

        while (!result) {

            if (playing.getPlayerState() != STANDBY && !playing.getActionHistory().isJump()) {
                if (controller.handler(event)) {
                    System.out.println("done");
                    playing.getPlayerBoard().printCard();
                    System.out.println("DraftPool");
                    model.getDraftPool().printDraftPool();

                    if (model.getRestrictions().isTool8needPlacement()) {
                        if (playing.getActionHistory().isJump()) playing.getActionHistory().setJump(false);
                        playing.getActionHistory().setPlacement(false);
                        playing.getActionHistory().setDieUsed(false);
                        model.getRestrictions().setCurrentPlacement(true);

                        System.out.println("choose another die");
                        model.hasChanged();
                        waitCorrectPlayer();
                        if (playing.getActionHistory().isJump()) playing.getActionHistory().setFreezed(false);

                    } else {
                        model.hasChanged();
                        result = true;
                    }
                } else {
                    System.out.println("match error 2 ");
                    waitCorrectPlayer();
                    if (playing.getActionHistory().isJump()) playing.getActionHistory().setFreezed(false);

                }

                //view.showNO()

                //view.showReduAction()

            } else result = true;
        }
    }

    public void waitCorrectPlayer(){

        controller.setActionEvent(null);

        event = controller.getActionEvent();
        LOGGER.log(Level.WARNING, "Event match: " + event);

        while (event == null && playing.getPlayerState()!=STANDBY){


                cli.ask(playing);

            event = controller.getActionEvent();
        }
        LOGGER.log(Level.WARNING,"exit while ");


        if(playing.getPlayerState()!=STANDBY) // for sure i got an event
        while (event!= null &&  event.getPlayer()!=playing.getIDPlayer() && playing.getPlayerState()!=STANDBY) {
            event = controller.getActionEvent();
            while (event == null && playing.getPlayerState()!=STANDBY ){
                event = controller.getActionEvent();
                //view.showWrongPlayer();
                ;

            }

        }

    }
}
