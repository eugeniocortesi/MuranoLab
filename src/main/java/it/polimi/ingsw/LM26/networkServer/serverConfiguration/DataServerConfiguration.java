package it.polimi.ingsw.LM26.networkServer.serverConfiguration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class DataServerConfiguration {

    private final int ServerSOCKETPORT=3009;

    private final int ServerRMIPORT=1009;

    public DataServerConfiguration(){

        ;
    }

    public int getSOCKETPORT() {
        return ServerSOCKETPORT;
    }

    public int getRMIPORT() {
        return ServerRMIPORT;
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
