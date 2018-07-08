package it.polimi.ingsw.LM26;

import it.polimi.ingsw.LM26.systemNetwork.clientNet.Client;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientInt;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientView;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ViewInterface;
import it.polimi.ingsw.LM26.view.GUI.View;
import it.polimi.ingsw.LM26.view.cli.ConsoleStrings;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClientMain extends Application{

    static ViewInterface view;
    static ClientInt client = new Client();

    public static void main(String[] args) {

        Logger.getLogger(ClientView.class.getPackage().getName()).getParent().getHandlers()[0].setLevel(Level.OFF);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = "";

        System.out.println("Scegli uno tra i seguenti metodi di gioco:\nCLI: c\nGUI: g");
        try {
            s = br.readLine();
        } catch (IOException e) {
            System.err.println("Impossible to take input, reset and try again");
        }

        while (!(s.equalsIgnoreCase("c") || s.equalsIgnoreCase("g"))) {
            try {
                s = br.readLine();
            } catch (IOException e) {
                System.err.println("Impossible to take input, reset and try again");
            }
            if (!(s.equalsIgnoreCase("c") || s.equalsIgnoreCase("g"))) System.out.println("c o g!!");
        }
        if (s.equalsIgnoreCase("C")) {

            startCLI();

        } else {

            launch(args);
        }
    }

    public static void startCLI() {

        view = new ConsoleStrings(client);
        view.showNetChoise();
    }


    public void start(Stage primaryStage) throws Exception {

        view = new View(primaryStage, client);
        view.showNetChoise();
    }
}