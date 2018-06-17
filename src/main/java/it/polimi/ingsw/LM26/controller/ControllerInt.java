package it.polimi.ingsw.LM26.controller;

import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ViewGameInterface;

public interface ControllerInt {

    boolean handler(ActionEvent actionEvent);

    void playersReady();

    ViewGameInterface getViewGameInterface();

    UpdatesHandler getUpdatesHandler();

    void setActionEvent(ActionEvent event);

    ActionEvent getActionEvent();
}
