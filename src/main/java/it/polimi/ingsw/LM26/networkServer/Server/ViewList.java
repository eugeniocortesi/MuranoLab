package it.polimi.ingsw.LM26.networkServer.Server;

import it.polimi.ingsw.LM26.networkServer.ClientHandler.VirtualViewInt;
import it.polimi.ingsw.LM26.view.ViewInt;

import java.util.ArrayList;

public class ViewList implements ViewInt {

    private ArrayList<VirtualViewInt> viewSet;

    //CHIEDI GIOCATORE CORRENTE AL MODEL

    public ViewList(){
        viewSet = new ArrayList<VirtualViewInt>();
    }

    public boolean addView(VirtualViewInt v){
        return viewSet.add(v);

    }

    @Override
    public void start() {
        
    }

    public boolean removeView(VirtualViewInt v){
        return viewSet.remove(v);

    }
    public ArrayList<VirtualViewInt> getViewSet() {
        return viewSet;
    }

    public void setViewSet(ArrayList<VirtualViewInt> viewSet) {
        this.viewSet = viewSet;
    }

    public void showLoginScreen() {

    }

    public void showLoggedScreen() {
    }

    public void showAlreadyLoggedScreen() {

    }

    public void showTooManyUsersScreen() {

    }

    public void showDisconnectScreen() {

    }

    public void showAddedPlayer() {

    }

    public void showTurnInitialPhase() {

    }

    public void showPlaceDie() {

    }

    public void showChooseCard() {

    }

    public void showTurnEndPhase() {

    }

    public void showPoints() {

    }
}
