package it.polimi.ingsw.LM26.controller;

import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;

public interface ControllerInt {

    boolean handler(ActionEvent actionEvent);

    public void playersReady();
}
