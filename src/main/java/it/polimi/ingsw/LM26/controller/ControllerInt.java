package it.polimi.ingsw.LM26.controller;

import it.polimi.ingsw.LM26.controller.GamePhases.PhaseInt;
import it.polimi.ingsw.LM26.controller.controllerHandler.RoundsHandler;
import it.polimi.ingsw.LM26.controller.controllerHandler.SetupHandler;
import it.polimi.ingsw.LM26.controller.controllerHandler.UpdatesHandler;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ServerBase;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ViewGameInterface;

public interface ControllerInt {

    boolean handler(ActionEvent actionEvent);

    void playersReady();

    ViewGameInterface getViewGameInterface();

    UpdatesHandler getUpdatesHandler();

    void setActionEvent(ActionEvent event);

    ActionEvent getActionEvent();

    void declareScoresAndWinner(PlayerZone winner);

    void setEndGame(PlayerZone last);

    void setGamePhase(PhaseInt phase);

    SetupHandler getSetupHandler();

    ServerBase getServer();

    RoundsHandler getRoundsHandler();
}
