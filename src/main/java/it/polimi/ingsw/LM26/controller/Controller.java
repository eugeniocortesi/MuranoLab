package it.polimi.ingsw.LM26.controller;

import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ServerBase;
import it.polimi.ingsw.LM26.view.ViewInt;
import it.polimi.ingsw.LM26.view.cli.ConsoleStrings;

import java.util.ArrayList;
import java.util.Observable;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class Controller implements ControllerInt {

    private Model model;
    private Match match;
    private ServerBase server;

    public Controller() {

        //server= new ServerBase();
        this.model = singletonModel();



        //view.addObserver(this);


        setupPlayers();
        /*for(int i=0; i<model.getPlayerList().size(); i++){

            c.showLoginScreen
        }*/
        newMatch(model, this);

    }

    public boolean checkEvent( ActionEvent event){

        if (event.getId()==1)
            if (check(event.getDieFromDraft(), event.getToBox1(), event.getPlayer())) {  model.hasChanged(); model.notifyObservers();
            return true; }
            else return false;
        if (event.getId()==2)
            if(check(event.getCard(), event.getFromBox1(), event.getToBox1(), event.getPlayer())) {   model.hasChanged(); model.notifyObservers(); return true;}
            else return false;
        if (event.getId()==3)
            if(check(event.getCard(), event.getFromBox1(), event.getToBox1(), event.getFromBox2(), event.getToBox2(),event.getPlayer())) {
            model.hasChanged(); model.notifyObservers();  return true;}
            else return false;
        if (event.getId()==4)
            if(check(event.getCard(), event.getDieFromDraft(), event.getToBox1(), event.getPlayer())) {   model.hasChanged(); model.notifyObservers(); return true;}
            else return false;
        if (event.getId()==5)
            if(check(event.getCard(), event.getDieFromDraft(), event.getDieFromRoundTrack(), event.getPlayer())) {   model.hasChanged(); model.notifyObservers(); return true;}
            else return false;
        if (event.getId()==6)
            if(check(event.getCard(), event.getDieFromDraft(), event.getInDeCrement(), event.getPlayer())) {  model.hasChanged(); model.notifyObservers(); return true;}
            else return false;
        if (event.getId()==7)
            if(check(event.getCard(), event.getDieFromDraft(), event.getPlayer())) {   model.hasChanged(); model.notifyObservers(); return true;}
            else return false;
        if (event.getId()==8)
            if(check(event.getCard(), event.getPlayer())) { model.hasChanged(); model.notifyObservers();  return true;}
            else return false;
        if (event.getId()==9) { System.out.println("i'll pass ");return true;}
        return false;

    }

    //TODO
    //OGNI CHECK DEVE CHIAMARE IL METODO checkNotSameAction() CHE CONTROLLA CHE NON VENGANO RICEVUTI DUE EVENTI UGUALI PER QUEL GIOCATORE IN QUEL TURNO
    //metti nei player dei boolean per sapere se hanno scelto una carta o piazzato un dado, alla fine del turno settali a false cosi
    //che rifunzionino per il secondo turno di quel giocatore in quel round
    //ATTENZIONE prima di ogni piazzamento controllare che non abbia gia piazzato dadi in quel turno, esempio con una toolcard

    public boolean check(Die dieFromDraft, Box toBox, int pl){

        PlayerZone player=model.getPlayerList().get(pl);
        if( player.getActionHistory().isPlacement() || player.getActionHistory().isDieUsed()) return false;
        PlaceDie placement = new PlaceDie(dieFromDraft, toBox, player);
        if(placement.placeDie()){
            model.getDraftPool().getInDraft().remove(dieFromDraft);
            //esempio: model.accept(new UpdateX());
            player.getActionHistory().setPlacement(true);
            return true;}
        else return false;
    }

    public boolean check(ToolCard twoThree, Box fromBox, Box toBox, int player){

        PlayerZone pl=model.getPlayerList().get(player);
        if ( pl.getActionHistory().isCardUsed()|| pl.getActionHistory().isDieUsed()) return false;

        if(checkToken(model.getPlayerList().get(player),twoThree))

            if(twoThree.play(fromBox, toBox, player)){
                pl.getActionHistory().setCardUsed(true);
                return true;}

        return false;
    }
    public boolean check(ToolCard four, Box fromBox1, Box toBox1, Box fromBox2, Box toBox2, int player){

        PlayerZone pl=model.getPlayerList().get(player);
        if ( pl.getActionHistory().isCardUsed() || pl.getActionHistory().isDieUsed()) return false;

        if(checkToken(model.getPlayerList().get(player),four))

            if(four.play(fromBox1, toBox1, fromBox2, toBox2, player)){
                pl.getActionHistory().setCardUsed(true);
                return true;}

        return false;
    }
    public boolean check(ToolCard sixEightNine, Die dieFromDraft, Box toBox, int player){

        PlayerZone pl=model.getPlayerList().get(player);
        if ( pl.getActionHistory().isCardUsed() || pl.getActionHistory().isDieUsed()) return false;

        if(checkToken(model.getPlayerList().get(player),sixEightNine))

            if(sixEightNine.play(dieFromDraft, toBox, player)){
                pl.getActionHistory().setCardUsed(true);
                return true;}

        return false;
    }
    public boolean check(ToolCard five, Die dieFromDraft, Die dieFromRoundTrack, int player){

        PlayerZone pl=model.getPlayerList().get(player);
        if ( pl.getActionHistory().isCardUsed() || pl.getActionHistory().isDieUsed()) return false;

        if(checkToken(model.getPlayerList().get(player),five))

            if(five.play(dieFromDraft, dieFromRoundTrack)){
                pl.getActionHistory().setCardUsed(true);
                return true; }

        return false;
    }
    public boolean check(ToolCard one, Die dieFromDraft, String inDeCrement, int player){

        PlayerZone pl=model.getPlayerList().get(player);
        if ( pl.getActionHistory().isCardUsed() || pl.getActionHistory().isDieUsed()) return false;

        if(checkToken(model.getPlayerList().get(player),one))

            if(one.play(dieFromDraft, inDeCrement)) {
            pl.getActionHistory().setCardUsed(true);
            return true;
        }

        return false;
    }
    public boolean check(ToolCard tenEleven, Die dieFromDraft, int player){

        PlayerZone pl=model.getPlayerList().get(player);
        if ( pl.getActionHistory().isCardUsed() || pl.getActionHistory().isDieUsed()) return false;

        if(checkToken(model.getPlayerList().get(player),tenEleven))

            if(tenEleven.play( dieFromDraft)){
                pl.getActionHistory().setCardUsed(true);
                return true; }

        return false;
    }
    public boolean check(ToolCard seven,  int player){

        PlayerZone pl=model.getPlayerList().get(player);
        if ( pl.getActionHistory().isCardUsed() || pl.getActionHistory().isDieUsed()) return false;

        if(checkToken(model.getPlayerList().get(player),seven))

            if(seven.play(player)){
                pl.getActionHistory().setCardUsed(true);
                return true; }

        return false;
    }


    public boolean check( String noAction, int player){

        return false;
    }

    public boolean checkToken(PlayerZone player, ToolCard toolCard){

        if(toolCard.getToken()>0)
            if(player.getToken().getTokenNumber()>1){
                toolCard.setTwoToken(player);
                return true;}
        else if(player.getToken().getTokenNumber()>0){
        toolCard.setOneToken(player);
        return true;}
        return false;
    }

    public void showLogin(){

        //view.showLogin()
    }

    public void setupPlayers(){

        String[] names;
        //server.playersName();
        //for(int i=0; i<names;i++){
        //create new player
        //view.showAddedPlayer();
        // }

        //TODO DELETE PLAYERS

        PlayerZone player1 = new PlayerZone("Eugenio", 0);
        PlayerZone player2 = new PlayerZone("Chiara", 1);
        PlayerZone player3 = new PlayerZone( "Claudia", 2);
        PlayerZone player4 = new PlayerZone("Tommaso", 3);

        player1.setNumberPlayer(0);
        player2.setNumberPlayer(1);
        player3.setNumberPlayer(2);
        player4.setNumberPlayer(3);

        ArrayList<PlayerZone> playerList = new ArrayList<PlayerZone>();
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);

        model.setPlayerList(playerList);
    }

    public void newMatch(Model model, Controller controller/*, ArrayList<ConsoleStrings> console*/){

        this.match=new Match(model, controller);

    }

    public void update(Observable o, Object arg){

        //i parametri sono viewInt, actionEvent

       setActionEvent((ActionEvent)arg);

    }


    public void setActionEvent(ActionEvent newEvent ){
        match.setActionEvent(newEvent);
    }


    public void close(){

    }

}

