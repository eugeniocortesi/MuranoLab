package it.polimi.ingsw.LM26.networkServer.serverConfiguration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.LM26.Main;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class DataServerImplementation {

    DataServerConfiguration dataServerConfiguration;

    public DataServerImplementation(){

        dataServerConfiguration = new DataServerConfiguration();
        //this.dataServerConfiguration.create();

    }

    public DataServerConfiguration implementation() {

        try {
            InputStream stream = Main.class.getResourceAsStream("DataServerConfiguration");
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

            DataServerConfiguration dataServer = gson.fromJson(br, DataServerConfiguration.class);
            return dataServer;

            //InputStream reader = Main.class.getResourceAsStream("DataServerConfiguration");
        } catch (Exception e) {

            System.out.println("Server not configured");
            return null;
        }
    }
}
