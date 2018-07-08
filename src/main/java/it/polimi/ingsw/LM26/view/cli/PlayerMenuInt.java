package it.polimi.ingsw.LM26.view.cli;

public interface PlayerMenuInt {

    /**
     * it shows the current menù
     */
    void showMenu();

    /**
     * calls methods from ActionEventGenerator class to handle input
     * @param input from user
     */
    void handleInput(String input);

    /**
     * @param input checked input fro muser
     * @return if the input is one of the menù options
     */
    boolean evaluateCondition(String input);
}
