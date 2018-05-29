package it.polimi.ingsw.LM26.systemNetwork.clientConfiguration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.LM26.Main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class DataClientImplementation {

    private DataClientConfiguration dataClientConfiguration;

    public DataClientImplementation(){

        this.dataClientConfiguration = new DataClientConfiguration();
        //this.dataClientConfiguration.create();
    }

    public DataClientConfiguration getDataClientConfiguration() {
        return dataClientConfiguration;
    }

    public DataClientConfiguration implementation() {

        try {
            InputStream stream = Main.class.getResourceAsStream("DataClientConfiguration");
            //FileReader fr = new FileReader("src/main/resources/DataClientConfiguration");
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

            DataClientConfiguration dataClient = gson.fromJson(br, DataClientConfiguration.class);
            return dataClient;

            //InputStream reader = Main.class.getResourceAsStream("DataServerConfiguration");
        } catch (Exception e) {

            System.out.println("Client not configured");
            return null;
        }
    }
}
