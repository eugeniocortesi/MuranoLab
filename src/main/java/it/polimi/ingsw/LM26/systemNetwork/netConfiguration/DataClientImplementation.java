package it.polimi.ingsw.LM26.systemNetwork.netConfiguration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.LM26.Main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * class DataClientImplementation
 * @author Chiara Criscuolo
 * It creates from reading file the class DataClientConfiguration
 */
public class DataClientImplementation {

         public DataClientConfiguration implementation() {

        try {
            InputStream stream = Main.class.getResourceAsStream("DataClientConfiguration");

            BufferedReader br = new BufferedReader(new InputStreamReader(stream));

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

            DataClientConfiguration dataClient = gson.fromJson(br, DataClientConfiguration.class);

            return dataClient;

        } catch (Exception e) {

            System.err.println("Client not configured");
            return null;
        }
    }
}
