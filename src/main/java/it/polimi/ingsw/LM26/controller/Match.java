package it.polimi.ingsw.LM26.controller;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.controller.GamePhases.CentralPhase;
import it.polimi.ingsw.LM26.controller.GamePhases.Game;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static it.polimi.ingsw.LM26.controller.GamePhases.RoundState.FINISHED;

public class Match {

    private PlayerZone playing;
    private boolean result=false;
    //private Model model;
    private ActionEvent event;

    public Match(Model model, Controller controller ) {


        Game game = new Game(model.getPlayerList(), model.getDecks(), model.getOnBoardCards());  //initialPhase
        game.getPhase().doAction(game, model.getPlayerList());    //centralPhase
        CentralPhase centralPhase = (CentralPhase) game.getPhase();


        for(int i=0; i<10; i++){

            //primo giocatore
            playing =centralPhase.getCurrentRound().nextPlayer(model.getPlayerList(), centralPhase.getTurn(), 0);

            // x ciascun giocatore
            while (centralPhase.getCurrentRound().getRoundState() != FINISHED) {         //1


                //while(secondo turno del player  è false)                               //2
                //se è la prima scelta    controller.getView().showActionMenu();
                //se è la seconda:
                    //se ha scleto una carta    controller.getView().showPlaceDie();
                    //se ha piazato un dado     controller.getView().showChooseCard();


                //la view crea l'evento e fa notifyall che all'interno ha update
                
                //while (event== null )
                // ovvero aspetto che la view faccia update


                //TODO
                //if(event.getPlayer== player corrente ){ controllo che il giocatore dell'evento sia quello corrente
                            //esegue l'azione dell'evento
                //else view.showWrongPlayer(); e rientra nel while 2


                System.out.println(playing.getName()+" is playing "  );
                playing.getPlayerBoard().printCard();
                model.getDraftPool().printDraftPool();


                while (!result) {


                   // while (event == null )    //ASPETTA DI NUOVO L?EVENTO

                    //TODO
                    ///////////////////////////////////////////////////////DELETE THIS PART

                    event = new ActionEvent();
                    int id=0;
                    System.out.println("insert id 1 o 9");
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
                        System.out.println("insert line");
                        read = new BufferedReader(new InputStreamReader(System.in));
                        try {
                            line = Integer.parseInt(read.readLine());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        int col = 0;
                        System.out.println("insert col");
                        read = new BufferedReader(new InputStreamReader(System.in));
                        try {
                            col = Integer.parseInt(read.readLine());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        int die = 0;
                        System.out.println("insert die");
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
                    ///////////////////////////////////////////

                    if (controller.checkEvent(event)) {
                        System.out.println("done");
                        // view.showOK()
                        result = true;
                    } else
                        System.out.println("error");
                        //view.showNO()
                        //view.showReduAction()

                }


                centralPhase.getCurrentRound().endAction(centralPhase.getTurn(), model.getRoundTrackInt(), model.getDraftPool(), centralPhase.getCurrentRound().getCurrentPlayer());
                playing = centralPhase.getCurrentRound().nextPlayer(model.getPlayerList(), centralPhase.getTurn(), 0);
                result=false;
            }

            //finiti i turni in un round
            System.out.println("end turn " + i);
            model.getRoundTrackInt().dump();


            centralPhase.nextRound(centralPhase.getCurrentRound(), game);
            //void showTurnEndPhase();
        }

        //void showPoints();
    }

    public void setActionEvent(ActionEvent newEvent) {
        this.event = newEvent;
    }
}
