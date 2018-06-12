package it.polimi.ingsw.LM26.systemNetwork.serverNet.timer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class TimerConfiguration {

    private long timerEnd = 5000;

    public long getTimerEnd() {
        return timerEnd;
    }

    public void create(){

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        FileWriter wr;

        {
            try {
                wr = new FileWriter("src/main/resources/TimerConfiguration");
                gson.toJson(this, wr);
                wr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
