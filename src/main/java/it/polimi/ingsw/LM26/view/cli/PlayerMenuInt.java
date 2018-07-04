package it.polimi.ingsw.LM26.view.cli;

public interface PlayerMenuInt {
    void showMenu();
    void handleInput(String input);
    boolean evaluateCondition(String input);
}
