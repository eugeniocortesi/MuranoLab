package it.polimi.ingsw.LM26;

import it.polimi.ingsw.LM26.controller.Controller;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello");

        Controller controller = new Controller();

        controller.startServer();

    }
}
