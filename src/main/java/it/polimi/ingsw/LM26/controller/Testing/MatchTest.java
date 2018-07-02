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
        this.game = new Game(model.getPlayerList(), model.getDecks(), model.getOnBoardCards());  //initialPhase
        game.getPhase().doAction(game, model.getPlayerList());    //centralPhase
        controller.setGamePhase(game.getPhase());
        this.model = model;
        this.model.hasChanged();

        play();
    }


    public void play() {

        while(i<game.getPhase().getNrounds() && !game.getPhase().getOnePlayer()) {

            playing = game.getPhase().getCurrentRound().nextPlayer(model.getPlayerList(), game.getPhase().getTurn());

            while (game.getPhase().getCurrentRound().getRoundState() != FINISHED) {

                //TODO DELETE
                System.out.println("              CHANGE TURN: " + playing.getName());
                LOGGER.log(Level.SEVERE, playing.getName() + " is playing ");
                playing.getPlayerBoard().printCard();
                System.out.println("DraftPool");
                model.getDraftPool().printDraftPool();

                System.out.println("        FIRST ACTION ");

                waitCorrectPlayer();

                if (playing.getPlayerState() != STANDBY && !playing.getActionHistory().isJump() && !playing.getActionHistory().isFreezed()) {

                    firstAction();

                    if (playing.getPlayerState() != STANDBY && !playing.getActionHistory().isJump()) {

                        result = false;

                        System.out.println("        SECOND ACTION ");

                        waitCorrectPlayer();

                        if (playing.getPlayerState() != STANDBY && !playing.getActionHistory().isJump())

                            secondAction();
                    }

                } else if (playing.getActionHistory().isFreezed()) {

                    System.out.println("this turn you are freezed");
                }

                game.getPhase().getCurrentRound().endAction(game.getPhase().getTurn(), model.getRoundTrackInt(), model.getDraftPool(), playing);

                playing = game.getPhase().getCurrentRound().nextPlayer(model.getPlayerList(), game.getPhase().getTurn());

                result = false;
            }

            //all turn ended
            System.out.println("end turn " + (i+1));

            model.getRoundTrackInt().dump();

            game.getPhase().nextRound(game.getPhase().getCurrentRound(), game);
        }

        game.getPhase().doAction(game, model.getPlayerList());

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
                        playing.getActionHistory().setFreezed(true);
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
        while (event.getPlayer()!=playing.getIDPlayer() && playing.getPlayerState()!=STANDBY) {
            event = controller.getActionEvent();
            while (event == null && playing.getPlayerState()!=STANDBY ){
                event = controller.getActionEvent();
                //view.showWrongPlayer();
                ;

            }

        }

    }
}
