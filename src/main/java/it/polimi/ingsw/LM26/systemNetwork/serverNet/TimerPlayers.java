package it.polimi.ingsw.LM26.systemNetwork.serverNet;

import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.observers.serverController.ActionEventTimerEnd;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.BeginTurnMessage;

import java.util.Timer;
import java.util.TimerTask;

public class TimerPlayers {

    public long myLong = 5000;

    public ServerBase serverBase;

    private Timer timer;


    public TimerPlayers(ServerBase serverBase) {

        this.serverBase = serverBase;
        timer = new Timer();

        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                this.doStuff();
            }

            private void doStuff() {


                if(serverBase.clientManagerListSize()<2)
                    run();
                else{
                    ActionEventTimerEnd timerEnd = new ActionEventTimerEnd("ready", true);
                    System.out.println("Timer end");
                    serverBase.getQueueController().pushMessage(timerEnd);
                }
            }
        }, 0, this.myLong);
    }

}
