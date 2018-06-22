package it.polimi.ingsw.LM26.systemNetwork.Testing;

import it.polimi.ingsw.LM26.systemNetwork.clientNet.Client;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientBase;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientInt;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ViewInterface;
import it.polimi.ingsw.LM26.view.GUI.View;
import it.polimi.ingsw.LM26.view.cli.ConsoleStrings;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ClientStarter extends Application{

    static ViewInterface view;
    static ClientInt client = new Client();

    public static void main(String[] args) {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = null;

        System.out.println("Scegli uno tra i seguenti metodi di gioco:\nCLI: c\nGUI: g");
        try {
            s = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (!(s.equalsIgnoreCase("c") || s.equalsIgnoreCase("g"))) {
            try {
                s = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!(s.equalsIgnoreCase("c") || s.equalsIgnoreCase("c"))) System.out.println("c o g!!");
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
        //view.showCurrentMenu("name");
        view.showNetChoise();
    }
}