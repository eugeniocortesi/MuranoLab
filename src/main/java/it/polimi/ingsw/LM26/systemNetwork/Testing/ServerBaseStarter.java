package it.polimi.ingsw.LM26.systemNetwork.Testing;

import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.SingletonModel;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientBase;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ServerBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observer;

public class ServerBaseStarter {

    public static void main(String[] args){
        ServerBase server = new ServerBase();
        Model model = SingletonModel.singletonModel();
        Observer ct = new ControllerBaseTest(model, server);

        System.out.println("Inserisci nome: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String nome = br.readLine();
            server.showWindowPattern(nome, 0, model.getDecks().getWindowPatternCardDeck());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
