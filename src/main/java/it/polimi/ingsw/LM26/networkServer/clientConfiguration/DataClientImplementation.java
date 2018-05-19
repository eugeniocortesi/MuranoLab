package it.polimi.ingsw.LM26.networkServer.clientConfiguration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.LM26.networkServer.serverConfiguration.DataServerConfiguration;

import java.io.BufferedReader;
import java.io.FileReader;

public class DataClientImplementation {

    private DataClientConfiguration dataClientConfiguration;

    public DataClientImplementation(){

        this.dataClientConfiguration = new DataClientConfiguration();
        this.dataClientConfiguration.create();
    }

    public DataClientConfiguration getDataClientConfiguration() {
        return dataClientConfiguration;
    }

    public DataClientConfiguration implementation() {

        try {
            FileReader fr = new FileReader("DataClientConfiguration");
            BufferedReader br = new BufferedReader(fr);

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
