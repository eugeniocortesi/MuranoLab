package it.polimi.ingsw.LM26.controller;

import it.polimi.ingsw.LM26.ServerController.ActionEvent;
import it.polimi.ingsw.LM26.controller.GamePhases.CentralPhase;
import it.polimi.ingsw.LM26.controller.GamePhases.Game;
import it.polimi.ingsw.LM26.controller.Testing.ControllerTest;
import it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator.DrawOneMoreDie8;
import it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator.RollAgainADie6;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static it.polimi.ingsw.LM26.controller.GamePhases.RoundState.FINISHED;

public class Match {

    private PlayerZone playing;
    private boolean result=false;
    private Model model;
    private ActionEvent event;
    private CentralPhase centralPhase;
    private Game game;
    //TODO STATIC TYPE CONTROLLER INT
    //private ControllerInt controller;
    private ControllerTest controller;



    public Match(Model model, ControllerInt controller ) {

        //TODO DELETE CAST AND ADD HANDLER METHOD TO INT

        //this.controller=controller;

        this.controller=(ControllerTest) controller;
        this.game = new Game(model.getPlayerList(), model.getDecks(), model.getOnBoardCards());  //initialPhase
        game.getPhase().doAction(game, model.getPlayerList());    //centralPhase
        this.centralPhase = (CentralPhase) game.getPhase();
        this.model=model;


        play();

    }

    public void play (){

        for(int i=0; i<10; i++){

            //primo giocatore
            playing =centralPhase.getCurrentRound().nextPlayer(model.getPlayerList(), centralPhase.getTurn(), 0);
            int k=playing.getIDPlayer();
            // x ciascun giocatore
            while (centralPhase.getCurrentRound().getRoundState() != FINISHED) {         //1


                //while(!playing.isSecondTurn()) {                              //2
                    //se è la prima scelta    controller.getView().showActionMenu();
                    //se è la seconda:
                        //se ha scleto una carta    controller.getView().showPlaceDie();
                        //se ha piazato un dado     controller.getView().showChooseCard();

                //TODO
                // X4 setPlayerMenu(String name, PlayerZone player)

                //la view crea l'evento e fa notifyall che all'interno ha update



                // HOW TO WAIT A NEW EVENT ????????????
                //while (event== null )
                // ovvero aspetto che la view faccia update

                //TODO DELETE
                System.out.println(playing.getName()+" is playing "  );
                playing.getPlayerBoard().printCard();
                System.out.println("DraftPool");
                model.getDraftPool().printDraftPool();



                //TODO check that the event player is the current one

                //if(event.getPlayer().getName() == playing.getName()) ){

                if(playing.getActionHistory().isFreezed()){
                    result = true;
                    System.out.println("this turn you are freezed");
                }
                while (!result) {


                    //while (event == null )    //ASPETTA DI NUOVO L?EVENTO

                    //TODO DELETE
                    setActionEvent(event);

                    if (controller.handler(event)) {
                        System.out.println("done");
                        playing.getPlayerBoard().printCard();
                        System.out.println("DraftPool");
                        model.getDraftPool().printDraftPool();
                        // view.showOK()
                        //update model
                        result = true;
                    }

                        else System.out.println("match error 1 ");


                    //view.showNO()
                    //view.showReduAction()      //IMPORTANT

                }
                //set the correct number of turn 1 0 2


                //}
                // else view.showWrongPlayer(); e rientra nel while 2

                //ripeti il controllo utente

                result=false;

                if(playing.getActionHistory().isFreezed()){
                    result = true;
                    System.out.println("this turn you are freezed");
                }

                while (!result) {


                    //while (event == null )    //ASPETTA DI NUOVO L?EVENTO

                    //TODO DELETE
                    setActionEvent(event);

                    if (controller.handler(event)) {
                        System.out.println("done");
                        playing.getPlayerBoard().printCard();
                        System.out.println("DraftPool");
                        model.getDraftPool().printDraftPool();
                        // view.showOK()
                        DrawOneMoreDie8 tool8=(DrawOneMoreDie8) model.getDecks().getToolCardDeck().get(7);
                        if(tool8.isNeedPlacement() ){
                            playing.getActionHistory().setPlacement(false);
                            playing.getActionHistory().setDieUsed(false);
                            tool8.setCurrentPlacement(true);
                            playing.getActionHistory().setFreezed(true);
                            System.out.println("choose another die");
                            //update model


                        }
                        else {
                            result = true;
                            //update model
                        }
                    } else
                        System.out.println("match error 2 ");

                    //view.showNO()
                    //view.showReduAction()      //IMPORTANT

                }
                //set the correct number of turn 1 0 2


                //}chiudi while 2


                centralPhase.getCurrentRound().endAction(centralPhase.getTurn(), model.getRoundTrackInt(), model.getDraftPool(), centralPhase.getCurrentRound().getCurrentPlayer());
                playing = centralPhase.getCurrentRound().nextPlayer(model.getPlayerList(), centralPhase.getTurn(), 0);
                result=false;
            }

            //finiti i turni in un round
            System.out.println("end turn " + i);
            model.getRoundTrackInt().dump();


            centralPhase.nextRound(centralPhase.getCurrentRound(), game);
            //controller.getView().showTurnEndPhase();
        }

        //controller.getView().showPoints();

    }


