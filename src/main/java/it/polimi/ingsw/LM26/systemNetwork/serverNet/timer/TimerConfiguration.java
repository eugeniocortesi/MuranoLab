package it.polimi.ingsw.LM26.systemNetwork.serverNet.timer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

/**
 * TimerConfiguration class
 * it has the number of milliseconds when a timer finish
 */

public class TimerConfiguration {

    private long timerEnd = 60000;

    private long timerGameEnd = 10000;

    public long getTimerEnd() {
        return timerEnd;
    }

    public long getTimerGameEnd(){
        return timerGameEnd;
    }

    /**
     * method tha create the file TimerConfiguration
     */

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
