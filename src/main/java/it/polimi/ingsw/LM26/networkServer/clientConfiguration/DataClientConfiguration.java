package it.polimi.ingsw.LM26.networkServer.clientConfiguration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class DataClientConfiguration {

    private final static int ClientSOCKETPORT = 3000;

    public int getClientSOCKETPORT() {
        return ClientSOCKETPORT;
    }

    public void create(){

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        FileWriter wr;

        {
            try {
                wr = new FileWriter("DataClientConfiguration");
                gson.toJson(this, wr);
                wr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