    public void setActionEvent(ActionEvent newEvent) {

        this.event = newEvent;













        //TODO DELETE THIS PART
        ///////////////////////////////////////////////////////

        event = new ActionEvent();
        int id=0;


            System.out.println("Insert 1 to place a die");
            System.out.println("Insert 2 for cards 2 and 3");
            System.out.println("insert 3 to for card 4");
            System.out.println("insert 6 to for card 1");
            System.out.println("insert 8 to for card 7 or 8");
            System.out.println("insert 7 to for card 6,10 or 11");
            System.out.println("Insert 9 to pass the turn");

            id=askId();

        int line = 0;
        int col = 0;
        int die = 0;
        int card=0;
        if(id==1) {

            //prendi indice dado
            //prendi indici i j


            line= askLine();
            col=askCol();
            die=askDie();

            //prendi gli oggetti
            Box[][] board = playing.getPlayerBoard().getBoardMatrix();
            event.setId(id);
            event.setDieFromDraft( model.getDraftPool().getInDraft().get(die - 1));
            event.setToBox1(board[line - 1][col - 1]);
            event.setPlayer(playing.getIDPlayer());
        }
        if (id==2){

            System.out.println("card 2 or 3?");
            card=askCard();

            line= askLine();
            col=askCol();


            Box[][] board = playing.getPlayerBoard().getBoardMatrix();
            event.setId(id);
            event.setCard(model.getDecks().getToolCardDeck().get(card-1));
            event.setFromBox1(board[line - 1][col - 1]);
            event.setPlayer(playing.getIDPlayer());

            line= askLine();
            col=askCol();

            event.setToBox1(board[line - 1][col - 1]);

        }

        if (id==3){

            card=4;
            Box[][] board = playing.getPlayerBoard().getBoardMatrix();
            event.setId(id);
            event.setCard(model.getDecks().getToolCardDeck().get(card-1));
            event.setPlayer(playing.getIDPlayer());
            line= askLine();
            col=askCol();
            event.setFromBox1(board[line - 1][col - 1]);
            line= askLine();
            col=askCol();
            event.setToBox1(board[line - 1][col - 1]);
            line= askLine();
            col=askCol();
            event.setFromBox2(board[line - 1][col - 1]);
            line= askLine();
            col=askCol();
            event.setToBox2(board[line - 1][col - 1]);

        }

        if (id==7){

            System.out.println("card 6,10 or 11?");
            event.setId(id);
            card=askCard();

            event.setCard(model.getDecks().getToolCardDeck().get(card-1));
            event.setPlayer(playing.getIDPlayer());

            die=askDie();
            event.setDieFromDraft( model.getDraftPool().getInDraft().get(die - 1));


        }

        if (id==6){

            event.setId(id);
            event.setPlayer(playing.getIDPlayer());
            event.setCard(model.getDecks().getToolCardDeck().get(0));

            die=askDie();
            event.setDieFromDraft( model.getDraftPool().getInDraft().get(die - 1));
            System.out.println("insert 1 to increment, 2 to decrement");
            id=askId();

            if(id==1)event.setInDeCrement("increment");
            if(id==2)event.setInDeCrement("decrement");


        }

        if (id==5){

            event.setId(id);
            event.setPlayer(playing.getIDPlayer());
            event.setCard(model.getDecks().getToolCardDeck().get(4));

            die=askDie();
            event.setDieFromDraft( model.getDraftPool().getInDraft().get(die - 1));

            System.out.println("insert number of turn");
            id=askId();

            while(id>model.getRoundTrackInt().getRoundTrackTurnList().size())
                id=askId();
            die=askDie();

            event.setDieFromRoundTrack( model.getRoundTrackInt().getRoundTrackTurnList().get(id-1).getDiceList().get(die-1));


        }

        if (id==8){

            System.out.println("card 7 or 8?");
            event.setId(id);
            card=askCard();
            event.setCard(model.getDecks().getToolCardDeck().get(card-1));
            event.setPlayer(playing.getIDPlayer());


        }
        if(id==9) event.setId(id);
        ///////////////////////////////////////////




    }

    /////TODO DELETE

    public int askId(){

        BufferedReader read;
        int id = 0;
        read = new BufferedReader(new InputStreamReader(System.in));
        try {
            id = Integer.parseInt(read.readLine());
            return id;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int askLine() {

        BufferedReader read;
        int line = 0;

        System.out.println("insert line");
        read = new BufferedReader(new InputStreamReader(System.in));
        try {
            line = Integer.parseInt(read.readLine());
            return line;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int askCol() {

        BufferedReader read;
        int col = 0;

        System.out.println("insert col");
        read = new BufferedReader(new InputStreamReader(System.in));
        try {
            col = Integer.parseInt(read.readLine());
            return col;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;

    }

    public int askDie() {

        BufferedReader read;
        int die = 0;

        System.out.println("insert die");
        read = new BufferedReader(new InputStreamReader(System.in));
        try {
            die = Integer.parseInt(read.readLine());
            return die;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int askCard (){

        BufferedReader read;
        int card = 0;

        read = new BufferedReader(new InputStreamReader(System.in));
        try {
            card = Integer.parseInt(read.readLine());
            return card;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;

    }

}
