package it.polimi.ingsw.LM26.controller;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.GamePhases.CentralPhase;
import it.polimi.ingsw.LM26.model.GamePhases.Game;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static it.polimi.ingsw.LM26.model.GamePhases.RoundState.FINISHED;

public class Match {

    private PlayerZone playing;
    private boolean result=false;
    private Model model;

    public Match(Model model, Controller controller ) {

        this.model=model;

        Game game = new Game(model.getPlayerList(), model.getDecks(), model.getOnBoardCards());  //initialPhase
        game.getPhase().doAction(game, model.getPlayerList());    //centralPhase
        CentralPhase centralPhase = (CentralPhase) game.getPhase();


        for(int i=0; i<10; i++){
            //primo giocatore
            playing =centralPhase.getCurrentRound().nextPlayer(model.getPlayerList(), centralPhase.getTurn(), 0);

            // x ciascun giocatore
            while (centralPhase.getCurrentRound().getRoundState() != FINISHED) {

                //event=askeventfromserver(); metodo da implementare sul server che restituisce il primo elemento della lista di eventi
                //checkEvent(event);
                //TODO
                //controllo che il giocatore dell'evento sia quello corrente

                System.out.println("sta giocando " + playing.getName());
                playing.getPlayerBoard().printCard();
                model.getDraftPool().printDraftPool();


                while (!result) {

                    //crea un nuovo evento
                    ActionEvent event = new ActionEvent();

                    int id=0;
                    System.out.println("inserisci id 1 o 9");
                    BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
                    try {
                        id = Integer.parseInt(read.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if(id!=9) {

                        //prendi indice dado
                        //prendi indici i j

                        int line = 0;
                        System.out.println("inserisci riga");
                        read = new BufferedReader(new InputStreamReader(System.in));
                        try {
                            line = Integer.parseInt(read.readLine());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        int col = 0;
                        System.out.println("inserisci colonna");
                        read = new BufferedReader(new InputStreamReader(System.in));
                        try {
                            col = Integer.parseInt(read.readLine());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        int die = 0;
                        System.out.println("inserisci inserisci dado");
                        read = new BufferedReader(new InputStreamReader(System.in));
                        try {
                            die = Integer.parseInt(read.readLine());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //prendi gli oggetti
                        Box[][] board = playing.getPlayerBoard().getBoardMatrix();
                        event.setId(id);
                        event.setDieFromDraft((Die) model.getDraftPool().getInDraft().get(die - 1));
                        event.setToBox1(board[line - 1][col - 1]);
                        event.setPlayer(playing.getIDPlayer());
                    }
                    event.setId(id);

                    if (controller.checkEvent(event)) {
                        System.out.println("done");
                        result = true;
                    } else System.out.println("error");

                }


                centralPhase.getCurrentRound().endAction(centralPhase.getTurn(), model.getRoundTrackInt(), model.getDraftPool(), centralPhase.getCurrentRound().getCurrentPlayer());
                playing = centralPhase.getCurrentRound().nextPlayer(model.getPlayerList(), centralPhase.getTurn(), 0);
                result=false;
            }

            System.out.println("fine turno " + i);
            //finiti i turni in un round

            centralPhase.nextRound(centralPhase.getCurrentRound(), game);
        }

    }
}
