package it.polimi.ingsw.LM26.controller;

import it.polimi.ingsw.LM26.ServerController.ActionEvent;
import it.polimi.ingsw.LM26.ServerController.ActionEventPlayer;
import it.polimi.ingsw.LM26.ServerController.ActionEventWindow;
import it.polimi.ingsw.LM26.model.Cards.ToolCardInt;
import it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator.RollAgainADie6;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ServerBase;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ViewGameInterface;
import it.polimi.ingsw.LM26.view.ViewInt;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class Controller implements ControllerInt {

    private Model model;
    private Match match;
    private ViewGameInterface server;

    public Controller() {



        this.model = singletonModel();



        //view.addObserver(this);


        //setupPlayers();
        /*for(int i=0; i<model.getPlayerList().size(); i++){

            c.showLoginScreen
        }*/
        //newMatch(model, this);
        //startServer();

    }

    public void startServer(){
        server= new ServerBase(this);
        server.startAcceptor();
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
        if (event.getId()==10) return false;
        return false;

    }

    //TODO
    //OGNI CHECK DEVE CHIAMARE IL METODO checkNotSameAction() CHE CONTROLLA CHE NON VENGANO RICEVUTI DUE EVENTI UGUALI PER QUEL GIOCATORE IN QUEL TURNO
    //metti nei player dei boolean per sapere se hanno scelto una carta o piazzato un dado, alla fine del turno settali a false cosi
    //che rifunzionino per il secondo turno di quel giocatore in quel round
    //ATTENZIONE prima di ogni piazzamento controllare che non abbia gia piazzato dadi in quel turno, esempio con una toolcard

    public boolean check(DieInt dieFromDraft, Box toBox, int pl){

        PlayerZone player=model.getPlayerList().get(pl);
        RollAgainADie6 tool6=(RollAgainADie6)model.getDecks().getToolCardDeck().get(5);

        if(tool6.isNeedPlacement()){

            if(dieFromDraft.equals(tool6.getDieCard6()))
                tool6.setNeedPlacement(false);
            else {
                System.out.println("must use the rolled die");
                return false;
            }

        }
        if (player.getActionHistory().isPlacement() || player.getActionHistory().isDieUsed()) {
            System.out.println("action expired");
            return false;
        }

        PlaceDie placement = new PlaceDie(dieFromDraft, toBox, player);

        if (placement.placeDie()) {
            model.getDraftPool().getInDraft().remove(dieFromDraft);
            player.getPlayerBoard().incrementNumDice();
            //esempio: model.accept(new UpdateX());
            player.getActionHistory().setDieUsed(true);
            player.getActionHistory().setPlacement(true);
            return true;
            }
        else{
            System.out.println("check not passed");
            return false;
        }
    }

    //ATTENZIONE ALL'ITERNO DI OGNI CARTA NON PERMETTO UN PIAZZAMENTO SE DADO è GIA ALREADY USED

    public boolean check(ToolCardInt twoThree, Box fromBox, Box toBox, int player){

        PlayerZone pl=model.getPlayerList().get(player);
        if ( pl.getActionHistory().isCardUsed()) {
            System.out.println("Action already done ");
            return false;}

        if(checkToken(model.getPlayerList().get(player),twoThree))

            if(twoThree.play(fromBox, toBox, player)){
                pl.getActionHistory().setCardUsed(true);
                return true;}

        return false;
    }
    public boolean check(ToolCardInt four, Box fromBox1, Box toBox1, Box fromBox2, Box toBox2, int player){

        PlayerZone pl=model.getPlayerList().get(player);
        if ( pl.getActionHistory().isCardUsed() ) return false;

        if(checkToken(model.getPlayerList().get(player),four))

            if(four.play(fromBox1, toBox1, fromBox2, toBox2, player)){
                pl.getActionHistory().setCardUsed(true);
                return true;}

        return false;
    }
    public boolean check(ToolCardInt EightNine, DieInt dieFromDraft, Box toBox, int player){
//potrei creare un altro evento es 41 che con solo un paio di coordinate, il check passa l'id dell'evento e il check prova afare il piazzamento controllando
// solo che abbia un dado dispobile e non se ha gia fatto un piazzamento. retun true si che il piazzamento avvenga che no
        PlayerZone pl=model.getPlayerList().get(player);
        if ( pl.getActionHistory().isCardUsed()) return false;

        if(checkToken(model.getPlayerList().get(player),EightNine))

            if(EightNine.play(dieFromDraft, toBox, player)){
                pl.getActionHistory().setCardUsed(true);
                return true;}

        return false;
    }
    public boolean check(ToolCardInt five, DieInt dieFromDraft, DieInt dieFromRoundTrack, int player){

        PlayerZone pl=model.getPlayerList().get(player);
        if ( pl.getActionHistory().isCardUsed() ) return false;

        if(checkToken(model.getPlayerList().get(player),five))

            if(five.play(dieFromDraft, dieFromRoundTrack)){
                pl.getActionHistory().setCardUsed(true);
                return true; }

        return false;
    }
    public boolean check(ToolCardInt one, DieInt dieFromDraft, String inDeCrement, int player){

        PlayerZone pl=model.getPlayerList().get(player);
        if ( pl.getActionHistory().isCardUsed() ) return false;

        if(checkToken(model.getPlayerList().get(player),one))

            if(one.play(dieFromDraft, inDeCrement)) {
            pl.getActionHistory().setCardUsed(true);
            return true;
        }

        return false;
    }
    public boolean check(ToolCardInt sixTenEleven, DieInt dieFromDraft, int player){

        PlayerZone pl=model.getPlayerList().get(player);
        if ( pl.getActionHistory().isCardUsed() ) return false;

        if(checkToken(model.getPlayerList().get(player),sixTenEleven))

            if(sixTenEleven.play( dieFromDraft,player)){
                pl.getActionHistory().setCardUsed(true);
                return true; }
        return false;
    }
    public boolean check(ToolCardInt seven,  int player){

        PlayerZone pl=model.getPlayerList().get(player);
        if ( pl.getActionHistory().isCardUsed() ) return false;

        if(checkToken(model.getPlayerList().get(player),seven))

            if(seven.play(player)){
                pl.getActionHistory().setCardUsed(true);
                return true; }

        return false;
    }


    public boolean check( String noAction, int player){

        return false;
    }

    public boolean checkToken(PlayerZone player, ToolCardInt toolCard){

        if(toolCard.getToken()>0) {
            if (player.getToken().getTokenNumber() > 1) {
                toolCard.setTwoToken(player);
                return true;
            }
        }
        else if(player.getToken().getTokenNumber()>0){
            toolCard.setOneToken(player);
            return true;
        }
        System.out.println("not enough tokens " + player.getToken().getTokenNumber() + player.getWindowPatternCard().getToken()+ toolCard.getToken());
        return false;
    }

    public void showLogin(){

        //view.showLogin()
    }

    public void setupPlayers(ArrayList<String> names){

        //String[] names;
        //server.playersName();
        //for(int i=0; i<names;i++){
        //create new player
        //view.showAddedPlayer();
        // }

        ArrayList<PlayerZone> playerList = new ArrayList<PlayerZone>();
        PlayerZone player;
        int i=names.size();
        System.out.println("Dimensione: " +i);

        for(int j=0; j<i; j++){
            player = new PlayerZone(names.get(j), j);
            player.setNumberPlayer(j);
            playerList.add(player);
            System.out.println(player.getName() + player.getIDPlayer());

            }
        if(playerList== null)
            System.out.println("qualcosa è nullo");
        model.setPlayerList(playerList);

        setupWindowCard();

    }


    public void setupWindowCard(){



        for(int i=0; i< model.getPlayerList().size(); i++){
            ArrayList<WindowPatternCard> windowlist = createWindowPattern();
            System.out.println(windowlist.size());
            if(model.getPlayerList().get(i).getName() == null)
                    System.out.println("name null");
            else if(model.getDecks().getWindowPatternCardDeck()== null)
                System.out.println("cards null");
            if(server== null)
                System.out.println("server: " + server);
            server.showWindowPattern(model.getPlayerList().get(i).getName(), model.getPlayerList().get(i).getIDPlayer(), windowlist);
            windowlist.clear();

        }
        //newMatch(model, this);
    }

    public void assignWindowCard(String name, WindowPatternCard windowPatternCard){

        //TODO SOMETHING
        model.getPlayer(name).setWindowPatternCard(windowPatternCard);
        model.getPlayer(name).getWindowPatternCard().printCard();
        System.out.println("Assigned card to player "+name);
    }


    public ArrayList<WindowPatternCard> createWindowPattern(){
        ArrayList<WindowPatternCard> temp = new ArrayList<WindowPatternCard>();
        ArrayList<WindowPatternCard> four = new ArrayList<WindowPatternCard>();

        temp.addAll(model.getDecks().getWindowPatternCardDeck());

        int count= temp.size();

        for (int j = 0; j < 4; j++) {

            Random rand = new Random();
            int index = rand.nextInt(count);
            while (temp.get(index).isInUse() == true) {
                rand = new Random();
                index = rand.nextInt(count);
            }


            four.add(temp.get(index));
            temp.remove(index);
            count = temp.size();
        }
        return four;
    }

        //TODO DELETE PLAYERS

 /*   public void setupPlayers(){

        PlayerZone player1 = new PlayerZone("eugenio", 0);
        PlayerZone player2 = new PlayerZone("Chiara", 1);
       // PlayerZone player3 = new PlayerZone( "Claudia", 2);
       // PlayerZone player4 = new PlayerZone("Tommaso", 3);

        player1.setNumberPlayer(0);
        player2.setNumberPlayer(1);
       // player3.setNumberPlayer(2);
       // player4.setNumberPlayer(3);

        ArrayList<PlayerZone> playerList = new ArrayList<PlayerZone>();

        playerList.add(player1);
        playerList.add(player2);
        //playerList.add(player3);
        //playerList.add(player4);

        model.setPlayerList(playerList);
    }
*/
    public void newMatch(Model model, Controller controller/*, ArrayList<ConsoleStrings> console*/){

        this.match=new Match(model, controller);

    }


    public void setActionEvent(ActionEvent newEvent ){
        match.setActionEvent(newEvent);
    }


    public void close(){

    }


    @Override
    public void updatePlayers(ActionEventPlayer actionEventPlayer) {

        if(actionEventPlayer.getMethodPlayer().equals("ready"))

            setupPlayers(actionEventPlayer.getUsers());
    }

    @Override
    public void updateAction(ActionEvent actionEvent) {

        //setActionEvent(actionEvent);

    }

    @Override
    public void updateWindowPattern(ActionEventWindow actionEventWindow) {
        //assegna ogni carta al player
        System.out.println("Notify window arrived");
        assignWindowCard(actionEventWindow.getName(), actionEventWindow.getWindowPatternCard());
    }
}


