package it.polimi.ingsw.LM26.observers.serverController;

import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.ConnectMessage;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.DataMessage;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.WindowAnswerMessage;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.WindowInitialMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Observable class
 * @author Chiara Criscuolo
 * @param <T> generic type
 * It uses overloading to call different notifies
 */

public class Observable<T> {

    private final List<Observer<T>> observers = new ArrayList<>();

    /**
     * Method that register the observer
     * @param observer added
     */

    public void register(Observer<T> observer){
        synchronized (observers) {
            observers.add(observer);
        }
    }

    /**
     * Method that deregister the Observer
     * @param observer to be deregistered
     */

    public void deregister(Observer<T> observer){
        synchronized (observers) {
            observers.remove(observer);
        }
    }

    /**
     * @param actionEvent action made by player
     */

    protected void notify(ActionEvent actionEvent){
        synchronized (observers) {
            for(Observer<T> observer : observers){
                observer.updateAction(actionEvent);
            }
        }
    }

    /**
     * @param actionPlayer information about the connection of a player
     */

    protected void notify(ActionEventPlayer actionPlayer){
        synchronized (observers) {
            for(Observer<T> observer : observers){
                observer.updatePlayers(actionPlayer);
            }
        }
    }

    /**
     * @param actionEventWindow windowPatternCard decided by the player
     */

    protected void notify(ActionEventWindow actionEventWindow){
        synchronized (observers) {
            for(Observer<T> observer : observers){
                observer.updateWindowPattern(actionEventWindow);
            }
        }
    }

    /**
     * Not implemented
     * @param connectMessage message of connection
     */

    protected void notify(ConnectMessage connectMessage){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *  Not implemented
     * @param dataMessage generic message
     */

    protected void notify(DataMessage dataMessage){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *  Not implemented
     * @param windowInitialMessage list of windowPatternCards
     */

    protected void notify(WindowInitialMessage windowInitialMessage){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *  Not implemented
     * @param windowAnswerMessage window chosen
     */

    protected void notify(WindowAnswerMessage windowAnswerMessage) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @param actionEventTimerEnd a client has finished is time or is not more online
     */

    public void notify(ActionEventTimerEnd actionEventTimerEnd) {
        synchronized (observers) {
            for(Observer<T> observer : observers){
                observer.updateActionEventTimerEnd(actionEventTimerEnd);
            }
        }
    }

    /**
     * @param beginGame notify that the game can starts
     */

    public void notify(Boolean beginGame) {
        synchronized (observers) {
            for(Observer<T> observer : observers){
                observer.updateBeginGame(beginGame);
            }
        }
    }
}
