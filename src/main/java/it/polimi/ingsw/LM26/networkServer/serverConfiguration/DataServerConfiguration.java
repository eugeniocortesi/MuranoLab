package it.polimi.ingsw.LM26.networkServer.serverConfiguration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class DataServerConfiguration {

    private final static int SOCKETPORT=3000;

    private final static int RMIPORT=1099;

    public DataServerConfiguration(){

        ;
    }

    public static int getSOCKETPORT() {
        return SOCKETPORT;
    }

    public static int getRMIPORT() {
        return RMIPORT;
    }

    public void create(){

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        FileWriter wr;

        {
            try {
                wr = new FileWriter("DataServerConfiguration");
                gson.toJson(this, wr);
                wr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
