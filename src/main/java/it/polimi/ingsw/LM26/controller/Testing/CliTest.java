package it.polimi.ingsw.LM26.controller.Testing;

import it.polimi.ingsw.LM26.controller.ControllerInt;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerState;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class CliTest {

    private PlayerZone playing;
    private ActionEvent event;
    private ControllerInt controller;
    private Model model;

    public CliTest (PlayerZone playing, ControllerInt controller) {

        this.playing=playing;
        this.controller=controller;
        this.model=singletonModel();

    }

    public void ask(PlayerZone playing){

        int op=0;

        System.out.println("Insert 1 set event");
        System.out.println("Insert 2 to manage STANDBY");
        System.out.println("Insert 11 to pass");
        op = askId();

        if(op==1) askEvent(playing);
        if(op==2) standby(playing);
        if(op==11) pass(playing);
    }

    private void standby(PlayerZone playing) {

        ArrayList<PlayerZone> plNotStandby = new ArrayList<PlayerZone>();
        int num;
        System.out.println("Insert num of player to STANDBY");
        num = askId();
        if(model.getPlayerList().get(num-1).getPlayerState()==PlayerState.STANDBY)
            model.getPlayerList().get(num-1).setPlayerState(PlayerState.ENDING);
        else model.getPlayerList().get(num-1).setPlayerState(PlayerState.STANDBY);

        for(int i =0; i< model.getPlayerList().size();i++)

            if(!model.getPlayerList().get(i).getPlayerState().equals(PlayerState.STANDBY))

                plNotStandby.add(model.getPlayerList().get(i));

        if(plNotStandby.size()==1)controller.setEndGame();

        askEvent(playing);

    }

    public void pass(PlayerZone playing) {

        this.event = new ActionEvent();

        event.setId(11);

        event.setPlayer(playing.getIDPlayer());

        controller.setActionEvent(event);
    }

    public void askEvent(PlayerZone playing){

        if(playing.getPlayerState()==PlayerState.STANDBY)return;
        this.event = new ActionEvent();
        int id = 0;


        System.out.println("Insert 1 to place a die");
        System.out.println("Insert 2 for cards 2 and 3");
        System.out.println("insert 3 to for card 4");
        System.out.println("insert 4 to for card 9");
        System.out.println("insert 5 to for card 5");
        System.out.println("insert 6 to for card 1");
        System.out.println("insert 8 to for card 7 or 8");
        System.out.println("insert 7 to for card 6,10 or 11");
        System.out.println("Insert 9 to continue with card 11 ");
        System.out.println("Insert 10 for card 12 ");
        System.out.println("Insert 11 to pass the turn");


        id = askId();

        int line = 0;
        int col = 0;
        int die = 0;
        int card = 0;
        if (id == 1) {


            line = askLine();
            col = askCol();
            die = askDie();

            Box[][] board = playing.getPlayerBoard().getBoardMatrix();
            event.setId(id);
            event.setDieFromDraft(model.getDraftPool().getInDraft().get(die - 1));
            event.setToBox1(board[line - 1][col - 1]);
            event.setPlayer(playing.getIDPlayer());
        }
        if (id == 2) {

            System.out.println("card 2 or 3?");
            card = askCard();

            line = askLine();
            col = askCol();


            Box[][] board = playing.getPlayerBoard().getBoardMatrix();
            event.setId(id);
            event.setCardID(card);
            event.setFromBox1(board[line - 1][col - 1]);
            event.setPlayer(playing.getIDPlayer());

            line = askLine();
            col = askCol();

            event.setToBox1(board[line - 1][col - 1]);

        }

        if (id == 3) {

            card = 4;
            Box[][] board = playing.getPlayerBoard().getBoardMatrix();
            ArrayList<Box> fromList = new ArrayList<Box>();
            ArrayList<Box> toList = new ArrayList<Box>();
            event.setId(id);
            event.setCardID(card);
            event.setPlayer(playing.getIDPlayer());
            System.out.println("first movement ");
            line = askLine();
            col = askCol();
            fromList.add(board[line - 1][col - 1]);
            System.out.println("to");
            line = askLine();
            col = askCol();
            toList.add(board[line - 1][col - 1]);
            System.out.println("second movement");
            line = askLine();
            col = askCol();
            fromList.add(board[line - 1][col - 1]);
            System.out.println("to");
            line = askLine();
            col = askCol();
            toList.add(board[line - 1][col - 1]);
            event.setFromBoxList(fromList);
            event.setToBoxList(toList);

        }

        if (id == 4) {
            line = askLine();
            col = askCol();
            die = askDie();
            event.setCardID(9);
            Box[][] board = playing.getPlayerBoard().getBoardMatrix();
            event.setId(id);
            event.setDieFromDraft(model.getDraftPool().getInDraft().get(die - 1));
            event.setToBox1(board[line - 1][col - 1]);
            event.setPlayer(playing.getIDPlayer());
        }

        if (id == 7) {

            System.out.println("card 6,10 or 11?");
            event.setId(id);
            card = askCard();

            event.setCardID(card);
            event.setPlayer(playing.getIDPlayer());

            die = askDie();
            event.setDieFromDraft(model.getDraftPool().getInDraft().get(die - 1));


        }

        if (id == 6) {

            event.setId(id);
            event.setPlayer(playing.getIDPlayer());
            event.setCardID(1);

            die = askDie();
            event.setDieFromDraft(model.getDraftPool().getInDraft().get(die - 1));
            System.out.println("insert 1 to increment, 2 to decrement");
            id = askId();

            if (id == 1) event.setInDeCrement("increment");
            if (id == 2) event.setInDeCrement("decrement");


        }

        if (id == 5) {

            event.setId(id);
            event.setPlayer(playing.getIDPlayer());
            event.setCardID(5);

            die = askDie();
            event.setDieFromDraft(model.getDraftPool().getInDraft().get(die - 1));

            System.out.println("insert number of turn");
            id = askId();

            while (id > model.getRoundTrackInt().getRoundTrackTurnList().size())
                id = askId();
            die = askDie();

            event.setDieFromRoundTrack(model.getRoundTrackInt().getRoundTrackTurnList().get(id - 1).getDiceList().get(die - 1));


        }

        if (id == 8) {

            System.out.println("card 7 or 8?");
            event.setId(id);
            card = askCard();
            event.setCardID(card);
            event.setPlayer(playing.getIDPlayer());


        }


        if (id == 9) {

            event.setId(id);
            event.setCardID(11);
            event.setPlayer(playing.getIDPlayer());
            System.out.println("insert number to set on the die ");
            id = askId();
            event.setNumber(id);
            Box[][] board = playing.getPlayerBoard().getBoardMatrix();
            line = askLine();
            col = askCol();
            event.setToBox1(board[line - 1][col - 1]);


        }

        if (id == 10) {

            Box[][] board = playing.getPlayerBoard().getBoardMatrix();
            ArrayList<Box> fromList = new ArrayList<Box>();
            ArrayList<Box> toList = new ArrayList<Box>();
            event.setId(id);
            event.setCardID(12);
            event.setPlayer(playing.getIDPlayer());

            System.out.println("insert number of turn");
            id = askId();

            while (id > model.getRoundTrackInt().getRoundTrackTurnList().size())
                id = askId();
            die = askDie();

            event.setDieFromRoundTrack(model.getRoundTrackInt().getRoundTrackTurnList().get(id - 1).getDiceList().get(die - 1));

            System.out.println("first movement ");
            line = askLine();
            col = askCol();
            fromList.add(board[line - 1][col - 1]);
            System.out.println("to");
            line = askLine();
            col = askCol();
            toList.add(board[line - 1][col - 1]);
            System.out.println("second movement");
            line = askLine();
            col = askCol();
            fromList.add(board[line - 1][col - 1]);
            System.out.println("to");
            line = askLine();
            col = askCol();
            toList.add(board[line - 1][col - 1]);
            event.setFromBoxList(fromList);
            event.setToBoxList(toList);

        }
        if (id == 11) {
            event.setId(id);
            event.setPlayer(playing.getIDPlayer());

        }

        if (id == 12) event.setId(id);

        controller.setActionEvent(event);
    }

    public int askId() {

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

    public int askCard() {

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
